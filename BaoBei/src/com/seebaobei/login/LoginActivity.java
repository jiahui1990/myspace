package com.seebaobei.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.huamaitel.api.HMDefines.UserInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huamaitel.api.HMDefines;
import com.jve.util.AsyncTaskUtils;
import com.jve.util.CountDownUtils;
import com.jve.util.DialogUtil;
import com.jve.util.NetUtils;
import com.jve.util.PackageUtils;
import com.jve.util.SharedPreferencesUtils;
import com.jve.util.UIHelperUtils;
import com.seebaobei.Constants;
import com.seebaobei.R;
import com.seebaobei.appmain.MainActivity;
import com.seebaobei.appmain.MainApp;
import com.seebaobei.base.BaseActivity;
import com.seebaobei.base.StackManager;
import com.seebaobei.findPassword.FindPasswordActivity;
import com.seebaobei.service.OnlineService;
import com.seebaobei.video.ViewMonitor;

public class LoginActivity extends BaseActivity implements OnClickListener{

	private EditText username,password;
	private TextView findPswd,version;
	private Button loginButton,getCodeButton;
	private CheckBox rememberCheckBox;
	private CountDownUtils countDownUtils;
	private AlertDialog.Builder builder = null;
	private SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(this);
	private PackageUtils packageUtils;
	
	private static final String TAG = "LoginActivity";
	private static final int EVENT_CONNECT_SUCCESS = 1;
	private static final int EVENT_CONNECT_FAIL = 2;
	private static final int EVENT_LOGIN_SUCCESS = 3;
	private static final int EVENT_LOGIN_FAIL = 4;
	private Handler handler;
	private Thread mServerThread = null;
	private HMDefines.LoginServerInfo info = null;
	
	private JSONObject outObj;
	private String phoneCode="";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		StackManager.getStackManager().pushActivity(this);
		super.netWorkStatus(this);
		this.initViews();
		this.initEvents();
		registerHander();
		Intent service = new Intent(LoginActivity.this, OnlineService.class);
		stopService(service);
	}

	
	
	@Override
	public void initViews() {
		packageUtils = new PackageUtils(this);
		findPswd = (TextView) findViewById(R.id.jve_id_loginPage_findPswd);
		version = (TextView) findViewById(R.id.jve_id_loginPage_version);
		username = (EditText) findViewById(R.id.jve_id_loginPage_username);
		password = (EditText) findViewById(R.id.jve_id_loginPage_password);
		rememberCheckBox = (CheckBox) findViewById(R.id.jve_id_loginPage_keepPswd);
		loginButton = (Button) findViewById(R.id.jve_id_loginPage_loginBtn);
		version.setText(getResources().getString(R.string.jve_str_loginPage_str8)+"(v"+packageUtils.getVersionName()+")");
		
		Drawable drawable1 = getResources().getDrawable(R.drawable.user_icon);
        drawable1.setBounds(0, 0, 40, 40);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        Drawable drawable2 = getResources().getDrawable(R.drawable.key_icon);
        drawable2.setBounds(0, 0, 40, 40);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        
        username.setCompoundDrawables(drawable1, null, null, null);//只放左边
        password.setCompoundDrawables(drawable2, null, null, null);//只放左边
		
		Map<String, ?> map = sharedPreferencesUtils.getSharePreference(Constants.JVE_FILE_SYS_STATUS);
		if (map != null && !map.isEmpty()) {
			if(map.get("keepPassword") != null) {
				if (Boolean.parseBoolean(map.get("keepPassword").toString())) {
					rememberCheckBox.setChecked(true);
					password.setText(map.get("password").toString());
					username.setText(map.get("username").toString());
				}
			}
		}
	
	}



	@Override
	public void initEvents() {
		loginButton.setOnClickListener(this);
		findPswd.setOnClickListener(this);
	}


	@Override
	public boolean vertifyFrom() {
		if(super.isEmpty(username.getText().toString()) && super.isEmpty(password.getText().toString())) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG4, this);
		}else if (super.isEmpty(username.getText().toString())) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG2, this);
		}else if(StringUtils.isEmpty(password.getText().toString())) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG3, this);
		}else {
			return true;
		}
		return super.vertifyFrom();
	}



	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.jve_id_loginPage_findPswd:
			Intent intent = new Intent(this, FindPasswordActivity.class);
			startActivity(intent);
			break;
		case R.id.jve_id_loginPage_loginBtn:
			if(vertifyFrom()) {
				checkLogin();
			}
			break;
		case R.id.jve_id_login_getCodeBtn:
			countDownUtils.start();
			sendCode();
			break;
		default:
			break;
		}
	}

	/**
	 * 正确登录
	 */
	private void jumpMainPage(String videos){
		JSONObject obj = JSON.parseObject(videos);
		String vString = JSON.toJSONString(obj.get("monitor"));
		List<ViewMonitor> videosList = JSON.parseArray(vString, ViewMonitor.class);
		Log.i("VIDEOS", vString);
		saveLoginInfo();
		MainActivity.actionStart(this, videosList);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			StackManager.getStackManager().popAllActivitys();
			this.finish();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	
	private void registerHander() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				//closeWaitDoalog();
				switch (msg.what) {
				case EVENT_LOGIN_SUCCESS:
					saveLoginInfo();
					
					Intent service = new Intent(LoginActivity.this, OnlineService.class);
					startService(service);
					
					/*Intent intent = new Intent();
					intent.setClass(LoginActivity.this, MainActivity.class);
					startActivity(intent);*/
					
					JSONObject obj = JSON.parseObject(outObj.getString("ret_info"));
					String vString = JSON.toJSONString(obj.get("monitor"));
					List<ViewMonitor> videosList = JSON.parseArray(vString, ViewMonitor.class);
					Log.i("VIDEOS", vString);
					saveLoginInfo();
					MainActivity.actionStart(LoginActivity.this, videosList);
					
					LoginActivity.this.finish();
					Log.i(TAG, "login success");
					break;

				case EVENT_LOGIN_FAIL:
					MainApp.getJni().disconnectServer(MainApp.serverId);
					UIHelperUtils.showShortToast("login fail", LoginActivity.this);
					break;

				case EVENT_CONNECT_SUCCESS:
					int result = MainApp.getJni().getDeviceList(MainApp.serverId);
					if (result != HMDefines.HMEC_OK) {
						sendEmptyMessage(EVENT_LOGIN_FAIL);
						return;
					}
					// step 2: Get user information.
					UserInfo userInfo = MainApp.getJni().getUserInfo(MainApp.serverId);
					if (userInfo == null) {
						sendEmptyMessage(EVENT_LOGIN_FAIL);
						return;
					}
					
					/**
					 * TODO:判断选择： huamaiyun和see1000中需要添加userInfo.useTransferService !=8
					 * 
					 * 这个判断 seebao中需要去掉，否则容易报错！
					 */
					// step 3: Get transfer service.
					// if (userInfo.useTransferService != 0&&userInfo.useTransferService !=8) {
					if (userInfo.useTransferService != 0) {
						result = MainApp.getJni().getTransferInfo(MainApp.serverId);
						if (result != HMDefines.HMEC_OK) {
							sendEmptyMessage(EVENT_LOGIN_FAIL);
							return;
						}
					}

					// step 4: Get tree id.
					MainApp.treeId = MainApp.getJni().getTree(MainApp.serverId);
					sendEmptyMessage(EVENT_LOGIN_SUCCESS);
					break;

				case EVENT_CONNECT_FAIL:
					//提示登录失败原因。
					UIHelperUtils.showShortToast(msg.obj.toString(), LoginActivity.this);
					break;
				}
			}
		};
	}
	
	/**
	 * 登录前的检测
	 * @return
	 */
	private void checkLogin() {
		
		String loginReturnString = null;
		try {
			loginReturnString = new AsyncTaskUtils().execute(buildParams()).get();
			//loginReturnString = new AsyncTaskUtils().execute(buildLoginParamsOfGet()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (super.isEmpty(loginReturnString)) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG12, this);
		} else {
			outObj = JSON.parseObject(loginReturnString);
			if("MSG6003".equalsIgnoreCase(outObj.getString("ret"))) { 
				//ret_info:用户拥有的监控集合(见对象结构2)
				//jumpMainPage(obj.getString("ret_info")); //使用monitor得到视频信息，弃用
				executeServerThread();
			//登陆设备超过三个，输入验证码。
			} else if ("MSG4007".equalsIgnoreCase(outObj.getString("ret"))) {
				//saveLoginInfo();
				showDialogSelected(outObj.getString("ret_info"));
			} else if ("MSG4006".equalsIgnoreCase(outObj.getString("ret"))) {
				showDialogSelected1(outObj.getString("ret_info"));
			}
			else {
				UIHelperUtils.showShortToast(outObj.getString("ret_info"), this);
			}
		}
	}
	
	/**
	 * 使用手机验证码登录
	 * @return
	 */
	private void checkLoginWithPhoneCode() {
		
		String loginReturnString = null;
		try {
			loginReturnString = new AsyncTaskUtils().execute(buildParamsWithCode(phoneCode)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (super.isEmpty(loginReturnString)) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG12, this);
		} else {
			outObj = JSON.parseObject(loginReturnString);
			if("MSG6003".equalsIgnoreCase(outObj.getString("ret"))) { 
				//ret_info:用户拥有的监控集合(见对象结构2)
				//jumpMainPage(obj.getString("ret_info")); 
				executeServerThread();
			//登陆设备超过三个，输入验证码。
			} else if ("MSG4007".equalsIgnoreCase(outObj.getString("ret"))) {
				showDialogSelected(outObj.getString("ret_info"));
			} else if ("MSG4006".equalsIgnoreCase(outObj.getString("ret"))) {
				showDialogSelected1(outObj.getString("ret_info"));
			}
			else {
				UIHelperUtils.showShortToast(outObj.getString("ret_info"), this);
			}
		}
	}
	
	private void executeServerThread(){
		mServerThread = new Thread() {
			@Override
			public void run() {
				// 平台相关
				info = new HMDefines.LoginServerInfo();
				info.ip = Constants.SERVER_VIDEO_ADDR; // 平台地址
				info.port = Constants.SERVER_PORT; // 平台端口
				//info.user = username.getText().toString(); // 用户名
				//info.password = password.getText().toString(); // 密码
				info.user = outObj.getString("videoAccountUsername"); // 用户名
				info.password = outObj.getString("videoAccountPassword"); // 密码
				info.model = android.os.Build.MODEL; // 手机型号
				info.version = android.os.Build.VERSION.RELEASE; // 手机系统版本号

				String error = jni_connectServer();
				if (error != null) {
					Log.e(TAG, "Connect server fail.");
					sendEmptyMessage(EVENT_CONNECT_FAIL, error);
				} else {
					Log.i(TAG, "Connect server success.");
					sendEmptyMessage(EVENT_CONNECT_SUCCESS);
				}
			}
		};
		mServerThread.start();
	}
	
	private void showDialogSelected1(final String voidos) {
		if(builder != null){
			builder = null;
		}
		if(builder == null) {
			builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
			builder.setTitle(R.string.jve_str_MSG14);
			builder.setIcon(android.R.drawable.ic_dialog_info);
			builder.setNegativeButton(R.string.jve_str_public_cancel, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			}).setPositiveButton(R.string.jve_str_public_confirm, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					jumpMainPage(voidos);
				}
			}).show();
		}
	}
	
	/**
	 * 对话框选择
	 * @return
	 */
	private void showDialogSelected(final String voidos) {
		if(builder != null){
			builder = null;
		}
		if(builder == null) {
			builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
			builder.setTitle(R.string.jve_str_MSG13);
			builder.setIcon(android.R.drawable.ic_dialog_info);
			final View view = getDialogView();
			builder.setView(view);
			getCodeButton = (Button) view.findViewById(R.id.jve_id_login_getCodeBtn);
			getCodeButton.setOnClickListener(this);
			countDownUtils = new CountDownUtils(120000, 1000, getCodeButton);
			countDownUtils.start();
			builder.setNegativeButton(R.string.jve_str_public_cancel, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					DialogUtil.dialogClose(dialog);
				}
			}).setPositiveButton(R.string.jve_str_public_confirm, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					EditText editText = (EditText) view.findViewById(R.id.jve_id_login_code);
					if(TextUtils.isEmpty(editText.getText())) {
						DialogUtil.dialogOpen(dialog);
						UIHelperUtils.showShortToast(R.string.jve_str_MSG7, LoginActivity.this);
					}else {
						//验证码正确
						phoneCode = editText.getText().toString();
						checkLoginWithPhoneCode();
						/*if(editText.getText().toString().equals(phoneCode)) {
							jumpMainPage(voidos);
						} else {
							//UIHelperUtils.showShortToast(resId, LoginActivity.this);
							UIHelperUtils.showShortToast("验证码错误，请重新输入！", LoginActivity.this);
						}*/
					}
				}
			}).show();
		}
	}
	
	private void sendEmptyMessage(int msgId) {
		sendEmptyMessage(msgId, null);
	}
	
	private void sendEmptyMessage(int msgId, String error) {
		if (handler == null) {
			return;
		}
		Message msg = new Message();
		msg.what = msgId;
		msg.obj = error;
		handler.sendMessage(msg);
	}
	
	public String jni_connectServer() {
		StringBuilder error = new StringBuilder();
		MainApp.serverId = MainApp.getJni().connectServer(info, error);
		if (MainApp.serverId == -1) {
			return error.toString();
		}
		return null;
	}
	
	/**
	 * 获取对话框类容
	 * @return
	 */
	private View getDialogView() {
		View view = null;
		LayoutInflater layoutInflater = this.getLayoutInflater();
		view = layoutInflater.inflate(R.layout.validation_code, null);
		return view;
	}
	
	/**
	 * QinChuan
	 * 为请求构建参数
	 * @return
	 */
	private Map<String, Object> buildParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		String paramString = "{\"msgID\":\"sys_login\",\"userName\":\""+username.getText().toString()+"\",\"passwd\":\""+password.getText().toString()+"\",\"mac\":\""+NetUtils.getLocalMacAddressFromWifiInfo(this)+"\"}";
		Log.i("HHH", paramString);
		params.put(Constants.JVE_URL_PARAMS, paramString);
		params.put(Constants.JVE_URL, Constants.WEB_URL);
		return params;
	}
	
	private Map<String, Object> buildParamsWithCode(String phoneCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		String paramString = "{\"msgID\":\"sys_login\",\"userName\":\""+username.getText().toString()+"\",\"passwd\":\""+password.getText().toString()
				+"\",\"mac\":\""+NetUtils.getLocalMacAddressFromWifiInfo(this)+"\",\"phoneCode\":\""+phoneCode+"\"}";
		Log.i("HHH", paramString);
		params.put(Constants.JVE_URL_PARAMS, paramString);
		params.put(Constants.JVE_URL, Constants.WEB_URL);
		return params;
	}
	
	private Map<String, Object> buildLoginParamsOfGet() {
		Map<String, Object> params = new HashMap<String, Object>();
		String paramString = "?username=ljh&password=123456";
		Log.i("HHH", paramString);
		params.put(Constants.JVE_URL_PARAMS, paramString);
		params.put(Constants.JVE_URL, Constants.WEB_URL+paramString);
		return params;
	}
	/**
	 * 发送短信验证码
	 */
	private void sendCode() {
		String sendCodeReturnString = null;
		try {
			sendCodeReturnString = new AsyncTaskUtils().execute(buildSendCodeParams()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (super.isEmpty(sendCodeReturnString)) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG12, this);
		} else {
			JSONObject obj = JSON.parseObject(sendCodeReturnString);
			if(!"MSG0000".equalsIgnoreCase(obj.getString("ret"))) { 
				UIHelperUtils.showShortToast(obj.getString("ret_info"), this);
			} else {
				Log.i("JJJ", obj.getString("ret_info"));
			}
		}
	}
	/**
	 * QinChuan
	 * 为请求构建参数
	 * @return
	 */
	private Map<String, Object> buildSendCodeParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		String paramString = "{\"msgID\":\"login_sendphonecode\",\"userName\":\""+username.getText().toString()+"\"}";
		params.put(Constants.JVE_URL_PARAMS, paramString);
		params.put(Constants.JVE_URL, Constants.WEB_URL);
		return params;
	}
	
	/**
	 * 保存登录信息
	 */
	private void saveLoginInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username.getText().toString().trim());
		map.put("password", password.getText().toString());

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("username", username.getText().toString().trim());
		map1.put("password", password.getText().toString());
		map1.put("keepPassword", rememberCheckBox.isChecked());
		
		//MainApp.username = username.getText().toString().trim();
		//MainApp.password = password.getText().toString().trim();

		sharedPreferencesUtils.saveSharePreference(Constants.JVE_FILE_LOGIN, map);
		sharedPreferencesUtils.saveSharePreference(Constants.JVE_FILE_SYS_STATUS, map1);
		
	}
}

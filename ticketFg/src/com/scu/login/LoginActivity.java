package com.scu.login;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.scu.R.id;
import com.scu.R.layout;
import com.scu.R.menu;
import com.scu.R.string;
import com.scu.R;
import com.scu.main.MainActivity;
import com.scu.utils.NetWorkUtils;
import com.scu.utils.ToastUtil;
import com.scu.utils.Utils;

public class LoginActivity extends Activity {
	
	private EditText et_username;
	private EditText et_password;
	private String userAccount;
	private String userPassword;
	public static final int NET_FAIL=-1;
	public static final int LOGIN_SUCCESS=100;
	public static final int LOGIN_FAIL=101;
	private String url;
	ProgressDialog pdDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		try {
			url = getResources().getString(R.string.BASE_URL)+"/salorAction_login?";
			setView();
			setListner();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	private void setView() {
		et_username = (EditText)findViewById(R.id.editText_username);
		et_password = (EditText)findViewById(R.id.editText_password);
	}
	
	private void setListner() {
		findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					boolean bool_username=false,bool_password=false;
					userAccount = et_username.getText().toString();
					userPassword = et_password.getText().toString();
					if (userAccount==null||userAccount.trim().equals("")) {
						et_username.setError("请输入用户名");
					}else {
						bool_username = true;
					}
					if (userPassword==null||userPassword.trim().equals("")) {
						et_password.setError("请输入密码");
					}else {
						bool_password = true;
					}
					if (bool_username&&bool_password) {
						if (!NetWorkUtils.getNetworkInfo(LoginActivity.this)) {
							ToastUtil.showShort(LoginActivity.this, "设备未联网，无法登陆");
						}else {
							pdDialog = ProgressDialog.show(LoginActivity.this, "正在验证...", "请稍等...");
							url=url+"userAccount="+userAccount+"&userPassword="+userPassword;
							Log.i("url", url);
							new LoginUtil().login(LoginActivity.this,url);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/*findViewById(R.id.back_more_mysetpasswodndengu).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		findViewById(R.id.wangjimima_textView3).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
			}
		});*/
	}
	
/************************************************************************************************************************************/	

	public Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case NET_FAIL:
			{
				pdDialog.dismiss();
				Toast.makeText(LoginActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
			}break;
			case LOGIN_SUCCESS:
			{
				pdDialog.dismiss();
				Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				intent.putExtra("userAccount", userAccount);
				intent.putExtra("userPassword", userPassword);
				startActivity(intent);
				finish();
			}break;
			case LOGIN_FAIL:
			{
				pdDialog.dismiss();
				Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
			}break;

			default:
				break;
			}
		};
	};

}

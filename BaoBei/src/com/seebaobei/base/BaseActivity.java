package com.seebaobei.base;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.jve.util.NetUtils;
import com.jve.util.SharedPreferencesUtils;
import com.jve.util.UIHelperUtils;
import com.seebaobei.Constants;
import com.seebaobei.R;
import com.seebaobei.login.LoginActivity;

public class BaseActivity extends FragmentActivity {
	//private LayoutInflater inflater;
	
	
	private SharedPreferencesUtils sharedPreferencesUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//inflater.inflate(resource, null);
		Log.d("BaseActivity", getClass().getSimpleName());
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}

	/**
	 * 链接网络状�?判断
	 */
	public void netWorkStatus(Context context) {
		if(!NetUtils.isConnected(context)){
			/*AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.ggyoa_str_MSG4017);
	
			builder.setPositiveButton(R.string.ggyoa_str_public_confirm, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.dismiss();
				}
			}).show();*/
			UIHelperUtils.showLongToast(R.string.jve_str_MSG1, context);
			
		}
		/*boolean netSataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo  = cwjManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			netSataus = networkInfo.isAvailable();
		}

		if (!netSataus) {
			UIHelperUtils.showDefaultToast("没有可用的网�?, context);
		}*/
		/*if (!netSataus) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("没有可用的网�?);
			builder.setMessage("是否对网络进行设�?");
	
			builder.setPositiveButton("�?, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
	
				Intent mIntent = new Intent("/");
		
				ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
				mIntent.setComponent(comp);
				mIntent.setAction("android.intent.action.VIEW");
				startActivityForResult(mIntent,0); // 如果在设置完成后�?��再次进行操作，可以重写操作代码，在这里不再重�?
			}});
			builder.setNeutralButton("�?, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}}).show();
		}*/
	}
	/**
	 * 判断是否登录
	 * @return
	 */
	public boolean isLogin(){
		sharedPreferencesUtils = new SharedPreferencesUtils(this);
		Map<String, ?> map = sharedPreferencesUtils.getSharePreference(Constants.JVE_FILE_LOGIN);
		if (map != null && !map.isEmpty()) {// 已登�?
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 获取登录信息
	 * @return
	 */
	public Map<String, ?> getLoginInfo(){
		sharedPreferencesUtils = new SharedPreferencesUtils(this);
		Map<String, ?> map = sharedPreferencesUtils.getSharePreference(Constants.JVE_FILE_LOGIN);
		return map;
	}
	
	/**
	 * 初始化Activity头部
	 */
	public void initHeader() {
		
	}
	/**
	 * 初始化Activity尾部
	 */
	public void initFooter() {
		
	}
	/**
	 * 初始化页面控�?
	 */
	public void initViews() {
		
	}
	/**
	 * 进入页面时，初始化页面数�?
	 */
	public void initDatas() {
		
	}
	/**
	 * QinChuan
	 * 为页面控件添加监听事�?
	 */
	public void initEvents() {
		
	}
	/**
	 * QinChuan
	 * 表单验证
	 * @return
	 */
	public boolean vertifyFrom() {
		return false;
	}
	/**
	 * QinChuan
	 * 判断字段是否为空
	 * @return
	 */
	public boolean isEmpty(String str) {
		if(StringUtils.isEmpty(str == null ? null : str.trim())) {
			return true;
		}
		return false;
	}
	/**
	 * QinChuan
	 * 判断字段是否不为�?
	 * @return
	 */
	public boolean isNotEmpty(String str) {
		if(StringUtils.isNotEmpty(str == null ? null : str.trim())) {
			return true;
		}
		return false;
	}
	/**
	 * 页面数据加载提示
	 */
	public void loadingDataAlert(ViewGroup viewGroup, Context context, int layout) {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(layout, null);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		view.setLayoutParams(layoutParams);
		viewGroup.removeAllViews();
		viewGroup.addView(view);
	}
}

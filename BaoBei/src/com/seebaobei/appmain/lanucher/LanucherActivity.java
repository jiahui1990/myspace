package com.seebaobei.appmain.lanucher;


import com.seebaobei.Constants;
import com.seebaobei.R;
import com.seebaobei.appmain.MainActivity;
import com.seebaobei.appmain.MainApp;
import com.seebaobei.base.BaseActivity;
import com.seebaobei.base.StackManager;
import com.seebaobei.login.LoginActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LanucherActivity extends BaseActivity {

	boolean isFirstIn = false;
	
	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	private static final int GO_LOGIN = 1002;
	// 延迟3秒
	private static final long LANUCH_DELAY_MILLIS = 1000;

	/**
	 * Handler:跳转到不同界面
	 */
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case GO_HOME:
					goHome();
					break;
				case GO_GUIDE:
					goGuide();
					break;
				case GO_LOGIN:
					goLogin();
					break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lanucher);
		StackManager.getStackManager().pushActivity(this);
		init();
		MainApp.getJni().init();
	}

	private void init() {
		// 读取SharedPreferences中需要的数据
		// 使用SharedPreferences来记录程序的使用次数
		SharedPreferences preferences = getSharedPreferences(
				Constants.JVE_FILE_SYS_STATUS, MODE_PRIVATE);

		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
		isFirstIn = preferences.getBoolean(Constants.JVE_IS_FIRST_IN, true);

		// 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
		//if (!isFirstIn) {
			// 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
			/*if(isLogin()){
				mHandler.sendEmptyMessageDelayed(GO_HOME, LANUCH_DELAY_MILLIS);
			}else {*/
				mHandler.sendEmptyMessageDelayed(GO_LOGIN, LANUCH_DELAY_MILLIS);
			//}
			
		/*} else {
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, LANUCH_DELAY_MILLIS);
		}*/
	}
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MainApp.getJni().uninit();
	}

	/**
	 * 调至登录页
	 */
	private void goLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		this.startActivity(intent);
		//this.finish();
	}
	/**
	 * 跳至主页
	 */
	private void goHome() {
		Intent intent = new Intent(this, MainActivity.class);
		this.startActivity(intent);
		//this.finish();
	}
	/**
	 * 跳至向导页
	 */
	private void goGuide() {
		Intent intent = new Intent(this, GuideActivity.class);
		this.startActivity(intent);
		//this.finish();
	}

}

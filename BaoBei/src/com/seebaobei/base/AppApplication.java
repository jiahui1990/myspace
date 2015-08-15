package com.seebaobei.base;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;

public class AppApplication extends Application {
	
	private static AppApplication appApplication;
	/** 当前打开的activity列表 */
	public ArrayList<Activity> activityList;

	@Override
	public void onCreate() {
		super.onCreate();
		appApplication = this;
	}

	public static AppApplication getAppApplication() {
		if(appApplication == null) {
			appApplication = new AppApplication();
		}
		return appApplication;
	}

	/**
	 * 添加当前Activity在列表中
	 * QinChuan
	 * @param acitivity
	 */
	public void addActivity(Activity acitivity) {
		if(activityList == null){
			activityList = new ArrayList<Activity>();
		}
		activityList.add(acitivity);
	}
	
	/**
	 * 清空列表
	 * QinChuan
	 */
	public void clearActivity(){
		activityList.clear();
	}
	/**
	 * 遍历�?���?��Activity
	 * QinChuan
	 */
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		clearActivity();
		System.exit(0);
	}
}

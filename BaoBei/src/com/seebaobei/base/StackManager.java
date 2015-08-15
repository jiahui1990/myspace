package com.seebaobei.base;

import java.util.Stack;

import android.app.Activity;

public class StackManager {
	
	private static Stack<Activity> activityStack;
	private static StackManager instanceStackManager;

	/**
	 * 获取栈管理工�?
	 * QinChuan
	 * @return
	 */
	public static StackManager getStackManager() {
		if (instanceStackManager == null) {
			instanceStackManager = new StackManager();
		}
		return instanceStackManager;
	}
	
	/**
	 * 弹出栈顶Activity
	 * QinChuan
	 * @param activity
	 */
	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}
	
	/**
	 * 获取当前栈顶Activity
	 * QinChuan
	 * @return
	 */
	public Activity currentActivity() {
		//lastElement()获取�?���?��子元素，这里是栈顶的Activity
		if(activityStack == null || activityStack.size() ==0){
			return null;
		}
		Activity activity = (Activity) activityStack.lastElement();
		return activity;
	}
	
	/**
	 * 将当前Activity推入栈中
	 * QinChuan
	 * @param activity
	 */
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	
	/**
	 * 弹出指定的clsss�?��栈的clsss顶部的所有Activity
	 * QinChuan
	 * @param clsss  指定的类
	 */
	public void popTopActivitys(Class clsss) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(clsss)) {
				break;
			}
			popActivity(activity);
		}
	}
	
	/**
	 * 弹出栈中�?��Activity
	 * QinChuan
	 */
	public void popAllActivitys() {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			popActivity(activity);
		}
	}
}

package com.jve.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageUtils {
	private Context context;
	private PackageManager manager;
	private PackageInfo info;

	public PackageUtils(Context context) {
		this.context = context;
		init();
	}

	/**
	 * 初始化数据
	 */
	public void init() {
		manager = context.getPackageManager();
		try {
			info = manager.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取版本号
	 * @return
	 */
	public int getVersionCode() {
		return info.versionCode;
	}

	/**
	 * 获取版本名称
	 * @return
	 */
	public String getVersionName() {
		return info.versionName;
	}
	/**
	 * 获取应用程序名称
	 * @return
	 */
	public String getAppName() {
		return context.getResources().getString(info.applicationInfo.labelRes);
	}

	/**
	 * 是否有更新版本
	 * @param oldVersion
	 * @param newVersion
	 * @return
	 */
	public boolean isUpgradeVersion(int oldVersion, int newVersion) {
		boolean flag = false;
		flag = (newVersion != oldVersion) ? true : false;
		return flag;
	}
}

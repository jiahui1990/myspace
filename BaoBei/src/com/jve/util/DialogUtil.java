package com.jve.util;

import java.lang.reflect.Field;

import android.content.DialogInterface;

public class DialogUtil {
	/**
	 * 关闭对话框
	 * QinChuan
	 * @param dialog
	 */
	public static void dialogClose(DialogInterface dialog) {
		// 需要关闭对话框
		Field field = null;
		try {
			field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(dialog, true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保持对话框开着
	 * QinChuan
	 * @param dialog
	 */
	public static void dialogOpen(DialogInterface dialog) {
		Field field = null;
		try {
			field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(dialog, false);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}

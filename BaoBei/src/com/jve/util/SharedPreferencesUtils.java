package com.jve.util;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtils {
	
	private Context context;

	public SharedPreferencesUtils(Context context) {
		this.context = context;
	}

	/*public boolean saveLoginMsg(String name, String password) {
		boolean flag = false;
		// 不要加后缀名，系统自动以.xml的文件保存
		SharedPreferences preferences = context.getSharedPreferences("login",
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("username", name);
		editor.putString("password", password);
		flag = editor.commit();
		return flag;
	}*/

	/**
	 * 将Map保存至指定的共享参数文件
	 * @param fileName 共享参数文件名
	 * @param map
	 * @return
	 */
	public boolean saveSharePreference(String fileName, Map<String, Object> map) {
		boolean flag = false;
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object object = entry.getValue();
			if (object instanceof Boolean) {
				Boolean new_name = (Boolean) object;
				editor.putBoolean(key, new_name);
			} else if (object instanceof Integer) {
				Integer integer = (Integer) object;
				editor.putInt(key, integer);
			} else if (object instanceof Float) {
				Float f = (Float) object;
				editor.putFloat(key, f);
			} else if (object instanceof Long) {
				Long l = (Long) object;
				editor.putLong(key, l);
			} else if (object instanceof String) {
				String s = (String) object;
				editor.putString(key, s);
			}
		}
		flag = editor.commit();
		return flag;
	}

	/**
	 * 通过文件名获取共享参数里面的所有参数
	 * @param fileName
	 * @return
	 */
	public Map<String, ?> getSharePreference(String fileName) {
		Map<String, ?> map = null;
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		map = preferences.getAll();
		return map;
	}
	/**
	 * 清理指定文件的共享参数
	 * @param fileName
	 */
	public boolean clearnSharePreference(String fileName){
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		if(preferences == null){
			return true;
		}
		Editor editor = preferences.edit();
		editor.clear();
		return editor.commit();
	}
	
	/**
	 * 修改指定文件的共享参数
	 * QinChuan
	 * @param fileName
	 * @param field
	 */
	public boolean modifySharePreference(String fileName, String field, Object value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		
		if (value instanceof Boolean) {
			Boolean new_name = (Boolean) value;
			editor.putBoolean(field, new_name);
		} else if (value instanceof Integer) {
			Integer integer = (Integer) value;
			editor.putInt(field, integer);
		} else if (value instanceof Float) {
			Float f = (Float) value;
			editor.putFloat(field, f);
		} else if (value instanceof Long) {
			Long l = (Long) value;
			editor.putLong(field, l);
		} else if (value instanceof String) {
			String s = (String) value;
			editor.putString(field, s);
		}
		return editor.commit();
	
	}
	
}

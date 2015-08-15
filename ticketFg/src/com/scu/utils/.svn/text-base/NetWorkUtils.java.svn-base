package com.scu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {
	
	/*
	 * �ж��ֻ��Ƿ�����trueΪ������
	 */
	 public static boolean getNetworkInfo(Context context) {
		 if (context == null) {
			return false;
		}
		ConnectivityManager cManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cManager==null) {
			return false;
		}
		NetworkInfo info = cManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()){ 
		       //do something 
		       //������ 
		        return true; 
		  }else{ 
		       //do something 
		       //�������� 
		        return false; 
		  } 
		
	}
	

}

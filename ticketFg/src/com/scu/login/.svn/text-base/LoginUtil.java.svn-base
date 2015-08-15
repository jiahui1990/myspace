package com.scu.login;

import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

import com.scu.utils.HttpClientPost;

import android.content.Context;

public class LoginUtil {
//{"flag":"true","password":null,"registerDAO_TAN":{},"register_time":null,"tel":"1234"}
	public void login(Context context,final String url) {
		final LoginActivity activity = (LoginActivity)context;
		new Thread(){
			public void run() {
				try {
					String data = HttpClientPost.get(url);
					if (data==null||data.trim().equals("")) {
						activity.handler.sendEmptyMessage(LoginActivity.NET_FAIL);
						Thread.currentThread().join();//请求完后 要结束线程
					}else {
						JSONObject object = new JSONObject(data);
						String msg = object.getString("msg");
						if (msg.equals("loginSuc")) {
							activity.handler.sendEmptyMessage(LoginActivity.LOGIN_SUCCESS);
							Thread.currentThread().join();
						}else {
							activity.handler.sendEmptyMessage(LoginActivity.LOGIN_FAIL);
							Thread.currentThread().join();
						}
					}
				} catch (Exception e) {
					try {
						activity.handler.sendEmptyMessage(LoginActivity.NET_FAIL);
						Thread.currentThread().join();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			};
		}.start();
		
	}
	
	

}

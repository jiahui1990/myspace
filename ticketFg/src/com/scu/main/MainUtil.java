package com.scu.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.scu.R;
import com.scu.service.PlayerService;
import com.scu.utils.Consts;
import com.scu.utils.HttpClientPost;

public class MainUtil {
	//测试代码---开通卡
	/*public void openCard(Context context,final String url) {
		final MainActivity activity = (MainActivity)context;
		new Thread(){
			public void run() {
				try {
					String data = HttpClientPost.get(url);
					if (data==null||data.trim().equals("")) {
						activity.handler.sendEmptyMessage(Consts.NET_FAIL);
						Thread.currentThread().join();//请求完后 要结束线程
					}else {
						JSONObject object = new JSONObject(data);
						String msg = object.getString("msg");
						if ("openSuc".equals(msg)) {
							activity.handler.sendEmptyMessage(Consts.OPEN_SUCCESS);
						}else {
							activity.handler.sendEmptyMessage(Consts.COMMON_FAIL);
						}
						Thread.currentThread().join();
					}
				} catch (Exception e) {
					try {
						activity.handler.sendEmptyMessage(Consts.NET_FAIL);
						Thread.currentThread().join();
					} catch (Exception e2) {
					}
				}
			};
		}.start();
		
	}*/
	
	/**
	 * 获取所有游乐项目的数据
	 * @param url 访问服务器的路径
	 * @param context
	 */
	public void getAllProjects(Context context,final String url) {
		final MainActivity activity = (MainActivity)context;
		new Thread(){
			public void run() {
				try {
					String data = HttpClientPost.get(url);
					if (data==null||data.trim().equals("")) {
						activity.handler.sendEmptyMessage(Consts.LOAD_FIELD_FAIL);
						Thread.currentThread().join();
					}else {
						Message message=new Message();  
	                    Bundle bundle=new Bundle();  
	                    bundle.putString("data", data);  
	                    message.setData(bundle);//bundle传值，耗时，效率低  
	                    
	                    //handler.sendEmptyMessage(SUCCESS);
	                    message.what=Consts.GET_FIELDS_SUCCESS;//标志是哪个线程传数据  
	                    activity.handler.sendMessage(message);//发送message信息  
	                    //message有四个传值方法，  
	                    //两个传int整型数据的方法message.arg1，message.arg2  
	                    //一个传对象数据的方法message.obj  
	                    //一个bandle传值方法  
					}
				} catch (Exception e) {
					activity.handler.sendEmptyMessage(Consts.NET_FAIL);
					try {
						Thread.currentThread().join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			};
		}.start();
	}
	
	/**
	 * 根据图片名称获取图片资源
	 * @param projectName 游乐项目名称
	 * @return 图片资源
	 */
	public int getImgResource(String projectName){
		if(projectName.equals(Consts.FIELD_PPC)){
			return R.drawable.img_ppc;
		}else if(projectName.equals(Consts.FIELD_DDC)){
			return R.drawable.img_dpc;
		}else{
			return R.drawable.img_default_project;
		}
	}
	
	public void playSound(Context context,int sound){
		MainActivity activity = (MainActivity)context;
		Intent intent =new Intent();
		intent.setClass(activity, PlayerService.class);
		intent.putExtra("sound",sound);
		activity.startService(intent);
	}	
	
}

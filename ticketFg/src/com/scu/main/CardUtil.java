package com.scu.main;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.scu.R;
import com.scu.service.PlayerService;
import com.scu.utils.Consts;
import com.scu.utils.HttpClientPost;

public class CardUtil {
	//刷卡
	public void getInfoFromServer(Context context,final String url) {
		final CardActivity activity = (CardActivity)context;
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
						Message message=new Message();  
	                    Bundle bundle=new Bundle();  
	                    bundle.putString("data", data);  
	                    message.setData(bundle);//bundle传值，耗时，效率低  
	                    message.what=Integer.parseInt(msg);//标志是哪个线程传数据  
	                    activity.handler.sendMessage(message);//发送message信息  
						Thread.currentThread().join();
					}
				} catch (Exception e) {
					System.out.println(e.toString());
					try {
						activity.handler.sendEmptyMessage(Consts.NET_FAIL);
						Thread.currentThread().join();
					} catch (Exception e2) {
					}
				}
			};
		}.start();
		
	}
	
	/**
	 * 声音
	 * @param context
	 * @param sound
	 */
	public void playSound(Context context,int sound){
		CardActivity activity = (CardActivity)context;
		Intent intent =new Intent();
		intent.setClass(activity, PlayerService.class);
		intent.putExtra("sound",sound);
		intent.putExtra("mini","");
		activity.startService(intent);
	}	
	
	/**
	 * 游乐项目选择
	 * @param context
	 * @param sound
	 */
	public void playSound(Context context,String field){
		int sound = 0;
		if(Consts.FIELD_PPC.equals(field)){
			sound = Consts.SOUND_SELECT_PPC;
		}else if(Consts.FIELD_DDC.equals(field)){
			sound = Consts.SOUND_SELECT_DDC;
		}else if(Consts.FIELD_JTC.equals(field)){
			sound = Consts.SOUND_SELECT_JTC;
		}else if(Consts.FIELD_QT.equals(field)){
			sound = Consts.SOUND_SELECT_QT;
		}else{
			System.out.println("不存在此项目");
		}
		
		CardActivity activity = (CardActivity)context;
		Intent intent =new Intent();
		intent.setClass(activity, PlayerService.class);
		intent.putExtra("sound",sound);
		intent.putExtra("mini","");
		activity.startService(intent);
	}	
	
	/**
	 * 声音，时间
	 * @param context
	 * @param sound
	 * @param time
	 */
	public void playSound(Context context,int sound,String time){
		CardActivity activity = (CardActivity)context;
		Intent intent =new Intent();
		intent.setClass(activity, PlayerService.class);
		intent.putExtra("sound",sound);           
		intent.putExtra("time",time);
		activity.startService(intent);
	}
	
	/**
	 * 声音，时间，余额
	 * @param context
	 * @param sound
	 * @param time
	 * @param yue
	 */
	public void playSound(Context context,int sound,String time,String yue){
		System.out.println("time:"+time);
		CardActivity activity = (CardActivity)context;
		Intent intent =new Intent();
		intent.setClass(activity, PlayerService.class);
		intent.putExtra("sound",sound);
		intent.putExtra("time",time);
		intent.putExtra("yue",yue);
		activity.startService(intent);
	}
}


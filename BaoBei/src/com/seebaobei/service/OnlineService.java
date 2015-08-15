package com.seebaobei.service;

import java.util.List;
import com.jve.util.UIHelperUtils;
import com.seebaobei.R;
import com.seebaobei.login.LoginActivity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

public class OnlineService extends Service {

	private static final String TAG = "OnlineService";
	//是否发送请求
	private boolean flag = true;
	
	private NotificationCompat.Builder notification;
	private NotificationManager manager;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 1) {
				UIHelperUtils.showLongToast("你在其他设备登录，已被迫下线", getApplicationContext());
			}
		}
		
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new MyThread()).start();
		Log.i(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		flag = false;
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}
	
	public class MyThread implements Runnable {

		@Override
		public void run() {
			/*while (flag) {
				try {
					
					Log.i(TAG, "dcscd---"+flag);
					Thread.sleep(15*1000);
					if(!getOnlineState()) {
						String packageName = getPackageName();
						Log.i(TAG, "packageName---"+packageName);
						String topActivityClassName=getTopActivityName(getApplicationContext());
						Log.i(TAG, "topActivityClassName---"+topActivityClassName);
						if(packageName != null && topActivityClassName != null && topActivityClassName.startsWith(packageName)) {
							Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intent);
							handler.sendEmptyMessage(1);
							stopSelf();
						} else {
							sendNotification();
						}
						
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}*/
		  //msgid="online"	userid mac
		}

		private boolean getOnlineState() {
			// TODO Auto-generated method stub
			return false;
		}
		
		private void sendNotification() {
			Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
			
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
			stackBuilder.addParentStack(LoginActivity.class);
			stackBuilder.addNextIntent(intent);
			
			
			PendingIntent pendingIntent  = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
			
			
			notification = new NotificationCompat.Builder(getApplicationContext());
			//notification.setTicker("");
			notification.setSmallIcon(R.drawable.ic_launcher);
			notification.setTicker("新消息！");
			notification.setContentTitle("通知");
			notification.setContentText("你在其他设备登录，被迫下线");
			notification.setContentIntent(pendingIntent);
			manager.notify(1001, notification.build());
		}
		private String getTopActivityName(Context context){
	        String topActivityClassName=null;
	         ActivityManager activityManager = (ActivityManager)(context.getSystemService(android.content.Context.ACTIVITY_SERVICE )) ;
	         List<RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1) ;
	         if(runningTaskInfos != null){
	             ComponentName f=runningTaskInfos.get(0).topActivity;
	             topActivityClassName=f.getClassName();
	         }
	         return topActivityClassName;
	    }
	}
}

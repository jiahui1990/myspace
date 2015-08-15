package com.jve.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class UIHelperUtils {

	/**
	 * 短时间显示
	 * QinChuan
	 * @param message 字符串
	 * @param context
	 */
	public static void showShortToast(CharSequence message, Context context) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 短时间显示
	 * QinChuan
	 * @param resId 资源id
	 * @param context
	 */
	public static void showShortToast(int resId, Context context) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}
	
	
	
  
    /** 
     * 长时间显示Toast 
     *  
     * @param context 
     * @param message 
     */  
    public static void showLongToast(CharSequence message, Context context) {  
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();  
    }  
  
    /** 
     * 长时间显示Toast 
     *  
     * @param context 
     * @param message 
     */  
    public static void showLongToast( int resId, Context context) {  
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show();  
    }  
  
    /** 
     * 自定义显示Toast时间 
     *  
     * @param context 
     * @param message 
     * @param duration 
     */  
    public static void show(Context context, CharSequence message, int duration) {  
            Toast.makeText(context, message, duration).show();  
    }  
  
    /** 
     * 自定义显示Toast时间 
     *  
     * @param context 
     * @param message 
     * @param duration 
     */  
    public static void show(Context context, int message, int duration) {  
            Toast.makeText(context, message, duration).show();  
    }  
  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 判断有没有网络
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean checkNetState(Context context) {
		boolean netstate = false;
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						netstate = true;
						break;
					}
				}
			}
		}
		return netstate;
	}
	
	
	/**
	 * 链接网络状态判断,并给予Toast提示
	 */
	public static void netWorkStatus(Context context) {
		boolean netSataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo  = cwjManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			netSataus = networkInfo.isAvailable();
		}

		if (!netSataus) {
			UIHelperUtils.showShortToast("没有可用的网络", context);
		}
		/*if (!netSataus) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("没有可用的网络");
			builder.setMessage("是否对网络进行设置?");
	
			builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
	
				Intent mIntent = new Intent("/");
		
				ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
				mIntent.setComponent(comp);
				mIntent.setAction("android.intent.action.VIEW");
				startActivityForResult(mIntent,0); // 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写
			}});
			builder.setNeutralButton("否", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}}).show();
		}*/
	}
	/**
	 * 函 数 名:sleep <br>
	 * 功 能：睡眠时间 <br>
	 * @time：2015-1-19 上午11:45:07  
	 * @author LiuLun
	 */
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}
}

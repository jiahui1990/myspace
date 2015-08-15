package com.jve.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetUtils {

    /** 
     * 判断网络是否连接 
     *  
     * @param context 
     * @return 
     */  
    public static boolean isConnected(Context context) {  
  
        ConnectivityManager connectivity = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
  
        if (null != connectivity) {  
  
            NetworkInfo info = connectivity.getActiveNetworkInfo();  
            if (null != info && info.isConnected())  
            {  
                if (info.getState() == NetworkInfo.State.CONNECTED)  
                {  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 判断是否是wifi连接 
     */  
    public static boolean isWifi(Context context) {  
        ConnectivityManager cm = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
  
        if (cm == null)  
            return false;  
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;  
  
    }  
  
    /** 
     * 打开网络设置界面 
     */  
    public static void openSetting(Activity activity) {  
        Intent intent = new Intent("/");  
        ComponentName cm = new ComponentName("com.android.settings",  
                "com.android.settings.WirelessSettings");  
        intent.setComponent(cm);  
        intent.setAction("android.intent.action.VIEW");  
        activity.startActivityForResult(intent, 0);  
    }
    /**
     * 根据Wifi信息获取本地Mac
     * @param context
     * @return
     */
    public static String getLocalMacAddressFromWifiInfo(Context context){
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
        WifiInfo info = wifi.getConnectionInfo();  
        return info.getMacAddress(); 
    }
    /**
     * 获取本地IP地址
     * @return
     */
    public static String getLocalIpAddress() {  
        try {  
            String ipv4;  
            List<NetworkInterface>  nilist = Collections.list(NetworkInterface.getNetworkInterfaces());  
            for (NetworkInterface ni: nilist){  
                List<InetAddress>  ialist = Collections.list(ni.getInetAddresses());  
                for (InetAddress address: ialist){  
                    if (!address.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4=address.getHostAddress()))   
                    {   
                        return ipv4;  
                    }  
                }  
   
            }  
   
        } catch (SocketException e) {  
            e.printStackTrace(); 
        }  
        return null;  
    } 
}

package com.scu.utils;

import java.util.Iterator;
import java.util.Set;

import com.scu.main.CardActivity;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class PdaUtil {
	
	static final int WIFI_STATE_DISABLED = 1;
	static final int WIFI_STATE_DISABLING  = 0;
	static final int WIFI_STATE_ENABLED = 3;  
	static final int WIFI_STATE_ENABLING = 2;
	static final int WIFI_STATE_UNKNOWN  = 4;
	
	public void checkWifi(Context context){
		WifiManager wifiManager=(WifiManager)context.getSystemService(Service.WIFI_SERVICE); 
        
        //获取网卡当前的状态 
        int wifiState = wifiManager.getWifiState();
        switch (wifiState) {
		case WIFI_STATE_DISABLED:
			//打开WIFI网卡 
	        wifiManager.setWifiEnabled(true); 
	        ToastUtil.showShort(context, "无线未打开！");
			break;
		case WIFI_STATE_ENABLED:
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			if(wifiInfo.getSSID()==null){
				ToastUtil.showShort(context, "无线未连接！");
			}
			break;
		default:
			break;
		}
	}
	
	public void openBluetooth(Context context){
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothAdapter != null) {
			if (!bluetoothAdapter.isEnabled()) {
				Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				context.startActivity(intent);
			}
		}

		/*Set<BluetoothDevice> set = bluetoothAdapter.getBondedDevices();
		if (set.size() > 0) {
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
			}
		}*/
	}
	
}

package com.seebaobei.appmain;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.huamaitel.api.HMDefines;
import com.huamaitel.api.HMDefines.DeviceInfo;
import com.huamaitel.api.HMJniInterface;

/**
 * 存放全局数据
 * 
 * @author admin
 * 
 */
public class MainApp extends Application {
	public static final String TAG = "MainApp";
	public static int mUserId = 0;
	public static int mVideoHandle = 0;
	public static int mAudioHandle = 0;
	public static int mTalkHandle = 0;
	public static int mAlarmHandle = 0;
	public static int mRecordHandle = 0;
	public static byte[] mCapputureHandle = null;

	public static int mLanSearchHandle = 0;

	public static HMDefines.DeviceInfo mDeviceInfo = null;
	public static HMDefines.ChannelCapacity mChannelCapacity[] = null;
	public static int serverId = 0;
	public static int treeId = 0;
	public static int userId = 0;
	public static int curNodeHandle = 0;
	public static int curNodeChannel;
	public static DeviceInfo deviceInfo = null;
	public static List<Integer> rootList;
	private static HMJniInterface jni = null;
	public String mRecordPath = ""; // The path of video record file.
	public static String mCapturePath = ""; // The path of captured picture file.
	public static String mLoginServerError = ""; // The error message of login sever.
	public static boolean mIsUserLogin = true; // Is IsUserLogin from intent
	
	//public static String username;
	//public static String password;

	public static final String NODE_ID = "nodeId";
	public static final String CHANNEL = "channel";
	public static final String VIDEO_STREAM = "video_stream";

	@Override
	public void onCreate() {
		super.onCreate();
		rootList = new ArrayList<Integer>();
	}

	public static HMJniInterface getJni() {
		if (null == jni) {
			jni = new HMJniInterface();
		}
		return jni;
	}
}

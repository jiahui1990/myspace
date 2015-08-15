package com.seebaobei.appmain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.huamaitel.api.HMDefines;
import com.huamaitel.api.HMJniInterface;
import com.huamaitel.api.HMDefines.ChannelInfo;
import com.huamaitel.api.HMDefines.NodeTypeInfo;
import com.jve.util.SharedPreferencesUtils;
import com.jve.util.UIHelperUtils;
import com.jve.util.adapter.CommonAdapter;
import com.jve.util.adapter.ViewHolder;
import com.seebaobei.R;
import com.seebaobei.base.BaseActivity;
import com.seebaobei.base.StackManager;
import com.seebaobei.login.LoginActivity;
import com.seebaobei.service.OnlineService;
import com.seebaobei.video.PlayVideoActivity;
import com.seebaobei.video.Video;
import com.seebaobei.video.ViewMonitor;
import com.seebaobei.Constants;

public class MainActivity extends BaseActivity {
	
	private ImageView backImageView;
	
	private TextView titleTextView,otherTextView;
	
	private static final String TAG = "MainActivity";
	private List<ViewMonitor> mListData;
	private List<Video> videos;
	private int mVideoStream = HMDefines.CodeStream.MAIN_STREAM;
	
	private LinearLayout contentLayout;
	private GridView gridView;
	
	private SharedPreferencesUtils sharedPreferencesUtils;
	
	
	public static void actionStart(Context context, List<ViewMonitor> datas) {
		Intent intent = new Intent(context, MainActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("mListData", (Serializable) datas);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.initViews();
		this.initEvents();
		
	}
	

	@Override
	public void initViews() {
		backImageView = (ImageView) findViewById(R.id.jve_id_top_back);
		backImageView.setVisibility(View.INVISIBLE);
		titleTextView = (TextView) findViewById(R.id.jve_id_top_title);
		otherTextView = (TextView) findViewById(R.id.jve_id_top_other);
		
		
		contentLayout = (LinearLayout) findViewById(R.id.jve_id_mainPage_contentLayout);
		gridView = (GridView) findViewById(R.id.jve_id_mainPage_gridView);
		this.initDatas();
		
	}
	
	

	@Override
	public void initDatas() {
		titleTextView.setText(R.string.jve_str_mainPage_str1);
		otherTextView.setText(R.string.jve_str_mainPage_str2);
		otherTextView.setVisibility(View.VISIBLE);
		mListData = (List<ViewMonitor>) getIntent().getExtras().getSerializable("mListData");
		videos = new ArrayList<Video>();
		/*StringBuilder error = new StringBuilder();
		MainApp.serverId = MainApp.getJni().connectServer(info, error);
		MainApp.treeId = MainApp.getJni().getTree(MainApp.serverId);*/
		
		// Get the root of the tree.
		int treeId = MainApp.treeId;
		int rootId = MainApp.getJni().getRoot(treeId);

		MainApp.rootList.clear();
		MainApp.rootList.add(rootId);

		getChildrenByNodeId(rootId);
		
		if(videos == null || videos.size() == 0) {
			super.loadingDataAlert(contentLayout, this, R.layout.layout_no_data);
		}else {
			/*Map<String, ?> map = getLoginInfo();
			videos = new ArrayList<Video>();
			for (Iterator iterator = mListData.iterator(); iterator.hasNext();) {
				ViewMonitor viewMonitor = (ViewMonitor) iterator.next();
				if(!TextUtils.isEmpty(viewMonitor.getSn())) {
					Video video = new Video();
					video.setSn(viewMonitor.getSn());
					video.setUsername((String) map.get("username"));
					video.setPass((String) map.get("password"));
					video.setName(viewMonitor.getMonitorName());
					video.setImg(R.drawable.video_icon);
					videos.add(video);
				}
			}*/
			gridView.setAdapter(new CommonAdapter<Video>(this, videos, R.layout.video_item) {

				@Override
				public void convert(ViewHolder helper, Video item) {
					
					helper.setText(R.id.jve_id_video_title, item.getName());
					helper.setImageResource(R.id.jve_id_video_icon, item.getImg());
				}
				
			});
		}
	}

	@Override
	public void initEvents() {
		otherTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sharedPreferencesUtils = new SharedPreferencesUtils(MainActivity.this);
				boolean success = false;
				do {
					success = sharedPreferencesUtils.clearnSharePreference(Constants.JVE_FILE_LOGIN);
				} while (!success);
				Intent service = new Intent(MainActivity.this, OnlineService.class);
				stopService(service);
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(intent);
				MainActivity.this.finish();
				
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Video video = (Video) parent.getItemAtPosition(position);
				
				int nodeType = video.getType();
				Log.d("DeviceActivity:", "nodeType:" + nodeType);
				int nodeId = video.getId();
				Log.d("DeviceActivity:", "nodeId:" + nodeId);

				MainApp.curNodeHandle = nodeId;
				/**
				 * TODO：异常处理 此处注意判断设备是否在线 请参考SDK文档 添加 isOnline这个接口进行判断
				 * 不在线的设备不能点击，不能进入下一步操作。
				 */
				if (nodeType == NodeTypeInfo.NODE_TYPE_DVS || nodeType == NodeTypeInfo.NODE_TYPE_GROUP ) {
					MainApp.rootList.add(nodeId);
					getChildrenByNodeId(nodeId);
					((SimpleAdapter) gridView.getAdapter()).notifyDataSetChanged();
				}
				if (nodeType == NodeTypeInfo.NODE_TYPE_CHANNEL) {
					/**
					 * 注意：针对DVS设备，在获取NodeId时，需要获取父类的Id（getParentId）,用于登录设备。
					 * 通道信息用于获取DVS下面对应的设备通道号
					 */
					int nodeDvsId=MainApp.getJni().getParentId(nodeId);
					ChannelInfo info = MainApp.getJni().getChannelInfo(nodeId); // 获取通道信息
					//如果设备在线
					Log.d(TAG, "info:"+info.index+"+"+info.name);
					video.setId(nodeDvsId);
					video.setmChannelIndex(info.index);
					PlayVideoActivity.actionStart(MainActivity.this, video, position, mVideoStream);
				} else if (nodeType == NodeTypeInfo.NODE_TYPE_DEVICE) {
					video.setmChannelIndex(0);
					PlayVideoActivity.actionStart(MainActivity.this, video, position, mVideoStream);
				}
			}
			
		});
	}
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (MainApp.treeId != 0) {
			MainApp.getJni().releaseTree(MainApp.treeId);
		}

		if (MainApp.serverId != 0) {
			MainApp.getJni().disconnectServer(MainApp.serverId);
		}
	}


	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).setTitle(R.string.jve_str_public_alert).setMessage(R.string.jve_str_public_exit_alert).setPositiveButton(R.string.jve_str_public_confirm, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					sharedPreferencesUtils = new SharedPreferencesUtils(MainActivity.this);
					boolean success = false;
					do {
						success = sharedPreferencesUtils.clearnSharePreference(Constants.JVE_FILE_LOGIN);
						if (!success) {
							UIHelperUtils.showShortToast(getResources().getString(R.string.jve_str_public_login_out), MainActivity.this);
						}
					} while (!success);
					
					Intent service = new Intent(MainActivity.this, OnlineService.class);
					stopService(service);
					StackManager.getStackManager().popAllActivitys();
					//Process.killProcess(Process.myPid());太暴力
					MainActivity.this.finish();
							
				}
			}).setNegativeButton(R.string.jve_str_public_cancel, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
				}

			}).show();
			return true;
		} else {
			return super.onKeyUp(keyCode, event);
		}
			/*if (MainApp.rootList.size() != 1) {
				int nodeId = MainApp.rootList.get(MainApp.rootList.size() - 2);
				MainApp.rootList.remove(MainApp.rootList.size() - 1);

				getChildrenByNodeId(nodeId);

				((SimpleAdapter) gridView.getAdapter()).notifyDataSetChanged();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);*/
	}

	// Get the children list by this parent node.
	/*private void getChildrenByNodeId(int nodeId) {
		Log.d(TAG, "getDeviceListByNodeId nodeId: " + nodeId);
		if (nodeId != 0) {
			HMJniInterface sdk = MainApp.getJni();
			videos.clear();

			int count = sdk.getChildrenCount(nodeId);
			Log.d(TAG, "getChildrenCount: " + count);
			for (int i = 0; i < count; ++i) {
				Video video = new Video();
				int childrenNode = sdk.getChildAt(nodeId, i);
				int nodeType = sdk.getNodeType(childrenNode);
				
				video.setType(nodeType);

				if (nodeType == NodeTypeInfo.NODE_TYPE_GROUP) {
					video.setImg(R.drawable.video_icon);
				} else if (nodeType == NodeTypeInfo.NODE_TYPE_DEVICE) {
					video.setImg(R.drawable.video_icon);
				} else if (nodeType == NodeTypeInfo.NODE_TYPE_DVS) {
					video.setImg(R.drawable.video_icon);
				} else if (nodeType == NodeTypeInfo.NODE_TYPE_CHANNEL) {
					video.setImg(R.drawable.video_icon);
				}

				Log.d(TAG, " childNode: " + childrenNode);
				Log.d(TAG, "childNode Url: " + sdk.getNodeUrl(childrenNode));
				Log.d(TAG, "childNode sn: " + sdk.getDeviceSn(childrenNode));
				
				video.setId(childrenNode);
				video.setName(sdk.getNodeName(childrenNode));

				videos.add(video);
			}
		}
	}*/
	
	/**
	 * Get the children list by this parent node.（多层结点信息获取）
	 * @param nodeId
	 */
	private void getChildrenByNodeId(int nodeId) {
		Log.d(TAG, "getDeviceListByNodeId nodeId: " + nodeId);
		if (nodeId != 0) {
			HMJniInterface sdk = MainApp.getJni();
			videos.clear();

			int count = sdk.getChildrenCount(nodeId);
			Log.d(TAG, "getChildrenCount: " + count);
			for (int i = 0; i < count; ++i) {
				Video video = new Video();
				int childrenNode = sdk.getChildAt(nodeId, i);
				int nodeType = sdk.getNodeType(childrenNode);
				
				if (nodeType == NodeTypeInfo.NODE_TYPE_DVS || nodeType == NodeTypeInfo.NODE_TYPE_GROUP ) {
					MainApp.rootList.add(childrenNode);
					getChildrenByNodeId(childrenNode);
					//((SimpleAdapter) gridView.getAdapter()).notifyDataSetChanged();
				}else{
					String sn = sdk.getDeviceSn(childrenNode);
					/*if(checkSnOfCurrentUser(sn)){
						video.setType(nodeType);

						if (nodeType == NodeTypeInfo.NODE_TYPE_GROUP) {
							video.setImg(R.drawable.video_icon);
						} else if (nodeType == NodeTypeInfo.NODE_TYPE_DEVICE) {
							video.setImg(R.drawable.video_icon);
						} else if (nodeType == NodeTypeInfo.NODE_TYPE_DVS) {
							video.setImg(R.drawable.video_icon);
						} else if (nodeType == NodeTypeInfo.NODE_TYPE_CHANNEL) {
							video.setImg(R.drawable.video_icon);
						}

						Log.d(TAG, " childNode: " + childrenNode);
						Log.d(TAG, "childNode Url: " + sdk.getNodeUrl(childrenNode));
						Log.d(TAG, "childNode sn: " + sdk.getDeviceSn(childrenNode));
						
						video.setId(childrenNode);
						video.setName(sdk.getNodeName(childrenNode));

						videos.add(video);
					}*/
					video.setType(nodeType);

					if (nodeType == NodeTypeInfo.NODE_TYPE_GROUP) {
						video.setImg(R.drawable.video_icon);
					} else if (nodeType == NodeTypeInfo.NODE_TYPE_DEVICE) {
						video.setImg(R.drawable.video_icon);
					} else if (nodeType == NodeTypeInfo.NODE_TYPE_DVS) {
						video.setImg(R.drawable.video_icon);
					} else if (nodeType == NodeTypeInfo.NODE_TYPE_CHANNEL) {
						video.setImg(R.drawable.video_icon);
					}

					Log.d(TAG, " childNode: " + childrenNode);
					Log.d(TAG, "childNode Url: " + sdk.getNodeUrl(childrenNode));
					Log.d(TAG, "childNode sn: " + sdk.getDeviceSn(childrenNode));
					
					video.setId(childrenNode);
					video.setName(sdk.getNodeName(childrenNode));

					videos.add(video);
					
				}
				
			}
		}
	}
	
	private boolean checkSnOfCurrentUser(String sn){
		for (int i = 0; i < mListData.size(); i++) {
			ViewMonitor viewMonitor = mListData.get(i);
			if(viewMonitor.getSn().equals(sn)){
				return true;
			}
		}
		return false;
	}

}

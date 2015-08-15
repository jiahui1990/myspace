package com.seebaobei.video;



import java.io.Serializable;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.huamaitel.api.HMDefines;
import com.jve.util.UIHelperUtils;
import com.seebaobei.Constants;
import com.seebaobei.R;
import com.seebaobei.appmain.MainApp;
import com.seebaobei.base.BaseActivity;

/**
 * 观看视频界面
 * @author javae
 *
 */
public class PlayVideoActivity extends BaseActivity implements OnClickListener {
	private static final String TAG = "PlayVideoActivity";
	
	/*public static void actionStart(Context context,List<Video> mListData, int position, int videoStream) {
		Intent intent = new Intent(context, PlayVideoActivity.class);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("mListData", (Serializable) mListData);
		bundle.putInt("currentItem", position);
		intent.putExtra(MainApp.VIDEO_STREAM, videoStream);
		
		intent.putExtras(bundle);
		
		MainApp.mIsUserLogin = true;
		context.startActivity(intent);
	}*/
	
	public static void actionStart(Context context,Video video, int position, int videoStream) {
		Intent intent = new Intent(context, PlayVideoActivity.class);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("video", (Serializable) video);
		intent.putExtra(MainApp.VIDEO_STREAM, videoStream);
		
		intent.putExtras(bundle);
		
		MainApp.mIsUserLogin = true;
		context.startActivity(intent);
	}

	
	private int mVideoStream = HMDefines.CodeStream.MAIN_STREAM; // The video
																	// stream
																	// (Main/sub
																	// video
	private ImageView back;																// stream.)
	
	private TextView textView;
	
	private PopupWindow popupWindow;
	//private  JazzyViewPager viewPager;
	
	//private List<VideoFragment> videoFragments;
	
	private List<Video> videos;
	
	private int oldPosition = 0;

	private int currentItem = 0; // 当前图片的索引号

	private Video oldVideo = null;

	private Video currentVideo = null;
	/**
	 * 视频质量
	 */
	private String videoQuality = Constants.JVE_GAOQ;
	
	final int RIGHT = 0;  
    final int LEFT = 1;  
    private GestureDetector gestureDetector; 
    
    private GestureDetector.OnGestureListener onGestureListener =   
            new GestureDetector.SimpleOnGestureListener() {  
            @Override  
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
                    float velocityY) {  
                float x = e2.getX() - e1.getX();  
                //float y = e2.getY() - e1.getY();  
      
                if (x > 0) {  
                    doResult(RIGHT);  
                } else if (x < 0) {  
                    doResult(LEFT);  
                }  
                return true;  
            }

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				swichHD();
				return true;
			} 
            
        };  
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_video);
		// 让屏幕保持不暗不关闭
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// Get the node id from the DeviceActivity .
		Intent intent = getIntent();
		//mNodeId = intent.getIntExtra(MainApp.NODE_ID, 0);
		//mChannelIndex = intent.getIntExtra(MainApp.CHANNEL, 0);
		mVideoStream = intent.getIntExtra(MainApp.VIDEO_STREAM, HMDefines.CodeStream.MAIN_STREAM);
//		videos = (List<Video>) intent.getExtras().getSerializable("mListData");
//		currentItem = intent.getIntExtra("currentItem", 0);
//		currentVideo = videos.get(currentItem);
		
		currentVideo = (Video)intent.getExtras().getSerializable("video");;
		this.initViews();
		this.initEvents();
	}

	@Override
	public void initViews() {
		textView = (TextView) findViewById(R.id.jve_id_video_textView);
		back = (ImageView) findViewById(R.id.jve_id_top_exit);
		this.initDatas();
	}
	
	@Override
	public void initDatas() {
		openVideo(currentVideo, Constants.JVE_GAOQ);
	}

	@Override
	public void initEvents() {
		back.setOnClickListener(this);
		gestureDetector = new GestureDetector(PlayVideoActivity.this, onGestureListener);
		//textView.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		back();
	}

	private void back() {
		new AlertDialog.Builder(this).setTitle("确定要退出视频播放吗？").setIcon(android.R.drawable.ic_dialog_info).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Exit the play activity for exiting.
				if (!currentVideo.ismIfExit()) {
					exitPlayActivity();
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// do nothing……
			}
		}).show();
	}
	private void exitPlayActivity() {
		// Avoid calling this function many times.
		currentVideo.setmIfExit(true);

		// Close some resources.
		new Thread() {
			public void run() {
				releaseResources(currentVideo);
			}

		}.start();

		// Back to the device list activity.
		PlayVideoActivity.this.finish();
	}
	/**
	 * 打开视频
	 * @param currentVideo 当前视频对象
	 * @param type 视频类型（高清、标清）
	 */
	private void openVideo(final Video currentVideo, final String type) {
		new Thread() {
			@Override
			public void run() {
				super.run();
				if (MainApp.mIsUserLogin) {
					// 从互联网登录
					if (currentVideo.getId() == 0) {
						return;
					}
					// Step 1: Login the device.

					MainApp.mUserId = MainApp.getJni().loginEx(currentVideo.getId(), HMDefines.ConnectPolicy.CONN_POLICY_DEFAULT);
					if (MainApp.mUserId == -1) {
						return;
					}
				} else {
					/**
					 * 从局域网登录 TODO:异常处理 从局域网登录（本地设备） 调试时需要注意手机的网络为同一个局域网 如果设备进行了设置，输入相应的账号密码。默认账号为：guest,密码不填。 此处请检查输入的正确性，代码中没有做相关信息正确性检查。
					 */
					String sIp = getIntent().getStringExtra("ip");
					String sPort = getIntent().getStringExtra("port");
					String sUser = getIntent().getStringExtra("user");
					String sPass = getIntent().getStringExtra("pass");
					String sSn = getIntent().getStringExtra("sn");
					if (sIp != null && sPort != null && sSn != null && sUser != null) {
						// Step 1: Login the device.
						MainApp.mUserId = MainApp.getJni().login(sIp, Short.parseShort(sPort), sSn, sUser, sPass);
						if (MainApp.mUserId == -1) {
							return;
						}
					}
				}
				
				/*String sIp = Constants.SERVER_VIDEO_ADDR;
				short sPort = Constants.SERVER_PORT;
				String sUser = MainApp.username;
				String sPass = MainApp.password;
				String sSn = currentVideo.getSn();
				if (sIp != null && sPort != 0 && sSn != null && sUser != null) {
					// Step 1: Login the device.
					MainApp.mUserId = MainApp.getJni().login(sIp, sPort, sSn, "icework", "scujb423");
					if (MainApp.mUserId == -1) {
						return;
					}
				}*/

				currentVideo.setmIfLogin(true);

				/**
				 * Step 2: Get device information. TODO:异常处理 当设备不在线，执行这一步容易返回为空。测试时请检查一下设备是否在线！！ 可参考SDK文档 添加 Online这个接口进行判断。
				 */
				MainApp.mDeviceInfo = MainApp.getJni().getDeviceInfo(MainApp.mUserId);
				MainApp.mChannelCapacity = MainApp.getJni().getChannelCapacity(MainApp.mUserId);
				if (MainApp.mDeviceInfo == null) {
					return;
				}
				// Step 3: Start video
				HMDefines.OpenVideoParam param = new HMDefines.OpenVideoParam();
				param.channel = currentVideo.getmChannelIndex();
				if(Constants.JVE_GAOQ.equals(type)) {
					param.codeStream = HMDefines.CodeStream.MAIN_STREAM;
				}else if (Constants.JVE_BIAOQ.equals(type)) {
					param.codeStream = HMDefines.CodeStream.SEC_STREAM;
				}
				
				param.videoType = HMDefines.VideoStream.REAL_STREAM;
				HMDefines.OpenVideoRes res = new HMDefines.OpenVideoRes();

				MainApp.mVideoHandle = MainApp.getJni().startVideo(MainApp.mUserId, param, res);

				if (MainApp.mVideoHandle == -1) {
					currentVideo.setmIsPlaying(false);
					return;
				}
				/**
				 * TODO：异常处理 startVideo成功后 ，执行 setNetworkCallback 回调函数检测网络数据。 为保证设备信息获取到，可以再执行一次getDeviceInfo操作。 如添加了进度条，在此时进行释放。
				 * 
				 */
				currentVideo.setmIsPlaying(true);
				oldPosition = currentItem;
			}
		}.start();
	}
	
	/**
	 * 释放资源
	 * @param oldVideo
	 */
	private void releaseResources(Video oldVideo) {
		if (oldVideo.ismIsPlaying()) {
			int result = MainApp.getJni().stopVideo(MainApp.mVideoHandle);
			if (result != 0) {
				Log.e(TAG, "stopvideo fail.");
			} else {
				Log.i(TAG, "stopvideo success.");
			}
			oldVideo.setmIsPlaying(false);
		}

		if (oldVideo.ismIfLogin()) {
			int result = MainApp.getJni().logout(MainApp.mUserId);
			if (result != 0) {
				Log.e(TAG, "logout fail.");
			} else {
				Log.i(TAG, "logout success.");
			}
			oldVideo.setmIfExit(false);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jve_id_top_exit:
			back();
			break;
		/*case R.id.jve_id_video_textView:
			initPopupMenu(v);
			break;
		case R.id.jve_id_popup_menu_biaoQ:
			if(!videoQuality.equals(Constants.JVE_BIAOQ)) {
				releaseResources(videos.get(currentItem));
				openVideo(videos.get(currentItem), Constants.JVE_BIAOQ);
				textView.setText(R.string.jve_str_videoPage_str2);
				videoQuality = Constants.JVE_BIAOQ;
			}
			//closePopupMenu();
			break;
		case R.id.jve_id_popup_menu_gaoQ:
			if(!videoQuality.equals(Constants.JVE_GAOQ)) {
				releaseResources(videos.get(currentItem));
				openVideo(videos.get(currentItem), Constants.JVE_GAOQ);
				textView.setText(R.string.jve_str_videoPage_str1);
				videoQuality = Constants.JVE_GAOQ;
			}
			//closePopupMenu();
			break;*/
		default:
			break;
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	public void doResult(int action) {  
		  
        switch (action) {  
        case RIGHT: 
        	if(currentItem != 0) {
        		releaseResources(videos.get(oldPosition));
    			currentItem --;
    			openVideo(videos.get(currentItem), Constants.JVE_GAOQ);
    			textView.setText(R.string.jve_str_videoPage_str1);
				videoQuality = Constants.JVE_GAOQ;
        	}else {
				UIHelperUtils.showShortToast(R.string.jve_str_videoPage_str3, this);
			}
        	//closePopupMenu();
            break;  
  
        case LEFT:  
        	if(currentItem != videos.size()-1) {
        		releaseResources(videos.get(oldPosition));
    			currentItem ++;
    			openVideo(videos.get(currentItem), Constants.JVE_GAOQ);
    			textView.setText(R.string.jve_str_videoPage_str1);
				videoQuality = Constants.JVE_GAOQ;
        	}else {
        		UIHelperUtils.showShortToast(R.string.jve_str_videoPage_str4, this);
			}
        	//closePopupMenu();
            break;  
        }  
    } 
	
	/**
	 * 切换高清、标清
	 */
	private void swichHD() {
		if(Constants.JVE_GAOQ.equals(videoQuality)){
			releaseResources(videos.get(currentItem));
			openVideo(videos.get(currentItem), Constants.JVE_BIAOQ);
			textView.setText(R.string.jve_str_videoPage_str2);
			videoQuality = Constants.JVE_BIAOQ;
		}else if (Constants.JVE_BIAOQ.equals(videoQuality)) {
			releaseResources(videos.get(currentItem));
			openVideo(videos.get(currentItem), Constants.JVE_GAOQ);
			textView.setText(R.string.jve_str_videoPage_str1);
			videoQuality = Constants.JVE_GAOQ;
		}
	}
	
	/**
	 * 关闭弹出菜单
	 */
	/*private void closePopupMenu() {
		if (popupWindow != null) {
			popupWindow.dismiss();
			popupWindow = null;
		}
	}*/
	
	/*@SuppressWarnings("deprecation")
	private void initPopupMenu(View v) {
		if (popupWindow == null) {
			LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout view = (LinearLayout) layoutInflater.inflate(R.layout.popup_menu, null);
			Button biaoQbutton = (Button) view.findViewById(R.id.jve_id_popup_menu_biaoQ);
			Button gaoQbutton = (Button) view.findViewById(R.id.jve_id_popup_menu_gaoQ);
			if(Constants.JVE_GAOQ.equals(videoQuality)) {
				gaoQbutton.setTextColor(getResources().getColor(R.color.jve_color_yellow1));
			}else if (Constants.JVE_BIAOQ.equals(videoQuality)) {
				biaoQbutton.setTextColor(getResources().getColor(R.color.jve_color_yellow1));
			}
			
			biaoQbutton.setOnClickListener(this);
			gaoQbutton.setOnClickListener(this);
			popupWindow = new PopupWindow(view, getApplicationContext().getResources().getDisplayMetrics().widthPixels / 12, getApplicationContext().getResources().getDisplayMetrics().heightPixels / 4, true);
		}

		// 设置整个popupwindow的样式。
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// 使窗口里面的空间显示其相应的效果，比较点击button时背景颜色改变。
		// 如果为false点击相关的空间表面上没有反应，但事件是可以监听到的。
		// listview的话就没有了作用。
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		popupWindow.showAsDropDown(v);
	}*/
}


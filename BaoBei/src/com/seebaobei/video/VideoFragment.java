package com.seebaobei.video;

import com.jve.util.UIHelperUtils;
import com.seebaobei.Constants;
import com.seebaobei.R;
import com.seebaobei.appmain.MainApp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class VideoFragment extends Fragment implements OnClickListener {
	
	private PopupWindow popupWindow;
	
	private TextView textView;
	//是否显示
	private boolean mIsShow = false;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_video,null);
		textView = (TextView) view.findViewById(R.id.jve_id_video_textView);
		this.initEvents();
		return view;
	}
	
	private void initEvents() {
		textView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jve_id_video_textView:
			initPopupMenu(v);
			/*if(mIsShow) {
				//隐藏
				Animation animation = new AlphaAnimation(1.0f, 0.1f);
				animation.setDuration(3000);
				videoTitleBar.setAnimation(animation);
				videoTitleBar.startAnimation(animation);
				mIsShow = false;
			} else {
				//显示
				Animation animation = new AlphaAnimation(0.1f, 1.0f);
				animation.setDuration(3000);
				videoTitleBar.startAnimation(animation);
				mIsShow = true;
			}*/
			
			break;
		case R.id.jve_id_popup_menu_biaoQ:
			//((PlayVideoActivity)getActivity()).switchHDSD(Constants.JVE_BIAOQ);
			break;
		case R.id.jve_id_popup_menu_gaoQ:
			//((PlayVideoActivity)getActivity()).switchHDSD(Constants.JVE_GAOQ);
			break;
		default:
			break;
		}
		
	}


	private void initPopupMenu(View v) {
		if (popupWindow == null) {
			LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout view = (LinearLayout) layoutInflater.inflate(R.layout.popup_menu, null);
			Button biaoQbutton = (Button) view.findViewById(R.id.jve_id_popup_menu_biaoQ);
			Button gaoQbutton = (Button) view.findViewById(R.id.jve_id_popup_menu_gaoQ);
			biaoQbutton.setOnClickListener(this);
			gaoQbutton.setOnClickListener(this);
			popupWindow = new PopupWindow(view, getActivity().getApplicationContext().getResources().getDisplayMetrics().widthPixels / 12, getActivity().getApplicationContext().getResources().getDisplayMetrics().heightPixels / 4, true);
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
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}
	
	
}

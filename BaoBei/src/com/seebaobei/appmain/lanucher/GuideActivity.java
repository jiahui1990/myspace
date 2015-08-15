package com.seebaobei.appmain.lanucher;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jve.util.SharedPreferencesUtils;
import com.jve.util.view.guide.viewPager.JazzyViewPager;
import com.jve.util.view.guide.viewPager.JazzyViewPager.TransitionEffect;
import com.jve.util.view.guide.viewPager.OutlineContainer;
import com.seebaobei.Constants;
import com.seebaobei.R;
import com.seebaobei.appmain.MainActivity;
import com.seebaobei.base.BaseActivity;
import com.seebaobei.login.LoginActivity;

public class GuideActivity extends BaseActivity implements OnPageChangeListener, OnClickListener {

	// int SCROLL_STATE_DRAGGING Indicates that the pager is currently being
	// dragged by the user.
	// int SCROLL_STATE_IDLE Indicates that the pager is in an idle, settled
	// state.
	// int SCROLL_STATE_SETTLING Indicates that the pager is in the process of
	// settling to a final position.
	//跳过按钮jve_id_click_skipBtn
	private TextView skipBtn;
	
	private JazzyViewPager viewPager;

	//闲置状态SCROLL_STATE_IDLE
	//private int state = 0;

	//private int[] imgIds;

	private List<View> views;
	//private List<ImageView> imageViews;
	// 底部小点图片
	private List<View> dots;
	
	private int oldPosition = 0;

	private int currentItem = 0; // 当前图片的索引号

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		this.initViews();
		this.initEvents();
		// 初始化底部小点
		initDots();
	}

	
	@Override
	public void initViews() {
		//LayoutInflater inflater = LayoutInflater.from(this);
		//imgIds = new int[] {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4, R.drawable.guide_5, R.drawable.guide_6, R.drawable.guide_7};
		//imageViews = new ArrayList<ImageView>();
		
		skipBtn = (TextView) findViewById(R.id.jve_id_click_skipBtn);
		views = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		// 初始化引导图片列表
		views.add(inflater.inflate(R.layout.guide_step1, null));
		views.add(inflater.inflate(R.layout.guide_step2, null));
		views.add(inflater.inflate(R.layout.guide_step3, null));
		views.add(inflater.inflate(R.layout.guide_step4, null));
		
		
		setupJazziness(TransitionEffect.Standard);
		/*viewPager.setAdapter(new ViewPagerAdapter(imgIds));
		viewPager.setOnPageChangeListener(this);*/
	}
	
	@Override
	public void initEvents() {
		skipBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jve_id_click_skipBtn: {
			// 设置已经引导
			setGuided();
			if(isLogin()) {
				goHome();
			}else {
				goLogin();
			}
		}
			break;
		default:
			break;
		}
		
	}
	
	private void setupJazziness(TransitionEffect effect) {
		viewPager = (JazzyViewPager) findViewById(R.id.jve_id_guide_viewPager);
		viewPager.setContext(this);
		viewPager.setTransitionEffect(effect);
		viewPager.setAdapter(new ViewPagerAdapter(views));
		//viewPager.setFadeEnabled(true);//有淡入淡出效果
		//viewPager.setPageMargin(30);//两个页面之间的间距
		viewPager.setOnPageChangeListener(this);
	}
	private void initDots() {
		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.jve_id_v_dot0));
		dots.add(findViewById(R.id.jve_id_v_dot1));
		dots.add(findViewById(R.id.jve_id_v_dot2));
		dots.add(findViewById(R.id.jve_id_v_dot3));
		/*
		 * LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		 * 
		 * dots = new ImageView[views.size()];
		 * 
		 * // 循环取得小点图片 for (int i = 0; i < views.size(); i++) { dots[i] =
		 * (ImageView) ll.getChildAt(i); dots[i].setEnabled(true);// 都设为灰色 }
		 * 
		 * currentIndex = 0; dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
		 */
	}

	/*private void setCurrentDot(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}

		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);

		currentIndex = position;
	}*/

	
	
	class ViewPagerAdapter extends PagerAdapter {

		private List<View> views;


		public ViewPagerAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public int getCount() {
			if (this.views != null && this.views.size() > 0) {
				return this.views.size();
			}
			return 0;
		}

		// 判断是否有对象生成界面
		@Override
		public boolean isViewFromObject(View view, Object object) {
			if (view instanceof OutlineContainer) {
				return ((OutlineContainer) view).getChildAt(0) == object;
			} else {
				return view == object;
			}
		}

		// 销毁position位置的界面
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			/*container.removeView(imageViews.get(position));
			viewPager.removeViewFromPosition(position);*/
			container.removeView(viewPager.findViewFromObject(position));
		}

		// 初始化position位置的界面
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position), 0);
			/*ImageView imageView = new ImageView(GuideActivity.this);
			imageView.setImageBitmap(FileUtils.readBitMap(GuideActivity.this, imgIds[position]));
			imageView.setScaleType(ScaleType.CENTER_CROP);*/
			
			
			
			//container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			
			viewPager.setObjectForPosition(views.get(position), position);
			
			if (position == views.size() - 1) {
				TextView enterBtn = (TextView) container
						.findViewById(R.id.jve_id_click_enterBtn);
				enterBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// 设置已经引导
						setGuided();
						if(isLogin()) {
							goHome();
						}else {
							goLogin();
						}
						

					}

				});
			}
			return views.get(position);
		}

	}
	/**
	 * 设置已经向导
	 */
	private void setGuided() {
		SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(this);
		boolean success = sharedPreferencesUtils.modifySharePreference(Constants.JVE_FILE_SYS_STATUS, Constants.JVE_IS_FIRST_IN, false);
		while (!success) {
			success = sharedPreferencesUtils.modifySharePreference(Constants.JVE_FILE_SYS_STATUS, Constants.JVE_IS_FIRST_IN, false);
		}
		
	}
	
	/**
	 * 跳至主页
	 */
	private void goHome() {
		Intent intent = new Intent(this, MainActivity.class);
		this.startActivity(intent);
		this.finish();
	}
	
	/**
	 * 调至登录页
	 */
	private void goLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		this.startActivity(intent);
		this.finish();
	}

	// Called when the scroll state changes.当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int state) {
		viewPager.setState(state);
	}

	// This method will be invoked when the current page is scrolled, either as
	// part of a programmatically initiated smooth scroll or a user initiated
	// touch scroll.
	//当前页面被滑动时调用
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		/*if(state == ViewPager.SCROLL_STATE_DRAGGING && position == imgIds.length-1) {
			setGuided();
			goHome();
		}*/
	}

	// This method will be invoked when a new page becomes selected.当新的页面被选中时调用 
	@Override
	public void onPageSelected(int position) {
		currentItem = position;
		dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
		dots.get(position).setBackgroundResource(R.drawable.dot_focused);
		oldPosition = position;  
		Log.i("jve", "onPageSelected : position——》" + position);
	}
}

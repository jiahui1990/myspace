package com.jve.util.view;

import java.util.Date;
import com.jve.util.DateUtil;
import com.seebaobei.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListViewUtils extends ListView implements OnScrollListener {
	// ListView底部加载View
	private View footerView;
	// ListView头部刷新
	private View headerView;
	// ListView头部高
	private int headerViewHeight;

	private int totalItemCount;// 总数量
	private int lastVisibleItem;// 最后一个可见的item
	private int firstVisibleItem;// 当前第一个可见的item位置
	private int scrollState;// 当前滚动状态
	private boolean isRemark;// 标记当前是在ListView顶端按下
	private int startY;// 按下时的Y值
	private int state;// 当前的状态

	final int NONE = 0;// 正常状态
	final int PULL = 1;// 提示下拉状态
	final int RELESE = 2;// 提示释放状态
	final int REFLASHING = 3;// 刷新状态

	private boolean isLoading;// 正在加载

	private IListViewCallback listViewCallback;

	public ListViewUtils(Context context) {
		super(context);
		initView(context);
	}

	public ListViewUtils(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public ListViewUtils(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	/**
	 * 初始化ListView头部刷新和底部加载View
	 */
	private void initView(Context context) {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		headerView = layoutInflater.inflate(R.layout.listview_header, null);
		measureView(headerView);
		headerViewHeight = headerView.getMeasuredHeight();
		topPadding(-headerViewHeight);
		Log.i("jve", "====" + headerViewHeight);
		footerView = layoutInflater.inflate(R.layout.listview_footer, null);
		footerView.findViewById(R.id.jve_id_footer_listView).setVisibility(View.GONE);
		this.addHeaderView(headerView);
		this.addFooterView(footerView);
		this.setOnScrollListener(this);
	}

	/**
	 * 通知父布局占用的宽高
	 * 
	 * @param view
	 */
	private void measureView(View view) {
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		int height;
		int tempHeight = p.height;
		if (tempHeight > 0) {
			height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}

	/**
	 * 设置header布局上边距
	 * 
	 * @param topPadding
	 */
	private void topPadding(int topPadding) {
		headerView.setPadding(headerView.getPaddingLeft(), topPadding, headerView.getPaddingRight(), headerView.getPaddingBottom());
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		this.firstVisibleItem = firstVisibleItem;
		this.lastVisibleItem = firstVisibleItem + visibleItemCount;
		this.totalItemCount = totalItemCount;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.scrollState = scrollState;
		//headerView和footerView也算;
		if (totalItemCount == lastVisibleItem && scrollState == SCROLL_STATE_IDLE) {
			//if(totalItemCount >= (Constants.jve_ROWS + 2)) {
				if (!isLoading && state != REFLASHING) {
					isLoading = true;
					footerView.findViewById(R.id.jve_id_footer_listView).setVisibility(View.VISIBLE);
					// 加载更多
					listViewCallback.onLoad();
				}
			/*}else {
				
			}*/
			
			

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (firstVisibleItem == 0) {
				isRemark = true;
				startY = (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			onMove(ev);
			break;
		case MotionEvent.ACTION_UP:
			if (state == RELESE) {
				state = REFLASHING;
				// 加载最新数据
				reflashViewByState();
				listViewCallback.onReflash();
			} else if (state == PULL) {
				state = NONE;
				isRemark = false;
				reflashViewByState();
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 判断移动过程操作
	 * 
	 * @param ev
	 */
	private void onMove(MotionEvent ev) {
		if (!isRemark) {
			return;
		}
		int tempY = (int) ev.getY();
		int space = tempY - startY;
		int topPadding = space - headerViewHeight;
		switch (state) {
		case NONE:
			if (space > 0) {
				state = PULL;
				reflashViewByState();
			}
			break;
		case PULL:
			topPadding(topPadding);
			if (space > headerViewHeight + 30 && scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				state = RELESE;
				reflashViewByState();
			}
			break;
		case RELESE:
			topPadding(topPadding);
			if (space < headerViewHeight + 30) {
				state = PULL;
				reflashViewByState();
			} else if (space <= 0) {
				state = NONE;
				isRemark = false;
				reflashViewByState();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 根据状态更新UI
	 */
	private void reflashViewByState() {
		ImageView arrow = (ImageView) headerView.findViewById(R.id.jve_id_header_listView_arrow);
		ProgressBar progressBar = (ProgressBar) headerView.findViewById(R.id.jve_id_header_listView_progressBar);
		TextView tip = (TextView) headerView.findViewById(R.id.jve_id_header_listView_tip);

		RotateAnimation animation = new RotateAnimation(0, 180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(500);
		animation.setFillAfter(true);
		RotateAnimation animation1 = new RotateAnimation(180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation1.setDuration(500);
		animation1.setFillAfter(true);
		
		switch (state) {
		case NONE:
			arrow.clearAnimation();
			topPadding(-headerViewHeight);
			break;
		case PULL:
			arrow.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tip.setText(R.string.jve_str_public_pull_refresh);
			//下拉状态时，箭头向下
			arrow.clearAnimation();
			arrow.setAnimation(animation1);
			break;
		case RELESE:
			arrow.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tip.setText(R.string.jve_str_public_relese_refresh);
			//释放状态时，箭头向上
			arrow.clearAnimation();
			arrow.setAnimation(animation);
			break;
		case REFLASHING:
			topPadding(50);
			arrow.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			tip.setText(R.string.jve_str_public_reflashing_refresh);
			arrow.clearAnimation();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 获取完数据
	 */
	public void reflashComplete(){
		state =NONE;
		isRemark = false;
		reflashViewByState();
		TextView lastUpdateTime = (TextView) headerView.findViewById(R.id.jve_id_header_listView_time);
		lastUpdateTime.setText(DateUtil.format(new Date()));
	}

	/**
	 * 加载完毕
	 */
	public void loadComplete() {
		isLoading = false;
		footerView.findViewById(R.id.jve_id_footer_listView).setVisibility(View.GONE);
	}

	public void setInterface(IListViewCallback listViewCallback) {
		this.listViewCallback = listViewCallback;
	}

	/**
	 * ListView回调接口
	 * @author javae
	 *
	 */
	public interface IListViewCallback {
		/**
		 * 加载更多数据回调方法
		 */
		public void onLoad();
		/**
		 * 下拉刷新方法
		 */
		public void onReflash();
	}
}

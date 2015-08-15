package com.seebaobei.appmain.lanucher;

import java.util.HashMap;
import java.util.Map;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class GuideViewPager extends ViewPager {

	private View leftView;
	private View rightView;

	private float trans;
	private float scale;

	private static final float MIN_SCALE = 0.6f;

	private Map<Integer, View> children = new HashMap<Integer, View>();

	public GuideViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GuideViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void setViewForPosition(View view, Integer position) {
		children.put(position, view);
	}

	public void removeViewFromPosition(Integer position) {
		children.remove(position);
	}

	@Override
	protected void onPageScrolled(int position, float offset, int offsetPixels) {
		leftView = children.get(position);
		rightView = children.get(position + 1);
		//添加切换动画效果
		animStack(leftView, rightView, offset, offsetPixels);
		super.onPageScrolled(position, offset, offsetPixels);
	}

	private void animStack(View leftView, View rightView, float offset,
			int offsetPixels) {
		if(rightView != null) {
			//从0页到1页，offset：0-1；
			/** 
             * 缩小比例 如果手指从右到左的滑动（切换到后一个）：0.0~1.0，即从一半到最大 
             * 如果手指从左到右的滑动（切换到前一个）：1.0~0，即从最大到一半 
             */  
			scale = (1-MIN_SCALE)*offset + MIN_SCALE;
			/** 
             * x偏移量： 如果手指从右到左的滑动（切换到后一个）：0-720 如果手指从左到右的滑动（切换到前一个）：720-0 
             */  
			trans = -getWidth()-getPageMargin() + offsetPixels;
			
			ViewHelper.setScaleX(rightView, scale);
			ViewHelper.setScaleY(rightView, scale);
			ViewHelper.setTranslationX(rightView, trans);
			ViewHelper.setAlpha(rightView, 0.8f);
		}
		if(leftView != null) {
			leftView.bringToFront();
		}
	}

}

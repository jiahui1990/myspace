package com.jve.util;

import com.seebaobei.R;
import android.os.CountDownTimer;
import android.widget.Button;

public class CountDownUtils extends CountDownTimer {
	/**
	 * 需要显示倒计时的Button
	 */
	private Button button;
	
	
	//参数依次为总时长,和计时的时间间隔
	public CountDownUtils(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}
	
	
	public CountDownUtils(long millisInFuture, long countDownInterval, Button button) {
		this(millisInFuture, countDownInterval);
		this.button = button;
	}

	/**
	 * 计时完毕时触发
	 */
	@Override
	public void onFinish() {
		this.button.setBackgroundResource(R.drawable.button_selector_blue);
		this.button.setText("重新发送");
		this.button.setClickable(true);
	}
	
	/**
	 * 计时过程显示
	 */
	@Override
	public void onTick(long millisUntilFinished) {
		this.button.setClickable(false);
		this.button.setBackgroundResource(R.drawable.button_selector_blue_grey);
		this.button.setText(String.valueOf(millisUntilFinished/1000)+"s后可操作");
	}

}

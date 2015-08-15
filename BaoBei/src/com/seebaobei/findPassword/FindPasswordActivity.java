package com.seebaobei.findPassword;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jve.util.AsyncTaskUtils;
import com.jve.util.CountDownUtils;
import com.jve.util.NetUtils;
import com.jve.util.RegExpUtils;
import com.jve.util.UIHelperUtils;
import com.seebaobei.Constants;
import com.seebaobei.R;
import com.seebaobei.base.BaseActivity;

public class FindPasswordActivity extends BaseActivity implements OnClickListener {

	private ImageView backImageView;
	
	private TextView titleTextView;
	
	private CountDownUtils countDownUtils;
	
	private EditText username,phoneNum,code;
	
	private Button getCodeBtn, nextBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password);
		this.initViews();
		this.initEvents();
	}

	@Override
	public void initViews() {
		backImageView = (ImageView) findViewById(R.id.jve_id_top_back);
		titleTextView = (TextView) findViewById(R.id.jve_id_top_title);
		username = (EditText) findViewById(R.id.jve_id_findPwsd_username);
		phoneNum = (EditText) findViewById(R.id.jve_id_findPwsd_phoneNum);
		code = (EditText) findViewById(R.id.jve_id_findPwsd_code);
		getCodeBtn = (Button) findViewById(R.id.jve_id_findPwsd_getCodeBtn);
		nextBtn = (Button) findViewById(R.id.jve_id_findPwsd_nextBtn);
		this.initDatas();
	}

	@Override
	public void initDatas() {
		titleTextView.setText(R.string.jve_str_findPswdPage_str1);
		countDownUtils = new CountDownUtils(120000, 1000, getCodeBtn);
	}

	@Override
	public void initEvents() {
		backImageView.setOnClickListener(this);
		getCodeBtn.setOnClickListener(this);
		nextBtn.setOnClickListener(this);
	}

	@Override
	public boolean vertifyFrom() {
		if(super.isEmpty(username.getText().toString())){
			UIHelperUtils.showShortToast(R.string.jve_str_MSG2, this);
		}else if(super.isEmpty(phoneNum.getText().toString())) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG5, this);
		}else if(!RegExpUtils.verification(Constants.JVE_REG_PHONE, phoneNum.getText().toString())) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG6, this);
		}else if(super.isEmpty(code.getText().toString())) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG7, this);
		}else {
			return true;
		}
		
		return super.vertifyFrom();
	}
	/**
	 * 发送验证码验证
	 * @return
	 */
	public boolean vertifySendCode() {
		if(super.isEmpty(username.getText().toString())){
			UIHelperUtils.showShortToast(R.string.jve_str_MSG2, this);
		}else if(super.isEmpty(phoneNum.getText().toString())) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG5, this);
		}else if(!RegExpUtils.verification(Constants.JVE_REG_PHONE, phoneNum.getText().toString())) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG6, this);
		}else {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jve_id_top_back:
			this.finish();
			break;
		case R.id.jve_id_findPwsd_getCodeBtn:
			if(vertifySendCode()) {
				countDownUtils.start();
				//......发送验证码
				sendCode();
			}
			
			break;
		case R.id.jve_id_findPwsd_nextBtn:
			if(vertifyFrom()) {
				//。。。。下一步
				if(verifyCode() ){
					//验证成功后
					Intent intent = new Intent(this, FindPasswordNextActivity.class);
					intent.putExtra("phoneNum", phoneNum.getText().toString());
					intent.putExtra("username", username.getText().toString());
					startActivity(intent);
				}else{
					UIHelperUtils.showShortToast("验证码错误，请重新输入！", this);
				}
			}
			break;
		default:
			break;
		}
		
	}

	/**
	 * 发送短信验证码
	 */
	private void sendCode() {
		String sendCodeReturnString = null;
		try {
			sendCodeReturnString = new AsyncTaskUtils().execute(buildSendCodeParams()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (super.isEmpty(sendCodeReturnString)) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG12, this);
		} else {
			JSONObject obj = JSON.parseObject(sendCodeReturnString);
			if(!"MSG0000".equalsIgnoreCase(obj.getString("ret"))) { 
				UIHelperUtils.showShortToast(obj.getString("ret_info"), this);
			} else {
				Log.i("JJJ", obj.getString("ret_info"));
			}
		}
	}
	
	/**
	 * 发送短信验证码
	 */
	private boolean verifyCode() {
		boolean bStatus = true;
		String verifyCodeReturnString = null;
		try {
			verifyCodeReturnString = new AsyncTaskUtils().execute(buildVerifyCodeParams()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (super.isEmpty(verifyCodeReturnString)) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG12, this);
		} else {
			JSONObject obj = JSON.parseObject(verifyCodeReturnString);
			if(!"MSG0000".equalsIgnoreCase(obj.getString("ret"))) { 
				UIHelperUtils.showShortToast(obj.getString("ret_info"), this);
				bStatus = false;
			} else {
				Log.i("JJJ", obj.getString("ret_info"));
			}
		}
		return bStatus;
	}
	
	/**
	 * QinChuan
	 * 为请求构建参数
	 * @return
	 */
	private Map<String, Object> buildSendCodeParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		String paramString = "{\"msgID\":\"setPwd_sendphonecode\",\"telphone\":\""+phoneNum.getText().toString()+"\"}";
		params.put(Constants.JVE_URL_PARAMS, paramString);
		params.put(Constants.JVE_URL, Constants.WEB_URL);
		return params;
	}
	
	/**
	 * QinChuan
	 * 为请求构建参数
	 * @return
	 */
	private Map<String, Object> buildVerifyCodeParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		String paramString = "{\"msgID\":\"setPwd_vertifycode\",\"telphone\":\""+phoneNum.getText().toString()+"\",\"phoneCode\":\""+code.getText().toString()+"\"}";
		params.put(Constants.JVE_URL_PARAMS, paramString);
		params.put(Constants.JVE_URL, Constants.WEB_URL);
		return params;
	}

}

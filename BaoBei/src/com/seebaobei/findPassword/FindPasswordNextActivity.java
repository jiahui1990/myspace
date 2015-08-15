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
import com.jve.util.RegExpUtils;
import com.jve.util.UIHelperUtils;
import com.seebaobei.Constants;
import com.seebaobei.R;
import com.seebaobei.base.BaseActivity;
import com.seebaobei.login.LoginActivity;

public class FindPasswordNextActivity extends BaseActivity implements OnClickListener {

	private ImageView backImageView;
	
	private TextView titleTextView;
	
	private EditText newPswd,reNewPswd;
	
	private Button submitBtn;
	
	private String phoneNum,username;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password_next);
		this.initViews();
		this.initEvents();
	}

	@Override
	public void initViews() {
		backImageView = (ImageView) findViewById(R.id.jve_id_top_back);
		titleTextView = (TextView) findViewById(R.id.jve_id_top_title);
		newPswd = (EditText) findViewById(R.id.jve_id_findPwsd_newPwsd);
		reNewPswd = (EditText) findViewById(R.id.jve_id_findPwsd_reNewPwsd);
		submitBtn = (Button) findViewById(R.id.jve_id_findPwsd_submitBtn);
		this.initDatas();
	}

	@Override
	public void initDatas() {
		titleTextView.setText(R.string.jve_str_findPswdPage_str1);
	}

	@Override
	public void initEvents() {
		backImageView.setOnClickListener(this);
		submitBtn.setOnClickListener(this);
	}

	@Override
	public boolean vertifyFrom() {
		if(super.isEmpty(newPswd.getText().toString())){
			UIHelperUtils.showShortToast(R.string.jve_str_MSG8, this);
		} else if(super.isEmpty(reNewPswd.getText().toString())){
			UIHelperUtils.showShortToast(R.string.jve_str_MSG9, this);
		} else if(!newPswd.getText().toString().equals(reNewPswd.getText().toString())){
			UIHelperUtils.showShortToast(R.string.jve_str_MSG10, this);
		} else {
			return true;
		}
		return super.vertifyFrom();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jve_id_top_back:
			this.finish();
			break;
		case R.id.jve_id_findPwsd_submitBtn:
			if(vertifyFrom()) {
				phoneNum = getIntent().getStringExtra("phoneNum");
				username = getIntent().getStringExtra("username");
				if(modifyPassword()){
					//。。。。提交修改，成功后跳入登录页面
					UIHelperUtils.showShortToast(R.string.jve_str_MSG11, this);
					Intent intent = new Intent(this, LoginActivity.class);
					startActivity(intent);
					this.finish();
				}
			}
			break;
		default:
			break;
		}
		
	}

	private boolean modifyPassword() {
		boolean bStatus = true;
		String modifyPasswordReturnString = null;
		try {
			modifyPasswordReturnString = new AsyncTaskUtils().execute(buildModifyPasswordParams()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (super.isEmpty(modifyPasswordReturnString)) {
			UIHelperUtils.showShortToast(R.string.jve_str_MSG12, this);
		} else {
			JSONObject obj = JSON.parseObject(modifyPasswordReturnString);
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
	private Map<String, Object> buildModifyPasswordParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		String paramString = "{\"msgID\":\"setPwdFromTel\",\"username\":\""+username+"\",\"telphone\":\""+phoneNum+"\",\"passwd\":\""+newPswd.getText().toString()+"\"}";
		params.put(Constants.JVE_URL_PARAMS, paramString);
		params.put(Constants.JVE_URL, Constants.WEB_URL);
		return params;
	}

}

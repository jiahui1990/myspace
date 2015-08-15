package com.jve.util;

import java.util.Map;

import com.jve.util.http.HttpUtils;
import com.seebaobei.Constants;

import android.os.AsyncTask;

public class AsyncTaskUtils extends AsyncTask<Map<String, Object>, Void, String>{

	@Override
	protected String doInBackground(Map<String, Object>... params) {
		Map<String, Object> map = params[0];
		String appModel = (String) map.get(Constants.JVE_URL_PARAMS);
		String result = HttpUtils.sendPostMethod(map.get(Constants.JVE_URL).toString(), appModel,"utf-8");
		//String result = HttpUtils.sendGetMethod(map.get(Constants.JVE_URL).toString(),"utf-8");
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	
	
}

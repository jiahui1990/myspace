package com.scu.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpClientPost {
      
	public static String post(final String url ){
		String data =null;
		try {
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,  7000);//连接时间20s
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,  7000);//数据传输时间60s
		    org.apache.http.client.methods.HttpPost post = new org.apache.http.client.methods.HttpPost(url);
		    HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode()==200) {
				HttpEntity entity = response.getEntity();
				data = EntityUtils.toString(entity,"GBK");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	public static String get(final String url ){
		String data =null;
		try {
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,  7000);//连接时间20s
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,  7000);//数据传输时间60s
			HttpGet gets = new HttpGet(url);
			HttpResponse response = client.execute(gets);
			if (response.getStatusLine().getStatusCode()==200) {
				HttpEntity entity = response.getEntity();
				data = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}

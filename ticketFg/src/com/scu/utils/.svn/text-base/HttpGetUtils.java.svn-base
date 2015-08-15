package com.scu.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * 
 * 
 * @class HttpClient
 * @描述 本类主要功能为：
                          联网类！
 *                            内含多个方法和模块以及若干功能，
 *                            详情注释请看每个功能对应的代码注�?
 *  @Lq 灵奇安卓�?��规范：详情请查看本项目res->lq->AndroidNorm.txt
 */
public class HttpGetUtils {

	private InputStream is;

	public InputStream getSend(String url) {
		// 创建httpClient对象
		String content = null;
		HttpClient client = new DefaultHttpClient();
		// 创建get对象
		HttpGet get = new HttpGet(url);
		// 构�?实体对象
		try {
			HttpResponse resp = client.execute(get);
			int coedeaaa = resp.getStatusLine().getStatusCode();
			// Log.i("info", "响应返回�?+resp.getStatusLine().getStatusCode()+"");
			if (resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity respEntity = resp.getEntity();
//				content = EntityUtils.toString(respEntity);
//				content =URLEncoder.encode(content, "GB2312");
				is = respEntity.getContent();
			}
		} catch (Exception e) {
			//TODO	有时候这里会出现问题
		e.printStackTrace();
		}

		return is;
	}

	public static String getNetData(String url) {
		// 创建httpClient对象
		String content = null;
		HttpClient client = new DefaultHttpClient();
		// 创建get对象
		HttpGet get = new HttpGet(url);
		// 构�?实体对象
		try {
			HttpResponse resp = client.execute(get);
			int coedeaaa = resp.getStatusLine().getStatusCode();
			// Log.i("info", "响应返回�?+resp.getStatusLine().getStatusCode()+"");
			if (resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity respEntity = resp.getEntity();
				content = EntityUtils.toString(respEntity);
//				content =URLEncoder.encode(content, "GB2312");
//				is = respEntity.getContent();
			}
		} catch (Exception e) {
			//TODO	有时候这里会出现问题
		e.printStackTrace();
		}

		return content;
	}
	
	/**
     * 
     * 判断网络状态是否可用
     * 
     * @return true: 网络可用 ; false: 网络不可用
     */ 
    public static boolean isNetworkConnected(Context activity) { 
        ConnectivityManager conManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE); 
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo(); 
        if (networkInfo != null) { // 这个判断一定要，要不然会出错 
            return networkInfo.isAvailable(); 
        } 
        return false; 
    } 
    
    /**
     * URL加密
     * @param s
     * @return null if the given string is null.
     * @throws UnsupportedEncodingException
     */ 
    public static String decode(String s, String enc) throws UnsupportedEncodingException { 
        return s == null ? null : URLDecoder.decode(s, enc); 
    } 
 
    public static String encode(String s, String enc) throws UnsupportedEncodingException { 
        return URLEncoder.encode((s == null ? "" : s), enc); 
    } 
 
}

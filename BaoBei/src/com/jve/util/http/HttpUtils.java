package com.jve.util.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.seebaobei.Constants;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * HTTP工具类
 * 
 * @author qinc
 * 
 */
public class HttpUtils {
	// 创建一个Http客户端对象
	private static HttpClient httpClient = new DefaultHttpClient();

	/**
	 * 以POST方式发送HTTP请求
	 * 
	 * @param path
	 * @param params
	 * @param encoding
	 * @return
	 */
	/*public static String sendPostMethod(String path, Map<String, Object> params, String encoding) {
		String result = "";
		// 创建HttpPost请求对象
		HttpPost httpPost = new HttpPost(path);
		// 需要提交的參數
		HttpEntity httpEntity = null;
		HttpResponse httpResponse = null;
		//
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String name = entry.getKey();
				String value = entry.getValue().toString();
				NameValuePair nameValuePair = new BasicNameValuePair(name, value);
				parameters.add(nameValuePair);
			}
		}
		try {
			// 純文本表單，不包含文件
			httpEntity = new UrlEncodedFormEntity(parameters, encoding);
			httpPost.setEntity(httpEntity);
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}*/

	/**
	 * 
	 * @param path
	 * @param paramsString
	 *            :一个变量把所有参数传到服务器
	 * @param encoding
	 * @return
	 */
	public static String sendPostMethod(String path, String paramsString, String encoding) {
		String result = "";
		// 创建HttpPost请求对象
		HttpPost httpPost = new HttpPost(path);
		// 需要提交的參數
		HttpEntity httpEntity = null;
		HttpResponse httpResponse = null;
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair nameValuePair = new BasicNameValuePair(Constants.JVE_URL_PARAMS, paramsString);
		parameters.add(nameValuePair);
		try {
			// 純文本表單，不包含文件
			httpEntity = new UrlEncodedFormEntity(parameters, encoding);
			httpPost.setEntity(httpEntity);
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 以GET方式发送HTTP请求
	 * 
	 * @param path
	 * @param encoding
	 * @return
	 */
	public static String sendGetMethod(String path, String encoding) {
		String result = "";
		// 创建HttpPost请求对象
		HttpGet httpGet = new HttpGet(path);
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	
	/**
	 * 根据图片路径加载网络图片
	 * QinChuan
	 * @param imageUrl
	 * @return
	 */
	public static  Bitmap getHttpBitmap(String imageUrl) {  
        Bitmap bitmap = null; 
        InputStream in = null;
        try  
        {  
            URL pictureUrl = new URL(imageUrl); 
            URLConnection connection = pictureUrl.openConnection();
            connection.connect();
            in = connection.getInputStream();  
            bitmap = BitmapFactory.decodeStream(in);  
        }catch(Exception e) {
        	e.printStackTrace();
        }finally{
        	if(in != null){
        		try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
        	}
        }
          
        return bitmap;  
	}
	/**
	 * 下载APK文件，返回下载完成的目录
	 * @param path
	 * @return
	 */
	public static String downLoadApk(String path) {
		byte[] data = null;
		HttpGet httpGet = new HttpGet(path);
		File file = Environment.getExternalStorageDirectory();
		FileOutputStream outputStream = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				data = EntityUtils.toByteArray(httpResponse.getEntity());
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					outputStream = new FileOutputStream(new File(file,
							Constants.JVE_APK_NAME));
					outputStream.write(data, 0, data.length);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return file.getAbsolutePath() + "/" + Constants.JVE_APK_NAME;
	}
	
	
	/*public static boolean uploadPicture(String path, String paramsString, String encoding) {
		boolean success = false;
		HttpPost httpPost = new HttpPost(path);
		// �?��提交的參�?
		HttpEntity httpEntity = null;
		HttpResponse httpResponse = null;
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair nameValuePair = new BasicNameValuePair(Constants.TESCOP_URL_PARAMS, paramsString);
		parameters.add(nameValuePair);
		try {
			// 純文本表單，不包含文�?
			httpEntity = new UrlEncodedFormEntity(parameters, encoding);
			httpPost.setEntity(httpEntity);
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				success = true;
			}else {
				success = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}*/
	
	
}

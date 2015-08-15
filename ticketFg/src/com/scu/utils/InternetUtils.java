package com.scu.utils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class InternetUtils { 

	private static final String USER_AGENT = "User-Agent"; 

	public static String httpPost(String url, List<NameValuePair> nameValuePairs) throws ClientProtocolException, IOException { 
		HttpClient httpclient = new DefaultHttpClient(); 
		HttpPost httpPost = new HttpPost(url); 
		// List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2); 
		// Your DATA 
		// nameValuePairs.add(new BasicNameValuePair("id", "12345")); 
		// nameValuePairs.add(new BasicNameValuePair("stringdata", 
		// "eoeAndroid.com is Cool!")); 

		httpPost.setHeader(USER_AGENT, "Mozilla/4.5"); 
		HttpEntity httpEntity = null; 
		try { 
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8)); 
			httpEntity = httpclient.execute(httpPost).getEntity(); 
		} finally { 
			//httpPost.abort(); 
		} 
		return retrieveHttpEntity(httpEntity); 
	} 

	public static InputStream download(URL url) throws IOException { 
		URLConnection conn = url.openConnection(); 
		InputStream is = conn.getInputStream(); 
		return is; 
	} 

	public static byte[] downloadFileData(String surl) throws IOException { 
		URL url = new URL(surl); 
		URLConnection conn = url.openConnection(); 
		// ��ȡ���� 
		int length = (int) conn.getContentLength(); 
		InputStream is = conn.getInputStream(); 
		byte[] imgData = null; 
		if (length != -1) { 
			imgData = new byte[length]; 
			byte[] temp = new byte[512]; 
			int readLen = 0; 
			int destPos = 0; 
			while ((readLen = is.read(temp)) > 0) { 
				System.arraycopy(temp, 0, imgData, destPos, readLen); 
				destPos += readLen; 
			} 
		} 
		return imgData; 
	} 

	public static InputStream download(String url) throws IOException { 
		return download(new URL(url)); 
	} 

	public static String httpPost(String url) throws ClientProtocolException, IOException { 
		return httpPost(url, new ArrayList<NameValuePair>()); 
	} 

	private static String retrieveHttpEntity(HttpEntity httpEntity) throws UnsupportedEncodingException, IllegalStateException, 
	IOException { 
		StringBuffer stringBuffer = new StringBuffer(); 
		InputStreamReader is = new InputStreamReader(httpEntity.getContent(), HTTP.UTF_8); 
		BufferedReader bufferedReader = new BufferedReader(is); 
		String line; 
		while ((line = bufferedReader.readLine()) != null) { 
			stringBuffer.append(line); 
		} 
		return stringBuffer.toString(); 
	} 

	public static String uploadFile(String actionUrl, String newName, InputStream fStream) { 
		String end = "\r\n"; 
		String twoHyphens = "--"; 
		String boundary = java.util.UUID.randomUUID().toString(); 
		DataOutputStream ds = null; 
		try { 
			URL url = new URL(actionUrl); 
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
			/* ����Input��Output����ʹ��Cache */ 
			con.setDoInput(true); 
			con.setDoOutput(true); 
			con.setUseCaches(false); 
			/* �趨���͵�method=POST */ 
			con.setRequestMethod("POST"); 
			/* setRequestProperty */ 
			con.setRequestProperty("Connection", "Keep-Alive"); 
			con.setRequestProperty("Charset", "UTF-8"); 
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary); 
			/* �趨DataOutputStream */ 
			ds = new DataOutputStream(con.getOutputStream()); 
			ds.writeBytes(twoHyphens + boundary + end); 
			ds.writeBytes("Content-Disposition: form-data; " + "name=\"Filedata\";filename=\"" + newName + "\"" + end); 
			ds.writeBytes(end); 

			/* ȡ���ļ���FileInputStream */ 
			// FileInputStream fStream = new FileInputStream(uploadFile); 
			/* �趨ÿ��д��1024bytes */ 
			int bufferSize = 1024; 
			byte[] buffer = new byte[bufferSize]; 

			int length = -1; 
			/* ���ļ���ȡ��ݵ������� */ 
			while ((length = fStream.read(buffer)) != -1) { 
				/* �����д��DataOutputStream�� */ 
				ds.write(buffer, 0, length); 
			} 
			ds.writeBytes(end); 
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end); 

			ds.flush(); 

			/* ȡ��Response���� */ 
			InputStream is = con.getInputStream(); 
			int ch; 
			StringBuffer b = new StringBuffer(); 
			while ((ch = is.read()) != -1) { 
				b.append((char) ch); 
			} 
			/* ��Response��ʾ��Dialog */ 
			// showDialog(b.toString().trim()); 
			return b.toString().trim(); 
			/* �ر�DataOutputStream */ 

		} catch (Exception e) { 
			// showDialog("" + e); 
		} finally { 
			try {
				ds.close();
				fStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return null; 
	} 

	/**
	 * 
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

	/**
	 * 
	 * �ж�����״̬�Ƿ����
	 * 
	 * @return true: ������� ; false: ���粻����
	 */ 
	public static boolean isNetworkConnected(Activity activity) { 
		ConnectivityManager conManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo networkInfo = conManager.getActiveNetworkInfo(); 
		if (networkInfo != null) { // ����ж�һ��Ҫ��Ҫ��Ȼ����� 
			return networkInfo.isAvailable(); 
		} 
		return false; 
	} 

	/**
	 * ����HttpUrlConnection
	 * ��ָ����URL�л�ȡ����
	 * @param urlPath
	 * @return
	 * @throws Exception
	 */
	public static String readParse(String urlPath) throws Exception {  
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
		byte[] data = new byte[1024];  
		int len = 0;  
		URL url = new URL(urlPath);  
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
		InputStream inStream = conn.getInputStream();  
		while ((len = inStream.read(data)) != -1) {  
			outStream.write(data, 0, len);  
		}  
		inStream.close();  
		return new String(outStream.toByteArray());//ͨ��out.Stream.toByteArray��ȡ��д�����  
	}


	/**
	 * ����HttpClient
	 * ������ݿⲢ����JSON����ַ�
	 * 
	 * @param params ��������˴��Ĳ���
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String doPost(List<NameValuePair> params, String url)
			throws Exception {
		String result = null;
		// ��ȡHttpClient����
		HttpClient httpClient = new DefaultHttpClient();
		// �½�HttpPost����
		HttpPost httpPost = new HttpPost(url);
		if (params != null) {
			// �����ַ�
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			// ���ò���ʵ��
			httpPost.setEntity(entity);
		}

		/*// ���ӳ�ʱ
         httpClient.getParams().setParameter(
                 CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
         // ����ʱ
         httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                 3000);*/
		// ��ȡHttpResponseʵ��
		HttpResponse httpResp = httpClient.execute(httpPost);
		// �ж��ǹ�����ɹ�
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// ��ȡ���ص����
			result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		} else {
			Log.i("HttpPost", "HttpPost��ʽ����ʧ��");
		}

		return result;
	}
}
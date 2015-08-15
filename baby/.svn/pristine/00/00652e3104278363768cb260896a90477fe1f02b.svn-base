package javacommon.javautil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientService {
	/**
	 * Description: HttpClient GET方法 1. 创建 HttpClient 的实例 2. 创建某种连接方法的实例，在这里是
	 * GetMethod。在 GetMethod 的构造函数中传入待连接的地址 3. 调用第一步中创建好的实例的 execute
	 * 方法来执行第二步中创建好的 method 实例 4. 读 response 5. 释放连接。无论执行方法是否成功，都必须释放连接 6.
	 * 对得到后的内容进行处理
	 * 
	 */
	public static String get(String url, Map<String, String> params) {
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口(第一个参数是代理服务器地址，第二个参数是端口号。)
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);

		// 创建GET方法的实例，在GET方法的构造函数中传入待连接的地址即可。
		// 用GetMethod将会自动处理转发过程，如果想要把自动处理转发过程去掉的话，可以调用方法setFollowRedirects(false)。
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		if (params != null)
			for (String key : params.keySet()) {
				url = url + (url.indexOf("?") == -1 ? "?" : "&") + key + "="
						+ params.get(key);
			}
		HttpMethod getMethod = new GetMethod(url);

		// 调用实例httpClient的executeMethod方法来执行getMethod。
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 执行getMethod
		int statusCode;
		String returnString = null;
		try {
			statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			/**
			 * 在返回的状态码正确后，即可取得内容。取得目标地址的内容有三种方法：
			 * 第一种，getResponseBody，该方法返回的是目标的二进制的byte流；
			 * 第二种，getResponseBodyAsString
			 * ，这个方法返回的是String类型，值得注意的是该方法返回的String的编码是根据系统默认的编码方式
			 * ，所以返回的String值可能编码类型有误，在本文的"字符编码"部分中将对此做详细介绍；
			 * 第三种，getResponseBodyAsStream
			 * ，这个方法对于目标地址中有大量数据需要传输是最佳的。在这里我们使用了最简单的getResponseBody方法。
			 */
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			returnString = new String(responseBody, "utf-8");
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return returnString;

	}

	/**
	 * Description: HttpClient POST方法
	 * HttpClient中的PostMethod与GetMethod类似，除了设置PostMethod的实例与GetMethod有些不同之外
	 * ，剩下的步骤都差不多
	 * 
	 */
	public static String post(String url, Map<String, String> params) {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		
		if (params != null) {
			NameValuePair[] pairs = new NameValuePair[params.size()];
			int i = 0;
			for (String key : params.keySet()) {
				pairs[i] = new NameValuePair(key, params.get(key));
				i++;
			}
			post.setRequestBody(pairs);
		}
		// 执行getMethod
		int statusCode;
		String returnString = null;
		try {
			statusCode = client.executeMethod(post);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + post.getStatusLine());
			}
			/**
			 * 在返回的状态码正确后，即可取得内容。取得目标地址的内容有三种方法：
			 * 第一种，getResponseBody，该方法返回的是目标的二进制的byte流；
			 * 第二种，getResponseBodyAsString
			 * ，这个方法返回的是String类型，值得注意的是该方法返回的String的编码是根据系统默认的编码方式
			 * ，所以返回的String值可能编码类型有误，在本文的"字符编码"部分中将对此做详细介绍；
			 * 第三种，getResponseBodyAsStream
			 * ，这个方法对于目标地址中有大量数据需要传输是最佳的。在这里我们使用了最简单的getResponseBody方法。
			 */
			/*System.out.println(post.getResponseBody().length);
			// 读取内容
			InputStream responseBody = post.getResponseBodyAsStream();
			// 处理内容int BUFFER_SIZE = 4096;  
			String resultData = "";  
			int n;
			StringBuffer out = new StringBuffer();  
			byte[] b =  new byte[4096];  
			while ((n=responseBody.read(b))!=-1){  
			    out.append(new String(b,0,n));  
			}  
			post.getResponseBody();
			resultData = out.toString();*/
			returnString = new String(post.getResponseBody(),"utf-8");
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			post.releaseConnection();
		}
		return returnString;
	}
	/*public static String timeTaskPost(String url, Map<String, String> params) {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		
		if (params != null) {
			NameValuePair[] pairs = new NameValuePair[params.size()];
			int i = 0;
			for (String key : params.keySet()) {
				pairs[i] = new NameValuePair(key, params.get(key));
				i++;
			}
			post.setRequestBody(pairs);
		}
		// 鎵цgetMethod
		int statusCode;
		String returnString = null;
		try {
			statusCode = client.executeMethod(post);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + post.getStatusLine());
			}
			InputStream responseBody = post.getResponseBodyAsStream();
			String resultData = "";  
			int n;
			StringBuffer out = new StringBuffer();  
			byte[] b =  new byte[4096];  
			while ((n=responseBody.read(b))!=-1){  
			    out.append(new String(b,0,n));  
			}  
			resultData = out.toString();
			returnString = resultData;
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return returnString;
	}
	*/
	/**
	 * 函 数 名:getHostDomain<br>
	 * 功 能：网站对外访问，请求时需带sessionid <br>
	 * @time：2014-12-16 下午2:47:54  
	 * @author LiuLun
	 */
	/*public static String getHostDomain(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() +(request.getServerPort()!=80?(":"
				+ request.getServerPort()):"") + "/";
	}*/
	/**
	 * 函 数 名:getLocalHostDomain<br>
	 * 功 能：网站对内访问，判定访问中带有localhost就表示内部请求 <br>
	 * @time：2014-12-16 下午2:47:54  
	 * @author LiuLun
	 */
	/*public static String getLocalHostDomain(HttpServletRequest request) {
		return request.getScheme() + "://localhost" +(request.getServerPort()!=80?(":"
				+ request.getServerPort()):"") + "/";
	}*/
	/**
	 * 函 数 名:getSessionUrl <br>
	 * 功 能：获取带有sessionid 的url <br>
	 * @time：2014-12-25 下午6:25:16  
	 * @author LiuLun
	 */
	/*public static String getSessionUrl(HttpServletRequest request,String url,Boolean fullUrl){
		String[] urls=url.split("\\?");
		url=(fullUrl?HttpClientService.getHostDomain(request):"")+urls[0]+";jsessionid="+request.getSession().getId()+(urls.length==1?"":("?"+urls[1]));
		return url;
	}*/
	/**
	 * 函 数 名:get <br>
	 * 功 能：同步或异步执行请求方法 <br>
	 * @time：2014-12-29 下午4:01:12  
	 * @author LiuLun
	 */
	/*public static void get(final String url, final Map<String, String> params,Boolean async){
		if(async)
		new Thread(){
			public void run() {
				get(url, params);
			};
		}.start();
		else get(url, params);
	}*/
	/**
	 * 函 数 名:post <br>
	 * 功 能：同步或异步执行请求方法 <br>
	 * @time：2014-12-29 下午4:01:12  
	 * @author LiuLun
	 */
	/*public static void post(final String url, final Map<String, String> params,Boolean async){
		if(async)
		new Thread(){
			public void run() {
				post(url, params);
			};
		}.start();
		else post(url, params);
	}
	public static String getParam(String url ,int index){
		String s[]=url.split("\\?");
		if(s.length>1){
			String params[]=s[1].split("&");
			if(params.length>index)
				return params[index].split("=")[0];
		}
		return null;
	}*/
}

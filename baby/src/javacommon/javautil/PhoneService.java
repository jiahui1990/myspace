package javacommon.javautil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.phone.SendPhoneServer.SendPhoneServer;


public class PhoneService 
{

	/**
	 * 发送手机验证码的路径
	 */
	private static String url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	
   /**
    * 功能：发送手机校验信息
    * 输入参数：phoneNumber：电话号码  手机号码，如多个以英文逗号分隔 如: 15102110086,151****0086
    *                     phoneContent：发送的电话内容  多个内容之间使用”{|}”分隔，如: 内容一{|}内容二
    * 返回值：0：不成功，2：成功
    * 关联函数：
    * 作者：
    * @param phoneNumber
    * @param phoneContent
    * @return String
 * @throws UnsupportedEncodingException 
    * @roseuid 538F16AD00C9
    */
   public static String sendPhone(String phoneNumber, String phoneContent)
   {
	   Document doc;
	try {
		
		Map<String, String> params = new HashMap<>();
		params.put("account", "cf_koubeiyoupin");
		params.put("password", "123456");
		params.put("mobile", phoneNumber);
		params.put("content", phoneContent);
		
		
		String returnXML = HttpClientService.post(url, params);
		doc = DocumentHelper.parseText(returnXML);
	    Element root = doc.getRootElement();  
	   String re= root.elementText("code");
	   return re.equals("2")?re:"0";
	} catch (Exception e) {
		System.out.println("解析返回短信XML出错");
		e.printStackTrace();
		 return "0";
	} 
   }
 
   /**
    * 功能：产生手机校验码
    * 输入参数：
    * 返回值：系统生成的手机校验码
    * 关联函数：
    * 作者：
    * @return Integer
    * @roseuid 538F16D7010F
    */
   public static Integer createPhoneLdentifier() 
   {
    return null;
   }
   
   private static Random random = new Random();  
	private static String randString = "0123456789";//随机产生的字符串  
	//private int stringNum = 4;//随机产生字符数量
	
	/**
	 * 获取随机的字符		
	 * @param num
	 * @return
	 */
	public static String getRandomString(int stringNum){ 
		StringBuffer randomString = new StringBuffer("");
		for(int i=0;i<stringNum;i++){
			randomString.append(randString.charAt(random.nextInt(randString.length()))) ;
		}
		return randomString.toString();  
	}
}

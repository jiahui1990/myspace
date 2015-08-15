package com.scu.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

//import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @������:�ַ�����
 * @����: StringUtils
 * @����: com.gnet.provision.util
 * @������: dongjun.duan
 * @����ʱ��: 2012-3-8 ����01:35:15
 */
public class StringUtil {
	
	/**
	 * @�����������滻�ַ���ָ����ŵ��ִ�
	 * @�����ߣ�dongjun.duan
	 * @����ʱ�䣺2012-3-8 ����01:35:49
	 *  
	 * @param sourceStr			Դ�ַ�	���磺"11{arg}55{arg}99{arg}88"
	 * @param replaceTemplate	�滻ģ�棨һ���̶����ִ��� ���磺"{arg}"
	 * @param replaceStr		�滻���� 	���磺"ddj"
	 * @return					��������	���磺"11ddj55ddj99ddj88"
	 * @����ֵ���ͣ�String
	 */
	public static String replaceStr(String sourceStr,String replaceTemplate,String replaceStr){
		String firstStr = "";
		String secondStr = "";
		boolean hasOther = sourceStr.indexOf(replaceTemplate) != -1;
		
		while(hasOther){
			firstStr = sourceStr.substring(0, sourceStr.indexOf(replaceTemplate));
			secondStr = sourceStr.substring(sourceStr.indexOf(replaceTemplate) + replaceTemplate.length());
			sourceStr = firstStr + replaceStr + secondStr;
			hasOther = sourceStr.indexOf(replaceTemplate) != -1;
		}
		return sourceStr;
	}
	
	/**
	 * 
	 * @������������������0(0.0,0.00)�������
	 * @�����ߣ�haijun.jiang
	 * @����ʱ�䣺2012-4-1 ����09:44:14
	 *  
	 * @param str
	 * @return result or "���"
	 * @����ֵ���ͣ�String
	 */
	public static String getFreeFromZero(String str){
		String result = "";
		if(str == null || "".equals(str)){
			return "";
		}
		if("0".equals(str) || "0.0".equals(str) || "0.00".equals(str)){
			result = "���";
		}else{
			result = str;
		}
		return result;
	}
	 
	
	/**
	 * 
	 * @����������if null or "",return 0
	 * @�����ߣ�haijun.jiang
	 * @����ʱ�䣺2012-3-16 ����02:11:04
	 *  
	 * @param str
	 * @return  str or "0"
	 * @����ֵ���ͣ�String
	 */
	private static String  getStringOfNullandEmptyString(String str){
		String result = "0";
		if(str != null && str.length() > 0){
			result = str;
		}else{
			//default value
		}
		return result;
	}
	
	
	/**
	 * @�����������Ӵ�����ַ��У��á�;���ָ�õ�sql����б�
	 * @�����ߣ�dongjun.duan
	 * @����ʱ�䣺2012-3-8 ����01:18:39
	 *  
	 * @param sql	sql�����ԡ�;���ָ�
	 * @return		sql����б�
	 * @����ֵ���ͣ�List<String>
	 */
	public static List<String> getCreateSqlList(String sql){
		String[] strArray = sql.split(";");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<strArray.length - 1;i++){
			list.add(strArray[i]);
		}
 		return list;
	}
	
	public static String getYmString(String str){
		str = str.substring(str.length()-7, str.length());
		return str;
	}
	
	public static String luanma(String str,int times){
		try {
			if(str!=null){
				for (int i = 0; i < times; i++) {
					str = new String(str.getBytes("iso8859-1"),"utf-8");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(str);
		return str;
	}
	
	public static String luanma(String str){
		try {
			if(str!=null){
				str = new String(str.getBytes("iso8859-1"),"utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String getRandom(int weishu){
		Random r = new Random();
		String ranStr = "";
		int j = 0;
		while (j < weishu) {
			ranStr += r.nextInt(10)+"";
			j++;
		}
		return ranStr;
	}
	
	public static String formatDateAsYmd(Date date){
		String str;
		 if(date!=null){
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
			 str = df.format(date);
		 }else{
			 str = "";
		 }
		 return str;
	}
	
	public static String formatDateAsYmdyms(Date date){
		String str;
		 if(date!=null){
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
			 str = df.format(date);
		 }else{
			 str = "";
		 }
		 return str;
	}

	public static void main(String[] args) {
		System.out.println(getYmString("KH10000012013-04"));
	}
}

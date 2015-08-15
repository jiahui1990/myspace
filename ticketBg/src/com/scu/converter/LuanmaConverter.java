package com.scu.converter;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class LuanmaConverter extends StrutsTypeConverter{

	/**
	 * @param Map
	 * @param String[]
	 * @param Class
	 */
	@Override
	public Object convertFromString(Map arg0, String[] str, Class toType) {
		String value = null;
		if(toType.equals(String.class)){
			String key = str[0];
			try {
				value  = new String(key.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		System.out.println("LuanmaConverter");
		return value;
	}

	/**
	 * Timestamp包括年月日时分秒，此处将其转化成年月日显�?	 * @param Map
	 * @param Object
	 */
	@Override
	public String convertToString(Map arg0, Object value) {
		Timestamp timestamp = (Timestamp) value;
		String formatType = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		String str = sdf.format(timestamp);
		return str;
	}
	
}

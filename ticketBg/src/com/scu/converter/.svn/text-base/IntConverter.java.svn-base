package com.scu.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class IntConverter extends StrutsTypeConverter{

	/**
	 * @param Map
	 * @param String[]
	 * @param Class
	 */
	@Override
	public Object convertFromString(Map arg0, String[] str, Class toType) {
		int value = 0;
		if(toType.equals(Integer.class)){
			String key = str[0];
			value = Integer.parseInt(key);
		}
		return value;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param Map
	 * @param Object
	 */
	/*@Override
	public String convertToString(Map arg0, Object value) {
		Timestamp timestamp = (Timestamp) value;
		String formatType = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		String str = sdf.format(timestamp);
		return str;
	}*/
	
	
}

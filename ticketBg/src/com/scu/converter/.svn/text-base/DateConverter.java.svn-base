package com.scu.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter{

	/**
	 * @param Map
	 * @param String[]
	 * @param Class
	 */
	@Override
	public Object convertFromString(Map arg0, String[] str, Class toType) {
		Timestamp timestamp = null;
		if(toType.equals(Timestamp.class)){
			String key = str[0];
			Date date = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = sdf.parse(key);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			timestamp = new Timestamp(date.getTime());
		}
		return timestamp;
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

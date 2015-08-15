package com.jve.util.json;

import com.alibaba.fastjson.JSON;
/**
 * 解析JSON数据工具类
 * @author qinc
 *
 * @param <T>
 */
public class JSONTools<T> {
	
	/**
	 * 把JSON字符串转换成相应的对像
	 * @param jsonString
	 * @return
	 */
	public T fromJsonStringToObject(String jsonString, T t) {
		T obj = null;
		try {
			obj = (T) JSON.parseObject(jsonString, t.getClass());
		}catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}
	/*public static Object jsonStringToObject(String jsonString, Class<?> clazz) {
		Object obj = null;
		try {
			obj = (Object) JSON.parseObject(jsonString, clazz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}
	public static String objectToJsonString(Object object){
		String json = null;
		try{
			json = JSON.toJSONStringWithDateFormat(object,"yyyy-MM-dd HH:mm:ss");
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}*/
	/**
	 * 把对像转换成JSON字符串
	 * @param t
	 * @return
	 */
	public String fromObjectToJsonString(T t){
		String json = null;
		try{
			json = JSON.toJSONStringWithDateFormat(t,"yyyy-MM-dd HH:mm:ss");
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
}

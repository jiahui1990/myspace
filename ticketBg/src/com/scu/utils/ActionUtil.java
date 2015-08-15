package com.scu.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class ActionUtil {
	
	private ActionUtil(){}
	
	//将request中的参数处理为参数Map
	public static void setParametersFromRequestIntoParametersMap(Map<String,Object> actiomParametersMap,HttpServletRequest request) {
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = ((String)paramValues[0]).trim();
				if(!paramValue.equals(""))
					actiomParametersMap.put(paramName, paramValue);
			} else {
				List<String> notEmptyOnes = new ArrayList<String>();
				for (int i = 0; i < paramValues.length; i++) {
					paramValues[i] = paramValues[i].trim();
					if(!paramValues[i].trim().equals(""))
						notEmptyOnes.add(paramValues[i].trim());
				}
				String[] pValues = new String[notEmptyOnes.size()];
				for(int j=0;j<notEmptyOnes.size();j++)
					pValues[j] = notEmptyOnes.get(j);
				if(pValues.length>0)
					actiomParametersMap.put(paramName, pValues);
			}
		}
	}
	
	//从参数Map中取得单个参数
	public static String getParameterFromParametersMap(Map<String,Object> map,String key) {
		String str = null;
		if (map.get(key) != null) {
			if(map.get(key) instanceof java.lang.String)
				str = (String)map.get(key);
			else if(map.get(key) instanceof java.lang.String[])
				str = ((String[])map.get(key))[0];
		}
		return str;
	}
	//从参数Map中取得参数数组
	public static String[] getParametersFromParametersMap(Map<String,Object> map,String key) {
		String[] strs = null;
		if (map.get(key) != null) {
			if(map.get(key) instanceof java.lang.String){
				strs = new String[1];
				strs[0] = (String)map.get(key);
			}else if(map.get(key) instanceof java.lang.String[])
				strs = (String[]) map.get(key);
		}
		return strs;
	}
	
	
	
	//将返回值写回到request中
	public static void setReturnValuesIntoRequest(Map actionReturnValuesMap, HttpServletRequest request) {
		Iterator it = actionReturnValuesMap.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			request.setAttribute(key, actionReturnValuesMap.get(key));
		}
	}
 	//得到IP地址
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
 	}
	//将返回值转换为json串并写回到response中
	public static void setReturnValuesIntoResponse(Map actiomReturnValuesMap, HttpServletResponse response) throws IOException {
		JSONObject jo = new JSONObject();
		jo.putAll(actiomReturnValuesMap);
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache");
		//有文件上传时回写的类型指定为html
		if(actiomReturnValuesMap.get("ajaxForm") != null){
			response.setContentType("text/html;charset=UTF-8");
			out.write("<div>"+jo.toString()+"</div>");
		}else{
			response.setContentType("text/json;charset=UTF-8");
			out.write(jo.toString());
		}
		out.flush();
		out.close();
	}
	
	//将消息写到session中
	public static void setActionMessageIntoSession(List<String> msgList, HttpSession session) {
		if(msgList.size()>0) session.setAttribute("msgList", msgList);
	}
	//将消息写到returnValueMap中	public static void setActionMessageIntoReturnValues(List<String> msgList, Map returnValuesMap, boolean isValidated) {
		if(msgList.size()>0){
			returnValuesMap.put("msgList", msgList);
			if(!isValidated)
				returnValuesMap.put("validateErr", "true");
		}
	}
}

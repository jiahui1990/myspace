package com.scu.utils;

public class StringUtil {
	//获取ic卡中第1块的内容(块内容为ticketId)
	public String getCardTicketId(byte[] buffer){
		String str = "";
		for (int i = 0; i < buffer.length; i++) {
			if(buffer[i]!=-52&&buffer[i]!=0){
				str+=(char)buffer[i];
			}
		}
		return str.trim();
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
}

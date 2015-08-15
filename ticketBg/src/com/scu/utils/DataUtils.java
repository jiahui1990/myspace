package com.scu.utils;


public class DataUtils {
	
	/**
	 * @������������ȡ���ڵģ�yyyy-mm-dd
	 * @�����ߣ�dongjun.duan
	 * @����ʱ�䣺2012-6-20 ����01:59:36
	 *  
	 * @param dateStr
	 * @return String
	 */
	public static String dateFormat(String dateStr){
		if(dateStr == null || "".equals(dateStr)){
			return "";
		}else{
			return dateStr.substring(0, dateStr.indexOf(" "));
		}
	}
	
	public static String getTableName(int table){
		String tableName="";
		switch (table) {
		case 1:tableName="用户表";break;
		case 2:tableName="区域表";break;
		case 3:tableName="客户表";break;
		case 4:tableName="账单表";break;
		case 5:tableName="日志表";break;
		default:
			break;
		}
		return tableName;
	}
	
	public static String getBackupTableName(int table){
		String backupTableName="";
		switch (table) {
		case 1:backupTableName="user_backup";break;
		case 2:backupTableName="area_backup";break;
		case 3:backupTableName="client_backup";break;
		case 4:backupTableName="bill_backup";break;
		case 5:backupTableName="log_backup";break;
		default:
			break;
		}
		return backupTableName;
	}

	public static void main(String[] args) {
		System.out.println(DataUtils.dateFormat("2012-03-14 16:48:11.723"));

	}

}

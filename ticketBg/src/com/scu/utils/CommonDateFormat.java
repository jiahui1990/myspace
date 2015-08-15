package com.scu.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;

import javax.swing.JApplet;


public class CommonDateFormat {
	public CommonDateFormat(){
		 
	}
    public static java.sql.Date getNowTime() {
        return new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }
    public static String getTimeAsDate(){
    	String nowTime;
    	Calendar cal  = Calendar.getInstance();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    	nowTime = formatter.format(cal.getTime());
    	return nowTime;
    }
    public static Date Str2Date(String date) {
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            if (date.length()>10) {
                ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            java.util.Date d = ft.parse(date);
            return new java.sql.Date(d.getTime());
        } catch (Exception ex) {
            return new Date(Calendar.getInstance().getTime().getTime());
        }
    }

    public static Date Str2Date1(String date) {
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            if (date.length()>10) {
                ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	//ft = new SimpleDateFormat("yyyy-MM-dd");
            }
            java.util.Date d = ft.parse(date); 
            
            return new java.sql.Date(d.getTime());
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date Str2DateX(String date, String pattern) {
        try {
            SimpleDateFormat ft = new SimpleDateFormat(pattern);
            java.util.Date d = ft.parse(date);
            return new java.sql.Date(d.getTime());
        } catch (Exception ex) {
            return null;
        }
    }

    public static String Date4Seq() {
        return DateToStr(getNowTime(),"yyyyMMddHHmmss");
    }

    public static String DefaultDateTOStr(java.util.Date date)
    {
    	if( date == null ) return "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(date);
    }
    public static String DefaultDateTOStr(java.sql.Date date)
    {
    	if( date == null ) return "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(date);
    }
    public static String DefaultDateTOStr2(java.sql.Date date)
    {
    	if( date == null ) return "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
        return ft.format(date);
    }  
    public static String DefaultDateTOStr2(java.util.Date date)
    {
    	if( date == null ) return "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
        return ft.format(date);
    }        
    public static String DateToStr(java.sql.Date date,String pattern) {
        if( date == null ) return "";
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
        return ft.format(date);
    }

    public static String DateToStr(java.util.Date date,String pattern) {
        if( date == null ) return "";
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
        return ft.format(date);
    }

    public static String getYear(java.sql.Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy");
        return ft.format(date);
    }
    
    public static String getYear(java.util.Date date){
    	SimpleDateFormat ft = new SimpleDateFormat("yyyy");
        return ft.format(date);
    }

    public static String getMonth(java.sql.Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("MM");
        return ft.format(date);
    }

    public static String getDay(java.sql.Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("dd");
        return ft.format(date);
    }

    public static String getHour(java.util.Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("HH");
        return ft.format(date);
    }

    public static String getMinute(java.util.Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("mm");
        return ft.format(date);
    }
    public static String getLastMonth(String dataDate) throws ParseException{
    	if(dataDate==null || dataDate.equals("")){
    		dataDate = DefaultDateTOStr(getNowTime());
    	}
    	String lastMonth = "";
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date1 = format.parse(dataDate);
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date1);
    	cal.add(Calendar.MONDAY,-1);
    	lastMonth = DefaultDateTOStr2(cal.getTime());
    	return lastMonth;
    }
    public static String getNextMonth(String dataDate) throws ParseException{
    	if(dataDate==null || dataDate.equals("")){
    		dataDate = DefaultDateTOStr(getNowTime());
    	}    	
    	String lastMonth = "";
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date1 = format.parse(dataDate);
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date1);
    	cal.add(Calendar.MONDAY,1);
    	lastMonth = DefaultDateTOStr2(cal.getTime());
    	return lastMonth;
    }
    public static String getLastDay(String dataDate) throws ParseException{
    	if(dataDate==null || dataDate.equals("")){
    		dataDate = DefaultDateTOStr(getNowTime());
    	}
    	String lastDay = "";
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date1 = format.parse(dataDate);
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date1);
    	cal.add(Calendar.DATE,-1);
    	lastDay = DefaultDateTOStr(cal.getTime());
    	return lastDay;
    }
    public static String getNextDay(String dataDate) throws ParseException{
    	if(dataDate==null || dataDate.equals("")){
    		dataDate = DefaultDateTOStr(getNowTime());
    	}
    	String nextDay = "";
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date date1 = format.parse(dataDate);
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date1);
    	cal.add(Calendar.DATE,1);
    	nextDay = DefaultDateTOStr(cal.getTime());
    	return nextDay;   	
    }
	public static String DateToString(java.util.Date date) {
		// �������ڸ�ʽ�����󣬲�ʵ��Ϊ��Ҫ������ڸ�ʽ
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datestr = null;
		try {
			// �����ڸ�ʽת��Ϊ�ַ����ڸ�ʽ
			datestr = formatter.format(date);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return datestr;
	}    
	
	/**
	 * 
	 * @������������ȡϵͳ��ǰʱ��,��java.sql.Date��ʽ����
	 * @�����ߣ�haijun.jiang
	 * @����ʱ�䣺2012-8-2 ����05:15:53
	 *  
	 * @return
	 * @����ֵ���ͣ�java.sql.Date ϵͳ��ǰʱ��
	 */
    public static Timestamp getCurTime() {
    	Timestamp tt =new Timestamp(System.currentTimeMillis());
        return tt ;
    }
    
    public static String getYearMonth(String date){
    	String str = date.substring(0, 7);
    	str = str.replace("/", "-");
    	return str;
    }
    
	public static void main(String[] args) {
		//System.out.println(getCurTime());
		System.out.println(getYearMonth("2013/04/29"));
	}
}

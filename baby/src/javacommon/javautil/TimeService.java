package javacommon.javautil;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimeService 
{
   
   /**
    * 功能：获取服务器当前时间
    * 输入参数：
    * 返回值：服务器当前时间
    * 关联函数：
    * 作者：
    * @return Date
    * @roseuid 538F2193021E
    */
   public Date getServiceTime() 
   {
    return null;
   }
   public static String getTimeString(int i) {
	   Date date = new Date();//取时间 
	   Calendar calendar = new GregorianCalendar(); 
	   calendar.setTime(date);
	   calendar.add(Calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
	   date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
	   return TimeService.format(date);
   }
   
   public static String getTimeStringShort(int i) {
	   Date date = new Date();//取时间 
	   Calendar calendar = new GregorianCalendar(); 
	   calendar.setTime(date);
	   calendar.add(Calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
	   date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
	   return TimeService.formatShort(date);
   }
   
   /**
    * 功能：取得格式化时间
    * 输入参数：
    * 返回值：格式化时间
    * @return Date
    * @roseuid 538F21C900F4
    */
   public Date GetFormatTime() 
   {
    return null;
   }
   
   /**
	 * 把时间格式化为"yyyy-MM-dd HH:mm:ss"的形式
	 * @param date
	 * @return
	 */
	public static String format(Date date){		
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);		
	}
	
	/**
	 * 把时间格式化为"yyyy-MM-dd"的形式
	 * @param date
	 * @return
	 */
	public static String formatShort(Date date){		
		return new SimpleDateFormat("yyyy-MM-dd").format(date);		
	}
	
	/**
	 * 把时间格式化为"yyyyMMddHHmmss"的形式
	 * @param date
	 * @return
	 */
	public static String formatOne(Date date){		
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);		
	}
	
	/**
	 * 将字符串转换为java.util.Date
	 * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'
     * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'
     * yy/MM/dd HH:mm:ss pm  如 '2002/1/1 17:55:00 pm'
     * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00'
     * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am'
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str){
		SimpleDateFormat formatter;
		int tempPos = str.indexOf("AD");
		str = str.trim();
		formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
		if (tempPos > -1) {
			str = str.substring(0, tempPos) + "公元" + str.substring(tempPos + "AD".length()); //china
			formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
		}
		tempPos = str.indexOf("-");
		if (tempPos > -1 && (str.indexOf(" ") < 0)) {
			formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
		} else if ((str.indexOf("/") > -1) && (str.indexOf(" ") > -1)) {
			formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		} else if ((str.indexOf("-") > -1) && (str.indexOf(" ") > -1)) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if ((str.indexOf("/") > -1) && (str.indexOf("am") > -1) || (str.indexOf("pm") > -1)) {
			formatter = new SimpleDateFormat("yyyy/MM/dd KK:mm:ss a");
		} else if ((str.indexOf("-") > -1) && (str.indexOf("am") > -1) || (str.indexOf("pm") > -1)) {
			formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
		}
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(str, pos);
		return date;
		
	}
	
	/**
	 * 精确到毫秒
	 * 把时间格式化为"yyyyMMddhhmmssSSS"的形式 
	 * @param date
	 * @return
	 */
	public static String formatMi(Date date){
		return new SimpleDateFormat("yyyyMMddhhmmssSSS").format(date);
	}
}
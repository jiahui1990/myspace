package com.seebaobei;


public class Constants {
	/*********************
	 * 《=《——公用常量——》=》
	 *********************/
	
	/**
	 * APK安装文件名
	 */
	public static final String JVE_APK_NAME = "baobei_apk_install.apk";

	/**
	 * 向服务器发送的参数常量
	 */
	public static final String JVE_URL_PARAMS = "param";
	/**
	 * URL路径常量
	 */
	public static final String JVE_URL = "url";
	
	/**
	 * 存到共享参数里面的标志（是否第一次进入APP标志）
	 */
	public static final String JVE_IS_FIRST_IN = "isFirstIn";
	
	/**
	 * 存到共享参数里面的标志（是否第一次登录）
	 */
	public static final String JVE_IS_FIRST_LOGIN = "isFirstLogin";
	/**
	 * 高清
	 */
	public static final String JVE_GAOQ = "gaoq";
	/**
	 * 标清
	 */
	public static final String JVE_BIAOQ = "biaoq";
	
	/*********************
	 * 《=《——用户访问URL——》=》
	 *********************/

	/**
	 * 微幼看宝贝平台地址
	 */
	//public static final String SERVER_ADDR  = "120.55.126.118";
	public static final String SERVER_ADDR  = "192.168.99.113";
	/**
	 * 微幼看宝贝视频平台地址
	 */
	public static final String SERVER_VIDEO_ADDR  = "www.seeyun.cn";
	/**
	 * 微幼看宝贝平台端口
	 */
	public static final short SERVER_PORT  = 80;
	/**
	 * 统一接口访问路径
	 */
	//public static final String WEB_URL  = "http://120.55.126.118:80/appAction_invoke.action";
	public static final String WEB_URL  = "http://192.168.99.113:8080/appAction_invoke.action";
	
	/********************
	 * 《=《——正则表达式——》=》
	 ********************/
	
	/**
	 * 验证手机用户正则表达式
	 */
	public static final String JVE_REG_PHONE = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";

	
	
	/********************
	 * 《=《——存放在共享参数的信息的文件名——》=》
	 ********************/
	/**
	 * 存放用户登录信息
	 */
	public static final String JVE_FILE_LOGIN = "login";
	/**
	 * 保存系统状态，如记住密码、自动登录、是否第一次运行APP信息
	 */
	public static final String JVE_FILE_SYS_STATUS = "sys_status";


}

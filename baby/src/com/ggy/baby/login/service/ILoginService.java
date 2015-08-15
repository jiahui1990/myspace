package com.ggy.baby.login.service;

import com.ggy.baby.login.model.Users;
import com.ggy.baby.login.pageModel.LoginIn;
import com.ggy.baby.login.pageModel.LoginOut;

/**
 * 登陆service接口
 * @author Chencong
 *
 */
public interface ILoginService {
	/**
	 * web端登陆
	 * @author Chencong
	 * @param in
	 * @return
	 */
	LoginOut login(LoginIn in);
	/**
	 * app登陆
	 * @author Chencong
	 * @param in
	 * @return
	 */
	LoginOut appLogin(LoginIn in);
	/**
	 * 发送手机验证码
	 * @author Chencong
	 * @param in
	 * @return
	 */
	LoginOut sendPhoneCode(LoginIn in);
	/**
	 * 登陆时有其他设备在线时，确认上线。
	 * @author Chencong
	 * @param in
	 * @return
	 */
	LoginOut confirmOnline(LoginIn in);
	/**
	 * 找回密码发送手机验证码
	 * @author Chencong
	 * @param in
	 * @return
	 */
	LoginOut sendForgotPassCode(LoginIn in);
	/**
	 * 验证找回密码的验证码
	 * @author Chencong
	 * @param in
	 * @return
	 */
	LoginOut vertifyForgotPassCode(LoginIn in);
	/**
	 * 修改密码
	 * @author Chencong
	 * @param in
	 * @return
	 */
	LoginOut modifyPassword(LoginIn in);
	
	boolean checkPhoneCodeTimes(Users users);
	
}

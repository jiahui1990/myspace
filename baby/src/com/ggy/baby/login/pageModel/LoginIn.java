package com.ggy.baby.login.pageModel;

import com.ggy.baby.login.model.Users;

/**
 * 登陆In参数
 * @author Chencong
 *
 */
public class LoginIn {
	/**
	 * 用户对象
	 */
	private Users users = new Users();
	/**
	 * mac地址
	 */
	private String mac;
	/**
	 * 手机验证码
	 */
	private String phoneCode;
	/**
	 * 手机号码
	 */
	private String password;
	/**
	 * 新密码
	 */
	private String phone;
	
	private String username;
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
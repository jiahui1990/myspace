package com.ggy.baby.login.model;

import javacommon.base.model.GlobalVariable;

/**
 * 手机验证码
 * @author Chencong
 *
 */
public class PhoneCode {
	/**
	 * 验证码id
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Users userId;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * BaseDao时间查询参数，比如"createTime,'2015-08-11 00:00:00','2015-08-12 00:00:00'"表示查询createTime在给定时间段内的数据
	 */
	private String time;
	/**
	 * 是否验证过 与全局变量关联
	 */
	private GlobalVariable isValidate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public GlobalVariable getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(GlobalVariable isValidate) {
		this.isValidate = isValidate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
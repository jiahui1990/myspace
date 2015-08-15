package com.ggy.baby.login.dao;

import java.util.List;

import com.ggy.baby.login.model.PhoneCode;

/**
 * 电话验证码dao接口
 * @author Chencong
 *
 */
public interface IPhoneCodeDao {
	/**
	 * 保存电话验证码
	 * @author Chencong
	 * @param phoneCode
	 * @return
	 */
	public PhoneCode savePhoneCode(PhoneCode phoneCode);
	/**
	 * 更新电话验证码
	 * @author Chencong
	 * @param phoneCode
	 * @return
	 */
	public Boolean updatePhoneCode(PhoneCode phoneCode);
	/**
	 * 查询电话验证码
	 * @author Chencong
	 * @param phoneCode
	 * @return
	 */
	public PhoneCode queryPhoneCode(PhoneCode phoneCode);
	
	/**
	 * 执行hql语句
	 * @param hql
	 * @return
	 */
	public List executeHql(String hql);
}
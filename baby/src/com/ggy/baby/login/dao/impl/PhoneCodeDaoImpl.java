package com.ggy.baby.login.dao.impl;

import java.util.List;

import com.ggy.baby.login.dao.IPhoneCodeDao;
import com.ggy.baby.login.model.PhoneCode;

import javacommon.base.dao.BaseDao;

/**
 * 电话验证码dao实现
 * @author Chencong
 *
 */
public class PhoneCodeDaoImpl extends BaseDao<PhoneCode> implements IPhoneCodeDao {
	/**
	 * 保存电话验证码
	 * @author Chencong
	 * @param phoneCode
	 * @return
	 */
	@Override
	public PhoneCode savePhoneCode(PhoneCode phoneCode) {
		super.save(phoneCode);
		return phoneCode;
	}
	/**
	 * 更新电话验证码
	 * @author Chencong
	 * @param phoneCode
	 * @return
	 */
	@Override
	public Boolean updatePhoneCode(PhoneCode phoneCode) {
		
		return super.updateObject(phoneCode) > 0;
	}
	/**
	 * 查询电话验证码
	 * @author Chencong
	 * @param phoneCode
	 * @return
	 */
	@Override
	public PhoneCode queryPhoneCode(PhoneCode phoneCode) {
		
		return super.query(phoneCode);
	}
	
	/**
	 * 执行hql语句
	 * @param hql
	 * @return
	 */
	@Override
	public List executeHql(String hql) {
		return super.find(hql);
	}

}

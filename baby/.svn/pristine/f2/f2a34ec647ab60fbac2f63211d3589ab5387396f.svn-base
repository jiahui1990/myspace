package com.ggy.baby.userManage.dao.impl;

import com.ggy.baby.userManage.dao.IUserKinderDao;
import com.ggy.baby.userManage.model.UserKinder;

import javacommon.base.dao.BaseDao;
/**
 * 用户幼儿园dao实现
 * @author Chencong
 *
 */
public class UserKinderDaoImpl extends BaseDao<UserKinder> implements IUserKinderDao {
	/**
	 * 保存用户幼儿园中间表
	 * @author Chencong
	 * @param userKinder
	 * @return
	 */
	@Override
	public UserKinder savUserKinder(UserKinder userKinder) {
		super.save(userKinder);
		return userKinder;
	}
	/**
	 * 删除用户幼儿园中间表
	 * @author Chencong
	 * @param userKinder
	 * @return
	 */
	@Override
	public Boolean deleteUserKinder(UserKinder userKinder) {		
		return super.deleteObject(userKinder)>0;
	}
	/**
	 * 获取用户幼儿园中间表
	 * @author Chencong
	 * @param userKinder
	 * @return
	 */
	@Override
	public UserKinder queryUserKinder(UserKinder userKinder) {
		// TODO Auto-generated method stub
		return super.query(userKinder);
	}

}

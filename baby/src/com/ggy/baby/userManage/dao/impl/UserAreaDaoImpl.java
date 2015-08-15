package com.ggy.baby.userManage.dao.impl;

import com.ggy.baby.login.model.UserRole;
import com.ggy.baby.userManage.dao.IUserAreaDao;
import com.ggy.baby.userManage.model.UserArea;

import javacommon.base.dao.BaseDao;

/**
 * 用户区域dao实现
 * @author Chencong
 *
 */
public class UserAreaDaoImpl extends BaseDao<UserArea> implements IUserAreaDao{
	/**
	 * 保存用户区域
	 * @author Chencong
	 * @param userRole
	 * @return
	 */
	@Override
	public UserArea saveUserRole(UserArea userArea) {
		super.save(userArea);
		return userArea;
	}
	/**
	 * 删除用户区域
	 * @author Chencong
	 * @param userRole
	 * @return
	 */
	@Override
	public Boolean deleteUserArea(UserArea userArea) {
		
		return super.deleteObject(userArea) > 0;
	}
	
	/**
	 * 获取用户区域
	 * @author Chencong
	 * @param userRole
	 * @return
	 */
	@Override
	public UserArea queryUserRole(UserArea userArea) {
		
		return super.query(userArea);
	}
	
}

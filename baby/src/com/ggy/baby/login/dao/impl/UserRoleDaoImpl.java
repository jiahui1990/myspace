package com.ggy.baby.login.dao.impl;

import com.ggy.baby.login.dao.IUserRoleDao;
import com.ggy.baby.login.model.UserRole;

import javacommon.base.dao.BaseDao;

/**
 * 用户角色dao实现
 * @author Chencong
 *
 */
public class UserRoleDaoImpl extends BaseDao<UserRole> implements IUserRoleDao {
	/**
	 * 查询用户角色对象
	 * @author Chencong
	 * @param userRole
	 * @return
	 */
	@Override
	public UserRole queryUserRole(UserRole userRole) {
		
		return super.query(userRole);
	}
	/**
	 * 删除用户角色
	 * @author Chencong
	 * @param userRole
	 * @return
	 */
	@Override
	public Boolean deleteUserRole(UserRole userRole) {
		return super.deleteObject(userRole)>0;
	}
	/**
	 * 保存用户角色
	 * @author Chencong
	 * @param userRole
	 * @return
	 */
	@Override
	public UserRole saveUserRole(UserRole userRole) {
		super.save(userRole);
		return userRole;
	}

}

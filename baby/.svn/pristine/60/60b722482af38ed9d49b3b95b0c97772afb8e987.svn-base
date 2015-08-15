package com.ggy.baby.login.dao.impl;

import com.ggy.baby.login.dao.IUsersDao;
import com.ggy.baby.login.model.Users;

import javacommon.base.dao.BaseDao;

/**
 * 用户dao实现
 * @author Chencong
 *
 */
public class UsersDaoImpl extends BaseDao<Users> implements IUsersDao {
	/**
	 * 查询用户
	 * @author Chencong
	 * @param users
	 * @return
	 */
	@Override
	public Users queryUsers(Users users) {
		
		return super.query(users);
	}
	/**
	 * 保存用户
	 * @author Chencong
	 * @param users
	 * @return
	 */
	@Override
	public Users saveUsers(Users users) {
		super.save(users);
		return users;
	}
	/**
	 * 更新用户对象
	 * @author Chencong
	 * @param users
	 * @return
	 */
	@Override
	public Boolean updateUsers(Users users) {
		return super.updateObject(users) > 0;
	}
	/**
	 * 删除用户对象
	 * @author Chencong
	 * @param users
	 * @return
	 */
	@Override
	public Boolean deleteUsers(Users users) {
		return super.deleteObject(users) > 0;
	}
	/**
	 * 保存所有未保存的用户
	 * @author Chencong
	 */
	@Override
	public void saveAllUsers() {
		String hql = "update Users o set o.isSaved=7 where o.isSaved=6";
		super.executeHql(hql, null);
	}
	
}

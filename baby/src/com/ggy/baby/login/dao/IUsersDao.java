package com.ggy.baby.login.dao;

import com.ggy.baby.login.model.Users;

/**
 * 用户dao接口
 * @author Chencong
 *
 */
public interface IUsersDao {
	/**
	 * 查询用户
	 * @author Chencong
	 * @param users
	 * @return
	 */
	public Users queryUsers(Users users);
	/**
	 * 保存用户
	 * @author Chencong
	 * @param users
	 * @return
	 */
	public Users saveUsers(Users users);
	/**
	 * 更新用户对象
	 * @author Chencong
	 * @param users
	 * @return
	 */
	public Boolean updateUsers(Users users);
	/**
	 * 删除用户对象
	 * @author Chencong
	 * @param users
	 * @return
	 */
	public Boolean deleteUsers(Users users);
	/**
	 * 保存所有未保存的用户
	 * @author Chencong
	 */
	public void saveAllUsers();
}

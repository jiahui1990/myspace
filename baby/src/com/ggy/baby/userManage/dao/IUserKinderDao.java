package com.ggy.baby.userManage.dao;

import com.ggy.baby.userManage.model.UserKinder;

/**
 * 用户幼儿园dao接口
 * @author Chencong
 *
 */
public interface IUserKinderDao {
	/**
	 * 保存用户幼儿园中间表
	 * @author Chencong
	 * @param userKinder
	 * @return
	 */
	public UserKinder savUserKinder(UserKinder userKinder);
	/**
	 * 获取用户幼儿园中间表
	 * @author Chencong
	 * @param userKinder
	 * @return
	 */
	public UserKinder queryUserKinder(UserKinder userKinder);
	/**
	 * 删除用户幼儿园中间表
	 * @author Chencong
	 * @param userKinder
	 * @return
	 */
	public Boolean deleteUserKinder(UserKinder userKinder);
}

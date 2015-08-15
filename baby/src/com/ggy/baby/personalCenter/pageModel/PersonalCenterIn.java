package com.ggy.baby.personalCenter.pageModel;

import com.ggy.baby.login.model.Users;

/**
 * 个人中心In参数
 * @author Chencong
 *
 */
public class PersonalCenterIn {
	private Users users = new Users();

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
}

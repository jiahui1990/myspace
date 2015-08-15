package com.ggy.baby.personalCenter.pageModel;

import com.ggy.baby.login.model.Users;

import javacommon.base.pageModel.JumpInfo;

/**
 * 个人中心Out参数
 * @author Chencong
 *
 */
public class PersonalCenterOut {
	/**
	 * 页面跳转对象
	 */
	private JumpInfo jumpInfo = new JumpInfo();
	/**
	 * 用户对象
	 */
	private Users users = new Users();
	

	public JumpInfo getJumpInfo() {
		return jumpInfo;
	}

	public void setJumpInfo(JumpInfo jumpInfo) {
		this.jumpInfo = jumpInfo;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
}

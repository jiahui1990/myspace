package com.ggy.baby.login.pageModel;

import java.util.ArrayList;
import java.util.List;

import com.ggy.baby.login.model.Users;
import com.ggy.baby.monitorManage.model.ViewMonitor;

import javacommon.base.pageModel.JumpInfo;

/**
 * 登陆Out参数
 * @author Chencong
 *
 */
public class LoginOut {
	/**
	 * 页面跳转对象
	 */
	private JumpInfo jumpInfo = new JumpInfo();
	/**
	 * 用户对象
	 */
	private Users users;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 视频编号信息请求接口账号用户名
	 */
	public String videoAccountUsername;
	/**
	 * 视频编号信息请求接口账号用户密码
	 */
	public String videoAccountPassword;
	/**
	 * 监控视图集合
	 */
	private List<ViewMonitor> viewMonitorList = new ArrayList<ViewMonitor>();

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVideoAccountUsername() {
		return videoAccountUsername;
	}

	public void setVideoAccountUsername(String videoAccountUsername) {
		this.videoAccountUsername = videoAccountUsername;
	}

	public String getVideoAccountPassword() {
		return videoAccountPassword;
	}

	public void setVideoAccountPassword(String videoAccountPassword) {
		this.videoAccountPassword = videoAccountPassword;
	}

	public List<ViewMonitor> getViewMonitorList() {
		return viewMonitorList;
	}

	public void setViewMonitorList(List<ViewMonitor> viewMonitorList) {
		this.viewMonitorList = viewMonitorList;
	}
}

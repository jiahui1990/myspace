package com.ggy.baby.userMonitor.pageModel;

import java.util.ArrayList;
import java.util.List;

import com.ggy.baby.monitorManage.model.Kindergarten;
import com.ggy.baby.monitorManage.model.KindergartenArea;
import com.ggy.baby.monitorManage.model.Monitor;
import com.ggy.baby.monitorManage.model.ViewMonitor;
import com.ggy.baby.userManage.model.UserArea;
import com.ggy.baby.userManage.model.UserKinder;

import javacommon.base.pageModel.JumpInfo;

/**
 * 用户监控Out参数
 * @author Chencong
 *
 */
public class UserMonitorOut {
	/**
	 * 页面跳转对象
	 */
	private JumpInfo jumpInfo = new JumpInfo();
	/**
	 * 幼儿园集合
	 */
	private List<Kindergarten> kindergartenList = new ArrayList<Kindergarten>();
	/**
	 * 幼儿园区域集合
	 */
	private List<KindergartenArea> areaList = new ArrayList<KindergartenArea>();
	/**
	 * 监控集合
	 */
	private List<Monitor> monitorList = new ArrayList<Monitor>();
	/**
	 * 监控视图集合
	 */
	private List<ViewMonitor> viewMonitorList = new ArrayList<ViewMonitor>();
	/**
	 * 用户幼儿园对象
	 */
	private UserKinder userKinder = new UserKinder();
	/**
	 * 用户区域对象
	 */
	private UserArea userArea = new UserArea();

	public List<KindergartenArea> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<KindergartenArea> areaList) {
		this.areaList = areaList;
	}

	public List<Monitor> getMonitorList() {
		return monitorList;
	}

	public void setMonitorList(List<Monitor> monitorList) {
		this.monitorList = monitorList;
	}

	public JumpInfo getJumpInfo() {
		return jumpInfo;
	}

	public void setJumpInfo(JumpInfo jumpInfo) {
		this.jumpInfo = jumpInfo;
	}

	public List<Kindergarten> getKindergartenList() {
		return kindergartenList;
	}

	public void setKindergartenList(List<Kindergarten> kindergartenList) {
		this.kindergartenList = kindergartenList;
	}

	public List<ViewMonitor> getViewMonitorList() {
		return viewMonitorList;
	}

	public void setViewMonitorList(List<ViewMonitor> viewMonitorList) {
		this.viewMonitorList = viewMonitorList;
	}

	public UserKinder getUserKinder() {
		return userKinder;
	}

	public void setUserKinder(UserKinder userKinder) {
		this.userKinder = userKinder;
	}

	public UserArea getUserArea() {
		return userArea;
	}

	public void setUserArea(UserArea userArea) {
		this.userArea = userArea;
	}
}

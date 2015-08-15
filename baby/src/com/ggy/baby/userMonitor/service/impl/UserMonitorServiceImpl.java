package com.ggy.baby.userMonitor.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import javacommon.base.service.impl.BaseServiceImpl;

import com.ggy.baby.monitorManage.dao.IKindergartenAreaDao;
import com.ggy.baby.monitorManage.dao.IKindergartenDao;
import com.ggy.baby.monitorManage.dao.IMonitorDao;
import com.ggy.baby.monitorManage.dao.IViewMonitorDao;
import com.ggy.baby.monitorManage.model.Kindergarten;
import com.ggy.baby.monitorManage.model.KindergartenArea;
import com.ggy.baby.monitorManage.model.Monitor;
import com.ggy.baby.monitorManage.model.ViewMonitor;
import com.ggy.baby.userManage.dao.IUserAreaDao;
import com.ggy.baby.userManage.dao.IUserKinderDao;
import com.ggy.baby.userManage.model.UserArea;
import com.ggy.baby.userManage.model.UserKinder;
import com.ggy.baby.userMonitor.pageModel.UserMonitorIn;
import com.ggy.baby.userMonitor.pageModel.UserMonitorOut;
import com.ggy.baby.userMonitor.service.IUserMonitorService;

/**
 * 用户监控service实现
 * @author Chencong
 *
 */
@Transactional
public class UserMonitorServiceImpl extends BaseServiceImpl implements IUserMonitorService {
	/**
	 * 幼儿园dao
	 */
	private IKindergartenDao kindergartenDao;
	/**
	 * 幼儿园区域dao
	 */
	private IKindergartenAreaDao kindergartenAreaDao;
	/**
	 * 监控视图dao
	 */
	private IViewMonitorDao viewMonitorDao;
	
	private IUserAreaDao userAreaDao;
	
	public IUserAreaDao getUserAreaDao() {
		return userAreaDao;
	}
	public void setUserAreaDao(IUserAreaDao userAreaDao) {
		this.userAreaDao = userAreaDao;
	}
	private IUserKinderDao userKinderDao;
	
	public IUserKinderDao getUserKinderDao() {
		return userKinderDao;
	}
	public void setUserKinderDao(IUserKinderDao userKinderDao) {
		this.userKinderDao = userKinderDao;
	}
	public IKindergartenDao getKindergartenDao() {
		return kindergartenDao;
	}
	public void setKindergartenDao(IKindergartenDao kindergartenDao) {
		this.kindergartenDao = kindergartenDao;
	}
	public IKindergartenAreaDao getKindergartenAreaDao() {
		return kindergartenAreaDao;
	}
	public void setKindergartenAreaDao(IKindergartenAreaDao kindergartenAreaDao) {
		this.kindergartenAreaDao = kindergartenAreaDao;
	}	
	public IViewMonitorDao getViewMonitorDao() {
		return viewMonitorDao;
	}
	public void setViewMonitorDao(IViewMonitorDao viewMonitorDao) {
		this.viewMonitorDao = viewMonitorDao;
	}
	
	/**
	 * 实时监控 管理员
	 * 
	 * 1.查询所有幼儿园信息
	 * 2.跳转页面到实时监控页面
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserMonitorOut actualTimeMonitor(UserMonitorIn in) {
		UserMonitorOut out = new UserMonitorOut();
		
		out.getJumpInfo().setFlag("MSG6004");
		
		List<Kindergarten> kindergartenList = kindergartenDao.findKindergartens(new Kindergarten());
		
		out.setKindergartenList(kindergartenList);
		
		return out;
	}
	/**
	 * 个人监控
	 * 
	 * 1.查询用户角色视图。
	 * 2.查询用户拥有的监控信息。
	 * 3.设置页面跳转到个人监控页面。
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserMonitorOut userMonitor(UserMonitorIn in) {
		UserMonitorOut out = new UserMonitorOut();
		
		UserArea userArea = new UserArea();
		userArea.setUserId(in.getUsers());
		userArea = userAreaDao.queryUserRole(userArea);
		
		ViewMonitor viewMonitor = new ViewMonitor();
		viewMonitor.setAreaId(userArea.getAreaId().getId());
		viewMonitor.setIsSaved("MDGV7");
		
		out.setViewMonitorList(viewMonitorDao.findViewMonitors(viewMonitor));
		out.setUserArea(userArea);
		
		out.getJumpInfo().setFlag("MSG6016");
		return out;
	}
	
	/**
	 * 实时监控 幼儿园管理员管理员
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserMonitorOut actualTimeMonitor_K(UserMonitorIn in) {
		UserMonitorOut out = new UserMonitorOut();
		
		UserKinder userKinder = new UserKinder();
		userKinder.setUserId(in.getUsers());
		
		userKinder = userKinderDao.queryUserKinder(userKinder);
		
		out.setUserKinder(userKinder);
		
		out.getJumpInfo().setFlag("MSG6009");
		
		return out;
	}
	/**
	 * 获取幼儿园所有的监控
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserMonitorOut getMonitor(UserMonitorIn in) {
		UserMonitorOut out = new UserMonitorOut();
		
		KindergartenArea area = new KindergartenArea();
		area.setKindergartenId(in.getKindergarten());
		
		List<KindergartenArea> areaList = kindergartenAreaDao.findKindergartenAreas(area);
		
		out.setAreaList(areaList);
		
		ViewMonitor viewMonitor = new ViewMonitor();
		viewMonitor.setKindergartenId(in.getKindergarten().getId());
		viewMonitor.setIsSaved("MDGV7");
		
		
		List<ViewMonitor> viewMonitorList = viewMonitorDao.findViewMonitors(viewMonitor);
		
		out.setViewMonitorList(viewMonitorList);
		
		return out;
	}
	/**
	 * 获取幼儿园所有区域
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserMonitorOut getAreas(UserMonitorIn in) {
		UserMonitorOut out = new UserMonitorOut();
		
		KindergartenArea area = new KindergartenArea();
		area.setKindergartenId(in.getKindergarten());
		
		List<KindergartenArea> areaList = kindergartenAreaDao.findKindergartenAreas(area);
		
		out.setAreaList(areaList);
		
		return out;
	}
	
}

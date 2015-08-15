package com.ggy.baby.monitorManage.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javacommon.base.model.GlobalVariable;
import javacommon.base.service.impl.BaseServiceImpl;
import javacommon.javautil.TimeService;

import com.ggy.baby.monitorManage.dao.IKindergartenAreaDao;
import com.ggy.baby.monitorManage.dao.IKindergartenDao;
import com.ggy.baby.monitorManage.dao.IMonitorDao;
import com.ggy.baby.monitorManage.dao.IViewMonitorDao;
import com.ggy.baby.monitorManage.model.Kindergarten;
import com.ggy.baby.monitorManage.model.KindergartenArea;
import com.ggy.baby.monitorManage.model.Monitor;
import com.ggy.baby.monitorManage.model.ViewMonitor;
import com.ggy.baby.monitorManage.pageModel.MonitorManageIn;
import com.ggy.baby.monitorManage.pageModel.MonitorManageOut;
import com.ggy.baby.monitorManage.service.IMonitorManageService;
import com.ggy.baby.userManage.dao.IUserKinderDao;
import com.ggy.baby.userManage.model.UserKinder;

/**
 * 监控管理service实现
 * @author Chencong
 *
 */
@Transactional
public class MonitorManageServiceImpl extends BaseServiceImpl implements IMonitorManageService {
	/**
	 * 监控dao
	 */
	private IMonitorDao monitorDao;
	/**
	 * 监控视图dao
	 */
	private IViewMonitorDao viewMonitorDao;
	/**
	 * 幼儿园dao
	 */
	private IKindergartenDao kindergartenDao;
	/**
	 * 幼儿园区域dao
	 */
	private IKindergartenAreaDao kindergartenAreaDao;	
	/**
	 * 用户幼儿园dao
	 */
	private IUserKinderDao userKinderDao;	
	
	public IUserKinderDao getUserKinderDao() {
		return userKinderDao;
	}
	public void setUserKinderDao(IUserKinderDao userKinderDao) {
		this.userKinderDao = userKinderDao;
	}
	public IMonitorDao getMonitorDao() {
		return monitorDao;
	}
	public void setMonitorDao(IMonitorDao monitorDao) {
		this.monitorDao = monitorDao;
	}
	public IViewMonitorDao getViewMonitorDao() {
		return viewMonitorDao;
	}
	public void setViewMonitorDao(IViewMonitorDao viewMonitorDao) {
		this.viewMonitorDao = viewMonitorDao;
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
	/**
	 * 初始化监控管理页面
	 * 
	 * 1.查询所有幼儿园。
	 * 2.设置跳转到监控管理页面。
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut initMonitorManage(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		ViewMonitor viewMonitor = new ViewMonitor();
		if(in.getPage()!=null&&in.getPage()>0){
			viewMonitor.setPage(in.getPage());
		}else{
			viewMonitor.setPage(1);
		}
		
		viewMonitor.setRows(10);
		
		viewMonitor.setIsSaved("MDGV7");
		
		viewMonitor.setSearch_content(in.getSearch());
		
		List<ViewMonitor> viewMonitorList = viewMonitorDao.findViewMonitors(viewMonitor);
		
		Integer total = viewMonitorDao.countViewMonitors(viewMonitor);
		
		Integer pages = total/10;
		if(total%10!=0){
			pages++;
		}
		
		out.setPages(pages);
		
		List<Kindergarten> kindergartenList = kindergartenDao.findKindergartens(new Kindergarten());
		
		out.setKindergartenList(kindergartenList);
		
		out.setViewMonitorList(viewMonitorList);
		
		out.getJumpInfo().setFlag("MSG6005");
		
		return out;
	}
	/**
	 * 根据幼儿园获取所有区域
	 * 
	 * 1.根据幼儿园获取所有区域
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut getArea(MonitorManageIn in) {
		
		return null;
	}
	/**
	 * 查询监控
	 * 
	 * 1.根据条件查询监控集合
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut findMonitor(MonitorManageIn in) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 添加监控
	 * 
	 * 1.检测区域id是否为空，如果为空进行区域添加。
	 * 2.保存监控。
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut addMonitor(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		String nowTime = TimeService.format(new Date());
		
		Kindergarten kindergarten = in.getKindergarten();
		if(kindergarten.getId()<0){
			kindergarten.setId(null);
			kindergarten.setCreateTime(nowTime);
			
			kindergarten = kindergartenDao.saveKindergarten(kindergarten);
		}
		
		KindergartenArea pArea = in.getArea();
		if(pArea.getId()<0){
			pArea.setId(null);
			pArea.setCreateTime(nowTime);
			pArea.setKindergartenId(kindergarten);
			pArea.setPid(0l);
			
			pArea = kindergartenAreaDao.saveKindergartenArea(pArea);
		}
		
		KindergartenArea cArea = in.getCarea();
		
		if(cArea.getId()<0){
			cArea.setId(null);
			cArea.setCreateTime(nowTime);
			cArea.setKindergartenId(kindergarten);
			cArea.setPid(pArea.getId());
			
			cArea = kindergartenAreaDao.saveKindergartenArea(cArea);
		}		
		
		Monitor monitor = in.getMonitor();
		monitor.setAreaId(cArea);
		monitor.setCreateTime(nowTime);
		monitor.setIsSaved(getGlobalvariable(new GlobalVariable("MDGV6")));
		
		monitor = monitorDao.saveMonitor(monitor);
		
		out.getJumpInfo().setFlag("MSG0002");
		
		return out;
	}
	/**
	 * 监控信息 幼儿园管理员页面
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut monitorInfo(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		UserKinder userKinder = new UserKinder();
		userKinder.setUserId(in.getUsers());
		userKinder = userKinderDao.queryUserKinder(userKinder);
		
		
		ViewMonitor viewMonitor = new ViewMonitor();
		viewMonitor.setKindergartenId(userKinder.getKindergartenId().getId());

		viewMonitor.setIsSaved("MDGV7");
		if(in.getPage()!=null&&in.getPage()>0){
			viewMonitor.setPage(in.getPage());
		}else{
			viewMonitor.setPage(1);
		}
		
		viewMonitor.setRows(10);
		
		viewMonitor.setSearch_content(in.getSearch());
		
		List<ViewMonitor> viewMonitorList = viewMonitorDao.findViewMonitors(viewMonitor);
		
		Integer total = viewMonitorDao.countViewMonitors(viewMonitor);
		
		Integer pages = total/10;
		if(total%10!=0){
			pages++;
		}
		
		out.setPages(pages);
		
		out.setViewMonitorList(viewMonitorList);
		
		out.getJumpInfo().setFlag("MSG6011");
		
		return out;
	}
	/**
	 * 进入添加监控页面
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut initAddMonitor(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		ViewMonitor viewMonitor = new ViewMonitor();
		if(in.getPage()!=null&&in.getPage()>0){
			viewMonitor.setPage(in.getPage());
		}else{
			viewMonitor.setPage(1);
		}
		viewMonitor.setRows(10);
		viewMonitor.setIsSaved("MDGV6");
		List<ViewMonitor> viewMonitorList = viewMonitorDao.findViewMonitors(viewMonitor);
		
		out.setViewMonitorList(viewMonitorList);
		
		List<Kindergarten> kindergartenList = kindergartenDao.findKindergartens(new Kindergarten());
		
		Integer total = viewMonitorDao.countViewMonitors(viewMonitor);
		
		Integer pages = total/10;
		if(total%10!=0){
			pages++;
		}
		
		out.setPages(pages);
		out.setKindergartenList(kindergartenList);
				
		out.getJumpInfo().setFlag("MSG6006");
		
		return out;
	}
	/**
	 * 获取幼儿园1级区域
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut getOneLebelArea(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		KindergartenArea area = new KindergartenArea();
		area.setKindergartenId(in.getKindergarten());
		area.setPid(0l);
		
		List<KindergartenArea> areaList = kindergartenAreaDao.findKindergartenAreas(area);
		
		out.setKindergartenAreaList(areaList);
		
		return out;
	}
	/**
	 * 获取幼儿园2级区域
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut getTwoLebelArea(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		KindergartenArea area = new KindergartenArea();
		area.setPid(in.getArea().getId());
		
		List<KindergartenArea> areaList = kindergartenAreaDao.findKindergartenAreas(area);
		
		out.setKindergartenAreaList(areaList);
		
		return out;
	}
	/**
	 * 删除监控
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut deleteMonitor(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		if(in.getMonitor()==null||in.getMonitor().getId()==null){
			out.getJumpInfo().setFlag("MSG4001");
			return out;
		}
		
		monitorDao.deleteMonitor(in.getMonitor());
		
		out.getJumpInfo().setFlag("MSG0007");
		return out;
	}
	/**
	 * 更新监控
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut updateMonitor(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		if(in.getMonitor()==null||in.getMonitor().getId()==null){
			out.getJumpInfo().setFlag("MSG4001");
			return out;
		}
		
		monitorDao.updateMonitor(in.getMonitor());
		
		out.getJumpInfo().setFlag("MSG0008");
		return out;
	}
	/**
	 * 导出监控
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut exportMonitor(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		ViewMonitor viewMonitor = new ViewMonitor();
		if(in.getPage()!=null&&in.getPage()>0){
			viewMonitor.setPage(in.getPage());
		}else{
			viewMonitor.setPage(1);
		}
		viewMonitor.setRows(10);
		viewMonitor.setSearch_content(in.getSearch());
		List<ViewMonitor> viewMonitorList = viewMonitorDao.findViewMonitors(viewMonitor);
		
		out.setViewMonitorList(viewMonitorList);
		
		return out;
	}
	/**
	 * 批量删除监控
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut batchDeleteMonitor(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		if(StringUtils.isEmpty(in.getIds())){
			out.getJumpInfo().setFlag("MSG4001");
			return out;
		}
		
		String[] idArray = in.getIds().split(",");
		
		for (int i = 0; i < idArray.length; i++) {
			Monitor monitor = new Monitor();
			monitor.setId(Long.valueOf(idArray[i]));
			monitorDao.deleteMonitor(monitor);
		}
		
		out.getJumpInfo().setFlag("MSG0007");
		return out;
	}
	/**
	 * 保存所有未保存的监控
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public MonitorManageOut saveMonitors(MonitorManageIn in) {
		MonitorManageOut out = new MonitorManageOut();
		
		monitorDao.saveAllMonitor();
		
		out.getJumpInfo().setFlag("MSG0012");
		return out;
	}	
	
}

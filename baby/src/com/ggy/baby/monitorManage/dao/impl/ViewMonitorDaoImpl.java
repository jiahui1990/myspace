package com.ggy.baby.monitorManage.dao.impl;

import java.util.List;

import com.ggy.baby.monitorManage.dao.IViewMonitorDao;
import com.ggy.baby.monitorManage.model.ViewMonitor;

import javacommon.base.dao.BaseDao;

/**
 * 监控视图dao实现
 * @author Chencong
 *
 */
public class ViewMonitorDaoImpl extends BaseDao<ViewMonitor> implements IViewMonitorDao {
	/**
	 * 根据条件查询所有监控视图
	 * @author Chencong
	 * @param viewMonitor
	 * @return
	 */
	@Override
	public List<ViewMonitor> findViewMonitors(ViewMonitor viewMonitor) {
		
		return super.globalParamsAndLikeSearchContent(viewMonitor);
	}
	/**
	 * 根据条件查询所有监控视图条数
	 * @author Chencong
	 * @param viewMonitor
	 * @return
	 */
	@Override
	public Integer countViewMonitors(ViewMonitor viewMonitor) {
		
		return super.countGlobalParamsAndLikeSearchContent(viewMonitor).intValue();
	}

}

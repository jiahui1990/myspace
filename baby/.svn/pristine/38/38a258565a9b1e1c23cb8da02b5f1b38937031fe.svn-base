package com.ggy.baby.monitorManage.dao.impl;

import com.ggy.baby.monitorManage.dao.IMonitorDao;
import com.ggy.baby.monitorManage.model.Monitor;

import javacommon.base.dao.BaseDao;

/**
 * 监控dao实现
 * @author Chencong
 *
 */
public class MonitorDaoImpl extends BaseDao<Monitor> implements IMonitorDao {
	/**
	 * 保存监控
	 * @author Chencong
	 * @param monitor
	 * @return
	 */
	@Override
	public Monitor saveMonitor(Monitor monitor) {
		super.save(monitor);
		return monitor;
	}

	@Override
	public Boolean deleteMonitor(Monitor monitor) {
		
		return super.deleteObject(monitor) > 0;
	}
	/**
	 * 更新监控
	 * @author Chencong
	 * @param monitor
	 * @return
	 */
	@Override
	public Boolean updateMonitor(Monitor monitor) {
		// TODO Auto-generated method stub
		return super.updateObject(monitor)>0;
	}

	@Override
	public void saveAllMonitor() {
		String hql = "update Monitor o set o.isSaved=7 where o.isSaved=6";
		
		super.executeHql(hql, null);
		
	}

}

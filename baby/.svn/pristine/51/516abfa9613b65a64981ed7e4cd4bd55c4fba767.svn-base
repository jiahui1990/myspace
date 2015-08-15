package com.ggy.baby.userManage.dao.impl;

import java.util.List;

import com.ggy.baby.userManage.dao.IViewUserAreaDao;
import com.ggy.baby.userManage.model.ViewUserArea;

import javacommon.base.dao.BaseDao;

public class ViewUserAreaDaoImpl extends BaseDao<ViewUserArea> implements IViewUserAreaDao {
	/**
	 * 查询用户区域视图
	 * @author Chencong
	 * @param viewUserArea
	 * @return
	 */
	@Override
	public ViewUserArea queryViewUserArea(ViewUserArea viewUserArea) {
		
		return super.query(viewUserArea);
	}
	/**
	 * 根据条件查询所有的用户区域视图
	 * @author Chencong
	 * @param viewUserArea
	 * @return
	 */
	@Override
	public List<ViewUserArea> findViewUserAreas(ViewUserArea viewUserArea) {		
		return super.globalParamsAndLikeSearchContent(viewUserArea);
	}
	/**
	 * 查询用户区域的条数
	 * @author Chencong
	 * @param viewUserArea
	 * @return
	 */
	@Override
	public Integer countViewUserAreas(ViewUserArea viewUserArea) {
		
		return super.countGlobalParamsAndLikeSearchContent(viewUserArea).intValue();
	}
	
}

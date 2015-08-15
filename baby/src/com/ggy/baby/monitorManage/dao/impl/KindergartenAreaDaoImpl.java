package com.ggy.baby.monitorManage.dao.impl;

import java.util.List;

import com.ggy.baby.monitorManage.dao.IKindergartenAreaDao;
import com.ggy.baby.monitorManage.model.KindergartenArea;

import javacommon.base.dao.BaseDao;

public class KindergartenAreaDaoImpl extends BaseDao<KindergartenArea> implements IKindergartenAreaDao {
	/**
	 * 保存幼儿园区域
	 * @author Chencong
	 * @param kindergartenArea
	 * @return
	 */
	@Override
	public KindergartenArea saveKindergartenArea(
			KindergartenArea kindergartenArea) {
		super.save(kindergartenArea);
		return kindergartenArea;
	}
	/**
	 * 根据幼儿园查询所有区域
	 * @author Chencong
	 * @param kindergartenArea
	 * @return
	 */
	@Override
	public List<KindergartenArea> findKindergartenAreas(
			KindergartenArea kindergartenArea) {
		
		return super.queryList(kindergartenArea);
	}

}

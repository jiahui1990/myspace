package com.ggy.baby.monitorManage.dao.impl;

import java.util.List;

import com.ggy.baby.monitorManage.dao.IKindergartenDao;
import com.ggy.baby.monitorManage.model.Kindergarten;

import javacommon.base.dao.BaseDao;

/**
 * 幼儿园dao实现
 * @author Chencong
 *
 */
public class KindergartenDaoImpl extends BaseDao<Kindergarten> implements IKindergartenDao {
	/**
	 * 保存幼儿园
	 * @author Chencong
	 * @param kindergarten
	 * @return
	 */
	@Override
	public Kindergarten saveKindergarten(Kindergarten kindergarten) {
		super.save(kindergarten);
		return kindergarten;
	}
	/**
	 * 获取所有幼儿园集合
	 * @author Chencong
	 * @param kindergarten
	 * @return
	 */
	@Override
	public List<Kindergarten> findKindergartens(Kindergarten kindergarten) {
		
		return super.globalQueryList(kindergarten);
	}
	
}

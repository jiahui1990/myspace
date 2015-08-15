package com.ggy.baby.monitorManage.dao;

import java.util.List;

import com.ggy.baby.monitorManage.model.KindergartenArea;

/**
 * 幼儿园区域dao接口
 * @author Chencong
 *
 */
public interface IKindergartenAreaDao {
	/**
	 * 保存幼儿园区域
	 * @author Chencong
	 * @param kindergartenArea
	 * @return
	 */
	public KindergartenArea saveKindergartenArea(KindergartenArea kindergartenArea);
	/**
	 * 根据幼儿园查询所有区域
	 * @author Chencong
	 * @param kindergartenArea
	 * @return
	 */
	public List<KindergartenArea> findKindergartenAreas(KindergartenArea kindergartenArea);
}

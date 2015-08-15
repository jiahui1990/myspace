package com.ggy.baby.monitorManage.dao;

import java.util.List;

import com.ggy.baby.monitorManage.model.Kindergarten;

/**
 * 幼儿园dao接口
 * @author Chencong
 *
 */
public interface IKindergartenDao {
	/**
	 * 保存幼儿园
	 * @author Chencong
	 * @param kindergarten
	 * @return
	 */
	public Kindergarten saveKindergarten(Kindergarten kindergarten);
	/**
	 * 获取所有幼儿园集合
	 * @author Chencong
	 * @param kindergarten
	 * @return
	 */
	public List<Kindergarten> findKindergartens(Kindergarten kindergarten);
}

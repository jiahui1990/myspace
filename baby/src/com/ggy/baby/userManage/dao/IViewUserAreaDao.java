package com.ggy.baby.userManage.dao;

import java.util.List;

import com.ggy.baby.userManage.model.ViewUserArea;

/**
 * 用户区域视图dao接口
 * @author Chencong
 *
 */
public interface IViewUserAreaDao {
	/**
	 * 查询用户区域视图
	 * @author Chencong
	 * @param viewUserArea
	 * @return
	 */
	public ViewUserArea queryViewUserArea(ViewUserArea viewUserArea);
	/**
	 * 根据条件查询所有的用户区域视图
	 * @author Chencong
	 * @param viewUserArea
	 * @return
	 */
	public List<ViewUserArea> findViewUserAreas(ViewUserArea viewUserArea);
	/**
	 * 查询用户区域的条数
	 * @author Chencong
	 * @param viewUserArea
	 * @return
	 */
	public Integer countViewUserAreas(ViewUserArea viewUserArea);
}

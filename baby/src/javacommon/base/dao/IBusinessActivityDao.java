package javacommon.base.dao;

import java.util.List;

import javacommon.base.model.BusinessActivity;

public interface IBusinessActivityDao {
	/**
	 * 根据编号获取业务字典对象
	 * @param businessActivity
	 * @return BusinessActivity业务字典对象
	 */
	public BusinessActivity getBusinessActivity(BusinessActivity businessActivity);
	/**
	 * 根据id获取业务字典对象
	 * @param businessActivity
	 * @return BusinessActivity业务字典对象
	 */
	public BusinessActivity getBusinessActivityByid(BusinessActivity businessActivity);
	/**
	 *查詢所有後臺菜單 
	 *@param businessActivity
	 *@return list<BusinessActivity>
	 */
	public List<BusinessActivity> getAllBusinessActivity(BusinessActivity businessActivity);
}

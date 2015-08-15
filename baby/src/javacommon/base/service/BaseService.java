package javacommon.base.service;

import javacommon.base.model.BusinessActivity;
import javacommon.base.model.GlobalVariable;




/**
 * 
 * @Description: TODO(公用BaseService接口)
 * @author qinchuan
 * @version V1.0
 * @date 2014-3-13 下午4:50:42
 */
public interface BaseService {
	
	
	
	/**
	 * 获取全局变量对象
	 * @param globalVariable
	 * @return
	 */
	public GlobalVariable getGlobalvariable(GlobalVariable globalVariable);
	/**
	 * 根据id获取全局变量对象
	 * @param globalVariable
	 * @return
	 */
	public GlobalVariable getGlobalvariableByid(GlobalVariable globalVariable);
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
	
	
}

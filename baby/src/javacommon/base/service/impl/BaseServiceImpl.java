package javacommon.base.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javacommon.base.dao.IBusinessActivityDao;
import javacommon.base.dao.IGlobalvariableDao;
import javacommon.base.dao.IMsgtipDao;
import javacommon.base.model.BusinessActivity;
import javacommon.base.model.GlobalVariable;
import javacommon.base.service.BaseService;

public class BaseServiceImpl implements BaseService{
	  
		/**
		 * 提示信息Dao
		 */
		private IMsgtipDao msgtipDao;
		
		public void setMsgtipDao(IMsgtipDao msgtipDao) {
			this.msgtipDao = msgtipDao;
			setMsgtipsDao(msgtipDao);
		}
		public IMsgtipDao getMsgtipDao() {
			return msgtipDao;
		}
		/**
		 * 静态成员不需要实例化调用
		 */
		private static IMsgtipDao msgtipsDao;
		

	
		public static IMsgtipDao getMsgtipsDao() {
			return msgtipsDao;
		}
	
		public static void setMsgtipsDao(IMsgtipDao msgtipsDao) {
			BaseServiceImpl.msgtipsDao = msgtipsDao;
		}
		
		
		/**
		 * 变量常量Dao
		 */
		private IGlobalvariableDao globalvariableDao;
		/**
		 * 业务字典表Dao
		 */
		private IBusinessActivityDao businessActivityDao;
		
		
		public IBusinessActivityDao getBusinessActivityDao() {
			return businessActivityDao;
		}
		public void setBusinessActivityDao(IBusinessActivityDao businessActivityDao) {
			this.businessActivityDao = businessActivityDao;
		}
		
		public IGlobalvariableDao getGlobalvariableDao() {
			return globalvariableDao;
		}
		public void setGlobalvariableDao(IGlobalvariableDao globalvariableDao) {
			this.globalvariableDao = globalvariableDao;
		}
		/**
		 * 获取全局变量对象
		 * @param globalVariable
		 * @return
		 */
		public GlobalVariable getGlobalvariable(GlobalVariable globalVariable){
			return globalvariableDao.getGlobalvariable(globalVariable);
		}
		
		/**
		 * 根据id获取全局变量对象
		 * @param globalVariable
		 * @return
		 */
		public GlobalVariable getGlobalvariableByid(GlobalVariable globalVariable){
			return globalvariableDao.getGlobalvariableByid(globalVariable);
		}
		@Override
		public BusinessActivity getBusinessActivity(
				BusinessActivity businessActivity) {
			return businessActivityDao.getBusinessActivity(businessActivity);
		}
		@Override
		public BusinessActivity getBusinessActivityByid(
				BusinessActivity businessActivity) {
			return businessActivityDao.getBusinessActivityByid(businessActivity);
		}
		
}

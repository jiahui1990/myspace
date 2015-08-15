package javacommon.base.dao.impl;

import java.util.List;

import javacommon.base.dao.BaseDao;
import javacommon.base.dao.IBusinessActivityDao;
import javacommon.base.model.BusinessActivity;

import org.apache.commons.lang3.StringUtils;

public class BusinessActivityDaoImpl extends BaseDao<BusinessActivity> implements IBusinessActivityDao {

	@Override
	public BusinessActivity getBusinessActivity(BusinessActivity businessActivity) {
		String hql ="FROM BusinessActivity o WHERE o.numbers=:numbers";
		if (StringUtils.isEmpty(hql)) {
			return null;
		}
		// 取对象
		params.clear();
		params.put("numbers", businessActivity.getNumbers());
		return super.get(hql, params);
	}

	@Override
	public BusinessActivity getBusinessActivityByid(BusinessActivity businessActivity) {
		String hql = "FROM BusinessActivity o WHERE o.id=:id";
		params.clear();
		params.put("id", businessActivity.getId());
		return super.get(hql, params);
	}

	@Override
	public List<BusinessActivity> getAllBusinessActivity(
			BusinessActivity businessActivity) {
		// TODO Auto-generated method stub
		return super.queryListIncludePaging(businessActivity);
	}

}

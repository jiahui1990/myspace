package javacommon.base.dao.impl;

import java.util.List;
import javacommon.base.dao.BaseDao;
import javacommon.base.dao.IGlobalvariableDao;
import javacommon.base.model.GlobalVariable;

public class GlobalvariableDaoImpl extends BaseDao<GlobalVariable> implements
		IGlobalvariableDao {

	@Override
	public GlobalVariable getGlobalvariable(GlobalVariable globalVariable) {
		// 取hql语句
		String hql ="FROM GlobalVariable o WHERE o.numbers=:numbers";
		params.clear();
		params.put("numbers", globalVariable.getNumbers());
		return super.get(hql, params);
	}

	@Override
	public GlobalVariable getGlobalvariableByid(GlobalVariable globalVariable) {
		// TODO Auto-generated method stubgetGlobalvariableByid
		// 取hql语句
		String hql = "FROM GlobalVariable o WHERE o.id=:id";
		params.clear();
		params.put("id", globalVariable.getId());
		return super.get(hql, params);
	}

	/**
	 * 增加全局變量
	 * 
	 * @param GlobalVariable
	 * @return
	 * */
	public GlobalVariable saveGlobalVariable(GlobalVariable globalVariable) {
		super.save(globalVariable);
		return globalVariable;
	}

	@Override
	public List<GlobalVariable> getAllGlobalVariable(
			GlobalVariable globalVariable) {
		return super.globalQueryList(globalVariable);
	}

	@Override
	public Long getGlobalvariableCount(GlobalVariable globalVariable) {
		return count(globalVariable);
	}

	@Override
	public GlobalVariable getGlobalVariableById(GlobalVariable globalVariable) {
		// TODO Auto-generated method stub
		return super.query(globalVariable);
	}

	@Override
	public void updeGlobalvariable(GlobalVariable globalVariable) {
		super.update(globalVariable);
	}

	@Override
	public void deleteGlobalvariable(GlobalVariable globalVariable) {
		// TODO Auto-generated method stub
		super.delete(globalVariable);
	}
}

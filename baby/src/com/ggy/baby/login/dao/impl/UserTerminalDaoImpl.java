package com.ggy.baby.login.dao.impl;

import java.util.List;

import com.ggy.baby.login.dao.IUserTerminalDao;
import com.ggy.baby.login.model.UserTerminal;

import javacommon.base.dao.BaseDao;

/**
 * 用户登陆终端dao实现
 * @author Chencong
 *
 */
public class UserTerminalDaoImpl extends BaseDao<UserTerminal> implements IUserTerminalDao {
	/**
	 * 查询用户设备集合
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	@Override
	public List<UserTerminal> findUserTerminals(UserTerminal userTerminal) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 删除最早的一个设备
	 * @author Chencong
	 * @return
	 */
	@Override
	public Boolean deleteOldestUserTerminal(UserTerminal userTerminal) {
		
		userTerminal.setOrderString("createTime asc");
		
		userTerminal = super.query(userTerminal);
		if(userTerminal==null){
			return false;
		}
		
		return super.deleteObject(userTerminal) > 0;
	}
	/**
	 * 保存用户设备
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	@Override
	public UserTerminal saveUserTerminal(UserTerminal userTerminal) {
		super.save(userTerminal);
		return userTerminal;
	}
	/**
	 * 查询用户设备
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	@Override
	public UserTerminal queryUserTerminal(UserTerminal userTerminal) {		
		return super.query(userTerminal);
	}
	/**
	 * 查询用户设备集合个数
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	@Override
	public Long counttUserTerminals(UserTerminal userTerminal) {		
		return super.count(userTerminal);
	}
	/**
	 * 删除用户设备
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	@Override
	public Boolean deleteUserTerminal(UserTerminal userTerminal) {
		// TODO Auto-generated method stub
		return super.deleteObject(userTerminal)>0;
	}
	
}

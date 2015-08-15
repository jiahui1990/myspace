package com.ggy.baby.login.dao;

import java.util.List;

import com.ggy.baby.login.model.UserTerminal;

/**
 * 用户登陆终端dao接口
 * @author Chencong
 *
 */
public interface IUserTerminalDao {
	/**
	 * 查询用户设备集合
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	public List<UserTerminal> findUserTerminals(UserTerminal userTerminal);
	/**
	 * 查询用户设备集合个数
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	public Long counttUserTerminals(UserTerminal userTerminal);
	
	/**
	 * 删除最早的一个设备
	 * @author Chencong
	 * @return
	 */
	public Boolean deleteOldestUserTerminal(UserTerminal userTerminal);
	/**
	 * 保存用户设备
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	public UserTerminal saveUserTerminal(UserTerminal userTerminal);
	/**
	 * 查询用户设备
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	public UserTerminal queryUserTerminal(UserTerminal userTerminal);
	/**
	 * 删除用户设备
	 * @author Chencong
	 * @param userTerminal
	 * @return
	 */
	public Boolean deleteUserTerminal(UserTerminal userTerminal);
}

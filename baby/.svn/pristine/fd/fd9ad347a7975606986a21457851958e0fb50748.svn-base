package com.ggy.baby.personalCenter.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javacommon.base.service.impl.BaseServiceImpl;
import javacommon.javautil.MD5Utils;

import com.ggy.baby.login.dao.IUserRoleDao;
import com.ggy.baby.login.dao.IUsersDao;
import com.ggy.baby.login.model.UserRole;
import com.ggy.baby.login.model.Users;
import com.ggy.baby.personalCenter.pageModel.PersonalCenterIn;
import com.ggy.baby.personalCenter.pageModel.PersonalCenterOut;
import com.ggy.baby.personalCenter.service.IPersonalCenterService;

/**
 * 个人中心service实现
 * @author Chencong
 *
 */
@Transactional
public class PersonalCenterServiceImpl extends BaseServiceImpl implements IPersonalCenterService {
	/**
	 * 用户角色dao
	 */
	private IUserRoleDao userRoleDao;
	/**
	 * 用户dao
	 */
	private IUsersDao usersDao;
	
	public IUsersDao getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}
	public IUserRoleDao getUserRoleDao() {
		return userRoleDao;
	}
	public void setUserRoleDao(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	/**
	 * 初始化个人中心
	 * 
	 * 跳转到个人中心页面
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public PersonalCenterOut initPersonalCenter(PersonalCenterIn in) {
		PersonalCenterOut out = new PersonalCenterOut();
		
		UserRole userRole = new UserRole();
		userRole.setUserid(in.getUsers());
		
		userRole = userRoleDao.queryUserRole(userRole);
		
		out.setUsers(usersDao.queryUsers(in.getUsers()));
		
		if(userRole==null){
			out.getJumpInfo().setFlag("MSG4001");
			return out;
		}
		
		switch (userRole.getRoleid().getRoleNumber()) {
		case "PROLE1":
			out.getJumpInfo().setFlag("MSG6012");
			break;
		case "PROLE2":

			out.getJumpInfo().setFlag("MSG6013");
			break;
		case "PROLE3":

			out.getJumpInfo().setFlag("MSG6014");
			break;
		default:
			break;
		}
		
		return out;
	}
	/**
	 * 更新个人信息
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public PersonalCenterOut updateUsers(PersonalCenterIn in) {
		PersonalCenterOut out = new PersonalCenterOut();
		
		Users users = in.getUsers();
		
		if(StringUtils.isNotEmpty(users.getPassword())){
			users.setPassword(MD5Utils.getMD5String(users.getPassword()));			
		}
		if(StringUtils.isEmpty(users.getTelphone())){
			users.setTelphone(null);
		}
		
		usersDao.updateUsers(in.getUsers());
		
		out.getJumpInfo().setFlag("MSG0004");
		
		return out;
	}
	/**
	 * 获取用户信息(手机接口)
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public PersonalCenterOut getUserInfo(PersonalCenterIn in) {
		PersonalCenterOut out = new PersonalCenterOut();
		
		Users dbUsers = usersDao.queryUsers(in.getUsers());
		
		if(dbUsers==null){
			out.getJumpInfo().setFlag("MSG4002");
			return out;
		}
		out.setUsers(dbUsers);
		out.getJumpInfo().setFlag("MSG0000");
		
		return out;
	}
	/**
	 * 更新用户手机号码
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public PersonalCenterOut updateUserPhone(PersonalCenterIn in) {
		PersonalCenterOut out = new PersonalCenterOut();
		
		Users users = in.getUsers();
		
		usersDao.updateUsers(in.getUsers());
		
		out.getJumpInfo().setFlag("MSG0010");
		
		return out;
	}
	
}

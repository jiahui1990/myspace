package com.ggy.baby.userManage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import oracle.net.aso.MD5;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javacommon.base.model.GlobalVariable;
import javacommon.base.service.impl.BaseServiceImpl;
import javacommon.javautil.MD5Utils;
import javacommon.javautil.TimeService;

import com.ggy.baby.login.dao.IUserRoleDao;
import com.ggy.baby.login.dao.IUserTerminalDao;
import com.ggy.baby.login.dao.IUsersDao;
import com.ggy.baby.login.model.UserRole;
import com.ggy.baby.login.model.UserTerminal;
import com.ggy.baby.login.model.Users;
import com.ggy.baby.monitorManage.dao.IKindergartenDao;
import com.ggy.baby.monitorManage.model.Kindergarten;
import com.ggy.baby.userManage.dao.IUserAreaDao;
import com.ggy.baby.userManage.dao.IUserKinderDao;
import com.ggy.baby.userManage.dao.IViewUserAreaDao;
import com.ggy.baby.userManage.model.UserArea;
import com.ggy.baby.userManage.model.UserKinder;
import com.ggy.baby.userManage.model.ViewUserArea;
import com.ggy.baby.userManage.pageModel.UserManageIn;
import com.ggy.baby.userManage.pageModel.UserManageOut;
import com.ggy.baby.userManage.service.IUserManageService;

/**
 * 用户管理service实现
 * @author Chencong
 *
 */
@Transactional
public class UserManageServiceImpl extends BaseServiceImpl implements IUserManageService {
	/**
	 * 用户dao
	 */
	private IUsersDao usersDao;
	/**
	 * 用户区域视图dao
	 */
	private IViewUserAreaDao viewUserAreaDao;
	/**
	 * 用户区域dao
	 */
	private IUserAreaDao userAreaDao;
	/**
	 * 用户角色dao
	 */
	private IUserRoleDao userRoleDao;	
	/**
	 * 幼儿园dao
	 */
	private IKindergartenDao kindergartenDao;
	/**
	 * 用户幼儿园dao
	 */
	private IUserKinderDao userKinderDao;
	/**
	 * 用户设备dao
	 */
	private IUserTerminalDao userTerminalDao;
	
	public IUserTerminalDao getUserTerminalDao() {
		return userTerminalDao;
	}

	public void setUserTerminalDao(IUserTerminalDao userTerminalDao) {
		this.userTerminalDao = userTerminalDao;
	}

	public IUserKinderDao getUserKinderDao() {
		return userKinderDao;
	}

	public void setUserKinderDao(IUserKinderDao userKinderDao) {
		this.userKinderDao = userKinderDao;
	}

	public IKindergartenDao getKindergartenDao() {
		return kindergartenDao;
	}

	public void setKindergartenDao(IKindergartenDao kindergartenDao) {
		this.kindergartenDao = kindergartenDao;
	}

	public IUsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public IViewUserAreaDao getViewUserAreaDao() {
		return viewUserAreaDao;
	}

	public void setViewUserAreaDao(IViewUserAreaDao viewUserAreaDao) {
		this.viewUserAreaDao = viewUserAreaDao;
	}	

	public IUserAreaDao getUserAreaDao() {
		return userAreaDao;
	}

	public void setUserAreaDao(IUserAreaDao userAreaDao) {
		this.userAreaDao = userAreaDao;
	}

	public IUserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	/**
	 * 初始化用户管理页面
	 * 
	 * 1.查询所有幼儿园集合。
	 * 2.设置跳转页面到用户管理页面。
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut initUserManage(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		
		ViewUserArea viewUserArea = new ViewUserArea();
		if(in.getPage()!=null&&in.getPage()>0){
			viewUserArea.setPage(in.getPage());
		}else{
			viewUserArea.setPage(1);
		}
		
		viewUserArea.setRows(10);
		
		viewUserArea.setIsSaved("MDGV7");
		
		viewUserArea.setSearch_content(in.getSearch());
		
		List<ViewUserArea> viewUserAreaList = viewUserAreaDao.findViewUserAreas(viewUserArea);
		
		Integer total = viewUserAreaDao.countViewUserAreas(viewUserArea);
		
		Integer pages = total/10;
		if(total%10!=0){
			pages++;
		}
		
		out.setPages(pages);
		
		out.setViewUserAreaList(viewUserAreaList);
		
		List<Kindergarten> kindergartenList = kindergartenDao.findKindergartens(new Kindergarten());
		
		out.setKindergartenList(kindergartenList);
		
		out.getJumpInfo().setFlag("MSG6007");
		return out;
	}
	
	/**
	 * 查询用户集合
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut findUsers(UserManageIn in) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 生成用户
	 * 
	 * 1.生成用户。
	 * 2.跳转至生成的用户页面。
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut generateUsers(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		
		String nowTime = TimeService.format(new Date());		
		
		Boolean normalUser = true;
		if(in.getRole().getId()!=3){
			normalUser = false;
		}
		Boolean kinUser = true;
		if(in.getRole().getId()!=2){
			kinUser = false;
		}
		
		Random r = new Random();
		
		for (int i = 0; i < in.getUserCount(); i++) {
			Users users = new Users();
			String username = "";
			while (users!=null) {
				users = new Users();
				username = generateUsername(r);
				users.setUsername(username);
				users = usersDao.queryUsers(users);
			}
			
			users = new Users();
			users.setUsername(username);
			users.setCreateTime(nowTime);
			users.setOperationTime(in.getOperationTime());
			users.setExpireTime(in.getExpireTime());
			users.setPassword(MD5Utils.getMD5String("123456"));
			users.setIsSaved(getGlobalvariable(new GlobalVariable("MDGV6")));
			
			users = usersDao.saveUsers(users);
			
			//保存用户角色
			UserRole userRole = new UserRole();
			userRole.setUserid(users);
			userRole.setRoleid(in.getRole());
			userRole.setCreateTime(nowTime);
			
			userRole = userRoleDao.saveUserRole(userRole);
			
			if(normalUser){
				UserArea userArea = new UserArea();
				userArea.setUserId(users);
				userArea.setAreaId(in.getArea());
				userArea.setCreateTime(nowTime);
				
				userArea = userAreaDao.saveUserRole(userArea);
			}
			
			if(kinUser){
				UserKinder userKinder = new UserKinder(null, users, in.getKindergarten(), nowTime);
				userKinder = userKinderDao.savUserKinder(userKinder);
			}
			
		}
		
		out.getJumpInfo().setFlag("MSG0003");
		
		return out;
	}
	
	private String generateUsername(Random r){		
		return MD5Utils.getMD5String(System.currentTimeMillis()+""+r.nextInt(1000)).substring(0,9).toLowerCase();
	}
	
	/**
	 * 用户信息 幼儿园管理
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut usersInfo(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		
		UserKinder userKinder = new UserKinder();
		userKinder.setUserId(in.getUsers());
		
		userKinder = userKinderDao.queryUserKinder(userKinder);
		
		ViewUserArea viewUserArea = new ViewUserArea();

		viewUserArea.setIsSaved("MDGV7");
		viewUserArea.setKindergartenId(userKinder.getKindergartenId().getId());
		
		if(in.getPage()!=null&&in.getPage()>0){
			viewUserArea.setPage(in.getPage());
		}else{
			viewUserArea.setPage(1);
		}
		
		viewUserArea.setRows(10);
		
		viewUserArea.setSearch_content(in.getSearch());
		
		List<ViewUserArea> viewUserAreaList = viewUserAreaDao.findViewUserAreas(viewUserArea);
		
		Integer total = viewUserAreaDao.countViewUserAreas(viewUserArea);
		
		Integer pages = total/10;
		if(total%10!=0){
			pages++;
		}
		
		out.setPages(pages);
		
		out.setViewUserAreaList(viewUserAreaList);
		
		out.getJumpInfo().setFlag("MSG6010");
		return out;
	}
	
	/**
	 * 进入生成用户页面
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut initGenerateUsers(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		
		ViewUserArea viewUserArea = new ViewUserArea();
		if(in.getPage()!=null&&in.getPage()>0){
			viewUserArea.setPage(in.getPage());
		}else{
			viewUserArea.setPage(1);
		}
		viewUserArea.setRows(10);
		
		viewUserArea.setIsSaved("MDGV6");
		List<ViewUserArea> viewUserAreaList = viewUserAreaDao.findViewUserAreas(viewUserArea);
		Integer total = viewUserAreaDao.countViewUserAreas(viewUserArea);
		
		Integer pages = total/10;
		if(total%10!=0){
			pages++;
		}
		
		out.setPages(pages);
		out.setViewUserAreaList(viewUserAreaList);
		
		List<Kindergarten> kindergartenList = kindergartenDao.findKindergartens(new Kindergarten());
		
		out.setKindergartenList(kindergartenList);
		
		
		out.getJumpInfo().setFlag("MSG6008");
		
		return out;
	}
	/**
	 * 删除用户
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut deleteUser(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		
		deleteUser_Method(in.getUsers());
		
		out.getJumpInfo().setFlag("MSG0005");
		return out;
	}
	
	/**
	 * 删除单个用户方法
	 * @author Chencong
	 * @param users
	 */
	private void deleteUser_Method(Users users) {
		UserTerminal userTerminal = new UserTerminal();
		userTerminal.setUserid(users);
		userTerminalDao.deleteUserTerminal(userTerminal);
		
		UserRole userRole = new UserRole();
		userRole.setUserid(users);
		userRoleDao.deleteUserRole(userRole);
		
		UserArea userArea = new UserArea();
		userArea.setUserId(users);
		userAreaDao.deleteUserArea(userArea);
		
		UserKinder userKinder = new UserKinder();
		userKinder.setUserId(users);
		userKinderDao.deleteUserKinder(userKinder);
		
		usersDao.deleteUsers(users);
	}
	
	/**
	 * 更新用户
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut updateUser(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		
		
		//判断是修改的幼儿园管理员还是普通用户
		if(in.getRoleId()==2l){
			//修改幼儿园管理员就查找用户幼儿园中间表 并进行修改
			UserKinder userKinder = new UserKinder();
			userKinder.setUserId(in.getUsers());
			
			userKinder = userKinderDao.queryUserKinder(userKinder);
			
			userKinder.setKindergartenId(in.getKindergarten());
			
		}else if(in.getRoleId()==3l){
			UserArea userArea = new UserArea();
			userArea.setUserId(in.getUsers());
			
			userArea = userAreaDao.queryUserRole(userArea);
			userArea.setAreaId(in.getArea());
		}
		
		if(StringUtils.isNotEmpty(in.getUsers().getPassword())){
			in.getUsers().setPassword(MD5Utils.getMD5String(in.getUsers().getPassword()));
		}
		
		//更新用户对象
		usersDao.updateUsers(in.getUsers());
		
		out.getJumpInfo().setFlag("MSG0006");
		return out;
	}
	/**
	 * 导出用户
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut exportUser(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		
		ViewUserArea viewUserArea = new ViewUserArea();
		if(in.getPage()!=null&&in.getPage()>0){
			viewUserArea.setPage(in.getPage());
		}else{
			viewUserArea.setPage(1);
		}
		viewUserArea.setRows(10);
		viewUserArea.setSearch_content(in.getSearch());
		
		List<ViewUserArea> viewUserAreaList = viewUserAreaDao.findViewUserAreas(viewUserArea);
		
		out.setViewUserAreaList(viewUserAreaList);
		
		return out;
	}
	/**
	 * 批量删除用户
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut batchDeleteUser(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		if(StringUtils.isEmpty(in.getIds())){
			out.getJumpInfo().setFlag("MSG4001");
			return out;
		}
		
		String[] idArray = in.getIds().split(",");
		
		for (int i = 0; i < idArray.length; i++) {
			Users users = new Users();
			users.setId(Long.valueOf(idArray[i]));
			deleteUser_Method(users);
			
		}
		
		
		out.getJumpInfo().setFlag("MSG0005");
		return out;
	}
	
	/**
	 * 保存用户
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public UserManageOut saveUsers(UserManageIn in) {
		UserManageOut out = new UserManageOut();
		
		usersDao.saveAllUsers();
		
		out.getJumpInfo().setFlag("MSG0011");
		return out;
	}
	
}

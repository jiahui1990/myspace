package com.ggy.baby.login.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javacommon.base.service.impl.BaseServiceImpl;
import javacommon.javautil.HttpClientService;
import javacommon.javautil.MD5Utils;
import javacommon.javautil.PhoneService;
import javacommon.javautil.TimeService;

import com.ggy.baby.constants.Constants;
import com.ggy.baby.login.dao.IPhoneCodeDao;
import com.ggy.baby.login.dao.IUserRoleDao;
import com.ggy.baby.login.dao.IUserTerminalDao;
import com.ggy.baby.login.dao.IUsersDao;
import com.ggy.baby.login.model.PhoneCode;
import com.ggy.baby.login.model.UserRole;
import com.ggy.baby.login.model.UserTerminal;
import com.ggy.baby.login.model.Users;
import com.ggy.baby.login.pageModel.LoginIn;
import com.ggy.baby.login.pageModel.LoginOut;
import com.ggy.baby.login.service.ILoginService;
import com.ggy.baby.monitorManage.dao.IViewMonitorDao;
import com.ggy.baby.monitorManage.model.ViewMonitor;
import com.ggy.baby.userManage.dao.IViewUserAreaDao;
import com.ggy.baby.userManage.model.ViewUserArea;

/**
 * 登陆service实现
 * @author Chencong
 *
 */
@Transactional
public class LoginServiceImpl extends BaseServiceImpl implements ILoginService {
	/**
	 * 用户dao
	 */
	private IUsersDao usersDao;
	/**
	 * 用户角色dao
	 */
	private IUserRoleDao userRoleDao;
	/**
	 * 用户登陆终端dao
	 */
	private IUserTerminalDao userTerminalDao;
	/**
	 * 电话验证码dao
	 */
	private IPhoneCodeDao phoneCodeDao;
	/**
	 * 用户区域视图dao
	 */
	private IViewUserAreaDao viewUserAreaDao;
	/**
	 * 监控视图dao
	 */
	private IViewMonitorDao viewMonitorDao;

	public IViewMonitorDao getViewMonitorDao() {
		return viewMonitorDao;
	}

	public void setViewMonitorDao(IViewMonitorDao viewMonitorDao) {
		this.viewMonitorDao = viewMonitorDao;
	}

	public IViewUserAreaDao getViewUserAreaDao() {
		return viewUserAreaDao;
	}

	public void setViewUserAreaDao(IViewUserAreaDao viewUserAreaDao) {
		this.viewUserAreaDao = viewUserAreaDao;
	}

	public IUsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public IUserTerminalDao getUserTerminalDao() {
		return userTerminalDao;
	}

	public void setUserTerminalDao(IUserTerminalDao userTerminalDao) {
		this.userTerminalDao = userTerminalDao;
	}

	public IPhoneCodeDao getPhoneCodeDao() {
		return phoneCodeDao;
	}

	public void setPhoneCodeDao(IPhoneCodeDao phoneCodeDao) {
		this.phoneCodeDao = phoneCodeDao;
	}

	public IUserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	/**
	 * web端登陆
	 * 
	 * 1.查询用户对象。不存在则提示用户不存在。
	 * 2.比较页面密码和数据库密码是否一致。不一致提示密码错误。
	 * 3.比较用户过期时间。如果已经过期，提示用户已过期。
	 * 4.检测是否带有手机验证码。
	 *      4_1.不带手机验证码，查询本次登陆的mac地址是否在用户登陆终端。
	 * 			4_1_1.如果不在，查询用户已登陆终端是否超过3个。
	 * 				4_1_1_1.已超过3个终端，且绑定了手机的用户则发送手机验证码,并提示页面输入验证码。 如果没有绑定手机，提示他拨打客服电话
	 * 				4_1_1_2.没超过3个终端，执行保存本终端到数据库。
	 * 		4_2.带手机验证码，比对数据库验证码。
	 * 			4_2_1.手机验证码正确，删除用户登陆终端中最早的一个，并把本次登陆终端保存到数据库。
	 * 			4_2_2.手机验证码不正确，提示用户手机验证码错误。
	 * 5.查询用户角色，根据不同的用户角色设置不同的跳转页面。
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public LoginOut login(LoginIn in) {
		LoginOut out = new LoginOut();
		
		Users pageUsers = in.getUsers();
		Users dbUsers = new Users();
		dbUsers.setUsername(pageUsers.getUsername());
		dbUsers = usersDao.queryUsers(dbUsers);
		
		if(dbUsers==null){
			out.getJumpInfo().setFlag("MSG4002");
			return out;
		}
		if(!comparePassword(pageUsers, dbUsers)){
			out.getJumpInfo().setFlag("MSG4003");
			return out;
		}
		if(!userIsOperation(dbUsers)){
			out.getJumpInfo().setFlag("MSG4004");
			return out;
		}
		if(userIsExpire(dbUsers)){
			out.getJumpInfo().setFlag("MSG4005");
			return out;
		}
		
		if(StringUtils.isEmpty(in.getPhoneCode())){
			out = macExists(in.getMac(),dbUsers);
			if(!StringUtils.isEmpty(out.getJumpInfo().getFlag())){
				return out;
			}
		}else{		
			String codeResult = comparePhoneCode(in,dbUsers);
			if(!codeResult.equals("SUCCESS")){
				out.getJumpInfo().setFlag(codeResult);
				return out;
			}
		}
		
		out = setLoginRightPage(dbUsers);
		out.setUsers(dbUsers);
		return out;
	}
	/**
	 * 设置登陆成功页面
	 * @author Chencong
	 * @param dbUsers
	 * @return
	 */
	private LoginOut setLoginRightPage(Users dbUsers){
		LoginOut out = new LoginOut();
		
		//登陆成功，查询用户角色，根据不同角色跳转不同页面。
		UserRole userRole = new UserRole();
		userRole.setUserid(dbUsers);
		
		userRole = userRoleDao.queryUserRole(userRole);
		
		String roleNumber = userRole.getRoleid().getRoleNumber();
		if(roleNumber.equals("PROLE1")){
			out.getJumpInfo().setFlag("MSG6001");
		}
		else if(roleNumber.equals("PROLE2")){
			out.getJumpInfo().setFlag("MSG6002");
		}
		else if(roleNumber.equals("PROLE3")){
			out.getJumpInfo().setFlag("MSG6003");
		}
		
		return out;
	}
	
	/**
	 * 比较手机验证码是否正确
	 * @author Chencong
	 * @param in
	 * @param dbUsers
	 * @return
	 */
	private String comparePhoneCode(LoginIn in,Users dbUsers){
		
		
		PhoneCode phoneCode = new PhoneCode();
		phoneCode.setCode(in.getPhoneCode());
		phoneCode.setUserId(dbUsers);
		
		phoneCode = phoneCodeDao.queryPhoneCode(phoneCode);
		if(phoneCode==null){
			//返回错误
			return "MSG4010";
		}
		
		Date createTime = TimeService.stringToDate(phoneCode.getCreateTime());
		long temp = (new Date()).getTime() - createTime.getTime();
		/**
		 * 120秒超时
		 */
		if(temp>120*1000){
			return "MSG4013";
		}
		
		UserTerminal userTerminal = new UserTerminal();
		userTerminal.setUserid(dbUsers);
		userTerminalDao.deleteOldestUserTerminal(userTerminal);
		
		userTerminal = new UserTerminal();
		userTerminal.setUserid(dbUsers);
		userTerminal.setMacAddress(in.getMac());
		userTerminal.setCreateTime(TimeService.format(new Date()));
		
		userTerminalDao.saveUserTerminal(userTerminal);
		
		return "SUCCESS";
	}
	
	/**
	 * 判断mac地址是否存在 不存在会去判断是否超过三个并发手机验证码
	 * @author Chencong
	 * @param mac
	 * @param dbUsers
	 * @return
	 */
	private LoginOut macExists(String mac,Users dbUsers){
		LoginOut out = new LoginOut();
		
		UserTerminal userTerminal = new UserTerminal();
		userTerminal.setUserid(dbUsers);
		userTerminal.setMacAddress(mac);
		
		UserTerminal dbTerminal = userTerminalDao.queryUserTerminal(userTerminal);
		if(dbTerminal == null){
			userTerminal.setMacAddress(null);
			if(userTerminalDao.counttUserTerminals(userTerminal)>=3){
				
				out = sendPhoneCodeMethod(dbUsers);
				if(StringUtils.isEmpty(out.getJumpInfo().getFlag())){
					out.getJumpInfo().setFlag("MSG4007");
				}
			}else{
				userTerminal.setMacAddress(mac);
				userTerminal.setCreateTime(TimeService.format(new Date()));
				
				userTerminalDao.saveUserTerminal(userTerminal);
			}
		}

		return out;
	}
	
	/**
	 * 发送手机验证码方法
	 * @author Chencong
	 * @param dbUsers
	 * @return
	 */
	private LoginOut sendPhoneCodeMethod(Users dbUsers){
		LoginOut out = new LoginOut();
		
		if(StringUtils.isEmpty(dbUsers.getTelphone())){
			out.getJumpInfo().setFlag("MSG4009");
			return out;
		}
		
		if(checkPhoneCodeTimes(dbUsers)){
			Random random = new Random();
			Integer x = random.nextInt(899999);
			x = x+100000;
			
			if(PhoneService.sendPhone(dbUsers.getTelphone(), "您的验证码是：" + x + "。请不要把验证码泄露给其他人。").equals("0")){
				out.getJumpInfo().setFlag("MSG4012");
				return out;
			}
			
			PhoneCode phoneCode = new PhoneCode();
			phoneCode.setUserId(dbUsers);
			phoneCode.setCode(x.toString());
			phoneCode.setCreateTime(TimeService.format(new Date()));
			phoneCodeDao.savePhoneCode(phoneCode);
			
			if(StringUtils.isEmpty(out.getJumpInfo().getFlag())){
				out.getJumpInfo().setFlag("MSG0000");
			}
		}else{
			out.getJumpInfo().setFlag("MSG4012");
			out.getJumpInfo().setInfo("手机验证码发送次数每天不能超过5次");
		}
		
		
		
		/*out.setCode(x.toString());*/
		
		return out;
	}
	
	/**
	 * 判断用户是否已经生效
	 * @author Chencong
	 * @param dbUsers
	 * @return
	 */
	private Boolean userIsOperation(Users dbUsers){
		Date opDate = TimeService.stringToDate(dbUsers.getOperationTime());
		Date now = new Date();
		
		if(now.before(opDate)){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判断用户是否已经过期
	 * @author Chencong
	 * @param dbUsers
	 * @return
	 */
	private Boolean userIsExpire(Users dbUsers){
		Date exDate = TimeService.stringToDate(dbUsers.getExpireTime());
		Date now = new Date();
		
		if(now.before(exDate)){
			return false;
		}
		
		return true;
	}
	/**
	 * 比较密码是否正确
	 * @author Chencong
	 * @param pageUsers
	 * @param dbUsers
	 * @return
	 */
	private Boolean comparePassword(Users pageUsers,Users dbUsers){
		if(dbUsers.getPassword().equals(MD5Utils.getMD5String(pageUsers.getPassword()))){
			return true;
		}
		return false;
	}

	/**
	 * app登陆
	 * 
	 * 1.查询用户对象。不存在则提示用户不存在。
	 * 2.比较页面密码和数据库密码是否一致。不一致提示密码错误。
	 * 3.比较用户过期时间。如果已经过期，提示用户已过期。
	 * 4.查询用户角色,如果登陆用户不是普通用户。提示手机端只能登陆普通用户。
	 * 5.检测是否带有手机验证码。
	 *      5_1.不带手机验证码，查询本次登陆的mac地址是否在用户登陆终端。
	 * 			5_1_1.如果不在，查询用户已登陆终端是否超过3个。
	 * 				5_1_1_1.已超过3个终端，且绑定了手机的用户则发送手机验证码,并提示页面输入验证码。 如果没有绑定手机，提示他拨打客服电话
	 * 				5_1_1_2.没超过3个终端，执行保存本终端到数据库。
	 * 		5_2.带手机验证码，比对数据库验证码。
	 * 			5_2_1.手机验证码正确，删除用户登陆终端中最早的一个，并把本次登陆终端保存到数据库。
	 * 			5_2_2.手机验证码不正确，提示用户手机验证码错误。
	 * 6.获取用户所有的监控集合。
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public LoginOut appLogin(LoginIn in) {
		LoginOut out = login(in);
		
		String loginResult = out.getJumpInfo().getFlag();
		if(loginResult.equals("MSG6001")||loginResult.equals("MSG6002")){
			out.getJumpInfo().setFlag("MSG4014");
			return out;
		}
		
		if(loginResult.equals("MSG6003")){
			ViewUserArea userArea = new ViewUserArea();
			userArea.setUserId(out.getUsers().getId());
			userArea = viewUserAreaDao.queryViewUserArea(userArea);
			if(userArea==null){
				out.getJumpInfo().setFlag("MSG4001");
				return out;
			}
			
			ViewMonitor monitor = new ViewMonitor();
			monitor.setAreaId(userArea.getAreaId());
			List<ViewMonitor> viewMonitorList = viewMonitorDao.findViewMonitors(monitor);
			out.setViewMonitorList(viewMonitorList);
		}
		
		out.setVideoAccountUsername(Constants.VIDEO_ACCOUNT_USERNAME);
		out.setVideoAccountPassword(Constants.VIDEO_ACCOUNT_PASSWORD);
		
		return out;
	}
	/**
	 * 发送手机验证码
	 * 
	 * 1.查询用户对象。
	 * 2.向用户绑定的手机发送随机验证码。如果用户未绑定手机号码，返回错误。
	 * 3.保存手机验证码到数据库。
	 * 
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public LoginOut sendPhoneCode(LoginIn in) {
		LoginOut out = new LoginOut();
		
		Users dbuser = usersDao.queryUsers(in.getUsers());
		
		if(dbuser==null){
			out.getJumpInfo().setFlag("MSG4002");
		}
		
		out = sendPhoneCodeMethod(dbuser);
		
		if(StringUtils.isEmpty(out.getJumpInfo().getFlag())){
			out.getJumpInfo().setFlag("MSG0000");
		}
		
		return out;
	}
	
	public boolean checkPhoneCodeTimes(Users users){
		//当天验证码发送次数验证
		String minTime = TimeService.getTimeStringShort(0);
		String maxTime = TimeService.getTimeStringShort(1);
		String hql = "select id from PhoneCode where userId.username='"+users.getUsername()+"' and createTime between '"+minTime+"'"+" and '"+maxTime+"'";
		List list = phoneCodeDao.executeHql(hql);
		if(list.size()<=Constants.SEND_PHONE_CODE_LIMIT){
			return true;
		}
		return false;
	}
	
	/**
	 * 登陆时有其他设备在线时，确认上线。
	 * 
	 * 1.保存登陆记录到数据库。
	 * 2.查询用户角色，设置跳转页面。
	 * 
	 * @author Chencong
	 * @param in
	 * @return 
	 */
	@Override
	public LoginOut confirmOnline(LoginIn in) {
		LoginOut out = new LoginOut();
		
		out = setLoginRightPage(in.getUsers());
		
		out.setUsers(in.getUsers());
		return out;
	}
	/**
	 * 找回密码发送手机验证码
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public LoginOut sendForgotPassCode(LoginIn in) {
		LoginOut out = new LoginOut();
		
		Users users = new Users();
		users.setTelphone(in.getPhone());
		users = usersDao.queryUsers(users);
		if(users==null){
			out.getJumpInfo().setFlag("MSG4002");
			return out;
		}
		
		out = sendPhoneCodeMethod(users);
		
		if(StringUtils.isEmpty(out.getJumpInfo().getFlag())){
			out.getJumpInfo().setFlag("MSG0000");
		}
		
		return out;
	}
	
	/**
	 * 验证找回密码的验证码
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public LoginOut vertifyForgotPassCode(LoginIn in) {
		LoginOut out = new LoginOut();
		
		Users users = new Users();
		users.setTelphone(in.getPhone());
		users = usersDao.queryUsers(users);
		if(users==null){
			out.getJumpInfo().setFlag("MSG4002");
			return out;
		}
		
		String compareResult = comparePhoneCode(in,users);
		if(compareResult!="SUCCESS"){
			out.getJumpInfo().setFlag(compareResult);
			return out;
		}
		
		out.getJumpInfo().setFlag("MSG0000");
		
		return out;
	}
	/**
	 * 修改密码
	 * @author Chencong
	 * @param in
	 * @return
	 */
	@Override
	public LoginOut modifyPassword(LoginIn in) {
		LoginOut out = new LoginOut();
		Users users = new Users(); 
		users.setTelphone(in.getPhone());
		users.setUsername(in.getUsername());
		users = usersDao.queryUsers(users);		
		users.setPassword(MD5Utils.getMD5String(in.getPassword()));
		
		usersDao.updateUsers(users);
		
		out.getJumpInfo().setFlag("MSG0000");
		return out;
	}
	
}

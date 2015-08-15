package com.ggy.baby.userManage.action;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javacommon.base.action.BaseAction;
import javacommon.javautil.ExcelService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ggy.baby.monitorManage.model.ViewMonitor;
import com.ggy.baby.userManage.model.ViewUserArea;
import com.ggy.baby.userManage.pageModel.UserManageIn;
import com.ggy.baby.userManage.pageModel.UserManageOut;
import com.ggy.baby.userManage.service.IUserManageService;
/**
 * 用户管理Action
 * @author Chencong
 *
 */
public class UserManageAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6645906906428382232L;
	/**
	 * 用户管理in参数
	 */
	private UserManageIn in = new UserManageIn();
	
	/**
	 * 用户管理service
	 */
	private IUserManageService userManageService;	
	/**
	 * 页数
	 */
	private Integer page;
	/**
	 * 搜索内容
	 */
	private String search = "";
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) throws UnsupportedEncodingException {
		this.search = URLDecoder.decode(search,"UTF-8");
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public UserManageIn getIn() {
		return in;
	}
	public void setIn(UserManageIn in) {
		this.in = in;
	}
	public IUserManageService getUserManageService() {
		return userManageService;
	}
	public void setUserManageService(IUserManageService userManageService) {
		this.userManageService = userManageService;
	}
	/**
	 * 初始化用户管理页面
	 * @author Chencong
	 * @return
	 */
	public String initUserManage(){
		UserManageOut out = new UserManageOut();
		
		try {
			if(page==null){
				page=1;
			}
			in.setPage(page);
			in.setSearch(getSearch());
			out = userManageService.initUserManage(in);

			getRequest().setAttribute("viewUserAreas", out.getViewUserAreaList());
			getRequest().setAttribute("kinders", out.getKindergartenList());

			getRequest().setAttribute("page", page);
			getRequest().setAttribute("pages", out.getPages());
			getRequest().setAttribute("search", search);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		return out.getJumpInfo().getUrl();
	}
	/**
	 * 查询用户集合
	 * @author Chencong
	 * @return
	 */
	public String findUsers(){
		UserManageOut out = new UserManageOut();
		
		try {
			out = userManageService.findUsers(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		super.writeJson(out.getViewUserAreaList());
		
		return null;
	}
	
	/**
	 * 进入生成用户页面
	 * @author Chencong
	 * @return
	 */
	public String initGenerateUsers(){
		UserManageOut out = new UserManageOut();
		
		try {
			if(page==null){
				page=1;
			}
			in.setPage(page);
			
			out = userManageService.initGenerateUsers(in);
			
			getRequest().setAttribute("kinders", out.getKindergartenList());
			getRequest().setAttribute("viewUserAreas", out.getViewUserAreaList());
			
			getRequest().setAttribute("page", page);
			getRequest().setAttribute("pages", out.getPages());
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		return out.getJumpInfo().getUrl();
	}
	
	/**
	 * 生成用户
	 * @author Chencong
	 * @return
	 */
	public String generateUsers(){
		UserManageOut out = new UserManageOut();
		
		try {
			out = userManageService.generateUsers(in);
			
			/*getRequest().setAttribute("viewUserRoles", out.getViewUserAreaList());*/
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	
	/**
	 * 用户信息 幼儿园管理
	 * @author Chencong
	 * @return
	 */
	public String usersInfo(){
		UserManageOut out = new UserManageOut();
		
		try {
			in.setUsers(getCurrentUser());
			if(page==null){
				page=1;
			}
			in.setPage(page);
			in.setSearch(getSearch());
			out = userManageService.usersInfo(in);

			getRequest().setAttribute("viewUserAreas", out.getViewUserAreaList());

			getRequest().setAttribute("page", page);
			getRequest().setAttribute("pages", out.getPages());
			getRequest().setAttribute("search", search);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		return out.getJumpInfo().getUrl();
		
	}
	
	/**
	 * 删除用户
	 * @author Chencong
	 * @return
	 */
	public String deleteUser(){
		UserManageOut out = new UserManageOut();
		
		try {
			out = userManageService.deleteUser(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	
	/**
	 * 批量删除用户
	 * @author Chencong
	 * @return
	 */
	public String batchDeleteUser(){
		UserManageOut out = new UserManageOut();
		
		try {
			out = userManageService.batchDeleteUser(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	/**
	 * 保存用户
	 * @author Chencong
	 * @return
	 */
	public String saveUsers(){
		UserManageOut out = new UserManageOut();
		
		try {
			out = userManageService.saveUsers(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	
	/**
	 * 更新用户
	 * @author Chencong
	 * @return
	 */
	public String updateUser(){
		UserManageOut out = new UserManageOut();
		
		try {
			out = userManageService.updateUser(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	/**
	 * 导出用户
	 * @author Chencong
	 * @return
	 */
	public String exportUser(){
		UserManageOut out = new UserManageOut();
		try {
			if(page==null){
				page=1;
			}
			in.setPage(page);
			in.setSearch(search);
			
			out = userManageService.exportUser(in);
			
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setHeader("Content-Disposition","attachment;fileName=user.xls");
			ExcelService.doopen(write(out),new DataOutputStream(getResponse().getOutputStream()));
			/*getRequest().setAttribute("viewMonitors", out.getViewMonitorList());
			
			getRequest().setAttribute("page", page);
			getRequest().setAttribute("pages", out.getPages());
			getRequest().setAttribute("search", search);*/
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		return null;
	}
	
	private JSONArray write(UserManageOut out) {
		List<ViewUserArea> userAreas = out.getViewUserAreaList();
		JSONArray arrayObject = new JSONArray();
		
		if(userAreas == null||userAreas.size()<=0){
			return arrayObject;
		}
		
		JSONObject username = new JSONObject();
	    JSONObject password = new JSONObject();
	    JSONObject kinder = new JSONObject();
	    JSONObject area = new JSONObject();
	    JSONObject telphone = new JSONObject();
	    JSONObject operationTime = new JSONObject();
	    JSONObject expireTime = new JSONObject();
	    JSONObject roleName = new JSONObject();
	    username.put("name", "用户名");
	    password.put("name", "密码");
	    kinder.put("name", "幼儿园名");
	    area.put("name", "所在班级");
	    telphone.put("name", "注册手机");
	    operationTime.put("name", "生效时间");
	    expireTime.put("name", "终止时间");
	    roleName.put("name", "权限");
	    
	    JSONArray array_username = new JSONArray();
	    JSONArray array_password = new JSONArray();
	    JSONArray array_kinder = new JSONArray();
	    JSONArray array_area = new JSONArray();
	    JSONArray array_telphone = new JSONArray();
	    JSONArray array_operationTime = new JSONArray();
	    JSONArray array_expireTime = new JSONArray();
	    JSONArray array_roleName = new JSONArray();
		
		for (ViewUserArea userarea : userAreas) {
			array_username.add(userarea.getUserName());
			array_password.add(userarea.getPassword());
			array_kinder.add(userarea.getKindergartenName());
			array_area.add(userarea.getAreaName());
			array_telphone.add(userarea.getTelphone());
			array_operationTime.add(userarea.getOperationTime());
			array_expireTime.add(userarea.getExpireTime());
			array_roleName.add(userarea.getRoleName());
		}
		username.put("value", array_username);
		password.put("value", array_password);
		kinder.put("value", array_kinder);
		area.put("value", array_area);
		telphone.put("value", array_telphone);
		operationTime.put("value", array_operationTime);
		expireTime.put("value", array_expireTime);
		roleName.put("value", array_roleName);
		
		
		arrayObject.add(username);
		arrayObject.add(password);
		arrayObject.add(kinder);
		arrayObject.add(area);
		arrayObject.add(telphone);
		arrayObject.add(operationTime);
		arrayObject.add(expireTime);
		arrayObject.add(roleName);
		
		return arrayObject;
	}
}

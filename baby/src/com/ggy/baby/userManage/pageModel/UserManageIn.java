package com.ggy.baby.userManage.pageModel;

import com.ggy.baby.login.model.Role;
import com.ggy.baby.login.model.Users;
import com.ggy.baby.monitorManage.model.Kindergarten;
import com.ggy.baby.monitorManage.model.KindergartenArea;

/**
 * 用户管理In参数
 * @author Chencong
 *
 */
public class UserManageIn {
	/**
	 * 创建的用户角色
	 */
	private Role role = new Role();
	/**
	 * 生成用户数量
	 */
	private Integer userCount;
	/**
	 * 生效时间
	 */
	private String operationTime;
	/**
	 * 失效时间
	 */
	private String expireTime;
	/**
	 * 创建的用户拥有的区域
	 */
	private KindergartenArea area = new KindergartenArea();	
	
	private Kindergarten kindergarten = new Kindergarten();
	/**
	 * 页数
	 */
	private Integer page;
	
	/**
	 * 搜索内容
	 */
	private String search;
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 批量删除的用户id集合
	 */
	private String ids;
	
	private Users users = new Users();
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public KindergartenArea getArea() {
		return area;
	}

	public void setArea(KindergartenArea area) {
		this.area = area;
	}
	public Kindergarten getKindergarten() {
		return kindergarten;
	}
	public void setKindergarten(Kindergarten kindergarten) {
		this.kindergarten = kindergarten;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}

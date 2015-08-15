package com.ggy.baby.login.model;
/**
 * 角色表
 * @author Chencong
 *
 */
public class Role {
	/**
	 * 角色id
	 */
	private Long id;
	/**
	 * 角色标号 如PROLE1
	 */
	private String roleNumber;
	/**
	 * 备注
	 */
	private String comments;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(String roleNumber) {
		this.roleNumber = roleNumber;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Role(Long id, String roleNumber, String comments) {
		super();
		this.id = id;
		this.roleNumber = roleNumber;
		this.comments = comments;
	}
	public Role() {
		super();
	}
	
	
}

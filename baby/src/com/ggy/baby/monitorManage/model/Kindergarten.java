package com.ggy.baby.monitorManage.model;
/**
 * 幼儿园对象
 * @author Chencong
 *
 */
public class Kindergarten {
	/**
	 * 幼儿园id
	 */
	private Long id;
	/**
	 * 幼儿园名字
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}

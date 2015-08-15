package com.ggy.baby.monitorManage.model;
/**
 * 幼儿园区域
 * @author Chencong
 *
 */
public class KindergartenArea {
	/**
	 * 区域id
	 */
	private Long id;
	/**
	 * 幼儿园id
	 */
	private Kindergarten kindergartenId;
	/**
	 * 区域名称
	 */
	private String name;
	/**
	 * 父id 一级区域父id为0
	 */
	private Long pid;
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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Kindergarten getKindergartenId() {
		return kindergartenId;
	}

	public void setKindergartenId(Kindergarten kindergartenId) {
		this.kindergartenId = kindergartenId;
	}

	public KindergartenArea(Long id, Kindergarten kindergartenId, String name,
			Long pid, String createTime) {
		super();
		this.id = id;
		this.kindergartenId = kindergartenId;
		this.name = name;
		this.pid = pid;
		this.createTime = createTime;
	}

	public KindergartenArea() {
		super();
	}

	
	
}

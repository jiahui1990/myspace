package javacommon.base.model;

import java.util.Date;

/**
 * Businessactivity entity. @author MyEclipse Persistence Tools
 */

public class BusinessActivity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8040594671142896452L;
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 业务编号
	 */
	private String numbers;
	/**
	 * 业务名称
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createtime;

	// Constructors

	/** default constructor */
	public BusinessActivity() {
	}
	
	public BusinessActivity(Long id) {
		this.id = id;
	}
	
	public BusinessActivity(String numbers) {
		this.numbers = numbers;
	}

	/** full constructor */
	public BusinessActivity(Long id, String numbers, String content,
			Date createtime) {
		this.id = id;
		this.numbers = numbers;
		this.content = content;
		this.createtime = createtime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
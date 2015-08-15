package com.scu.dto;


/**
 * ProjectForm entity. @author MyEclipse Persistence Tools
 */
public class FieldInfoDto implements java.io.Serializable {

	// Fields

	private Integer id;
	private String field;
	private Integer num;
	private String fieldId;
	private Integer position;

	// Constructors

	/** default constructor */
	public FieldInfoDto() {
	}

	/** full constructor */
	public FieldInfoDto(String name, Integer position) {
		this.position = position;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

}
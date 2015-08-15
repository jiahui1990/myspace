package com.scu.bean;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProjectForm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Field_Info_Xihua", schema = "dbo")
public class FieldInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String field;
	private Integer num;
	private String fieldId;
	private Integer position;

	// Constructors

	/** default constructor */
	public FieldInfo() {
	}

	/** full constructor */
	public FieldInfo(String name, Integer position) {
		this.position = position;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "field")
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	@Column(name = "num")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	@Column(name = "field_id")
	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
}
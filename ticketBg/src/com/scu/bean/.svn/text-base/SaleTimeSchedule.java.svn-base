package com.scu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SaleTimeSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sale_time_schedule", schema = "dbo", catalog = "beihuTicket")
public class SaleTimeSchedule implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer number;
	private String workBegin1;
	private String workEnd1;
	private String workBegin2;
	private String workEnd2;
	private String workBegin3;
	private String workEnd3;
	private String standardModifyTime;

	// Constructors

	/** default constructor */
	public SaleTimeSchedule() {
	}

	/** full constructor */
	public SaleTimeSchedule(Integer number, String workBegin1, String workEnd1,
			String workBegin2, String workEnd2, String workBegin3,
			String workEnd3, String standardModifyTime) {
		this.number = number;
		this.workBegin1 = workBegin1;
		this.workEnd1 = workEnd1;
		this.workBegin2 = workBegin2;
		this.workEnd2 = workEnd2;
		this.workBegin3 = workBegin3;
		this.workEnd3 = workEnd3;
		this.standardModifyTime = standardModifyTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Number")
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "Work_begin1")
	public String getWorkBegin1() {
		return this.workBegin1;
	}

	public void setWorkBegin1(String workBegin1) {
		this.workBegin1 = workBegin1;
	}

	@Column(name = "Work_end1")
	public String getWorkEnd1() {
		return this.workEnd1;
	}

	public void setWorkEnd1(String workEnd1) {
		this.workEnd1 = workEnd1;
	}

	@Column(name = "Work_begin2")
	public String getWorkBegin2() {
		return this.workBegin2;
	}

	public void setWorkBegin2(String workBegin2) {
		this.workBegin2 = workBegin2;
	}

	@Column(name = "Work_end2")
	public String getWorkEnd2() {
		return this.workEnd2;
	}

	public void setWorkEnd2(String workEnd2) {
		this.workEnd2 = workEnd2;
	}

	@Column(name = "Work_begin3")
	public String getWorkBegin3() {
		return this.workBegin3;
	}

	public void setWorkBegin3(String workBegin3) {
		this.workBegin3 = workBegin3;
	}

	@Column(name = "Work_end3")
	public String getWorkEnd3() {
		return this.workEnd3;
	}

	public void setWorkEnd3(String workEnd3) {
		this.workEnd3 = workEnd3;
	}

	@Column(name = "Standard_Modify_Time")
	public String getStandardModifyTime() {
		return this.standardModifyTime;
	}

	public void setStandardModifyTime(String standardModifyTime) {
		this.standardModifyTime = standardModifyTime;
	}

}
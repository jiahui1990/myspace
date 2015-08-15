package com.scu.bean;

// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PlayDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Play_Detail", schema = "dbo")
public class PlayDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ticketId;
	private String field;
	private Integer fieldNum;
	private Timestamp ticketDateBegin;
	private Timestamp ticketDateEnd;

	// Constructors

	/** default constructor */
	public PlayDetail() {
	}

	/** minimal constructor */
	public PlayDetail(String ticketId, Timestamp ticketDateBegin) {
		this.ticketId = ticketId;
		this.ticketDateBegin = ticketDateBegin;
	}

	/** full constructor */
	public PlayDetail(String ticketId, String field, Integer fieldNum,
			Timestamp ticketDateBegin, Timestamp ticketDateEnd) {
		this.ticketId = ticketId;
		this.field = field;
		this.fieldNum = fieldNum;
		this.ticketDateBegin = ticketDateBegin;
		this.ticketDateEnd = ticketDateEnd;
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

	@Column(name = "ticket_ID", nullable = false, length = 50)
	public String getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	@Column(name = "field", length = 50)
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Column(name = "field_num")
	public Integer getFieldNum() {
		return this.fieldNum;
	}

	public void setFieldNum(Integer fieldNum) {
		this.fieldNum = fieldNum;
	}

	@Column(name = "ticket_date_begin", nullable = false, length = 23)
	public Timestamp getTicketDateBegin() {
		return this.ticketDateBegin;
	}

	public void setTicketDateBegin(Timestamp ticketDateBegin) {
		this.ticketDateBegin = ticketDateBegin;
	}

	@Column(name = "ticket_date_end", length = 23)
	public Timestamp getTicketDateEnd() {
		return this.ticketDateEnd;
	}

	public void setTicketDateEnd(Timestamp ticketDateEnd) {
		this.ticketDateEnd = ticketDateEnd;
	}

}
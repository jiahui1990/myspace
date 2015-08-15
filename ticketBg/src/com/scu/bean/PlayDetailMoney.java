package com.scu.bean;

// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PlayDetailMoneyId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "play_detail_money", schema = "dbo")
public class PlayDetailMoney implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ticketId;
	private String field;
	private Integer fieldNum;
	private Timestamp ticketDateBegin;
	private Timestamp ticketDateEnd;
	private Double total;
	private String saleTicketType;
	private Double perMoney;
	private Timestamp ticketEffectiveDate;
	private String mini;

	// Constructors

	/** default constructor */
	public PlayDetailMoney() {
	}

	/** minimal constructor */
	public PlayDetailMoney(String ticketId, Timestamp ticketDateBegin) {
		this.ticketId = ticketId;
		this.ticketDateBegin = ticketDateBegin;
	}

	/** full constructor */
	public PlayDetailMoney(String ticketId, String field, Integer fieldNum,
			Timestamp ticketDateBegin, Timestamp ticketDateEnd, Double total,
			String saleTicketType, Double perMoney,
			Timestamp ticketEffectiveDate) {
		this.ticketId = ticketId;
		this.field = field;
		this.fieldNum = fieldNum;
		this.ticketDateBegin = ticketDateBegin;
		this.ticketDateEnd = ticketDateEnd;
		this.total = total;
		this.saleTicketType = saleTicketType;
		this.perMoney = perMoney;
		this.ticketEffectiveDate = ticketEffectiveDate;
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
	@Column(name = "ticket_ID", length = 50)
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

	@Column(name = "total", precision = 53, scale = 0)
	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "sale_ticket_type", length = 50)
	public String getSaleTicketType() {
		return this.saleTicketType;
	}

	public void setSaleTicketType(String saleTicketType) {
		this.saleTicketType = saleTicketType;
	}

	@Column(name = "per_money", precision = 53, scale = 0)
	public Double getPerMoney() {
		return this.perMoney;
	}

	public void setPerMoney(Double perMoney) {
		this.perMoney = perMoney;
	}

	@Column(name = "ticket_effective_date", length = 23)
	public Timestamp getTicketEffectiveDate() {
		return this.ticketEffectiveDate;
	}

	public void setTicketEffectiveDate(Timestamp ticketEffectiveDate) {
		this.ticketEffectiveDate = ticketEffectiveDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PlayDetailMoney))
			return false;
		PlayDetailMoney castOther = (PlayDetailMoney) other;

		return ((this.getTicketId() == castOther.getTicketId()) || (this
				.getTicketId() != null
				&& castOther.getTicketId() != null && this.getTicketId()
				.equals(castOther.getTicketId())))
				&& ((this.getField() == castOther.getField()) || (this
						.getField() != null
						&& castOther.getField() != null && this.getField()
						.equals(castOther.getField())))
				&& ((this.getFieldNum() == castOther.getFieldNum()) || (this
						.getFieldNum() != null
						&& castOther.getFieldNum() != null && this
						.getFieldNum().equals(castOther.getFieldNum())))
				&& ((this.getTicketDateBegin() == castOther
						.getTicketDateBegin()) || (this.getTicketDateBegin() != null
						&& castOther.getTicketDateBegin() != null && this
						.getTicketDateBegin().equals(
								castOther.getTicketDateBegin())))
				&& ((this.getTicketDateEnd() == castOther.getTicketDateEnd()) || (this
						.getTicketDateEnd() != null
						&& castOther.getTicketDateEnd() != null && this
						.getTicketDateEnd()
						.equals(castOther.getTicketDateEnd())))
				&& ((this.getTotal() == castOther.getTotal()) || (this
						.getTotal() != null
						&& castOther.getTotal() != null && this.getTotal()
						.equals(castOther.getTotal())))
				&& ((this.getSaleTicketType() == castOther.getSaleTicketType()) || (this
						.getSaleTicketType() != null
						&& castOther.getSaleTicketType() != null && this
						.getSaleTicketType().equals(
								castOther.getSaleTicketType())))
				&& ((this.getPerMoney() == castOther.getPerMoney()) || (this
						.getPerMoney() != null
						&& castOther.getPerMoney() != null && this
						.getPerMoney().equals(castOther.getPerMoney())))
				&& ((this.getTicketEffectiveDate() == castOther
						.getTicketEffectiveDate()) || (this
						.getTicketEffectiveDate() != null
						&& castOther.getTicketEffectiveDate() != null && this
						.getTicketEffectiveDate().equals(
								castOther.getTicketEffectiveDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTicketId() == null ? 0 : this.getTicketId().hashCode());
		result = 37 * result
				+ (getField() == null ? 0 : this.getField().hashCode());
		result = 37 * result
				+ (getFieldNum() == null ? 0 : this.getFieldNum().hashCode());
		result = 37
				* result
				+ (getTicketDateBegin() == null ? 0 : this.getTicketDateBegin()
						.hashCode());
		result = 37
				* result
				+ (getTicketDateEnd() == null ? 0 : this.getTicketDateEnd()
						.hashCode());
		result = 37 * result
				+ (getTotal() == null ? 0 : this.getTotal().hashCode());
		result = 37
				* result
				+ (getSaleTicketType() == null ? 0 : this.getSaleTicketType()
						.hashCode());
		result = 37 * result
				+ (getPerMoney() == null ? 0 : this.getPerMoney().hashCode());
		result = 37
				* result
				+ (getTicketEffectiveDate() == null ? 0 : this
						.getTicketEffectiveDate().hashCode());
		return result;
	}
	@Column(name = "mini")
	public String getMini() {
		return mini;
	}

	public void setMini(String mini) {
		this.mini = mini;
	}

}
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
 * SaleTicketFormId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Sale_Ticket_Form", schema = "dbo")
public class SaleTicketForm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ticketId;
	private String ticketType;
	private Double ticketPrice;
	private Timestamp ticketDateSold;
	private Timestamp ticketEffectiveDate;
	private String ticketEntertainment;
	private String ticketIsUsed;
	private Integer ticketKioskSold;
	private String ticketSalorSold;
	private String ticketJiezhang;
	private String saleTicketType;
	private String checkOut;
	private Timestamp returnEffectiveDate;

	// Constructors

	/** default constructor */
	public SaleTicketForm() {
	}

	/** minimal constructor */
	public SaleTicketForm(Integer id, String ticketId, String ticketType,
			Double ticketPrice, Timestamp ticketDateSold,
			Timestamp ticketEffectiveDate) {
		this.id = id;
		this.ticketId = ticketId;
		this.ticketType = ticketType;
		this.ticketPrice = ticketPrice;
		this.ticketDateSold = ticketDateSold;
		this.ticketEffectiveDate = ticketEffectiveDate;
	}

	/** full constructor */
	public SaleTicketForm(Integer id, String ticketId, String ticketType,
			Double ticketPrice, Timestamp ticketDateSold,
			Timestamp ticketEffectiveDate, String ticketEntertainment,
			String ticketIsUsed, Integer ticketKioskSold,
			String ticketSalorSold, String ticketJiezhang,
			String saleTicketType, String checkOut,
			Timestamp returnEffectiveDate) {
		this.id = id;
		this.ticketId = ticketId;
		this.ticketType = ticketType;
		this.ticketPrice = ticketPrice;
		this.ticketDateSold = ticketDateSold;
		this.ticketEffectiveDate = ticketEffectiveDate;
		this.ticketEntertainment = ticketEntertainment;
		this.ticketIsUsed = ticketIsUsed;
		this.ticketKioskSold = ticketKioskSold;
		this.ticketSalorSold = ticketSalorSold;
		this.ticketJiezhang = ticketJiezhang;
		this.saleTicketType = saleTicketType;
		this.checkOut = checkOut;
		this.returnEffectiveDate = returnEffectiveDate;
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

	@Column(name = "ticket_type", nullable = false, length = 20)
	public String getTicketType() {
		return this.ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	@Column(name = "ticket_price", nullable = false, precision = 53, scale = 0)
	public Double getTicketPrice() {
		return this.ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Column(name = "ticket_date_sold", nullable = false, length = 23)
	public Timestamp getTicketDateSold() {
		return this.ticketDateSold;
	}

	public void setTicketDateSold(Timestamp ticketDateSold) {
		this.ticketDateSold = ticketDateSold;
	}

	@Column(name = "ticket_effective_date", nullable = false, length = 23)
	public Timestamp getTicketEffectiveDate() {
		return this.ticketEffectiveDate;
	}

	public void setTicketEffectiveDate(Timestamp ticketEffectiveDate) {
		this.ticketEffectiveDate = ticketEffectiveDate;
	}

	@Column(name = "ticket_entertainment", length = 50)
	public String getTicketEntertainment() {
		return this.ticketEntertainment;
	}

	public void setTicketEntertainment(String ticketEntertainment) {
		this.ticketEntertainment = ticketEntertainment;
	}

	@Column(name = "ticket_is_used", length = 5)
	public String getTicketIsUsed() {
		return this.ticketIsUsed;
	}

	public void setTicketIsUsed(String ticketIsUsed) {
		this.ticketIsUsed = ticketIsUsed;
	}

	@Column(name = "ticket_kiosk_sold")
	public Integer getTicketKioskSold() {
		return this.ticketKioskSold;
	}

	public void setTicketKioskSold(Integer ticketKioskSold) {
		this.ticketKioskSold = ticketKioskSold;
	}

	@Column(name = "ticket_salor_sold", length = 50)
	public String getTicketSalorSold() {
		return this.ticketSalorSold;
	}

	public void setTicketSalorSold(String ticketSalorSold) {
		this.ticketSalorSold = ticketSalorSold;
	}

	@Column(name = "ticket_jiezhang", length = 50)
	public String getTicketJiezhang() {
		return this.ticketJiezhang;
	}

	public void setTicketJiezhang(String ticketJiezhang) {
		this.ticketJiezhang = ticketJiezhang;
	}

	@Column(name = "sale_ticket_type", length = 50)
	public String getSaleTicketType() {
		return this.saleTicketType;
	}

	public void setSaleTicketType(String saleTicketType) {
		this.saleTicketType = saleTicketType;
	}

	@Column(name = "check_out")
	public String getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	@Column(name = "return_effective_date", length = 23)
	public Timestamp getReturnEffectiveDate() {
		return this.returnEffectiveDate;
	}

	public void setReturnEffectiveDate(Timestamp returnEffectiveDate) {
		this.returnEffectiveDate = returnEffectiveDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SaleTicketForm))
			return false;
		SaleTicketForm castOther = (SaleTicketForm) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTicketId() == castOther.getTicketId()) || (this
						.getTicketId() != null
						&& castOther.getTicketId() != null && this
						.getTicketId().equals(castOther.getTicketId())))
				&& ((this.getTicketType() == castOther.getTicketType()) || (this
						.getTicketType() != null
						&& castOther.getTicketType() != null && this
						.getTicketType().equals(castOther.getTicketType())))
				&& ((this.getTicketPrice() == castOther.getTicketPrice()) || (this
						.getTicketPrice() != null
						&& castOther.getTicketPrice() != null && this
						.getTicketPrice().equals(castOther.getTicketPrice())))
				&& ((this.getTicketDateSold() == castOther.getTicketDateSold()) || (this
						.getTicketDateSold() != null
						&& castOther.getTicketDateSold() != null && this
						.getTicketDateSold().equals(
								castOther.getTicketDateSold())))
				&& ((this.getTicketEffectiveDate() == castOther
						.getTicketEffectiveDate()) || (this
						.getTicketEffectiveDate() != null
						&& castOther.getTicketEffectiveDate() != null && this
						.getTicketEffectiveDate().equals(
								castOther.getTicketEffectiveDate())))
				&& ((this.getTicketEntertainment() == castOther
						.getTicketEntertainment()) || (this
						.getTicketEntertainment() != null
						&& castOther.getTicketEntertainment() != null && this
						.getTicketEntertainment().equals(
								castOther.getTicketEntertainment())))
				&& ((this.getTicketIsUsed() == castOther.getTicketIsUsed()) || (this
						.getTicketIsUsed() != null
						&& castOther.getTicketIsUsed() != null && this
						.getTicketIsUsed().equals(castOther.getTicketIsUsed())))
				&& ((this.getTicketKioskSold() == castOther
						.getTicketKioskSold()) || (this.getTicketKioskSold() != null
						&& castOther.getTicketKioskSold() != null && this
						.getTicketKioskSold().equals(
								castOther.getTicketKioskSold())))
				&& ((this.getTicketSalorSold() == castOther
						.getTicketSalorSold()) || (this.getTicketSalorSold() != null
						&& castOther.getTicketSalorSold() != null && this
						.getTicketSalorSold().equals(
								castOther.getTicketSalorSold())))
				&& ((this.getTicketJiezhang() == castOther.getTicketJiezhang()) || (this
						.getTicketJiezhang() != null
						&& castOther.getTicketJiezhang() != null && this
						.getTicketJiezhang().equals(
								castOther.getTicketJiezhang())))
				&& ((this.getSaleTicketType() == castOther.getSaleTicketType()) || (this
						.getSaleTicketType() != null
						&& castOther.getSaleTicketType() != null && this
						.getSaleTicketType().equals(
								castOther.getSaleTicketType())))
				&& ((this.getCheckOut() == castOther.getCheckOut()) || (this
						.getCheckOut() != null
						&& castOther.getCheckOut() != null && this
						.getCheckOut().equals(castOther.getCheckOut())))
				&& ((this.getReturnEffectiveDate() == castOther
						.getReturnEffectiveDate()) || (this
						.getReturnEffectiveDate() != null
						&& castOther.getReturnEffectiveDate() != null && this
						.getReturnEffectiveDate().equals(
								castOther.getReturnEffectiveDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTicketId() == null ? 0 : this.getTicketId().hashCode());
		result = 37
				* result
				+ (getTicketType() == null ? 0 : this.getTicketType()
						.hashCode());
		result = 37
				* result
				+ (getTicketPrice() == null ? 0 : this.getTicketPrice()
						.hashCode());
		result = 37
				* result
				+ (getTicketDateSold() == null ? 0 : this.getTicketDateSold()
						.hashCode());
		result = 37
				* result
				+ (getTicketEffectiveDate() == null ? 0 : this
						.getTicketEffectiveDate().hashCode());
		result = 37
				* result
				+ (getTicketEntertainment() == null ? 0 : this
						.getTicketEntertainment().hashCode());
		result = 37
				* result
				+ (getTicketIsUsed() == null ? 0 : this.getTicketIsUsed()
						.hashCode());
		result = 37
				* result
				+ (getTicketKioskSold() == null ? 0 : this.getTicketKioskSold()
						.hashCode());
		result = 37
				* result
				+ (getTicketSalorSold() == null ? 0 : this.getTicketSalorSold()
						.hashCode());
		result = 37
				* result
				+ (getTicketJiezhang() == null ? 0 : this.getTicketJiezhang()
						.hashCode());
		result = 37
				* result
				+ (getSaleTicketType() == null ? 0 : this.getSaleTicketType()
						.hashCode());
		result = 37 * result
				+ (getCheckOut() == null ? 0 : this.getCheckOut().hashCode());
		result = 37
				* result
				+ (getReturnEffectiveDate() == null ? 0 : this
						.getReturnEffectiveDate().hashCode());
		return result;
	}

}
package com.scu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FieldMoneyXihua entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Field_Money_Xihua", schema = "dbo", catalog = "beihuTicket")
public class FieldMoneyXihua implements java.io.Serializable {

	// Fields

	private Integer id;
	private String field;
	private Double perMoney;
	private Integer perTime;
	private Double extraMoney;
	private Integer extraTime;
	private Double APerMoney;
	private Integer APerTime;
	private Double AExtraMoney;
	private Integer AExtraTime;
	private Double NPerMoney;
	private Integer NPerTime;
	private Double NExtraMoney;
	private Integer NExtraTime;
	private Integer fieldType;
	private Double onceMoney;
	private Double deposit;
	private Double lowestMoney;

	// Constructors

	/** default constructor */
	public FieldMoneyXihua() {
	}

	/** full constructor */
	public FieldMoneyXihua(String field, Double perMoney, Integer perTime,
			Double extraMoney, Integer extraTime, Double APerMoney,
			Integer APerTime, Double AExtraMoney, Integer AExtraTime,
			Double NPerMoney, Integer NPerTime, Double NExtraMoney,
			Integer NExtraTime, Integer fieldType, Double onceMoney,
			Double deposit) {
		this.field = field;
		this.perMoney = perMoney;
		this.perTime = perTime;
		this.extraMoney = extraMoney;
		this.extraTime = extraTime;
		this.APerMoney = APerMoney;
		this.APerTime = APerTime;
		this.AExtraMoney = AExtraMoney;
		this.AExtraTime = AExtraTime;
		this.NPerMoney = NPerMoney;
		this.NPerTime = NPerTime;
		this.NExtraMoney = NExtraMoney;
		this.NExtraTime = NExtraTime;
		this.fieldType = fieldType;
		this.onceMoney = onceMoney;
		this.deposit = deposit;
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

	@Column(name = "field", length = 50)
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Column(name = "per_money", precision = 53, scale = 0)
	public Double getPerMoney() {
		return this.perMoney;
	}

	public void setPerMoney(Double perMoney) {
		this.perMoney = perMoney;
	}

	@Column(name = "per_time")
	public Integer getPerTime() {
		return this.perTime;
	}

	public void setPerTime(Integer perTime) {
		this.perTime = perTime;
	}

	@Column(name = "extra_money", precision = 53, scale = 0)
	public Double getExtraMoney() {
		return this.extraMoney;
	}

	public void setExtraMoney(Double extraMoney) {
		this.extraMoney = extraMoney;
	}

	@Column(name = "extra_time")
	public Integer getExtraTime() {
		return this.extraTime;
	}

	public void setExtraTime(Integer extraTime) {
		this.extraTime = extraTime;
	}

	@Column(name = "a_per_money", precision = 53, scale = 0)
	public Double getAPerMoney() {
		return this.APerMoney;
	}

	public void setAPerMoney(Double APerMoney) {
		this.APerMoney = APerMoney;
	}

	@Column(name = "a_per_time")
	public Integer getAPerTime() {
		return this.APerTime;
	}

	public void setAPerTime(Integer APerTime) {
		this.APerTime = APerTime;
	}

	@Column(name = "a_extra_money", precision = 53, scale = 0)
	public Double getAExtraMoney() {
		return this.AExtraMoney;
	}

	public void setAExtraMoney(Double AExtraMoney) {
		this.AExtraMoney = AExtraMoney;
	}

	@Column(name = "a_extra_time")
	public Integer getAExtraTime() {
		return this.AExtraTime;
	}

	public void setAExtraTime(Integer AExtraTime) {
		this.AExtraTime = AExtraTime;
	}

	@Column(name = "n_per_money", precision = 53, scale = 0)
	public Double getNPerMoney() {
		return this.NPerMoney;
	}

	public void setNPerMoney(Double NPerMoney) {
		this.NPerMoney = NPerMoney;
	}

	@Column(name = "n_per_time")
	public Integer getNPerTime() {
		return this.NPerTime;
	}

	public void setNPerTime(Integer NPerTime) {
		this.NPerTime = NPerTime;
	}

	@Column(name = "n_extra_money", precision = 53, scale = 0)
	public Double getNExtraMoney() {
		return this.NExtraMoney;
	}

	public void setNExtraMoney(Double NExtraMoney) {
		this.NExtraMoney = NExtraMoney;
	}

	@Column(name = "n_extra_time")
	public Integer getNExtraTime() {
		return this.NExtraTime;
	}

	public void setNExtraTime(Integer NExtraTime) {
		this.NExtraTime = NExtraTime;
	}

	@Column(name = "field_type")
	public Integer getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "once_money", precision = 53, scale = 0)
	public Double getOnceMoney() {
		return this.onceMoney;
	}

	public void setOnceMoney(Double onceMoney) {
		this.onceMoney = onceMoney;
	}

	@Column(name = "deposit", precision = 53, scale = 0)
	public Double getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	@Column(name = "lowest_money", precision = 53, scale = 0)
	public Double getLowestMoney() {
		return lowestMoney;
	}

	public void setLowestMoney(Double lowestMoney) {
		this.lowestMoney = lowestMoney;
	}

	
	
}
package com.scu.dto;

// default package

import java.sql.Timestamp;

import com.scu.bean.PlayDetailMoney;

/**
 * PlayDetail entity. @author MyEclipse Persistence Tools
 */
public class PlayDetailDto implements java.io.Serializable {

	// Fields

	public Integer id;
	public String ticketId;
	public String field;
	public String fieldNotEnd;
	public Integer fieldNum;
	public Timestamp ticketDateSold;
	public Timestamp ticketDateReturn;
	public Timestamp ticketDateBegin;
	public Timestamp ticketDateEnd;
	public String ticketStatus;
	public String msg;
	public Double total;//此卡所有消费金额
	public Double totalOfCurrentField;//当前游玩项目消费金额
	public Double totalOfOtherField;//当前游玩项目消费金额
	public Double costOfCurrent;//当前次消费金额
	public Double ticketPrice;
	public Double perMoney;
	public Double restMoney;
	public Double lowestMoney;
	public String mini;
	public String countOfCostByCi;//总共按次扣费次数
	public String countToCostByCi;//当前要扣费次数

	// Constructors

	/** default constructor */
	public PlayDetailDto() {
	}

	/** minimal constructor */
	public PlayDetailDto(String ticketId, Timestamp ticketDateBegin) {
		this.ticketId = ticketId;
		this.ticketDateBegin = ticketDateBegin;
	}

	/** full constructor */
	public PlayDetailDto(String ticketId, String field, Integer fieldNum,
			Timestamp ticketDateBegin, Timestamp ticketDateEnd) {
		this.ticketId = ticketId;
		this.field = field;
		this.fieldNum = fieldNum;
		this.ticketDateBegin = ticketDateBegin;
		this.ticketDateEnd = ticketDateEnd;
	}
	
	/**
	 * 同步playDetailMoney的数据到playDetailDto
	 * @param playDetailMoney
	 */
	public void updateData(PlayDetailMoney playDetailMoney){
		id = playDetailMoney.getId();
		ticketId = playDetailMoney.getTicketId();
		fieldNum = playDetailMoney.getFieldNum();
		ticketDateBegin = playDetailMoney.getTicketDateBegin();
		ticketDateEnd = playDetailMoney.getTicketDateEnd();
		mini = playDetailMoney.getMini();
		//total = playDetailMoney.getTotal()==null?total:playDetailMoney.getTotal()+total;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Integer getFieldNum() {
		return this.fieldNum;
	}

	public void setFieldNum(Integer fieldNum) {
		this.fieldNum = fieldNum;
	}

	public Timestamp getTicketDateBegin() {
		return this.ticketDateBegin;
	}

	public void setTicketDateBegin(Timestamp ticketDateBegin) {
		this.ticketDateBegin = ticketDateBegin;
	}

	public Timestamp getTicketDateEnd() {
		return this.ticketDateEnd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setTicketDateEnd(Timestamp ticketDateEnd) {
		this.ticketDateEnd = ticketDateEnd;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getRestMoney() {
		return restMoney;
	}

	public void setRestMoney(Double restMoney) {
		this.restMoney = restMoney;
	}

	public String getMini() {
		return mini;
	}

	public void setMini(String mini) {
		this.mini = mini;
	}

	public Double getPerMoney() {
		return perMoney;
	}

	public void setPerMoney(Double perMoney) {
		this.perMoney = perMoney;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getFieldNotEnd() {
		return fieldNotEnd;
	}

	public void setFieldNotEnd(String fieldNotEnd) {
		this.fieldNotEnd = fieldNotEnd;
	}

	public Double getLowestMoney() {
		return lowestMoney;
	}

	public void setLowestMoney(Double lowestMoney) {
		this.lowestMoney = lowestMoney;
	}

	public Timestamp getTicketDateSold() {
		return ticketDateSold;
	}

	public void setTicketDateSold(Timestamp ticketDateSold) {
		this.ticketDateSold = ticketDateSold;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Timestamp getTicketDateReturn() {
		return ticketDateReturn;
	}

	public void setTicketDateReturn(Timestamp ticketDateReturn) {
		this.ticketDateReturn = ticketDateReturn;
	}

	public Double getTotalOfCurrentField() {
		return totalOfCurrentField;
	}

	public void setTotalOfCurrentField(Double totalOfCurrentField) {
		this.totalOfCurrentField = totalOfCurrentField;
	}

	public Double getTotalOfOtherField() {
		return totalOfOtherField;
	}

	public void setTotalOfOtherField(Double totalOfOtherField) {
		this.totalOfOtherField = totalOfOtherField;
	}

	public Double getCostOfCurrent() {
		return costOfCurrent;
	}

	public void setCostOfCurrent(Double costOfCurrent) {
		this.costOfCurrent = costOfCurrent;
	}

	public String getCountOfCostByCi() {
		return countOfCostByCi;
	}

	public void setCountOfCostByCi(String countOfCostByCi) {
		this.countOfCostByCi = countOfCostByCi;
	}

	public String getCountToCostByCi() {
		return countToCostByCi;
	}

	public void setCountToCostByCi(String countToCostByCi) {
		this.countToCostByCi = countToCostByCi;
	}

}
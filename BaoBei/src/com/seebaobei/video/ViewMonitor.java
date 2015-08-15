package com.seebaobei.video;

import java.io.Serializable;


/**
 * 监控视图
 * @author Chencong
 *
 */
public class ViewMonitor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1310459542429463420L;
	
	/**
	 * 监控id
	 */
	private Long monitorId;
	/**
	 * 监控名称
	 */
	private String monitorName;
	/**
	 * 监控sn号
	 */
	private String sn;
	/**
	 * 观看时间段
	 */
	private String timeQuantum;
	/**
	 * 幼儿园id
	 */
	private Long kindergartenId;
	/**
	 * 幼儿园名称
	 */
	private String kindergartenName;
	/**
	 * 区域id
	 */
	private Long areaId;
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 父区域id
	 */
	private Long pkaId;
	
	
	
	public ViewMonitor() {
	}
	public Long getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}
	public String getMonitorName() {
		return monitorName;
	}
	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getTimeQuantum() {
		return timeQuantum;
	}
	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	public Long getKindergartenId() {
		return kindergartenId;
	}
	public void setKindergartenId(Long kindergartenId) {
		this.kindergartenId = kindergartenId;
	}
	public String getKindergartenName() {
		return kindergartenName;
	}
	public void setKindergartenName(String kindergartenName) {
		this.kindergartenName = kindergartenName;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getPkaId() {
		return pkaId;
	}
	public void setPkaId(Long pkaId) {
		this.pkaId = pkaId;
	}
}

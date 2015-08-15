package com.ggy.baby.monitorManage.pageModel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.ggy.baby.login.model.Users;
import com.ggy.baby.monitorManage.model.Kindergarten;
import com.ggy.baby.monitorManage.model.KindergartenArea;
import com.ggy.baby.monitorManage.model.Monitor;

/**
 * 监控管理In参数
 * @author Chencong
 *
 */
public class MonitorManageIn {
	/**
	 * 幼儿园对象
	 */
	private Kindergarten kindergarten = new Kindergarten();
	/**
	 * 监控
	 */
	private Monitor monitor = new Monitor();
	/**
	 * 区域对象
	 */
	private KindergartenArea area = new KindergartenArea();
	/**
	 * 子区域名字
	 */
	private KindergartenArea carea = new KindergartenArea();
	
	/**
	 * 页数
	 */
	private Integer page;
	/**
	 * 搜索内容
	 */
	private String search;
	private String ids;
	
	private Users users =new Users();
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) throws UnsupportedEncodingException {
		this.search = URLDecoder.decode(search,"UTF-8");
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

	public Kindergarten getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(Kindergarten kindergarten) {
		this.kindergarten = kindergarten;
	}

	public KindergartenArea getArea() {
		return area;
	}

	public void setArea(KindergartenArea area) {
		this.area = area;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public KindergartenArea getCarea() {
		return carea;
	}

	public void setCarea(KindergartenArea carea) {
		this.carea = carea;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}

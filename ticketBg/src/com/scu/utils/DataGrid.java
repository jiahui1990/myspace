package com.scu.utils;

import java.util.List;

/**
 * easyui的datagrid模型
 * 
 * @author jove
 * 
 */
public class DataGrid implements java.io.Serializable {

	private int total;// 总记录数
	private List rows;// 每行记录
	private List footer;



	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}

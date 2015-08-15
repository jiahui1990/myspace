package javacommon.base.model;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

public class BaseModel {
	private Integer page;
	private Integer rows;
	/**
	 * 时间返回查询
	 * 格式：查询字段，起始时间，结束时间setTime("createtime,2012-12-09 12:22:00,2014-12-04 13:23:45")
	 */
	private String time;
	/**
	 * 排序查询的查询条件 
	 * 格式：排序字段1 asc/desc,排序字段2 asc/desc,排序字段3 asc/desc
	 * 例如：username asc,age desc
	 */
	private String orderString;
	/**
	 * 金额返回查询
	 * 格式：查询字段，起始金额，结束金额setMoney("money,6767.3d,765.0d") ("money,more,765.0d")
	 */
	private String moneyBucket;
	/**
	 * or条件
	 * 格式 "字段1,字段2,字段3..."
	 * 例如"username,nickname,keyword" 当对象中 username属性，nickname属性，keyword属性的值都为'test'
	 * 构建出来的hql为 (o.username='test' or o.nickname='test' or o.keyword='test')
	 */
	private String orString;
	/**
	 * 全局关键字查询 为空字段全部like 此属性值
	 * 需 generateParamsAndLikeSearchContent 方法调用生效
	 */
	private String search_content;
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getOrderString() {
		return orderString;
	}
	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMoneyBucket() {
		return moneyBucket;
	}
	public void setMoneyBucket(String moneyBucket) {
		this.moneyBucket = moneyBucket;
	}
	public String getOrString() {
		return orString;
	}
	public void setOrString(String orString) {
		this.orString = orString;
	}
	public String getSearch_content() {
		return search_content;
	}
	public void setSearch_content(String search_content) {
		/*byte[] byte1;
		try {
			if(StringUtils.isNotEmpty(search_content)){
				byte1 = search_content.getBytes("ISO-8859-1");
				this.search_content = new String(byte1, "UTF-8");
				return;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		this.search_content = search_content;
	}
	
	
	public void search_time(String startTime, String endTime, String time_content){
		String time = null;
		
		if(StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)){
			this.time = null;
			return;
		}
		
		if(StringUtils.isEmpty(startTime)){
			time = time_content + ",less,"+ endTime;
		}
		if(StringUtils.isEmpty(endTime)){
			time = time_content +",more," + startTime ;
		}
		
		if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
			time = time_content + "," + startTime + "," + endTime;
		}
		this.time = time;
	}
}

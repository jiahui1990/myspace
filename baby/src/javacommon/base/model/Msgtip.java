package javacommon.base.model;

import java.util.Date;



/**
 * 
 * @Description: TODO(错误信息提示对象，与数据库表对应)
 * @author qinchuan
 * @version V1.0
 * @date 2014-3-20 下午2:33:34
 */
public class Msgtip implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3810783264493670873L;
	/**
	 * 错误信息主键id
	 */
	private Long id;
	/**
	 * 错误信息编码
	 */
	private String name;
	/**
	 * 错误信息值
	 */
	private String returnmsg;
	/**
	 * 错误信息图标
	 */
	private String returntco;
	/**
	 * 错误跳转页面
	 */
	private String returnurl;
	/**
	 * 创建时间
	 */
	private String createtime;
	/**
	 * 是否保存日志标识 
	 */
	private GlobalVariable globalVariableByIslog;
	
	/**
	 * 正确或者错误
	 */
	private GlobalVariable globalVariableByIssuccess;
	

	

	// Constructors

	/** default constructor */
	public Msgtip() {
	}

	/** minimal constructor */
	public Msgtip(Long id, String name, String returnmsg) {
		this.id = id;
		this.name = name;
		this.returnmsg = returnmsg;
	}

	/** full constructor */
	public Msgtip(Long id, GlobalVariable globalVariableByIslog,
			GlobalVariable globalVariableByIssuccess, String name,
			String returnmsg, String returntco, String returnurl,
			String createtime) {
		this.id = id;
		this.globalVariableByIslog = globalVariableByIslog;
		this.globalVariableByIssuccess = globalVariableByIssuccess;
		this.name = name;
		this.returnmsg = returnmsg;
		this.returntco = returntco;
		this.returnurl = returnurl;
		this.createtime = createtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnmsg() {
		return returnmsg;
	}

	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}

	public String getReturntco() {
		return returntco;
	}

	public void setReturntco(String returntco) {
		this.returntco = returntco;
	}

	public String getReturnurl() {
		return returnurl;
	}

	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public GlobalVariable getGlobalVariableByIslog() {
		return globalVariableByIslog;
	}

	public void setGlobalVariableByIslog(GlobalVariable globalVariableByIslog) {
		this.globalVariableByIslog = globalVariableByIslog;
	}

	public GlobalVariable getGlobalVariableByIssuccess() {
		return globalVariableByIssuccess;
	}

	public void setGlobalVariableByIssuccess(GlobalVariable globalVariableByIssuccess) {
		this.globalVariableByIssuccess = globalVariableByIssuccess;
	}

	// Property accessors

	
	
	

}
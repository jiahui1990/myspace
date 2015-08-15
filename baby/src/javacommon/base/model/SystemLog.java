package javacommon.base.model;

import java.util.Date;


/**
 * 
 * @Description: TODO(系统日志对象，与数据库表对应)
 * @author qinchuan
 * @version V1.0
 * @date 2014-3-20 下午2:32:09
 */
public class SystemLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4778850596172459417L;
	// Fields
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 关联错误信息提示
	 */
	private Msgtip msgtip;
	/**
	 * 业务名称,保存业务字典表的主键
	 */
	private Long action;
	/**
	 * 操作时间
	 */
	private Date operationtime;
	/**
	 * 操作描述
	 */
	private String descripetion;
	/**
	 * 操作IP
	 */
	private String ip;
	/**
	 * 用户名
	 */
	private String username;

	// Constructors

	/** default constructor */
	public SystemLog() {
	}

	/** minimal constructor */
	public SystemLog(Long id, Msgtip msgtip, String username) {
		this.id = id;
		this.msgtip = msgtip;
		this.username = username;
	}

	/** full constructor */
	public SystemLog(Long id, Msgtip msgtip, Long action, Date operationtime,
			String descripetion, String ip, String username) {
		super();
		this.id = id;
		this.msgtip = msgtip;
		this.action = action;
		this.operationtime = operationtime;
		this.descripetion = descripetion;
		this.ip = ip;
		this.username = username;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	

	public void setId(Long id) {
		this.id = id;
	}

	public Msgtip getMsgtip() {
		return this.msgtip;
	}

	public void setMsgtip(Msgtip msgtip) {
		this.msgtip = msgtip;
	}
	public Long getAction() {
		return action;
	}

	public void setAction(Long action) {
		this.action = action;
	}

	public Date getOperationtime() {
		return this.operationtime;
	}

	public void setOperationtime(Date operationtime) {
		this.operationtime = operationtime;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescripetion() {
		return descripetion;
	}

	public void setDescripetion(String descripetion) {
		this.descripetion = descripetion;
	}
	
}
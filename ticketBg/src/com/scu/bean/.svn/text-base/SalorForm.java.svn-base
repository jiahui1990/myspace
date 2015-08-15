package com.scu.bean;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SalorForm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Salor_Form", schema = "dbo")
public class SalorForm implements java.io.Serializable {

	// Fields

	private String userAccount;
	private String userPassword;
	private String userStatus;
	private Integer userPermissions;

	// Constructors

	/** default constructor */
	public SalorForm() {
	}

	/** full constructor */
	public SalorForm(String userPassword, String userStatus,
			Integer userPermissions) {
		this.userPassword = userPassword;
		this.userStatus = userStatus;
		this.userPermissions = userPermissions;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "user_account", unique = true, nullable = false, length = 16)
	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "user_password", nullable = false, length = 20)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_status", nullable = false, length = 8)
	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "user_permissions", nullable = false)
	public Integer getUserPermissions() {
		return this.userPermissions;
	}

	public void setUserPermissions(Integer userPermissions) {
		this.userPermissions = userPermissions;
	}

}
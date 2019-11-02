package com.nondirectional.imagewall.bean;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private Integer id;
	
	@NotEmpty(message = "UN_EPT")
	@Size(min = 1,max = 20,message = "UN_LEN")
	private String username;
	
	@NotEmpty(message = "PWD_EPT")
	@Size(min = 6,max = 20,message = "PWD_LEN")
	private String password;
	private Date registTime;
	private Date lastLoginTime;
	
	@NotEmpty(message = "PWDL2_EPT")
	@Size(min = 6,max = 20,message = "PWDL2_LEN")
	private String secondryPassword;
	private Integer wealth;
	private ArrayList<Integer> manageGroup;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSecondryPassword() {
		return secondryPassword;
	}

	public void setSecondryPassword(String secondryPassword) {
		this.secondryPassword = secondryPassword;
	}

	public Integer getWealth() {
		return wealth;
	}

	public void setWealth(Integer wealth) {
		this.wealth = wealth;
	}

	public ArrayList<Integer> getManageGroup() {
		return manageGroup;
	}

	public void setManageGroup(ArrayList<Integer> manageGroup) {
		this.manageGroup = manageGroup;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", registTime=" + registTime
				+ ", lastLoginTime=" + lastLoginTime + ", secondryPassword=" + secondryPassword + ", wealth=" + wealth
				+ ", manageGroup=" + manageGroup + "]";
	}

}

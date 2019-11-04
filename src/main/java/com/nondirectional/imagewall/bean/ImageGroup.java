package com.nondirectional.imagewall.bean;

import java.util.Date;

public class ImageGroup {
	private Integer id;
	private String description;
	private Integer laudCount;
	private Integer badCount;
	private Date createTime;
	private Integer createUser;
	private String groupName;
	public ImageGroup(String groupName, Integer userId, String description) {
		this.groupName = groupName;
		this.createUser = userId;
		this.description = description;
	}
	
	public ImageGroup() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getLaudCount() {
		return laudCount;
	}
	public void setLaudCount(Integer laudCount) {
		this.laudCount = laudCount;
	}
	public Integer getBadCount() {
		return badCount;
	}
	public void setBadCount(Integer badCount) {
		this.badCount = badCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}

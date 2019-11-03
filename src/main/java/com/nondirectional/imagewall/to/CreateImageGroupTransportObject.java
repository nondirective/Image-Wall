package com.nondirectional.imagewall.to;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateImageGroupTransportObject {
	@NotEmpty(message="UNLOGIN")
	private Integer userId;
	@NotEmpty(message="GROUP_NAME_EMPTY")
	private String groupName;
	private String description;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

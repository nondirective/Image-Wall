package com.nondirectional.imagewall.bean;

import java.util.Date;

public class Image {

	private Integer id;
	private String imageUrl;
	private Integer laudCount;
	private Integer badCount;
	private Boolean isVideo;
	private Date uploadTime;
	private Integer uploadUser;
	private Integer parentGroup;
	
	public Image(){}
	
	public Image(String relativePath, Integer userId, Integer parentGroup,Boolean isVideo) {
		this.imageUrl = relativePath;
		this.uploadUser = userId;
		this.parentGroup = parentGroup;
		this.isVideo = isVideo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public Boolean getIsVideo() {
		return isVideo;
	}
	public void setIsVideo(Boolean isVideo) {
		this.isVideo = isVideo;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Integer getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(Integer uploadUser) {
		this.uploadUser = uploadUser;
	}
	public Integer getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(Integer parentGroup) {
		this.parentGroup = parentGroup;
	}
}

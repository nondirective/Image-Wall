package com.nondirectional.imagewall.to;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadTransportObject {
	private MultipartFile[] files;
	private Integer userId;
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(Integer parentGroup) {
		this.parentGroup = parentGroup;
	}
	private Integer parentGroup;

}

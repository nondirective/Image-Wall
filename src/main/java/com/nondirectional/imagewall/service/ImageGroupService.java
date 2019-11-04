package com.nondirectional.imagewall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nondirectional.imagewall.bean.ImageGroup;
import com.nondirectional.imagewall.dao.ImageGroupMapper;

@Service
public class ImageGroupService {
	@Autowired
	ImageGroupMapper imageGroupMapper;
	public Integer createImageGroup(String groupName,Integer userId,String description){
		ImageGroup imageGroup = new ImageGroup(groupName,userId,description);
		Integer groupId = imageGroupMapper.insertImageGroup(imageGroup);
		return groupId;
	}
	public ImageGroup getImageGroup(Integer groupId) {
		ImageGroup imageGroup = null;
		imageGroup = imageGroupMapper.selectImageGroupById(groupId);
		return imageGroup;
	}
}

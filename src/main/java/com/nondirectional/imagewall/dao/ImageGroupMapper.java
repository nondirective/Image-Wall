package com.nondirectional.imagewall.dao;

import com.nondirectional.imagewall.bean.ImageGroup;

public interface ImageGroupMapper {
	Integer insertImageGroup(ImageGroup imageGroup);
	ImageGroup selectImageGroupById(Integer groupId);
}

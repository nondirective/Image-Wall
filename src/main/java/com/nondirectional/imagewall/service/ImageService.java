package com.nondirectional.imagewall.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nondirectional.imagewall.bean.Image;
import com.nondirectional.imagewall.dao.ImageMapper;

@Service
public class ImageService {
	@Autowired
	ImageMapper imageMapper;

	public void publishImage(Image image) {
		image.setUploadTime(new Date());
		imageMapper.insertImage(image);
	}
}

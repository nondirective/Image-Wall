package com.nondirectional.imagewall.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nondirectional.imagewall.bean.Image;
import com.nondirectional.imagewall.service.ImageService;
import com.nondirectional.imagewall.to.ImageUploadTransportObject;

@Controller
public class ImageUploadController {
	@Autowired
	ImageService imageService;
	
	@RequestMapping("/imageUploadPage/{groupId}")
	public String testFileUpload(@PathVariable("groupId")Integer groupId,Map<String,Object>map) {
		map.put("groupId",groupId);
		return "imageupload";
	}

	private Long MAX_UPLOAD_SIZE = (long) (20 * 1024 * 1024);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_HH-mm-ss");

	/*
	 * .jpg contentType-->image/jpeg .mp4 contentType-->video/mp4
	 * windows�µ��ļ������������23:59:59�����������ַ���
	 */
	@RequestMapping("/imageUpload")
	public String imageUpload(ImageUploadTransportObject to, Map<String, Object> map, HttpServletRequest request) {
		// �жϵ����ļ��Ƿ񳬳���С���Ƽ��Ƿ�ΪĿ���ļ���ʽ
		for (MultipartFile file : to.getFiles()) {
			if (file.getSize() > MAX_UPLOAD_SIZE) {
				map.put("msg_fileupload", "�����ļ���С���ܳ���20M");
				// ?
				return "forward:/imageUploadPage";
			}

			if ((!file.getContentType().equals("image/jpeg")) && (!file.getContentType().equals("video/mp4"))) {
				map.put("msg_fileupload", "��֧��jpg��mp4�ļ�");
				return "forward:/imageUploadPage";
			}
		}

		/*
		 * �ļ��ϴ���Ϊ������ͬһ���ļ����д�Ź�����ļ���ʹ���ļ�����Ϊ����������ķ������������ļ��� Ϊ�����ļ�����ͬ��������֣�ʹ��UUID+ʱ�����Ϊ�ļ���
		 * ������Ҫע�⣡Windows�ļ�ϵͳ�������ļ����г�����21:22:23��ʽ���ַ����� ��ʱ����ķָ������ʹ�� : ��Ϊ�ָ���
		 */
		for (MultipartFile file : to.getFiles()) {
			String contentType = file.getContentType().equals("image/jpeg") ? ".jpg" : ".mp4";

			String rootPath = request.getServletContext().getRealPath("\\WEB-INF\\files\\");

			String childPath = String.valueOf((int) (Math.random() * 16)) + "\\"
					+ String.valueOf((int) (Math.random() * 16)) + "\\" + UUID.randomUUID().toString() + "_"
					+ dateFormat.format(new Date()) + contentType;

			// ���·��������������ʹ��
			String relativePath = "\\WEB-INF\\files\\" + childPath;

			try {
				// commons.io�µľ�̬��������ʳ�ļ����Ƶ�Ŀ��·����
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(rootPath + "\\" + childPath));
			} catch (Exception e) {
				map.put("msg_fileupload", "�ϴ��ļ�ʧ��");
				return "forward:/imageUploadPage";
			}

			imageService.publishImage(new Image(relativePath, to.getUserId(), to.getParentGroup(), contentType.equals(".jpg") ? false : true));
		}
		
		return "redirect:/imageGroupView/"+to.getParentGroup();
	}
}

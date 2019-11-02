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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	private Long MAX_UPLOAD_SIZE = (long) (20 * 1024 * 1024);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_HH-mm-ss");

	@RequestMapping("/imageUploadPage")
	public String testFileUpload() {
		return "fileupload";
	}

	/*
	 * .jpg contentType-->image/jpeg .mp4 contentType-->video/mp4
	 * windows�µ��ļ������������23:59:59�����������ַ���
	 */
	@RequestMapping("/imageUpload")
	public String imageUpload(@RequestParam("files") MultipartFile[] files, Map<String, Object> map,
			HttpServletRequest request) {
		for (MultipartFile file : files) {
			if (file.getSize() > MAX_UPLOAD_SIZE) {
				map.put("msg_fileupload", "�����ļ���С���ܳ���20M");
				return "forward:/imageUploadPage";
			}

			if ((!file.getContentType().equals("image/jpeg")) && (!file.getContentType().equals("video/mp4"))) {
				map.put("msg_fileupload", "��֧��jpg��mp4�ļ�");
				return "forward:/imageUploadPage";
			}
		}

		for (MultipartFile file : files) {
			String contentType = file.getContentType().equals("image/jpeg") ? ".jpg" : ".mp4";

			String path = request.getServletContext().getRealPath("/WEB-INF/files/")
					+ String.valueOf((int)(Math.random()*16)) + "\\"
					+ String.valueOf((int)(Math.random()*16)) + "\\"
					+ UUID.randomUUID().toString() + "_" 
					+ dateFormat.format(new Date()) + contentType;
			
			try {
				 FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
			} catch (Exception e) {
				map.put("msg_fileupload", "�ϴ��ļ�ʧ��");
				return "forward:/imageUploadPage";
			}
		}
		return "success";
	}
}

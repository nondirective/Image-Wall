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
	 * windows下的文件名不允许出现23:59:59类似这样的字符的
	 */
	@RequestMapping("/imageUpload")
	public String imageUpload(ImageUploadTransportObject to, Map<String, Object> map, HttpServletRequest request) {
		// 判断单个文件是否超出大小限制及是否为目标文件格式
		for (MultipartFile file : to.getFiles()) {
			if (file.getSize() > MAX_UPLOAD_SIZE) {
				map.put("msg_fileupload", "单个文件大小不能超过20M");
				// ?
				return "forward:/imageUploadPage";
			}

			if ((!file.getContentType().equals("image/jpeg")) && (!file.getContentType().equals("video/mp4"))) {
				map.put("msg_fileupload", "仅支持jpg与mp4文件");
				return "forward:/imageUploadPage";
			}
		}

		/*
		 * 文件上传，为避免在同一个文件夹中存放过多的文件，使用文件夹名为生成随机数的方法生成两级文件夹 为避免文件名相同的情况出现，使用UUID+时间戳作为文件名
		 * 这里需要注意！Windows文件系统不允许文件名中出现如21:22:23格式的字符串， 故时间戳的分割符不能使用 : 作为分隔符
		 */
		for (MultipartFile file : to.getFiles()) {
			String contentType = file.getContentType().equals("image/jpeg") ? ".jpg" : ".mp4";

			String rootPath = request.getServletContext().getRealPath("\\WEB-INF\\files\\");

			String childPath = String.valueOf((int) (Math.random() * 16)) + "\\"
					+ String.valueOf((int) (Math.random() * 16)) + "\\" + UUID.randomUUID().toString() + "_"
					+ dateFormat.format(new Date()) + contentType;

			// 相对路径，服务器访问使用
			String relativePath = "\\WEB-INF\\files\\" + childPath;

			try {
				// commons.io下的静态方法将零食文件复制到目标路径下
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(rootPath + "\\" + childPath));
			} catch (Exception e) {
				map.put("msg_fileupload", "上传文件失败");
				return "forward:/imageUploadPage";
			}

			imageService.publishImage(new Image(relativePath, to.getUserId(), to.getParentGroup(), contentType.equals(".jpg") ? false : true));
		}
		
		return "redirect:/imageGroupView/"+to.getParentGroup();
	}
}

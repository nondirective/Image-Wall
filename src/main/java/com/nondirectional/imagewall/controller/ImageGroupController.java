package com.nondirectional.imagewall.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nondirectional.imagewall.bean.ImageGroup;
import com.nondirectional.imagewall.service.ImageGroupService;
import com.nondirectional.imagewall.service.ImageService;
import com.nondirectional.imagewall.to.CreateImageGroupTransportObject;

@Controller
public class ImageGroupController {
	@Autowired
	ImageGroupService imageGroupService;
	
	@RequestMapping("/imageGroupView/{groupId}")
	public String imageGroupView(@PathVariable("groupId")Integer groupId,Map<String,Object>map) {
		ImageGroup imageGroup = imageGroupService.getImageGroup(groupId);
		map.put("imageGroup", imageGroup);
		return "imageGroup";
	}
	@RequestMapping(value= "/createImageGroupPage")
	public String createImageGroupPage() {
		return "createImageGroup";
	}
	@RequestMapping(value="/createImageGroup")
	public String createImageGroup(@Valid CreateImageGroupTransportObject to,BindingResult bindingResult,Map<String,Object> map) {
		for(ObjectError e:bindingResult.getAllErrors()) {
			if(e.getDefaultMessage().equals("UNLOGIN")) 
				return "unlogin";
			if(e.getDefaultMessage().equals("GROUP_NAME_EMPTY")) {
				map.put("msg_groupName","照片组名称不能为空");
				return "forward:/createImageGroupPage";
			}
		}
		Integer groupId = imageGroupService.createImageGroup(to.getGroupName(),to.getUserId(),to.getDescription());
		return "redirect:/imageGroupView?groupId="+groupId;
	}
}

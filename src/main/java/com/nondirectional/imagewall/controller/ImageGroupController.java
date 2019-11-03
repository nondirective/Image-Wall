package com.nondirectional.imagewall.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nondirectional.imagewall.to.CreateImageGroupTransportObject;

@Controller
public class ImageGroupController {
	@RequestMapping("/createImageGroupPage")
	public String createImageGroupPage() {
		return "createImageGroup";
	}
	@RequestMapping(method = RequestMethod.POST,value="/createImageGroup")
	public String createImageGroup(@Valid CreateImageGroupTransportObject to,BindingResult bindingResult) {
		for(ObjectError e:bindingResult.getAllErrors()) {
			if(e.getDefaultMessage().equals("UNLOGIN")) 
				return "unlogin";
			if(e.getDefaultMessage().equals("GROUP_NAME_EMPTY")) {
				
			}
		}
		return null;
	}
}

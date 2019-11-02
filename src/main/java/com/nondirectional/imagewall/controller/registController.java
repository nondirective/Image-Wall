package com.nondirectional.imagewall.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nondirectional.imagewall.bean.User;
import com.nondirectional.imagewall.exception.UsernameExistsException;
import com.nondirectional.imagewall.service.UserService;
import com.nondirectional.imagewall.utils.CheckCode;

@Controller
public class registController {
	@Autowired
	UserService userService;

	// 注册业务
	@RequestMapping("/regist")
	public String regist(@Valid User user, BindingResult result, Map<String, String> map,
			@RequestParam("password2") String password2, @RequestParam("checkcode") String checkcode,
			HttpSession session) {
		// Validtor校验
		if (result.getErrorCount() != 0)
			for (ObjectError oe : result.getAllErrors()) {
				if (oe.getDefaultMessage().equals("UN_EPT")) {
					map.put("msg_username", "用户名不能为空");
					continue;
				} else if (oe.getDefaultMessage().equals("PWD_EPT")) {
					map.put("msg_password", "密码不能为空");
					continue;
				} else if (oe.getDefaultMessage().equals("PWDL2_EPT")) {
					map.put("msg_passwordl2", "二级密码不能为空");
					continue;
				} else if (oe.getDefaultMessage().equals("UN_LEN") && !map.containsKey("msg_username")) {
					map.put("msg_username", "用户名长度需在1-20个字符之间");
					continue;
				} else if (oe.getDefaultMessage().equals("PWD_LEN") && !map.containsKey("msg_password")) {
					map.put("msg_username", "密码长度需在6-20个字符之间");
					continue;
				} else if (oe.getDefaultMessage().equals("PWDL2_LEN") && !map.containsKey("msg_passwordl2")) {
					map.put("msg_username", "密码长度需在6-20个字符之间");
				}
			}

		if (password2.equals(""))
			map.put("msg_password2", "确认密码不能为空");
		else if (!user.getPassword().equals(password2))
			map.put("msg_password2", "两次输入的密码不一致");
		
		if (!((String) session.getAttribute("checkcode")).equals(checkcode))
			map.put("msg_checkcode", "验证码错误");

		/*
		 * 在map对象当中除了控制器方法放入的键值对之外还有Springmvc封装进去的
		 * 
		 * user:User [id=null, username=xxxxxx, password=xxxxxx, registTime=null,
		 * lastLoginTime=null, secondrPassword=xxxxxx, wealth=0, manageGroup=null]
		 * org.springframework.validation.BindingResult.user:org.springframework.
		 * validation.BeanPropertyBindingResult: 0 errors
		 * 
		 * 所以map的大小如果大于二即校验出出现错误
		 */
		if (map.size() > 2)
			return "forward:/registPage";

		// 检查用户名是否已存在
		try {
			userService.registUser(user);
		} catch (UsernameExistsException e) {
			map.put("msg_username", "用户名已存在");
			return "forward:/registPage";
		}
		
		map.put("suc_msg","注册成功");

		return "success";
	}

	// 页面跳转
	@RequestMapping("/registPage")
	public String registPage() {
		return "regist";
	}

	// 验证码图片
	@RequestMapping("/checkcodeImage")
	public void checkcodeImage(HttpServletResponse response, HttpSession session) {
		Map<String, Object> checkcode = CheckCode.getCheckCodeImage();
		try {
			ImageIO.write((BufferedImage) checkcode.get("image"), "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		session.setAttribute("checkcode", ((String) checkcode.get("code")));
		return;
	}
}

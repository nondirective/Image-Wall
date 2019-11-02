package com.nondirectional.imagewall.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nondirectional.imagewall.bean.User;
import com.nondirectional.imagewall.dao.UserMapper;
import com.nondirectional.imagewall.exception.PasswordMismatchException;
import com.nondirectional.imagewall.exception.UsernameNotExistsException;
import com.nondirectional.imagewall.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping("loginPage")
	public String loginPage() {
		return "login";
	}


	@RequestMapping("/logout")
	public String logout(HttpSession session,Map<String,Object> map) {
		if(session.getAttribute("user")!=null) {
			session.removeAttribute("user");
			map.put("msg","注销成功");
		}else {
			map.put("msg","未登录");
		}
		return "success";
	}
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			Map<String, Object> map, HttpSession session, HttpServletResponse response) {
		User user = null;

		try {
			user = userService.loginUser(username, password);
		
			session.setAttribute("user", user);
		} catch (UsernameNotExistsException e) {
			map.put("msg_username", "用户名不存在");
			return "forward:/loginPage";
		} catch (PasswordMismatchException e) {
			map.put("msg_password", "密码错误");
			return "forward:/loginPage";
		}
		map.put("msg","登录成功");
		return "success";
	}
}

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

	// ע��ҵ��
	@RequestMapping("/regist")
	public String regist(@Valid User user, BindingResult result, Map<String, String> map,
			@RequestParam("password2") String password2, @RequestParam("checkcode") String checkcode,
			HttpSession session) {
		// ValidtorУ��
		if (result.getErrorCount() != 0)
			for (ObjectError oe : result.getAllErrors()) {
				if (oe.getDefaultMessage().equals("UN_EPT")) {
					map.put("msg_username", "�û�������Ϊ��");
					continue;
				} else if (oe.getDefaultMessage().equals("PWD_EPT")) {
					map.put("msg_password", "���벻��Ϊ��");
					continue;
				} else if (oe.getDefaultMessage().equals("PWDL2_EPT")) {
					map.put("msg_passwordl2", "�������벻��Ϊ��");
					continue;
				} else if (oe.getDefaultMessage().equals("UN_LEN") && !map.containsKey("msg_username")) {
					map.put("msg_username", "�û�����������1-20���ַ�֮��");
					continue;
				} else if (oe.getDefaultMessage().equals("PWD_LEN") && !map.containsKey("msg_password")) {
					map.put("msg_username", "���볤������6-20���ַ�֮��");
					continue;
				} else if (oe.getDefaultMessage().equals("PWDL2_LEN") && !map.containsKey("msg_passwordl2")) {
					map.put("msg_username", "���볤������6-20���ַ�֮��");
				}
			}

		if (password2.equals(""))
			map.put("msg_password2", "ȷ�����벻��Ϊ��");
		else if (!user.getPassword().equals(password2))
			map.put("msg_password2", "������������벻һ��");
		
		if (!((String) session.getAttribute("checkcode")).equals(checkcode))
			map.put("msg_checkcode", "��֤�����");

		/*
		 * ��map�����г��˿�������������ļ�ֵ��֮�⻹��Springmvc��װ��ȥ��
		 * 
		 * user:User [id=null, username=xxxxxx, password=xxxxxx, registTime=null,
		 * lastLoginTime=null, secondrPassword=xxxxxx, wealth=0, manageGroup=null]
		 * org.springframework.validation.BindingResult.user:org.springframework.
		 * validation.BeanPropertyBindingResult: 0 errors
		 * 
		 * ����map�Ĵ�С������ڶ���У������ִ���
		 */
		if (map.size() > 2)
			return "forward:/registPage";

		// ����û����Ƿ��Ѵ���
		try {
			userService.registUser(user);
		} catch (UsernameExistsException e) {
			map.put("msg_username", "�û����Ѵ���");
			return "forward:/registPage";
		}
		
		map.put("suc_msg","ע��ɹ�");

		return "success";
	}

	// ҳ����ת
	@RequestMapping("/registPage")
	public String registPage() {
		return "regist";
	}

	// ��֤��ͼƬ
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

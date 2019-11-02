package com.nondirection.imagewall.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nondirectional.imagewall.bean.User;
import com.nondirectional.imagewall.dao.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test01 {
	@Autowired
	UserMapper userMapper;

	@Test
	public void testGetUser() {
		String username = "xxx";
		System.out.println(userMapper.getIdByUsername(username));
		
	}
	
	
	
	@Test
	public void func01() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<5;i++) {
			switch((int)(Math.random()*3)) {
			case 0:
				sb.append((char)(Math.random()*10+48));
				break;
			case 1:
				sb.append((char)(Math.random()*26+65));
				break;
			case 2:
				sb.append((char)(Math.random()*26+97));
				break;
			default:
				i--;
			}
		}
		
		System.out.println(sb.toString());
	}

	@Test
	public void testCheckCode() throws Exception {
	}


}

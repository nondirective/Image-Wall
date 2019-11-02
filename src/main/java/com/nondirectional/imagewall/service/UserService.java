package com.nondirectional.imagewall.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nondirectional.imagewall.bean.User;
import com.nondirectional.imagewall.dao.UserMapper;
import com.nondirectional.imagewall.exception.PasswordMismatchException;
import com.nondirectional.imagewall.exception.UsernameExistsException;
import com.nondirectional.imagewall.exception.UsernameNotExistsException;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	@Transactional
	public void registUser(User user)throws UsernameExistsException {
		//检查用户名是否已存在
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username",user.getUsername());
		if(userMapper.getUserByOne(map)!=null)
			throw new UsernameExistsException();
		
		user.setRegistTime(new Date());
		userMapper.insertUser(user);
	}

	public User loginUserr(String username, String password)throws UsernameNotExistsException,PasswordMismatchException {
		if(userMapper.getIdByUsername(username)==null)
			throw new UsernameNotExistsException();
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user = userMapper.usernameMatchPassword(user);
		if(user.getId()==null) 
			throw new PasswordMismatchException();
		else
			return user;
		
	}
	
}

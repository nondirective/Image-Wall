package com.nondirectional.imagewall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.nondirectional.imagewall.bean.User;

@Mapper
public interface UserMapper {
	User getUserByOne(Map<String,Object> map);
	User usernameMatchPassword(User user);
	Integer getIdByUsername(String username);
	List<User> getUserByTimeBefore(Map<String,Object> map);
	List<User> getUserByTimeAfter(Map<String,Object> map);
	List<User> getUserByTimeInterval(Map<String,Object> map);
	List<User> getAllUser();
	void insertUser(User user);
	void updateLastLoginTimeByUsername(String username);
}

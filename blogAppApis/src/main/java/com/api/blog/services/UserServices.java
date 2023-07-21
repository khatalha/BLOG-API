package com.api.blog.services;

import java.util.List;

import com.api.blog.payloads.UserDto;

public interface UserServices {

	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto ,Integer userId);
	
	UserDto getUserByID(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(Integer userId);
}

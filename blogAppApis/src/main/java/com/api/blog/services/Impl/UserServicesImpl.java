package com.api.blog.services.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.blog.entities.User;
import com.api.blog.exceptions.ResourceNotFoundException;
import com.api.blog.payloads.UserDto;
import com.api.blog.repositries.UserRepo;
import com.api.blog.services.UserServices;


@Service
public class UserServicesImpl implements UserServices{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;

	// INSERT THE USER
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}
	
	

	// UPDATE THE USER
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId ));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updateUser);
		
		return userDto1;
	}

	// GET USER BY ID
	@Override
	public UserDto getUserByID(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId ));
		return this.userToDto(user);
	}

	
	//GET ALL USER 
	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> collect = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		
		
		return collect;
	}
	
	
//	DELETE USER BY ID
	@Override
	public void deleteUser(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId ));
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
		
	}
	
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		
		return userDto;
	}

}

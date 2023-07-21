package com.api.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.api.blog.payloads.ApiResponse;
import com.api.blog.payloads.UserDto;
import com.api.blog.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	//Insert the User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		
		UserDto createUserDto = this.userServices.createUser(userDto);
		
		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
		
	}
	
	
	//Update the user
	@PostMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
		UserDto updateUser = this.userServices.updateUser(userDto, uid);
		return ResponseEntity.ok(updateUser);
	}
	
	//delete User
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		
		this.userServices.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted success fully" , true),HttpStatus.OK);
	}
	
	//get all User
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		
		return ResponseEntity.ok(this.userServices.getAllUser());
	}
	
	//get single user by id
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uid){
		
		return ResponseEntity.ok(this.userServices.getUserByID(uid));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package com.api.blog.services;

import java.util.List;

import com.api.blog.entities.Post;
import com.api.blog.payloads.PostDto;
import com.api.blog.payloads.PostRespones;

public interface PostServices {
	
	//create
	
	PostDto createPost(PostDto postDto ,Integer userId ,Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
//	get All
	
	PostRespones getAllPost(Integer pageNumber ,Integer pageSize ,String sortBy ,String sortDir);
	
//	get Single
	
	PostDto getSinglePost(Integer postId);
	
	
	
	// get all post by category
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	
	//get All post by User
	
	List<PostDto> getAllPostByUser(Integer userId);
	
	
//	search post
	
	List<PostDto> searchPost(String key);
	
	
	

}

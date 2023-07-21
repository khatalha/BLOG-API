package com.api.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.api.blog.entities.Category;
import com.api.blog.entities.Comment;
import com.api.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {

	private Integer postId;
	
	private String title;
	private String content;
	
	private String imageName;
	private Date addedDate;
	
	
	private CategoryDto category;
	

	private UserDto user;
	
	
	private Set<Comment> comments= new HashSet<>();
}

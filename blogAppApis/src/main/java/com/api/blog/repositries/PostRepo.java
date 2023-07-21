package com.api.blog.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.blog.entities.Category;
import com.api.blog.entities.Post;
import com.api.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
	
	

	
}

package com.api.blog.services;

import java.util.List;

import com.api.blog.entities.Category;
import com.api.blog.payloads.CategoryDto;

public interface CategoryServices {
	
	
	//cerate
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	
	void deleteCategory(Integer categoryId);
	
	//get Single user
	CategoryDto getSingleCategory(Integer categoryId);

	//get All User
	List<CategoryDto> getAllCategory();



}

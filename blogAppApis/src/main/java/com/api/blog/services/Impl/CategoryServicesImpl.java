package com.api.blog.services.Impl;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.blog.entities.Category;
import com.api.blog.exceptions.ResourceNotFoundException;
import com.api.blog.payloads.CategoryDto;
import com.api.blog.repositries.CategoryRepo;
import com.api.blog.services.CategoryServices;

@Service
public class CategoryServicesImpl implements CategoryServices{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cate = this.modelMapper.map(categoryDto, Category.class);
		Category addedCate = this.categoryRepo.save(cate);
		
		return this.modelMapper.map(addedCate, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cate = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		cate.setCategoryTitle(categoryDto.getCategoryTitle());
		cate.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updateCate = this.categoryRepo.save(cate);
	
		return this.modelMapper.map(updateCate, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cate = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		this.categoryRepo.delete(cate);
		
	}

	@Override
	public CategoryDto getSingleCategory(Integer categoryId) {
		Category cate = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		return this.modelMapper.map(cate, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> dtoCate = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return dtoCate;
	}

}

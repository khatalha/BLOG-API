package com.api.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.payloads.ApiResponse;
import com.api.blog.payloads.CategoryDto;
import com.api.blog.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categries")
public class CategoryController {
	
	
	@Autowired
	private CategoryServices categoryServices;
	//
	
	// create
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategory = this.categoryServices.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	
	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer cId){
		
		CategoryDto updateCategory = this.categoryServices.updateCategory(categoryDto, cId);
		
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cId){
		
		this.categoryServices.deleteCategory(cId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Success fully" ,false) ,HttpStatus.OK);
	}
	
	
//	get single
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> gatSingleCategory(@PathVariable("categoryId") Integer cId){
		
		CategoryDto singleCategory = this.categoryServices.getSingleCategory(cId);
		return new ResponseEntity<CategoryDto>(singleCategory,HttpStatus.OK);
	}


	
	//gel All

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategory(){
		
		List<CategoryDto> allCategory = this.categoryServices.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}

}

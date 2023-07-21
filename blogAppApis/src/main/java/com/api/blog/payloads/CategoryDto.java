package com.api.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	
	@NotBlank
	@Size(min = 6, max = 80 ,message = "Category must be min 3 chracter anf max 80")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 3 ,message = "Name must be min 3 chracter")
	private String categoryDescription;


}

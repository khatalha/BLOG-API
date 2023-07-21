package com.api.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	
	private int id;
	
	@NotBlank
	@Size(min = 4 ,message = "Name must be min 3 chracter")
	private String name;
	
	@Email
	private String email;
		
	@NotBlank
	@Size(min = 3,max = 10 ,message = "password must be 3 to 10 chracter")
	private String password;
	
	@NotBlank
	private String about;
	
	
}

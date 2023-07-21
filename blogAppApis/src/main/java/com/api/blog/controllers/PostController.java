package com.api.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.blog.config.AppConstans;
import com.api.blog.payloads.ApiResponse;
import com.api.blog.payloads.PostDto;
import com.api.blog.payloads.PostRespones;
import com.api.blog.services.FileServices;
import com.api.blog.services.PostServices;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostServices postServices;
	
	@Autowired
	private FileServices fileServices;
	
	@Value("${project.image}")
	private String path;
	
	//add the posts
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable("userId") Integer uid,
			@PathVariable("categoryId") Integer cId
			
			)
	{
		PostDto createPost = this.postServices.createPost(postDto, uid, cId);
		return new ResponseEntity<PostDto>(createPost ,HttpStatus.CREATED);
		
		
	}
	
	
	//get posts by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer uId ){
		
		List<PostDto> allPostByUser = this.postServices.getAllPostByUser(uId);
		
		return new ResponseEntity<List<PostDto>>(allPostByUser ,HttpStatus.OK);
	}
	
	//get posts by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId") Integer uId ){
		
		List<PostDto> postByCategory = this.postServices.getPostByCategory(uId);
		return new ResponseEntity<List<PostDto>>(postByCategory ,HttpStatus.OK);
	}
	
	
	//get All posts

	//http://localhost:8081/api/posts/
	//http://localhost:8081/api/posts?pageNumber=2&pageSize=2 

	@GetMapping("/posts")
	public ResponseEntity<PostRespones> getAllPost(
			@RequestParam(value = "pageNumber" ,defaultValue = AppConstans.PAGE_NUMBER ,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize" , defaultValue = AppConstans.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy" , defaultValue = AppConstans.SORT_BY , required = false) String sortBy,
			@RequestParam(value="sortDir" ,defaultValue = AppConstans.SORT_DIR ,required = false) String sortDir
			){
		
		PostRespones allPost = this.postServices.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostRespones>(allPost  ,HttpStatus.OK);
	}
	
	//get a single posts
	//http://localhost:8081/api/posts/pId
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable("postId") Integer pId){
		PostDto singlePost = this.postServices.getSinglePost(pId);
		
		return new ResponseEntity<PostDto>(singlePost,HttpStatus.OK);
		
	}
	
	
	//http://localhost:8081/api/posts/pId
	//delete 
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable("postId") Integer  pId) {
		
		this.postServices.deletePost(pId);
		return new ApiResponse("posts is success fully deleted" ,true);
	}
	
	
	//http://localhost:8081/api/posts/Pid
	
	//post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer pId){
		
		PostDto updatePost = this.postServices.updatePost(postDto, pId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	
	
	//serching 
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String key){
		List<PostDto> searchPost = this.postServices.searchPost(key);
		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
	}
	
	//http://localhost:8080/api/post/image/upload/Pid
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId
			) throws IOException
	
	{
		PostDto postDto = this.postServices.getSinglePost(postId);
		String fileName = this.fileServices.uploadImage(path, image);
		
		postDto.setImageName(fileName);
		PostDto updatePost = this.postServices.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost ,HttpStatus.OK);
		
	}
	
	//serve the image
		//Fetch the image in the browser
		
		@GetMapping(value="/post/image/{imageName}" , produces =MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(@PathVariable("imageName") String imageName ,HttpServletResponse respones) throws IOException {
			
			InputStream resource = this.fileServices.getResource(path, imageName);
			respones.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource, respones.getOutputStream());
		}
		
		
		// http://localhost:8080/api/post/image/imagename
	
	
	  
	
	

}

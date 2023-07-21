package com.api.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.payloads.ApiResponse;
import com.api.blog.payloads.CommentDto;
import com.api.blog.services.CommentServices;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentServices commentServices;
	
	//http://localhost:8081/api/post/{postId}/comments
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto
			,@PathVariable("postId") Integer postId)
	{
		CommentDto createComment = this.commentServices.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(commentDto ,HttpStatus.OK);
		
		
	}
	
	//http://localhost:8081/api/comment/{commentId}
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer cId)
	{
		this.commentServices.deleteComment(cId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted success fully" ,true),HttpStatus.OK);
	}

}

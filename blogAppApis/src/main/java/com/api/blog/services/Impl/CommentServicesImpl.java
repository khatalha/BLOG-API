package com.api.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.blog.entities.Comment;
import com.api.blog.entities.Post;
import com.api.blog.exceptions.ResourceNotFoundException;
import com.api.blog.payloads.CommentDto;
import com.api.blog.repositries.CommentRepo;
import com.api.blog.repositries.PostRepo;
import com.api.blog.services.CommentServices;


@Service
public class CommentServicesImpl implements CommentServices{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "Id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment save = this.commentRepo.save(comment);
		
		
		
		return this.modelMapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment cId = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		this.commentRepo.delete(cId);
	}

}

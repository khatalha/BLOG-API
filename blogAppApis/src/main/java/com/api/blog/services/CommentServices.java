package com.api.blog.services;

import com.api.blog.payloads.CommentDto;

public interface CommentServices {
	
	CommentDto createComment(CommentDto commentDto ,Integer postId);
	
	void deleteComment(Integer commentId);

}

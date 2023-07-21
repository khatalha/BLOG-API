package com.api.blog.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}

package com.api.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.blog.entities.Category;
import com.api.blog.entities.Post;
import com.api.blog.entities.User;
import com.api.blog.exceptions.ResourceNotFoundException;
import com.api.blog.payloads.PostDto;
import com.api.blog.payloads.PostRespones;
import com.api.blog.repositries.CategoryRepo;
import com.api.blog.repositries.PostRepo;
import com.api.blog.repositries.UserRepo;
import com.api.blog.services.PostServices;


@Service
public class PostServicesImpl implements PostServices{
	
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Override
	public PostDto createPost(PostDto postDto ,Integer userId ,Integer categoryId) {
		
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId ));

		Category cate = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("defult.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(cate);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatepost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatepost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

	Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
	this.postRepo.delete(post);
	}

	@Override
	public PostRespones getAllPost(Integer pageNumber ,Integer pageSize,String sortBy ,String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable p =PageRequest.of(pageNumber, pageSize, sort);
		
		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post>  posts= pagePost.getContent();
		 
		List<PostDto> allPosts = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	
		PostRespones postRespones = new PostRespones();
		postRespones.setContent(allPosts);
		postRespones.setPageNumber(pagePost.getNumber());
		postRespones.setPageSize(pagePost.getSize());
		postRespones.setTotalElement(pagePost.getTotalElements());
		postRespones.setTotalPages(pagePost.getTotalPages());
		postRespones.setLastPage(pagePost.isLast());
		return postRespones;
	}

	@Override
	public PostDto getSinglePost(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cate = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cate);
		
		List<PostDto> catePosts = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return catePosts;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {

		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId ));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> coteUser = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return coteUser;
	}

	@Override
	public List<PostDto> searchPost(String key) {
		
		List<Post> posts = this.postRepo.findByTitleContaining(key);
		List<PostDto> collect = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return collect;
	}

}

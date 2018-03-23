package com.tidder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tidder.model.Post;
import com.tidder.repository.PostsRepository;

@Service("postsService")
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Transactional
	public List<Post> getAllPosts() { 
		return postsRepository.findAll();
	}

}

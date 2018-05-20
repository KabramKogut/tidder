package com.tidder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tidder.model.LikePostEntity;
import com.tidder.model.UserEntity;
import com.tidder.repository.LikeCommentsRepository;
import com.tidder.repository.LikePostsRepository;
import com.tidder.repository.LoginRepository;
import com.tidder.repository.PostsRepository;

@Service("likesService")
public class LikesServiceImpl implements LikesService {
	
	@Autowired
	private LikePostsRepository likePostsRepository;
	
	@Autowired
	private LikeCommentsRepository likeCommentsRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PostsRepository postsRepository;

	@Transactional
	public void like(int postId) {
		if(getAuthenticatedUser() == null) { // default abcd user
			if(likePostsRepository.getByIds(102, postId) == null) {
				likePostsRepository.save(createLikePost(postId));
			} else {
				likePostsRepository.delete(102, postId);
			}
		} else { // authenticated user
			if(likePostsRepository.getByIds(getAuthenticatedUser().getId(), postId) == null) {
				likePostsRepository.save(createLikePost(postId));
			} else {
				likePostsRepository.delete(getAuthenticatedUser().getId(), postId);
			}
		}
	}

	@Transactional
	public void commentLike(int postId, int commentId) {
		// to do
	}
	
	//---------HELPERS--------------------------------------------------
	
	@Transactional
	private UserEntity getAuthenticatedUser() {		
		return loginRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());		
	}
	
	//---------dao -> entity----------

	private LikePostEntity createLikePost(int id) {
		LikePostEntity entity = new LikePostEntity();
		if (getAuthenticatedUser()==null) {
			entity.setUser(loginRepository.findByEmail("abcd"));
		} else {
			entity.setUser(getAuthenticatedUser());
		}
		entity.setPost(postsRepository.findById(id).get());
		return entity;
	}
}

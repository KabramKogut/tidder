package com.tidder.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tidder.api.dto.Comment;
import com.tidder.api.dto.Post;
import com.tidder.api.dto.PostWithComments;
import com.tidder.api.dto.User;
import com.tidder.model.CommentEntity;
import com.tidder.model.PostEntity;
import com.tidder.model.UserEntity;
import com.tidder.repository.LoginRepository;
import com.tidder.repository.PostsRepository;

@Service("postsService")
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Transactional
	public void createPost(Post post) {
		//TO-DO: add binding with real user 
		postsRepository.save(postToEntity(post));
	}
	
	@Transactional
	public List<Post> getAllPosts() { 		
		return entityToPost(postsRepository.findAll());
	}
	
	@Transactional
	public PostWithComments getPostById(int id) {
		return entityToPost(postsRepository.findOne(id));
	}
	
	@Transactional
	public List<Post> getPostsByPageId(int id, int amount) {
		int to = id*amount;
		int from = to-amount;
		return entityToPost(postsRepository.findBetweenId(from,to));
	}

	//---------helpers----------
	
	private PostEntity postToEntity(Post dto) {
		PostEntity entity = new PostEntity();
		try {
			entity.setTopic(dto.getTopic());
			entity.setText(dto.getText());
			entity.setDate(new Date(System.currentTimeMillis()));
			entity.setUser(getAuthenticatedUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@Transactional
	private UserEntity getAuthenticatedUser() throws Exception {
		//TO-DO: get really logged user 
		
		UserEntity user = loginRepository.findByEmail("congue.turpis.In@molestieorcitincidunt.ca");
		if (user!=null) {
			return user;
		} else {
			throw new Exception("You must be signed in to submit a new post.");
		}
	}
	
	private PostWithComments entityToPost(PostEntity entityPost) {
		PostWithComments dtoPost = new PostWithComments();
		if(entityPost != null) {
			User dtoUser = new User();
			bindPostWithUser(entityPost,dtoPost,dtoUser);
			List<Comment> commentsList = new ArrayList<Comment>();
			for (CommentEntity commentEntity : entityPost.getComments()) {
				User user = new User();
				user.setId(commentEntity.getUser().getId());
				user.setName(commentEntity.getUser().getName());
				user.setLastname(commentEntity.getUser().getLastname());
				user.setEmail(commentEntity.getUser().getEmail());
				
				Comment comment = new Comment();
				comment.setId(commentEntity.getUser().getId());
				comment.setText(commentEntity.getText());
				comment.setDate(commentEntity.getDate());
				comment.setUser(user);
				commentsList.add(comment);
			}
			dtoPost.setComments(commentsList);

		}
		return dtoPost;
	}

	private List<Post> entityToPost(List<PostEntity> entitiesList) {
		List<Post> dtoList = new ArrayList<Post>();
		if(entitiesList != null) {
			for (PostEntity entity : entitiesList) {
				Post dtoPost = new Post();
				User dtoUser = new User();				
				bindPostWithUser(entity,dtoPost,dtoUser);
				dtoList.add(dtoPost);
			}
		}
		return dtoList;
	}
	
	private void bindPostWithUser(PostEntity entityPost, Post dtoPost, User dtoUser) { 
		dtoUser.setId(entityPost.getUser().getId());
		dtoUser.setName(entityPost.getUser().getName());
		dtoUser.setLastname(entityPost.getUser().getLastname());
		dtoUser.setEmail(entityPost.getUser().getEmail());
		
		dtoPost.setId(entityPost.getId());
		dtoPost.setTopic(entityPost.getTopic());
		dtoPost.setText(entityPost.getText());
		dtoPost.setDate(entityPost.getDate());
		dtoPost.setUser(dtoUser);
	}
}

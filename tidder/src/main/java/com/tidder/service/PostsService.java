package com.tidder.service;

import java.util.List;

import com.tidder.api.dto.Post;
import com.tidder.api.dto.PostWithComments;

public interface PostsService {

	public List<Post> getAllPosts() ;

	public PostWithComments getPostById(int id);

	public List<Post> getPostsByPageId(int id, int size);

	public void createPost(Post post);
}

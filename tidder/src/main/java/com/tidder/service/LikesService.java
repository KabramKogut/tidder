package com.tidder.service;

public interface LikesService {

	void like(int postId);

	void commentLike(int postId, int commentId);
	
}

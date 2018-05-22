package com.tidder.service;

import com.tidder.api.dto.LikeResponse;

public interface LikesService {

	LikeResponse likePost(int postId);

	void likeComment(int postId);
	
}

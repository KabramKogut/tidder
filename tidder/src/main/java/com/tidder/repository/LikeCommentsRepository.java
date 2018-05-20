package com.tidder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tidder.model.LikeCommentEntity;

@Repository("likeCommentsRepository")
public interface LikeCommentsRepository extends JpaRepository<LikeCommentEntity, Integer> {

}

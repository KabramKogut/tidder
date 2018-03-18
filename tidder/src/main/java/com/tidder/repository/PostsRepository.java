package com.tidder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tidder.model.Post;

@Repository("postsRepository")
public interface PostsRepository extends JpaRepository<Post, Integer> {

}

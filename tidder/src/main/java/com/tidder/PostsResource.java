package com.tidder;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tidder.model.Post;
import com.tidder.service.PostsService;

@Component
@Path("post")
public class PostsResource {
	
	@Autowired
	private PostsService postsService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getAllPosts() {
		List<Post> list = postsService.getAllPosts();
		for (Post post : list) {
			System.out.println(post.toString());
		}
		return list;
	}

}

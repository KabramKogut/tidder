package com.tidder.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name="Comments")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String text;
	@NotNull
	private Date date;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.REMOVE)
	private List<LikeCommentEntity> likes;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="PostId")
	private PostEntity post;
	
	public List<LikeCommentEntity> getLikes() {
		return likes;
	}
	public void setLikes(List<LikeCommentEntity> likes) {
		this.likes = likes;
	}	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public PostEntity getPost() {
		return post;
	}
	public void setPost(PostEntity post) {
		this.post = post;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

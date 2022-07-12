package com.pizeon.forum.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String userId;
	private String title;
	private String description;
	private String commentCount;
	
	public Post() {
		
	}
	
	public Post(String userId, String title, String description) {
		this.userId = userId;
		this.title = title;
		this.description = description;
		commentCount = "0";
	}
	
	public void update(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCommentCount() {
		return commentCount;
	}
	
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	
	public void addComment() {
		commentCount = Integer.toString(Integer.parseInt(commentCount) + 1);
	}
	
	public void removeComment() {
		commentCount = Integer.toString(Integer.parseInt(commentCount) - 1);
	}

}

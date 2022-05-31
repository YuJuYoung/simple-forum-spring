package com.pizeon.forum.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String userId;
	private String postId;
	private String description;
	
	public Comment() {
		
	}
	
	public Comment(String postId, String userId, String description) {
		this.postId = postId;
		this.userId = userId;
		this.description = description;
	}
	
	public void update(String description) {
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public String getPostId() {
		return postId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

package com.pizeon.forum.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizeon.forum.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	public List<Comment> findByPostId(String postId);
	
}

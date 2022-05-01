package com.pizeon.forum.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizeon.forum.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}

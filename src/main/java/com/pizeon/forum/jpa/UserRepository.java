package com.pizeon.forum.jpa;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizeon.forum.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findById(String id);
	public User findByEmail(String email);
	
	@Transactional
	public void deleteById(String id);
}

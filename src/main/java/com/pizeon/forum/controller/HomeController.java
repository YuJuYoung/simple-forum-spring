package com.pizeon.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizeon.forum.domain.Post;
import com.pizeon.forum.jpa.PostRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("")
	public String home(Model model) {
		List<Post> posts = postRepository.findAll();
		
		model.addAttribute("posts", posts);
		return "index";
	}
	
}

package com.pizeon.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizeon.forum.jpa.PostRepository;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping("/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("post", postRepository.findById(id));
		return "post/show";
	}
	
}

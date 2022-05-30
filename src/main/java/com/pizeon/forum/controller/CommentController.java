package com.pizeon.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post/{postId}/comment")
public class CommentController {
	
	@GetMapping("/createForm")
	public String createCommentForm(@PathVariable String postId, Model model) {
		model.addAttribute("postId", postId);
		return "comment/createForm :: #create-form";
	}
	
}

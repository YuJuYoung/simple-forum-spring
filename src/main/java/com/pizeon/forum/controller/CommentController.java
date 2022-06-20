package com.pizeon.forum.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizeon.forum.domain.Comment;
import com.pizeon.forum.jpa.CommentRepository;
import com.pizeon.forum.util.HttpSessionUtil;

@Controller
@RequestMapping("/post/{postId}/comment")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping("/createForm")
	public String createCommentForm(@PathVariable String postId, Model model) {
		model.addAttribute("postId", postId);
		return "comment/createForm :: #create-form";
	}
	
	@PostMapping("/create")
	public String create(@PathVariable String postId, HttpSession session, @RequestBody HashMap<String, Object> body, Model model) {
		String logined_id = HttpSessionUtil.getLoginedId(session);
		String userId = (String) body.get("userId");
		
		if (logined_id == null || !logined_id.equals(userId)) {
			return null;
		}
		
		String description = (String) body.get("description");
		Comment comment = new Comment(postId, logined_id, description);
		commentRepository.save(comment);
		
		model.addAttribute("comment", commentRepository.findById(comment.getId()));
		return "comment/show :: .show-form";
	}
	
}

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizeon.forum.domain.Comment;
import com.pizeon.forum.domain.Post;
import com.pizeon.forum.jpa.CommentRepository;
import com.pizeon.forum.jpa.PostRepository;
import com.pizeon.forum.util.HttpSessionUtil;
import com.pizeon.forum.util.Result;

@Controller
@RequestMapping("/post/{postId}/comment")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/createForm")
	public String createCommentForm(@PathVariable String postId, Model model) {
		model.addAttribute("postId", postId);
		return "comment/createForm :: #create-comment-form";
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
		
		Post post = postRepository.findById(postId);
		post.addComment();
		postRepository.save(post);
		
		model.addAttribute("comment", commentRepository.findById(comment.getId()));
		return "comment/show :: .show-form";
	}
	
	@PostMapping("/delete")
	public @ResponseBody Result delete(@PathVariable String postId, HttpSession session, @RequestBody HashMap<String, Object> body) {
		String logined_id = HttpSessionUtil.getLoginedId(session);
		String userId = (String) body.get("userId");
		
		if (logined_id == null || !logined_id.equals(userId)) {
			return Result.FAIL;
		}
		
		Post post = postRepository.findById(postId);
		post.removeComment();
		postRepository.save(post);
		
		commentRepository.deleteById((String) body.get("commentId"));
		return Result.SUCCESS;
	}
	
	@PostMapping("/updateForm")
	public String updateForm(@PathVariable String postId, HttpSession session, Model model, @RequestBody HashMap<String, Object> body) {
		String logined_id = HttpSessionUtil.getLoginedId(session);
		String userId = (String) body.get("userId");
		
		if (logined_id == null || !logined_id.equals(userId)) {
			return null;
		}
		
		String commentId = (String) body.get("commentId");
		Comment comment = commentRepository.findById(commentId);
		
		if (!comment.getPostId().equals(postId) || !comment.getUserId().equals(userId)) {
			return null;
		}
		
		model.addAttribute("commentId", commentId);
		return "comment/updateForm :: .update-comment-form";
	}
	
}

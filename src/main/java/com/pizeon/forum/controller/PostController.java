package com.pizeon.forum.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizeon.forum.domain.Post;
import com.pizeon.forum.jpa.PostRepository;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping("/create")
	public String createForm() {
		return "post/createForm";
	}
	
	@PostMapping("/create")
	public String create(HttpSession session, String title, String description) {
		if (getLoginedId(session) == null) {
			return "redirect:/user/login";
		}
		String userId = (String) session.getAttribute("logined_id");
		Post newPost = new Post(userId, title, description);
		
		postRepository.save(newPost);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("post", postRepository.findById(id));
		return "post/show";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable String id, HttpSession session) {
		String logined_id = getLoginedId(session);
		Post post = postRepository.findById(id);
		
		if (!logined_id.equals(post.getUserId())) {
			return "redirect:/user/login";
		}
		postRepository.deleteById(id);
		
		return "redirect:/";
	}
	
	@GetMapping("/{id}/update")
	public String updateForm(@PathVariable String id, HttpSession session, Model model) {
		String logined_id = getLoginedId(session);
		Post post = postRepository.findById(id);
		
		if (!logined_id.equals(post.getUserId())) {
			return "redirect:/user/login";
		}
		model.addAttribute("post", postRepository.findById(id));
		
		return "post/updateForm";
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable String id, HttpSession session, String title, String description) {
		String logined_id = getLoginedId(session);
		Post post = postRepository.findById(id);
		
		if (!logined_id.equals(post.getUserId())) {
			return "redirect:/user/login";
		}
		post.update(title, description);
		postRepository.save(post);
		
		return "redirect:/post/{id}";
	}
	
	private String getLoginedId(HttpSession session) {
		return (String) session.getAttribute("logined_id");
	}
	
}

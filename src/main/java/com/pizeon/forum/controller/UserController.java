package com.pizeon.forum.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizeon.forum.data.User;
import com.pizeon.forum.jpa.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/profile")
	public String profile(HttpSession session, Model model) {
		String logined_id = (String) session.getAttribute("logined_id");
		
		if (logined_id == null) {
			return "redirect:/";
		}
		User user = userRepository.findById(logined_id);
		model.addAttribute(user);
		return "user/profile";
	}
	
	@GetMapping("/create")
	public String createForm() {
		return "user/createForm";
	}
	
	@PostMapping("/create")
	public String create(HttpSession session, User user, Model model) {
		userRepository.save(user);
		session.setAttribute("logined_id", user.getId());
		return "redirect:/";
	}
	
}

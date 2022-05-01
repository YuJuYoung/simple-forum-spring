package com.pizeon.forum.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizeon.forum.domain.User;
import com.pizeon.forum.jpa.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/create")
	public String createForm() {
		return "user/createForm";
	}
	
	@PostMapping("/create")
	public String create(HttpSession session, User user) {
		userRepository.save(user);
		session.setAttribute("logined_id", user.getId());
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, String email, String password) {
		User user = userRepository.findByEmail(email);
		
		if (user != null) {
			if (password.equals(user.getPassword())) {
				session.setAttribute("logined_id", user.getId());
				return "redirect:/";
			}
		}
		return "redirect:/user/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("logined_id");
		return "redirect:/";
	}
	
}

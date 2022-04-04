package com.pizeon.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizeon.forum.data.User;
import com.pizeon.forum.jpa.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/profile")
	public String profile(Model model) {
		User user = userRepository.findById("1");
		model.addAttribute(user);
		return "user/profile";
	}
	
}

package com.pizeon.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizeon.forum.data.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/profile")
	public String profile(Model model) {
		User user = new User("ㅇㅅㅇ");
		model.addAttribute("user", user);
		return "user/profile";
	}
	
}

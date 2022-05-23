package com.pizeon.forum.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}/delete")
	public String delete(HttpSession session, @PathVariable String id) {
		if (!session.getAttribute("logined_id").equals(id)) {
			return "redirect:/user/login";
		}
		userRepository.deleteById(id);
		session.setAttribute("logined_id", null);
		
		return "redirect:/";
	}
	
	@GetMapping("/{id}/updatePwd")
	public String updatePwdForm(HttpSession session, @PathVariable String id) {
		if (!session.getAttribute("logined_id").equals(id)) {
			return "redirect:/user/login";
		}
		return "user/updatePwdForm";
	}
	
	@PostMapping("/{id}/updatePwd")
	public String updatePwd(HttpSession session, @PathVariable String id, String lastPwd, String newPwd) {
		String logined_id = (String) session.getAttribute("logined_id");
		
		if (!logined_id.equals(id)) {
			return "redirect:/user/login";
		}
		User user = userRepository.findById(id);
		
		if (!user.getPassword().equals(lastPwd)) {
			return "redirect:/user/login";
		}
		user.setPassword(newPwd);
		userRepository.save(user);
		
		return "redirect:/";
	}
	
	@GetMapping("/{id}/updateNickname")
	public String updateNicknameForm(HttpSession session, @PathVariable String id) {
		if (!session.getAttribute("logined_id").equals(id)) {
			return "redirect:/user/login";
		}
		return "user/updateNicknameForm";
	}
	
	@PostMapping("/{id}/updateNickname")
	public String updateNickname(HttpSession session, @PathVariable String id, String nickname, String password) {
String logined_id = (String) session.getAttribute("logined_id");
		
		if (!logined_id.equals(id)) {
			return "redirect:/user/login";
		}
		User user = userRepository.findById(id);
		
		if (!user.getPassword().equals(password)) {
			return "redirect:/user/login";
		}
		user.setNickname(nickname);
		userRepository.save(user);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("logined_id");
		return "redirect:/";
	}
	
}

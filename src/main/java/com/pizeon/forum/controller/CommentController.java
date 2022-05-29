package com.pizeon.forum.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizeon.forum.util.HttpSessionUtil;
import com.pizeon.forum.util.Result;

@RestController
@RequestMapping("/post/{postId}/comment")
public class CommentController {
	
	private HttpSessionUtil httpSessionUtil;
	
	@GetMapping
	public Result createCommentForm(HttpSession session) {
		if (httpSessionUtil.getLoginedId(session) == null) {
			return Result.NO;
		}
		return Result.OK;
	}
	
}

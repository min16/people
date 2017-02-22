package com.handstudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.handstudio.model.Post;
import com.handstudio.service.PostService;

@Controller
public class MainController {
	
	@Autowired
	PostService postService;

	/**
	 * main 페이지 리턴
	 */
	@RequestMapping("")
	public String main(Model model) {
		List<Post> navPosts = postService.getPostsForNav();
		model.addAttribute("navPosts", navPosts);
		return "main";
	}
	
}

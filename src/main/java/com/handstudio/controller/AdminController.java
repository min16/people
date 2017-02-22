package com.handstudio.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.handstudio.service.AdminService;
import com.handstudio.service.PostService;
import com.handstudio.utility.AdminUtil;
import com.handstudio.model.Admin;
import com.handstudio.model.Post;


@Controller
public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	PostService postService;
	
	/**
	 * login 페이지 리턴
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("admin") != null){
			return "redirect:/";
		}
		
		List<Post> navPosts = postService.getPostsForNav();
		model.addAttribute("navPosts", navPosts);
		return "admin/login";
	}
	
	/**
	 * 
	 * login 처리 후,
	 * main페이지로 리턴
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value="/login/proc", method = RequestMethod.POST)
	public @ResponseBody String loginProc(@ModelAttribute @Valid Admin requestAdmin, HttpServletRequest request, 
							Model model, BindingResult bindingResult) throws NoSuchAlgorithmException{
		String result = null;
		if(bindingResult.hasErrors()) {
			result = "invalid input";
			return result;
		}
		
		String rawPassword = requestAdmin.getPassword();
		String encodedPassword = AdminUtil.encodePassword(rawPassword);
		requestAdmin.setPassword(encodedPassword);
		
		Admin admin = adminService.getAdminByNameAndPassword(requestAdmin);
		if(admin == null){
			//인증실패
			result = "login failed";
			return result;
		}
		adminService.updateLoginDate(admin.getNo());
		//세션에 admin no 저장
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin);
		return result;
	}
	
}

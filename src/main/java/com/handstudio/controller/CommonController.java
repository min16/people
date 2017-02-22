package com.handstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	/**
	 * header 페이지 리턴
	 * @return
	 */
	@RequestMapping("/header")
	public String header(){
		return "common/header";
	}
	
	/**
	 * footer 페이지 리턴
	 * @return
	 */
	
	@RequestMapping("/footer")
	public String footer(){
		return "common/footer";
	}
}

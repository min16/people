package com.handstudio.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.handstudio.annotation.AccessRole;
import com.handstudio.annotation.AccessRole.AccessRoles;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * AccessRole에 따른 인터셉터 처리
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		System.out.println("prehandle");
		HttpSession session = request.getSession();
		AccessRole access = ((HandlerMethod)handler).getMethodAnnotation(AccessRole.class);
		
		if(access == null){
			return true;
		}
		
		if(access.role() == AccessRoles.ADMIN && session.getAttribute("admin") == null){
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
	
	@Override public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
	}

}

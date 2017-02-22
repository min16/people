package com.handstudio.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;

public class AdminUtil {
	
	public static String encodePassword(String rawPassword) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(rawPassword.getBytes());
		byte[] digest =md.digest();
		String encodedPassword = Base64.encodeBase64String(digest);
		return encodedPassword;
	}
	
	public static boolean isAdmin(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("admin") != null){
			return true;
		}else{
			return false;
		}
	}
}

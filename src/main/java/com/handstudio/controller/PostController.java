package com.handstudio.controller;



import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.handstudio.annotation.AccessRole;
import com.handstudio.annotation.AccessRole.AccessRoles;
import com.handstudio.model.Post;
import com.handstudio.service.PostService;
import com.handstudio.utility.AdminUtil;


@Controller
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
    ApplicationContext appContext;
	@Autowired
	PostService postService;
	@Autowired
	Environment environment;
	
	
	@Value("${localUrl}") private String localUrl;
	@Value("${fileUrl}") private String fileUrl;
	
	
 	/**
	 * posts 페이지 리턴 (GET)
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getPosts(
			@RequestParam(value="category", defaultValue="all") String category,
			@RequestParam(value="query", defaultValue="") String query,
			@RequestParam(value="queryType", defaultValue="all") String queryType,
			@RequestParam(value="pageNo", defaultValue="0") Integer pageNo,
			Model model,
			HttpServletRequest request) {
		System.out.println("************Controller: posts************");
	
		if(AdminUtil.isAdmin(request)){
			model.addAttribute("isAdmin", true);
		}
		
		
		List<Post> posts;
		List<Post> navPosts = postService.getPostsForNav();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		
		if(!category.equals("NOTICE") && !category.equals("all")){
			posts = postService.getPostsByCategroy(category);
			model.addAttribute("navPosts", navPosts);
			model.addAttribute("category", category);
			model.addAttribute("posts", posts);
			return "post/list";
		}
		map.put("query", query);
		map.put("queryType", queryType);
		
		//페이징 처리
		Integer totalPosts = postService.countPosts(map);
		Integer pagingTotal = totalPosts/10;
		if(totalPosts%10 != 0){
			pagingTotal++;
		}
		Integer pageOffset = pageNo;
		if(pageNo != 0){
			pageOffset = pageNo * 10;
		}
		map.put("pageOffset", pageOffset);
		
		posts = postService.getPostsByMap(map);
		
		model.addAttribute("navPosts", navPosts);
		model.addAttribute("category", category);
		model.addAttribute("query", query);
		model.addAttribute("queryType", queryType);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pagingTotal", pagingTotal);
		model.addAttribute("posts", posts);
		return "post/board";
	}
	
	/**
	 * post 생성 처리(POST)
	 * posts 페이지 리턴
	 */
	@AccessRole(role=AccessRoles.ADMIN)
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody String write(@ModelAttribute @Valid Post post, BindingResult bindingResult) {
		System.out.println("************Controller: write************");
		String result = null;
		if(bindingResult.hasErrors() || post.getCategory().equals("all")) result = "invalid input";
		postService.createPost(post);
		result = post.getCategory();
		return result;
	}
	
	/**
	 * post write 페이지 리턴 (GET)
	 * 
	 */
	@AccessRole(role=AccessRoles.ADMIN)
	@RequestMapping("/proc")
	public String writeProc(Model model) {
		System.out.println("************Controller: write_proc************");
		List<Post> navPosts = postService.getPostsForNav();
		model.addAttribute("navPosts", navPosts);
		return "post/write";
	}
	
	/**
	 * post 상세 페이지 리턴 (GET)
	 * 존재하지 않은 no posts로 리턴
	 * 처음 방문한 세션일 경우 조회수 증가
	 */
	@RequestMapping(value = "/{postNo}", method = RequestMethod.GET)
	public String getPost(@PathVariable(value = "postNo") Integer postNo,
					      @RequestParam(value="category", defaultValue="all") String category,
						  @RequestParam(value="query", defaultValue="") String query,
						  @RequestParam(value="queryType", defaultValue="all") String queryType,
						  @RequestParam(value="pageNo", defaultValue="0") Integer pageNo,
						  HttpServletRequest request, 
						  HttpServletResponse response, 
						  Model model) {
		System.out.println("************Controller: post**************");
		
		Post post = postService.getPostByNo(postNo);
		if(post == null) {
			return "redirect:/posts";
		}
		
		HttpSession session = request.getSession();	
		String visitedPost = (String)session.getAttribute("readCount");
		if(visitedPost == null){
			visitedPost = "";
		}
		if(visitedPost == "" || visitedPost.indexOf(postNo + "") == -1){
			session.setAttribute("readCount", visitedPost + postNo + "|");
			postService.increasePageView(postNo);
		}
		
		List<Post> navPosts = postService.getPostsForNav();
		
		//admin 로그인시 attribute 추가
		String adminName = (String) request.getAttribute("admin");
		
		model.addAttribute("admin", adminName);		
		model.addAttribute("navPosts", navPosts);
		model.addAttribute("post", post);
		model.addAttribute("query", query);
		model.addAttribute("category", category);
		model.addAttribute("queryType", queryType);
		model.addAttribute("pageNo", pageNo);
		return "post/index";
	}
	
	/**
	 * post 수정 처리(PUT)
	 */
	@AccessRole(role=AccessRoles.ADMIN)
	@RequestMapping(value = "/{postNo}", method = RequestMethod.PUT)
	public String update(@ModelAttribute @Valid Post post, BindingResult bindingResult, @PathVariable(value = "postNo") Integer postNo, Model model, HttpServletRequest request) {
		System.out.println("***********Controller: update***********");
		
		if(bindingResult.hasErrors() || post.getCategory().equals("all")) return "redirect:/posts/"+postNo+"/proc";
		post.setNo(postNo);
		postService.updatePost(post);
		
		String category = post.getCategory();
		
		if(!category.equals("NOTICE")){
			return "redirect:/posts?category=" + category;
		}
		
		// go to index
		post = postService.getPostByNo(postNo);
		model.addAttribute("post", post);
		return "redirect:" + postNo;
	}
	
	/**
	 * post 수정 페이지 리턴 (GET)
	 * 존재하지 않은 no posts로 리턴
	 */
	@AccessRole(role=AccessRoles.ADMIN)
	@RequestMapping("/{postNo}/proc")
	public String updateProc(@PathVariable(value = "postNo") Integer postNo, Model model) {
		System.out.println("***********Controller: update_proc***********");
		
		Post post = postService.getPostByNo(postNo);
	
		if(post == null) {
			return "redirect:/posts";
		}
		
		List<Post> navPosts = postService.getPostsForNav();
		model.addAttribute("navPosts", navPosts);
		model.addAttribute("post", post);
		return "post/update";
	}
	
	/**
	 * post 삭제 처리(DELETE)
	 */
	@AccessRole(role=AccessRoles.ADMIN)
	@RequestMapping(value = "/{postNo}", method = RequestMethod.DELETE)
	public @ResponseBody Integer delete(@PathVariable(value = "postNo") Integer postNo) {
		System.out.println("***********Controller: delete***********");
		Integer result = postService.deletePost(postNo);
		return result;
	}
	
	/**
	 * 이미지 업로드 처리
	 * @throws NoSuchAlgorithmException 
	 */
	@AccessRole(role=AccessRoles.ADMIN)
	@RequestMapping(value="/upload", method = RequestMethod.POST, headers=("content-type=multipart/*"))
	public @ResponseBody Map<String, Object> handleUpload(MultipartHttpServletRequest fileRequest)  throws IOException, NoSuchAlgorithmException{
		System.out.println("*****************Controller: upload*************");
		Map<String, Object> map = new HashMap<String, Object>();
		MultipartFile uploadFile = fileRequest.getFile("file");
		if(uploadFile != null) {
			String fileName = uploadFile.getOriginalFilename();
			
			String fileType = fileName.split("[.]")[1];
			if(!fileType.equals("png") && !fileType.equals("jpg") && !fileType.equals("jpeg")){
				map.put("error", "fileType error");
				return map;
			}
			
			Date date = new Date();
			String currentTime = String.valueOf(date.getTime());
			
			String rawFileName = fileName + currentTime;
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			sha256.update(rawFileName.getBytes());
			byte[] byteData = sha256.digest();
			String encodedFileName = Base64.encodeBase64String(byteData);
			encodedFileName = encodedFileName.replace('/',' ');
			
			try {
				File file = new File(fileUrl + encodedFileName + "." + fileType);
				uploadFile.transferTo(file);
				map.put("location", localUrl + encodedFileName + "." + fileType);
				
			} catch (IOException e) {
			}
		}
		else{
			map.put("error", "empty file");
		}
		return map;
	}
}


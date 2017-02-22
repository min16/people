package com.handstudio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handstudio.model.Category;
import com.handstudio.model.Post;
import com.handstudio.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public List<Post> getPostsByMap(Map<String, Object> map) {
		System.out.println("************Dao: getPostListByMap******************");
		return postRepository.findAllByMap(map);
	}
	
	public List<Post> getPostsForNav() {
		System.out.println("************Dao: getPostList******************");
		List<Post> posts = new ArrayList<Post>();
		for(Category category : Category.values()) {
			posts.addAll(postRepository.findPostForNav(category));
		}
		return posts;
	}
		
	public List<Post> getPostsByCategroy(String category) {
		System.out.println("***********Dao: getPostListByCategory************");
		return postRepository.findByCategory(category);
	}
	
	public Post getPostByNo(Integer no) {
		System.out.println("***********Dao: getPostByNo**************");
		return postRepository.findByNo(no);
	}
	
	public Integer createPost(Post post) {
		System.out.println("************Dao: createPost******************");
		Integer result = postRepository.create(post);
		return result;
	}
	
	public void increasePageView(Integer no) {
		System.out.println("************Dao: increasePageView******************");
		postRepository.increasePageView(no);
	}
	
	public Integer updatePost(Post post) {
		System.out.println("************Dao: updatePost******************");
		Integer result = postRepository.update(post);
		return result;
	}
	
	public Integer deletePost(Integer no) {
		System.out.println("************Dao: updatePost******************");
		Integer result = postRepository.delete(no);
		return result;
	}
	
	public Integer countPosts(Map<String, Object> map) {
		System.out.println("************Dao: updatePost******************");
		Integer count = postRepository.countPosts(map);
		return count;
	}
}

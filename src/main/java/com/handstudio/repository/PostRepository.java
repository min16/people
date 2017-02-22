package com.handstudio.repository;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.handstudio.model.Category;
import com.handstudio.model.Post;

@Repository
public interface PostRepository {
	
	List<Post> findAllByMap(Map<String, Object> map);
	List<Post> findPostForNav(Category category);
	List<Post> findByCategory (String category);
	Post findByNo(Integer no);
	Integer create(Post post);
	Integer update(Post post);
	Integer delete(Integer no);
	void increasePageView(Integer no);
	Integer countPosts(Map<String, Object> map);
	
}

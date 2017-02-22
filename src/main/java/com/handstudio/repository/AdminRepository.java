package com.handstudio.repository;

import org.springframework.stereotype.Repository;

import com.handstudio.model.Admin;

@Repository
public interface AdminRepository {
	
	Admin findByNameAndPassword(Admin admin);
	Integer update(Admin admin);
	Integer updateLoginDate(Integer no);
	
}

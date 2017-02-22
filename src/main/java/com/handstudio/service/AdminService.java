package com.handstudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handstudio.model.Admin;
import com.handstudio.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	public Admin getAdminByNameAndPassword(Admin admin){
		return adminRepository.findByNameAndPassword(admin);
	}
	
	public Integer updateAdmin(Admin admin){
		return adminRepository.update(admin);
	}
	
	public Integer updateLoginDate(Integer no){
		return adminRepository.updateLoginDate(no);
	}
}

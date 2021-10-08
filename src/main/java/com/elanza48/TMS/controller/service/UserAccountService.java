package com.elanza48.TMS.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elanza48.TMS.model.dao.UserAccountRepository;
import com.elanza48.TMS.model.dto.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository userRepo;
	
	public UserAccount saveUser(UserAccount user) {
		return userRepo.save(user);
	}
	
	public UserAccount findUserByEmail(String email) {
		return userRepo.findByEmailEquals(email);
	}
	
	public UserAccount findUserById(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	public List<UserAccount> getAllUser() {
		return userRepo.findAll();
	}

}

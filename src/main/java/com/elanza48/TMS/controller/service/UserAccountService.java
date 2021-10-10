package com.elanza48.TMS.controller.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elanza48.TMS.model.dao.UserAccountRepository;
import com.elanza48.TMS.model.dto.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository userRepo;
	
	public UserAccount createUser(UserAccount user) {
		return userRepo.save(user);
	}
	
	public  UserAccount findUser(String email) {
		return userRepo.findByEmailEquals(email);
	}
	
	public UserAccount findUser(UUID id) {
		return userRepo.findById(id).orElse(null);
	}

	public void deleteUser(UUID id) {
		userRepo.deleteById(id);
	}
	
	public void deleteUser(String email) {
		userRepo.deleteById(findUser(email).getId());
	}
	
	
	public List<UserAccount> getAllUser() {
		return userRepo.findAll();
	}

}

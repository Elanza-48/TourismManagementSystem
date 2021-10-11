package com.elanza48.TMS.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elanza48.TMS.model.dao.UserAccountRepository;
import com.elanza48.TMS.model.dto.UserAccount;

@Service
public class UserAccountService implements UserDetailsService{
	
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("admin", "admin", new ArrayList<>());
	}

	

}

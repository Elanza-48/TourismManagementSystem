package com.elanza48.TMS.controller.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elanza48.TMS.controller.service.UserAccountService;
import com.elanza48.TMS.model.dto.UserAccount;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserAccountService userService;
	
	@PostMapping
	public UserAccount getUserByEmail(@RequestBody UserAccount user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/{email}")
	public UserAccount getUserByEmail(@PathVariable String email) {
		return userService.findUserByEmail(email);
	}
	
	@GetMapping
	public List<UserAccount> getAllUser() {
		return userService.getAllUser();
	}
	


}

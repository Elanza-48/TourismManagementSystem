package com.elanza48.TMS.controller.mapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elanza48.TMS.model.dto.UserAccount;
import com.elanza48.TMS.service.UserAccountService;

@RestController
@RequestMapping(value = "/user", produces = {"application/hal+json"})
public class UserController {
	
	@Autowired
	UserAccountService userService;
	
	@PostMapping
	public UserAccount getUserByEmail(@RequestBody UserAccount user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/email/{email}")
	public UserAccount getUserByEmail(@PathVariable String email) {
		return userService.findUser(email).get();
	}

	@GetMapping("/id/{id}")
	public UserAccount getUserById(@PathVariable UUID id) {
		return userService.findUser(id).get();
	}

	@PatchMapping("/id/{id}")
	public UserAccount updateUserbyId(@PathVariable UUID id, @RequestBody Map<String , Object> body){
		UserAccount user = userService.updateUserById(id, body);
		return user;
	}

	@PatchMapping("/email/{email}")
	public ResponseEntity<?> updateUserbyId(@PathVariable String email, @RequestBody Map<String , Object> body){
		return ResponseEntity.ok(userService.updateUserByEmail(email, body));
	}

	@DeleteMapping("/id/{id}")
	public void deleteUserByID(@PathVariable UUID id){
		userService.deleteUser(id);
	}

	@DeleteMapping("/email/{email}")
	public void deleteUserByEmail(@PathVariable String email){
		userService.deleteUser(email);
	}
	
	@GetMapping
	public List<UserAccount> getAllUser() {
		return userService.getAllUser();
	}
	


}

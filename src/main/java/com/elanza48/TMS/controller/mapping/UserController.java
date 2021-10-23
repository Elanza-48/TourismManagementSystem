package com.elanza48.TMS.controller.mapping;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

	private void checkUser(String email){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		for (GrantedAuthority a : auth.getAuthorities()){
			if(a.toString().equals("ADMIN")) return;
		}
		if(!auth.getPrincipal().toString().equals(email)){
			throw new AccessDeniedException("explicit user access attempt !");
		}
	}	
	
	@PostMapping
	public UserAccount createUser(@RequestBody UserAccount user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/email/{email}")
	public UserAccount getUserByEmail(@PathVariable String email) {
		checkUser(email);
		return userService.findUser(email).get();
	}

	@GetMapping("/email/{email}/address")
	public ResponseEntity<?> getUserAddress(@PathVariable String email) {
		checkUser(email);
		return ResponseEntity.ok(userService.findUser(email).get().getAddress());
	}

	@GetMapping("/email/{email}/bookings")
	public ResponseEntity<?> getUserBooking(@PathVariable String email) {
		checkUser(email);
		return ResponseEntity.ok(userService.findUser(email).get().getBookings());
	}

	@PatchMapping("/email/{email}")
	public ResponseEntity<?> updateUserbyId(@PathVariable String email, @RequestBody Map<String , Object> body){
		checkUser(email);
		return ResponseEntity.ok(userService.updateUserByEmail(email, body));
	}

	@DeleteMapping("/email/{email}")
	public void deleteUserByEmail(@PathVariable String email){
		checkUser(email);
		userService.deleteUser(email);
	}
	
	@GetMapping
	public List<UserAccount> getAllUser() {
		return userService.getAllUser();
	}
	


}

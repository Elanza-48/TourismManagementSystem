package com.elanza48.TMS.controller.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elanza48.TMS.model.dto.AddressDTO;
import com.elanza48.TMS.model.dto.BookingDTO;
import com.elanza48.TMS.model.dto.UserAccountDTO;
import com.elanza48.TMS.model.entity.Booking;
import com.elanza48.TMS.model.entity.UserAccount;
import com.elanza48.TMS.model.mapper.BookingMapper;
import com.elanza48.TMS.model.mapper.UserAccountMapper;
import com.elanza48.TMS.service.UserAccountService;

@RestController
@RequestMapping(value = "/user", produces = {"application/hal+json"})
public class UserController {
	
	@Autowired
	private UserAccountService userService;

	@Autowired
	private UserAccountMapper userAccountMapper;

	@Autowired
	private BookingMapper bookingMapper;
	
	@PostMapping
	public ResponseEntity<UserAccountDTO> createUser(@RequestBody UserAccount user) {
		return ResponseEntity.accepted().body(userAccountMapper.userAccountModelToDto(
			userService.createUser(user)
		));
	}
	
	@GetMapping("/email/{email}")
	@PreAuthorize("#email == authentication.name")
	public UserAccountDTO getUserByEmail(@PathVariable String email) {
		return  userAccountMapper.userAccountModelToDto( userService.findUser(email).get());
	}

	@GetMapping("/email/{email}/address")
	@PreAuthorize("#email == authentication.name")
	public ResponseEntity<AddressDTO> getUserAddress(@PathVariable String email) {
		return ResponseEntity.ok(userAccountMapper.userAccountModelToDto(
			userService.findUser(email).get()).getAddress());
	}

	@GetMapping("/email/{email}/booking")
	@PreAuthorize("#email == authentication.name")
	public ResponseEntity<List<BookingDTO>> getUserBooking(@PathVariable String email) {
		return ResponseEntity.ok(bookingMapper.bookingModelToDtoList(
			new ArrayList<Booking>(userService.findUser(email).get().getBookings())
		));
	}

	@DeleteMapping("/email/{email}")
	@PreAuthorize("#email == authentication.name")
	public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
		userService.deleteUser(email);
		return ResponseEntity.accepted().body("{\"message\": \"User Deleted.\"}");
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<UserAccountDTO> getAllUser() {
		return userAccountMapper.userAccountModelToDtoList(userService.getAllUser());
	}

}

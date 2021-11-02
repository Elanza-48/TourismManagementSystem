package com.elanza48.TMS.controller.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elanza48.TMS.model.ModelDtoMapper;
import com.elanza48.TMS.model.dto.AddressDTO;
import com.elanza48.TMS.model.dto.BookingDTO;
import com.elanza48.TMS.model.dto.UserAccountDTO;
import com.elanza48.TMS.model.entity.Booking;
import com.elanza48.TMS.model.entity.UserAccount;
import com.elanza48.TMS.service.UserAccountService;

@RestController
@RequestMapping(value = "/user", produces = {"application/hal+json"})
public class UserController {

	private UserAccountService userService;
	private ModelDtoMapper modelDtoMapper;

	@Autowired
	public void setUserService(UserAccountService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setModelDtoMapper(ModelDtoMapper modelDtoMapper) {
		this.modelDtoMapper = modelDtoMapper;
	}

	@PostMapping
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserAccountDTO> createUser(@RequestBody UserAccount user) {
		return ResponseEntity.accepted().body(modelDtoMapper.userAccountModelToDto(
			userService.createUser(user)
		));
	}
	
	@GetMapping("/email/{email}")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name")
	public UserAccountDTO getUserByEmail(@PathVariable String email) {
		return  modelDtoMapper.userAccountModelToDto( userService.findUser(email).get());
	}

	@GetMapping("/email/{email}/address")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name")
	public ResponseEntity<AddressDTO> getUserAddress(@PathVariable String email) {
		return ResponseEntity.ok(modelDtoMapper.userAccountModelToDto(
			userService.findUser(email).get()).getAddress());
	}

	@GetMapping("/email/{email}/booking")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name")
	public ResponseEntity<List<BookingDTO>> getUserBooking(@PathVariable String email) {
		return ResponseEntity.ok(modelDtoMapper.bookingModelToDtoList(
			new ArrayList<Booking>(userService.findUser(email).get().getBookings())
		));
	}

	@DeleteMapping("/email/{email}")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name")
	public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
		userService.deleteUser(email);
		return ResponseEntity.accepted().body("{\"message\": \"User Deleted.\"}");
	}
	
	@GetMapping
	@Secured({"ROLE_ADMIN"})
	public List<UserAccountDTO> getAllUser() {
		return modelDtoMapper.userAccountModelToDtoList(userService.getAllUser());
	}

}

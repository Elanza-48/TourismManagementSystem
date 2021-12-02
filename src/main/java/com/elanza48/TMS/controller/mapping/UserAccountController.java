package com.elanza48.TMS.controller.mapping;

import java.util.*;

import com.elanza48.TMS.model.dto.UserAccountDTO;
import com.elanza48.TMS.model.entity.UserAccount;
import com.elanza48.TMS.model.mapper.DtoToModelMapper;
import com.elanza48.TMS.model.mapper.ModelDtoToMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.elanza48.TMS.model.entity.Booking;
import com.elanza48.TMS.service.UserAccountService;


@RestController
@RequestMapping(value = "/user", produces = {"application/hal+json"})
public class UserAccountController {

	private UserAccountService userService;
	private ModelDtoToMapper modelDtoToMapper;
	private DtoToModelMapper dtoToModelMapper;


	@Autowired
	public void setUserService(UserAccountService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setModelDtoToMapper(ModelDtoToMapper modelDtoToMapper) {
		this.modelDtoToMapper = modelDtoToMapper;
	}
	@Autowired
	public void setDtoToModelMapper(DtoToModelMapper dtoToModelMapper) {
		this.dtoToModelMapper = dtoToModelMapper;
	}

	@GetMapping
	@PreAuthorize("permitAll()")
	public ResponseEntity<RepresentationModel<?>> getUserAccountHAL() {
		return ResponseEntity.ok(new RepresentationModel<>(getHyperLinks()));
	}

	@GetMapping("/register")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> getRegisterFormat(){
		return ResponseEntity.ok(UserAccountDTO.getBodyFormat());
	}

	@PostMapping("/register")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> createUser(@RequestBody UserAccountDTO userAccountDTO) {
		return ResponseEntity.accepted().body(modelDtoToMapper.userAccountModelToDto(
			userService.createUser(dtoToModelMapper.userAccountDtoToModel(userAccountDTO, new UserAccount()))));
	}

	@Caching(
			cacheable = @Cacheable(value = "TMSCache"),
			put = @CachePut(value = "TMSCache")
	)
	@GetMapping("/email/{email}")
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
			return ResponseEntity.ok(modelDtoToMapper.userAccountModelToDto(
					userService.findUser(email).get()));
	}

	@GetMapping("/email/{email}/address")
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<?> getUserAddress(@PathVariable String email) {
			return ResponseEntity.ok(modelDtoToMapper.userAccountModelToDto(
					userService.findUser(email).get()).getAddress());
	}

	@GetMapping("/email/{email}/booking")
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<?> getUserBooking(@PathVariable String email) {
			return ResponseEntity.ok(modelDtoToMapper.bookingModelToDtoList(
					new ArrayList<Booking>(userService.findUser(email).get().getBookings())
			));
	}

	@GetMapping("/email/{email}/role")
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<?> getUserRole(@PathVariable String email) {
			return ResponseEntity.ok(modelDtoToMapper.userRoleModelToDto(
					userService.findUser(email).get().getRole()
			));
	}

	@PatchMapping("/email/{email}")
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<?> partialUpdateUser(@PathVariable String email,
				   @RequestBody UserAccountDTO userAccountDTO){
		return ResponseEntity.accepted().body(userService.updateUser(userAccountDTO, email).get());
	}

	@DeleteMapping("/email/{email}")
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
		userService.deleteUser(email);
		return ResponseEntity.accepted().body(
				Map.of("message","User Deleted.")
		);
	}
	
	@GetMapping("/all")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> getAllUser() {
		return ResponseEntity.ok(modelDtoToMapper.userAccountModelToDtoList(
				userService.getAllUser()));
	}

	private List<Link> getHyperLinks(){
		List<Link> links = new LinkedList<>();
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserAccountController.class).getUserBooking(null)).withRel("user_booking"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserAccountController.class).getUserRole(null)).withRel("user_role"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserAccountController.class).getUserAddress(null)).withRel("user_address"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserAccountController.class).getUserByEmail(null)).withRel("user_details"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserAccountController.class).getAllUser()).withRel("user_all"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserAccountController.class).getRegisterFormat()).withRel("user_registration"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserRoleController.class).getAllRole()).withRel("user_role_all"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserPrivilegeController.class).getAllPrivilege()).withRel("user_privilege_all"));

		return links;
	}
}

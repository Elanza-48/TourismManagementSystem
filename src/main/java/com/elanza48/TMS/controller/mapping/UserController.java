package com.elanza48.TMS.controller.mapping;

import java.util.*;

import com.elanza48.TMS.model.dto.*;
import com.elanza48.TMS.model.entity.UserPrivilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.elanza48.TMS.model.ModelDtoMapper;
import com.elanza48.TMS.model.entity.Booking;
import com.elanza48.TMS.model.entity.UserAccount;
import com.elanza48.TMS.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;

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

	@Caching(
			cacheable = @Cacheable(value = "TMSCache"),
			put = @CachePut(value = "TMSCache")
	)
	@GetMapping("/email/{email}")
	@Secured({"ROLE_USER"})
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<RepresentationModel<?>> getUserByEmail(@PathVariable String email) {
			return ResponseEntity.ok(CollectionModel.of(modelDtoMapper.userAccountModelToDto(
					userService.findUser(email).get()),getHyperLinks()));
	}

	@GetMapping("/email/{email}/address")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<RepresentationModel<?>> getUserAddress(@PathVariable String email) {
			return ResponseEntity.ok(CollectionModel.of(modelDtoMapper.userAccountModelToDto(
					userService.findUser(email).get()).getAddress(),getHyperLinks()));
	}

	@GetMapping("/email/{email}/booking")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<RepresentationModel<?>> getUserBooking(@PathVariable String email) {
			return ResponseEntity.ok(CollectionModel.of(modelDtoMapper.bookingModelToDtoList(
					new ArrayList<Booking>(userService.findUser(email).get().getBookings())
			),getHyperLinks()));
	}

	@GetMapping("/email/{email}/role")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<RepresentationModel<?>> getUserRole(@PathVariable String email) {

			return ResponseEntity.ok(CollectionModel.of(modelDtoMapper.userRoleModelToDto(
					userService.findUser(email).get().getRole()
			),getHyperLinks()));
	}

	@GetMapping("/email/{email}/role/privilege")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name or hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<RepresentationModel<?>> getUserRolePrivilege(@PathVariable String email) {
			return ResponseEntity.ok(CollectionModel.of(modelDtoMapper.userPrivilegeModelToDtoList(
					new ArrayList<UserPrivilege>(userService.findUser(email).get().getRole().getPrivileges())
			),getHyperLinks()));
	}

	@DeleteMapping("/email/{email}")
	@Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
	@PreAuthorize("#email == authentication.name")
	public ResponseEntity<String> deleteUserByEmail(@PathVariable String email){
		userService.deleteUser(email);
		return ResponseEntity.accepted().body("{\"message\": \"User Deleted.\"}");
	}
	
	@GetMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<RepresentationModel<?>> getAllUser() {
		return ResponseEntity.ok(CollectionModel.of(
				modelDtoMapper.userAccountModelToDtoList(userService.getAllUser()),getHyperLinks()));
	}

	private List<Link> getHyperLinks(){
		List<Link> links = new LinkedList<>();
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserController.class).getUserBooking(null)).withRel("userBooking"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserController.class).getUserRole(null)).withRel("userRole"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserController.class).getUserRolePrivilege(null)).withRel("userPrivilege"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserController.class).getUserAddress(null)).withRel("userAddress"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserController.class).getUserByEmail(null)).withRel("user"));
		links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UserController.class).getAllUser()).withRel("users"));

		return links;
	}

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<RepresentationModel<?>> accessDeniedHandler(
            AccessDeniedException e, HttpServletRequest request){

        Map<String, Object> body= new LinkedHashMap<>();
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
        body.put("message",  "Required Admin Privileges");
        return new ResponseEntity<>(CollectionModel.of(body,getHyperLinks()),HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler(NoSuchElementException.class)
	private ResponseEntity<RepresentationModel<?>> resourceNotFoundHandler(){
		Map<String, Object> body= new LinkedHashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
		body.put("message",  "Resource doesn't exists !");
		return new ResponseEntity<>(CollectionModel.of(body,getHyperLinks()),HttpStatus.NOT_FOUND);
	}
}

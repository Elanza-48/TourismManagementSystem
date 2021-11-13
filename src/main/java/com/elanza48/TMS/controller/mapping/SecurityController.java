package com.elanza48.TMS.controller.mapping;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.regex.Pattern;

import com.elanza48.TMS.security.JWTUtils;
import com.elanza48.TMS.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(produces = {"application/hal+json"})
@PreAuthorize("permitAll()")
public class SecurityController {

  private AuthenticationManager authenticationManager;
  private UserAccountService userAccountService;
  private JWTUtils jwtTokenUtils;

  @Autowired
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }
  @Autowired
  public void setUserAccountService(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }
  @Autowired
  public void setJwtTokenUtils(JWTUtils jwtTokenUtils) {
    this.jwtTokenUtils = jwtTokenUtils;
  }

  /**
   * Initial Welcome Message.
   * @return {@link ResponseEntity}
   */
  @GetMapping
  public ResponseEntity<RepresentationModel<?>> getMethodName() {

    Map<String, String> map = new LinkedHashMap<>();
    map.put("message:", "Welcome to the Tour Company !");

    List<Link> links = new LinkedList<>();
    links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
            SecurityController.class).authBodyFormat()).withRel("authenticate"));
    links.add(WebMvcLinkBuilder.linkTo(UserController.class).withRel("user"));
    links.add(WebMvcLinkBuilder.linkTo(PackageController.class).withRel("tourPackages"));
    links.add(WebMvcLinkBuilder.linkTo(HotelController.class).withRel("hotel"));
    links.add(WebMvcLinkBuilder.linkTo(DestinationController.class).withRel("destination"));
    links.add(WebMvcLinkBuilder.linkTo(SecurityController.class).withSelfRel());

    return ResponseEntity.ok(CollectionModel.of(map,links));
  }

  /**
   * provides the authentication body format to the client.
   * @return {@link ResponseEntity}
   */
  @GetMapping(value="/authenticate")
  public ResponseEntity<Map<String,String>> authBodyFormat(){
    Map<String, String> map = new LinkedHashMap<>();
    map.put("email", null);
    map.put("password", null);
    return ResponseEntity.ok(map);
  }

  /**
   * Gets authentication credentials from the user
   * authenticates user and returns JWT
   *
   * @return {@link ResponseEntity}
   */
  @PostMapping(value="/authenticate", consumes = "application/hal+json")
  public ResponseEntity<RepresentationModel<?>> generateAuthToken(@RequestBody Map<String, String> request) throws Exception {
    String emailPattern="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    Map<String, String> tokenMap = new LinkedHashMap<>();

    try{
      if(!Pattern.compile(emailPattern).matcher(request.get("email")).matches())
        throw new BadCredentialsException("Malformed Email id !");

      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password"))
      );
    }catch(BadCredentialsException e){
      throw new Exception("Incorrect credentials !", e);
    }
    String token=jwtTokenUtils.generateToken(userAccountService.findUser(request.get("email")).get());
    tokenMap.put("token", token);
    tokenMap.put("expiresOn", jwtTokenUtils.extractClaims(token).get("exp").asDate().toInstant()
            .atZone(ZoneId.of("Asia/Kolkata"))
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));

    List<Link> links = new LinkedList<>();
    links.add(WebMvcLinkBuilder.linkTo(SecurityController.class).withRel("home"));
    links.add(WebMvcLinkBuilder.linkTo(UserController.class).withRel("user"));
    links.add(WebMvcLinkBuilder.linkTo(PackageController.class).withRel("tourPackages"));
    links.add(WebMvcLinkBuilder.linkTo(HotelController.class).withRel("hotel"));
    links.add(WebMvcLinkBuilder.linkTo(DestinationController.class).withRel("destination"));
    links.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
            SecurityController.class).authBodyFormat()).withSelfRel());
    return ResponseEntity.accepted().body(CollectionModel.of(tokenMap,links));
  }
}

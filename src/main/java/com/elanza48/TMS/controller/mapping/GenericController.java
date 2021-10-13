package com.elanza48.TMS.controller.mapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.elanza48.TMS.security.JWTUtils;
import com.elanza48.TMS.service.AuthenticationRequest;
import com.elanza48.TMS.service.AuthenticationResponse;
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
@RequestMapping
public class GenericController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private JWTUtils jwtTokenUtils;

  @GetMapping(produces = {"application/hal+json"})
  public Map<String, String> getMethodName() {

    Map<String, String> map = new HashMap<>();
    map.put("message:", "Welcome to the Tour Company !");
    map.put("link", "./authenticate");
    map.put("link", "./user");
    return map;
  }

  @GetMapping(value="/authenticate")
  public AuthenticationRequest authBodyFormat(){
    return new AuthenticationRequest();
  }

  @PostMapping(value="/authenticate")
  public ResponseEntity<?> generateAuthToken(@RequestBody AuthenticationRequest request) throws Exception {

    try{
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );
    }catch(BadCredentialsException e){
      throw new Exception("Incorrect credentials !", e);
    }
    
    return ResponseEntity.ok(new AuthenticationResponse(
      jwtTokenUtils.genrateToken(
        userAccountService.findUser(request.getEmail())
      )
    ));   
  }
}

package com.elanza48.TMS.controller.mapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.elanza48.TMS.controller.service.AuthenticationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

  // @Autowired
  // AuthenticationManager authenticationManager;

  @GetMapping(produces = {"application/hal+json"})
  public Map<String, String> getMethodName() {

    Map<String, String> map = new HashMap<>();
    map.put("message:", "Welcome to the Tour Company !");
    map.put("link", "./user");
    return map;
  }

  // @PostMapping(value="/authenticate")
  // public ResponseEntity<?> generateAuthToken(@RequestBody AuthenticationRequest request) throws Exception {

  //   try{
  //     authenticationManager.authenticate(
  //       new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
  //     );
  //   }catch(BadCredentialsException e){
  //     throw new Exception("Bad credentials !",e);
  //   }
  //   //TODO: implement method.
  //   return new ResponseEntity<String>("json.web.tokwn",HttpStatus.CREATED);
      
  // }
  
  
}

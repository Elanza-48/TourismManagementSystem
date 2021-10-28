package com.elanza48.TMS.controller.mapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
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
@RequestMapping
public class SecurityController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private JWTUtils jwtTokenUtils;

  /**
   * Initial Welcome Message.
   * @return
   */

  @GetMapping(produces = {"application/hal+json"})
  public ResponseEntity<Map<String,String>> getMethodName() {

    Map<String, String> map = new HashMap<>();
    map.put("message:", "Welcome to the Tour Company !");
    map.put("link", "./authenticate");
    map.put("link", "./user");
    return ResponseEntity.ok(map);
  }

  /**
   * provides the authentication body format to the client.
   * @return {@link ResponseEntity}
   */
  @GetMapping(value="/authenticate")
  public ResponseEntity<Map<String,String>> authBodyFormat(){
    Map<String, String> map = new HashMap<>();
    map.put("email", null);
    map.put("password", null);
    return ResponseEntity.ok(map);
  }

  /**
   * Gets authentication credentials from the user
   * authentcates user and returns JWT
   * valid for 12 hours.
   * 
   * @param request
   * @return {@link ResponseEntity}
   * @throws Exception
   */
  @PostMapping(value="/authenticate")
  public ResponseEntity<Map<String,String>> generateAuthToken(@RequestBody Map<String, String> request) throws Exception {
    String emailPattern="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    Map<String, String> token = new HashMap<>();

    try{
      if(!Pattern.compile(emailPattern).matcher(request.get("email")).matches())
        throw new BadCredentialsException("Malformed Email id !");

      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password"))
      );
    }catch(BadCredentialsException e){
      throw new Exception("Incorrect credentials !", e);
    }
    token.put("jwt", jwtTokenUtils.genrateToken(userAccountService.findUser(request.get("email")).get()));
    token.put("validity", "12 hours");

    return ResponseEntity.accepted().body(token);   
  }
}

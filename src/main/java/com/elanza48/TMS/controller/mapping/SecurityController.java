package com.elanza48.TMS.controller.mapping;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
  public ResponseEntity<Map<String,String>> getMethodName() {

    Map<String, String> map = new HashMap<>();
    map.put("message:", "Welcome to the Tour Company !");
    map.put("link", "./authenticate");
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
   * authenticates user and returns JWT
   * valid for 12 hours.
   *
   * @return {@link ResponseEntity}
   */
  @PostMapping(value="/authenticate")
  public ResponseEntity<Map<String,String>> generateAuthToken(@RequestBody Map<String, String> request) throws Exception {
    String emailPattern="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    Map<String, String> tokenMap = new HashMap<>();

    try{
      if(!Pattern.compile(emailPattern).matcher(request.get("email")).matches())
        throw new BadCredentialsException("Malformed Email id !");

      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password"))
      );
    }catch(BadCredentialsException e){
      throw new Exception("Incorrect credentials !", e);
    }
    String token=jwtTokenUtils.genrateToken(userAccountService.findUser(request.get("email")).get());
    tokenMap.put("token", token);
    tokenMap.put("expiresOn", jwtTokenUtils.extractClaims(token).get("exp").asDate().toInstant()
            .atZone(ZoneId.of("Asia/Kolkata"))
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
    return ResponseEntity.accepted().body(tokenMap);
  }
}

package com.elanza48.TMS.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationRequest {
  private String email;
  private String password;
  
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  
}

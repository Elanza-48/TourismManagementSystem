package com.elanza48.TMS.controller.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationResponse {

  private String jwt;

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}

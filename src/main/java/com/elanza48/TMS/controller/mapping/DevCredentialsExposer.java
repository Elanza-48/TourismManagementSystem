package com.elanza48.TMS.controller.mapping;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.elanza48.TMS.security.CipherUtilsDev;
import com.elanza48.TMS.security.JWTUtils;


@Component
@Profile("dev")
@Endpoint(id="jwt-credentials")
public class DevCredentialsExposer {

  private JWTUtils jwtUtils=null;
  private CipherUtilsDev cipherUtils=null;

  @Autowired
  public void setJwtUtils(JWTUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @Autowired
  public void setCipherUtils(CipherUtilsDev cUtils){
    this.cipherUtils=cUtils;
  }
  
  @ReadOperation
  public String JWTSigningKeys() throws IOException{

    Map<String,String> pemKeyPair = cipherUtils.keyPairToPEM(jwtUtils.getJWTSignatureKeyPair());
    StringBuilder buffer = new StringBuilder();

    buffer.append(pemKeyPair.get("public"));
    buffer.append("\n\n");
    buffer.append(pemKeyPair.get("private"));
    return buffer.toString();
  }
}

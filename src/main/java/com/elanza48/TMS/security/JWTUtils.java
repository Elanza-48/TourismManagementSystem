package com.elanza48.TMS.security;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.elanza48.TMS.model.dto.UserAccount;

import org.springframework.stereotype.Service;

@Service
public class JWTUtils {

  private ECPublicKey publicKey=null;
  private ECPrivateKey privateKey=null;
  private Algorithm algorithm=null;

  private void initUtils(){
    try{
      if(this.publicKey==null){
        this.publicKey = (ECPublicKey) PemUtils.readPublicKeyFromFile("src/main/resources/public.pem","EC");
      }
      if(this.privateKey==null){
        this.privateKey = (ECPrivateKey) PemUtils.readPrivateKeyFromFile("src/main/resources/private.pem","EC");
      }if(this.algorithm==null){
        algorithm = Algorithm.ECDSA512(publicKey,privateKey);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public String genrateToken(UserAccount user){
    Map<String, Object> payload= new HashMap<>();
    payload.put("email", user.getEmail());
    payload.put("role", user.getRole().toString());

    return createJWToken(payload, user.getName());
  }

  private String createJWToken( Map<String, Object> payload, String subject){
    String token=null;
    try{
      initUtils();
      token = JWT.create()
         .withPayload(payload)
         .withIssuedAt(new Date())
         .withSubject(subject)
         .withIssuer("elanza48").sign(algorithm);
    }catch(JWTCreationException e){
      e.printStackTrace();
    }
    return token;
    
  } 
  
  private Map<String, Claim> verifyJWToken(String token){

    DecodedJWT decodedJWT=null;
    try{
      initUtils();
      JWTVerifier verifier = JWT.require(algorithm).build();
      decodedJWT= verifier.verify(JWT.decode(token));

    }catch(JWTVerificationException e){
      e.printStackTrace();
    }
    return decodedJWT.getClaims();
  }

  public boolean validateToken(String token, UserAccount user){
    Date issuedAt = verifyJWToken(token).get("iat").asDate();
    String email = verifyJWToken(token).get("email").asString();
    String role = verifyJWToken(token).get("role").asString();
    int duration = (int) TimeUnit.MILLISECONDS.toMinutes(new Date().getTime()-issuedAt.getTime())%60;

    if(user.getEmail().equals(email) && user.getRole().toString().equals(role) && duration<=5) return true;
    else return false; 
  }

  public String extractEmail(String jwt){
    try{
      return JWT.decode(jwt).getClaim("email").asString();
    }catch(JWTDecodeException e){
      e.printStackTrace();
      return null;
    }
  }
  
}

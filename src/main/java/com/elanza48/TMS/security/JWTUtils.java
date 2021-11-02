package com.elanza48.TMS.security;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.elanza48.TMS.model.entity.UserAccount;

import org.springframework.stereotype.Service;

@Service
public class JWTUtils {

  private final ZoneId ZONE= ZoneOffset.UTC;
  private final int DURATION=12;

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
    payload.put("role", user.getRole().getName());

    return createJWToken(payload, user.getName());
  }

  private String createJWToken( Map<String, Object> payload, String subject){
    String token=null;

    LocalDateTime issuedTime= LocalDateTime.now(ZONE);
    LocalDateTime expirationTime = issuedTime.plusHours(DURATION);
    try{
      initUtils();
      token = JWT.create()
         .withPayload(payload)
         .withIssuedAt(Date.from(issuedTime.atZone(ZONE).toInstant()))
         .withExpiresAt(Date.from(expirationTime.atZone(ZONE).toInstant()))
         .withSubject(subject)
         .withIssuer("elanza48").sign(algorithm);

    }catch(JWTCreationException e){
      e.printStackTrace();
    }
    return token;
    
  } 
  
  private Optional<Map<String, Claim>> verifyJWToken(String token){

    DecodedJWT decodedJWT=null;
    try {
      initUtils();
      JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(60).build();
      decodedJWT = verifier.verify(JWT.decode(token));
    }catch(JWTVerificationException e){
      e.printStackTrace();
      return Optional.empty();
    }
    return Optional.of(decodedJWT.getClaims());
  }

  public boolean validateToken(String token, UserAccount user){

    if (verifyJWToken(token).isEmpty()) return false;

    String email = verifyJWToken(token).get().get("email").asString();
    String role = verifyJWToken(token).get().get("role").asString();
    return (user.getEmail().equals(email) && user.getRole().getName().equals(role));
  }

  public Map<String,Claim> extractClaims(String jwt){
    try{
      return JWT.decode(jwt).getClaims();
    }catch(JWTDecodeException e){
      e.printStackTrace();
      return null;
    }
  }

}

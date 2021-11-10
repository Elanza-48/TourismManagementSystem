package com.elanza48.TMS.security;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.elanza48.TMS.model.entity.UserAccount;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JWTUtils {

  private final ZoneId ZONE= ZoneOffset.UTC;

  @Value("${webtoken.jwt.validity.duration-hours:12}")
  private int duration=12;

  @Value("${webtoken.jwt.encrytion.ecdsa.private-key}")
  private Resource privateKeyPath;

  @Value("${webtoken.jwt.encrytion.ecdsa.public-key}")
  private Resource publicKeyPath;


  private ECPublicKey publicKey=null;
  private ECPrivateKey privateKey=null;
  private Algorithm algorithm=null;

  private void initUtils(){
    final String ASYMMETRIC_ALGORITHM = "EC";
    try{
      System.out.println(publicKeyPath.getFile().getAbsolutePath());
      if(this.publicKey==null){
        this.publicKey = (ECPublicKey) PemUtils.readPublicKeyFromFile(
                publicKeyPath.getFile(), ASYMMETRIC_ALGORITHM);
      }
      if(this.privateKey==null){
        this.privateKey = (ECPrivateKey) PemUtils.readPrivateKeyFromFile(
                privateKeyPath.getFile(), ASYMMETRIC_ALGORITHM);
      }if(this.algorithm==null){
        algorithm = Algorithm.ECDSA512(publicKey,privateKey);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public String generateToken(UserAccount user){
    Map<String, Object> payload= new HashMap<>();
    payload.put("email", user.getEmail());
    payload.put("role", user.getRole().getName());

    return createJWToken(payload, user.getName());
  }

  private String createJWToken( Map<String, Object> payload, String subject){
    String token=null;

    LocalDateTime issuedTime= LocalDateTime.now(ZONE);
    LocalDateTime expirationTime = issuedTime.plusHours(duration);
    try{
      initUtils();
      token = JWT.create()
         .withPayload(payload)
         .withIssuedAt(Date.from(issuedTime.atZone(ZONE).toInstant()))
         .withExpiresAt(Date.from(expirationTime.atZone(ZONE).toInstant()))
         .withSubject(subject)
         .withIssuer("elanza48").sign(algorithm);

    }catch(JWTCreationException e){
      log.error("JWT: [status: error, user:{}, message: {}]",payload.get("email") ,e.getLocalizedMessage());
    }
    log.info("JWT: [status: success, user:{}, message: Generated token.]", payload.get("email"));
    return token;
    
  } 
  
  private Optional<Map<String, Claim>> verifyJWToken(String token){

    DecodedJWT decodedJWT=null;
    try {
      initUtils();
      JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(60).build();
      decodedJWT = verifier.verify(JWT.decode(token));
    }catch(JWTVerificationException e){
      log.error("JWT: [status: error, message: {}]",e.getLocalizedMessage());
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
      log.error("JWT: [status: error, message: {}]",e.getLocalizedMessage());
      return null;
    }
  }

}

package com.elanza48.TMS.security;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


/**
 * Utility class to handle JWT tokens.
 *
 * @author Elanza-48
 */
@Log4j2
@Service
public class JWTUtils {

  private static final ZoneId ZONE= ZoneOffset.UTC;

  private int duration;
  private KeyPair keyPair=null;
  private Algorithm algorithm=null;
  private CipherUtilsBase cipherUtils=null;
  private Environment environment=null;


  @Autowired
  public void setDuration(@Value("${webtoken.jwt.validity.duration-hours:12}") int duration) {
    this.duration = duration;
  }

  @Autowired 
  public void setCipherUtils(CipherUtilsBase cipherUtils) {
    this.cipherUtils = cipherUtils;
  }

  @Autowired
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  private void initUtils(){
    try{
      if(this.keyPair==null){
        this.keyPair = this.cipherUtils.getEC512KeyPair();

      }if(this.algorithm==null){
        this.algorithm = Algorithm.ECDSA512(
                (ECPublicKey) keyPair.getPublic(),
                (ECPrivateKey) keyPair.getPrivate()
        );
      }
    }catch(Exception e){
      log.error("JWT : [message :{}]",e.getLocalizedMessage());
    }
  }

  private String generateJwtId(String data){  
    String id = this.cipherUtils.hashSHA256(data);
        
    if(id==null) throw new NullPointerException("JWT-ID couldn't be generated !");
    return id;
  }

  public String generateToken(UserAccount user){

    Map<String, Object> payload= new HashMap<>();
    payload.put("email", user.getEmail());
    payload.put("role", user.getRole().getName());

    return createJWToken(payload, generateJwtId(String.format("%s.%s",
            user.getId(), user.getEmail())), user.getName());
  }

  private String createJWToken( Map<String, Object> payload, String jwtId,String subject){
    String token=null;

    LocalDateTime issuedTime= LocalDateTime.now(ZONE);
    LocalDateTime expirationTime = issuedTime.plusHours(this.duration);
    try{
      initUtils();
      token = JWT.create()
         .withPayload(payload)
         .withJWTId(jwtId)
         .withIssuedAt(Date.from(issuedTime.atZone(ZONE).toInstant()))
         .withExpiresAt(Date.from(expirationTime.atZone(ZONE).toInstant()))
         .withSubject(subject)
         .withIssuer("auth0").sign(this.algorithm);

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
      JWTVerifier verifier = JWT.require(this.algorithm).acceptExpiresAt(60).build();
      decodedJWT = verifier.verify(JWT.decode(token));
    }catch(JWTVerificationException e){
      log.error("JWT: [status: error, message: {}]",e.getLocalizedMessage());
      return Optional.empty();
    }
    return Optional.of(decodedJWT.getClaims());
  }

  public boolean validateToken(String token, UserAccount user){
    if (verifyJWToken(token).isEmpty()) return false;
    Map<String, Claim> decodedToken = verifyJWToken(token).get();

    String email = decodedToken.get("email").asString();
    String role = decodedToken.get("role").asString();
    String jid= decodedToken.get("jti").asString();

    return (user.getEmail().equals(email) && user.getRole().getName().equals(role) &&
      generateJwtId(String.format("%s.%s",user.getId(), user.getEmail())).equals(jid));
  }

  public Map<String,Claim> extractClaims(String jwt){
    try{
      return JWT.decode(jwt).getClaims();
    }catch(JWTDecodeException e){
      log.error("JWT: [status: error, message: {}]",e.getLocalizedMessage());
      return new LinkedHashMap<>();
    }
  }

  public KeyPair getJWTSignatureKeyPair(){
    initUtils();
    boolean isDevProfile=Arrays.stream(environment.getActiveProfiles()).anyMatch("dev"::equals);
    return isDevProfile?this.keyPair:null;
  }

}

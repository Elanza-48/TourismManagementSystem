package com.elanza48.TMS.security;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


/**
 * Utility class to handle JWT tokens.
 *
 * @author Elanza-48
 */
@Slf4j
@Service
public class JWTUtils {

  private final ZoneId ZONE= ZoneOffset.UTC;

  @Value("${webtoken.jwt.validity.duration-hours:12}")
  private int duration;

  @Value("${webtoken.jwt.encrytion.ecdsa.key-pair}")
  private Resource keyPair;


  private ECPublicKey publicKey=null;
  private ECPrivateKey privateKey=null;
  private Algorithm algorithm=null;

  private CipherUtils cipherUtils=null;

  @Autowired
  public void setCipherUtils(CipherUtils cipherUtils) {
    this.cipherUtils = cipherUtils;
  }

  private void initUtils(){
    try{
      if(this.publicKey==null){
        this.publicKey =  (ECPublicKey) cipherUtils.getECKeyVal(
                this.keyPair.getFile()).getPublic();
      }
      if(this.privateKey==null){
        this.privateKey = (ECPrivateKey) cipherUtils.getECKeyVal(
                this.keyPair.getFile()).getPrivate();
      }if(this.algorithm==null){
        this.algorithm = Algorithm.ECDSA512(publicKey,privateKey);
      }
    }catch(IOException e){
      log.error("JWT : [message :{}]",e.getLocalizedMessage());
    }
  }

  private String generateJwtId(String data){
    String id = cipherUtils.hashSHA256(data);
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
         .withIssuer("elanza48").sign(this.algorithm);

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

    return (user.getEmail().equals(email) && user.getRole().getName().equals(role));
    //TODO: temporarily jwt id check disabled.
//            && generateJwtId(String.format("%s.%s",user.getId(), user.getEmail())).equals(jid));
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

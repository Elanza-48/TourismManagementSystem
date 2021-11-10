package com.elanza48.TMS.controller.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elanza48.TMS.security.JWTUtils;
import com.elanza48.TMS.service.UserAccountService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter{

  private UserAccountService userAccountService;
  private JWTUtils jwtUtils;

  @Autowired
  public void setUserAccountService(UserAccountService userAccountService) {
      this.userAccountService = userAccountService;
  }
  @Autowired
  public void setJwtUtils(JWTUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

    @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

        final String authHeader= request.getHeader("Authorization");
        String email=null, jwt=null;


        if(authHeader !=null && authHeader.startsWith("Bearer ")){
          jwt=authHeader.substring(7);
          email=jwtUtils.extractClaims(jwt).get("email").asString();
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication()==null){
          UserDetails credentialDetails = userAccountService.loadUserByUsername(email);

          if(jwtUtils.validateToken(jwt, userAccountService.findUser(email).get())){
            UsernamePasswordAuthenticationToken userAuthToken= new UsernamePasswordAuthenticationToken(
              credentialDetails.getUsername(), credentialDetails.getPassword(),credentialDetails.getAuthorities());
              userAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(userAuthToken);
              log.info("LOGIN: [status: success, user: {}, role: {}]", credentialDetails.getUsername(),
                      credentialDetails.getAuthorities());
          }
        }
    filterChain.doFilter(request, response);
  }
  
}

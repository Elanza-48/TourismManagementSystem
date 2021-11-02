package com.elanza48.TMS.controller.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elanza48.TMS.security.JWTUtils;
import com.elanza48.TMS.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private JWTUtils jwtUtils;
  

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
          }
        }
    filterChain.doFilter(request, response);
  }
  
}

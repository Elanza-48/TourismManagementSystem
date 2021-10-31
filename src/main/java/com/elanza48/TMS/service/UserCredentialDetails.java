package com.elanza48.TMS.service;

import java.util.Arrays;
import java.util.Collection;

import com.elanza48.TMS.model.entity.UserAccount;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserCredentialDetails implements UserDetails{

  private UserAccount user;

  public UserCredentialDetails(){}

  public UserCredentialDetails(UserAccount user){
    this.user=user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName()));
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    if(!user.isActive() && user.isSuspended())
      return false;
    else return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    if(user.isActive() && user.isSuspended())
      return false;
    else return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    if(!isAccountNonExpired())
      return false;
    else return true;
  }

  @Override
  public boolean isEnabled() {
    if(user.isActive())
      return true;
    else return false;
  }
  
}

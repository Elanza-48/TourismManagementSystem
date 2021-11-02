package com.elanza48.TMS.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.elanza48.TMS.model.entity.UserAccount;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
public class UserCredentialDetails implements UserDetails{

  private UserAccount user;

  public UserCredentialDetails(UserAccount user){
    this.user=user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List <SimpleGrantedAuthority> rolesAndPermissions= user.getRole().getPrivileges().stream()
            .map(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
    rolesAndPermissions.add(new SimpleGrantedAuthority(user.getRole().getDescription()));

    return rolesAndPermissions;
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
    return (user.isActive() || !user.isSuspended());
  }

  @Override
  public boolean isAccountNonLocked() {
    return (!user.isActive() || !user.isSuspended());
  }

  @Override
  public boolean isCredentialsNonExpired() {
    int duration=1;
    LocalDateTime lastUpdate = user.getMetaData().getUpdateTimestamp().toLocalDateTime();

    return (isAccountNonExpired() ||
            ChronoUnit.YEARS.between(lastUpdate, LocalDateTime.now(ZoneOffset.UTC)) >= duration);
  }

  @Override
  public boolean isEnabled() {
    return user.isActive();
  }
  
}

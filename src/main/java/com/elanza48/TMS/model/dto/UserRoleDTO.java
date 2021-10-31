package com.elanza48.TMS.model.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleDTO extends IdentityNameDTO{

  private String description;
  Set<UserPrivilegeDTO> privileges;

  public UserRoleDTO(String name, String description) {
    super(name);
    this.description = description;
  }

  @Override
  public String getName() {
    return super.getName();
  }
  
  @Override
  public void setName(String name) {
    super.setName(name);
  }
  @Override
  public String toString() {
    return "UserRole [description=" + description + ", privileges=" + privileges + "]";
  }

}
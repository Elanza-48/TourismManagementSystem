package com.elanza48.TMS.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class UserPrivilegeDTO extends IdentityNameDTO implements Serializable {

  private String description;

  public UserPrivilegeDTO(String name, String description) {
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
    return "UserPrivilegeDTO [description=" + description + "]";
  }
}

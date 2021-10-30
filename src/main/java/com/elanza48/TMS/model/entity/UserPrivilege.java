package com.elanza48.TMS.model.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_privilege")
@AttributeOverride(name = "name", 
  column = @Column(name = "title", unique = true))
public class UserPrivilege extends IdentityName {

  @Column
  private String description;

  @Embedded
  @NotNull
  private MetaData metaData= new MetaData();

  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "privileges")
  private Set<UserRole> roles;


  public UserPrivilege(){}
  public UserPrivilege(@NotNull String name, String description) {
    super(name);
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }

  public MetaData getMetaData() {
		return metaData;
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
    return "UserPrivilege [description=" + description + ", roles=" + roles + "]";
  }
}

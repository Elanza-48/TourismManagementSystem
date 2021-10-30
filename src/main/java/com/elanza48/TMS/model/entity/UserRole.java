package com.elanza48.TMS.model.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_role")
@AttributeOverride(name = "name", 
  column = @Column(name = "title", unique = true))
public class UserRole extends IdentityName{

  @Column
  private String description;

  @Embedded
  @NotNull
  private MetaData metaData = new MetaData();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role_privilege_map", 
    joinColumns = {@JoinColumn(name = "role_id") }, 
    inverseJoinColumns = { @JoinColumn(name = "privilege_id") }
  )
  Set<UserPrivilege> privileges;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
  private Set<UserAccount> users;


  public UserRole(){}
  public UserRole(@NotNull String name, String description) {
    super(name);
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<UserPrivilege> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(Set<UserPrivilege> privileges) {
    this.privileges = privileges;
  }

  public Set<UserAccount> getUsers() {
    return users;
  }

  public void setUsers(Set<UserAccount> users) {
    this.users = users;
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
    return "UserRole [description=" + description + ", privileges=" + privileges + "]";
  }

}
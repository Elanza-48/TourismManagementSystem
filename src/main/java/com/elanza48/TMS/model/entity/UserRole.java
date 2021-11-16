package com.elanza48.TMS.model.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "user_role")
@AttributeOverride(name = "name", 
  column = @Column(name = "title", unique = true))
public class UserRole extends IdentityName implements Serializable {

  public enum ROLES {
    ADMIN, MANAGER, USER, ALL;

    public String getRole(){
      return "ROLE_"+name();
    }
  };

  @Column
  private String description;

  @Embedded
  @NotNull
  private MetaData metaData = new MetaData();

  @ManyToMany(cascade = {
          CascadeType.MERGE,
          CascadeType.REFRESH
  }, fetch = FetchType.EAGER)
  @JoinTable(name = "user_role_privilege_map", 
    joinColumns = {@JoinColumn(name = "role_id") }, 
    inverseJoinColumns = { @JoinColumn(name = "privilege_id") }
  )
  @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
  Set<UserPrivilege> privileges;

  @OneToMany(cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE,
          CascadeType.REFRESH
  }, mappedBy = "role")
  @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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
package com.elanza48.TMS.model.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "user_privilege")
@AttributeOverride(name = "name", 
  column = @Column(name = "title", unique = true))
public class UserPrivilege extends IdentityName {

  @Column
  private String description;

  @Embedded
  @NotNull
  private MetaData metaData= new MetaData();

  @ManyToMany(cascade = {
          CascadeType.MERGE,
          CascadeType.REFRESH
  },
          mappedBy = "privileges",
          fetch = FetchType.EAGER
  )
  @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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
    return "UserPrivilege [description=" + description + "]";
  }
}

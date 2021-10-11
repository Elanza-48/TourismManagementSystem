package com.elanza48.TMS.model.dto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
abstract class Identity {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(updatable = false)
	@Type(type = "org.hibernate.type.PostgresUUIDType")
	protected UUID id;
	
	public Identity() {}
	public Identity(UUID id) {
		super();
		this.id = id;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
}

@MappedSuperclass
abstract class IdentityName extends Identity{
	
	@Column
	@NotNull
	protected String name;
	
	public IdentityName(){}
	public IdentityName(@NotNull String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

package com.elanza48.TMS.model.dto;

import java.util.UUID;

import javax.persistence.Id;

abstract class IdentityDTO {

	@Id
	protected UUID id;
	
	public IdentityDTO() {}
	public IdentityDTO(UUID id) {
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

abstract class IdentityNameDTO extends IdentityDTO{
	protected String name;
	
	public IdentityNameDTO(){}
	public IdentityNameDTO(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

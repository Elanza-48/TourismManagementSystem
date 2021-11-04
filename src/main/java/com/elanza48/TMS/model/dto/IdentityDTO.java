package com.elanza48.TMS.model.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
abstract class IdentityDTO implements Serializable {

	@Id
	protected UUID id;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
}


@AllArgsConstructor
@NoArgsConstructor
abstract class IdentityNameDTO extends IdentityDTO implements Serializable{
	protected String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

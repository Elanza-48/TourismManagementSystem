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
	@Type(type = "pg-uuid")
	protected UUID id;
}

@MappedSuperclass
abstract class IdentityName extends Identity{
	
	@Column
	@NotNull
	protected String name;
}

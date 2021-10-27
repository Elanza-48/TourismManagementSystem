package com.elanza48.TMS.model.dto;

import com.elanza48.TMS.model.entity.Transport.TransportEntity;
import com.elanza48.TMS.model.entity.Transport.TransportMode;

public class TransportDTO extends IdentityNameDTO{
	
	private String description;
	private TransportMode mode = TransportMode.CAR;
	private TransportEntity entity= TransportEntity.PRIVATE;
	
	
	public TransportDTO() {}

	public TransportDTO(String name, String description, TransportMode mode, TransportEntity entity) {
		super(name);
		this.description = description;
		this.mode = mode;
		this.entity = entity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TransportMode getMode() {
		return mode;
	}

	public void setMode(TransportMode mode) {
		this.mode = mode;
	}

	public TransportEntity getEntity() {
		return entity;
	}

	public void setEntity(TransportEntity entity) {
		this.entity = entity;
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
		return "Transport [description=" + description + ", mode=" + mode + ", entity=" + entity + 
		", name=" + name + ", id=" + id + "]";
	}
	
}

package com.elanza48.TMS.model.dto;

import com.elanza48.TMS.model.entity.Transport.TransportMode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TransportDTO extends IdentityNameDTO{
	
	private String description;
	private TransportMode mode = TransportMode.CAR;
	private boolean isPublic;
	

	public TransportDTO(String name, String description, TransportMode mode, boolean isPublic) {
		super(name);
		this.description = description;
		this.mode = mode;
		this.isPublic = isPublic;
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
		return "Transport [description=" + description + ", mode=" + mode + ", public=" + isPublic + 
		", name=" + name + ", id=" + id + "]";
	}
	
}

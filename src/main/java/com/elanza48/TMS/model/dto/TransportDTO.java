package com.elanza48.TMS.model.dto;

import com.elanza48.TMS.model.entity.Transport.TransportMode;

public class TransportDTO extends IdentityNameDTO{
	
	private String description;
	private TransportMode mode = TransportMode.CAR;
	private boolean isPublic;
	
	
	public TransportDTO() {}
	public TransportDTO(String name, String description, TransportMode mode, boolean isPublic) {
		super(name);
		this.description = description;
		this.mode = mode;
		this.isPublic = isPublic;
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

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
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

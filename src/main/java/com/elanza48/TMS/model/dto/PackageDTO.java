package com.elanza48.TMS.model.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PackageDTO extends IdentityNameDTO implements Serializable {
	
	private String description;
	private String activities;
	private String events;
	private boolean isActive;
	private Set<DestinationDTO> destinations;
	private Set<TransportDTO> transports;
	

	public PackageDTO(String name, String description, String activities, String events) {
		super(name);
		this.description = description;
		this.activities = activities;
		this.events = events;
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
		return "PackageDTO [description=" + description + ", activities=" + activities + ", events=" + events
				+ ", isActive=" + isActive + ", destinations=" + destinations + ", transports=" + transports
				+ ", name=" + name + ", id=" + id + "]";
	}

}

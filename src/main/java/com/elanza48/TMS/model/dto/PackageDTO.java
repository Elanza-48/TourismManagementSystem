package com.elanza48.TMS.model.dto;

import java.util.Set;

public class PackageDTO extends IdentityNameDTO{
	
	private String description;
	private String activities;
	private String events;
	private boolean isActive;
	private Set<DestinationDTO> destinations;
	private Set<TransportDTO> transports;
	
	public PackageDTO() {}

	public PackageDTO(String name, String description, String activities, String events) {
		super(name);
		this.description = description;
		this.activities = activities;
		this.events = events;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<DestinationDTO> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<DestinationDTO> destinations) {
		this.destinations = destinations;
	}

	public Set<TransportDTO> getTransports() {
		return transports;
	}

	public void setTransports(Set<TransportDTO> transports) {
		this.transports = transports;
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
		return "Package [description=" + description + ", activities=" + activities + ", events=" + events
				+ ", isActive=" + isActive + ", destinations=" + destinations + ", transports=" + transports
				+ ", name=" + name + ", id=" + id + "]";
	}

}

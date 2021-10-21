package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "tour_Package")
public class Package extends IdentityName{
	
	@Column
	@Type(type = "text")
	private String description;
	
	@Column
	private String activities;
	
	@Column
	private String events;
	
	@Column
	@NotNull
	private boolean isActive=true;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "package_destination_map",
		joinColumns = {@JoinColumn(name="pkg_id")},
		inverseJoinColumns = {@JoinColumn(name="dest_id")}
	)
	@JsonBackReference
	private Set<Destination> destinations;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "package_transport_map",
		joinColumns = {@JoinColumn(name="pkg_id")},
		inverseJoinColumns = {@JoinColumn(name="transport_id")}
	)
	@JsonBackReference
	private Set<Transport> transports;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "packageId")
	@JsonManagedReference
	private Set<Booking> bookings;
	
	public Package() {
		super();
	}

	public Package(@NotNull String name, String description, String activities, String events) {
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

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
	}

	public Set<Transport> getTransports() {
		return transports;
	}

	public void setTransports(Set<Transport> transports) {
		this.transports = transports;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
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
				+ ", bookings=" + bookings + ", name=" + name + ", id=" + id + "]";
	}

}

package com.elanza48.TMS.model.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tour_Package")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Package extends IdentityName {
	
	@Column
	@Type(type = "text")
	private String description;
	
	@Column
	private String activities;
	
	@Column
	private String events;
	
	@Column
	@NotNull
	private boolean active=true;

	@Embedded
	@NotNull
	private MetaData metaData=new MetaData();
	
	@ManyToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
	)
	@JoinTable(name = "package_destination_map",
		joinColumns = {@JoinColumn(name="pkg_id")},
		inverseJoinColumns = {@JoinColumn(name="dest_id")}
	)
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Destination> destinations;
	
	@ManyToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
	)
	@JoinTable(name = "package_transport_map",
		joinColumns = {@JoinColumn(name="pkg_id")},
		inverseJoinColumns = {@JoinColumn(name="transport_id")}
	)
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Transport> transports;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "packageId",
			fetch = FetchType.EAGER
	)
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Booking> bookings;
	
	public Package() {}
	public Package(@NotNull String name, String description, String activities, 
		String events) {
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
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public MetaData getMetaData() {
		return metaData;
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
				+ ", isActive=" + active + ", destinations=" + destinations + ", transports=" + transports
				+ ", bookings=" + bookings + ", name=" + name + ", id=" + id + "]";
	}

}

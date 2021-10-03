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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tour_Package")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Package extends IdentityName{
	
	@Column
	@Type(type = "text")
	private String description;
	
	@Column
	private String activities;
	
	@Column
	private String events;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "package_destination_map",
		joinColumns = {@JoinColumn(name="pkg_id")},
		inverseJoinColumns = {@JoinColumn(name="dest_id")}
	)
	private Set<Destination> destinations;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "package_transport_map",
		joinColumns = {@JoinColumn(name="pkg_id")},
		inverseJoinColumns = {@JoinColumn(name="transport_id")}
	)
	private Set<Transport> transports;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "packageId")
	private Set<Booking> bookings;

}

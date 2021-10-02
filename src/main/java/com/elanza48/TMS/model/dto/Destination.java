package com.elanza48.TMS.model.dto;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "destination_Info")
public class Destination {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(updatable = false)
	@Type(type = "pg-uuid")
	private UUID id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private String province;
	
	@Column
	@Type(type = "text")
	private String description;
	
	@Column(name = "MAX_STAY_DURATION")
	@Size(min = 1, max=2)
	@NotNull
	private int stayDuration=1;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "destinationId")
	private Set<Hotel> hotels;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "destinations")
	private Set<Package> packages;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "destinationId")
	private Set<DestinationReview> reviews; 

}

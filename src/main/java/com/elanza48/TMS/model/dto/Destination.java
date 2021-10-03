package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "destination_Info")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Destination extends IdentityName{
	
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

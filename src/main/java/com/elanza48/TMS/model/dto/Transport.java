package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "transport_info")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Transport extends IdentityName{
	
	public enum TransportMode{
		BUS,
	    CAR,
	    CRUISE,
	    STEAMER,
	    FLIGHT,
	    RAILWAY
	}
	
	public enum TransportEntity{
	    PRIVATE,
	    PUBLIC
	}
	
	@Column
	private String description;
	
	@Column
	@NotNull
	@Enumerated
	private TransportMode mode = TransportMode.CAR;
	
	@Column
	@NotNull
	@Enumerated
	private TransportEntity entity= TransportEntity.PRIVATE;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "transports")
	private Set<Package> packages;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transportId")
	private Set<Ticket> tickets;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transportId")
	private Set<Enquiry> enquiries;
	
}

package com.elanza48.TMS.model.dto;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "transport_info")
public class Transport {
	
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

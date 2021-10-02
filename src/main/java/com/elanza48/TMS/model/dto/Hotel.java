package com.elanza48.TMS.model.dto;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "hotel_Info")
public class Hotel {
	
	public enum HotelType{
		STANDARD,
	    PRIME,
	    ROYALE
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
	@NotNull
	private String address;
	
	@Column
	@NotNull
	@Size(min=6, max = 6)
	private int zip;
	
	@Embedded
	private Contact contact;
	
	@Column
	@NotNull
	@Enumerated
	private HotelType type = HotelType.STANDARD;
	
	@Column(name = "base_price")
	@NotNull
	private int basePrice;
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dest_id",referencedColumnName = "id")
	private Destination destinationId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelId")
	private Set<HotelReview> reviews;
	
}

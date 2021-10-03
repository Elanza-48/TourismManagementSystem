package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "hotel_Info")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Hotel extends IdentityNameContact{
	
	public enum HotelType{
		STANDARD,
	    PRIME,
	    ROYALE
	}
	
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

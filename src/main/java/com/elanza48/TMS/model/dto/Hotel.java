package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "hotel_Info")
public class Hotel extends Contact{
	
	public enum HotelType{
		STANDARD,
	    PRIME,
	    ROYAL
	}
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private HotelType type = HotelType.STANDARD;
	
	@Column(name = "base_price")
	@NotNull
	private int basePrice;
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dest_id",referencedColumnName = "id")
	@JsonBackReference("destHotels")
	private Destination destinationId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelId")
	@JsonManagedReference("hotelsRvw")
	private Set<HotelReview> reviews;
	
	public Hotel(){
		super();
	}

	public Hotel(@NotNull String name, @NotNull @Email String email, @NotNull  long mobileNo,
			@NotNull Address address, @NotNull HotelType type, @NotNull int basePrice,
			Destination destinationId) {
		super(name, email, mobileNo, address);
		this.type = type;
		this.basePrice = basePrice;
		this.destinationId = destinationId;
	}

	public HotelType getType() {
		return type;
	}

	public void setType(HotelType type) {
		this.type = type;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public Destination getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Destination destinationId) {
		this.destinationId = destinationId;
	}

	public Set<HotelReview> getReviews() {
		return reviews;
	}

	public void setReviews(Set<HotelReview> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String getEmail() {
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		super.setEmail(email);
	}

	@Override
	public long getMobileNo() {
		return super.getMobileNo();
	}

	@Override
	public void setMobileNo(long mobileNo) {
		super.setMobileNo(mobileNo);
	}

	@Override
	public Address getAddress() {
		return super.getAddress();
	}

	@Override
	public void setAddress(Address address) {
		super.setAddress(address);
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
		return "Hotel [type=" + type + ", basePrice=" + basePrice + ", destinationId=" + destinationId + ", reviews="
				+ reviews + ", email=" + email + ", mobileNo=" + mobileNo + ", address=" + address + ", name=" + name
				+ ", id=" + id + "]";
	}
}

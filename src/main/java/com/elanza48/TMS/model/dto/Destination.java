package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "destination_Info")
public class Destination extends IdentityName{
	
	@Column
	@NotNull
	private String province;
	
	@Column
	@Type(type = "text")
	private String description;
	
	@Column(name = "MAX_STAY_DURATION", length = 2)
	@Range(min = 1, max=10)
	@NotNull
	private short stayDuration=1;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "destinationId")
	@JsonManagedReference("destHotels")
	private Set<Hotel> hotels;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "destinations")
	private Set<Package> packages;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "destinationId")
	@JsonManagedReference("destRvw")
	private Set<DestinationReview> reviews;
	
	public Destination() {
		super();
	}
	public Destination(@NotNull String name, @NotNull String province, String description, @Range(min = 1, max = 13) @NotNull short stayDuration) {
		super(name);
		this.province = province;
		this.description = description;
		this.stayDuration = stayDuration;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStayDuration() {
		return stayDuration;
	}

	public void setStayDuration(short stayDuration) {
		this.stayDuration = stayDuration;
	}

	public Set<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(Set<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Set<Package> getPackages() {
		return packages;
	}

	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}

	public Set<DestinationReview> getReviews() {
		return reviews;
	}

	public void setReviews(Set<DestinationReview> reviews) {
		this.reviews = reviews;
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
		return "Destination [province=" + province + ", description=" + description + ", stayDuration=" + stayDuration
				+ ", hotels=" + hotels + ", packages=" + packages + ", reviews=" + reviews + ", name=" + name + ", id="
				+ id + "]";
	}

}

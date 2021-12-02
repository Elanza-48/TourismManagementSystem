package com.elanza48.TMS.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "destination_Info")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Destination extends IdentityName implements Serializable {
	
	@Column
	@NotNull
	@Length(min = 2, max = 2)
	private String province;
	
	@Column
	@Type(type = "text")
	private String description;
	
	@Column(name = "MAX_STAY_DURATION", length = 2)
	@Range(min = 1, max=10)
	@NotNull
	private short stayDuration=1;

	@Embedded
	@NotNull
	private MetaData metaData=new MetaData();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "destinationId")
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Hotel> hotels;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "destinations")
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Package> packages;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "destinationId")
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<DestinationReview> reviews;
	
	public Destination() {}
	public Destination(@NotNull String name, @NotNull @Length(min = 2, max = 2)
			String province, String description,
		@Range(min = 1, max = 13) @NotNull short stayDuration) {
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
		return "Destination [province=" + province + ", description=" + description + ", stayDuration=" + stayDuration
				+ ", hotels=" + hotels + ", packages=" + packages + ", reviews=" + reviews + ", name=" + name + ", id="
				+ id + "]";
	}

}

package com.elanza48.TMS.model.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Embeddable
public class Address {
	@Column
	@NotNull
	private String street;
	
	@Column
	@NotNull
	private String district;
	
	@Column
	@NotNull
	private String state;
	
	@Column(length = 6)
	@NotNull
	@Pattern(regexp="(^$|[0-9]{6})")
	private int zip;
	
	public Address(){}
	public Address(@NotNull String street, @NotNull String district, @NotNull String state,
			@NotNull @Pattern(regexp="(^$|[0-9]{6})") int zip) {
		this.street = street;
		this.district = district;
		this.state = state;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", district=" + district + ", state=" + state + ", zip=" + zip + "]";
	}
}

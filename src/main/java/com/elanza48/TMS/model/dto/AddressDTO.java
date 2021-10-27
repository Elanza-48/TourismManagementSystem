package com.elanza48.TMS.model.dto;

public class AddressDTO {

	private String street;
	private String district;
	private String state;
	private int zip;
	
	public AddressDTO(){}
	public AddressDTO(String street, String district, String state, int zip) {
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

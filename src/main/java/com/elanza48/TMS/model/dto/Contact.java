package com.elanza48.TMS.model.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Embeddable
class Address{
	@Column
	@NotNull
	private String street;
	
	@Column
	@NotNull
	private String district;
	
	@Column
	@NotNull
	private String state;
	
	@Column
	@NotNull
	@Size(min=6, max = 6)
	private int zip;

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
}


@MappedSuperclass
abstract class IdentityNameContact extends IdentityName {
	
	@Column
	@NotNull
	@Email
	@NaturalId(mutable = true)
	protected String email;
	
	@Column(name = "mobile_no")
	@NaturalId(mutable = true)
	@NotNull
	@Size(min = 10, max = 10)
	protected long mobileNo;
	
	@Embedded
	@NotNull
	protected Address address;
	
	public IdentityNameContact() {}

	public IdentityNameContact(@NotNull @Email String email, @NotNull @Size(min = 10, max = 10) long mobileNo,
			@NotNull Address address) {
		super();
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}

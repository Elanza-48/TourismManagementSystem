package com.elanza48.TMS.model.dto;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
abstract class Contact extends IdentityName {
	
	@Column(unique = true)
	@NotNull
	@Email
	protected String email;
	
	@Column(name = "mobile_no", unique = true)
	@NotNull
	@Size(min = 10, max = 10)
	protected long mobileNo;
	
	@Embedded
	@NotNull
	protected Address address;
	
	public Contact() {
		super();
	}

	public Contact(@NotNull String name, @NotNull @Email String email, @NotNull @Size(min = 10, max = 10) long mobileNo,
			@NotNull Address address) {
		super(name);
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

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}
	
	
	
}

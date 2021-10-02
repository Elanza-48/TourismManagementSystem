package com.elanza48.TMS.model.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Embeddable
public class Contact {
	@Column
	@NotNull
	@Email
	@NaturalId(mutable = true)
	private String email;
	
	@Column(name = "mobile_no")
	@NotNull
	@NaturalId(mutable = true)
	@Size(min = 10, max = 10)
	private long mobileNo;

	
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

}


package com.elanza48.TMS.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.elanza48.TMS.model.entity.UserAccount.UserGender;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserAccountDTO extends ContactDTO implements Serializable {

	private UserGender gender;
	@JsonSerialize(using = com.elanza48.TMS.config.JSONDateFormatter.class)
	@JsonDeserialize(using = com.elanza48.TMS.config.JSONDateValidator.class)
	private Date dob;
	private UserRoleDTO role;
	private String password="hidden";
	
	
	public UserAccountDTO(String name,String email,long mobileNo, AddressDTO address,
	 Date dob, UserGender gender, UserRoleDTO role) {
		super(name, email, mobileNo, address);
		this.gender=gender;
		this.dob=dob;
		this.role=role;
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
	public AddressDTO getAddress() {
		return super.getAddress();
	}

	@Override
	public void setAddress(AddressDTO address) {
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
		return "UserAccount [role=" + role + ", dob=" + dob + ", email=" + email + ", mobileNo=" + mobileNo 
			+ ", address=" + address + ", name=" + name + "gender=" + gender + ", id=" + id + "]";
	}

}

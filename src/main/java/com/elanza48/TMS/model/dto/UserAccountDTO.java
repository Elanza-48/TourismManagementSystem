package com.elanza48.TMS.model.dto;

import java.sql.Date;

import com.elanza48.TMS.model.entity.UserRole;
import com.elanza48.TMS.model.entity.UserAccount.UserGender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserAccountDTO extends ContactDTO{

	private UserGender gender;
	private Date dob;
	private UserRole role;
	
	
	public UserAccountDTO(String name,String email,long mobileNo, AddressDTO address,
	 Date dob, UserGender gender) {
		super(name, email, mobileNo, address);
		this.gender=gender;
		this.dob=dob;
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

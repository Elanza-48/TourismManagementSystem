package com.elanza48.TMS.model.dto;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.elanza48.TMS.model.entity.UserAccount;
import com.elanza48.TMS.model.entity.UserAccount.UserGender;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserAccountDTO extends ContactDTO {

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

	public static Map<String, Object> getBodyFormat(){
		Map<String, Object> userFormat = new LinkedHashMap<>();
		userFormat.put("name", "string");
		userFormat.put("email", "email pattern string");
		userFormat.put("mobileNo", "integer: {10}");
		userFormat.put("address", Map.of(
				"street", "string",
				"district", "string",
				"state", "string: {^2} [goto: '/IndianStates.json']",
				"zip", "integer: {6}"
		));
		userFormat.put("gender", String.format("string: [%s, %s, %s]",
				UserAccount.UserGender.MALE.name(),
				UserAccount.UserGender.FEMALE.name(),
				UserAccount.UserGender.TRANS.name()
		));
		userFormat.put("dob", "string: [dd-MM-yyyy]");
		userFormat.put("password", "string");

		return userFormat;
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
		return "UserAccountDTO [role=" + role + ", dob=" + dob + ", email=" + email + ", mobileNo=" + mobileNo
			+ ", address=" + address + ", name=" + name + ", gender=" + gender + ", id=" + id + "]";
	}

}

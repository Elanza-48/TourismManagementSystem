package com.elanza48.TMS.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
abstract class ContactDTO extends IdentityNameDTO {

	protected String email;
	protected long mobileNo;
	protected AddressDTO address;

	public ContactDTO(String name,String email, long mobileNo, AddressDTO address) {
		super(name);
		this.email = email;
		this.mobileNo = mobileNo;
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

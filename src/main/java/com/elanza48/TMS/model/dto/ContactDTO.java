package com.elanza48.TMS.model.dto;

abstract class ContactDTO extends IdentityNameDTO {

	protected String email;
	protected long mobileNo;
	protected AddressDTO address;
	
	public ContactDTO() {}

	public ContactDTO(String name,String email, long mobileNo, AddressDTO address) {
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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
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

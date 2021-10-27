package com.elanza48.TMS.model.dto;

import com.elanza48.TMS.model.entity.Hotel.HotelType;

public class HotelDTO extends ContactDTO{

	private HotelType type = HotelType.STANDARD;
	private int basePrice;
	private DestinationDTO destinationId;


	public HotelDTO(){}

	public HotelDTO(String name,String email,long mobileNo,AddressDTO address, HotelType type, int basePrice,
			DestinationDTO destinationId) {
		super(name, email, mobileNo, address);
		this.type = type;
		this.basePrice = basePrice;
		this.destinationId = destinationId;
	}

	public HotelType getType() {
		return type;
	}

	public void setType(HotelType type) {
		this.type = type;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public DestinationDTO getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(DestinationDTO destinationId) {
		this.destinationId = destinationId;
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
		return "Hotel [type=" + type + ", basePrice=" + basePrice + ", destinationId=" + destinationId + 
		 ", email=" + email + ", mobileNo=" + mobileNo + ", address=" + address + ", name=" + name
				+ ", id=" + id + "]";
	}
}

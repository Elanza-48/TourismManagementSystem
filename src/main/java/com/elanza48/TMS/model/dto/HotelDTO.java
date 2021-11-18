package com.elanza48.TMS.model.dto;

import com.elanza48.TMS.model.entity.Hotel.HotelType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class HotelDTO extends ContactDTO implements Serializable {

	private HotelType type = HotelType.STANDARD;
	private int basePrice;
	private DestinationDTO destinationId;


	public HotelDTO(String name,String email,long mobileNo,AddressDTO address, HotelType type, int basePrice,
			DestinationDTO destinationId) {
		super(name, email, mobileNo, address);
		this.type = type;
		this.basePrice = basePrice;
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
		return "HotelDTO [type=" + type + ", basePrice=" + basePrice + ", destinationId=" + destinationId +
		 ", email=" + email + ", mobileNo=" + mobileNo + ", address=" + address + ", name=" + name
				+ ", id=" + id + "]";
	}
}

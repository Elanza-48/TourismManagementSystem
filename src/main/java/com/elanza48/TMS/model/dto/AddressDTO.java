package com.elanza48.TMS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	private String street;
	private String district;
	private String state;
	private int zip;


	@Override
	public String toString() {
		return "Address [street=" + street + ", district=" + district + ", state=" + state + ", zip=" + zip + "]";
	}
}

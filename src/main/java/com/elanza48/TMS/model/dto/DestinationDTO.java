package com.elanza48.TMS.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DestinationDTO extends IdentityNameDTO{
	
	private String province;
	private String description;
	private short stayDuration=1;

	public DestinationDTO(String name, String province, String description, short stayDuration) {
		super(name);
		this.province = province;
		this.description = description;
		this.stayDuration = stayDuration;
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
		return "Destination [province=" + province + ", description=" + description + ", stayDuration=" + stayDuration
				+ ", name=" + name + ", id="
				+ id + "]";
	}

}

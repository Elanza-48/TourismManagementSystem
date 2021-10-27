package com.elanza48.TMS.model.dto;

public class DestinationDTO extends IdentityNameDTO{
	
	private String province;
	private String description;
	private short stayDuration=1;

	public DestinationDTO() {}
	public DestinationDTO(String name, String province, String description, short stayDuration) {
		super(name);
		this.province = province;
		this.description = description;
		this.stayDuration = stayDuration;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStayDuration() {
		return stayDuration;
	}

	public void setStayDuration(short stayDuration) {
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

package com.elanza48.TMS.model.dto;

import com.elanza48.TMS.config.JSONIndianStateMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.Map;

@Log4j2
@NoArgsConstructor
public class DestinationDTO extends IdentityNameDTO implements Serializable {

	@Getter private String province;
	@Getter @Setter private String description;
	@Getter @Setter private short stayDuration=1;


	public DestinationDTO(String name, String province, String description, short stayDuration) {
		super(name);
		this.setProvince(province);
		this.description = description;
		this.stayDuration = stayDuration;
	}

	public void setProvince(String province) {
		if(province.length()==2 && JSONIndianStateMapper.getStateMapping().containsKey(province)){
			this.province=province;
		}else if(province.length()>2 && JSONIndianStateMapper.getStateMapping().containsValue(province)) {
			for (Map.Entry<String, String> entry : JSONIndianStateMapper.getStateMapping().entrySet()) {
				if (province.equals(entry.getValue())) {
					this.province = entry.getKey();
					break;
				}
			}
		}else{
			throw new IllegalArgumentException();
		}
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
		return "DestinationDTO [province=" + province + ", description=" + description + ", stayDuration=" + stayDuration
				+ ", name=" + name + ", id=" + id + "]";
	}

}

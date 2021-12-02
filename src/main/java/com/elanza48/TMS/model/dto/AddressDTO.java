package com.elanza48.TMS.model.dto;

import com.elanza48.TMS.config.JSONIndianStateMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Named;

import java.io.Serializable;
import java.util.Map;

@Slf4j
@NoArgsConstructor
public class AddressDTO implements Serializable {

	@Getter @Setter private String street;
	@Getter @Setter private String district;
	@Getter private String state;
	@Getter @Setter private int zip;

	public AddressDTO(String street, String district, String state, int zip) {
		this.street = street;
		this.district = district;
		this.setState(state);
		this.zip = zip;
	}

	public void setState(String state) {
		if(state.length()==2 && JSONIndianStateMapper.getStateMapping().containsKey(state)){
			this.state=state;
		}else if(state.length()>2 && JSONIndianStateMapper.getStateMapping().containsValue(state)) {
			for (Map.Entry<String, String> entry : JSONIndianStateMapper.getStateMapping().entrySet()) {
				if (state.equalsIgnoreCase(entry.getValue())) {
					this.state = entry.getKey();
					break;
				}
			}
		}else{
			throw new IllegalArgumentException();
		}

	}


	@Override
	public String toString() {
		return "AddressDTO [street=" + street + ", district=" + district + ", state=" + state + ", zip=" + zip + "]";
	}
}

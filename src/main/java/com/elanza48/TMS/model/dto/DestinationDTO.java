package com.elanza48.TMS.model.dto;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class DestinationDTO extends IdentityNameDTO implements Serializable {

	private String province;
	@Getter @Setter private String description;
	@Getter @Setter private short stayDuration=1;
	@JsonIgnore private Map<String, String> stateMap=null;

	public DestinationDTO(String name, String province, String description, short stayDuration) {
		super(name);
		this.province = province;
		this.description = description;
		this.stayDuration = stayDuration;
	}

	private void jsonToMap() {
		if(this.stateMap==null) {
			try {
				byte[] rawData = Files.readAllBytes(Paths.get("src/main/resources/IndianStates.json"));
				ObjectMapper objectMapper = new ObjectMapper();
				this.stateMap = objectMapper.readValue(rawData, HashMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getProvince(){
		jsonToMap();
		return this.stateMap.get(this.province);
	}

	public void setProvince(String province) {
		if(province.length()==2){
			this.province=province;
			return;
		}else if(province.length()>2) {
			jsonToMap();
			for (Map.Entry<String, String> entry : this.stateMap.entrySet()) {
				if (province.equals(entry.getValue())) {
					this.province = entry.getKey();
					return;
				}
			}
		}
		throw new IllegalArgumentException();
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

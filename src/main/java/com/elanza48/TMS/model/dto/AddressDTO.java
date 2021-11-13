package com.elanza48.TMS.model.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

	@Getter @Setter private String street;
	@Getter @Setter private String district;
	private String state;
	@Getter @Setter private int zip;
	@JsonIgnore private Map<String,String> stateMap=null;

	private void jsonToMap() {
		if(this.stateMap==null) {
			try {
				Resource resource = new ClassPathResource("static/IndianStates.json");
				byte[] rawData = Files.readAllBytes(resource.getFile().toPath());
				ObjectMapper objectMapper = new ObjectMapper();
				this.stateMap = objectMapper.readValue(rawData, HashMap.class);
			} catch (IOException e) {
				log.error("ADDRESS_DTO: [status : error, message: {}]",e.getLocalizedMessage());
			}
		}
	}

	public String getState(){
		jsonToMap();
		return this.stateMap.get(this.state);
	}

	public void setState(String state) {
		if(state.length()==2){
			this.state=state;
			return;
		}else if(state.length()>2) {
			jsonToMap();
			for (Map.Entry<String, String> entry : this.stateMap.entrySet()) {
				if (state.equals(entry.getValue())) {
					this.state = entry.getKey();
					return;
				}
			}
		}
		throw new IllegalArgumentException();
	}


	@Override
	public String toString() {
		return "Address [street=" + street + ", district=" + district + ", state=" + state + ", zip=" + zip + "]";
	}
}

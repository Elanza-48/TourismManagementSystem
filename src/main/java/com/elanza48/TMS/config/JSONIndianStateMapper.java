package com.elanza48.TMS.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JSONIndianStateMapper {
    private static Map<String, String> stateMap=null;
    public static Map<String, String> getStateMapping() {
        if(stateMap==null) {
            try {
                Resource resource = new ClassPathResource("static/IndianStates.json");
                byte[] rawData = resource.getInputStream().readAllBytes();
                ObjectMapper objectMapper = new ObjectMapper();
                stateMap = objectMapper.readValue(rawData, HashMap.class);
            } catch (IOException e) {
                log.error("ADDRESS_DTO: [status : error, message: {}]",e.getLocalizedMessage());
            }
        }
        return stateMap;
    }
}

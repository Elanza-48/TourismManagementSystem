package com.elanza48.TMS.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Custom deserialized class used to validate date following the [dd-MM-yyyy] pattern.
 * @see {@link com.fasterxml.jackson.databind.JsonDeserializer}
 *
 * @author Elanza-48
 *
 */
@Log4j2
@JsonComponent
public class JSONDateValidator extends StdDeserializer<Date> {

    public JSONDateValidator(){
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException, DateTimeParseException{
        String value = p.readValueAs(String.class);

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try{
            return dateFormat.parse(value);
        }catch (ParseException e){
            log.error("JSON_DATE: [status: error, message: {}]", e.getLocalizedMessage());
            throw new DateTimeParseException(e.getLocalizedMessage(), e.getMessage(), e.getErrorOffset());
        }
    }
}

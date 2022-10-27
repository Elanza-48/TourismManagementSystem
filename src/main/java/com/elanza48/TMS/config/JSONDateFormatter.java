package com.elanza48.TMS.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Custom serialized class used to format date following the [dd-MM-yyyy] pattern.
 * @see {@link com.fasterxml.jackson.databind.JsonDeserializer}
 *
 * @author Elanza-48
 *
 */
@JsonComponent
public class JSONDateFormatter extends StdSerializer<Date> {

    public JSONDateFormatter(Class c){
        super(c);
    }

    public JSONDateFormatter() {
        this(null);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        gen.writeString(dateFormat.format(value));

    }
}

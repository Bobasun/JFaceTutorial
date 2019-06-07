package com.jfacetutorial.modellayer;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserDataSerializer extends JsonSerializer<UserData>{
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void serialize(UserData value, 
			JsonGenerator gen, 
			SerializerProvider serializer) 
			throws IOException {
		
		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, value);
		gen.writeFieldName(writer.toString());		
	}

}

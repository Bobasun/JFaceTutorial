package com.jfacetutorial.modellayer;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

public class MyUserDataDeserializer extends KeyDeserializer{

	@Override
	public Object deserializeKey(String key, DeserializationContext context) throws IOException {
		return new UserDataImpl(key);
	}

	
	
}

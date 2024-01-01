package com.booking.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	// To read Json Data as Map file we must use ObjectMapper
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static Map<String, Object> getJsonDataAsMap(String jsonFileName) throws JsonParseException, JsonMappingException, IOException
	{
		String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
		String jsonPath = System.getProperty("user.dir") + "\\resources\\config\\qa.json";
		System.out.println(jsonPath);
		Map<String, Object> data = objectMapper.readValue(new File(jsonPath), new TypeReference<Map<String,Object>>() {});
		return data;
	}

}

package com.booking.Test;

import java.io.IOException;
import java.util.Map;

import com.booking.util.JsonUtils;

public class Base {
	
	public static Map<String, Object> dataFromJsonFile;
	
	static {
		
		String env = System.getProperty("env")  == null ? "qa" : System.getProperty("env");
		
		try {
			dataFromJsonFile = JsonUtils.getJsonDataAsMap(env);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

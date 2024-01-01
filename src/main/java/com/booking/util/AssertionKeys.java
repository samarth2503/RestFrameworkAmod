package com.booking.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssertionKeys {
	
	private String jsonPath;
	private Object expectedvalue;
	private Object actualValue;
	private String result;

}

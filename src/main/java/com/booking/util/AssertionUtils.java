package com.booking.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.booking.reporting.ExtentReportManager;
import com.booking.reporting.SetUp;

import io.restassured.response.Response;

public class AssertionUtils {
	
	public static void assertExpectedJsonValuesWithJsnPath(Response resposne, Map<String,Object> expectedValuesMap)
	{
		List<AssertionKeys> actualValuesMap = new ArrayList<>(); 
		boolean allMatched = true;
		
		Set<String> jsonPaths = expectedValuesMap.keySet();
		
		for(String jsonPath : jsonPaths)
		{
			Optional<Object> actualValue = Optional.ofNullable(resposne.jsonPath().get(jsonPath));
			
			if(actualValue.isPresent())
			{
				Object value = actualValue.get();
				if(value.equals(expectedValuesMap.get(jsonPath)))
				{
					actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPaths),value, "MATCHED"));
				}
				else{
					allMatched = false;
					actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPaths),value, "NOT MATCHED"));
				}
			}
			else {
				allMatched = false;
				actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPaths),"VALUE NOT FOUND", "NOT MATCHED"));
			}
		}
		
		if(allMatched)
		{
			ExtentReportManager.logPassDetails("All Assertion are passed..");
		}
		else {
			ExtentReportManager.logFailDetails("Assertion are failed..");
		}
		
		String [][] arrayHeader = actualValuesMap.stream().map(assertions -> new String[] {String.valueOf(assertions.getJsonPath()),String.valueOf(assertions.getActualValue()),
				String.valueOf(assertions.getExpectedvalue()),String.valueOf(assertions.getResult())})
				.toArray(String[][]::new);
		
		SetUp.extentTest.get().info(MarkupHelper.createTable(arrayHeader));
	}
	
	

}

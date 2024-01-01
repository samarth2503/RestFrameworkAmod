package com.booking.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.booking.pojos.AdditionalDetails;
import com.booking.pojos.Booking;
import com.booking.util.JsonUtils;
import com.booking.util.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookingTest extends BookingAPI {
	
	@Test(enabled=false)
	public void createAirline()
	{
		Map<String,Object> payload = Payload.getBookingPayloadFromMap("Don", "Alex", 230, false, new Date("2024-01-01"),new Date("2024-01-05"), "Dinner");
		Response res = createBooking(payload);
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(enabled=false)
	public void createAirlineFromString()
	{
		String payload = Payload.getBookingPayloadFromString("Don", "Alex", 230, false,"2024-01-01","2024-01-05", "Dinner");
		Response res = createBooking(payload);
		Assert.assertEquals(res.getStatusCode(), 200);
		
		System.out.println("Post Repsosne is "+res.prettyPrint());
		
		int id = res.jsonPath().getInt("bookingid");
		
		Response resposne = RestAssured.given()
		.baseUri("https://restful-booker.herokuapp.com/booking/"+id)
		.get();
		
		System.out.println("Repsosne is "+resposne.prettyPrint());	
	}
	
	@Test(enabled=false)
	public void createBookingUsingPojo()
	{
		
//		Booking book = Payload.getBookingPayloadFromPojo();
		
//		Booking book = new Booking().toBuilder().build();
//		try {
//			System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(book));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//		Booking book = Booking.builder().firstName("Samarth").build();			// To enable Pojo to accept default value we have make toBuilder = true
		Booking book = new Booking().toBuilder().firstname("Samarth").build();
		//Booking book = new Booking().getAddDet(AdditionalDetails.Lunch).build();
		
		Response resposne = createBooking(book);
		
		Assert.assertEquals(resposne.getStatusCode(), 200);
	}
	
	@Test(enabled=true)
	public void createBookingAndVerifyResponse() throws JsonMappingException, JsonProcessingException
	{
		Booking payload = new Booking().toBuilder().build();
		
		Response resposne = createBooking(payload);
		
		// First way
		Assert.assertEquals(resposne.jsonPath().getString("booking.firstname"), payload.getFirstname());
		
		
		// Deseralizing Reposne to POJO class
		ObjectMapper objectMapper = new ObjectMapper();
		Booking book = objectMapper.readValue(resposne.getBody().asString(), Booking.class);
		
		
		Assert.assertEquals(book,payload);
		
		
	}

}

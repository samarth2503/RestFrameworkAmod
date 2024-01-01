package com.booking.Test;

import java.util.HashMap;
import java.util.Map;

import com.booking.pojos.Booking;
import com.booking.util.RestUtils;

import io.restassured.response.Response;

public class BookingAPI {
	
	public Response createBooking(Map<String,Object> createBooking)
	{
		String endPoint = (String) Base.dataFromJsonFile.get("createBookingEndpoint");
		return RestUtils.performPost(endPoint, createBooking, new HashMap<>());
	}
	
	public Response createBooking(Booking createBookingPayload)
	{
		String endpoint = (String) Base.dataFromJsonFile.get("createBookingEndpoint");
		return RestUtils.performPost(endpoint, createBookingPayload, new HashMap<>());
	}
	
	public Response createBooking(String createBookingPayload)
	{
		String endpoint = (String) Base.dataFromJsonFile.get("createBookingEndpoint");
		return RestUtils.performPost(endpoint, createBookingPayload, new HashMap<>());
	}

}

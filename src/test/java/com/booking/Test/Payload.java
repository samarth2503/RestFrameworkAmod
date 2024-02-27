package com.booking.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.booking.pojos.Booking;
import com.booking.pojos.BookingDates;
import com.booking.util.RandomDataGenerator;
import com.booking.util.RandomDataTypeNames;

import net.datafaker.Faker;

public class Payload {
	
	public static Map<String,Object> getBookingPayloadFromMap(String firstName, String lastName, int totalPrice, boolean paid, Date checkin, Date checkout, String additional)
	{
		Map<String, Object> hm = new HashMap<String, Object>();
		
		Faker faker = new Faker();
		
		hm.put("firstName", RandomDataGenerator.getRandomDatafor(RandomDataTypeNames.FIRSTNAME));
		hm.put("lastName", RandomDataGenerator.getRandomDatafor(RandomDataTypeNames.LASTNAME));
		hm.put("totalPrice", faker.number().numberBetween(1000, 5000));
		hm.put("paid", faker.bool());
		hm.put("checkin", faker.date().birthday("YYYY-MM-dd"));
		hm.put("checkout", faker.date().birthday("YYYY-MM-dd"));
		hm.put("additional", RandomDataGenerator.getAdditionalDetails());
		
		return hm;
	}
	
	public static Booking getBookingPayloadFromPojo()
	{
		return Booking.builder()
		.firstname(RandomDataGenerator.getRandomDatafor(RandomDataTypeNames.FIRSTNAME))
		.lastname(RandomDataGenerator.getRandomDatafor(RandomDataTypeNames.LASTNAME))
		.totalprice(RandomDataGenerator.getRandomNumberBetween(1000, 5000))
		.depositpaid(RandomDataGenerator.getBooleanValue())
		.bookingdates(new BookingDates())
		.additionalneeds(RandomDataGenerator.getAdditionalDetails())
		.build();
	}
	
	public static String getBookingPayloadFromString(String firstName, String lastName, int totalPrice, boolean Paid, String checkinDate, String checkoutDate, String additional)
	{
		
		String payload = "{\r\n"
				+ "    \"firstname\" : \""+firstName+"\",\r\n"
				+ "    \"lastname\" : \""+lastName+"\",\r\n"
				+ "    \"totalprice\" : totalPrice,\r\n"
				+ "    \"depositpaid\" : Paid,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \""+checkinDate+"\",\r\n"
				+ "        \"checkout\" :  \""+checkoutDate+"\" \r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \""+additional+"\"\r\n"
				+ "}";
		
		payload = payload.replaceAll("totalPrice", String.valueOf(totalPrice))
				.replace("Paid", String.valueOf(Paid))
				.replaceAll("checkinDate", String.valueOf(checkinDate))
				.replaceAll("checkoutDate", String.valueOf(checkoutDate));
		
		return payload;
	}

}

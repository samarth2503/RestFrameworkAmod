package com.booking.reporting;

public class Practice {
	
	public static void main(String[] args)
	{
		String pay = getBookingPayloadFromString("Sam","Jain",45,true,"2024-01-01","2024-01-10","Lunch");
		//System.out.println(pay);
	}
	
	public static String getBookingPayloadFromString(String firstName, String lastName, int totalPrice, boolean paid, String checkin, String checkout, String additional)
	{
		
		String payload = "{\r\n"
				+ "    \"firstname\" : \""+firstName+"\",\r\n"
				+ "    \"lastname\" : \""+lastName+"\",\r\n"
				+ "    \"totalprice\" : totalPrice,\r\n"
				+ "    \"depositpaid\" : paid,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : checkinDate,\r\n"
				+ "        \"checkout\" :  checkoutDate \r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \""+additional+"\"\r\n"
				+ "}";
		System.out.println(payload);
		
		payload = payload.replaceAll("totalPrice", String.valueOf(totalPrice))
				.replaceAll("paid", String.valueOf(paid))
				.replaceAll("checkinDate", checkin)
				.replaceAll("checkoutDate", checkout);
		
		System.out.println(payload);
		
		return payload;
	}
}

package com.booking.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.booking.pojos.Booking;
import com.booking.pojos.BookingDates;
import com.booking.pojos.BookingPoiji;
import com.booking.util.ExcelUtils;
import com.poiji.bind.Poiji;

import io.restassured.response.Response;

public class BookingTestNew extends BookingAPI {
	
	@Test(dataProvider = "bookingData")
	public void createBookingAndVerify(Booking booking)
	{
		Booking booking1 = Payload.getBookingPayloadFromPojo();
		Response resposne = createBooking(booking1);
		
	}
	
	
	@DataProvider(name = "bookingData")
	public Iterator<Booking> getBookingData()
	{
		List<LinkedHashMap<String,String>> excelData = ExcelUtils.getExcelDataAsListOfMap("CreateBookingData", "Sheet1");
		List<Booking> booking = new ArrayList<>();
		
		for(LinkedHashMap<String,String> data: excelData)
		{
			Booking booking1 = Booking.builder()
			.firstname(data.get("FirstName"))
			.lastname(data.get("LastName"))
			.totalprice(Integer.parseInt(data.get("TotalPrice")))
			.depositpaid(Boolean.parseBoolean(data.get("Paid")))
			.bookingdates(new BookingDates())
			.additionalneeds(data.get("AdditionalDetails"))
			.build();
			
			booking.add(booking1);
		}
		return booking.iterator();
	}
	
	@DataProvider(name = "bookingPoiji")
	public Iterator<Booking> getCreateBookingDataPoiji()
	{
		List<BookingPoiji> book= Poiji.fromExcel(new File("C:\\Users\\samarjain\\eclipse-workspace\\RestAssuredFrameWork\\resources\\testdata\\CreateBookingData.xlsx"), BookingPoiji.class);
		return null;
		
	}
	
}

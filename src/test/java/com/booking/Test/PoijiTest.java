package com.booking.Test;

import java.io.File;
import java.util.List;

import com.booking.pojos.BookingPoiji;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

public class PoijiTest {

	public static void main(String[] args) {
		
		PoijiOptions option = PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();
		List<BookingPoiji> data = Poiji.fromExcel(new File("C:\\Users\\samarjain\\eclipse-workspace\\RestAssuredFrameWork\\resources\\testdata\\CreateBookingData.xlsx"), BookingPoiji.class,option);
		System.out.println(data);

	}

}

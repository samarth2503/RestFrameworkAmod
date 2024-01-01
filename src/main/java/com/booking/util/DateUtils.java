package com.booking.util;

import org.joda.time.LocalDate;

public class DateUtils {
	
	public static int getCurrentYear()
	{
		return LocalDate.now().getYear();
	}

}

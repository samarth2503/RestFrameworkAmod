package com.booking.pojos;

import java.util.Date;

import com.booking.util.RandomDataGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {
	
	private String firstname = RandomDataGenerator.getRandomFirstName();
	private String lastname = RandomDataGenerator.getRandomLasttName();
	private int totalprice = RandomDataGenerator.getRandomNumberBetween(1000, 5000);
	private boolean depositpaid = RandomDataGenerator.getBooleanValue();
	private BookingDates bookingdates = new BookingDates();
	private String additionalneeds = RandomDataGenerator.getAdditionalDetails();


}

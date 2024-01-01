package com.booking.util;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import org.joda.time.LocalDate;

import net.datafaker.Faker;

public class RandomDataGenerator {
	
	public static Faker faker = new Faker();
	
	public static String getRandomFirstName()
	{
		return faker.name().firstName();
	}
	
	public static String getRandomLasttName()
	{
		return faker.name().lastName();
	}
	
	public static String getRandomFullName()
	{
		return faker.name().fullName();
	}
	
	public static String getAdditionalDetails()
	{
		String[] options = new String[] {"Lunch","Dinner","Breakfast"};
		return options[faker.number().numberBetween(0, 3)];
	}
	
	public static String getCurrentDate()
	{
		LocalDate currentDate = LocalDate.now();
		String fakeFormattedDate = currentDate.toString("YYYY-MM-dd");
		return fakeFormattedDate;
	}
	
	public static String getFutureDate()
	{
		LocalDate currentDate = LocalDate.now();
        int randomDaysToAdd = ThreadLocalRandom.current().nextInt(1, 30); // Change range as needed
        LocalDate futureDate = currentDate.plusDays(randomDaysToAdd);
        String formattedFutureDate = futureDate.toString("YYYY-MM-dd");
        return formattedFutureDate;
        
	}
	
	public static String getRandomAlphabet(int count)
	{
		return null;
	}
	
	public static int getRandomNumberBetween(int min, int max)
	{
		return faker.number().numberBetween(min, max);
	}
	
	public static boolean getBooleanValue()
	{
		return faker.bool().bool();
	}
	
	public static String getRandomDatafor(RandomDataTypeNames dataTypeNames)
	{
		switch(dataTypeNames) {
		
		case FIRSTNAME:
			return faker.name().firstName();
			
		case LASTNAME:
			return faker.name().lastName();
			
		case FULLNAME:
			return faker.name().fullName();
			
		case COUNTRY:
			return faker.address().country();
			
		case CITYNAME:
			return faker.address().cityName();
			
		default:
			return null;
		
		}
		
		
	}

}

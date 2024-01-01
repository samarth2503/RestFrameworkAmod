package com.booking.pojos;

import java.util.Map;

import com.booking.util.RandomDataGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@EqualsAndHashCode
@Setter
@Getter
@Builder(toBuilder=true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingPoiji {
	
	@ExcelCell(0)
	private String firstName = RandomDataGenerator.getRandomFirstName();
	
	@ExcelCellName("LastName")
	private String lastName = RandomDataGenerator.getRandomLasttName();
	
	@ExcelCellName("TotalPrice")
	private int totalPrice = RandomDataGenerator.getRandomNumberBetween(1000, 2500);
	
	@ExcelCellName("DepositPaid")
	private boolean paid = RandomDataGenerator.getBooleanValue();
	
	@ExcelCellName("Checkin")
	private String checkin = RandomDataGenerator.getCurrentDate();
	
	@ExcelCellName("Checkout")
	private String checkout = RandomDataGenerator.getCurrentDate();
	
	@ExcelCellName("AdditionalNeeds")
	private String additionalNeed = RandomDataGenerator.getAdditionalDetails();
	
	@ExcelUnknownCells
	private Map<String,String> extracells;
	
	
	

}

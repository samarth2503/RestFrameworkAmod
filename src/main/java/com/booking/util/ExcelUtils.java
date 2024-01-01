package com.booking.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	public static List<LinkedHashMap<String,String>> getExcelDataAsListOfMap(String excelfileName, String sheetName)
	{
		List<LinkedHashMap<String,String>> dataFromExcel = new ArrayList<>();
		
		try {
			Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\samarjain\\eclipse-workspace\\RestAssuredFrameWork\\resources\\testdata\\"+excelfileName+".xlsx"));
			
			Sheet sheet = workbook.getSheet(sheetName);
			
			int totalRows = sheet.getPhysicalNumberOfRows();
			LinkedHashMap<String,String> mapData;
			List<String> allKeys = new ArrayList<>();
			DataFormatter dataFormat = new DataFormatter();
			
			for(int i=0;i<totalRows;i++)
			{
				mapData = new LinkedHashMap<>();
				if(i==0)
				{
					int totalCol = sheet.getRow(0).getPhysicalNumberOfCells();
					
					for(int j=0;j<totalCol;j++)
					{
						allKeys.add(sheet.getRow(0).getCell(j).getStringCellValue());
					}
				}
				else {
					int totalCols = sheet.getRow(i).getPhysicalNumberOfCells();
					
					for(int j=0;j<totalCols;j++)
					{
						String cellvalue = dataFormat.formatCellValue(sheet.getRow(i).getCell(j));
						
						if(cellvalue.equals("random") && j==0)
						{
							cellvalue = RandomDataGenerator.getRandomFirstName();
						}
						else if(cellvalue.equals("random") && j==1)
						{
							cellvalue = RandomDataGenerator.getRandomLasttName();
						}
						else if(cellvalue.equals("random") && j==2)
						{
							cellvalue = String.valueOf(RandomDataGenerator.getRandomNumberBetween(1000, 2000));
						}
						else if(cellvalue.equals("random") && j==3)
						{
							cellvalue = String.valueOf(RandomDataGenerator.getBooleanValue()).toLowerCase();
						}
						else if(cellvalue.equals("random") && j==6)
						{
							cellvalue = String.valueOf(RandomDataGenerator.getAdditionalDetails());
						}
						
						mapData.put(allKeys.get(j), cellvalue);
					}
					
					dataFromExcel.add(mapData);
				}
				
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return dataFromExcel;
	}

}

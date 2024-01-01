package com.booking.reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;

public class ExtentReportManager {
	
	public static ExtentReports extentReports;
	
	public static ExtentReports createInstance(String fileName, String reportName,String documentTitle)
	{
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
		
		extentSparkReporter.config().setReportName(reportName);
		extentSparkReporter.config().setDocumentTitle(documentTitle);
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setEncoding("UTF-8");
		
		extentReports = new ExtentReports();
		
		extentReports.attachReporter(extentSparkReporter);
		
		return extentReports;
	}
	
	public static String getReportNameWithTimestamp()
	{
		DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		
		String formattedTime = datetimeFormatter.format(localDateTime);
		String reportName = "TestReport" + formattedTime + ".html";
		
		return reportName; 
	}
	
	public static void logPassDetails(String log) {
		SetUp.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}
	
	public static void logFailDetails(String log) {
		SetUp.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
	}
	
	public static void logInfoDetails(String log) {
		SetUp.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
	}
	
	public static void logWarningDetails(String log) {
		SetUp.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
	}
	
	public static void logJson(String json)
	{
		SetUp.extentTest.get().info(MarkupHelper.createCodeBlock(json,CodeLanguage.JSON));
	}
	
	public static void logHeaders(List<Header> headerList)
	{
		String[][] arrayHeaders = headerList.stream().map(header -> new String [] {header.getName(),header.getValue()}).toArray(String[][]::new);
		SetUp.extentTest.get().info(MarkupHelper.createTable(arrayHeaders));
	}
}
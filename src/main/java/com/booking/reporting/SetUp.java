package com.booking.reporting;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class SetUp implements ITestListener{
	
	private static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	public void onStart(ITestContext context)
	{
		String filename = ExtentReportManager.getReportNameWithTimestamp();
		String fullReportPath = System.getProperty("user.dir")+"\\reports\\"+filename;
		extentReports = ExtentReportManager.createInstance(filename, "Test API Automation Report", "Test ExecutionReport");
	}
	
	public void onFinish(ITestContext context)
	{
		if(extentReports!=null)
		{
			extentReports.flush();
		}
	}
	
	public void onTestStart(ITestResult result)
	{
		ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		ExtentReportManager.logFailDetails(result.getThrowable().getMessage());
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		
		stackTrace.replaceAll(",", "<br>");
		String formattedTrace = "<details>\n" +
				"<summary>Click here to see Exception Logs</summary>\n" +
				" "+stackTrace+"\n" +
				"</details>\n";
		
		ExtentReportManager.logFailDetails(formattedTrace);
	}


}

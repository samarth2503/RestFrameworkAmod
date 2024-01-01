package com.booking.util;

import java.util.Map;

import com.booking.reporting.ExtentReportManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class RestUtils {
	
	private static RequestSpecification getRequestSpecification(String endPoint, String requestBody, Map<String,String> headers)
	{
		return RestAssured.given()
				.baseUri(endPoint)
				.headers(headers)
				.contentType(ContentType.JSON)
				.body(requestBody);
	}
	
	private static void printRequestLogInReport(RequestSpecification requestSpecification)
	{
		QueryableRequestSpecification queryablerequestspecification = SpecificationQuerier.query(requestSpecification);
		ExtentReportManager.logInfoDetails("EndPoint is : "+ queryablerequestspecification.getBaseUri());
		ExtentReportManager.logInfoDetails("Method is : "+ queryablerequestspecification.getMethod());
		ExtentReportManager.logInfoDetails("Headers are : ");
		ExtentReportManager.logHeaders(queryablerequestspecification.getHeaders().asList());
		ExtentReportManager.logInfoDetails("Request Body is ");
		ExtentReportManager.logJson(queryablerequestspecification.getBody());
	}
	
	private static void printReposneLogInReport(Response resposne)
	{
		ExtentReportManager.logInfoDetails("StatusCode is : "+ resposne.getStatusCode());
		ExtentReportManager.logInfoDetails("Repsosne Headers is : "+ resposne.getHeaders().asList().toString());
		ExtentReportManager.logInfoDetails("ResposneBody is : "+ resposne.getBody());
	}
	
	public static Response performPost(String endPoint, String requestPayload, Map<String, String> headers)
	{
		RequestSpecification requestSpecification = RestUtils.getRequestSpecification(endPoint, requestPayload, headers).log().all();
		Response resposne = requestSpecification.post();
		printRequestLogInReport(requestSpecification);
		printReposneLogInReport(resposne);
		
		return resposne;
		
	}
	
	public static Response performPost(String endPoint, Map<String,Object> requestPayload, Map<String, String> headers)
	{
		Response resposne = RestAssured.given().log().all()
		.baseUri(endPoint)
		.headers(headers)
		.contentType(ContentType.JSON)
		.body(requestPayload)
		.post()
		.then().log().all().extract().response();
		
		return resposne;
		
	}
	
	public static Response performPost(String endPoint, Object requestPayloadAsPojo, Map<String, String> headers)
	{
		Response resposne = RestAssured.given().log().all()
		.baseUri(endPoint)
		.headers(headers)
		.contentType(ContentType.JSON)
		.body(requestPayloadAsPojo)
		.post()
		.then().log().all().extract().response();
		
		return resposne;
		
	}
	

}

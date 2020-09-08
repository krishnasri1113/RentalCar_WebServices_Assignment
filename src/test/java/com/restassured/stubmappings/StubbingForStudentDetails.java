package com.restassured.stubmappings;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.testng.annotations.BeforeTest;

import com.restassured.utilites.TestBase;

public class StubbingForStudentDetails extends TestBase {
	@BeforeTest
	public void getmappingreqforrentalcarsRequestandResponse()
	{
		wiremockserver.stubFor(
				get(urlEqualTo("/getstudentdetails"))
				.withHeader("Content-Type",equalTo("application/json; charset=UTF-8"))
				.willReturn(
						aResponse()
						.withStatus(200)
						.withHeader("Content-Type","application/json; charset=UTF-8")
						.withBodyFile("StudentDetails.json")
						));
	}

	

}

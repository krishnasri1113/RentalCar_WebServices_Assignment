package com.restassured.stubmappings;

import org.testng.annotations.BeforeTest;

import com.restassured.utilites.TestBase;
import  static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import  static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import  static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import  static com.github.tomakehurst.wiremock.client.WireMock.get;
public class StubMappingForRentalCars  extends TestBase{
	@BeforeTest
	public void getmappingreqforrentalcarsRequestandResponse()
	{
		wiremockserver.stubFor(
				get(urlEqualTo("/getcarsdetails"))
				.withHeader("Content-Type",equalTo("application/json; charset=UTF-8"))
				.willReturn(
						aResponse()
						.withStatus(200)
						.withHeader("Content-Type","application/json; charset=UTF-8")
						.withBodyFile("RentalCarsSchema.json")
						));
	}

}

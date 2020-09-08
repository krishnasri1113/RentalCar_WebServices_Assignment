package com.restassured.RentalCarTests;

import java.util.List;

import org.testng.annotations.Test;

import com.restassured.stubmappings.StubbingForStudentDetails;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StudentDetails extends StubbingForStudentDetails {
	
	@Test(priority=1)
	public void getusername()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getstudentdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		System.out.println(response.asString());
		String username=response.jsonPath().getString("username");
		System.out.println("username is: "+username);
	}
	@Test(priority =2)
	public void Values_of_sessionid()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getstudentdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		List<Integer> sessionidlist=response.jsonPath().getList("sessionid");
		System.out.println(sessionidlist.size());
		int sessionidsum=0;
		System.out.println("All values of session id is");
		for(int i=0;i<sessionidlist.size();i++)
		{
			//sessionidsum=sessionidsum+sessionidlist.get(i);
			System.out.println(sessionidlist.get(i));
		}
		//System.out.println("sum of session id is "+sessionidsum);
		System.out.println("last value of session id is "+sessionidlist.get(sessionidlist.size()-1));
		
		
	}
	@Test(priority=3)
	public void Allmarks_of_secondstudent()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getstudentdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		List<Integer> secondstudentmarks=response.jsonPath().getList("students[1].marks");
		System.out.println(secondstudentmarks.size());
		int sumofmarks=0;
		for(int i=0;i<secondstudentmarks.size();i++)
		{
			sumofmarks=sumofmarks+secondstudentmarks.get(i);
			
		}
		System.out.println("sum of second student marks are "+sumofmarks);
		
	}
	@Test(priority=4)
	public void secondstate_value_of_firststudent()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getstudentdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		 String secondstatevalue=response.jsonPath().getString("students[0].adresss[1].state");
		 System.out.println("second state value of first student is: "+secondstatevalue);
		

	}
	@Test(priority=5)
	public void second_contactvalue_of_firststudent()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getstudentdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		String secondcontactvalofFirstsudent=response.jsonPath().getString("students[0].contact[1]");
		System.out.println("second contact value of first student is: "+secondcontactvalofFirstsudent);
		
	}
	@Test(priority=6)
	public void Allcities_of_firststudent()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getstudentdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		String allcities=response.jsonPath().getString("students[1].adresss.city");
		System.out.println("All cities of first student is "+allcities);
		
	}
	@Test(priority=7)
	public void contacts_allstudents() {
		String sHostname="http://localhost:8088";
		String sURI="/getstudentdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		String contacts=response.jsonPath().getString("students.contact");
		System.out.println("all contacts of students are "+contacts);
		
		
	}
	@Test(priority =8)
	public void address_of_firststudent()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getstudentdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		String firststudent_address=response.jsonPath().getString("students[0].adresss");
		System.out.println("address of first student is "+firststudent_address);
	}
}

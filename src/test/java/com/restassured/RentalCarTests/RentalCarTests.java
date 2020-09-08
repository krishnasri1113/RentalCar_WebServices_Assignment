package com.restassured.RentalCarTests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import com.restassured.stubmappings.StubMappingForRentalCars;
import com.restassured.utilites.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.comparesEqualTo;;




public class RentalCarTests  extends StubMappingForRentalCars{
	Logger log = Logger.getLogger(getClass().getSimpleName());
	@Test(priority = 1)
	public void getrequest_return_listofcars()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getcarsdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response getdataresponse=RestAssured.given().contentType("application/json").get();
		System.out.println(getdataresponse.asString());
		log.info("Response as string as "+getdataresponse.asString());
		System.out.println("Status code of the response "+getdataresponse.statusCode());
		log.info("Status code of the response"+getdataresponse.statusCode());
		String responsebody=getdataresponse.getBody().asString();
		//System.out.println(responsebody);
	  assertThat(responsebody,containsString("Tesla"));
		
	}
	
	@Test(priority = 2)
	public void getrequest_return_bluecolorTeslas_And_Notes()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getcarsdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response getdataresponse=RestAssured.given().contentType("application/json").get();
		//System.out.println(getdataresponse.asString());
		System.out.println("Status code of the response "+getdataresponse.statusCode());
		List<String> limake=getdataresponse.jsonPath().getList("Cars.make");
		System.out.println(limake);
		log.info("Name of the cars are "+limake);
		List<String> licolor=getdataresponse.jsonPath().get("Cars.metadata.Color");
		System.out.println(licolor);
		log.info("Car colors are" +licolor);
		List<String> linotes=getdataresponse.jsonPath().get("Cars.metadata.Notes");
		List<String> licars=getdataresponse.jsonPath().getList("Cars");
		System.out.println(licars.size());
		for(int i=0;i<licolor.size();i++)
		{
			if(licolor.get(i).equalsIgnoreCase("Blue"))
			{
				System.out.println("Blue color cars are:"+limake.get(i));
				 String carcolor=licolor.get(i);
				 System.out.println(carcolor);
				 log.info("Blue clor cars are "+limake.get(i));
				System.out.println("Blue color Tesla notes are: "+linotes.get(i));
				log.info("Blue color car notes are "+linotes.get(i));
				assertThat(carcolor,containsString("Blue"));
			}
			
		}
		
		
	}
	
	@Test(priority=3)
	public void  getrequest_return_carsOf_Lowest_Perdayrent_and_PerdaypriceAfterdiscount()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getcarsdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response getdataresponse=RestAssured.given().contentType("application/json").get();
		//System.out.println(getdataresponse.asString());
		//System.out.println(getdataresponse);
		System.out.println("Status code of the response "+getdataresponse.statusCode());
		List<Float> liperdayrent=getdataresponse.jsonPath().getList("Cars.perdayrent");
		System.out.println("Perday rent of all cars"+liperdayrent);
		log.info("Per day rent of all cars are "+liperdayrent);
		ArrayList<Perdayrentprice> alperdayrentprice=new ArrayList<Perdayrentprice>();
		ArrayList<Perdayrentafterdiscount> alperdayrentafterdiscount=new ArrayList<Perdayrentafterdiscount>();
		for(int i=0;i<liperdayrent.size();i++)
		{
			String model=getdataresponse.jsonPath().getString("Cars["+i+"].model");
			System.out.println("Car model is:"+model);
			Integer perdayrentprice=getdataresponse.jsonPath().getInt("Cars["+i+"].perdayrent.Price");
			System.out.println("Perday rent price of the car "+perdayrentprice);
			Integer perdaydiscount=getdataresponse.jsonPath().getInt("Cars["+i+"].perdayrent.Discount");
		System.out.println("Perday rent price of the car discount is "+perdaydiscount);
		Integer perdaypriceafterdiscount=(perdayrentprice-(perdayrentprice*perdaydiscount/100));
			System.out.println("perday rent of the car price after discount is "+perdaypriceafterdiscount);
			alperdayrentprice.add(new Perdayrentprice(model,perdayrentprice));
			alperdayrentafterdiscount.add(new Perdayrentafterdiscount(model,perdaypriceafterdiscount));
			
		}
		Collections.sort(alperdayrentprice);
		Collections.sort(alperdayrentafterdiscount);
		System.out.println("Return all cars which have the lowest perdayrent prcice ");
		Iterator<Perdayrentprice> it =alperdayrentprice.iterator();
		while(it.hasNext())
		{
			Perdayrentprice tempPerdayrentprice=it.next();
			System.out.println("car model is "+tempPerdayrentprice.model+" Price of that car "+tempPerdayrentprice.perdayrentprice);
		}
		System.out.println("Return all cars which have the perdayrent after discount");
		Iterator<Perdayrentafterdiscount> it1=alperdayrentafterdiscount.iterator();
		while(it1.hasNext())
		{
			Perdayrentafterdiscount tempPerdayRentafterdiscount=it1.next();
			System.out.println("car model is "+tempPerdayRentafterdiscount.model+" Price after discount of that car is "+tempPerdayRentafterdiscount.perdayrentafterdiscount);
		}
				
		 assertThat(Integer.valueOf(100),comparesEqualTo(alperdayrentprice.get(0).perdayrentprice));
		 assertThat(Integer.valueOf(90),comparesEqualTo(alperdayrentafterdiscount.get(0).perdayrentafterdiscount));
	}
	
	@Test(priority =4)
	public void getrequest_Returns_Carswith_highestRevenue_forFullYear()
	{
		String sHostname="http://localhost:8088";
		String sURI="/getcarsdetails";
		String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response getdataresponse=RestAssured.given().contentType("application/json").get();
		List<Float> limetrics=getdataresponse.jsonPath().getList("Cars.metrics");
		System.out.println(limetrics);
		System.out.println(limetrics.size());
		ArrayList<HighestCarRevnue> alhighestcarRevnue=new ArrayList<HighestCarRevnue>();
		for(int i=0;i<limetrics.size();i++)
		{
			String model=getdataresponse.jsonPath().getString("Cars["+i+"].model");
			System.out.println("Car model is "+model);
			Float yoymaintenancecost=getdataresponse.jsonPath().getFloat("Cars["+i+"].metrics.yoymaintenancecost");
			System.out.println("yoy maintaince cost is "+yoymaintenancecost);
			Float depreciation=getdataresponse.jsonPath().getFloat("Cars["+i+"].metrics.depreciation");
			System.out.println("the depreciation is "+depreciation);
			Float yearTodate=getdataresponse.jsonPath().getFloat("Cars["+i+"].metrics.rentalcount.yeartodate");
			System.out.println("year to date  is "+yearTodate);
			Integer perdayrentprice=getdataresponse.jsonPath().getInt("Cars["+i+"].perdayrent.Price");
			System.out.println("Perday rent price of the car "+perdayrentprice);
			Integer perdaydiscount=getdataresponse.jsonPath().getInt("Cars["+i+"].perdayrent.Discount");
		System.out.println("Perday rent discount  of the car "+perdaydiscount);
		Integer perdaypriceafterdiscount=(perdayrentprice-(perdayrentprice*perdaydiscount/100));
			System.out.println("Perday rent aftter discount is "+perdaypriceafterdiscount);
			Float carRevenue=((yearTodate*perdaypriceafterdiscount)-(yoymaintenancecost+depreciation));
			System.out.println("car revnue is "+carRevenue);
			alhighestcarRevnue.add(new HighestCarRevnue(model,carRevenue));
		}
		Collections.sort(alhighestcarRevnue,Collections.reverseOrder());
		System.out.println("highest revnue cars are");
		Iterator<HighestCarRevnue> it=alhighestcarRevnue.iterator();
		while(it.hasNext())
		{
			HighestCarRevnue obj=it.next();
			System.out.println("car model is "+obj.model+" Highest car revenues are "+obj.fcarRevnue);
		}
		assertThat(Float.valueOf((float)21998.81),comparesEqualTo(alhighestcarRevnue.get(0).fcarRevnue));
		
	}
	

}

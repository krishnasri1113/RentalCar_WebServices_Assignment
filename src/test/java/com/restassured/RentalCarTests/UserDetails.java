package com.restassured.RentalCarTests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.restassured.stubmappings.StubbingforUserDetails;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserDetails extends StubbingforUserDetails {
	
	@Test 
	public void Get_UserDetails()
	{
		String sURL="https://jsonplaceholder.typicode.com/posts";
		//String sURI="/getuserDetails";
		//String sURL=sHostname+sURI;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").get();
		System.out.println(response.asString());
	List<HashMap<String,String>> userlist=response.jsonPath().getList("$");
	System.out.println(userlist.size());
	for(int i=0;i<userlist.size();i++)
	{
		String userid=String.valueOf(userlist.get(i).get("userId"));
		System.out.println(userid);
		if(userid.equals("7")) {
			System.out.println("titles are "+userlist.get(i).get("title"));
			
		}
	}
	//System.out.println(titleofuserid);
	//int userid=7;
	//if(useridlist.equals(userid))
	//{
		//String title=response.jsonPath().getString("title");
		//System.out.println(title);
	//}
//	System.out.println(useridlist.size());
//	System.out.println("title of the userid 7 is");
//	for(int i=0;i<userlist.size();i++)
//	{
//		String userid=userlist.get(i).ge
//	}
//		if(userlist.get(i).equals(userid))
//		{
//			//int UserID=useridlist.get(i);
//			String title=response.jsonPath().getString("title");
//			
//			System.out.println(title);
//			//System.out.println(UserID);
//			//assertThat(UserID,equalTo(userid));
//		}
//		
//		
//		
//	}
//	
	}
	
	

}

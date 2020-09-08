package com.restassured.utilites;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class TestBase {
	
	public static int port=8088;
	public WireMockServer wiremockserver;
	Logger log = Logger.getLogger(TestBase.class);
	public static CommonUtilites commonutil=new CommonUtilites();


@BeforeSuite
public void setUpWireMockServer() throws Exception 
{
	
     wiremockserver =new WireMockServer(port);
     //WireMock wiremock=new WireMock("localhost",9999);
     wiremockserver.start();
     commonutil.loadlog4jproperties(System.getProperty("user.dir")+"/src/main/resources/Properties/Log4j.properties");
     
     
}
@AfterSuite
public void shutdown()
{
	wiremockserver.stop();
}
}

package com.ittrident.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import static io.restassured.RestAssured.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class MonicaTest {  
	
  @BeforeMethod
  public void setup() throws IOException  
  {  
	 String filepath =  System.getProperty("user.dir") + "\\"+ "src\\main\\java\\com\\ittrident\\config";
	 FileInputStream in = new FileInputStream(filepath+"\\"+"config.properties");
	 Properties prop = new Properties();
	 prop.load(in);
	 baseURI = prop.getProperty("url_api");
  }	
	
  @Test
  public void taskTest() 
  {   
	  //Make the API Request
	  Response response = given().params("limit", 1, "page", 1).get("tasks");
	  
	  //Task Name and count
	  ArrayList<String> titles = ((ArrayList)response.jsonPath().getList("data.title"));
	  System.out.println("Total Number of Tasks is--"+titles.size());
	  for(String title : titles)
	  {
		  System.out.println("The Title is--"+title);
	  }
  }
  
  @AfterMethod
  public void clean()
  {
	 reset(); 
  }
  
}

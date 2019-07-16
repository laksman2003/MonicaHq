package com.ittrident.test;

import java.io.IOException;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ittrident.util.PropertyReader;
import io.restassured.response.Response;

public class MonicaTest {  
	
  @BeforeMethod
  public void setup() throws IOException  
  {  
	 PropertyReader prop = new PropertyReader();
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

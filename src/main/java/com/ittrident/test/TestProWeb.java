package com.ittrident.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ittrident.pages.ProgrammableWeb;
import com.ittrident.util.PropertyReader;

public class TestProWeb {
	
  WebDriver driver = null; 
	
  @BeforeMethod
  public void setup() throws IOException
  {	    
	 String path = System.getProperty("user.dir")+File.separator+"src\\main\\java\\com\\ittrident\\config"+File.separator+"chromedriver.exe";
	 System.setProperty("webdriver.chrome.driver", path);
	 ChromeOptions opt = new ChromeOptions();
	 opt.addArguments("disable-infobars");
	 driver = new ChromeDriver(opt);
	 driver.manage().window().maximize();
	 PropertyReader prop =null;
	 
	try 
	{
	  prop = new PropertyReader();
	} 
	catch (IOException e) 
	{		
		e.printStackTrace();
	}
	String url = prop.getProperty("url_webui");
	this.driver.get(url);	
  }  
	
  @Test
  public void testPrintAttributes() 
  {
	  ProgrammableWeb prowebpage = new ProgrammableWeb(this.driver).get();
	  int sum = 0;
	  	  
	  //Print API count in console
	  int apicount = prowebpage.getAPICount();
	  System.out.println("Total API Count in Search Box is--"+apicount);
	  
	  //Print Programmable attributes in console
	  Map<String, Integer> attributes = prowebpage.getAttributes();
	  for(String key : attributes.keySet())
	  {
		  System.out.println("The are "+attributes.get(key).toString()+" in "+key);
		  sum = sum + attributes.get(key);
	  }
	  
	  //Sum the count of each resources
	  System.out.println("Sum of all articles and resources are--"+sum);	  
  }
  
  @AfterMethod
  public void teardown()
  {
	  driver.close();
	  driver.quit();
  } 
  
}

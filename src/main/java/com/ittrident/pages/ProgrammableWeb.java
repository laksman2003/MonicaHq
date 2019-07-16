package com.ittrident.pages;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ittrident.util.PropertyReader;

public class ProgrammableWeb extends LoadableComponent<ProgrammableWeb>
{  
	private static final int TIMEOUT = 30;
	private static final long POLLING = 5;
	private static final String ulselector = "ul#myTab li";
	WebDriver driver;
	WebDriverWait wait;
	
	
	@FindBy(css="input#edit-term--2")
	WebElement searchbox;

	@FindBy(css=ulselector)
	List<WebElement> ullist;

	public ProgrammableWeb(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
		wait = new WebDriverWait(driver, TIMEOUT, POLLING);
	}
	

	@Override
	protected void load() { 
	}

	@Override
	protected void isLoaded() throws Error
	{
		try 
	    {
	    	//Wait for Page Load
			ExpectedCondition<Boolean> pageLoadCondition = new
		            ExpectedCondition<Boolean>() {
		                public Boolean apply(WebDriver driver) {                	
		                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		                }
		            };
		    wait.until(pageLoadCondition);
	    	
		    //Wait for Tabs
	    	wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(ulselector), 3));
	    }
	    catch(Exception e)
	    {
	    	throw new Error("Page not loaded");
	    }  
	}
	
	/**
	 * This method will return the total API count displayed in Search Box
	 * @return int
	 */
	public int getAPICount()
	{
	   int apicount = 0;
	   String placeholder = this.searchbox.getAttribute("placeholder");
	   String counttext = placeholder.split(" ")[2].replace(",", "");  
	   apicount = Integer.parseInt(counttext);   
	   return apicount;	
	}
	
	/**
	 * This method will return the attributes in tab about the site
	 * @return Map
	 */
	public Map<String, Integer> getAttributes()
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(WebElement li : this.ullist)
		{
			if(li.findElement(By.cssSelector("a")).getAttribute("innerHTML").toLowerCase().contains("summary"))
			{continue;}
			else
			{
				String key = li.findElement(By.cssSelector("a")).getAttribute("class")
						.trim().replace(" ", "");
				int value = Integer.parseInt(li.findElement(By.cssSelector("a>span")).getAttribute("innerHTML")
						.trim().replace(" ", "").replace("(", "").replace(")", ""));
				map.put(key,  value);						
			}
		}
		
		return map;	
	}

}

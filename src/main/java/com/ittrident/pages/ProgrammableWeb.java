package com.ittrident.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgrammableWeb extends LoadableComponent<ProgrammableWeb>
{  
	private static final int TIMEOUT = 30;
	private static final long POLLING = 5;
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(css="input#edit-term--2")
	WebElement searchbox;

	@FindBy(css="ul#myTab li")
	WebElement ullist;

	protected ProgrammableWeb(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
		wait = new WebDriverWait(driver, TIMEOUT, POLLING);
	}
	

	@Override
	protected void load() {
	
		driver.get("https://www.programmableweb.com/category/open-data/api");
		
	}

	@Override
	protected void isLoaded() throws Error {
		
		ExpectedCondition<Boolean> pageLoadCondition = new
	            ExpectedCondition<Boolean>() {
	                public Boolean apply(WebDriver driver) {                	
	                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                }
	            };
	    wait.until(pageLoadCondition);
		
	}
	
	/**
	 * This method will return the total API count displayed in Search Box
	 * @return
	 */
	public int getAPICount()
	{
	   int apicount = 0;
	   String placeholder = this.searchbox.getAttribute("placeholder");
	   String counttext = placeholder.split(" ")[2].replace(",", "");  
	   apicount = Integer.parseInt(counttext);   
	   return apicount;	
	}

}

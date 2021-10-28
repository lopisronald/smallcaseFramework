package com.smallcase.genericfunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.smallcase.pageobject.FlipkartHomePage;
import io.qameta.allure.Step;

public class FlipkartHomePageActions {
	
	//Required references are created
	WebDriver driver;
	FlipkartHomePage flipkartHomePageObj;
	
	 //Provide driver reference to all the elements of classes which will be called from this class
	 public  FlipkartHomePageActions(WebDriver driver)
	 {
		 this.driver =driver;
		 flipkartHomePageObj = PageFactory.initElements(driver, FlipkartHomePage.class);
	 }
	 
    //Search for the product whose name is sent by calling method
	@Step 
	public boolean searchProduct(String productName) 
	{	
		flipkartHomePageObj.clickCloseIconInLoginPopUp();
		flipkartHomePageObj.searchProduct(productName);
		flipkartHomePageObj.clickSearch();
		
		try
		{
			flipkartHomePageObj.clickOnProductName();
			return true;
		}
		
		catch(Exception exceptionObj)
		{
			Assert.assertTrue(false, productName+" is currently unavailable in Flipkart");
		}
		
		return false;			
	}	
}

package com.smallcase.genericfunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.smallcase.pageobject.AmazonHomePage;

import io.qameta.allure.Step;

public class AmazonHomePageActions {

	//Required references are created
	WebDriver driver;
	AmazonHomePage amazonHomePageObj;
	
	 //Provide driver reference to all the elements of classes which will be called from this class
	 public  AmazonHomePageActions(WebDriver driver)
	 {
		 this.driver =driver;
		 amazonHomePageObj = PageFactory.initElements(driver, AmazonHomePage.class);
	 }
	 
	//Search for the product whose name is sent by calling method
	@Step
	public boolean searchProduct(String productName) 
	{	
		amazonHomePageObj.searchProduct(productName);
		amazonHomePageObj.clickSearch();

		try
		{
			amazonHomePageObj.clickOnProductName();
			return true;
		}
		
		catch(Exception exceptionObj)
		{
			Assert.assertTrue(false, productName+" is currently unavailable in Amazon");
		}	
		
		return false;		
	}
}

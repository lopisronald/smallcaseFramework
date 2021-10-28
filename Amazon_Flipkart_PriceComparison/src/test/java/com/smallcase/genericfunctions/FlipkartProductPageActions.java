package com.smallcase.genericfunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.smallcase.pageobject.FlipkartProductPage;
import com.smallcase.util.CommonFunctions;
import com.smallcase.util.Log;
import io.qameta.allure.Step;

public class FlipkartProductPageActions {

	//Required references are created
	WebDriver driver;
	FlipkartProductPage flipkartProductPageObj;
	FlipkartHomePageActions flipkartHomePageActionsObj;
	CommonFunctions commonFunctionsObj;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public FlipkartProductPageActions(WebDriver driver)
	 {
		 this.driver =driver;
		 flipkartProductPageObj = PageFactory.initElements(driver, FlipkartProductPage.class);
		 flipkartHomePageActionsObj = PageFactory.initElements(driver, FlipkartHomePageActions.class);
		 commonFunctionsObj=PageFactory.initElements(driver, CommonFunctions.class);
	 }
	
	//This method will add product to cart if it is searched successfully and available to add to cart
	@Step
	public boolean addProductToCart(String productName)
	{	
		boolean result=flipkartHomePageActionsObj.searchProduct(productName);
		
		if(result)
		{
			commonFunctionsObj.switchToChildWindow();
			Log.info("The Price of Product before adding to Flipkart cart is "+flipkartProductPageObj.priceOnProductPage());
			
			try
			{
				flipkartProductPageObj.clickAddToCartButton();
			}
			
			catch(Exception exceptionObj)
			{
				Assert.assertTrue(false, productName+" cannot be added to cart in Flipkart and may be available in future");

			}
		}
		return result;
	}
}

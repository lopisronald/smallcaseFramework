package com.smallcase.genericfunctions;

import org.openqa.selenium.WebDriver;
import com.smallcase.util.Log;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.smallcase.pageobject.AmazonProductPage;
import com.smallcase.util.CommonFunctions;

import io.qameta.allure.Step;

public class AmazonProductPageActions {

	//Required references are created
	WebDriver driver;
	AmazonProductPage amazonProductPageObj;
	AmazonHomePageActions amazonHomePageActionsObj;
	CommonFunctions commonFunctionsObj;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public AmazonProductPageActions(WebDriver driver)
	 {
		 this.driver =driver;
		 amazonProductPageObj = PageFactory.initElements(driver, AmazonProductPage.class);
		 amazonHomePageActionsObj = PageFactory.initElements(driver, AmazonHomePageActions.class);
		 commonFunctionsObj=PageFactory.initElements(driver, CommonFunctions.class);
	 }
	
	//This method will add product to cart if it is searched successfully and available to add to cart
	@Step
	public boolean addProductToCart(String productName)
	{	
		//Call the method to search the product
		boolean result=amazonHomePageActionsObj.searchProduct(productName);
		
		if(result)
		{
			commonFunctionsObj.switchToChildWindow();	
			Log.info("The Price of Product before adding to Amazon cart is "+amazonProductPageObj.priceOnProductPage());
			
			try
			{
				amazonProductPageObj.clickAddToCartButton();		
				amazonProductPageObj.clickCartButton();
			}
			
			catch(Exception exceptionObj)
			{
				Assert.assertTrue(false, productName+" cannot be added to cart in Amazon and may be available in future");

			}	
		}
		else
		{
			Assert.assertTrue(result, productName+" is currently unavailable in Amazon");
		}
		return result;	
	}
}

package com.smallcase.genericfunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.smallcase.pageobject.AmazonCartPage;
import com.smallcase.util.CommonFunctions;
import com.smallcase.util.Log;
import io.qameta.allure.Step;

public class AmazonCartPageActions {
	
	//Required references are created
	WebDriver driver;
	AmazonCartPage amazonCartPageObj;
	AmazonProductPageActions amazonProductPageActionsObj;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public AmazonCartPageActions(WebDriver driver)
	 {
		 this.driver =driver;
		 amazonCartPageObj = PageFactory.initElements(driver, AmazonCartPage.class);
		 amazonProductPageActionsObj= PageFactory.initElements(driver, AmazonProductPageActions.class);
	 }
	
	//Get the final price after adding to cart
	@Step
	public double productAvailableToBuyInAmazon(String productName, int quantity)
	{
		double result=getProductFinalPrice(productName,quantity);	
		
		if(result>0) 
		  { 
			  Log.info("The price for " +quantity + " quanity of " +productName +" is " +result);
		  }
		return result;
	}
	
	@Step
	public double getProductFinalPrice(String productName, int quantity)
	{
		//Call the method which will add product to the cart
		boolean result=amazonProductPageActionsObj.addProductToCart(productName);
		
		if(result)
		{	
			//Capture price for single quantity of product
			String originalAmount = amazonCartPageObj.getTotalAmount();
		
			if(quantity>1)
			{
				//Select required quantity from selection dropdown
				amazonCartPageObj.selectQuantity(Integer.toString(quantity));
				
				if(!amazonCartPageObj.cannotIncreaseItemCountMessage.isDisplayed())
				{
					//Below 6 lines of code will try to get the updated price after increasing the quantity
					String updatedAmount = amazonCartPageObj.getTotalAmount();
					while(updatedAmount.equalsIgnoreCase(originalAmount))
					{
						updatedAmount = amazonCartPageObj.getTotalAmount();
						continue;
					}
					
					//Delete the product from the cart and return the final price after increasing the quantity
					amazonCartPageObj.clickDeleteLabel();
					return (CommonFunctions.returnDigitsFromString(updatedAmount));
				}
				
				else
				{
					//Delete the product from the cart
					amazonCartPageObj.clickDeleteLabel();
					
					//The required quantity of product is currently unavailable
					Assert.assertTrue(false, "You cannot order "+quantity+" of "+productName +" in Amazon"); 
				}
			}
		
			else
			{
				//Delete the product from the cart
				amazonCartPageObj.clickDeleteLabel();
				
				//Return the price for single quantity of product
				return (CommonFunctions.returnDigitsFromString(originalAmount));
			}
		}
		return 0;	
	}
}

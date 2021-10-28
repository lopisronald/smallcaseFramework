package com.smallcase.genericfunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.smallcase.pageobject.FlipkartCartPage;
import com.smallcase.util.CommonFunctions;
import com.smallcase.util.Log;
import io.qameta.allure.Step;

public class FlipkartCartPageActions {
	
	//Required references are created
	WebDriver driver;
	FlipkartCartPage flipkartCartPageObj;
	FlipkartProductPageActions flipkartProductPageActionsObj;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public FlipkartCartPageActions(WebDriver driver)
	 {
		 this.driver =driver;
		 flipkartCartPageObj = PageFactory.initElements(driver, FlipkartCartPage.class);
		 flipkartProductPageActionsObj= PageFactory.initElements(driver, FlipkartProductPageActions.class);
	 }
	
	//Get the final price after adding to cart
	@Step
	public double productAvailableToBuyInFlipkart(String productName, int quantity)
	{
		double result=getProductFinalPrice(productName,quantity);	
		if(result>0.0) 
		  { 
			  Log.info("The price for " +quantity + " quanity of " +productName +" is " +result);  
		  }  
		return result;
	}
	
	@Step
	public double getProductFinalPrice(String productName, int quantity)
	{
		//Call the method which will add product to the cart
		boolean result=flipkartProductPageActionsObj.addProductToCart(productName);
		
		if(result)
		{	
			//Capture price for single quantity of product
			String originalAmount = flipkartCartPageObj.getTotalAmount();
		
			if(quantity>1)
			{	
				//Click + icon quantity-1 number of times
				for(int i=2;i<=quantity;i++)
				{			
						flipkartCartPageObj.clickAddQuantity();					
				}
				
				  //Get the Final Quantity value
				  int actualQuanity=CommonFunctions.convertStringtoInt(flipkartCartPageObj.getQuanityValue()); 
				  
				  if(actualQuanity==quantity) 
				  	{ 
					  //Below 6 lines of code will try to get the updated price after increasing the quantity
					  String updatedAmount = flipkartCartPageObj.getTotalAmount();
				  
					  while(updatedAmount.equalsIgnoreCase(originalAmount)) 
					  { 
						  updatedAmount = flipkartCartPageObj.getTotalAmount(); 
						  continue; 
					  }
					  
					  //Delete the product from the cart and return the final price after increasing the quantity
					  removeItemsFromCart();
				      return (CommonFunctions.returnDigitsFromString(updatedAmount)); 
				  	} 
				  else 
				  {
						//Delete the product from the cart
					  removeItemsFromCart();
					  
						//The required quantity of product is currently unavailable
					  Assert.assertTrue(false, "You cannot order "+quantity+" of "+productName +" in Flipkart"); 
				  }
				 
			}
			else
			{
				//Delete the product from the cart
				removeItemsFromCart();
				
				//Return the price for single quantity of product
				return (CommonFunctions.returnDigitsFromString(originalAmount));
			}
		}
			return 0;	
	}	
	
	//Remove the items from the cart
	public void removeItemsFromCart()
	{
		flipkartCartPageObj.clickOnRemoveButton();
		flipkartCartPageObj.clickOnRemoveButtonInPopUp();
	}
}

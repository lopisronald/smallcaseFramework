package com.smallcase.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.smallcase.util.CommonFunctions;

public class FlipkartProductPage {
	
	//Required references are created
	WebDriver driver;
	WebDriverWait wait;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public FlipkartProductPage(WebDriver driver) 
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,10);
	}
	
	//Identify all the required page elements
	@FindBy(how = How.XPATH, using = "//ul[@class='row']/li/button")
	WebElement addToCartButton;
	
	@FindBy(how=How.XPATH, using="//img[@id='price-info-icon']/parent::div/preceding-sibling::div/div[1]/div[1]")
	WebElement priceOnProductPage;
		
	public void clickAddToCartButton()
	{
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		
		if(addToCartButton.isDisplayed())
			{
				addToCartButton.click();
			}	
	}
	
	//Here we verify if the price is displayed in Product page before adding to cart
	public double priceOnProductPage()
	{
		double productPrice=0;

		try
		{
			wait.until(ExpectedConditions.visibilityOf(priceOnProductPage));
			return (CommonFunctions.returnDigitsFromString(priceOnProductPage.getText()));
		}
		
		catch(Exception exceptionObj)
		{
			Assert.assertTrue(false, "The Product price is not available to buy, please select some other product"); 
		}
		
		return productPrice;
		
	}	
}

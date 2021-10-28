package com.smallcase.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.smallcase.util.CommonFunctions;

public class AmazonProductPage {

	//Required references are created
	WebDriver driver;
	WebDriverWait wait;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public AmazonProductPage(WebDriver driver) 
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,10);
	}
	
	//Identify all the required page elements
	@FindBy(how = How.ID, using = "add-to-cart-button")
	WebElement addToCartButton;
	
	@FindBy(how=How.XPATH, using="//div[@id='price']/table/tbody/tr[2]/td[2]/span")
	WebElement priceOnProductPage;
	
	@FindBy(how=How.ID, using="attach-sidesheet-view-cart-button")
	WebElement cartButton;
	
	@FindBy(how=How.ID, using="nav-cart-count-container")
	WebElement cartIcon;
	
	public void clickAddToCartButton()
	{
		
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));
		addToCartButton.click();
	}
	
	//Here we verify if the price is displayed in Product page before adding to cart
	public double priceOnProductPage()
	{
		double productPrice=0;
		try {
			wait.until(ExpectedConditions.visibilityOf(priceOnProductPage));
			return (CommonFunctions.returnDigitsFromString(priceOnProductPage.getText()));
		}
		
		catch(Exception exceptionObj)
		{
			Assert.assertTrue(false, "The Product price is not available to buy, please select some other product"); 
		}
		
		return productPrice;	
	}
	
	//Click on Cart button once after clicking on Add to Cart button. This is only in Amazon website case.
	public void clickCartButton()
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(cartButton));
		cartButton.click();
		}
		
		catch(Exception exceptionObj)
		{
			clickCartIcon();
		}
	}
	
	/*
	 * Click on Cart button once after clicking on Add to Cart button. This is only
	 * in Amazon website case. Sometimes this button appears and other time earlier
	 * method button will appear. Hence both button scenarios are handled.
	 */
	public void clickCartIcon()
	{
		wait.until(ExpectedConditions.visibilityOf(cartIcon));
		cartIcon.click();
	}
	
}

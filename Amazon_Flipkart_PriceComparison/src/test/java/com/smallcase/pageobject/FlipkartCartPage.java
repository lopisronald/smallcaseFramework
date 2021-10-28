package com.smallcase.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartCartPage {
	
	//Required references are created
	WebDriver driver;
	WebDriverWait wait;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public FlipkartCartPage(WebDriver driver)
	{
		this.driver= driver;
		wait = new WebDriverWait(driver,10);
	}
	
	//Identify all the required page elements
	@FindBy(how = How.XPATH, using ="//div[contains(text(),'Save for later')]/parent::div/preceding-sibling::div/descendant::button[2]/preceding-sibling::div/input")
	WebElement quanityValueAfterClickingOnAddQuanityIcon;
	
	@FindBy(how = How.XPATH, using ="//div[contains(text(),'Save for later')]/parent::div/preceding-sibling::div/descendant::button[2]")
	WebElement addQuantity;
	
	@FindBy(how = How.XPATH , using="//div[contains(text(),'Total Amount')]/parent::div/following-sibling::span/descendant::span")
	WebElement totalAmount;
	
	@FindBy(how = How.XPATH, using="//div[contains(text(),'Remove')]")
	WebElement removeButton;
	
	@FindBy(how = How.XPATH, using="//div[contains(text(), 'Remove Item')]/following-sibling::div[2]/div/div[contains(text(),'Remove')]")
	WebElement removeButtonInPopUp;
	
	
	public String getQuanityValue()
	{	
		String quanityValue="";
		
		/*
		 * The + icon in cart page once clicked will be disabled mode for some seconds
		 * and Quantity value will not be updated, hence this loop will run until + icon is enabled
		 */
		while(!addQuantity.isEnabled())
		{	
			continue;				
		}
		
		/*
		 * Once the + icon is enabled the quantity value will be updated to new value
		 * after some. Hence this loop is being used
		 */
		while(addQuantity.isEnabled())
		{
			wait.until(ExpectedConditions.visibilityOf(quanityValueAfterClickingOnAddQuanityIcon));
			quanityValue= quanityValueAfterClickingOnAddQuanityIcon.getAttribute("value");
			break;
		}
		return quanityValue;
	}
	public void clickAddQuantity()
	{
		wait.until(ExpectedConditions.visibilityOf(addQuantity));
		
		//+ icon will be disabled after we click it. Hence we need to wait until it is enabled back.
		if(addQuantity.isEnabled())
		{
			addQuantity.click();
		}
	}
	
	public String getTotalAmount()
	{
		wait.until(ExpectedConditions.visibilityOf(totalAmount));
		return totalAmount.getText(); 
	}
	
	//Click on remove button in the cart page
	public void clickOnRemoveButton()
	{
		wait.until(ExpectedConditions.visibilityOf(removeButton));
		removeButton.click();
	}
	
	//select Remove after clicking on remove button
	public void clickOnRemoveButtonInPopUp()
	{
		wait.until(ExpectedConditions.visibilityOf(removeButtonInPopUp));
		removeButtonInPopUp.click();
	}
}

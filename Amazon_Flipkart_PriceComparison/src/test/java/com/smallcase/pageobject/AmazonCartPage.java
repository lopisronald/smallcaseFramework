package com.smallcase.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.smallcase.util.CommonFunctions;

public class AmazonCartPage {

	//Required references are created
	WebDriver driver;
	WebDriverWait wait;
	CommonFunctions commonFunctionsObj;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public AmazonCartPage(WebDriver driver)
	{
		this.driver= driver;
		wait = new WebDriverWait(driver,10);
		commonFunctionsObj=PageFactory.initElements(driver, CommonFunctions.class);
	}
	
	//Identify all the required page elements
	@FindBy(how = How.ID , using ="quantity")
	WebElement selectQuantityDropdown;
	
	@FindBy(how = How.ID , using="sc-subtotal-amount-buybox")
	WebElement totalAmount;
	
	@FindBy(how = How.XPATH, using="//span[contains(text(),'This seller has a limit')]")
	public WebElement cannotIncreaseItemCountMessage;
	
	@FindBy(how = How.XPATH, using="//input[@value='Delete']")
	public WebElement deleteLabel;
	
	public void selectQuantity(String value)
	{
		wait.until(ExpectedConditions.visibilityOf(selectQuantityDropdown));
		commonFunctionsObj.selectValueFromDropdown(selectQuantityDropdown, value);
	}
	
	public String getTotalAmount()
	{
		wait.until(ExpectedConditions.visibilityOf(totalAmount));
		return totalAmount.getText(); 
	}
	
	//Delete the item from the cart
	public void clickDeleteLabel()
	{
		wait.until(ExpectedConditions.visibilityOf(deleteLabel));
		deleteLabel.click();

	}
	
}

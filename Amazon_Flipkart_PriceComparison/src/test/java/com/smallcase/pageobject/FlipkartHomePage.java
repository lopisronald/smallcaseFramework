package com.smallcase.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.smallcase.util.Log;

public class FlipkartHomePage {
	
	//Required references are created
	WebDriver driver;
	WebDriverWait wait;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public FlipkartHomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,10);
	}

	//Identify all the required page elements
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'✕')]")
	WebElement closeIconInLoginPopUp;
	
	@FindBy(how = How.NAME, using = "q")
	WebElement searchBox;
	
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'REDMI Note 10') and contains(text(),'Frost White') and contains(text(),'64 GB')]")
	public WebElement productTitle;
	
	public void searchProduct(String productName)
	{
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.sendKeys(productName);	
	}
	
	public String getProductName()
	{
		return (productTitle.getText());
	}
	
	public void clickOnProductName()
	{
		productTitle.click();
	}
	
	public void clickSearch()
	{
		wait.until(ExpectedConditions.visibilityOf(searchButton));
		searchButton.click();	
	}
	
	/*
	 * This method clicks on the pop up which is always displayed for the Login
	 * screen in flipkart. In case if it is not displayed then same is being handled
	 * in try catch block.
	 */
	public void clickCloseIconInLoginPopUp()
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(closeIconInLoginPopUp));
		closeIconInLoginPopUp.click();
		}
		
		catch(Exception exceptionObj)
		{
			Log.info("Login Pop up is not displayed");
		}		
	}	
}

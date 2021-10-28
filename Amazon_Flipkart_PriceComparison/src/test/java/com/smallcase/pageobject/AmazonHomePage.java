package com.smallcase.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonHomePage {

	//Required references are created
	WebDriver driver;
	WebDriverWait wait;
	
	//Provide driver reference to all the elements of classes which will be called from this class
	public AmazonHomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,10);
	}

	//Identify all the required page elements
	@FindBy(how = How.ID, using = "twotabsearchtextbox")
	WebElement searchBox;
	
	@FindBy(how = How.ID, using = "nav-search-submit-button")
	WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Redmi Note 10') and contains(text(),'Frost White') and contains(text(),'64GB')]")
	WebElement productTitle;
	
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
}

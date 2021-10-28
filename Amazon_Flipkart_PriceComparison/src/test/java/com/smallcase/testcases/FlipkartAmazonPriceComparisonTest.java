package com.smallcase.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.smallcase.genericfunctions.AmazonCartPageActions;
import com.smallcase.genericfunctions.AmazonHomePageActions;
import com.smallcase.genericfunctions.FlipkartCartPageActions;
import com.smallcase.genericfunctions.FlipkartHomePageActions;

import com.smallcase.util.CommonFunctions;
import com.smallcase.util.AllureListener;
import com.smallcase.util.BrowserManager;

@Listeners({AllureListener.class})
public class FlipkartAmazonPriceComparisonTest 
{
	//Declare the required variables and references
	public static WebDriver driver;	
	public static String urlFlipkart = "";
	public static String urlAmazon = "";
	public static String browser= "";
	public static String productName="";
	public static String productQuantity="";
	public static String priceCompareQuantity="";
	
	
	CommonFunctions commonFunctionsObj;		
	FlipkartHomePageActions flipkartHomePageActionsObj;
	FlipkartCartPageActions flipkartCartPageActionsObj;
	AmazonHomePageActions amazonHomePageActionObj;
	AmazonCartPageActions amazonCartPageActionsObj;
	

	@BeforeMethod(alwaysRun = true)
	public void initialSetup(Method method) throws Exception 
	{
		//Load the Flipkart URL
		driver.get(urlFlipkart);
	}

	@BeforeTest(alwaysRun = true)
	public void setup() throws Exception
	{	
		//Fetch the values from datafile.properties file
		urlFlipkart = CommonFunctions.readPropertiesFileAndReturnValue("urlFlipkart");
		urlAmazon = CommonFunctions.readPropertiesFileAndReturnValue("urlAmazon");
		browser = CommonFunctions.readPropertiesFileAndReturnValue("browser");
		productName=CommonFunctions.readPropertiesFileAndReturnValue("productName");
		productQuantity=CommonFunctions.readPropertiesFileAndReturnValue("productQuantity");
		priceCompareQuantity=CommonFunctions.readPropertiesFileAndReturnValue("priceCompareQuantity");
		
		//Get the appropriate browser driver
		driver = BrowserManager.getDriver(browser);
		
		//Required objects are created
		flipkartCartPageActionsObj=new FlipkartCartPageActions(driver);
		amazonCartPageActionsObj=new AmazonCartPageActions(driver);
		commonFunctionsObj=new CommonFunctions(driver);
					
	}

	@Test(priority=1)
	public void verifyFlipkartProductAvailibility() throws Exception {
		
		//Convert string quantity value to integer
		int quantityInInt=CommonFunctions.convertStringtoInt(productQuantity); 
		
		flipkartCartPageActionsObj.productAvailableToBuyInFlipkart(productName,quantityInInt);
	  }
	 	
		
	@Test(priority=2) 
	public void compareProductPriceInFlipkartAndAmazon() throws Exception { 
		
		//Convert string quantity value to integer
		int quantityInInt=CommonFunctions.convertStringtoInt(priceCompareQuantity);
		
		//Get the price of product from Flipkart website
		double flipkartPrice=flipkartCartPageActionsObj.productAvailableToBuyInFlipkart(productName,quantityInInt); 
		
		//Load the URL of Amazon
		commonFunctionsObj.loadURL(urlAmazon); 
		
		//Get the price of product from Amazon website
		double amazonPrice=amazonCartPageActionsObj.productAvailableToBuyInAmazon(productName,quantityInInt); 
		
		//Compare the prices from Flipkart and Amazon
		CommonFunctions.compareProductPrices(flipkartPrice,amazonPrice); 
		}
		 

   	@AfterTest
	public void close_browser() throws Exception {
		
   		//Close all the browsers
		driver.quit();
   	}
}

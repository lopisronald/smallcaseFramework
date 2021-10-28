package com.smallcase.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonFunctions {

	WebDriver driver;

	public CommonFunctions(WebDriver driver)
	 {
		 this.driver =driver;	 
	 }
	
	//This method returns the value(from datafile.properties) for the key sent by the calling method
	public static String readPropertiesFileAndReturnValue(String key)
	{
		File file = new File(System.getProperty("user.dir") + "\\TestData\\datafile.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);

		} catch (IOException exceptionObj) {
			exceptionObj.printStackTrace();
		}

		String value = prop.getProperty(key);
		return value;
	}
	
	//This method return the integer value for the string sent by the calling method
	public static int convertStringtoInt(String stringValue)
	{
		int integerValue=Integer.parseInt(stringValue);
		return integerValue;
	}
	
	//This method returns double value for the string sent by the calling method
	public static double returnDigitsFromString(String amount)
	{
		String amountWithoutSpace=amount.trim();
		String[] temporaryStringArray= {};
		
		if(!Character.isDigit(amountWithoutSpace.charAt(0)))
		{
			temporaryStringArray=amountWithoutSpace.replace(",", "").split("\\"+Character.toString(amountWithoutSpace.charAt(0)));
			return Double.parseDouble(temporaryStringArray[1]);
		}
		else
		{
			return Double.parseDouble(amountWithoutSpace.replace(",", ""));
		}
		
		}
	
	//This method switched the driver control to the child window(Latest opened browser window)
	public void switchToChildWindow()
	{
		Set <String> windows = driver.getWindowHandles();
		Iterator <String>iteratorObj = windows.iterator();
		
		while(iteratorObj.hasNext())
		{	
			String child = iteratorObj.next();
			driver.switchTo().window(child);
		}
	}
	
	//This method selects the value from select dropdown for the WebElement and Value sent by calling method
	public void selectValueFromDropdown(WebElement selectElement, String value)
	{
		Select selectObj=new Select(selectElement);
		selectObj.selectByValue(value);
	}
	
	//Load the URL(sent by calling method) in the browser 
	public void loadURL(String URL)
	{
		driver.get(URL);
	}
	
	//Compare the prices sent by calling method and capture log accordingly
	public static void compareProductPrices(double flipkartPrice, double amazonPrice)
	{
		
		if(flipkartPrice<amazonPrice)
		{
			Log.info("The product is available in Flipkart at cheaper price. " +"You will save "+ +(amazonPrice-flipkartPrice));
		}
		
		else if(amazonPrice<flipkartPrice)
		{
			Log.info("The product is available in Amazon at cheaper price. " +"You will save "+ +(flipkartPrice-amazonPrice));
		}
		
		else
		{
			Log.info("The product is available at same price in both Flipkart and Amazon");
		}
	}
}

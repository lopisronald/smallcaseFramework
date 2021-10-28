package com.smallcase.util;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.Reporter;

public class BrowserManager {
	public static WebDriver driver;
	public static String localdir = System.getProperty("user.dir");
	public static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<WebDriver>();
	static Properties propertiesObj;
	
//	This Method will launch browser and hit URL and return driver 
	public static WebDriver getDriver(String browsername) throws Exception {
		
		String localdir = System.getProperty("user.dir");
		if (browsername.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", localdir + "\\Drivers\\chromedriver.exe");
			
			// To handle certificate issue Chrome capability is used
			ChromeOptions capability = new ChromeOptions();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			
			driver = new ChromeDriver(capability);

		} else if (browsername.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", localdir + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		} else if (browsername.equalsIgnoreCase("IE")) {
			
			System.setProperty("webdriver.ie.driver", localdir + "\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
		} else if (browsername.equalsIgnoreCase("edge")) {
			
			System.setProperty("webdriver.edge.driver", localdir + "\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			
		} 
		else {
			
			Assert.assertTrue(false, "No Browser type sent");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		Reporter.log(browsername+ " browser is launched successfully");
	
		driverInstance.set(driver);
		return driver;
	}

	//Get the driver instance
	public static WebDriver getDriverInstance() {
		return driverInstance.get();
	}

}

package com.smallcase.util;

import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener {
	private static String getMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] saveScreenshotOnFailure(WebDriver driver) throws IOException {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveLogAsText(String text) {
		return text;
	}

	@Override
	public void onTestStart(ITestResult result) {
		Log.info("Execution of testcase " + getMethodName(result) + " has Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Log.info("Execution of testcase " + getMethodName(result) + " is Completed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Log.info("OnTestFailure Method " + getMethodName(result) + " Failure");
		WebDriver driver = BrowserManager.getDriverInstance();

		if (driver instanceof WebDriver) {
			Log.info("Screenshot captured for test case :" + getMethodName(result));
			try {
				saveScreenshotOnFailure(driver);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Log.info("OnTestSkipped Method " + getMethodName(result) + " Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Log.info("OnTestFailureWithinSuccess Method " + getMethodName(result) + " Failure");
	}


	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}
}

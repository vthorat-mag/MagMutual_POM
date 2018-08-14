package com.mm.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mm.browsers.BrowserTypes;
import com.mm.utils.ExtentReporter;

import MMTestCase.SmokeTestCasesUpdated;

public class testListners implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("==============================================");
		System.out.println(result.getName() + " test case execution started.");
		System.out.println("==============================================");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		ExtentReporter.logger=null;
		TakesScreenshot ts=(TakesScreenshot)SmokeTestCasesUpdated.driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String screenshotLocation ="C:\\SmokeTestFM\\"+result.getName()+".png";
		File destination=new File(screenshotLocation);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}

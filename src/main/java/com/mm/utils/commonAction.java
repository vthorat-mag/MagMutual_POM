package com.mm.utils;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import BaseClass.CommonActionInterface;

public class commonAction implements CommonActionInterface {
	
	
	Properties pro=new Properties();
	

	public void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow) {

		try {
			driver.switchTo().window(parentwindow);
			ExtentReporter.logger.log(LogStatus.PASS, "Control switched back to parent window.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to parent window.");
		}
	}

	public void switchToFrame(WebDriver driver, String frameName) {

		try {
			driver.switchTo().frame(frameName);
			ExtentReporter.logger.log(LogStatus.PASS, "Control switched Switched to frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to frame.");
		}

	}

	public void switchToParentWindowfromframe(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			ExtentReporter.logger.log(LogStatus.PASS, "User Switched to default frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to default frame.");
		}
	}

	public String randomNoGenerator() {
		return RandomStringUtils.random(2, "1234567890");
	}

	public void navigateTo(String url) {

	}

	public void enterTextIn(WebElement pageElement, String text, String textField) {
		// TODO Auto-generated method stub
		try {
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
			pageElement.sendKeys(text);
			ExtentReporter.logger.log(LogStatus.PASS, "Value entered in text box -" + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

		public void clickButton(WebElement pageElement, String buttonName) {
			// TODO Auto-generated method stub
			try
			{
				Assert.assertTrue(pageElement.isDisplayed(), buttonName+" button is displayed on screen.");
				pageElement.click();
				ExtentReporter.logger.log(LogStatus.PASS, "clicked on button - "+buttonName);
			}catch(Exception e)
			{
				ExtentReporter.logger.log(LogStatus.FAIL, buttonName+ " element is not found.");
			}
		}

	public void waitFor(WebDriver driver, long time) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void close(WebDriver driver) {
		// TODO Auto-generated method stub
		driver.quit();
		ExtentReporter.logger.log(LogStatus.PASS, "Browser is closed.");
	}

	public void takeScreenShot(String pageTitle) {
		// TODO Auto-generated method stub
		
	}

	public String getPageTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getText(WebElement pageElement) {
		// TODO Auto-generated method stub
		return null;
	}

	public void clearTextBox(WebElement pageElement, String textField) {
		// TODO Auto-generated method stub
		try
		{
			Assert.assertTrue(pageElement.isDisplayed(), textField+" is displayed");
			pageElement.clear();
			ExtentReporter.logger.log(LogStatus.PASS, "Cleared the initial contents from " + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

	public String getAttributeValue(WebElement pageElement, String attributeName) {
		
		return null;
	}

	public void click(WebElement pageElement, String ElementName) {

		try {
			Assert.assertTrue(pageElement.isDisplayed(), ElementName + " is not displayed on screen.");
			pageElement.click();
			ExtentReporter.logger.log(LogStatus.PASS, "clicked on Element - " + ElementName);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, ElementName + " element is not found on page.");
		}

	}

	public void visibilityOfElement(WebElement pageElement, String text) {

		try {
			Assert.assertTrue(pageElement.isDisplayed(), "Logo " + text + " is not displayed on the page.");

			ExtentReporter.logger.log(LogStatus.PASS, "Logo " + text + " is displayed on page after login");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Logo " + text + " is not displayed on page after login");
		}
	}

	public String switchToWindow(WebDriver driver) {

		ExtentReporter.logger.log(LogStatus.INFO, "Switching to the pop up window");
		Set<String> handles = driver.getWindowHandles();      //Return a set of window handle
		 
	    String parentWindow = driver.getWindowHandle();
	   
		 for(String currentWindow : handles){
	    	   if(!currentWindow.equals(parentWindow))
	    	  {
	    	   driver.switchTo().window(currentWindow);
	    	   ExtentReporter.logger.log(LogStatus.INFO, "Control is switched to pop up window");
	    	  }
		 }
		 return parentWindow;
	}

	public void selectDropdownByValue(WebElement element, String DropDownOption, String label) {

		try {
			ExtentReporter.logger.log(LogStatus.INFO, "Selecting the value from " + label + " drop down");
			Select Sel = new Select(element);
			Sel.selectByValue(DropDownOption);
			ExtentReporter.logger.log(LogStatus.PASS, "Value is selected from " + label + " drop down");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Value is not selected from" + label + "drop down");
		}

	}
	
	public void selectDropdownByVisibleText(WebElement element, String DropDownOption, String label) {

		try {
			ExtentReporter.logger.log(LogStatus.INFO, "Selecting the value from " + label + " drop down");
			Select Sel = new Select(element);
			Sel.selectByVisibleText(DropDownOption);
			ExtentReporter.logger.log(LogStatus.PASS, "Value is selected from " + label + " drop down");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Value is not selected from" + label + "drop down");
		}

	}
	

	public void excelRead() throws Exception {
		ExcelUtil exlutil = new ExcelUtil();
	}

	public void verifyTextPresent(String getTextPolicyPhase, String actualText, String fieldName) {
		try {

			Assert.assertEquals(getTextPolicyPhase, actualText, getTextPolicyPhase + " element is present");
			ExtentReporter.logger.log(LogStatus.PASS, getTextPolicyPhase + " element is present");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, getTextPolicyPhase + " element is NOT present");
		}
	}

	public void enterTextIn(WebElement pageElement, String text) {
		try {

			pageElement.sendKeys(text);
			ExtentReporter.logger.log(LogStatus.PASS, text+" is entered in "+pageElement+"text box");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL,
					"Could not enter text " + text + " in" + pageElement + " text box");
		}
	}

	//This method is called to load a page during navigation through pages
	
	public void waitForPageLoad(WebDriver driver, int Timeout) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>()
		{ public Boolean apply(WebDriver driver)
			{
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(pageLoadCondition);
			
	}

	public void waitForElementToLoad(WebDriver driver, int time){
		
		WebDriverWait wait=new WebDriverWait(driver, time);
		
	}

	public void clickButton(WebElement pageElement) {
		// TODO Auto-generated method stub
		
	}

	public void waitFor(long ms) {
		// TODO Auto-generated method stub
		
	}
}

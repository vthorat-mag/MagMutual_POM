package com.mm.utils;

import java.awt.AWTException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import BaseClass.CommonActionInterface;

public class CommonAction implements CommonActionInterface {
	
	
	Properties pro=new Properties();

	//Integer.valueOf(properties.prop.getProperty("High"));
	
	int Low=10;
	int Medium=25;
	int High=50;

	
	public void selectValue(WebDriver driver, WebElement pageElement, String value) {
		// TODO Auto-generated method stub
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Assert.assertTrue(pageElement.isDisplayed(),  pageElement+ " is Not displayed on screen.");
			js.executeScript("arguments[0].click();", pageElement);
			ExtentReporter.logger.log(LogStatus.PASS, "Selected value " + value);
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, value + " element is not found.");
		}
		
		
	}
	
	public void switchToSecondFramefromFirst(WebDriver driver,String frameID){
		
		List<WebElement> secondFrame = driver.findElements(By.id(frameID));
     	driver.switchTo().frame(secondFrame.get(0));
		
	}
	
	public void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow) {

		try {
			driver.switchTo().window(parentwindow);
			ExtentReporter.logger.log(LogStatus.PASS, "Control switched back to parent window.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to parent window.");
		}
	}

	public void switchToFrameUsingId(WebDriver driver, String frameName) {

		try {
			driver.switchTo().frame(frameName);
			ExtentReporter.logger.log(LogStatus.PASS, "Control switched Switched to frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to frame.");
		}

	}
	
	public void switchToFrameUsingElement(WebDriver driver, WebElement element) throws Exception
	{
		waitFor(driver, 15);
		Thread.sleep(5000);
		try {
			driver.switchTo().frame(element);
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

	//Enter text values in the text field
	public void enterTextIn(WebDriver driver, WebElement pageElement, String text, String textField) {
		// TODO Auto-generated method stub
		try {
			WebDriverWait wait = new WebDriverWait(driver,High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
			pageElement.sendKeys(text);
			ExtentReporter.logger.log(LogStatus.PASS, "Value entered in text box -" + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

	
	//Enter the data values which are not text, like date and amount.
	public void enterDataIn(WebDriver driver, WebElement pageElement,String text, String textField) {
		// TODO Auto-generated method stub
		try {
			WebDriverWait wait = new WebDriverWait(driver,Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
			pageElement.sendKeys(text);
		//	driver.executeScript("arguments[0].setAttribute(value, arguments[1]);", pageElement, text);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value=text;", pageElement);
			
			
			ExtentReporter.logger.log(LogStatus.PASS, "Value entered in text box -" + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}
	
	
	public void clickButton(WebDriver driver, WebElement pageElement, String buttonName) {
		// TODO Auto-generated method stub
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Assert.assertTrue(pageElement.isDisplayed(), buttonName + " button is displayed on screen.");
			js.executeScript("arguments[0].click();", pageElement);
			ExtentReporter.logger.log(LogStatus.PASS, "clicked on button - " + buttonName);
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, buttonName + " element is not found.");
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

	public String getPageTitle(WebDriver driver, String expectedPageTitle ) throws InterruptedException {
		Thread.sleep(5000);
		String getPageTitleFromPage = driver.findElement(By.xpath("//div[@class='pageTitle']")).getAttribute("innerHTML").trim();
		WebDriverWait wait = new WebDriverWait(driver,High);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='pageTitle']"))));
		Assert.assertEquals(getPageTitleFromPage ,expectedPageTitle, "Page title is not matching.");
		return null;
	}

	public String getText(WebDriver driver, WebElement pageElement) {
		WebDriverWait wait = new WebDriverWait(driver,High);
		wait.until(ExpectedConditions.visibilityOf(pageElement));
		return pageElement.getAttribute("innerHTML");
	}

	public void clearTextBox(WebDriver driver, WebElement pageElement, String textField) {
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
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

	public void click(WebDriver driver, WebElement pageElement, String ElementName) {

		try {
			WebDriverWait wait = new WebDriverWait(driver,High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), ElementName + " is not displayed on screen.");
			pageElement.click();
			ExtentReporter.logger.log(LogStatus.PASS, "clicked on Element - " + ElementName);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, ElementName + " element is not found on page.");
		}

	}

	public void visibilityOfElement(WebDriver driver, WebElement pageElement, String text) {

		try {
			WebDriverWait wait = new WebDriverWait(driver,High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), "Logo / text" + text + " is not displayed on the page.");
			ExtentReporter.logger.log(LogStatus.PASS, "Logo / text" + text + " is displayed on page after login");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, text + " is not displayed on page after login");
		}
	}

	
	public String switchToWindow(WebDriver driver) throws InterruptedException {

		ExtentReporter.logger.log(LogStatus.INFO, "Switching to the pop up window");
		
		Set<String> handles = driver.getWindowHandles();      //Return a set of window handle
		 
	    String parentWindow = driver.getWindowHandle();
	   
	    Thread.sleep(5000);
		 for(String currentWindow : handles){
	    	   if(!currentWindow.equals(parentWindow))
	    	  {
	    	   driver.switchTo().window(currentWindow);
	    	   ExtentReporter.logger.log(LogStatus.INFO, "Control is switched to pop up window");
	    	  }
		 }
		 Thread.sleep(5000);
		 return parentWindow;
	}

	public void selectDropdownByValue(WebDriver driver, WebElement element, String DropDownOption, String label) {

		try {
			WebDriverWait wait = new WebDriverWait(driver,High);
			wait.until(ExpectedConditions.visibilityOf(element));
			Select Sel = new Select(element);
			Sel.selectByValue(DropDownOption);
			ExtentReporter.logger.log(LogStatus.PASS, "Value is selected from " + label + " drop down");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Value is not selected from" + label + "drop down");
		}

	}
	
	public void selectDropdownByVisibleText(WebDriver driver, WebElement element, String DropDownOption, String label) {

		try {
			WebDriverWait wait = new WebDriverWait(driver,High);
			wait.until(ExpectedConditions.visibilityOf(element));
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

	public void verifyTextPresent(String getText, String actualText, String fieldName) {
		try {
			Assert.assertEquals(getText, actualText, getText + " element is present");
			ExtentReporter.logger.log(LogStatus.PASS, getText + " element is present");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, getText + " element is NOT present");
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

	public void waitForElementToLoad(WebDriver driver, int time, WebElement element){
		
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void policySearch(WebDriver driver, String policyNo, WebElement policySearchTxtBox, WebElement searchBtn) 
	{
		clearTextBox(driver, policySearchTxtBox, "Enter Policy text field");
		ExtentReporter.logger.log(LogStatus.INFO, "Click policy in right corner of screen");
		enterTextIn(driver, policySearchTxtBox, policyNo, "Enter Policy text field");
		click(driver, searchBtn, "Search button");
		ExtentReporter.logger.log(LogStatus.INFO, "Enter Policy # into Policy entry box, Click Search.");
	}
}

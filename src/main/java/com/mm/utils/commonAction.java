package com.mm.utils;

import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import BaseClass.CommonActionInterface;

public class commonAction implements CommonActionInterface {



	public void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow)
	{
		
		driver.switchTo().window(parentwindow);
	}
	
	public void switchToFrame(WebDriver driver, String frameName)
	{
		
		driver.switchTo().frame(frameName);
	}
	public void switchToParentWindowfromframe(WebDriver driver)
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Switching control back to main window");
		driver.switchTo().defaultContent();
	}
	
	public String randomNoGenerator()
	{
		return RandomStringUtils.random(2,"1234567890");
	}

	public void navigateTo(String url) {
		// TODO Auto-generated method stub
	
		ExtentReporter.logger.log(LogStatus.INFO, "Navigating to eOasis web application");
		
	}

	public void enterTextIn(WebElement pageElement, String text) {
		// TODO Auto-generated method stub
		pageElement.sendKeys(text);
		
		ExtentReporter.logger.log(LogStatus.INFO, "Entered text in text box "+text);
		
	}

	public void clickButton(WebElement pageElement, String buttonName) {
		// TODO Auto-generated method stub
		pageElement.click();
		
		ExtentReporter.logger.log(LogStatus.INFO, "Clicked on the "+buttonName);
		
		
	}

	public void waitFor(long ms) {
		// TODO Auto-generated method stub
	}

	public void close(WebDriver driver) {
		// TODO Auto-generated method stub
		driver.quit();
		
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
		pageElement.clear();
		ExtentReporter.logger.log(LogStatus.INFO, "Cleared the initial contents from "+textField);
		
	}

	public String getAttributeValue(WebElement pageElement, String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void click(WebElement pageElement, String ElementName) {
		// TODO Auto-generated method stub
		pageElement.click();
		
		ExtentReporter.logger.log(LogStatus.INFO, "Clicked on the "+ ElementName);
		
	}

	public void clear(WebElement pageElement) {
		// TODO Auto-generated method stub
		
	}

	public void visibilityOfElement(WebElement pageElement) {
		// TODO Auto-generated method stub
		
		
		
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

	public void selectDropdown(WebElement element, String DropDownOption, String label) {
		
		ExtentReporter.logger.log(LogStatus.INFO, "Selecting the value from "+label+ " drop down");
		Select Sel = new Select(element);
		Sel.selectByValue(DropDownOption);
		ExtentReporter.logger.log(LogStatus.INFO, "Value is selected from "+label+" drop down");
	}
	
	public void excelRead() throws Exception
	{
		ExcelUtil exlutil = new ExcelUtil();
	}

	public void enterTextIn(WebElement pageElement, String text, String field) {
		// TODO Auto-generated method stub
		
	}


}

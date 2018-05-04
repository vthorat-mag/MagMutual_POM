package com.mm.utils;

import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
		driver.switchTo().defaultContent();
	}
	
	public String randomNoGenerator()
	{
		return RandomStringUtils.random(2,"1234567890");
	}

	public void navigateTo(String url) {
		// TODO Auto-generated method stub
		
	}

	public void enterTextIn(WebElement pageElement, String text) {
		// TODO Auto-generated method stub
		pageElement.sendKeys(text);
		
	}

	public void clickButton(WebElement pageElement) {
		// TODO Auto-generated method stub
		pageElement.click();
		
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

	public void clearTextBox(WebElement pageElement) {
		// TODO Auto-generated method stub
		pageElement.clear();
		
	}

	public String getAttributeValue(WebElement pageElement, String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void click(WebElement pageElement) {
		// TODO Auto-generated method stub
		pageElement.click();
		
	}

	public void clear(WebElement pageElement) {
		// TODO Auto-generated method stub
		
	}

	public void visibilityOfElement(WebElement pageElement) {
		// TODO Auto-generated method stub
		
	}

	public String switchToWindow(WebDriver driver) {
		Set<String> handles = driver.getWindowHandles();      //Return a set of window handle
		 
	    String parentWindow = driver.getWindowHandle();
	   
		 for(String currentWindow : handles){
	    	   if(!currentWindow.equals(parentWindow))
	    	  {
	    	   driver.switchTo().window(currentWindow);
	    	  }
		 }
		 return parentWindow;
	}

	public void selectDropdown(WebElement element, String DropDownOption) {
		
		Select Sel = new Select(element);
		Sel.selectByValue(DropDownOption);
	}
	
	public void excelRead() throws Exception
	{
		ExcelUtil exlutil = new ExcelUtil();
	}


}

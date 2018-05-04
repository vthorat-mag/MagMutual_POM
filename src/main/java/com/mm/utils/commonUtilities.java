package com.mm.utils;

import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import BaseClass.CommonActionInterface;

public class commonUtilities {
	
	public void selectDropdown(WebElement Element, String value){
		
		Select Sel = new Select(Element);
		
		Sel.selectByValue(value);
		
		}
	
	public String switchToWindow(WebDriver driver)
	{
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
	
}

 
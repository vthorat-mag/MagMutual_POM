package com.mm.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class commonUtilities {
	
	public void selectDropdown(WebElement Element, String value){
		
		Select Sel = new Select(Element);
		
		Sel.selectByValue(value);
		
		}
	
}

 
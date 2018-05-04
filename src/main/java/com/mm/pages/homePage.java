package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {

	
	WebDriver driver;
	
	@FindBy(id="CIS")
	WebElement cisTab;
	
	@FindBy(xpath="//li[@id='Policy']//a[@class='fNiv']")
	WebElement Policy_tab;
	
	@FindBy(name="logoff")
	WebElement logoff;
	
	public homePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToCISPage()
	{
		cisTab.click();
	}
	
	public void navigateToPolicyPage()
	{
		Policy_tab.click();
	}
	
	public void logoutFromeOasis()
	{
		logoff.click();
	}
}

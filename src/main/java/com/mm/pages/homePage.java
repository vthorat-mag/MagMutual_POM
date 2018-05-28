package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.utils.commonAction;
import com.relevantcodes.extentreports.LogStatus;

public class homePage extends commonAction{

	
	WebDriver driver;
	
	@FindBy(id="CIS")
	WebElement cisTab;
	
	@FindBy(xpath="//li[@id='Policy']//a[@class='fNiv']")
	WebElement Policy_tab;
	
	@FindBy(name="logoff")
	WebElement logoff;
	
	@FindBy(id="headerLogoTips")
	WebElement logo;
	
	@FindBy(id="topnav_Policy")
	WebElement Policy_link;
	
	public homePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyLogoIsAvailable(){
		
		visibilityOfElement(logo, "DELPHI TECHNOLOGY");
		
		
	}
	
	public void navigateToCISPage()
	{
		click(cisTab,"CIS tab");
	}
	
	public void navigateToPolicyPage()
	{
	//	Policy_link.isSelected();
		
		click(Policy_tab, "Policy tab");
		
		ExtentReporter.logger.log(LogStatus.INFO, "Search Policy Screen is opened");
	}
		
	public void logoutFromeOasis()
	{
		click(logoff,"Logoff button");
		ExtentReporter.logger.log(LogStatus.INFO, "User is logged out from application");
	}
}

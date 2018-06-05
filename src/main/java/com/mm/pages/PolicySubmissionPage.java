package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.ExtentReporter;
import com.mm.utils.CommonAction;
import com.relevantcodes.extentreports.LogStatus;

public class PolicySubmissionPage extends CommonAction {
	
	//Global Assignment/initialization of variables.
	WebDriver driver;
	String indicationPhaseValue="INDICATION";
	String valueOfPolicyActionCopy = "javascript:copyQuote();";
	
	//Element repository for Policy Submission page.
	@FindBy(id="PM_COMMON_TABS_SAVEWIP")
	WebElement saveWIP;
	
	@FindBy(name="policyPhaseCode")
	WebElement policyPhase;
	
	@FindBy(xpath="//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
	WebElement policyAction;
	
	//Constructor to initialize elements on policy submission page.
	public PolicySubmissionPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Select Copy from Action value from Action drop down.
	public void copyFromActionDropDown(String policyNum) throws InterruptedException
	{
		selectDropdownByValue(driver,policyAction, valueOfPolicyActionCopy, "Policy Action");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy");
		Thread.sleep(3000);
	}
	
	
	//Change policy phase to indication.
	public void changePhaseToIndication() throws InterruptedException
	{
		selectDropdownByValue(driver,policyPhase, indicationPhaseValue, "Phase");
		ExtentReporter.logger.log(LogStatus.INFO, "Change Policy Phase to Indication");
		Thread.sleep(3000);
	}
	
	//Save policy / Quote as Work in progress.
	public void saveWip() throws InterruptedException
	{
		clickButton(driver, saveWIP, "Save WIP");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save WIP");
		Thread.sleep(2000);
	}

}

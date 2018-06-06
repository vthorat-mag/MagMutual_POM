package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.CommonAction;
import com.mm.utils.ExtentReporter;
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
	
	@FindBy(name="policyPhaseCode")
	WebElement Phase;
	
	@FindBy(name="organizationTypeCode")
	WebElement Org_Type;
	
	@FindBy(name="discoveryPeriodRating")
	WebElement Hosp_Disc_Period_Rating;
	
	@FindBy(name="termDesc")
	WebElement Quote_Description;
	
	@FindBy(id="PM_COMMON_TABS_SAVEWIP")
	WebElement Save_WIP;
	
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
	
	// Update policy details for a policy and change policy phase from Submission to Indication.
	public void updatePolicyDetails() throws InterruptedException{
		
		waitForElementToLoad(driver, 30, Phase);
		selectDropdownByValue(driver, Phase, "INDICATION", "Phase");
		selectDropdownByValue(driver, Org_Type, "HOSPITAL", "Organisation Type");
		enterTextIn(driver, Hosp_Disc_Period_Rating, "2");
		enterTextIn(driver, Quote_Description, "Automated Test");
		click(driver, Save_WIP, "Save WIP button");
		ExtentReporter.logger.log(LogStatus.INFO, "Indication saved as WIP");
	}


}

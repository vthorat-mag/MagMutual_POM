package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.ExtentReporter;
import com.mm.utils.commonAction;
import com.relevantcodes.extentreports.LogStatus;

public class Policy_Submission_Page extends commonAction {
	
	WebDriver driver;
	
	String indicationPhaseValue="INDICATION";
	String valueOfPolicyActionCopy = "javascript:copyQuote();";
	
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
	
	
	
	public Policy_Submission_Page(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void copyFromActionDropDown(String policyNum) throws InterruptedException
	{
		selectDropdownByValue(policyAction, valueOfPolicyActionCopy, "Policy Action");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy");
		Thread.sleep(3000);
	}
	
	public void changePhaseToIndication() throws InterruptedException
	{
		selectDropdownByValue(policyPhase, indicationPhaseValue, "Phase");
		ExtentReporter.logger.log(LogStatus.INFO, "Change Policy Phase to Indication");
		Thread.sleep(3000);
	}
	
	public void saveWip() throws InterruptedException
	{
		clickButton(driver, saveWIP, "Save WIP");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save WIP");
		Thread.sleep(2000);
	}
	
	// Update policy details for a policy and change policy phase from Submission to Indication.
	public void updatePolicyDetails() throws InterruptedException{
		
		waitForElementToLoad(driver, 30, Phase);
		selectDropdownByValue(Phase, "INDICATION", "Phase");
		selectDropdownByValue(Org_Type, "HOSPITAL", "Organisation Type");
		enterTextIn(Hosp_Disc_Period_Rating, "2");
		enterTextIn(Quote_Description, "Automated Test");
		click(Save_WIP, "Save WIP button");
		ExtentReporter.logger.log(LogStatus.INFO, "Indication saved as WIP");
}
	
	
	
	

}

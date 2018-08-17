package com.mm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mm.dto.PolicySubmissionPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

public class PolicySubmissionPage extends CommonAction {
	
	// Global Assignment/initialization of variables.
	WebDriver driver;
	String indicationPhaseValue = "INDICATION";
	String valueOfPolicyActionCopy = "javascript:copyQuote();";
	PolicyIndicationPage policyIndicationPage;
	PolicySubmissionPageDTO policysubmissionpageDTO;

	// Element repository for Policy Submission page.
	@FindBy(id = "PM_COMMON_TABS_SAVEWIP")
	WebElement saveWIP;

	@FindBy(name = "policyPhaseCode")
	WebElement policyPhase;

	@FindBy(xpath = "//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
	WebElement policyAction;

	@FindBy(name = "policyPhaseCode")
	WebElement Phase;

	@FindBy(name = "organizationTypeCode")
	WebElement Org_Type;

	@FindBy(name = "discoveryPeriodRating")
	WebElement Hosp_Disc_Period_Rating;

	@FindBy(name = "termDesc")
	WebElement Quote_Description;

	@FindBy(id = "PM_COMMON_TABS_SAVEWIP")
	WebElement Save_WIP;

	// Constructor to initialize driver, page elements and DTO PageObject for
	// PolicySubmissionPage
	public PolicySubmissionPage(WebDriver driver)
			throws IllegalArgumentException, IllegalAccessException, SecurityException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		policysubmissionpageDTO = new PolicySubmissionPageDTO(TestCaseDetails.testDataDictionary);
	}

	// Select Copy from Action value from Action drop down.
	public PolicySubmissionPage copyFromPolicyActionDropDown(String policyNum)
			throws IllegalArgumentException, IllegalAccessException, SecurityException, InterruptedException {
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy. Verify Phase is editable.");
		selectDropdownByValue(driver,policyAction, policysubmissionpageDTO.valueOfPolicyActionCopy, "Policy Action");
		Thread.sleep(3000);
		return new PolicySubmissionPage(driver);

	}

	// Change policy phase to indication.
	public PolicySubmissionPage changePhaseToIndicationAndAddQuoteDescription()
			throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		Thread.sleep(6000);
		ExtentReporter.logger.log(LogStatus.INFO, "Change Policy Phase to Indication. Verify Policy is changed from Submission to Indication");
		selectDropdownByValue(driver,policyPhase, policysubmissionpageDTO.indicationPhaseValue, "Phase");
		ExtentReporter.logger.log(LogStatus.INFO, "Enter "+policysubmissionpageDTO.quoteDescription+" in the Quote Description. Verify "+policysubmissionpageDTO.quoteDescription+" is entered in Quote Description.");
		enterTextIn(driver, Quote_Description, policysubmissionpageDTO.quoteDescription, "Quote Description");
		Thread.sleep(1000);
		return new PolicySubmissionPage(driver);
	}
	
	//Save policy / Quote as Work in progress.
	public PolicyQuotePage saveWip() throws Exception
	{
		clickButton(driver, saveWIP, "Save WIP");
		Thread.sleep(2000);
		return new PolicyQuotePage(driver);
	}
	
	
	// Update policy details for a policy and change policy phase from
	// Submission to Indication.
	public PolicyIndicationPage updatePolicyDetails() throws Exception {
		Thread.sleep(5000);
		invisibilityOfLoader(driver);
		//getPageTitle(driver, expectedPageTitle)
		//Change policy type to Indication and add organization type as Hospital from DDL
		ExtentReporter.logger.log(LogStatus.INFO, "In Policy Detail tab,Select/Enter the below information:"
				+ " Phase: Indication, Organization Type: Hospital,Hospital Discovery Period Rating %: 2 "+
				"Quote Description: Automated Test. Click Save WIP Button. Verify Indication saved as WIP");
		selectDropdownByValue(driver, Phase, policysubmissionpageDTO.policyPhase, "Phase");
		selectDropdownByValue(driver, Org_Type, policysubmissionpageDTO.organisationType, "Organisation Type");
		Thread.sleep(2000);
		//Add Discovery period rating, Quote Description and save as WIP
		enterTextIn(driver, Hosp_Disc_Period_Rating,policysubmissionpageDTO.discoveryPeriodRating, "Discovery_Period Rating");
		enterTextIn(driver, Quote_Description, policysubmissionpageDTO.quoteDescription, "Quote Description");
		click(driver, Save_WIP, "Save WIP button");
		return new PolicyIndicationPage(driver);
	}
}
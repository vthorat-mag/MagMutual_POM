package com.mm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.utils.CommonAction;
import com.relevantcodes.extentreports.LogStatus;

public class PolicyBinderPage extends CommonAction {

	// Global Variable assignment.
	WebDriver driver;
	String valueOfPolicyActionEndorse = "javascript:endorseTransaction('oosendorse');";
	String saveAsPolicyValue = "OFFICIAL";
	String ProductNotifyValue = "Y";
	String valueOfSelectReason = "END009";
	String valueOfPolicyActionCopyToQuote = "javascript:copyToQuote();";

	// Element repository for the page Policy Binder page.
	@FindBy(name = "globalSearch")
	WebElement Policy_Search;

	@FindBy(name = "search")
	WebElement Search_btn;

	@FindBy(id = "pageTitleForpageHeaderForPolicyFolder")
	WebElement pageHeaderForPolicyFolder;

	@FindBy(xpath = "//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
	WebElement policyAction;

	@FindBy(name = "endorsementCode")
	WebElement selectReason;

	@FindBy(id = "PM_ENDORSE_OK")
	WebElement okBtnEndorsmentPopup;

	@FindBy(xpath = "//table[@id='formFieldsTableForHeaderFieldsSecond']//span[@id='polPhaseCodeROSPAN']")
	WebElement policyPhasePolicy;

	@FindBy(id = "PM_COMMON_TABS_RATE")
	WebElement rateBtn;

	@FindBy(id = "PM_VIEW_PREM_CLOSE")
	WebElement closeBtnOnViewPremiumPopup;

	@FindBy(name = "workflowExit_Ok")
	WebElement okPolicySaveAsWIPPopup;

	@FindBy(id = "PM_COMMON_TABS_SAVE")
	WebElement saveOptionBtn;

	@FindBy(xpath = "//select[@name='saveAsCode']")
	WebElement saveAsDropDown;

	@FindBy(id = "PM_SAVE_OPTION_OK")
	WebElement saveOptionOkBtn;

	@FindBy(name = "workflowExit_Ok")
	WebElement Exit_Ok;

	@FindBy(xpath = "//select[contains(@name,'confirmed')]")
	WebElement productNotifyDropDown;

	@FindBy(id = "PM_NOTIFY_CLOSE")
	WebElement prodNotifyClose;

	@FindBy(id = "policyHolderNameROSPAN1")
	WebElement policyHolderNameLink;

	@FindBy(xpath = "//iframe[@contains(id,'popupframe')]")
	WebElement entityMiniPopupFrameId;

	@FindBy(id = "entity_clientIDROSPAN")
	WebElement clientId;

	@FindBy(id = "CI_ENTITY_MINI_POP_CLS")
	WebElement entityMiniPopupCloseBtn;

	// Constructor to initialize variables on policy binder page.
	public PolicyBinderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// get Client Id from Entity menu popup flow.
	public void getClientId() {
		clickButton(driver, policyHolderNameLink, "Policy Holder Name");
		switchToFrameUsingElement(driver, entityMiniPopupFrameId);
		getPageTitle(driver, "Entity Mini Popup");
		String getClientIdValue = clientId.getAttribute("innerHTML");
		clickButton(driver, entityMiniPopupCloseBtn, "Entity Mini Popup Close");
		switchToParentWindowfromframe(driver);
	}

	// Identify Policy number from Page.
	public String policyNo() {
		String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
		String[] portfolioNo = profileNoLable.split(" ", 3);
		return portfolioNo[2];
	}

	// Select Endorsement from "Action DropoDown".
	public void endorsementFromActionDropDown() {
		selectDropdownByValue(driver, policyAction, valueOfPolicyActionEndorse, "Policy Action");
		ExtentReporter.logger.log(LogStatus.PASS, "Click Policy Actions > Select value from the dropdown screen.");
	}

	// Select Copy To Quote from "Action DropoDown".
	public void copyToQuoteFromActionDropDown(String policyNum) throws InterruptedException {
		selectDropdownByValue(driver, policyAction, valueOfPolicyActionCopyToQuote, "Policy Action");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy to Quote");
		Thread.sleep(10000);
		String getUpdatedPolicyNo = policyNo();
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + getUpdatedPolicyNo + "')]")));
		click(driver, Exit_Ok, "OK button");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
	}

	// Endorse Policy Flow.
	public void endorsPolicy(String policyNum) throws InterruptedException {
		Thread.sleep(3000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNum + "')]")));
		waitForElementToLoad(driver, 25, selectReason);
		selectDropdownByValue(driver, selectReason, valueOfSelectReason, "Select Reason");
		clickButton(driver, okBtnEndorsmentPopup, "Ok");
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click the dropdown by Reason:  Select Issue Policy Forms-->Click [Ok]");
		Thread.sleep(4000);
	}

	// Identify Phase from page.
	public void identifyPhase() throws InterruptedException {
		waitFor(driver, 5);
		String getTextPolicyPhase = policyPhasePolicy.getAttribute("innerText");
		verifyTextPresent(getTextPolicyPhase, "Policy", "Policy Phase");
		ExtentReporter.logger.log(LogStatus.INFO, "Verify phase is " + getTextPolicyPhase);
	}

	// Rate a Functionality flow.
	public void rateFunctionality(String policyNo) throws InterruptedException {
		Thread.sleep(3000);
		clickButton(driver, rateBtn, "Rate Tab");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Rate]");
		Thread.sleep(4000);
		/*
		 * try{ switchToFrameUsingElement(driver,
		 * driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+
		 * policyNo+"')]"))); selectDropdownByValue(productNotifyDropDown,
		 * ProductNotifyValue, "product notify"); Thread.sleep(1000);
		 * clickButton(driver, prodNotifyClose, "Product Notify Close");
		 * ExtentReporter.logger.log(LogStatus.INFO,
		 * "Product Notify Window dispalyed to user.");
		 * ExtentReporter.logger.log(LogStatus.PASS,
		 * " Yes selection from Product Notify dorp down."); }catch (Exception
		 * e) { ExtentReporter.logger.log(LogStatus.FAIL,
		 * "Product Notify Window is NOT dispalyed to user."); }
		 */
		Thread.sleep(3000);
		// switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		// switchToFrameUsingId(driver,"popupframe1");
		Thread.sleep(2000);
		clickButton(driver, closeBtnOnViewPremiumPopup, "Close");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Close] click [Ok]");
		switchToParentWindowfromframe(driver);
	}

	// Save Option functionality flow.
	public void saveOption(String policyNo) throws InterruptedException {
		Thread.sleep(2000);
		clickButton(driver, saveOptionBtn, "Save Option");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		selectDropdownByValue(driver, saveAsDropDown, saveAsPolicyValue, "Save Option");
		clickButton(driver, saveOptionOkBtn, "Save");
		ExtentReporter.logger.log(LogStatus.INFO, "Select Official Click [OK]");
		Thread.sleep(6000);
		/*
		 * try{ switchToParentWindowfromframe(driver);
		 * switchToFrameUsingElement(driver,
		 * driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+
		 * policyNo+"')]"))); selectDropdownByValue(productNotifyDropDown,
		 * ProductNotifyValue, "product notify"); Thread.sleep(1000);
		 * clickButton(driver, prodNotifyClose, "Product Notify Close");
		 * ExtentReporter.logger.log(LogStatus.INFO,
		 * "Product Notify Window dispalyed to user.");
		 * ExtentReporter.logger.log(LogStatus.PASS,
		 * " Yes selection from Product Notify dorp down."); }catch (Exception
		 * e) { ExtentReporter.logger.log(LogStatus.FAIL,
		 * "Product Notify Window is NOT dispalyed to user."); }
		 */
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		Thread.sleep(4000);
		clickButton(driver, Exit_Ok, "Exit Ok");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
	}

}

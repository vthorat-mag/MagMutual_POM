package com.mm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.utils.commonAction;
import com.relevantcodes.extentreports.LogStatus;

public class Policy_Binder_Page extends commonAction {
	
	WebDriver driver;
	
	String valueOfPolicyActionEndorse = "javascript:endorseTransaction('oosendorse');";
	String saveAsPolicyValue="OFFICIAL";
	String ProductNotifyValue="Y";
	String valueOfSelectReason = "END009";
	String valueOfPolicyActionCopyToQuote = "javascript:copyToQuote();";
	
	@FindBy(name="globalSearch")
	WebElement Policy_Search;

	@FindBy(name="search")
	WebElement Search_btn;
	
	@FindBy(id="pageTitleForpageHeaderForPolicyFolder")
	WebElement pageHeaderForPolicyFolder;
	
	@FindBy(xpath="//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
	WebElement policyAction;
	
	@FindBy(name="endorsementCode")
	WebElement selectReason;
	
	@FindBy(id="PM_ENDORSE_OK")
	WebElement okBtnEndorsmentPopup;
	
	@FindBy(xpath="//table[@id='formFieldsTableForHeaderFieldsSecond']//span[@id='polPhaseCodeROSPAN']")
	WebElement policyPhasePolicy;
	
	@FindBy(id="PM_COMMON_TABS_RATE")
	WebElement rateBtn;
	
	@FindBy(id="PM_VIEW_PREM_CLOSE")
	WebElement closeBtnOnViewPremiumPopup;
	
	@FindBy(name="workflowExit_Ok")
	WebElement okPolicySaveAsWIPPopup;
	
	@FindBy(id="PM_COMMON_TABS_SAVE")
	WebElement saveOptionBtn;
	
	@FindBy(xpath="//select[@name='saveAsCode']")
	WebElement saveAsDropDown;
	
	@FindBy(id="PM_SAVE_OPTION_OK")
	WebElement saveOptionOkBtn;
	
	@FindBy(name="workflowExit_Ok")
	WebElement Exit_Ok;
	
	@FindBy(xpath="//select[contains(@name,'confirmed')]")
	WebElement productNotifyDropDown;
	
	@FindBy(id="PM_NOTIFY_CLOSE")
	WebElement prodNotifyClose;
	
	public Policy_Binder_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//String policy_no="09101526";
	
	public void searchPolicy(String policy_no) throws InterruptedException
	{
		Thread.sleep(3000);
		policySearch(policy_no,Policy_Search, Search_btn);
		String actual=getText(pageHeaderForPolicyFolder);
		Assert.assertEquals(actual, "Policy Folder "+policy_no, "The policy "+policy_no+" is Not available.");
		Thread.sleep(3000);
	}
	
	public String policyNo()
	{
		String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
		String[] portfolioNo = profileNoLable.split(" ",3);
		return portfolioNo[2];
	}
	
	public void endorsementFromActionDropDown()
	{
		selectDropdownByValue(policyAction, valueOfPolicyActionEndorse, "Policy Action");
		ExtentReporter.logger.log(LogStatus.PASS, "Click Policy Actions > Select value from the dropdown screen.");
		
	}
	
	public void copyToQuoteFromActionDropDown(String policyNum) throws InterruptedException
	{
		selectDropdownByValue(policyAction, valueOfPolicyActionCopyToQuote, "Policy Action");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy to Quote");
		Thread.sleep(10000);
		String getUpdatedPolicyNo = policyNo();
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+getUpdatedPolicyNo+"')]")));
		click(Exit_Ok, "OK button");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
		Thread.sleep(2000);
   	 	switchToParentWindowfromframe(driver);
	}
	
	public void endorsPolicy(String policyNum) throws InterruptedException
	{
		Thread.sleep(3000);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNum+"')]")));
		waitForElementToLoad(driver, 25, selectReason);
		selectDropdownByValue(selectReason, valueOfSelectReason, "Select Reason");
		clickButton(driver, okBtnEndorsmentPopup, "Ok");
		ExtentReporter.logger.log(LogStatus.INFO, "Click the dropdown by Reason:  Select Issue Policy Forms-->Click [Ok]");
		Thread.sleep(4000);
	}
	
	public void identifyPhase() throws InterruptedException
	{
		waitFor(driver, 5);
		String getTextPolicyPhase = policyPhasePolicy.getAttribute("innerText");
		verifyTextPresent(getTextPolicyPhase,"Policy","Policy Phase");
		ExtentReporter.logger.log(LogStatus.INFO, "Verify phase is "+getTextPolicyPhase);
	}
	
	public void rateFunctionality(String policyNo) throws InterruptedException
	{
		Thread.sleep(3000);
		clickButton(driver, rateBtn, "Rate Tab");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Rate]");
		Thread.sleep(4000);
		/*try{
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
			selectDropdownByValue(productNotifyDropDown, ProductNotifyValue, "product notify");
			Thread.sleep(1000);
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window dispalyed to user.");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selection from Product Notify dorp down.");
		}catch (Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Product Notify Window is NOT dispalyed to user.");
		}*/
		Thread.sleep(3000);
		//switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		//switchToFrameUsingId(driver,"popupframe1");
		Thread.sleep(2000);
		clickButton(driver, closeBtnOnViewPremiumPopup, "Close");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Close] click [Ok]");
		switchToParentWindowfromframe(driver);
	}
	
	public void saveOption(String policyNo) throws InterruptedException
	{
		Thread.sleep(2000);
		clickButton(driver, saveOptionBtn, "Save Option");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		selectDropdownByValue(saveAsDropDown, saveAsPolicyValue, "Save Option");
		clickButton(driver, saveOptionOkBtn, "Save");
		ExtentReporter.logger.log(LogStatus.INFO, "Select Official Click [OK]");
		Thread.sleep(6000);
		/*try{
			switchToParentWindowfromframe(driver);
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
			selectDropdownByValue(productNotifyDropDown, ProductNotifyValue, "product notify");
			Thread.sleep(1000);
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window dispalyed to user.");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selection from Product Notify dorp down.");
		}catch (Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Product Notify Window is NOT dispalyed to user.");
		}*/
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		Thread.sleep(4000);
		clickButton(driver, Exit_Ok, "Exit Ok");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
	}

}

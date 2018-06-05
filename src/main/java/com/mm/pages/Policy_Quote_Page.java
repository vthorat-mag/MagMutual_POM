package com.mm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.utils.commonAction;
import com.relevantcodes.extentreports.LogStatus;

public class Policy_Quote_Page extends commonAction {
	
	WebDriver driver;
	
	String valueOfPolicyActionCopy = "javascript:copyQuote();";
	String saveAsPolicyValue="OFFICIAL";
	String QuotePhaseValue="QUOTE";
	String ProductNotifyValue="Y";
	
	
	
	@FindBy(name="globalSearch")
	WebElement Policy_Search;

	@FindBy(name="search")
	WebElement Search_btn;
	
	@FindBy(id="pageTitleForpageHeaderForPolicyFolder")
	WebElement pageHeaderForPolicyFolder;
	
	@FindBy(xpath="//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
	WebElement policyAction;
	
	@FindBy(id="PM_CPT_TRAN_OK")
	WebElement capt_Tranx_Ok;
	
	@FindBy(xpath="//a[@id='PM_PT_VIEWCVG']//span[@class='tabWithNoDropDownImage']")
	WebElement coverageTab;
	
	@FindBy(xpath = "//table[@id='coverageListGrid']//tbody//td//div[@id='CPRODUCTCOVERAGEDESC']")
	List<WebElement> coverageList;
	
	@FindBy(xpath = "//input[@id='PM_QT_MANU_PUP']")
	WebElement optionalFormBtn;
	
	@FindBy(xpath = "//table[@id='maintainManuscriptListGrid']")
	WebElement 	manuscriptList;
	
	@FindBy(id="PM_MANU_DELETE")
	WebElement manuscriptPageDeleteBtn;
	
	@FindBy(id="PM_MANU_ADD")
	WebElement manuscriptPageAddBtn;
	
	@FindBy(xpath="//table[@id='selectManuscriptGrid']//tr//div[@id='CSHORTDESCRIPTION']")
	List<WebElement> manuscriptAddListformName;
	
	@FindBy(xpath="//table[@id='selectManuscriptGrid']//tr//input[@name='chkCSELECT_IND']")
	List<WebElement> manuscriptAddListformNameChkBox;
	
	@FindBy(id="PM_SEL_MANU_DONE")
	WebElement manuscriptAddListDoneBtn;
	
	@FindBy(id="PM_MANU_SAVE")
	WebElement manuscriptPageSaveBtn;
	
	@FindBy(id="PM_MANU_CLOSE")
	WebElement manuscriptPageCloseBtn;
	
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
	
	@FindBy(name="policyPhaseCode")
	WebElement policyPhase;
	
//	@FindBy(xpath="//select[contains(@name,'confirmed')]")
	@FindBy(name="483865737.confirmed")
	WebElement productNotifyDropDown;
	
	@FindBy(id="PM_NOTIFY_CLOSE")
	WebElement prodNotifyClose;
	
	@FindBy(name="additionalText")
	WebElement addText;
	
	public Policy_Quote_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
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
	
	public void CopyOptionFromActionDropDown() throws InterruptedException
	{
		Thread.sleep(4000);
		selectDropdownByValue(policyAction, valueOfPolicyActionCopy, "Policy Action");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy>Ok");
	}
	public void coverageDetailsSelect() throws InterruptedException
	{
		Thread.sleep(3000);
		clickButton(driver, coverageTab, "Coverage");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Coverage tab");
		Thread.sleep(3000);
	}
	
	public void coverageUpdates(String CoverageName, String binderForm, String PolicyNo) throws InterruptedException
	{
		for (int i = 0; i<coverageList.size();i++)
		{
			if(coverageList.get(i).getAttribute("innerHTML").equals(CoverageName))
			{
				clickButton(driver, coverageList.get(i),coverageList.get(i).getAttribute("innerHTML"));
				ExtentReporter.logger.log(LogStatus.INFO, "Select"+ CoverageName +" Coverage.");
				Thread.sleep(4000);
				break;
			}
		}
		clickButton(driver, optionalFormBtn, "Optional Form");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Optional Forms]");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		if (manuscriptList.isDisplayed())
		{
			clickButton(driver, manuscriptPageDeleteBtn, "Manu script Delete");
			driver.switchTo().alert().accept();
			ExtentReporter.logger.log(LogStatus.INFO, "Delete current Indication form, Are you sure you want to delete this? Click Ok");
			Thread.sleep(2000);
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
			ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");
			
		}else{
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
			ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");
		}
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		Thread.sleep(3000);
		for(int i=0;i<manuscriptAddListformName.size();i++)
		{
			if(manuscriptAddListformName.get(i).getAttribute("innerHTML").equals(binderForm))
			{
				clickButton(driver, manuscriptAddListformNameChkBox.get(i), "check Box for "+binderForm);
				ExtentReporter.logger.log(LogStatus.INFO, "Select "+ binderForm +", Click done.");
				break;
			}
		}
		clickButton(driver, manuscriptAddListDoneBtn, "Done");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		enterTextIn(addText, binderForm+" form added.", "Aditional Text");
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
		Thread.sleep(2000);
		clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
		ExtentReporter.logger.log(LogStatus.INFO, "Enter additional text: "+ binderForm+" form added  Click [Save] and Click [Close].");
		switchToParentWindowfromframe(driver);
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
	
	public void saveOption(String saveAsPolicyValue) throws InterruptedException
	{
		waitForElementToLoad(driver, 15, saveOptionBtn);
		clickButton(driver, saveOptionBtn, "Save Option");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options");
		Thread.sleep(4000);
		
		switchToFrameUsingId(driver, "popupframe1");
		selectDropdownByVisibleText(saveAsDropDown, saveAsPolicyValue, "Selected "+saveAsPolicyValue);
		clickButton(driver, saveOptionOkBtn, "Save");
		ExtentReporter.logger.log(LogStatus.INFO,  "Select "+saveAsPolicyValue+" Click [OK]");
		switchToParentWindowfromframe(driver);
		Thread.sleep(3000);
		
	
	
}

	public void product_Notify() throws InterruptedException{
		
			switchToFrameUsingId(driver, "popupframe1");
		
			waitForElementToLoad(driver, 10, productNotifyDropDown);
			
			selectDropdownByValue(productNotifyDropDown, ProductNotifyValue, "product notify");
			Thread.sleep(1000);
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window dispalyed to user.");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selected from Product Notify drop down list.");
		//	switchToParentWindowfromframe(driver);
			
	/*	}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window is NOT dispalyed to user.");
		//	switchToParentWindowfromframe(driver);
		}*/
			
		
		
	}
	
	public void changePhaseToQuote() throws InterruptedException
	{
		Thread.sleep(4000);
		selectDropdownByValue(policyPhase, QuotePhaseValue, "Phase");
		ExtentReporter.logger.log(LogStatus.INFO, "Change Phase from Indication to Quote");
	}

	
		
	//Select the policy Action from DDL
	public void select_policyAction(String policyAction_value) throws InterruptedException{
		
		waitForElementToLoad(driver, 15, policyAction);
		selectDropdownByVisibleText(policyAction, policyAction_value, "Renewal");
		ExtentReporter.logger.log(LogStatus.INFO, "Capture Transaction Details window opens");
		
		Thread.sleep(3000);
		
		switchToFrameUsingId(driver, "popupframe1");
	}
		
	
	public void switchToNextFrame() throws InterruptedException{
		
		//switchToParentWindowfromframe(driver);
		
		List<WebElement> secondFrame = driver.findElements(By.id("popupframe2"));
     	driver.switchTo().frame(secondFrame.get(0));
	
	}
	
	
	public void save_CaptureTransactionDetails () throws InterruptedException{
		
		Thread.sleep(3000);
		
		click(capt_Tranx_Ok, "Ok button for Renewal");
		
		switchToParentWindowfromframe(driver);
	
	}
		
	
	public void exit_SaveOption() throws InterruptedException{
			
		waitForElementToLoad(driver, 10, Exit_Ok);

		clickButton(driver, Exit_Ok, "Exit Ok");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
	}
	
	
	
}

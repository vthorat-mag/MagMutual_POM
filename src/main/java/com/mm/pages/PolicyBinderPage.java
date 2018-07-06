package com.mm.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.dto.PolicyBinderPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExtentReporter;
import com.relevantcodes.extentreports.LogStatus;

public class PolicyBinderPage extends CommonAction {

	// Global Variable assignment.
	WebDriver driver;
	PolicyBinderPageDTO policybinderpageDTO;
	
	CommonUtilities comUtil = new CommonUtilities();
	String valueOfPolicyActionEndorse = "javascript:endorseTransaction('oosendorse');";
	String saveAsPolicyValue = "OFFICIAL";
	String ProductNotifyValue = "Y";
	String valueOfSelectReason = "END009";
	String valueOfPolicyActionCopyToQuote = "javascript:copyToQuote();";
	String FileSearchPageTitle = "File Search";
	String addFilePageTitle = "Add File";
	String entitySelectListPageTitle = "Entity Select List";
	String entitySearchListPageTitle = "Entity Select Search";
	String fileTypeDropDownValue = "CLAIM";
	String lobDropDownValue = "HLP";
	String fileHandlerDropDownValue = "416012116";
	String stateOfLossDropDownValue = "GA";
	String searchEntityPageTitle = "Entity Select Search";
	String policyPhaseVlaue="Policy";
	String innerText ="innerText";

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

	@FindBy(xpath = "//iframe[@id='popupframe1']")
	WebElement entityMiniPopupFrameId;

	@FindBy(id = "entity_clientIDROSPAN")
	WebElement clientId;

	@FindBy(id = "CI_ENTITY_MINI_POP_CLS")
	WebElement entityMiniPopupCloseBtn;
	
	@FindBy(id = "topnav_Claims")
	WebElement headerClaimsTab;
	
	@FindBy(xpath="//a[@class='selectedMenu fNiv isParent']//span")
	WebElement filesMenuTab;
	
	@FindBy(xpath= "//li[@id='CM_ADD_CLAIM_MI']//a")
	WebElement fileAddMenuOption;
	
	@FindBy(id="btnFind_claimantFullName")
	WebElement patientSearchIcon;
	
	@FindBy(xpath= "//input[@name='entitySearch_lastOrOrgName']")
	WebElement lastNameEntitySearchPage;
	
	@FindBy(xpath= "//input[@name='entity_firstName']")
	WebElement firstNameEntitySearchPage;
	
	@FindBy(id="CI_ENTITY_SELECT_SCH_SCH")
	WebElement searchBtnOnEntitySearchPage;
	
	@FindBy(id="CCLIENT_NAME")
	WebElement resultOnEntityListPage;
	
	@FindBy(xpath="//input[@name='chkCSELECTIND']")
	WebElement selectEntityChkBox;	
	
	@FindBy(id="CI_ENT_SEL_LST_FORM_SEL")
	WebElement selectBtnOnEntitySelectListPage;
	
	@FindBy(xpath = "//select[@name ='claimType']")
	WebElement FileTypeDropDown;
	
	@FindBy(xpath = "//select[@name ='cmLobCode']")
	WebElement lobDropDown;
	
	@FindBy(xpath = "//textarea[@class='oasis_formfieldreq']")
	WebElement descriptionTextBox;
	
	@FindBy(xpath = "//input[@name='claimantFullName']")
	WebElement patientSelectedValue;
	
	@FindBy(xpath = "//select[@name='entityExaminerId']")
	WebElement fileHandlerDorpDown;
	
	@FindBy(xpath = "//select[@name='claimStateCode']")
	WebElement stateOfLossDorpDown;
	
	@FindBy(xpath = "//input[@name='lossDate']")
	WebElement accidentDateTextBox;
	
	@FindBy(xpath = "//img[@id='btnFind_insuredFullName']")
	WebElement insuredSearchIcon;
	
	@FindBy(xpath = "//input[@name = 'entity_clientID']")
	WebElement entityClientId;
	
	// Constructor to initialize variables on policy binder page.
	public PolicyBinderPage(WebDriver driver) throws IllegalArgumentException, IllegalAccessException, SecurityException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		policybinderpageDTO = new PolicyBinderPageDTO();
	}
	
	//Navigate to Claims page.
	public ClaimsPage navigatetoClaimsPage() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Click Claims in right corner of screen");
		clickButton(driver, headerClaimsTab, "Header CIS");
	//	getPageTitle(driver, policybinderpageDTO.fileSearchPageTitle);
		return new ClaimsPage(driver);
	}
	
	//Select Patient.
	public void getPatientDetails(String clientIdValue) throws Exception
	{
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.moveToElement(filesMenuTab).build().perform();
		clickButton(driver, fileAddMenuOption, "Add File Menu");
		getPageTitle(driver, policybinderpageDTO.addFilePageTitle);
		clickButton(driver, patientSearchIcon, "Patient Search");
		waitFor(driver, 10);
		String parentWindowId = switchToWindow(driver);
		getPageTitle(driver, policybinderpageDTO.entitySearchListPageTitle);
		/*String LastName = "ABNEY";
		String FirstName = "DARYL";*/
		//TODO - need to store above 2  values in Excel sheet.
		enterTextIn(driver, lastNameEntitySearchPage,policybinderpageDTO.lastName,"Last Name");
		enterTextIn(driver, firstNameEntitySearchPage, policybinderpageDTO.firstName,"First Name");
		click(driver, searchBtnOnEntitySearchPage, "Entity Search Page's Search");
		Assert.assertEquals(resultOnEntityListPage.getAttribute("innerHTML").trim(),policybinderpageDTO.lastName+", "+policybinderpageDTO.firstName+",", "Data displayed after search is not correct");
		waitFor(driver,5);
		click(driver, selectEntityChkBox, "Select Entity Check Box");
		clickButton(driver, selectBtnOnEntitySelectListPage, "Entity Select List Page's Select");
		switchToParentWindowfromotherwindow(driver, parentWindowId);
		Assert.assertEquals(patientSelectedValue.getAttribute("value").trim(), policybinderpageDTO.lastName+", "+policybinderpageDTO.firstName+",", "Patient selected is NOT displayed correctly");
		selectDropdownByValue(driver, FileTypeDropDown, policybinderpageDTO.fileTypeDropDownValue, "File Type");
		selectDropdownByValue(driver, lobDropDown, policybinderpageDTO.lobDropDownValue, "LOB");
		enterTextIn(driver, descriptionTextBox, policybinderpageDTO.description,"Description");
		selectDropdownByValue(driver, fileHandlerDorpDown, policybinderpageDTO.fileHandlerDropDownValue, "File Handler");
		selectDropdownByValue(driver, stateOfLossDorpDown, policybinderpageDTO.stateOfLossDropDownValue, "State Of Loss");
		enterTextIn(driver, accidentDateTextBox, comUtil.getSystemDatemmddyyyy(),"Accident Date");
		clickButton(driver, insuredSearchIcon, "Insured Search Icon");
		String parentWindowIdSearchEntity = switchToWindow(driver);
		//switchToFrameUsingElement(driver, entityMiniPopupFrameId);
		String searchEntityTitle = getPageTitle(driver, policybinderpageDTO.searchEntityPageTitle);
		//enterTextIn(driver, entityClientId,clinetIdValue , "Clent Id");
		enterTextIn(driver, entityClientId,clientIdValue , "Client Id");
		clickButton(driver, searchBtnOnEntitySearchPage,"Entity Search Page's Search");
		waitFor(driver, 10);
		Assert.assertTrue(resultOnEntityListPage.isDisplayed(), "Insured Name is not populated on 'Entity Select List' page.");
		click(driver, selectEntityChkBox, "Insured Name");
		clickButton(driver, selectBtnOnEntitySelectListPage, "Select");
		
		//TODO - Need To add below steps once got confirmaiton on query - Cant see policy No from Policy No drop down field.
		/*In the filter criteria section, click the Policy No dropdown and 
		Select [Policy number entered in step 3]
		Click the checkbox next the Prof Liab coverage 
		Click Save as Claim
		Possible duplicate claim screen displays
		Click Save as Claim 
		Claim No displays in the upper left corner. 
		Note (and save for later input) the claim number:  ****add ###########
		Click [Close]*/

		
	}

	// Get Client Id from Entity menu popup flow.
	public String getClientId() throws Exception {
		clickButton(driver, policyHolderNameLink, "Policy Holder Name");
		switchToFrameUsingElement(driver, entityMiniPopupFrameId);
		getPageTitle(driver, "Entity Mini Popup");
		String getClientIdValue = clientId.getAttribute("innerHTML");
		//TODO - need to store above value in Excel sheet.
		clickButton(driver, entityMiniPopupCloseBtn, "Entity Mini Popup Close");
		switchToParentWindowfromframe(driver);
		return getClientIdValue;
	}

	// Identify Policy number from Page.
	public String policyNo() {
		String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
		String[] portfolioNo = profileNoLable.split(" ", 3);
		return portfolioNo[2];
	}

	// Select Endorsement from "Action DropoDown".
	public PolicyBinderPage endorsementFromActionDropDown() throws IllegalArgumentException, IllegalAccessException, SecurityException {
		ExtentReporter.logger.log(LogStatus.PASS, "Click Policy Actions > Select value from the dropdown screen.");
		selectDropdownByValue(driver, policyAction, policybinderpageDTO.valueOfPolicyActionEndorse, "Policy Action");
		return new PolicyBinderPage(driver);
	}

	// Select Copy To Quote from "Action DropoDown".
	public PolicySubmissionPage copyToQuoteFromActionDropDown(String policyNum) throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy to Quote");
		selectDropdownByValue(driver, policyAction, policybinderpageDTO.valueOfPolicyActionCopyToQuote, "Policy Action");
		Thread.sleep(10000);
		String getUpdatedPolicyNo = policyNo();
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + getUpdatedPolicyNo + "')]")));
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
		click(driver, Exit_Ok, "OK button");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		return new PolicySubmissionPage(driver);
	}

	// Endorse Policy Flow.
	public PolicyBinderPage endorsPolicy(String policyNum) throws Exception {
		Thread.sleep(3000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNum + "')]")));
		waitForElementToLoad(driver, 25, selectReason);
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click the dropdown by Reason:  Select Issue Policy Forms-->Click [Ok]");
		selectDropdownByValue(driver, selectReason, policybinderpageDTO.valueOfSelectReason, "Select Reason");
		clickButton(driver, okBtnEndorsmentPopup, "Ok");
		Thread.sleep(4000);
		return new PolicyBinderPage(driver);
	}

	// Identify Phase from page.
	public PolicyBinderPage identifyPhase() throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		waitFor(driver, 5);
		String getTextPolicyPhase = policyPhasePolicy.getAttribute("innerText");
		ExtentReporter.logger.log(LogStatus.INFO, "Verify phase is " + getTextPolicyPhase);
		//verifyValueFromField(driver, policyPhasePolicy, policyPhaseVlaue, "innerText");
		return new PolicyBinderPage(driver);
	}

	// Rate a Functionality flow.
	public PolicyBinderPage rateFunctionality(String policyNo) throws Exception {
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Rate]");
		clickButton(driver, rateBtn, "Rate Tab");
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
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Close] click [Ok]");
		clickButton(driver, closeBtnOnViewPremiumPopup, "Close");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
		switchToParentWindowfromframe(driver);
		return new PolicyBinderPage (driver);
	}

	// Save Option functionality flow.
	public PolicyQuotePage saveOption() throws Exception {
		/*
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options");
		clickButton(driver, saveOptionBtn, "Save Option");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		ExtentReporter.logger.log(LogStatus.INFO, "Select Official Click [OK]");
		selectDropdownByValue(driver, saveAsDropDown, policybinderpageDTO.saveAsPolicyValue, "Save Option");
		clickButton(driver, saveOptionOkBtn, "Save");
		Thread.sleep(6000);
		
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
		
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		Thread.sleep(4000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
		clickButton(driver, Exit_Ok, "Exit Ok");
		*/
		saveOption(driver, saveOptionBtn, saveAsDropDown, saveOptionOkBtn, policybinderpageDTO.saveAsPolicyValue);
		return new PolicyQuotePage(driver); 
	}

}

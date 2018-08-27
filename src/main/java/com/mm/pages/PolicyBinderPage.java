package com.mm.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import com.mm.dto.RateAPolicyPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExtentReporter;
import com.mm.utils.TestCaseDetails;
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
	String policyPhaseVlaue = "Policy";
	String innerText = "innerText";
	String policyValue = "Policy";

	// Element repository for the page Policy Binder page.
	@FindBy(name = "globalSearch")
	WebElement Policy_Search;

	@FindBy(name = "search")
	WebElement Search_btn;

	@FindBy(xpath = "//*[@id = 'pageTitleForpageHeaderForPolicyFolder'] |//*[@id = 'pageTitleForpageHeaderForClaimFolder']")
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
	
	@FindBy(id = "PM_CPT_TRAN_OK")
	WebElement okBtnCaptureTxnDetails;

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

	@FindBy(id = "polPhaseCodeLOVLABELSPAN")
	WebElement phaseField;

	@FindBy(xpath = "//a[@class='selectedMenu fNiv isParent']//span")
	WebElement filesMenuTab;

	@FindBy(xpath = "//li[@id='CM_ADD_CLAIM_MI']//a")
	WebElement fileAddMenuOption;

	@FindBy(id = "btnFind_claimantFullName")
	WebElement patientSearchIcon;

	@FindBy(xpath = "//input[@name='entitySearch_lastOrOrgName']")
	WebElement lastNameEntitySearchPage;

	@FindBy(xpath = "//input[@name='entity_firstName']")
	WebElement firstNameEntitySearchPage;

	@FindBy(id = "CI_ENTITY_SELECT_SCH_SCH")
	WebElement searchBtnOnEntitySearchPage;

	@FindBy(id = "CCLIENT_NAME")
	WebElement resultOnEntityListPage;

	@FindBy(xpath = "//input[@name='chkCSELECTIND']")
	WebElement selectEntityChkBox;

	@FindBy(id = "CI_ENT_SEL_LST_FORM_SEL")
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
	public PolicyBinderPage(WebDriver driver)
			throws IllegalArgumentException, IllegalAccessException, SecurityException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		policybinderpageDTO = new PolicyBinderPageDTO(TestCaseDetails.testDataDictionary);
	}

	// Navigate to Claims page.
	public ClaimsPage navigatetoClaimsPage() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Claims in right corner of screen");
		clickButton(driver, headerClaimsTab, "Header CIS");
		// getPageTitle(driver, policybinderpageDTO.fileSearchPageTitle);
		return new ClaimsPage(driver);
	}

	// Verify phase method
	public PolicyBinderPage verifyPhase() throws IllegalArgumentException, IllegalAccessException, SecurityException {
		ExtentReporter.logger.log(LogStatus.INFO, "Phase changed to Policy.");
		// Assert.assertEquals(phaseField.getAttribute("innerHTML").trim(),
		// policyValue,"Expected Frame is not displayed");
		return new PolicyBinderPage(driver);
	}

	// Select Patient.
	public void getPatientDetails(String clientIdValue) throws Exception {
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.moveToElement(filesMenuTab).build().perform();
		clickButton(driver, fileAddMenuOption, "Add File Menu");
		getPageTitle(driver, policybinderpageDTO.addFilePageTitle);
		clickButton(driver, patientSearchIcon, "Patient Search");
		waitFor(driver, 10);
		String parentWindowId = switchToWindow(driver);
		getPageTitle(driver, policybinderpageDTO.entitySearchListPageTitle);
		/*
		 * String LastName = "ABNEY"; String FirstName = "DARYL";
		 */
		// TODO - need to store above 2 values in Excel sheet.
		enterTextIn(driver, lastNameEntitySearchPage, policybinderpageDTO.lastName, "Last Name");
		enterTextIn(driver, firstNameEntitySearchPage, policybinderpageDTO.firstName, "First Name");
		click(driver, searchBtnOnEntitySearchPage, "Entity Search Page's Search");
		Assert.assertEquals(resultOnEntityListPage.getAttribute("innerHTML").trim(),
				policybinderpageDTO.lastName + ", " + policybinderpageDTO.firstName + ",",
				"Data displayed after search is not correct");
		waitFor(driver, 5);
		click(driver, selectEntityChkBox, "Select Entity Check Box");
		clickButton(driver, selectBtnOnEntitySelectListPage, "Entity Select List Page's Select");
		switchToParentWindowfromotherwindow(driver, parentWindowId);
		Assert.assertEquals(patientSelectedValue.getAttribute("value").trim(),
				policybinderpageDTO.lastName + ", " + policybinderpageDTO.firstName + ",",
				"Patient selected is NOT displayed correctly");
		selectDropdownByValue(driver, FileTypeDropDown, policybinderpageDTO.fileTypeDropDownValue, "File Type");
		selectDropdownByValue(driver, lobDropDown, policybinderpageDTO.lobDropDownValue, "LOB");
		enterTextIn(driver, descriptionTextBox, policybinderpageDTO.description, "Description");
		selectDropdownByValue(driver, fileHandlerDorpDown, policybinderpageDTO.fileHandlerDropDownValue,
				"File Handler");
		selectDropdownByValue(driver, stateOfLossDorpDown, policybinderpageDTO.stateOfLossDropDownValue,
				"State Of Loss");
		enterTextIn(driver, accidentDateTextBox, comUtil.getSystemDatemm_dd_yyyy(), "Accident Date");
		clickButton(driver, insuredSearchIcon, "Insured Search Icon");
		String parentWindowIdSearchEntity = switchToWindow(driver);
		// switchToFrameUsingElement(driver, entityMiniPopupFrameId);
		String searchEntityTitle = getPageTitle(driver, policybinderpageDTO.searchEntityPageTitle);
		// enterTextIn(driver, entityClientId,clinetIdValue , "Clent Id");
		enterTextIn(driver, entityClientId, clientIdValue, "Client Id");
		clickButton(driver, searchBtnOnEntitySearchPage, "Entity Search Page's Search");
		waitFor(driver, 10);
		Assert.assertTrue(resultOnEntityListPage.isDisplayed(),
				"Insured Name is not populated on 'Entity Select List' page.");
		click(driver, selectEntityChkBox, "Insured Name");
		clickButton(driver, selectBtnOnEntitySelectListPage, "Select");

		// TODO - Need To add below steps once got confirmaiton on query - Cant see
		// policy No from Policy No drop down field.
		/*
		 * In the filter criteria section, click the Policy No dropdown and Select
		 * [Policy number entered in step 3] Click the checkbox next the Prof Liab
		 * coverage Click Save as Claim Possible duplicate claim screen displays Click
		 * Save as Claim Claim No displays in the upper left corner. Note (and save for
		 * later input) the claim number: ****add ########### Click [Close]
		 */
	}

	// Get Client Id from Entity menu pop up flow.
	public String getClientId() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click on the Policyholder Name: Note (and save for later input) the Client ID:  Click [Close]");
		clickButton(driver, policyHolderNameLink, "Policy Holder Name");
		switchToFrameUsingElement(driver, entityMiniPopupFrameId);
		getPageTitle(driver, "Entity Mini Popup");
		String getClientIdValue = clientId.getAttribute("innerHTML");
		// TODO - need to store above value in Excel sheet.
		clickButton(driver, entityMiniPopupCloseBtn, "Entity Mini Popup Close");
		switchToParentWindowfromframe(driver);
		return getClientIdValue;
	}

	// Identify Policy number from Page.
	public String policyNo() throws InterruptedException {
		Thread.sleep(2000);
		String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
		String[] portfolioNo = profileNoLable.split(" ", 3);
		return portfolioNo[2];
	}

	// Select Endorsement from "Action DropoDown".
	public PolicyBinderPage endorsementFromActionDropDown() throws Exception {
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.PASS, "Select Policy Actions-> Endorsement. Verify Endorse policy window displays.");
		if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policybinderpageDTO.valueOfPolicyActionEndorse, "Policy Action").equals("false")) {
			RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
			RateAPolicyPageDTO rateApolicyPageDTO = new RateAPolicyPageDTO(TestCaseDetails.testDataDictionary);
			rateapolicypage.searchBackUpPolicy();
			PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
			policybinderpage.copyToQuoteFromActionDropDown(rateApolicyPageDTO.backUpPolicyNum);
			selectDropdownByValueFromPolicyActionDDL(driver, policyAction,
					policybinderpageDTO.valueOfPolicyActionEndorse, "Policy Action");
		}

		// selectDropdownByValue(driver, policyAction,
		// policybinderpageDTO.valueOfPolicyActionEndorse, "Policy Action");
		return new PolicyBinderPage(driver);
	}

	// Select Endorsement from "Action DropoDown" without searching for backup
	// policy.
	public PolicyBinderPage endorsementFromActionDropDownwithoutBackupPolicy() throws Exception {
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.PASS,
				"Select Policy Actions-> Endorsement. Verify Endorse policy window displays.");
		selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policybinderpageDTO.valueOfPolicyActionEndorse,
				"Policy Action");
		// selectDropdownByValue(driver, policyAction,
		// policybinderpageDTO.valueOfPolicyActionEndorse, "Policy Action");
		return new PolicyBinderPage(driver);
	}
	// Select Copy To Quote from "Action DropoDown".
	public PolicySubmissionPage copyToQuoteFromActionDropDown(String policyNum) throws Exception {
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click Policy Actions>Copy to Quote. Verify Policy folder shows a new number, Phase show Submission.");
		if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction,
				policybinderpageDTO.valueOfPolicyActionCopyToQuote, "Policy Action").equals("false")) {
			RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
			RateAPolicyPageDTO rateApolicyPageDTO = new RateAPolicyPageDTO(TestCaseDetails.testDataDictionary);
			rateapolicypage.searchBackUpPolicy();
			PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
			Thread.sleep(2000);
			policybinderpage.copyToQuoteFromActionDropDownwithoutBackUpPolicy(rateApolicyPageDTO.backUpPolicyNum);
			selectDropdownByValueFromPolicyActionDDL(driver, policyAction,
					policybinderpageDTO.valueOfPolicyActionCopyToQuote, "Policy Action");
		}

		// selectDropdownByValue(driver, policyAction,
		// policybinderpageDTO.valueOfPolicyActionCopyToQuote, "Policy Action");
		Thread.sleep(5000);
		invisibilityOfLoader(driver);
		//invisibilityOfProcessesingWindow(driver);
		String getUpdatedPolicyNo = policyNo();
		verifyCpatureTxnDetailsPageDisplayedOrNot(getUpdatedPolicyNo);
		Thread.sleep(8000);
		invisibilityOfLoader(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo() + "')]")));
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click [OK]. Verify Policy folder shows a new number, Phase show Submission.");
		click(driver, Exit_Ok, "OK button");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		return new PolicySubmissionPage(driver);
	}
	
	// Select Copy To Quote from "Action DropoDown" without backup policyserach.
		public PolicySubmissionPage copyToQuoteFromActionDropDownwithoutBackUpPolicy(String policyNum) throws Exception {
			Thread.sleep(2000);
			ExtentReporter.logger.log(LogStatus.INFO,
					"Click Policy Actions>Copy to Quote. Verify Policy folder shows a new number, Phase show Submission.");
				RateAPolicyPageDTO rateApolicyPageDTO = new RateAPolicyPageDTO(TestCaseDetails.testDataDictionary);
				PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
				selectDropdownByValueFromPolicyActionDDL(driver, policyAction,
						policybinderpageDTO.valueOfPolicyActionCopyToQuote, "Policy Action");
			Thread.sleep(15000);
			invisibilityOfLoader(driver);
			//invisibilityOfProcessesingWindow(driver);
			String getUpdatedPolicyNo = policyNo();
			verifyCpatureTxnDetailsPageDisplayedOrNot(getUpdatedPolicyNo);
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + getUpdatedPolicyNo + "')]")));
			ExtentReporter.logger.log(LogStatus.INFO,
					"Click [OK]. Verify Policy folder shows a new number, Phase show Submission.");
			click(driver, Exit_Ok, "OK button");
			Thread.sleep(2000);
			switchToParentWindowfromframe(driver);
			return new PolicySubmissionPage(driver);
		}
		
		public void verifyCpatureTxnDetailsPageDisplayedOrNot(String policyNo) throws Exception
		{
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo+ "')]")));
			
			try {
				if (okBtnCaptureTxnDetails.isDisplayed()) {
					clickButton(driver, okBtnCaptureTxnDetails, "Captuer Transaction Details Cancel");
					ExtentReporter.logger.log(LogStatus.INFO, "Capture transaction details displayed.");
				}
			} catch (Exception e) {
				ExtentReporter.logger.log(LogStatus.INFO, "Capture transaction details is NOT displayed.");
			}
		}

	//Select Copy To Quote from "Action DropoDown" for copy to quote TC.
	//We don't have to select copy to quote option from action ddl in COpy to quote TC hence separate method is written for backup policy search.
	public PolicySubmissionPage copyToQuoteFromActionDropDownForCopyToQuoteTC(String policyNum) throws Exception {
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy to Quote. Verify Policy folder shows a new number, Phase show Submission.");
		if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policybinderpageDTO.valueOfPolicyActionCopyToQuote,
				"Policy Action").equals("false")) {
			RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
			RateAPolicyPageDTO rateApolicyPageDTO = new RateAPolicyPageDTO(TestCaseDetails.testDataDictionary);
			rateapolicypage.searchBackUpPolicy();
			//PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
			//policybinderpage.copyToQuoteFromActionDropDown(rateApolicyPageDTO.backUpPolicyNum);
			selectDropdownByValueFromPolicyActionDDL(driver, policyAction, policybinderpageDTO.valueOfPolicyActionCopyToQuote,
					"Policy Action");
		}
		Thread.sleep(5000);
		invisibilityOfLoader(driver);
		String getUpdatedPolicyNo = policyNo();
		Thread.sleep(4000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + getUpdatedPolicyNo + "')]")));
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click [OK]. Verify Policy folder shows a new number, Phase show Submission.");
		click(driver, Exit_Ok, "OK button");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		return new PolicySubmissionPage(driver);
	}

	public RateApolicyPage endorseAPolicyforRateApolicyPage(String policyNum) throws Exception {
		endorsePolicy(policyNum);
		return new RateApolicyPage(driver);
	}

	// Endorse Policy Flow.
	public PolicyBinderPage endorsePolicy(String policyNum) throws Exception {
		Thread.sleep(3000);
		/*
		 * switchToFrameUsingElement(driver,
		 * driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNum +
		 * "')]")));
		 */
		switchToFrameUsingId(driver, "popupframe1");
		waitForElementToLoad(driver, 10, selectReason);
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click the dropdown by Reason:  Select Issue Policy Forms-->Click [Ok] & verify window closes.");
		selectDropdownByValue(driver, selectReason, policybinderpageDTO.valueOfSelectReason, "Select Reason");
		clickButton(driver, okBtnEndorsmentPopup, "Ok");
		Thread.sleep(4000);
		return new PolicyBinderPage(driver);
	}

	// Identify Phase from page.
	public PolicyBinderPage identifyPhase()
			throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		waitFor(driver, 5);
		String getTextPolicyPhase = policyPhasePolicy.getAttribute("innerText");
		ExtentReporter.logger.log(LogStatus.INFO, "Verify phase is " + getTextPolicyPhase);
		// verifyValueFromField(driver, policyPhasePolicy, policyPhaseVlaue,
		// "innerText");
		return new PolicyBinderPage(driver);
	}

	// Rate a Functionality flow.
	public PolicyBinderPage rateFunctionality(String policyNo) throws Exception {

		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		rateapolicypage.rateFunctionality(policyNo);
		return new PolicyBinderPage(driver);
	}

	// Save Option functionality flow.
	public PolicyQuotePage saveOption(String policyNo) throws Exception {
		saveOption(driver, saveOptionBtn, saveAsDropDown, saveOptionOkBtn, Exit_Ok,
				policybinderpageDTO.saveAsPolicyValue, policyNo);
		return new PolicyQuotePage(driver);
	}

}

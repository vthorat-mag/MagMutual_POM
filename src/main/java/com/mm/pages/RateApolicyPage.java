package com.mm.pages;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.MidiDevice.Info;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.dto.RateAPolicyPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.TestCaseDetails;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mongodb.client.model.ReturnDocument;
import com.relevantcodes.extentreports.LogStatus;

public class RateApolicyPage extends CommonAction {
	WebDriver driver;
	RateAPolicyPageDTO rateApolicyPageDTO;
	PolicyIndicationPage policyindicationpage;
	PolicyQuotePage policyquotepage = new PolicyQuotePage(this.driver);
	String valueOfPolicyActionAccept = "javascript:acceptQuote();";
	String billingSetup = "javascript:billingSetup();";
	String paymentPlanValue = "659689385";
	String saveAsPolicyValue = "OFFICIAL";
	String ProductNotifyValue = "Y";
	String manageBillingSetupPageTitle = "Manage Billing Setup";
	String manuscriptInformationPageTitle = "Manuscript Information";
	String policyPhaseValue = "Binder";
	String innerText = "innerText";
	String commentText = "Issue Policy Forms";
	String dropDownNameLevel = "Level";
	String prodNotFlag;
	String ExcelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx";
	CommonUtilities comUtil = new CommonUtilities();
	PDFReader pdfread = new PDFReader(driver);

	// Object repository for all elements on rate A policy page.

	@FindBy(name = "globalSearch")
	WebElement Policy_Search;

	@FindBy(name = "search")
	WebElement Search_btn;

	@FindBy(id = "PM_COMMON_TABS_RATE")
	WebElement RateBtn;

	@FindBy(name = "483865737.confirmed")
	WebElement Continue_saving;

	@FindBy(id = "PM_NOTIFY_CLOSE")
	WebElement Notify_Close;

	// @FindBy(id="PM_VIEW_PREM_CLOSE")
	@FindBy(id = "PM_VIEW_VAL_ERR_CLOSE ")
	WebElement Prem_Close;

	@FindBy(name = "btnSaveAsCSV")
	WebElement Export;

	@FindBy(name = "workflowExit_Ok")
	WebElement Exit_Ok;

	@FindBy(xpath = "//select[@id = 'PM_QT_POLICY_FOLDER_AG'] | //select[@id = 'PM_POLICY_FOLDER_AG']")
	WebElement policyAction;

	@FindBy(xpath = "//div[contains(@id ,'pageTitleForpageHeaderFor')]")
	List<WebElement> pageHeaderForPolicyFolder;

	@FindBy(xpath = "//div[contains(@id ,'pageTitleForpageHeader')] | //div[contains(@id ,'pageTitleForpageHeaderFor')]")
	WebElement pageHeaderForPageTitle;

	// @FindBy(id="polPhaseCodeROSPAN")
	@FindBy(xpath = "//table[@id='formFieldsTableForHeaderFieldsSecond']//span[@id='polPhaseCodeROSPAN']")
	WebElement policyPhaseBinder;

	@FindBy(xpath = "//select[@name='paymentPlanId']")
	WebElement paymentPlan;

	@FindBy(xpath = "//div[@class='horizontalButtonCollection']//input[@id='PM_BILLNG_SAVE'] | //input[@id = 'FM_BILLING_SETUP_SAVE']")
	WebElement billingSetupSaveBtn;

	@FindBy(xpath = "//a[@id='PM_PT_VIEWCVG']//span[@class='tabWithNoDropDownImage']")
	WebElement coverageTab;

	@FindBy(xpath = "//select[@name='policyNavLevelCode']//option")
	WebElement primaryRisk;

	@FindBy(xpath = "//select[@name='policyNavLevelCode']")
	WebElement listDDL;

	@FindBy(xpath = "//table[@id='coverageListGrid']//tbody//td//div[@id='CPRODUCTCOVERAGEDESC']")
	List<WebElement> coverageList;

	@FindBy(xpath = "//*[@id = 'PM_QT_MANU_PUP'] | //*[@id='PM_MANU_PUP']")
	WebElement optionalFormBtn;

	@FindBy(xpath = "//table[@id='maintainManuscriptListGrid']")
	WebElement manuscriptList;

	@FindBy(id = "PM_MANU_DELETE")
	WebElement manuscriptPageDeleteBtn;

	@FindBy(id = "PM_MANU_ADD")
	WebElement manuscriptPageAddBtn;

	@FindBy(xpath = "//table[@id='maintainManuscriptListGrid']//tr")
	List<WebElement> manuscriptInformationTypeListGrid;

	@FindBy(xpath = "//table[@id='selectManuscriptGrid']//tr//div[@id='CSHORTDESCRIPTION']")
	List<WebElement> manuscriptAddListformName;

	@FindBy(xpath = "//table[@id='selectManuscriptGrid']//tr//input[@name='chkCSELECT_IND']")
	List<WebElement> manuscriptAddListformNameChkBox;

	@FindBy(id = "PM_SEL_MANU_DONE")
	WebElement manuscriptAddListDoneBtn;

	@FindBy(id = "PM_MANU_SAVE")
	WebElement manuscriptPageSaveBtn;

	@FindBy(id = "PM_MANU_DTENTRY")
	WebElement dataEntryBtn;

	@FindBy(id = "ictextField")
	WebElement titleHFLHPLCHGGE;

	@FindBy(name = "paraSel")
	WebElement freeFormCHGGEBeginChkBox;

	@FindBy(xpath = "//button[@role='presentation']//i[@class='mce-ico mce-i-paste']")
	WebElement saveIconOnCincom;

	@FindBy(xpath = "//body[@id = 'tinymce']//p")
	WebElement freeFormCHGGEBeginTxtField;

	@FindBy(xpath = "//iframe[contains(@title,'Rich Text Area')]")
	WebElement cincomIFrame;

	@FindBy(id = "PM_MANU_CLOSE")
	WebElement manuscriptPageCloseBtn;

	@FindBy(xpath = "//iframe[contains(@id='popupframe')]")
	List<WebElement> allIframes;

	@FindBy(id = "PM_COMMON_TABS_RATE")
	WebElement rateBtn;

	@FindBy(id = "PM_VIEW_PREM_CLOSE")
	WebElement closeBtnOnViewPremiumPopup;

	@FindBy(name = "workflowExit_Ok")
	WebElement okPolicySaveAsWIPPopup;

	@FindBy(id = "PM_COMMON_TABS_PREVIEW")
	WebElement previewBtn;

	@FindBy(xpath = "//div[@id='spellchecker-content']")
	WebElement spellchkBox;

	@FindBy(xpath = "//span[@class='dragbox_main_head_class']")
	WebElement spellchkBoxHeading;

	@FindBy(xpath = "//div[@class='noerror']")
	WebElement sucessMsg;

	@FindBy(xpath = "//button[contains(@class,'titlebar-close')]")
	WebElement closePdf;

	@FindBy(id = "PM_COMMON_TABS_SAVE")
	WebElement saveOptionBtn;

	@FindBy(xpath = "//select[@name='saveAsCode']")
	WebElement saveAsDropDown;

	@FindBy(id = "PM_SAVE_OPTION_OK")
	WebElement saveOptionOkBtn;

	@FindBy(xpath = "//select[contains(@name,'confirmed')]")
	WebElement productNotifyDropDown;

	@FindBy(id = "PM_NOTIFY_CLOSE")
	WebElement prodNotifyClose;

	@FindBy(name = "additionalText")
	WebElement addText;

	@FindBy(name = "transactionComment")
	WebElement CommentsTxtBoxOnEndorsePolicyPopup;

	@FindBy(xpath = "//iframe[@class ='cover']")
	WebElement checkSpellIframe;

	@FindBy(id = "policyPhaseCode_VALUE_CONTAINER")
	WebElement phaseNonEditableField;

	@FindBy(xpath = "//span[@id='policyWrittenPremiumROSPAN1']")
	WebElement writtenPremium;

	@FindBy(id = "CWRITTENPREMIUM")
	List<WebElement> cwWrittenPremiumOnViewPremium;

	// For policy add forms TC42399

	@FindBy(name = "policyViewMode")
	WebElement viewMode;

	@FindBy(xpath = "//select[@id='PM_POLICY_FOLDER_AG'] | //select[@id='PM_QT_POLICY_FOLDER_AG']")
	WebElement policyActionDDL;

	@FindBy(id = "CPRODUCTCOVERAGEDESC")
	WebElement coverage;

	@FindBy(id = "PM_COMMON_TABS_SAVEWIP")
	WebElement saveWIP;

	@FindBy(xpath = "//a[@id='PM_PT_VIEWPOL']//span")
	WebElement policyTab;

	@FindBy(name = "endorsementCode")
	WebElement endorsementReason;

	@FindBy(id = "PM_ENDORSE_OK")
	WebElement endorsePolicyOK;

	@FindBy(id = "CFORMCODELOVLABEL")
	List<WebElement> manuscriptAddedForm;

	@FindBy(id = "findPolicyListGrid_CPOLICYNO_0_HREF") // QA
	WebElement policyList;

	// Constructor to initialize driver, page elements and DTO PageObject for
	// CISPage
	public RateApolicyPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		rateApolicyPageDTO = new RateAPolicyPageDTO(TestCaseDetails.testDataDictionary);
	}
	public RateApolicyPage policyEndorsement(String PolicyNo) throws Exception
	{
		PolicyBinderPage pbp = new PolicyBinderPage(driver);
		pbp.endorsementFromActionDropDown();
		pbp.endorsePolicy(PolicyNo);
		return new RateApolicyPage(driver);
	}

	// Select Endorsement from Policy Action and from Endorse Policy pop up
	// select Reason as 'Issue Policy Forms'. This method will be used to endorse without backup policy
	public RateApolicyPage policyEndorsementwithoutBackupPolicy(String PolicyNo) throws Exception {
		invisibilityOfLoader(driver);
		PolicyBinderPage pbp = new PolicyBinderPage(driver);
		pbp.endorsementFromActionDropDown();
		pbp.endorsePolicy(PolicyNo);
		
		invisibilityOfLoader(driver);
		Thread.sleep(5000);
		AcceptFromActionDropDownwithoutBackupPolicy();
		return new RateApolicyPage(driver);
	}

	// Search Policy from Search Policy text field.
	public RateApolicyPage searchPolicy(String policy_no) throws Exception {
		// Thread.sleep(3000);
		policySearch(driver, policy_no, Policy_Search, Search_btn, policyList);
		//String actual = getText(driver, pageHeaderForPolicyFolder.get(0));
		Thread.sleep(3000);
		return new RateApolicyPage(driver);
	}

	public String checkPolicyViewModeAndUpdateCoverage(String policyNo) throws Exception {

		Thread.sleep(3000);
		String currentViewMode = getSelectedTextFromDropDown(driver, viewMode);

		// If the policy View Mode is official then endorse a policy and then go
		// to coverage tab
		if (currentViewMode.equals(rateApolicyPageDTO.viewModeOfficial)) {
			//policyEndorsement(policyNo);
			invisibilityOfLoader(driver);
			PolicyBinderPage pbp = new PolicyBinderPage(driver);
			pbp.endorsementFromActionDropDown();
			pbp.endorsePolicy(policyNo);
			Thread.sleep(2000);
			ExtentReporter.logger.log(LogStatus.INFO, "Coverage for Primary Risk is displayed");
			clickButton(driver, coverageTab, "Coverage Tab");
			Thread.sleep(3000);
			waitForElementToLoad(driver, 30, coverageList.get(0));
			// If the policy View Mode is official then select Coverage Tab
		} else if (currentViewMode.equals(rateApolicyPageDTO.viewModeWIP)) {
			clickButton(driver, coverageTab, "Coverage Tab");
			Thread.sleep(5000);
			waitForElementToLoad(driver, 30, coverageList.get(0));
		}
		// Check if the primary coverage is selected
		if (coverageList.get(0).isSelected()) {
			ExtentReporter.logger.log(LogStatus.INFO, "Primary risk is selected and coverages are displayed");
			// Select 'Coverage' tab and add Manuscript from optional forms and
			// Save
			coverageUpdatesForSingleCoverage(policyNo);
		}
		// else select primary coverage and call method to update coverage
		else {

			ExtentReporter.logger.log(LogStatus.INFO, "Primary risk is selected and coverages are displayed");
			selectValue(driver, coverageList.get(0), "Primary coverage");
			// Method to update single coverage
			coverageUpdatesForSingleCoverage(policyNo);
		}
		waitForElementToLoad(driver, 10, policyTab);
		clickButton(driver, policyTab, "Policy tab");

		return policyNo;
	}

	// Select 'Coverage' tab and add Manuscript from optional forms and Save
	public RateApolicyPage coverageUpdatesForSingleCoverage(String PolicyNo) throws Exception {
		Thread.sleep(3000);
		// Get Coverage count from list on coverage page
		for (int i = 0; i < coverageList.size(); i++) {
			// compare coverage from coverage list with coverage from excel
			// sheet
			if (coverageList.get(i).getAttribute("innerHTML").equals(rateApolicyPageDTO.coverageFromCoverageTabGrid)) {
				// Select the coverage from the list if it matches
				clickButton(driver, coverageList.get(i), rateApolicyPageDTO.coverageFromCoverageTabGrid);
				ExtentReporter.logger.log(LogStatus.INFO,
						rateApolicyPageDTO.coverageFromCoverageTabGrid + "Coverage is highlighted");
				break;
			}
		}

		// Click on 'Optional forms', navigate to pop up window and click 'Add'
		// button
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Manuscript Information Window displays");
		clickButton(driver, optionalFormBtn, "Optional Form");
		Thread.sleep(2000);
		PolicyBinderPage pbp = new PolicyBinderPage(driver);
		String policyNumber = pbp.policyNo();
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNumber + "')]")));
		ExtentReporter.logger.log(LogStatus.INFO, "Add Manuscript window displays");
		clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
		Thread.sleep(1000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNumber + "')]")));

		// Search manuscript Form from pop up window and select check box
		Thread.sleep(5000);
		for (int i = 0; i < manuscriptAddListformName.size(); i++) {
			if (manuscriptAddListformName.get(i).getAttribute("innerHTML").equals(rateApolicyPageDTO.manuscriptForm)) {
				ExtentReporter.logger.log(LogStatus.INFO, rateApolicyPageDTO.manuscriptForm + "Form is added");
				clickButton(driver, manuscriptAddListformNameChkBox.get(i),
						"check Box for " + rateApolicyPageDTO.manuscriptForm);
				break;
			}
		}
		ExtentReporter.logger.log(LogStatus.INFO, "Window closes and forms are attached to Policy");
		clickButton(driver, manuscriptAddListDoneBtn, "Done");
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNumber + "')]")));
		Thread.sleep(2000);

		// Verify that form selected from 'Add manuscript' pop up is added under
		// list
		CommonUtilities comUtil = new CommonUtilities();
		comUtil.verifyFormIsAdded(manuscriptAddedForm, rateApolicyPageDTO.manuscriptForm);

		// Save manuscript and close the window
		clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Form is saved to coverage and window closes");
		clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		Thread.sleep(5000); // Add wait using loader element
		click(driver, saveWIP, "Save WIP");
		return new RateApolicyPage(driver);
	}

	public RateApolicyPage searchPolicyRateAPolicyPage() throws Exception {
		if(searchPolicy(rateApolicyPageDTO.policyNum).equals("false"))
		{
		Thread.sleep(3000);
		AcceptFromActionDropDownwithoutBackupPolicy();
		}
		return new RateApolicyPage(driver);
	}

	public PolicyBinderPage searchPolicyPolicyBinderPage() throws Exception {
		if(searchPolicy(rateApolicyPageDTO.policyNum).equals("false"))
				{
					
				}
		return new PolicyBinderPage(driver);
	}

	public PolicyQuotePage searchPolicyPolicyQuotePage() throws Exception {
		searchPolicy(rateApolicyPageDTO.policyNum);
		return new PolicyQuotePage(driver);
	}

	public void searchBackUpPolicy() throws Exception {
		searchPolicy(rateApolicyPageDTO.backUpPolicyNum);
		PolicyBinderPage pbp = new PolicyBinderPage(driver);
		pbp.copyToQuoteFromActionDropDownwithoutBackUpPolicy(rateApolicyPageDTO.backUpPolicyNum);
	}

	// Save Rate details code.
	public RateApolicyPage saveRatedetails() throws Exception {
		click(driver, RateBtn, "Rate button");
		Thread.sleep(5000);
		switchToFrameUsingId(driver, "popupframe1");
		Thread.sleep(1000);
		selectDropdownByValue(driver, Continue_saving, rateApolicyPageDTO.productNotifyValue,
				"Continue saving without Quote");
		Thread.sleep(1000);
		click(driver, Notify_Close, "Close button");
		Thread.sleep(3000);
		return new RateApolicyPage(driver);
	}

	// Download Excel report and save in defined folder
	public String startExcelExport() throws InterruptedException, AWTException {
		clickButton(driver, Export, "Export link");
		Thread.sleep(2000);

		Robot rob = new Robot();

		rob.keyPress(KeyEvent.VK_F6);
		rob.keyRelease(KeyEvent.VK_F6);

		rob.setAutoDelay(1000);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyRelease(KeyEvent.VK_TAB);

		rob.setAutoDelay(1000);
		rob.keyPress(KeyEvent.VK_DOWN);

		rob.setAutoDelay(1000);
		rob.keyPress(KeyEvent.VK_DOWN);
		rob.keyRelease(KeyEvent.VK_DOWN);

		rob.setAutoDelay(1000);
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);

		String fileDate = comUtil.getSystemDateMMddyy_hhmmss();

		String fileNamePath = "C:\\MM_Testcase_Output\\" + fileDate + ".xlsx";
		StringSelection fileName = new StringSelection(fileNamePath);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileName, null);

		rob.keyPress(KeyEvent.VK_CONTROL); // press 'cntrl' key
		rob.keyPress(KeyEvent.VK_V); // press 'V' key

		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyRelease(KeyEvent.VK_V);
		rob.setAutoDelay(2000);
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);

		click(driver, Prem_Close, "Premium close");
		Thread.sleep(1000);

		click(driver, Exit_Ok, "OK button");
		switchToParentWindowfromframe(driver);

		rob.keyPress(KeyEvent.VK_F6);
		rob.keyRelease(KeyEvent.VK_F6);
		rob.setAutoDelay(1000);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyRelease(KeyEvent.VK_TAB);
		rob.setAutoDelay(1000);
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);
		/*
		 * try { File file = new File("C:\\MM_Testcase_Output\\"+fileDate+".xlsx");
		 * 
		 * if(file.exists()) { System.out.println("File is available at location");
		 * ExtentReporter.logger.log(LogStatus.PASS,
		 * "Excel file is available at download location."); }
		 * 
		 * }catch(Exception e) {
		 * System.out.println("File is Not available at location");
		 * ExtentReporter.logger.log(LogStatus.FAIL,
		 * "Excel file is Not available at download location."); e.printStackTrace(); }
		 */
		return fileNamePath;
	}

	// Verify file exist or not.
	public void verifyFileExists(String fileNamePath) {

		comUtil.downloadedFileExists(fileNamePath);
	}

	// Select Accept option from "Action Drop Down".
	public RateApolicyPage AcceptFromActionDropDown() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO,
				"Select Accept from the dropdown screen.Same type of policy exists for the period. Continue? message displays. Click Ok & verify Policy is editable.");
		Thread.sleep(3000);
		if (selectDropdownByValueFromPolicyActionDDL(driver, policyAction, rateApolicyPageDTO.valueOfPolicyActionAccept,
				"Policy Action").equals("false")) {
			RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
			RateAPolicyPageDTO rateApolicyPageDTO = new RateAPolicyPageDTO(TestCaseDetails.testDataDictionary);
			rateapolicypage.searchBackUpPolicy();
			selectDropdownByValueFromPolicyActionDDL(driver, policyAction, rateApolicyPageDTO.valueOfPolicyActionAccept,
					"Policy Action");
			invisibilityOfLoader(driver);
			Thread.sleep(2000);
			/*Alert alert = driver.switchTo().alert();
			alert.accept();*/
			isAlertPresent(driver);
			Thread.sleep(3000);
			Assert.assertTrue(policyPhaseBinder.isEnabled(), "Policy" + policyNo() + "is NOT Editable.");
		}
		return new RateApolicyPage(driver);
	}

	// Select Accept option from "Action Drop Down" without searching for backup
	// Policy.
	public RateApolicyPage AcceptFromActionDropDownwithoutBackupPolicy() throws Exception {
		visibilityOfElement(driver, policyAction, "Policy Action Drop Down list");
		ExtentReporter.logger.log(LogStatus.INFO,
				"Select Accept from the dropdown screen.Same type of policy exists for the period. Continue? message displays. Click Ok & verify Policy is editable.");
		Thread.sleep(3000);
		selectDropdownByValueFromPolicyActionDDL(driver, policyAction, rateApolicyPageDTO.valueOfPolicyActionAccept,
				"Policy Action");
		Thread.sleep(4000);
		Alert alert = driver.switchTo().alert();
		alert.accept();

		Assert.assertTrue(policyPhaseBinder.isEnabled(), "Policy" + policyNo() + "is NOT Editable.");
		return new RateApolicyPage(driver);
	}

	public RateApolicyPage selectValueFromActionDropDown() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>" + rateApolicyPageDTO.optionName + ".");
		selectDropdownByValue(driver, policyAction, rateApolicyPageDTO.optionValue, "Policy Action");
		return new RateApolicyPage(driver);
	}

	// This method will open Image Right app.
	public void openImageRight() throws IOException, InterruptedException {
		ExtentReporter.logger.log(LogStatus.INFO, "Opening ImageRight applciation.");
		File file = new File("C:\\Program Files (x86)\\ImageRight\\Clients\\imageright.desktop.exe");
		Desktop desktop = Desktop.getDesktop();
		desktop.open(file);
		Thread.sleep(6000);
	}

	// Screen shot is captured in last step by focusing on imageright app.
	public void ImageRightFocus() throws IOException, InterruptedException {
		// invisibilityOfLoader(driver);
		Thread.sleep(6000);
		ExtentReporter.logger.log(LogStatus.INFO, "Setting focus on ImageRight application.");
		String[] executionPath = {
				System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\ImageRight.exe" };
		Runtime.getRuntime().exec(executionPath);
		// ProcessBuilder pb = new ProcessBuilder(executionPath);
		captureScreenshot(driver, "ImageRight");
	}

	// Verify Alert is present or not.
	public RateApolicyPage isAlertPresent() throws Exception {
		try {
			Thread.sleep(3000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
			ExtentReporter.logger.log(LogStatus.INFO, alert.getText());
			Thread.sleep(3000);
		} // try
		catch (NoAlertPresentException Ex) {
			ExtentReporter.logger.log(LogStatus.INFO, "Alert is not displayed for Same policy exist.");
		} // catch
		return new RateApolicyPage(driver);
	}

	// Identify Phase displayed on Page.
	public RateApolicyPage identifyPhase() throws Exception {
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.PASS, "Verify Phase is changed to Binder.");
		//verifyValueFromField(driver, policyPhaseBinder, policyPhaseValue,"innerText","phase");
		return new RateApolicyPage(driver);
	}

	// Identify Policy number from Page.
	public String policyNo() throws InterruptedException {
		Thread.sleep(10000);
		// String profileNoLable =
		// pageHeaderForPolicyFolder.getAttribute("innerHTML");
		String profileNoLable = getText(driver, pageHeaderForPolicyFolder.get(0));
		String[] portfolioNo = profileNoLable.split(" ", 3);
		return portfolioNo[2];
	}

	// Billing setup flow code.
	public RateApolicyPage billingSetup() throws Exception {
		Thread.sleep(4000);
		invisibilityOfLoader(driver);
		String policyNo = policyNo();
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click Policy Actions-->Select Billing Setup & verify Manage Billing setup window opens.");
		Thread.sleep(1000);
		selectDropdownByValue(driver, policyAction, rateApolicyPageDTO.valueOfPolicyActionBillingSetup,
				"Policy Action");
		invisibilityOfLoader(driver);
		Thread.sleep(4000);
		// switchToFrameUsingId(driver, "popupframe1");
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		getPageTitle(driver, manageBillingSetupPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Payment plan dropdown:" + rateApolicyPageDTO.paymentPlanValue
				+ ". Verify " + rateApolicyPageDTO.paymentPlanValue + " billing plan is selected");
		// quote
		selectDropdownByValue(driver, paymentPlan, rateApolicyPageDTO.paymentPlanValue, "Payment Plan");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Save] Billing account is setup");
		clickButton(driver, billingSetupSaveBtn, "Save Button");
		Thread.sleep(20000);
		invisibilityOfLoader(driver);
		switchToParentWindowfromframe(driver);
		visibilityOfElement(driver,rateBtn,"Rate Button");
		return new RateApolicyPage(driver);
	}

	// Coverage Details flow.
	public RateApolicyPage coverageDetailsSelect() throws Exception {
		try {
			invisibilityOfLoader(driver);
			ExtentReporter.logger.log(LogStatus.PASS,
					"Click Coverage tab & verify Coverage for Primary Risk is displayed.");
			clickButton(driver, coverageTab, "Coverage");
			invisibilityOfLoader(driver);
			Thread.sleep(2000);
			ExtentReporter.logger.log(LogStatus.PASS, "Select Primary risk in DDL if not selected.");
			if (verifyValueFromField(driver, primaryRisk, rateApolicyPageDTO.listDDLValue, "value",
					dropDownNameLevel) == true) {
				ExtentReporter.logger.log(LogStatus.PASS,
						"Primary risk in" + "'" + "Level" + "'" + "DDL is already selected.");
			} else {
				ExtentReporter.logger.log(LogStatus.PASS,
						"Primary risk in" + "'" + "Level" + "'" + "DDL is NOT selected.");
				selectDropdownByValue(driver, listDDL, rateApolicyPageDTO.listDDLValue, dropDownNameLevel);
			}

		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, "Can not click on Coverage tab.");
		}
		return new RateApolicyPage(driver);
	}

	// Below method is to execute coverage details select method and return
	// Cincom page object.
	public CincomPage coverageDetailSelectForCinCom() throws Exception {
		coverageDetailsSelect();
		return new CincomPage(driver);
	}

	// Coverage updates flow.
	public RateApolicyPage coverageUpdates(String PolicyNo) throws Exception {
		invisibilityOfLoader(driver);
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.visibilityOf(optionalFormBtn));
		// Thread.sleep(3000);
		for (int j = 0; j < rateApolicyPageDTO.coverage.size(); j++) {
			// Code to select Coverage.
			try {
				WebElement coverageType = driver
						.findElement(By.xpath("//div[text()='" + rateApolicyPageDTO.coverage.get(j) + "']"));
				clickButton(driver, coverageType, "Coverage Type");
			} catch (Exception e) {
				ExtentReporter.logger.log(LogStatus.INFO,
						rateApolicyPageDTO.coverage.get(j) + " Coverage is NOT found under coverage section.");
				Assert.assertTrue(false,
						rateApolicyPageDTO.coverage.get(j) + " Coverage is NOT found under coverage section.");
			}
			ExtentReporter.logger.log(LogStatus.INFO,
					"Click [Optional Forms] & verify Manuscript Information window displays.");
			Thread.sleep(2000);
			clickButton(driver, optionalFormBtn, "Optional Form");
			invisibilityOfLoader(driver);
			Thread.sleep(4000);
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
			// Code to to verify manuscript list is displayed and delete existing selected
			// coverage form.
			if (manuscriptList.isDisplayed()) {
				ExtentReporter.logger.log(LogStatus.INFO,
						"Delete current Indication form, Are you sure you want to delete this? Click Ok & verify Form is deleted.");
				clickButton(driver, manuscriptPageDeleteBtn, "Manu script Delete");
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				ExtentReporter.logger.log(LogStatus.INFO, "Click [Add] & verify Add Manuscript window dispalys.");
				clickButton(driver, manuscriptPageAddBtn, "Manu script Add");

			} else {
				ExtentReporter.logger.log(LogStatus.INFO, "Click [Add] & verify Add Manuscript window dispalys.");
				clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
			}
			// Code to select Phase.
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
			Thread.sleep(3000);
			int coverageIdx = 0;
			try {
				WebElement coverageFormType = driver
						.findElement(By.xpath("//div[text()='" + rateApolicyPageDTO.phase.get(j)
								+ "']/parent::td/preceding-sibling::td/input[@name='chkCSELECT_IND']"));
				clickButton(driver, coverageFormType, "Coverage Form Type");
			} catch (Exception e) {
				ExtentReporter.logger.log(LogStatus.INFO,
						rateApolicyPageDTO.phase.get(j) + " Coverage is NOT found on Select Coverage Type window.");
				Assert.assertTrue(false,
						rateApolicyPageDTO.phase.get(j) + " Coverage is NOT found on Select Coverage Type window.");
			}
			clickButton(driver, manuscriptAddListDoneBtn, "Done");
			Thread.sleep(4000);
			switchToParentWindowfromframe(driver);
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
			ExtentReporter.logger.log(LogStatus.INFO, "Enter additional text: " + rateApolicyPageDTO.phase.get(j)
					+ " form added  Click [Save] and Click [Close].");
			enterTextIn(driver, addText, rateApolicyPageDTO.phase.get(j) + " form added.", "Aditional Text");
			switchToParentWindowfromframe(driver);
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
			clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
			Thread.sleep(2000);
			clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
			switchToParentWindowfromframe(driver);
		}
		return new RateApolicyPage(driver);
	}

	public String verifyProductNotifyWindowDisplayed(String PolicyNo) {
		try {
			Thread.sleep(10000);
			WebDriverWait wait = new WebDriverWait(driver, High);
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
			// Thread.sleep(3000);
			invisibilityOfLoader(driver);
			List<WebElement> getPageTitleFromPage = driver.findElements(By.xpath("//div[@class='pageTitle']"));
			try {
				//wait.until(ExpectedConditions.visibilityOf(productNotifyDropDown));
				int i = 0;
				for (i = 0; i < getPageTitleFromPage.size(); i++) {
					if ((getPageTitleFromPage.get(i).getAttribute("innerHTML").trim().equals("Product Notify"))) {
						ExtentReporter.logger.log(LogStatus.PASS,
								getPageTitleFromPage.get(i).getAttribute("innerHTML").trim()
										+ " is sucessfully displayed.");
						prodNotFlag = "true";
						break;
					}

					else {
						prodNotFlag = "false";
					}
				}
			} catch (Exception e) {
				prodNotFlag = "false";
			}
		} catch (Exception e) {
			prodNotFlag = "false";
		}
		return prodNotFlag;
	}

	public RateApolicyPage refreshCurrentPage(WebDriver driver) throws Exception {
		refreshAPage(driver);
		Thread.sleep(6000);
		if (isAlertPresent(driver) == false) {
			ExtentReporter.logger.log(LogStatus.INFO, "Alert not present.");
		}
		Thread.sleep(12000);
		// visibilityOfElement(driver, pageHeaderForPageTitle, "Page Title");

		return new RateApolicyPage(driver);
	}

	// If Product Notify Window appears then it will switch to window and
	// select 'Yes' from that window and close window
	public void handleProducNotifyWindow(String policyNo) {
		if (verifyProductNotifyWindowDisplayed(policyNo).equals("true")) {
			try {
				ExtentReporter.logger.log(LogStatus.PASS, "Product Notify Window is displayed to user.");
				selectDropdownByValue(driver, productNotifyDropDown, rateApolicyPageDTO.productNotifyValue,
						"product notify");
				Thread.sleep(3000);
				clickButton(driver, prodNotifyClose, "Product Notify Close");
				ExtentReporter.logger.log(LogStatus.PASS, " Yes selected from Product Notify dorp down.");
			} catch (Exception e) {
				ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window is NOT dispalyed to user.");
			}
			// If Product Notify Window does not appear it will log info in
			// report and move ahead.
		} else {
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window is NOT dispalyed to user.");
		}
	}

	// Rate a functionality flow.
	public PolicyQuotePage rateFunctionality(String policyNo) throws Exception {
		Thread.sleep(5000);
		invisibilityOfLoader(driver);
		// TODO - Need to confirm where we have to compare premium value.
		// refreshCurrentPage(driver);
		// Get value of Written Premium from Policy page to compare with Written
		// premium
		// from View premium window.

		// ExtentReporter.logger.log(LogStatus.PASS, "Captured value of Written
		// Premium
		// from Policy Page.");
		// String writtenPremiumAmount=
		// writtenPremium.getAttribute("innerHTML").trim();

		ExtentReporter.logger.log(LogStatus.INFO,
				"Click rate button in center of screen. Rate window validates and save, View Premium pop up window displays with correct rates");
		clickButton(driver, rateBtn, "Rate Tab");
		Thread.sleep(10000);
		invisibilityOfLoader(driver);
		// invisibilityOfProcessesingWindow(driver);
		// If Product Notify Window appears then it will switch to window and
		// select 'Yes' from that window and close window
		if (verifyProductNotifyWindowDisplayed(policyNo).equals("true")) {
			try {
				selectDropdownByValue(driver, productNotifyDropDown, rateApolicyPageDTO.productNotifyValue,
						"product notify");
				Thread.sleep(1000);
				clickButton(driver, prodNotifyClose, "Product Notify Close");
				ExtentReporter.logger.log(LogStatus.PASS,
						"Product Notify Window is dispalyed to user and Yes selected from Product Notify dorp down.");
				Thread.sleep(6000);
				invisibilityOfLoader(driver);
			} catch (Exception e) {
				ExtentReporter.logger.log(LogStatus.WARNING, "Product Notify Window is NOT dispalyed to user.");
			}
			// If Product Notify Window does not appear it will log info in
			// report and move ahead.
		} else {
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window is NOT dispalyed to user.");
		}
		switchToParentWindowfromframe(driver);
		Thread.sleep(4000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		// Get the number of last row of Written premium from View premium window.
		int lastRowOfWrittenPremium = cwWrittenPremiumOnViewPremium.size() - 1;
		// Close the View Premium window
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Close]");
		clickButton(driver, closeBtnOnViewPremiumPopup, "Close");
		invisibilityOfLoader(driver);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click on Ok button to save policy in Work in Progress status[WIP]");
		clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
		invisibilityOfLoader(driver);
		switchToParentWindowfromframe(driver);
		return new PolicyQuotePage(driver);
	}

	// PDF verification flow.
	public RateApolicyPage pdfVerify() throws Exception {
		clickButton(driver, previewBtn, "Preview");
		Thread.sleep(6000);
		clickButton(driver, closePdf, "Close PDF");
		return new RateApolicyPage(driver);
	}

	// Save Option flow.
	public void saveOption() throws Exception {
		String policyNo = "";
		Thread.sleep(2000);
		clickButton(driver, saveOptionBtn, "Save Option");
		ExtentReporter.logger.log(LogStatus.PASS, "Click Save Options.");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		selectDropdownByValue(driver, saveAsDropDown, rateApolicyPageDTO.saveAsPolicyValue, "Save Option");
		ExtentReporter.logger.log(LogStatus.PASS, "Select Official.");
		clickButton(driver, saveOptionOkBtn, "Save");
		ExtentReporter.logger.log(LogStatus.PASS, "Click [OK]");
		Thread.sleep(6000);
		try {
			switchToParentWindowfromframe(driver);
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
			selectDropdownByValue(driver, productNotifyDropDown, rateApolicyPageDTO.productNotifyValue,
					"product notify");
			Thread.sleep(1000);
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selection from Product Notify dorp down.");
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, "Product Notify Window is NOT dispalyed to user.");
		}
		Thread.sleep(5000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + policyNo + "')]")));
		clickButton(driver, Exit_Ok, "Exit Ok");
		ExtentReporter.logger.log(LogStatus.PASS, "Click [OK]");
	}

}

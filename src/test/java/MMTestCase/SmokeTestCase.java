package MMTestCase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mm.browsers.BrowserTypes;
import com.mm.dto.FinancePageDTO;
import com.mm.dto.HomePageDTO;
import com.mm.dto.LoginPageDTO;
import com.mm.dto.PolicyIndicationPageDTO;
import com.mm.dto.PolicyQuotePageDTO;
import com.mm.pages.CISPage;
import com.mm.pages.ClaimsPage;
import com.mm.pages.FinancePage;
import com.mm.pages.FindPolicyPage;
import com.mm.pages.HomePage;
import com.mm.pages.LoginPage;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyIndicationPage;
import com.mm.pages.PolicyQuotePage;
import com.mm.pages.PolicySubmissionPage;
import com.mm.pages.QuickAddOrganisation;
import com.mm.pages.RateApolicyPage;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.IntegrateRallyRestAPI;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

public class SmokeTestCase extends BrowserTypes {

	// Global Assignment/initialization of variables.
	IntegrateRallyRestAPI iR;
	WebDriver driver = BrowserTypes.getDriver();
	public static HashMap<String, List<String>> testDataMap;

	String serverURL = "";
	String username = "";
	String password = "";
	String workspace = "";
	String project = "";
	String testcaseFormattedID = ""; // method.getName();
	String buildNumber = "";
	String notes = "";
	double duration = 0.0; // get from extent test
	String verdict = "Pass";
	String userRef = "";

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws IOException, URISyntaxException {
		// Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");

		/*
		 * Properties configProperties = new Properties(); InputStream inputFile
		 * = new FileInputStream("config.properties");
		 * configProperties.load(inputFile);
		 * 
		 * // Temporary variables declared, this would be fetched from
		 * properties // file. serverURL =
		 * configProperties.getProperty("ServerURL"); username =
		 * configProperties.getProperty("Username"); password =
		 * configProperties.getProperty("Password"); workspace =
		 * configProperties.getProperty("Workspace"); project =
		 * configProperties.getProperty("Project"); testcaseFormattedID =
		 * "TC42249"; // method.getName(); buildNumber =
		 * configProperties.getProperty("BuildNumber"); notes =
		 * configProperties.getProperty("DefaultNote"); duration = 0.0; // get
		 * from extent test // "Blocked", "Deferred", "Enhancement", "Error",
		 * "Fail", "Hold", "In // Progress", "Inconclusive", "Invalid", "Out of
		 * Scope", "Pass", // "Waiting for Policy" verdict = "Pass";
		 * 
		 * iR = new IntegrateRallyRestAPI(); password =
		 * iR.decodeString(password); // This should go in Before Suite method
		 * userRef = iR.getRallyUserDetails(serverURL, username, password);
		 */
	}

	// Extent report initialization before every test case.
	@BeforeMethod(alwaysRun = true)
	public void Setup(Method method) throws Exception {
		ExtentReporter.logger = ExtentReporter.report.startTest(method.getName());
		System.out.println("==============================================");
		System.out.println(method.getName() + " test case execution started.");
		System.out.println("==============================================");
		testcaseFormattedID = method.getName();
		// Code to populate HashMap from excel
		// Instantiate ExcelUtil and call testData and fill a HashMap
		// testDataMap
		ExcelUtil excelUtil = new ExcelUtil();
		testDataMap = excelUtil.testData(method.getName());
	}

	
	@Test(description = "Hospital - Add multiple risks", groups = { "Smoke Test" })
	public void TC42244() throws Exception {
			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
			PolicyBinderPage policyBinderPage = new PolicyBinderPage(driver);
			PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
			PolicyIndicationPage policyIndicationPage= new PolicyIndicationPage(driver);
			PolicySubmissionPage policySubmissionPage = new PolicySubmissionPage(driver); 
			PolicyQuotePageDTO policyQuotePageDTO = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password)
			.navigateToPolicyPageUsingHeaderPolicyLink()
			.searchPolicyPolicyBinderPage()
			.copyToQuoteFromActionDropDown(policyBinderPage.policyNo());
			policySubmissionPage.copyFromPolicyActionDropDown(policyBinderPage.policyNo());
			policyQuotePage.changePolicyPhase(policyQuotePageDTO.policyPhaseValue);
			String policyNo= policyBinderPage.policyNo();
			policyIndicationPage.selectRiskTab()
			.selectRiskTypeFromPopupWindow(policyNo)
			.searchAndSelectRisk()
			.addRiskInformation()
			.selectCoverageTab()
			.selectCoverageFromCoverageList()
			.addRetroactiveDate();
	}
	
	
	//@Test(description = "Claims - Verify that user is allowed to change Billing Parameter for an Existing Account", groups = {
	//	 "Smoke Test" })
	public void TC42403() throws Exception {
			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password)
			.navigateToFinancePageFromHeaderLink()
			.searchAccountUsingSearchCriteria()
			.selectLastAccountFromAccountList()
			.maintainAccount()
			.saveAccountInformation()
			.captureSaveScreenshotofMantainAccountpage();
		}
	
	//TODO-Verify info statements
	//@Test(description = "FM - Hospital Verify FM Installment", groups = { "Smoke Test" })
	public void TC42246()throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		FinancePage financePage = new FinancePage(driver);
		FinancePageDTO financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
		PolicyIndicationPage policyIndicationPage = new PolicyIndicationPage(driver);
		RateApolicyPage rateApolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password)
		.navigateToFinanceHomePage()
		.searchPolicyOnFinanceHomePage()
		.openFirstAccount()
		.onDemandInvoice()
		.exportExcelSheet(financePageDTO.onDemandInvoiceInstallmentExcel)
		.selectReceivableTabAndExportExcel(financePageDTO.onDemandInvoiceInstallementBeforeExcel)
		.selectAccountTabInvoicesButtonAndExportExcel()
		.navigateToPolicyPageThroughPolicyHeaderLink()
		.policySearchUsingSearchCriteria();
		String cellValue=financePage.readDataFromExcelSheet(financePageDTO.dataSheetName,financePageDTO.testDataColumnName,financePageDTO.dataRowNumber,
				financePageDTO.exportedExcelSheetName);
		financePage.writeDataInExcelSheet(cellValue, financePageDTO.TCSheetNumber,financePageDTO.testDataColumnheader,
				financePageDTO.rowNumber);
		String policyNum = rateApolicyPage.policyNo();
		String nextDay=financePage.nextDayOfDueDate();
		financePage.policyEndorsementWithDate(policyNum,nextDay)  //Delete WIP if Endorsement is not shown in policy Action
		.selectCoverageTab();
		financePage.selectCoverageFromGridList()
		.selectAddCoverageButton();
		policyIndicationPage.selectCoverageFromPopupListAddDatePremium(nextDay).closeAddCoverageWindow();
		rateApolicyPage.refreshCurrentPage(driver).rateFunctionality(policyNum).clickPreviewTab(policyNum).savePDF();
		policyQuotePage.saveOptionOfficial(policyNum);
		homePage.navigateToFinancePageFromHeaderLink()
		.searchPolicyOnFinanceHomePage()
		.openFirstAccount()
		.exportExcelSheet(financePageDTO.excelNameAddCoverageInstallment)
		.selectReceivableTabAndExportExcel(financePageDTO.excelNameOnDemandInvoiceInstallmentAfter);
		
		//TODO- upload all excels and pdf to rally.
	}
	
	

	//@Test(description = "Claims - Enter Transactions", groups = { "Smoke Test" })
	public void TC42252() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password)
		.navigateToClaimsPageFromHomePageLink()
		.searchClaim()
		.openTransactionTab()
		.addTransactionDataAndSaveTransaction();
	}

	
	
	//TODO-Add values for parameters in excel
	//@Test(description = "FM - Hospital Verify On Demand Invoice, Create Batch and Post Batch", groups = {
			//"Smoke Test" })
	public void TC42250() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		FinancePage financePage = new FinancePage(driver);
		FinancePageDTO financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinanceHomePage()
				.searchPolicyOnFinanceHomePage().openFirstAccount().onDemandInvoice().exportExcelSheet("").getInvoiceAmountFromExcel().cashEntry().batchFunction()
				.validateBatch().postBatchFunctionality().donwloadFinalSheetBySearchingAccountNo();
	}
	
	//QA test case.
	//@Test(description = "QA FM - Hospital Verify On Demand Invoice, Create Batch and Post Batch-Complete", groups = {
		//	"Smoke Test" })
	public void TC43783() throws Exception {
		TC42250();
	}


	//@Test(description = "Verify CIS Page Displays", groups = { "Smoke Test" })
	public void TC42253() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().searchAndSelectAClientName()
				.verifyPagesHavingMenuOnPersonPageAreDisplayed().verifyPagesWithoutSubMenu();

	}

	//@Test(description = "Hospital Rate", groups = { "Smoke Test" })
	public void TC42239() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink();
		HomePage homepage = new HomePage(driver);
		String policyNum = homepage.policySearchUsingSearchCriteria();
		rateapolicyPage.rateFunctionality(policyNum);
	}
	
	//@Test(description= " QA Hospital Rate",groups = { "Smoke Test" })
	public void TC42155() throws Exception{
				
				TC42239();
	}

	
	//@Test(description= "Verify Add Organization",groups = { "Smoke Test" })
	public void TC42404() throws Exception {
		LoginPage loginpage = new LoginPage(driver);
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().navigateToAddOrgPage();
		CISPage cisPage = new CISPage(driver);
		String organizationName = cisPage.addOrganizationInformation();
		cisPage.addOrgAddress().selectZipCode().addPhoneNumber().searchRecentlyAddedOrganisation(organizationName);
	}

	
	//@Test(description= " QA CIS Add Organization",groups = { "Smoke Test" })
	public void TC42203() throws Exception{
			
			TC42404();
	}
	
	
	//@Test(description = "Hospital Verify Interactive Form", groups = { "Smoke Test" })
	public void TC42247() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink().searchPolicyRateAPolicyPage();
		String policyNo = rateapolicypage.policyNo();
		rateapolicypage.coverageDetailSelectForCinCom().cincomFlow(policyNo).rateFunctionality(policyNo)
				.clickPreviewTab(policyNo).savePDF().verifyPdfContent();
	}

	// QA HPL Test Case.
	// @Test(description ="QA Hospital Verify Interactive Form - Complete",
	// groups = { "Smoke Test" })
	public void TC42219() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink().searchPolicyRateAPolicyPage();
		String policyNo = rateapolicypage.policyNo();
		rateapolicypage.coverageDetailSelectForCinCom().cincomFlow(policyNo).rateFunctionality(policyNo)
				.clickPreviewTab(policyNo).savePDF().verifyPdfContent();
		// TODO - CINCOM page in QA is different than BTS page hence need
		// confirmation from onshore team.
	}

  
  // @Test(description="Hospital Verify Attach Form", groups = { "Smoke
	// Test"})
	public void TC42399() throws Exception {

		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage();
		HomePage homepage = new HomePage(driver);
		String policyNum = homepage.policySearchUsingSearchCriteria();
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		String policyNumber = rateapolicyPage.checkPolicyViewModeAndUpdateCoverage(policyNum);
		rateapolicyPage.rateFunctionality(policyNumber).clickPreviewTab(policyNumber).savePDF().verifyPdfContent();
	}

	// @Test(description = "HPL - Binder", groups = { "Smoke Test" })
	public void TC42242() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage().AcceptFromActionDropDown().isAlertPresent().identifyPhase()
				.billingSetup().coverageDetailsSelect();
		String policyNumber = rateapolicyPage.policyNo();
		rateapolicyPage.coverageUpdates(policyNumber);
		rateapolicyPage.rateFunctionality(policyNumber).clickPreviewTab(policyNumber).savePDF().verifyPdfContent().saveOption(policyNumber);
	}

	// @Test(priority=1, description="Hospital Claim - Verify Change Claim
	// Status", groups ={ "Smoke Test" })
	public void TC42405() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		PolicyBinderPage policybinderpage = null;
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
		policybinderpage = new PolicyBinderPage(driver);
		// String ClaimNumber = rateapolicyPage.policyNo();
		policybinderpage.navigatetoClaimsPage().searchClaim().statusChange(rateapolicyPage.policyNo());
	}

	// @Test(description="Hospital Issue Policy Forms", groups = { "Smoke Test" })
	public void TC42665(String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromPolicyBinderPage()
				.searchPolicyPolicyBinderPage();

		PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
		String policyNumber = policybinderpage.policyNo();

		policybinderpage.endorsementFromActionDropDown().endorsePolicy(policyNumber).identifyPhase()
				.rateFunctionality(policyNumber).saveOption(policyNumber).exit_SaveOption().clickPreviewTab(policyNumber).savePDF()
				.verifyPdfContent();
	}

	//@Test(description="Hospital Verify Image Right", groups = { "Smoke Test"})
	public void TC42243() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		FindPolicyPage findapolicypage = new FindPolicyPage(driver);
		lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink();

		findapolicypage.searchFromFindPolicyPage().selectValueFromActionDropDown();
		// TODO - Verify image right window is opened successfully after
		// selecting image right option from action drop down.
	}


	//@Test(description = "Verify Hospital Preview Forms", groups = { "Smoke Test" })
	public void TC42240() throws Exception {
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink().searchPolicyRateAPolicyPage();

		String PolicyNo = rateapolicypage.policyNo();
		rateapolicypage.policyEndorsement(PolicyNo).rateFunctionality(PolicyNo).clickPreviewTab(PolicyNo).savePDF()
				.verifyPdfContent();
	}

	// QA test case.
	// @Test(description = "QA Hospital Verify Preview Forms", groups = { "Smoke
	// Test" })
	public void TC42215() throws Exception {
		TC42240();
	}

	// @Test(description = "Hospital Create Claim", groups = { "Smoke Test" })
	public void TC42666() throws Exception {

		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).naviagetToPolicyFromHeaderLink().searchPolicyRateAPolicyPage();
		String clientID = policybinderpage.getClientId();
		policybinderpage.verifyPhase().navigatetoClaimsPage().getPatientDetails().enterDataOnClaimsPage(clientID);
	}


	//@Test(description="Hospital Renewal", groups = {"Smoke Test"})
	public void TC42400() throws Exception {
		//TODO- Get policy number//09100510, 09100511, 09100512, 09100514
		
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
		PolicyQuotePageDTO policyquotepageDTO = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
		PolicyBinderPage policyBinderPage = new PolicyBinderPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage().selectPolicyActionAndAddDescription().save_CaptureTransactionDetails();
				String PolicyNo = policyBinderPage.policyNo();
				policyQuotePage.saveOptionAndCaptureTransactionDetails(policyquotepageDTO.saveAsPolicyDDLValue,PolicyNo);
				String policyNum = policyBinderPage.policyNo();
				policyQuotePage.saveOptionOfficial(policyNum)
				.applyChanges()
				.changePolicyPhase(policyquotepageDTO.policyPhaseValue)
				.saveOptionOfficial(PolicyNo);
	}

	
	// @Test(description = "Hospital Quote", groups = { "Smoke Test" })
	public void TC42238() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyquotepage;
		PolicyQuotePageDTO policyquotepagedto = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);

		lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		loginpage = new LoginPage(driver);

		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage().CopyOptionFromActionDropDown().changePolicyPhase(policyquotepagedto.quotePhaseValue)
				.coverageDetailsSelect();

		policyquotepage = new PolicyQuotePage(driver);
		String policyNumber = policyquotepage.policyNo();

		rateapolicyPage.coverageUpdates(policyNumber);
		policyquotepage.rateFunctionality(policyNumber).saveOption(policyquotepagedto.secondSaveAsPolicyDDLValue,policyNumber)
				.exit_SaveOption().clickPreviewTab(policyNumber).savePDF().verifyPdfContent();
	}

	//TODO- make info statements generic
	//@Test(description = "Hospital Copy to Quote", groups = { "Smoke Test" })
	public void TC42245() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
		RateApolicyPage rateApolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
		PolicyQuotePageDTO policyquotepagedto= new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
		
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage();

		String policyNumber = policybinderpage.policyNo();

		policybinderpage.copyToQuoteFromActionDropDown(policyNumber).copyFromPolicyActionDropDown(policyNumber)
				.changePhaseToIndicationAndAddQuoteDescription();
		rateApolicyPage.rateFunctionality(policybinderpage.policyNo());
		policyQuotePage.saveOptionOfficial(policyNumber).CopyOptionFromActionDropDown().changePolicyPhase(policyquotepagedto.quotePhaseValue);
		rateApolicyPage.rateFunctionality(policybinderpage.policyNo());
		policyQuotePage.saveOptionOfficial(policyNumber);
		rateApolicyPage.AcceptFromActionDropDown().billingSetup().refreshCurrentPage(driver)
				.rateFunctionality(policybinderpage.policyNo()).saveOptionOfficial(policyNumber);
		policybinderpage.endorsementFromActionDropDown().endorsePolicy(policyNumber)
				.rateFunctionality(policybinderpage.policyNo());
		policyQuotePage.clickPreviewTab(policybinderpage.policyNo()).savePDF();
		policyQuotePage.saveOptionOfficial(policyNumber);
	}

	//BTS Test Case
	//@Test(testName = "HospitalIndication", groups = { "Smoke Test" })
	public void TC42249() throws Exception { 
		String Empty="";
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		HomePageDTO homePageDTO = new HomePageDTO(TestCaseDetails.testDataDictionary);
		PolicyIndicationPage policyindicationpage = new PolicyIndicationPage(driver);
		PolicyIndicationPageDTO hospitalIndicationDTO = new PolicyIndicationPageDTO(TestCaseDetails.testDataDictionary);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);

		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().create_New();
		String ParentWindow = homepage.create_Quote();
		homepage.searchEntity(homePageDTO.lastOrgName,Empty).selectEntity(ParentWindow).selectPolicyTypeForBTS().updatePolicyDetails();

		List<WebElement> firstFrame = policyindicationpage.open_Underwriter();

		policyindicationpage.add_Underwriter(firstFrame).closeUnderwriter().addAgent().addRiskInformation()
				.selectCoverageTab().selectAddCoverageButton().selectCoverageFromPopupListAddDatePremium(hospitalIndicationDTO.retroDate.get(0))
				.closeAddCoverageWindow()
				.selectCoverageFromGridList().addCoverageClass();

		String PolicyNo = policyindicationpage.policyNo();

		policyindicationpage.coverageUpdates(PolicyNo).openLimitSharingTab(PolicyNo).addSharedGroup(PolicyNo)
				.closeLimitSharingtab().rateFunctionality(PolicyNo);
		policyQuotePage.clickPreviewTab(PolicyNo).savePDF().verifyPdfContent();
		policyQuotePage.saveOptionOfficial(PolicyNo);
		//TODO- Note policy number
	}
	
	// QA Test case 
	//@Test(testName = "HospitalIndication", groups = { "Smoke Test" })
		public void TC43768() throws Exception{ 

			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			HomePage homepage = new HomePage(driver);
			HomePageDTO homePageDTO = new HomePageDTO(TestCaseDetails.testDataDictionary);
			String Empty="";
			PolicyIndicationPage policyindicationpage = new PolicyIndicationPage(driver);
			PolicyIndicationPageDTO hospitalIndicationDTO = new PolicyIndicationPageDTO(TestCaseDetails.testDataDictionary);
			PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);

			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().create_New();
			String ParentWindow = homepage.create_Quote();
			homepage.searchEntity(homePageDTO.lastOrgName,Empty).selectEntity(ParentWindow).selectPolicyTypeForQA().updatePolicyDetails();

			List<WebElement> firstFrame = policyindicationpage.open_Underwriter();

			policyindicationpage.add_Underwriter(firstFrame).closeUnderwriter().addAgent().addRiskInformation()
					.selectCoverageTab().selectAddCoverageButton().selectCoverageFromPopupListAddDatePremium(hospitalIndicationDTO.retroDate.get(0)).closeAddCoverageWindow()
					.selectCoverageFromGridList().addCoverageClass();

			String PolicyNo = policyindicationpage.policyNo();

			policyindicationpage.coverageUpdates(PolicyNo).openLimitSharingTab(PolicyNo).addSharedGroup(PolicyNo)
					.closeLimitSharingtab().rateFunctionality(PolicyNo);
			policyQuotePage.clickPreviewTab(PolicyNo).savePDF().verifyPdfContent();
			policyQuotePage.saveOptionOfficial(PolicyNo);
		}

	
	// DTO code is not implemented as this is not part of scope.
	// @Test(description="Rate a policy ",groups = { "Smoke Test" })
	public void TC42239(String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage().saveRatedetails().startExcelExport();
	}

	// DTO code is not implemented as this is not part of scope.
	// @Test(testName="EndorsePolicy",groups = { "Smoke Test" })
	public void TC42530(Method method, String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		loginpage = new LoginPage(driver);
		String PolicyNo = "9865321";
		loginpage.loginToeOasis(UserName, PassWord).navigateToPolicyPageFromPolicyBinderPage()
				.searchPolicyPolicyBinderPage().endorsePolicy(PolicyNo).rateFunctionality(PolicyNo).saveOption(PolicyNo)
				.clickPreviewTab(PolicyNo).savePDF().verifyPdfContent();
	}

	// DTO code is not implemented as this is not part of scope.
	// This is additional test, removed later from rally
	// @Test(priority=2, testName="VerifyExistingPolicy",groups = { "Smoke Test"
	// })
	public void TC42536() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis("vthor", "M@G5").navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage();
	}

	// DTO code is not implemented as this is not part of scope.
	// @Test(description= "Quick_Add_Organisation",groups = { "Smoke Test" })
	public void Quick_Add(String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		QuickAddOrganisation quickaddorganisation;
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord).navigateToCISPage();
		quickaddorganisation = new QuickAddOrganisation(driver);
		quickaddorganisation.navigate_To_Add_Org_Window().add_Org_Information().add_Org_Address().selectZipCode()
				.add_Phone_Number();
	}

	// @Test(description = "FM - Hospital Verify Credit Applications", groups =
	// {"Smoke Test" })
	public void TC42248() throws Exception {

		FinancePage financepage = new FinancePage(driver);
		RateApolicyPage rateAPolicyPage = new RateApolicyPage(driver);
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		FinancePageDTO financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinancePageFromHeaderLink()
				.receivableDownload(financePageDTO.CreditInstallmentBeforeFileName).naviagetToPolicyFromHeaderLink()
				.searchPolicyRateAPolicyPage().coverageDetailsSelect();
		financepage.selectUMBCoverage().selectCancelFromPolicyActionDDL().rateFunctionality().openPDF(rateAPolicyPage.policyNo()).savePDF();
		financepage.savePolicyAsWIP().navigateToFinancePageFromHeaderLink().searchPolicyOnFinanceHomePage()
				.openFirstAccount().downloadExcel(financePageDTO.CancelledCoverageTransactionFileName)
				.receivableDownload(financePageDTO.CreditInstallmentAfterFileName);
	}

	//@Test(description = "FM - Verify that user can create an account in FM", groups = { "Smoke Test" })
	public void TC42251() throws Exception {

		FinancePage financepage = new FinancePage(driver);
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		FinancePageDTO financePagedto = new FinancePageDTO(TestCaseDetails.testDataDictionary);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinancePageFromHeaderLink().newAccountOpen()
				.entitySelectSearch().selectAddress().saveAccountDetails().captureSaveScreenshotofMantainAccountpage();
	}

	@AfterMethod(alwaysRun = true)
	public void logoffFromAppclication(ITestResult result)
			throws IOException, InterruptedException, URISyntaxException {
		// homepage.logoutFromeOasis();
		ExtentReporter.report.endTest(ExtentReporter.logger);

		if (ITestResult.FAILURE == result.getStatus()) {
			// verdict = "Fail";
			String ScreenshotPath = CommonUtilities.captureScreenshot(driver, result.getName());
			String imagePath = ExtentReporter.logger.addScreenCapture(ScreenshotPath);
			ExtentReporter.logger.log(LogStatus.FAIL, result.getName(), imagePath);
			ExtentReporter.logger.log(LogStatus.FAIL, result.getThrowable());

		} else if (ITestResult.SUCCESS == result.getStatus()) {
			// verdict = "Pass";
			String ScreenshotPath = CommonUtilities.captureScreenshot(driver, result.getName());
			String imagePath = ExtentReporter.logger.addScreenCapture(ScreenshotPath);
			ExtentReporter.logger.log(LogStatus.PASS, result.getName(), imagePath);
			ExtentReporter.logger.log(LogStatus.INFO, "User is logged out of applciation");
			ExtentReporter.logger.log(LogStatus.PASS, result.getName());
		} else if (ITestResult.SKIP == result.getStatus()) {
			// verdict = "Hold";
			ExtentReporter.logger.log(LogStatus.SKIP, result.getName());
		}
		ExtentReporter.report.flush();
		// "Blocked", "Deferred", "Enhancement", "Error", "Fail", "Hold", "In
		// Progress", "Inconclusive", "Invalid", "Out of Scope", "Pass",
		// "Waiting for Policy"

		/*
		 * iR.updateResultsInRally(serverURL, username, password, workspace,
		 * project, testcaseFormattedID.toUpperCase(), buildNumber, notes,
		 * userRef, duration, verdict);
		 */

		Thread.sleep(2000);
		// driver.close();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		// ExtentReporter.report.close();
		// driver.quit();
	}
}
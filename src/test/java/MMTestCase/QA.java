package MMTestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.WriteAbortedException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

import com.mm.browsers.BrowserTypes;
import com.mm.dto.FinancePageDTO;
import com.mm.dto.HomePageDTO;
import com.mm.dto.LoginPageDTO;
import com.mm.dto.PolicyIndicationPageDTO;
import com.mm.dto.PolicyQuotePageDTO;
import com.mm.listeners.RetryListner;
import com.mm.pages.CISPage;
import com.mm.pages.FinancePage;
import com.mm.pages.FindPolicyPage;
import com.mm.pages.HomePage;
import com.mm.pages.LoginPage;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyIndicationPage;
import com.mm.pages.PolicyQuotePage;
import com.mm.pages.PolicySubmissionPage;
import com.mm.pages.RateApolicyPage;
import com.mm.utils.CommonAction;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.FailedTCRerun;
import com.mm.utils.IntegrateRallyRestAPI;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import BaseClass.BaseTest;

public class QA extends ExtentReporter {

	// Global Assignment/initialization of variables.
	IntegrateRallyRestAPI iR;
	public static WebDriver driver;
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
	String ExcelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx";

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws IOException, URISyntaxException {
		Process process = Runtime.getRuntime().exec("TASKKILL /F /FI \"USERNAME eq saccount\" /IM iexplorer.exe");
		// Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");

		/*
		 * Properties configProperties = new Properties(); InputStream inputFile = new
		 * FileInputStream("config.properties"); configProperties.load(inputFile);
		 * 
		 * // Temporary variables declared, this would be fetched from properties //
		 * file. serverURL = configProperties.getProperty("ServerURL"); username =
		 * configProperties.getProperty("Username"); password =
		 * configProperties.getProperty("Password"); workspace =
		 * configProperties.getProperty("Workspace"); project =
		 * configProperties.getProperty("Project"); testcaseFormattedID = "TC42249"; //
		 * method.getName(); buildNumber = configProperties.getProperty("BuildNumber");
		 * notes = configProperties.getProperty("DefaultNote"); duration = 0.0; // get
		 * from extent test // "Blocked", "Deferred", "Enhancement", "Error", "Fail",
		 * "Hold", "In // Progress", "Inconclusive", "Invalid", "Out of Scope", "Pass",
		 * // "Waiting for Policy" verdict = "Pass";
		 * 
		 * iR = new IntegrateRallyRestAPI(); password = iR.decodeString(password); //
		 * This should go in Before Suite method userRef =
		 * iR.getRallyUserDetails(serverURL, username, password);
		 */
		createReportFolder(this.getClass().getSimpleName());
	}

	// Extent report initialization before every test case.
	@BeforeMethod(alwaysRun = true)
	public void Setup(Method method) throws Exception {
		//Process processKillPdf = Runtime.getRuntime().exec("taskkill /F /IM savePdf.exe");
		Process processkillpdf = Runtime.getRuntime().exec("TASKKILL /F /FI \"USERNAME eq "+System.getProperty("user.name")+"\" /IM savePdf.exe");
		Process processkillIE = Runtime.getRuntime().exec("TASKKILL /F /FI \"USERNAME eq "+System.getProperty("user.name")+"\" /IM iexplore.exe");
		Process processkillSaveExcel = Runtime.getRuntime().exec("TASKKILL /F /FI \"USERNAME eq "+System.getProperty("user.name")+"\" /IM saveExcel.exe");
		Process processkillCloseExcel = Runtime.getRuntime().exec("TASKKILL /F /FI \"USERNAME eq "+System.getProperty("user.name")+"\" /IM Excel.exe");
		Process processKillExcel = Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE");
		driver = BrowserTypes.getDriver();
		ExtentReporter.logger = ExtentReporter.report.startTest(method.getName(),
				method.getAnnotation(Test.class).description());
		/*
		 * System.out.println("==============================================");
		 * System.out.println(method.getName() + " test case execution started.");
		 * System.out.println("==============================================");
		 */
		// ExtentReporter.logger = ExtentReporter.report.startTest(method.getName());
		testcaseFormattedID = method.getName();
		// Code to populate HashMap from excel
		// Instantiate ExcelUtil and call testData and fill a HashMap
		// testDataMap
		TestCaseDetails.testcaseId = method.getName().toUpperCase();
		ExcelUtil excelUtil = new ExcelUtil();
		try {
		TestCaseDetails.testDataDictionary = excelUtil.testData(TestCaseDetails.testcaseId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	//@Test(description = "Rate a policy that existed before the change or deployment to confirm it still displays as expected", groups = {
	//		"Smoke Test" }, priority = 0)
	public void TC42239() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink();
		HomePage homepage = new HomePage(driver);
		String policyNum = homepage.policySearchUsingSearchCriteria();
		rateapolicyPage.rateFunctionality(policyNum);
	}
	
	
	@Test(description= "QA Hospital Rate",groups = { "QA Smoke Test" },priority = 0)
	public void TC43778() throws Exception{
					
					TC42239();
		}

	

	@Test(description= "QA Hospital Verify Add Organization",groups = { "QA Smoke Test" },priority = 1)
	public void TC43767() throws Exception{
				
		//TC42404();
		LoginPage loginpage = new LoginPage(driver);
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		ExcelUtil exlUtil = new ExcelUtil();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().navigateToAddOrgPage();
		CISPage cisPage = new CISPage(driver);
		String OrganizationName = cisPage.addOrganizationInformation();
		cisPage.addOrgAddress().selectZipCode().addPhoneNumber().searchRecentlyAddedOrganisation(OrganizationName);
		exlUtil.writeData("TC43768", "lastOrgName", OrganizationName, 1, ExcelPath);
	}
	
	
		//	@Test(description = "This test case will cover smoke test for Hospital(BTS) CIS\r\n" + "Verify CIS opens \r\n"
		//		+ "Search an entity/person\r\n"
		//		+ "Navigate through the CIS screens", groups = { "Smoke Test" }, priority = 2)
		public void TC42253() throws Exception {
			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().searchAndSelectAClientName()
					.verifyPagesHavingMenuOnPersonPageAreDisplayed().verifyPagesWithoutSubMenu();
		}
		
	
		@Test(description= "QA Verify CIS Page Displays",groups = { "QA Smoke Test" },priority = 2)
		public void TC43766() throws Exception{
					
					TC42253();
		}
	
	
	
	@Test(description = "QA Hospital Indication", groups = { "QA Smoke Test" }, priority = 3)
	public void TC43768() throws Exception {

		String Empty="";
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		HomePageDTO homePageDTO = new HomePageDTO(TestCaseDetails.testDataDictionary);
		ExcelUtil exlUtil = new ExcelUtil();
		PolicyIndicationPage policyindicationpage = new PolicyIndicationPage(driver);
		PolicyIndicationPageDTO indicationPageDTO = new PolicyIndicationPageDTO(TestCaseDetails.testDataDictionary);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);

		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().create_New();
		String ParentWindow = homepage.create_Quote();
		homepage.searchEntity(homePageDTO.lastOrgName, Empty).selectEntity(ParentWindow, Empty).selectPolicyTypeForQA()
				.updatePolicyDetails();

		String policyNo	=policyindicationpage.open_Underwriter();
		
		policyindicationpage.add_UnderwriterForQA(policyNo).closeUnderwriter().addAgent().selectRiskTab()
				.addRiskInformation(indicationPageDTO.riskCounty.get(0), indicationPageDTO.riskSpeciality.get(0),
						indicationPageDTO.riskTypeValue.get(0), Empty).selectCoverageTab().selectAddCoverageButton()
				.selectCoverageFromPopupListAddDatePremium(indicationPageDTO.retroDate.get(0))
				.closeAddCoverageWindow().selectCoverageFromGridList().addCoverageClass();

		Thread.sleep(10000);
		String PolicyNo = policyindicationpage.policyNo();

		policyindicationpage.coverageUpdates(PolicyNo).openLimitSharingTab(PolicyNo).addSharedGroup(PolicyNo)
				.closeLimitSharingtab().rateFunctionality(PolicyNo);
		policyQuotePage.clickPreviewTab(PolicyNo).savePDF(reportFolderPath).verifyPdfContent();
		policyQuotePage.saveOptionOfficial(PolicyNo);
		exlUtil.writeData("TC43769", "PolicyNum", PolicyNo, 1, ExcelPath);
	}
	
	
	
	@Test(description = "QA Hospital Quote", groups = { "QA Smoke Test" }, priority = 4)
	public void TC43769() throws Exception {
		//TC42238();
		
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyquotepage = new PolicyQuotePage(driver);
		ExcelUtil exlUtil = new ExcelUtil();
		PolicyQuotePageDTO policyquotepagedto = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
		lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		loginpage = new LoginPage(driver);

		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
		.searchPolicyPolicyQuotePage().copyFromActionDropDownForQA()
		.changePolicyPhase(policyquotepagedto.quotePhaseValue).coverageDetailsSelect();
		String policyNumber = policyquotepage.policyNo();
		rateapolicyPage.coverageUpdates(policyNumber);
		exlUtil.writeData("TC43770", "PolicyNum", policyNumber, 1, ExcelPath);
		policyquotepage.rateFunctionalityWithoutPremiumVerification(policyNumber).clickPreviewTab(policyNumber).savePDF(reportFolderPath).verifyPdfContent()
				.saveOption(policyNumber);
	
	}

	
	//latest back up policy search - error
	@Test(description = "QA Hospital Binder", groups = { "QA Smoke Test" }, priority = 5)
	public void TC43770() throws Exception {
	
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		ExcelUtil exlUtil = new ExcelUtil();
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
				.searchPolicyRateAPolicyPage().AcceptFromActionDropDown().identifyPhase().billingSetup().refreshCurrentPage(driver)
				.coverageDetailsSelect();
		String policyNumber = rateapolicyPage.policyNo();
		rateapolicyPage.coverageUpdates(policyNumber).rateFunctionalityWithoutPremiumAmountVerification(policyNumber).clickPreviewTab(policyNumber)
				.savePDF(reportFolderPath).verifyPdfContent().saveOption(policyNumber);
		exlUtil.writeData("TC43771", "PolicyNum", policyNumber, 1, ExcelPath);
	
	}

		//latest back up policy search done
		@Test(description = "QA Hospital Issue Policy Forms", groups = { "QA Smoke Test" }, priority = 6)
		public void TC43771() throws Exception {
			//TC42665();
			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			ExcelUtil exlUtil = new ExcelUtil();
			PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromPolicyBinderPage()
					.searchPolicyPolicyBinderPage();

			PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
			String policyNumber = policybinderpage.policyNo();

			policybinderpage.endorsementFromActionDropDown().endorsePolicy(policyNumber).identifyPhase()
					.rateFunctionality(policybinderpage.policyNo());
			String policyNo = policybinderpage.policyNo();
			policyQuotePage.clickPreviewTab(policyNo).savePDF(reportFolderPath).verifyPdfContent();
			policybinderpage.saveOption(policyNo);
			exlUtil.writeData("TC43783", "PolicyNum", policyNo, 1, ExcelPath);
			exlUtil.writeData("TC43773", "PolicyNum", policyNo, 1, ExcelPath);
			exlUtil.writeData("TC43775", "PolicyNum", policyNo, 1, ExcelPath);
			exlUtil.writeData("TC43776", "PolicyNum", policyNo, 1, ExcelPath);
			exlUtil.writeData("TC43781", "PolicyNum", policyNo, 1, ExcelPath);
			exlUtil.writeData("TC43777", "PolicyNum", policyNo, 1, ExcelPath);
		}
		

	//TODO-latest search
	@Test(description = "QA FM - Hospital Verify On Demand Invoice, Create Batch and Post Batch", groups = {
	"QA Smoke Test" }, priority = 7)
	public void TC43783() throws Exception {
		
		//TC42250();
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		FinancePageDTO financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
		ExcelUtil exlUtil = new ExcelUtil();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinanceHomePage()
				.searchPolicyOnFinanceHomePage().openFirstAccount().onDemandInvoice().exportExcelSheet("")
				.getInvoiceAmountFromExcel().cashEntry().batchFunction().validateBatch().postBatchFunctionality()
				.donwloadFinalSheetBySearchingAccountNo();
				exlUtil.writeData("TC44219", "PolicyNum", financePageDTO.policyNum, 1, ExcelPath);
	}
					
		
		
		//TODO-latest search
		@Test(description = "FM - Hospital Verify Credit Applications", groups = { "QA Smoke Test" }, priority = 8)
		public void TC44219() throws Exception {
			//To reuse canceled UMBPL -reinstate the UMB PL coverage by selecting the coverage UMB PL
			//and click Policy Actions>Reinstate, then click rate and then save as official, 
			FinancePage financepage = new FinancePage(driver);
			RateApolicyPage rateAPolicyPage = new RateApolicyPage(driver);
			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			FinancePageDTO financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinancePageFromHeaderLink()
					.receivableDownload(financePageDTO.CreditInstallmentBeforeFileName).naviagetToPolicyFromHeaderLink()
					.searchPolicyRateAPolicyPage().coverageDetailsSelect();
			financepage.selectUMBCoverage().selectCancelFromPolicyActionDDL().rateFunctionalityWithoutPremiumVerification()
					.openPDF(rateAPolicyPage.policyNo()).savePDF(reportFolderPath);
			financepage.savePolicyAsofficial().navigateToFinancePageFromHeaderLink().searchPolicyOnFinanceHomePage()
					.openFirstAccount().downloadExcel(financePageDTO.CancelledCoverageTransactionFileName)
					.receivableDownloaWithoutNavigationForQA(financePageDTO.CreditInstallmentAfterFileName);
			//TODO - upload all pdf and excel to rally.
		}
	
		

		
		@Test(description = "QA Hospital Verify Attach Form", groups = { "QA Smoke Test" }, priority = 9)
		public void TC43773() throws Exception {
		
			//TC42399();
			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			ExcelUtil exlUtil = new ExcelUtil();
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).naviagetToPolicyFromHeaderLink();
			HomePage homepage = new HomePage(driver);
			String policyNum = homepage.policySearchUsingSearchCriteria();
			RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
			String policyNumber = rateapolicyPage.checkPolicyViewModeAndUpdateCoverage(policyNum);
			exlUtil.writeData("TC43774", "PolicyNum", policyNumber, 1, ExcelPath);
			PolicyBinderPage pbp = new PolicyBinderPage(driver);
			String policyNo = pbp.policyNo();
			rateapolicyPage.rateFunctionality(policyNo).clickPreviewTab(policyNo).savePDF(reportFolderPath).verifyPdfContent();
			
		}
	
	
		//@Test(description = "Hospital Verify Interactive Form", groups = { "Smoke Test" }, priority = 10)
		public void TC42247() throws Exception {
			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
					.searchPolicyRateAPolicyPage();
			String policyNo = rateapolicypage.policyNo();
			rateapolicypage.coverageDetailSelectForCinCom().cincomFlow(policyNo).rateFunctionalityWithoutPremiumAmountVerification(policyNo)
					.clickPreviewTab(policyNo).savePDF(reportFolderPath).verifyPdfContent();
		}

		//Note- updated script to select Form CHGGE before Data Entry action and for cincom.
		@Test(description = "QA Hospital Verify Interactive Form", groups = { "Smoke Test" }, priority = 10)
		public void TC43774() throws Exception {
			
			TC42247();
		}
	

		//TODO-done on QA	
		@Test(description = "QA Hospital Create Claim", groups = { "QA Smoke Test" }, priority = 11)
		public void TC43776() throws Exception {
			
			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
			ExcelUtil exlUtil = new ExcelUtil();
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
					.searchPolicyRateAPolicyPage();
			String clientID = policybinderpage.getClientId();
			policybinderpage.verifyPhase().navigatetoClaimsPage().getPatientDetails().enterDataOnClaimsPage(clientID);
			String claimNumber=policybinderpage.claimNo();
			exlUtil.writeData("TC43784", "claimNum", claimNumber, 1, ExcelPath);
			exlUtil.writeData("TC43782", "claimNum", claimNumber, 1, ExcelPath);
		}
		
		
		//@Test(description = "Hospital Claim - Verify Change Claim Status", groups = { "Smoke Test" }, priority = 12)
		public void TC42405() throws Exception {

			LoginPage loginpage = new LoginPage(driver);

			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
			PolicyBinderPage policybinderpage = null;
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
			policybinderpage = new PolicyBinderPage(driver);
			// String ClaimNumber = rateapolicyPage.policyNo();
			policybinderpage.navigatetoClaimsPage().searchClaim().statusChange(rateapolicyPage.policyNo());
		}


		//Known issue.- It needs special user privilege to change policy status.
		@Test(description = "QA Hospital Claim - Verify Change Claim Status", groups = { "QA Smoke Test" }, priority = 12)
		public void TC43784() throws Exception {
			
			TC42405();
			
		}
		
	
		//@Test(description = "Claims - Enter Transactions", groups = { "Smoke Test" },priority = 13)
		public void TC42252() throws Exception {
				LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
				LoginPage loginpage = new LoginPage(driver);
				loginpage.loginToeOasis(lpDTO.username, lpDTO.password)
				.navigateToClaimsPageFromHomePageLink()
				.searchClaim()
				.openTransactionTab()
				.addTransactionDataAndSaveTransaction();
			}
			
		@Test(description = "QA Claims - Enter Transactions", groups = { "QA Smoke Test" },priority = 13)
		public void TC43782() throws Exception {
				TC42252();
			}
		

		//updated search criteria for backup
		//@Test(description = "Verify Hospital Preview Forms", groups = { "Smoke Test" }, priority = 14)
		public void TC42240() throws Exception {
			RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
			LoginPageDTO lpDTO;
			LoginPage loginpage;
			PolicyQuotePage policyquotepage = new PolicyQuotePage(driver);
			lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			loginpage = new LoginPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
					.searchPolicyRateAPolicyPage();
			String policyNumber=rateapolicypage.policyNo();
			rateapolicypage.policyEndorsement(policyNumber).rateFunctionality(policyNumber)
					.clickPreviewTab(policyNumber).savePDF(reportFolderPath).verifyPdfContent();
			policyquotepage.saveOptionOfficial(policyNumber);
		
		}
		
		
		//Search policy done.
		@Test(description = "QA Verify Hospital Preview Forms", groups = { "QA Smoke Test" }, priority = 14)
		public void TC43775() throws Exception {
			
			TC42240();
		}
		

	
	@Test(description = "QA Hospital Copy to Quote", groups = { "QA Smoke Test" }, priority = 15)
	public void TC43781() throws Exception {
	
		//TC42245();
		ExcelUtil exlUtil = new ExcelUtil();
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
		RateApolicyPage rateApolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
		PolicySubmissionPage policySubmissionPage = new PolicySubmissionPage(driver);
		PolicyQuotePageDTO policyquotepagedto = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
		lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).naviagetToPolicyFromHeaderLink()
				.searchPolicyRateAPolicyPage();

		String policyNumber = policybinderpage.policyNo();

		policybinderpage.copyToQuoteFromActionDropDown(policyNumber);   //For QA
		policyQuotePage.copyFromActionDropDownForQAWithoutBackup();
		policySubmissionPage.changePhaseToIndicationAndAddQuoteDescription();
		rateApolicyPage.rateFunctionality(policybinderpage.policyNo());
		policyQuotePage.saveOptionOfficial(policybinderpage.policyNo())
				.copyFromActionDropDownForQAWithoutBackup()
				.changePolicyPhase(policyquotepagedto.quotePhaseValue);
		rateApolicyPage.rateFunctionality(policybinderpage.policyNo());
		policyQuotePage.saveOptionOfficial(policybinderpage.policyNo());
		rateApolicyPage.AcceptFromActionDropDownwithoutBackupPolicy().billingSetup().refreshCurrentPage(driver)
				.rateFunctionality(policybinderpage.policyNo()).saveOptionOfficial(policybinderpage.policyNo());
		policybinderpage.endorsementFromActionDropDownwithoutBackupPolicy()
				.endorseAPolicyforRateApolicyPage(policyNumber).rateFunctionality(policybinderpage.policyNo());
		String policyNumb=policybinderpage.policyNo();
		policyQuotePage.clickPreviewTab(policyNumb).savePDF(reportFolderPath).verifyPdfContent();	
		policyQuotePage.saveOptionOfficial(policyNumb);
		exlUtil.writeData("TC44218", "PolicyNum", policyNumb, 1, ExcelPath);
		exlUtil.writeData("TC43780", "PolicyNum", policyNumb, 1, ExcelPath); //42243,42246
	}
	

	//@Test(description = "Hospital Verify Image Right", groups = { "Smoke Test" }, priority = 16)
	public void TC42243() throws Exception {
			LoginPageDTO lpDTO;
			LoginPage loginpage;
			FindPolicyPage findapolicypage = new FindPolicyPage(driver);
			RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
			rateapolicypage.openImageRight();
			lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			loginpage = new LoginPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink();
			findapolicypage.openSearchPolicyPane()
			.openSearchPolicyPane()
			.selectTermStatusAndIssueCompany()
			.selectPolicyType()
			.searchFromFindPolicyPage()
			.selectValueFromActionDropDown().ImageRightFocus();
		}


		@Test(description = "QA Hospital Verify Image Right", groups = { "QA Smoke Test" }, priority = 21)
		public void TC43780() throws Exception {
			
			TC42243();
		}
	
	
	//@Test(description = "FM - Hospital Verify FM Installment", groups = { "BTS Smoke Test" }, priority = 17)
	public void TC42246() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		FinancePage financePage = new FinancePage(driver);
		FinancePageDTO financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
		PolicyIndicationPage policyIndicationPage = new PolicyIndicationPage(driver);
		RateApolicyPage rateApolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinanceHomePage()
				.searchPolicyOnFinanceHomePage().openFirstAccount().onDemandInvoice()
				.exportExcelSheet(financePageDTO.onDemandInvoiceInstallmentExcel)
				.selectReceivableTabAndExportExcel(financePageDTO.onDemandInvoiceInstallementBeforeExcel)
				.selectAccountTabInvoicesButtonAndExportExcel().navigateToPolicyPageThroughPolicyHeaderLink()
				.policySearchUsingSearchCriteria();
		String cellValue = financePage.readDataFromExcelSheet(financePageDTO.dataSheetName,
				financePageDTO.testDataColumnName, financePageDTO.dataRowNumber, financePageDTO.exportedExcelSheetName);
		financePage.writeDataInExcelSheet(cellValue, financePageDTO.TCSheetNumber, financePageDTO.testDataColumnheader,
				financePageDTO.rowNumber);
		String policyNum = rateApolicyPage.policyNo();
		String nextDay = financePage.nextDayOfDueDate();
		financePage.policyEndorsementWithDate(policyNum, nextDay) // Delete WIP if Endorsement is not shown in policy
																	// Action
				.selectCoverageTab();
		financePage.selectCoverageFromGridList().selectAddCoverageButton();
		policyIndicationPage.selectCoverageFromPopupListAddDatePremium(nextDay).closeAddCoverageWindow();
		rateApolicyPage.rateFunctionalityWithoutPremiumAmountVerification(policyNum).clickPreviewTab(policyNum).savePDF(reportFolderPath);
		policyQuotePage.saveOptionOfficial(policyNum);
		homePage.navigateToFinancePageFromHeaderLink().searchPolicyOnFinanceHomePage().openFirstAccount()
				.exportExcelSheet(financePageDTO.excelNameAddCoverageInstallment)
				.selectReceivableTabAndExportExcel(financePageDTO.excelNameOnDemandInvoiceInstallmentAfter);

		// TODO- upload all excels and pdf to rally.
	}
	
	
	//TODO-done on QA
	@Test(description = "QA FM - Hospital Verify FM Installment", groups = { "QA Smoke Test" }, priority = 17)
	public void TC44218() throws Exception {
		TC42246();
	
	}
	

		//@Test(description = "Hospital Renewal", groups = { "Smoke Test" }, priority = 18)
		public void TC42400() throws Exception {
		// TODO- Get policy number//09100510, 09100511, 09100512, 09100514
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
		PolicyQuotePageDTO policyquotepageDTO = new PolicyQuotePageDTO(TestCaseDetails.testDataDictionary);
		PolicyBinderPage policyBinderPage = new PolicyBinderPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage().selectPolicyAction().addQuoteDescription().save_CaptureTransactionDetails();

		String PolicyNo = policyBinderPage.policyNo();
		policyQuotePage.saveOptionAndCaptureTransactionDetails(policyquotepageDTO.saveAsPolicyDDLValue, PolicyNo);
		String policyNum = policyBinderPage.policyNo();
		policyQuotePage.saveOptionOfficial(policyNum).applyChanges()
				.changePolicyPhase(policyquotepageDTO.policyPhaseValue).saveOptionOfficial(PolicyNo);
	}

	
	@Test(description = "QA Hospital Renewal", groups = { "QA Smoke Test" }, priority = 18)
	public void TC43777()  throws Exception {
		TC42400();

	}
	
		
	//@Test(description = "FM - Verify that user can create an account in FM", groups = { "Smoke Test" })
	public void TC42251() throws Exception {

			LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
			LoginPage loginpage = new LoginPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinancePageFromHeaderLink().newAccountOpen()
					.entitySelectSearch().selectIssueCompany().selectAddress().saveAccountDetails().captureSaveScreenshotofMantainAccountpage();
		}
	
	
	@Test(description = "QA FM - Verify that user can create an account in FM", groups = { "QA Smoke Test" },priority = 19)
	public void TC44217() throws Exception {

			TC42251();
		}
		
	
		//@Test(description = "Claims - Verify that user is allowed to change Billing Parameter for an Existing Account", groups = {
		//"Smoke Test" }, priority = 20)
		public void TC42403() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO(TestCaseDetails.testDataDictionary);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToFinancePageFromHeaderLink()
				.searchAccountUsingSearchCriteria().selectLastAccountFromAccountList().maintainAccount()
				.saveAccountInformation().captureSaveScreenshotofMantainAccountpage();
		}

		
		@Test(description = "QA FM Hospital - Change Billing Parameter for an Existing Account", groups = {
		"QA Smoke Test" }, priority = 20)
		public void TC44216() throws Exception {
		
		TC42403();
		
		}
	
		//TODO-Verify & Add this tests in QA suite- 42218 & 42219
			
	
	@AfterMethod(alwaysRun = true)
	public void logoffFromAppclication(ITestResult result)
			throws IOException, InterruptedException, URISyntaxException,Exception {
		ExtentReporter.report.endTest(ExtentReporter.logger);

		if (ITestResult.FAILURE == result.getStatus()) {
			// verdict = "Fail";
			try {
				String screenshotLocation = screenshotfolderpath + result.getName() + ".png";
				ExtentReporter.logger.log(LogStatus.FAIL, ExtentReporter.logger.addScreenCapture(screenshotLocation));
				ExtentReporter.logger.log(LogStatus.FAIL, result.getThrowable());
			} catch (Exception e) {
				System.out.println("Error in repot");
				e.getStackTrace();
			}

		} else if (ITestResult.SUCCESS == result.getStatus()) {
			HomePage homepage= new HomePage(driver);
			homepage.logoutFromeOasis();
			// verdict = "Pass";
			//ExtentReporter.logger.log(LogStatus.INFO, "User is logged out of application");
		} else if (ITestResult.SKIP == result.getStatus()) {
			// verdict = "Hold";
		}

		// "Blocked", "Deferred", "Enhancement", "Error", "Fail", "Hold", "In
		// Progress", "Inconclusive", "Invalid", "Out of Scope", "Pass",
		// "Waiting for Policy"

		/*
		 * iR.updateResultsInRally(serverURL, username, password, workspace, project,
		 * testcaseFormattedID.toUpperCase(), buildNumber, notes, userRef, duration,
		 * verdict);
		 */

		Thread.sleep(2000);
		driver.close();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		ExtentReporter.report.flush();
		/*
		 * FailedTCRerun failedtcrun = new FailedTCRerun(); failedtcrun.reRunFailedTC();
		 */
		// ExtentReporter.report.close();
		/*
		 * FailedTCRerun failedtcrun = new FailedTCRerun(); failedtcrun.reRunFailedTC();
		 */
		// driver.quit();
	}

}
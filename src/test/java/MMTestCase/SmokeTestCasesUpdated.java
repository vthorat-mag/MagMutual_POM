package MMTestCase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.WriteAbortedException;
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
import com.mm.dto.HomePageDTO;
import com.mm.dto.LoginPageDTO;
import com.mm.dto.PolicyIndicationPageDTO;
import com.mm.dto.PolicyQuotePageDTO;
import com.mm.pages.CISPage;
import com.mm.pages.HomePage;
import com.mm.pages.LoginPage;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyIndicationPage;
import com.mm.pages.PolicyQuotePage;
import com.mm.pages.RateApolicyPage;
import com.mm.utils.CommonAction;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.IntegrateRallyRestAPI;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SmokeTestCasesUpdated {

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
	}

	// Extent report initialization before every test case.
	@BeforeMethod(alwaysRun = true)
	public void Setup(Method method) throws Exception {
		//Process processKillPdf = Runtime.getRuntime().exec("taskkill /F /IM savePdf.exe");
		//Process process = Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");
		Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");
		driver = BrowserTypes.getDriver();
		ExtentReporter.logger = ExtentReporter.report.startTest(method.getName(),
				method.getAnnotation(Test.class).description());
		System.out.println("==============================================");
		System.out.println(method.getName() + " test case execution started.");
		System.out.println("==============================================");
		// ExtentReporter.logger = ExtentReporter.report.startTest(method.getName());
		testcaseFormattedID = method.getName();
		// Code to populate HashMap from excel
		// Instantiate ExcelUtil and call testData and fill a HashMap
		// testDataMap
		ExcelUtil excelUtil = new ExcelUtil();
		testDataMap = excelUtil.testData(method.getName());
	}

	@Test(description = "Rate a policy that existed before the change or deployment to confirm it still displays as expected" , 
			groups = { "Smoke Test" }, priority = 0)
	public void TC42239() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage();
		HomePage homepage = new HomePage(driver);
		String policyNum = homepage.policySearchUsingSearchCriteria();
		rateapolicyPage.rateFunctionality(policyNum);
	}

	@Test(description = "This test case will cover smoke test for Hospital(BTS) CIS\r\n" + "Verify CIS opens \r\n"
			+ "Search an entity/person\r\n"
			+ "Navigate through the CIS screens", groups = { "Smoke Test" }, priority = 1)
	public void TC42253() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().searchAndSelectAClientName()
				.verifyPagesHavingMenuOnPersonPageAreDisplayed().verifyPagesWithoutSubMenu();
	}

	@Test(description = "Verify Add Organization", groups = { "Smoke Test" }, priority = 2)
	public void TC42404() throws Exception {
		LoginPage loginpage = new LoginPage(driver);
		LoginPageDTO lpDTO = new LoginPageDTO();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().navigateToAddOrgPage();
		CISPage cisPage = new CISPage(driver);
		String OrganizationName = cisPage.addOrganizationInformation();
		cisPage.addOrgAddress().selectZipCode().addPhoneNumber().searchRecentlyAddedOrganisation(OrganizationName);
	}

	@Test(description = "Hospital Indication", groups = { "Smoke Test" }, priority = 3)
	public void TC42249() throws Exception {

		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		HomePageDTO homePageDTO = new HomePageDTO();
		ExcelUtil exlUtil = new ExcelUtil();
		PolicyIndicationPage policyindicationpage = new PolicyIndicationPage(driver);
		PolicyIndicationPageDTO hospitalIndicationDTO = new PolicyIndicationPageDTO();
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);

		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().create_New();
		String ParentWindow = homepage.create_Quote();
		homepage.searchEntity(homePageDTO.lastOrgName, "").selectEntity(ParentWindow).selectPolicyTypeForBTS()
				.updatePolicyDetails();

		List<WebElement> firstFrame = policyindicationpage.open_Underwriter();

		policyindicationpage.add_Underwriter(firstFrame).closeUnderwriter().addAgent().addRiskInformation()
				.selectCoverageTab().selectAddCoverageButton()
				.selectCoverageFromPopupListAddDatePremium(hospitalIndicationDTO.retroDate.get(0))
				.closeAddCoverageWindow().selectCoverageFromGridList().addCoverageClass();

		String PolicyNo = policyindicationpage.policyNo();

		policyindicationpage.coverageUpdates(PolicyNo).openLimitSharingTab(PolicyNo).addSharedGroup(PolicyNo)
				.closeLimitSharingtab().rateFunctionality(PolicyNo);
		policyQuotePage.clickPreviewTab(PolicyNo).savePDF().verifyPdfContent();
		policyQuotePage.saveOptionOfficial(PolicyNo);
		exlUtil.writeData("TC42238", "PolicyNum", PolicyNo, 1, ExcelPath);
	}

	// DTO done
	@Test(description = "Hospital Quote", groups = { "Smoke Test" }, priority = 4)
	public void TC42238() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyquotepage;
		ExcelUtil exlUtil = new ExcelUtil();
		PolicyQuotePageDTO policyquotepagedto = new PolicyQuotePageDTO();
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);

		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage().CopyOptionFromActionDropDown().changePhaseToQuote()
				.coverageDetailsSelect();

		policyquotepage = new PolicyQuotePage(driver);
		String policyNumber = policyquotepage.policyNo();

		rateapolicyPage.coverageUpdates(policyNumber);
		policyquotepage.rateFunctionality(policyNumber)
				.saveOption(policyquotepagedto.secondSaveAsPolicyDDLValue, policyNumber).clickPreviewTab(policyNumber)
				.savePDF().verifyPdfContent();
		exlUtil.writeData("TC42242", "PolicyNum", policyNumber, 1, ExcelPath);
	}

	@Test(description = "HPL Binder", groups = { "Smoke Test" }, priority = 5)
	public void TC42242() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		ExcelUtil exlUtil = new ExcelUtil();
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage().AcceptFromActionDropDown().identifyPhase().billingSetup()
				.coverageDetailsSelect();
		String policyNumber = rateapolicyPage.policyNo();
		rateapolicyPage.coverageUpdates(policyNumber);
		rateapolicyPage.rateFunctionality(policyNumber).clickPreviewTab(policyNumber).savePDF().verifyPdfContent()
				.saveOption(policyNumber);
		exlUtil.writeData("TC42245", "PolicyNum", policyNumber, 1, ExcelPath);
	}

	@Test(description = "Hospital Copy to Quote", groups = { "Smoke Test" }, priority = 6)
	public void TC42245() throws Exception {
		ExcelUtil exlUtil = new ExcelUtil();
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
		RateApolicyPage rateApolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyQuotePage = new PolicyQuotePage(driver);
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage();

		String policyNumber = policybinderpage.policyNo();

		policybinderpage.copyToQuoteFromActionDropDown(policyNumber).copyFromPolicyActionDropDown(policyNumber)
				.changePhaseToIndicationAndAddQuoteDescription();
		rateApolicyPage.rateFunctionality(policybinderpage.policyNo());
		policyQuotePage.saveOptionOfficial(policybinderpage.policyNo()).CopyOptionFromActionDropDown()
				.changePhaseToQuote();
		rateApolicyPage.rateFunctionality(policybinderpage.policyNo());
		policyQuotePage.saveOptionOfficial(policybinderpage.policyNo());
		rateApolicyPage.AcceptFromActionDropDown().billingSetup().refreshCurrentPage(driver)
				.rateFunctionality(policybinderpage.policyNo()).saveOptionOfficial(policybinderpage.policyNo());
		policybinderpage.endorsementFromActionDropDown().endorseAPolicyforRateApolicyPage(policyNumber)
				.rateFunctionality(policybinderpage.policyNo());
		policyQuotePage.clickPreviewTab(policybinderpage.policyNo()).savePDF();
		policyQuotePage.saveOptionOfficial(policybinderpage.policyNo());
		exlUtil.writeData("TC42665", "PolicyNum", policyNumber, 1, ExcelPath);
	}

	@Test(description = "Hospital Issue Policy Forms", groups = { "Smoke Test" }, priority = 7)
	public void TC42665() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		ExcelUtil exlUtil = new ExcelUtil();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromPolicyBinderPage()
				.searchPolicyPolicyBinderPage();

		PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
		String policyNumber = policybinderpage.policyNo();

		policybinderpage.endorsementFromActionDropDown().endorsePolicy(policyNumber).identifyPhase()
				.rateFunctionality(policyNumber).saveOption(policyNumber).clickPreviewTab(policybinderpage.policyNo())
				.savePDF().verifyPdfContent();
		exlUtil.writeData("TC42399", "PolicyNum", policyNumber, 1, ExcelPath);
	}

	@Test(description = "Hospital Verify Attach Form", groups = { "Smoke Test" }, priority = 8)
	public void TC42399() throws Exception {

		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		ExcelUtil exlUtil = new ExcelUtil();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage();
		HomePage homepage = new HomePage(driver);
		String policyNum = homepage.policySearchUsingSearchCriteria();
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		String policyNumber = rateapolicyPage.checkPolicyViewModeAndUpdateCoverage(policyNum);
		rateapolicyPage.rateFunctionality(policyNumber).clickPreviewTab(policyNumber).savePDF().verifyPdfContent();
		exlUtil.writeData("TC42247", "PolicyNum", policyNumber, 1, ExcelPath);
	}

	@Test(description = "Hospital Verify Interactive Form", groups = { "Smoke Test" }, priority = 9)
	public void TC42247() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		ExcelUtil exlUtil = new ExcelUtil();
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
				.searchPolicyRateAPolicyPage();
		String policyNo = rateapolicypage.policyNo();
		rateapolicypage.coverageDetailSelectForCinCom().cincomFlow(policyNo).rateFunctionality(policyNo)
				.clickPreviewTab(policyNo).savePDF().verifyPdfContent();
		exlUtil.writeData("TC42240", "PolicyNum", policyNo, 1, ExcelPath);
	}

	@Test(description = "Verify Hospital Preview Forms", groups = { "Smoke Test" }, priority = 10)
	public void TC42240() throws Exception {
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		lpDTO = new LoginPageDTO();
		ExcelUtil exlUtil = new ExcelUtil();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
				.searchPolicyRateAPolicyPage();

		String PolicyNo = rateapolicypage.policyNo();
		rateapolicypage.policyEndorsement(PolicyNo).rateFunctionality(PolicyNo).clickPreviewTab(PolicyNo).savePDF()
				.verifyPdfContent();
		exlUtil.writeData("TC42400", "PolicyNum", PolicyNo, 1, ExcelPath);
	}

	@Test(description = "Hospital Renewal", groups = { "Smoke Test" }, priority = 11)
	public void TC42400() throws Exception {
		// String policy_no = " ";
		LoginPageDTO lpDTO = new LoginPageDTO();
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		LoginPage loginpage = new LoginPage(driver);
		ExcelUtil exlUtil = new ExcelUtil();
		PolicyQuotePageDTO policyquotepageDTO = new PolicyQuotePageDTO();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage().selectPolicyAction().save_CaptureTransactionDetails()
				.saveOption(policyquotepageDTO.saveAsPolicyDDLValue, rateapolicypage.policyNo()).switchToNextFrame()
				.save_CaptureTransactionDetails()
				.saveOption(policyquotepageDTO.secondSaveAsPolicyDDLValue, rateapolicypage.policyNo()).product_Notify()
				.exit_SaveOption();
		String PolicyNo = rateapolicypage.policyNo();
		exlUtil.writeData("TC43666", "PolicyNum", PolicyNo, 1, ExcelPath);

	}

	@Test(description = "Hospital Create Claim", groups = { "Smoke Test" }, priority = 12)
	public void TC43666() throws Exception {

		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		PolicyBinderPage policybinderpage = new PolicyBinderPage(driver);
		ExcelUtil exlUtil = new ExcelUtil();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageUsingHeaderPolicyLink()
				.searchPolicyRateAPolicyPage();
		String clientID = policybinderpage.getClientId();
		policybinderpage.verifyPhase().navigatetoClaimsPage().getPatientDetails().enterDataOnClaimsPage(clientID);
		exlUtil.writeData("TC42405", "PolicyNum", clientID, 1, ExcelPath);
	}

	@Test(description = "Hospital Claim - Verify Change Claim Status", groups = { "Smoke Test" }, priority = 13)
	public void TC42405() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		PolicyBinderPage policybinderpage = null;
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
		policybinderpage = new PolicyBinderPage(driver);
		// String ClaimNumber = rateapolicyPage.policyNo();
		policybinderpage.navigatetoClaimsPage().searchClaim().statusChange(rateapolicyPage.policyNo());
	}

	@AfterMethod(alwaysRun = true)
	public void logoffFromAppclication(ITestResult result)
			throws IOException, InterruptedException, URISyntaxException {
		// homepage.logoutFromeOasis();
		ExtentReporter.report.endTest(ExtentReporter.logger);

		if (ITestResult.FAILURE == result.getStatus()) {
			// verdict = "Fail";
			String screenshotLocation = "C:\\SmokeTestFM\\" + result.getName() + ".png";
			ExtentReporter.logger.log(LogStatus.FAIL, ExtentReporter.logger.addScreenCapture(screenshotLocation));
			ExtentReporter.logger.log(LogStatus.FAIL, result.getName());
			ExtentReporter.logger.log(LogStatus.FAIL, result.getThrowable());

		} else if (ITestResult.SUCCESS == result.getStatus()) {
			// verdict = "Pass";
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
		 * iR.updateResultsInRally(serverURL, username, password, workspace, project,
		 * testcaseFormattedID.toUpperCase(), buildNumber, notes, userRef, duration,
		 * verdict);
		 */

		Thread.sleep(2000);
		driver.close();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		ExtentReporter.report.close();
		// driver.quit();
	}

}

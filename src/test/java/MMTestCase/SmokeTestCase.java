package MMTestCase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mm.browsers.BrowserTypes;
import com.mm.dto.LoginPageDTO;
import com.mm.pages.CISPage;
import com.mm.pages.EndorsePolicyPage;
import com.mm.pages.FindPolicyPage;
import com.mm.pages.HomePage;
import com.mm.pages.LoginPage;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyIndicationPage;
import com.mm.pages.PolicyQuotePage;
import com.mm.pages.PolicySubmissionPage;
import com.mm.pages.QuickAddOrganisation;
import com.mm.pages.RateApolicyPage;
import com.mm.utils.ExcelApiTest;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.relevantcodes.extentreports.LogStatus;
 
public class SmokeTestCase extends BrowserTypes {

	// Global Assignment/initialization of variables.
	WebDriver driver = BrowserTypes.getDriver();
	public static HashMap<String, List<String>> testDataMap;
	
	
	LoginPage loginpage;
	CISPage cispage;
	HomePage homepage;
	RateApolicyPage rateapolicyPage;
	FindPolicyPage findpolicypage;
	PolicyBinderPage policybinderpage;
	PolicyQuotePage policyquotepage;
	PolicySubmissionPage policysubmissionpage;
	QuickAddOrganisation quickaddorganisation;
	PolicyIndicationPage policyindicationpage;
	EndorsePolicyPage endorsepolicypage;
	
	// List of coverage and Phases for Coverage Update flow.
	List<String> coverages = Arrays.asList("Prof Liab-Out", "Prof Liab-Out", "UMB PL-Ins");
	List<String> phase = Arrays.asList("BINDER", "BINDER-EXCESS", "BINDER-UMB");

	// Extent report initialization before every test case.
	@BeforeMethod
	public void Setup(Method method) throws Exception {
		ExtentReporter.logger = ExtentReporter.report.startTest(method.getName());
		//Code to populate HashMap from excel
		// Instantiate ExcelUtil and call testData and fill a HashMap testDataMap
		ExcelUtil excelUtil = new ExcelUtil();
		testDataMap = excelUtil.testData(method.getName());
		
	}

//	 @Test(description="Verify Add Organization",groups = { "Smoke Test" })
	public void TC42404() throws Exception {
		LoginPageDTO lpDTO;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
		homepage = new HomePage(driver);
		homepage.navigateToCISPage();
		cispage = new CISPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage();
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();
	}

	// @Test(description="Rate A Policy",dataProvider = "userTestData",
	// dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42239(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicyPage = new RateApolicyPage(driver);
		// RateApolicyPage.policySearch();
		rateapolicyPage.saveRatedetails();
		rateapolicyPage.startExcelExport();
	}

	// @Test(description="HPL - Binder",dataProvider = "userTestData",
	// dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42242(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		findpolicypage = new FindPolicyPage(driver);
		//String policyNo = findpolicypage.findQuotewithActiveState("Policy", "Active");
		rateapolicyPage = new RateApolicyPage(driver);
		String searchPolicyNum = "Q09101597-NB17-01";
		rateapolicyPage.searchPolicy(searchPolicyNum);
		rateapolicyPage.AcceptFromActionDropDown();
		rateapolicyPage.isAlertPresent();
		rateapolicyPage.identifyPhase();
		rateapolicyPage.billingSetup();
		rateapolicyPage.coverageDetailsSelect();
		String policyNumber = rateapolicyPage.policyNo();
		
		// Below code will run same test steps based on number of coverage you want to update.
		//Refer List coverage and phase defined on this page for values.
		for (int i = 0; i < coverages.size(); i++) {
			rateapolicyPage.coverageUpdates(coverages.get(i), phase.get(i), policyNumber);
		}
		rateapolicyPage.rateFunctionality(policyNumber);
		// TODO - Add PDF verification.
		rateapolicyPage.saveOption(policyNumber);
	}

	// @Test(description="Hospital Issue Policy Forms- Complete",dataProvider =
	// "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42665(String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new PolicyBinderPage(driver);
		String searchPolicyNum = "09100275";
		rateapolicyPage = new RateApolicyPage(driver);
		rateapolicyPage.searchPolicy(searchPolicyNum);
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.endorsementFromActionDropDown();
		policybinderpage.endorsPolicy(policyNumber);
		policybinderpage.identifyPhase();
		policybinderpage.rateFunctionality(policyNumber);
		// TODO - Add PDF verification.
		policybinderpage.saveOption(policyNumber);
		policyquotepage.exit_SaveOption();

	}

	/*@Test(description="Hospital Renewal - Complete",
			dataProvider = "userTestData", dataProviderClass = ExcelApiTest.class,groups = { "Smoke Test" })*/
	public void TC42400() throws Exception {

		String policy_no = "09101673";
		LoginPageDTO lpDTO;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicyPage = new RateApolicyPage(driver);
		rateapolicyPage.searchPolicy(policy_no);
		policyquotepage = new PolicyQuotePage(driver);
		policyquotepage.select_policyAction("Renewal");
		policyquotepage.save_CaptureTransactionDetails();
		policyquotepage.saveOption("Renewal Quote");
		policyquotepage.switchToNextFrame();
		policyquotepage.save_CaptureTransactionDetails();
		policyquotepage.saveOption("Official");
		policyquotepage.product_Notify();
		policyquotepage.exit_SaveOption();

	}

//	@Test(description = "Hospital Quote", dataProvider = "userTestData", dataProviderClass = ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42238(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		policyquotepage = new PolicyQuotePage(driver);
		String searchPolicyNum = "09101645";
		rateapolicyPage = new RateApolicyPage(driver);
		rateapolicyPage.searchPolicy(searchPolicyNum);
		/*
		policyquotepage.CopyOptionFromActionDropDown();
		policyquotepage.changePhaseToQuote();
		policyquotepage.coverageDetailsSelect();
		String policyNumber = policyquotepage.policyNo();
		policyquotepage.coverageUpdates("Prof Liab-Out", "QUOTE-EXCESS", policyNumber);
		// policyquotepage.coverageUpdates("Quote UMB-Out", "QUOTE-UMB",
		// policyNumber);
		// policyquotepage.coverageUpdates("UMB PL-Ins", "INDICATION_UMB",
		// policyNumber);
		policyquotepage.rateFunctionality(policyNumber);
		policyquotepage.saveOption("Official");
		policyquotepage.exit_SaveOption();*/
		policyquotepage.clickPreviewTab();
		Thread.sleep(8000);
		PDFReader pdf = new PDFReader();
		pdf.savePDF();
		pdf.verifyPdfContent("Binder 03 16");
	}

	// @Test(description = "Hospital Copy to Quote",dataProvider =
	// "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42245(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new PolicyBinderPage(driver);
		String searchPolicyNum = "09100200";
		rateapolicyPage = new RateApolicyPage(driver);
		rateapolicyPage.searchPolicy(searchPolicyNum);
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.copyToQuoteFromActionDropDown(policyNumber);
		// TODO - Add PDF verification.
		policysubmissionpage = new PolicySubmissionPage(driver);
		policysubmissionpage.copyFromActionDropDown(policyNumber);
		policysubmissionpage.changePhaseToIndication();
		policysubmissionpage.saveWip();
	}

	@Test(testName="HospitalIndication",groups = { "Smoke Test" })
	public void TC_Indication() throws Exception {
		 
		LoginPageDTO lpDTO;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		homepage.create_New();
		String ParentWindow = homepage.create_Quote();
		homepage.search_Quote(ParentWindow);
		homepage.selectPolicyType();
		policysubmissionpage = new PolicySubmissionPage(driver);
		policysubmissionpage.updatePolicyDetails();
		policyindicationpage = new PolicyIndicationPage(driver);
		List<WebElement> firstFrame = policyindicationpage.open_Underwriter();
		policyindicationpage.add_Underwriter(firstFrame);
		policyindicationpage.close_Underwriter();
		policyindicationpage.addAgent();
		policyindicationpage.addRiskInformation();
		policyindicationpage.addCoverage();
		policyindicationpage.selectCoverageFromPopupListAddDatePremium();
		policyindicationpage.closeAddCoveragetab();
		policyindicationpage.selectCoverageFromGridList();
		policyindicationpage.addCoverageClass();
		String PolicyNo = policyindicationpage.policyNo();
		policyindicationpage.coverageUpdates(PolicyNo);
		policyindicationpage.openLimitSharingTab(PolicyNo);
		policyindicationpage.addSharedGroup(PolicyNo);
		policyindicationpage.closeLimitSharingtab();

	}

	// @Test(testName="EndorsePolicy",dataProvider = "userTestData",
	// dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42530(Method method, String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		endorsepolicypage = new EndorsePolicyPage(driver);
		endorsepolicypage.findPolicy();
	}

	// This is additional test, removed later from rally
	// @Test(testName="VerifyExistingPolicy",dataProvider = "userTestData",
	// dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42536(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicyPage = new RateApolicyPage(driver);
		String searchPolicyNum = "09100275";
		rateapolicyPage.searchPolicy(searchPolicyNum);
	}

	// @Test(description= "Quick_Add_Organisation",dataProvider =
	// "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void Quick_Add(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToCISPage();
		Thread.sleep(3000);
		quickaddorganisation = new QuickAddOrganisation(driver);
		quickaddorganisation.navigate_To_Add_Org_Window();
		quickaddorganisation.add_Org_Information();
		quickaddorganisation.add_Org_Address();
		Thread.sleep(3000);
		quickaddorganisation.selectZipCode();
		quickaddorganisation.add_Phone_Number();
	}

	@AfterMethod
	public void logoffFromAppclication(ITestResult result) throws IOException, InterruptedException {
		// homepage.logoutFromeOasis();
		ExtentReporter.report.endTest(ExtentReporter.logger);

		if (ITestResult.FAILURE == result.getStatus()) {
			ExtentReporter.logger.log(LogStatus.FAIL, result.getName());
			ExtentReporter.logger.log(LogStatus.FAIL, result.getThrowable());

		} else if (ITestResult.SUCCESS == result.getStatus()) {
			ExtentReporter.logger.log(LogStatus.INFO, "User is logged out of applciation");
			ExtentReporter.logger.log(LogStatus.PASS, result.getName());
		} else if (ITestResult.SKIP == result.getStatus()) {
			ExtentReporter.logger.log(LogStatus.PASS, result.getName());
		}
		ExtentReporter.report.flush();
		Thread.sleep(2000);
		// driver.close();
	}

	@AfterClass
	public void closeBrowser() {
		//ExtentReporter.report.close();
		//driver.quit();
	}
}

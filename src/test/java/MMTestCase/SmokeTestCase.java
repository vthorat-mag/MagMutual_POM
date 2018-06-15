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
import com.mm.dto.PolicyBinderPageDTO;
import com.mm.dto.RateAPolicyPageDTO;
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
	//DTO page objects
	PolicyBinderPageDTO policybinderpageDTO;
	RateAPolicyPageDTO rateApolicyPageDTO;
	LoginPageDTO lpDTO;
	
	// List of coverage and Phases for Coverage Update flow.
	/*List<String> coverages = Arrays.asList("Prof Liab-Out", "Prof Liab-Out", "UMB PL-Ins");
	List<String> phase = Arrays.asList("BINDER", "BINDER-EXCESS", "BINDER-UMB");*/

	// Extent report initialization before every test case.
	@BeforeMethod
	public void Setup(Method method) throws Exception {
		ExtentReporter.logger = ExtentReporter.report.startTest(method.getName());
		//Code to populate HashMap from excel
		// Instantiate ExcelUtil and call testData and fill a HashMap testDataMap
		ExcelUtil excelUtil = new ExcelUtil();
		testDataMap = excelUtil.testData(method.getName());
		
	}
	//DTO done
	//@Test(description="Verify Add Organization",groups = { "Smoke Test" })
	public void TC42404() throws Exception {
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().clickOnNewOrganization().enterDataInNewOrgPage().selectZipCode().saveNewOrgDetails();
		/*homepage = new HomePage(driver);
		homepage.navigateToCISPage();
		cispage = new CISPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage();
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();*/
	}

	//@Test(description="Hospital Rate",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42239(String UserName, String PassWord) throws Exception {
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().searchPolicy("09101711").saveRatedetails().startExcelExport();
		/*homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new RateApolicyPage(driver);
		//rateapolicypage.policySearch();
		rateapolicypage.saveRatedetails();
		rateapolicypage.startExcelExport();*/
	}

	//DTO done
	//@Test(description="HPL - Binder",dataProvider = "userTestData",dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42242(String UserName, String PassWord) throws Exception {
		String searchPolicyNum = "Q09101726-NB16-01";
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().searchPolicy(searchPolicyNum).AcceptFromActionDropDown().isAlertPresent().identifyPhase().billingSetup().coverageDetailsSelect();
		/*homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		findpolicypage = new FindPolicyPage(driver);
		String policyNo = findpolicypage.findQuotewithActiveState("Policy", "Active"); // Need to remove this page
		rateapolicypage = new RateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);
		rateapolicypage.AcceptFromActionDropDown();
		rateapolicypage.isAlertPresent();
		rateapolicypage.identifyPhase();
		rateapolicypage.billingSetup();
		rateapolicypage.coverageDetailsSelect();*/
		rateapolicyPage = new RateApolicyPage(driver);
		String policyNumber = rateapolicyPage.policyNo();
		// Below code will run same test steps based on number of coverage you want to update.
		//Refer List coverage and phase defined on this page for values.
		for (int i = 0; i < rateApolicyPageDTO.coverage.size(); i++) {
			rateapolicyPage.coverageUpdates(rateApolicyPageDTO.coverage.get(i), rateApolicyPageDTO.phase.get(i), policyNumber);
		}
		rateapolicyPage.rateFunctionality(policyNumber);
		// TODO - Add PDF verification.
		rateapolicyPage.saveOption(policyNumber);
	}

	//DTO done
	//@Test(description="Hospital Issue Policy Forms- Complete",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42665(String UserName, String PassWord) throws Exception {
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		String searchPolicyNum = "09100275";
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().searchPolicy(searchPolicyNum);
		
		/*homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new PolicyBinderPage(driver);
		rateapolicypage = new RateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);*/
		
		policybinderpage = new PolicyBinderPage(driver);
		String policyNumber = policybinderpage.policyNo();
		
		policybinderpage.endorsementFromActionDropDown().endorsPolicy(policyNumber).identifyPhase().rateFunctionality(policyNumber).saveOption(policyNumber).exit_SaveOption();
		/*policybinderpage.endorsPolicy(policyNumber);
		policybinderpage.identifyPhase();
		policybinderpage.rateFunctionality(policyNumber);
		// TODO - Add PDF verification.
		policybinderpage.saveOption(policyNumber);
		policyquotepage.exit_SaveOption();*/
	}
	
	//DTO Done
	//@Test(description = "Hospital Create Claim", groups = { "Smoke Test" })
	public void TC43666() throws Exception
	{
		String searchPolicyNum = "Q09101675-NB16-01";
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).headerPolicyTab().searchPolicy(searchPolicyNum);
		//homepage = new HomePage(driver);
		//homepage.headerPolicyTab();
		//rateapolicypage = new RateApolicyPage(driver);
		//rateapolicypage.searchPolicy(searchPolicyNum);
		policybinderpage = new PolicyBinderPage(driver);
		String clientID = policybinderpage.getClientId();
		policybinderpage.navigatetoClaimsPage().getPatientDetails(clientID);
	}
	

	/*@Test(description="Hospital Renewal - Complete",
			dataProvider = "userTestData", dataProviderClass = ExcelApiTest.class,groups = { "Smoke Test" })*/
	public void TC42400() throws Exception {

		String policy_no = "09101673";
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
		String searchPolicyNum = "09101645";
		LoginPageDTO lpDTO;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().searchPolicy(searchPolicyNum);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		policyquotepage = new PolicyQuotePage(driver);
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
		LoginPageDTO lpDTO;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		String searchPolicyNum = "09100200";
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().searchPolicy(searchPolicyNum);
		/*homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new PolicyBinderPage(driver);
		rateapolicypage = new RateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);*/
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.copyToQuoteFromActionDropDown(policyNumber).copyFromActionDropDown(policyNumber).changePhaseToIndication().saveWip();
		// TODO - Add PDF verification.
		/*policysubmissionpage = new PolicySubmissionPage(driver);
		policysubmissionpage.copyFromActionDropDown(policyNumber);
		policysubmissionpage.changePhaseToIndication();
		policysubmissionpage.saveWip();*/
	}

	//DTO done
//	@Test(testName="HospitalIndication",groups = { "Smoke Test" })
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
		loginpage.loginToeOasis(UserName, PassWord).navigateToPolicyPage();
		/*homepage = new HomePage(driver);
		homepage.navigateToPolicyPage()*/;
		endorsepolicypage = new EndorsePolicyPage(driver);
		endorsepolicypage.findPolicy();
	}

	// This is additional test, removed later from rally
	// @Test(testName="VerifyExistingPolicy",dataProvider = "userTestData",
	// dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42536(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		String searchPolicyNum = "09100275";
		loginpage.loginToeOasis(UserName, PassWord).navigateToPolicyPage().searchPolicy(searchPolicyNum);
	}

	// @Test(description= "Quick_Add_Organisation",dataProvider =
	// "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void Quick_Add(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord).navigateToCISPage();
		/*homepage = new HomePage(driver);
		homepage.navigateToCISPage();*/
		quickaddorganisation = new QuickAddOrganisation(driver);
		quickaddorganisation.navigate_To_Add_Org_Window().add_Org_Information().add_Org_Address().selectZipCode().add_Phone_Number();
		/*quick_add_orgpage.add_Org_Information();
		quick_add_orgpage.add_Org_Address();
		quick_add_orgpage.selectZipCode();
		quick_add_orgpage.add_Phone_Number();*/
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
			ExtentReporter.logger.log(LogStatus.SKIP, result.getName());
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
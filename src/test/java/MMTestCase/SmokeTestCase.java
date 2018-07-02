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
import org.testng.annotations.BeforeClass;
import com.mm.browsers.BrowserTypes;
import com.mm.dto.FindPolicyPageDTO;
import com.mm.dto.LoginPageDTO;
import com.mm.dto.PolicyBinderPageDTO;
import com.mm.dto.PolicyQuotePageDTO;
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

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws IOException {
		// Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");

	}

	// Extent report initialization before every test case.
	@BeforeMethod(alwaysRun = true)
	public void Setup(Method method) throws Exception {
		ExtentReporter.logger = ExtentReporter.report.startTest(method.getName());
		System.out.println("==============================================");
		System.out.println(method.getName() + " test case execution started.");
		System.out.println("==============================================");

		// Code to populate HashMap from excel
		// Instantiate ExcelUtil and call testData and fill a HashMap
		// testDataMap
		ExcelUtil excelUtil = new ExcelUtil();
		testDataMap = excelUtil.testData(method.getName());
	}

	// DTO done
	// @Test(description = "Verify Add Organization", groups = { "Smoke Test" })
	public void TC42404() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().clickOnNewOrganization()
				.enterDataInNewOrgPage().selectZipCode().saveNewOrgDetails();
	}

	//DTO done.
	// @Test(description="Hospital Rate",groups = { "Smoke Test" })
	public void TC42239(String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage().saveRatedetails().startExcelExport();
	}

	// DTO Done
	//@Test(description = "Verify Hospital Preview Forms", groups = { "Smoke Test" })
	public void TC42240() throws Exception {
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).headerPolicyTab().searchPolicyRateAPolicyPage();

		String PolicyNo = rateapolicypage.policyNo();
		rateapolicypage.policyEndorsement(PolicyNo).rateFunctionality(PolicyNo).clickPreviewTab().savePDF()
				.verifyPdfContent(PolicyNo);
	}
	
	//DTO done
	@Test(description = "Hospital Verify Interactive Form", groups = { "Smoke Test" })
	public void TC42247() throws Exception
	{
		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).headerPolicyTab().searchPolicyRateAPolicyPage();
		String policyNo = rateapolicypage.policyNo();
		rateapolicypage.coverageDetailsSelect().cincomFlow(policyNo);
	}

	// DTO done
	//@Test(description = "HPL - Binder", groups = { "Smoke Test" })
	public void TC42242() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage().AcceptFromActionDropDown().isAlertPresent().identifyPhase()
				.billingSetup().coverageDetailsSelect();

		String policyNumber = rateapolicyPage.policyNo();

		rateapolicyPage.coverageUpdates(policyNumber);
		rateapolicyPage.rateFunctionality(policyNumber).clickPreviewTab().savePDF().verifyPdfContent(policyNumber)
				.saveOption();
	}

	// DTO Implemented
	//@Test(priority=1, description="Hospital Claim - Verify Change Claim Status", groups ={ "Smoke Test" })
	public void TC42405() throws Exception {
		LoginPageDTO lpDTO = new LoginPageDTO();
		LoginPage loginpage = new LoginPage(driver);
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		PolicyBinderPage policybinderpage = null;
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
		policybinderpage = new PolicyBinderPage(driver);
		//String ClaimNumber = rateapolicyPage.policyNo();
		policybinderpage.navigatetoClaimsPage().searchClaim().statusChange(rateapolicyPage.policyNo());
	}

	// DTO done
	// @Test(description="Hospital Issue Policy Forms", groups = { "Smoke Test"
	// })
	public void TC42665(String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		PolicyBinderPage policybinderpage;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromPolicyBinderPage()
				.searchPolicyPolicyBinderPage();

		policybinderpage = new PolicyBinderPage(driver);
		String policyNumber = policybinderpage.policyNo();

		policybinderpage.endorsementFromActionDropDown().endorsPolicy(policyNumber).identifyPhase()
				.rateFunctionality(policyNumber).saveOption().exit_SaveOption().clickPreviewTab().savePDF()
				.verifyPdfContent(policyNumber);
	}

	// DTO done
	// @Test(description="Hospital Verify Image Right", groups = { "Smoke Test"
	// })
	public void TC42243() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		FindPolicyPage findapolicypage = new FindPolicyPage(driver);
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).headerPolicyTab();

		findapolicypage.searchFromFindPolicyPage().selectValueFromActionDropDown();
		// TODO - Verify image right window is opened successfully after
		// selecting image right option from action drop down.
	}

	// DTO Done
	// @Test(description = "Hospital Create Claim", groups = { "Smoke Test" })
	public void TC43666() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		PolicyBinderPage policybinderpage = null;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).headerPolicyTab().searchPolicyRateAPolicyPage();

		policybinderpage = new PolicyBinderPage(driver);
		String clientID = policybinderpage.getClientId();

		policybinderpage.navigatetoClaimsPage().getPatientDetails(clientID);
	}

	// DTO done
	// @Test(description="Hospital Renewal", groups = {"Smoke Test"
	// })
	public void TC42400() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		// String policy_no = " ";
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		// RateAPolicyPageDTO rateApolicyPageDTO = new RateAPolicyPageDTO();
		PolicyQuotePageDTO policyquotepagedto = new PolicyQuotePageDTO();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage().select_policyAction().save_CaptureTransactionDetails()
				.saveOption(policyquotepagedto.saveAsPolicyDDLValue).switchToNextFrame()
				.save_CaptureTransactionDetails().saveOption(policyquotepagedto.secondSaveAsPolicyDDLValue)
				.product_Notify().exit_SaveOption();
	}

	// DTO done
	// @Test(description = "Hospital Quote", groups = { "Smoke Test" })
	public void TC42238() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		RateApolicyPage rateapolicyPage = new RateApolicyPage(driver);
		PolicyQuotePage policyquotepage;
		PolicyQuotePageDTO policyquotepagedto = new PolicyQuotePageDTO();
		
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);

		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage().CopyOptionFromActionDropDown().changePhaseToQuote()
				.coverageDetailsSelect();

		policyquotepage = new PolicyQuotePage(driver);
		String policyNumber = policyquotepage.policyNo();

		rateapolicyPage.coverageUpdates(policyNumber);
		policyquotepage.rateFunctionality(policyNumber).saveOption(policyquotepagedto.secondSaveAsPolicyDDLValue).exit_SaveOption().clickPreviewTab()
				.savePDF().verifyPdfContent(policyNumber);
	}

	// DTO done
	// @Test(description = "Hospital Copy to Quote",groups = { "Smoke Test" })
	public void TC42245(String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		PolicyBinderPage policybinderpage = null;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage();

		policybinderpage = new PolicyBinderPage(driver);
		String policyNumber = policybinderpage.policyNo();

		policybinderpage.copyToQuoteFromActionDropDown(policyNumber).copyFromActionDropDown(policyNumber)
				.changePhaseToIndication().saveWip().clickPreviewTab().savePDF().verifyPdfContent(policyNumber);
	}

	// DTO done
	// @Test(testName="HospitalIndication",groups = { "Smoke Test" })
	public void TC_Indication() throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		HomePage homepage;
		PolicyBinderPage policybinderpage;
		PolicyIndicationPage policyindicationpage;
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().create_New();

		homepage = new HomePage(driver);
		String ParentWindow = homepage.create_Quote();

		homepage.search_Quote(ParentWindow).selectPolicyType().updatePolicyDetails();

		policyindicationpage = new PolicyIndicationPage(driver);
		List<WebElement> firstFrame = policyindicationpage.open_Underwriter();

		policyindicationpage.add_Underwriter(firstFrame).close_Underwriter().addAgent().addRiskInformation()
				.addCoverage().selectCoverageFromPopupListAddDatePremium().closeAddCoveragetab()
				.selectCoverageFromGridList().addCoverageClass();

		String PolicyNo = policyindicationpage.policyNo();

		policyindicationpage.coverageUpdates(PolicyNo).openLimitSharingTab(PolicyNo).addSharedGroup(PolicyNo)
				.closeLimitSharingtab();
	}

	// DTO code is not implemented as this is not part of scope.
	// @Test(testName="EndorsePolicy",groups = { "Smoke Test" })
	public void TC42530(Method method, String UserName, String PassWord) throws Exception {
		LoginPageDTO lpDTO;
		LoginPage loginpage;
		loginpage = new LoginPage(driver);
		String PolicyNo = "9865321";
		loginpage.loginToeOasis(UserName, PassWord).navigateToPolicyPageFromPolicyBinderPage()
				.searchPolicyPolicyBinderPage().endorsPolicy(PolicyNo).rateFunctionality(PolicyNo).saveOption()
				.clickPreviewTab().savePDF().verifyPdfContent(PolicyNo);
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

	@AfterMethod(alwaysRun = true)
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

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		// ExtentReporter.report.close();
		// driver.quit();
	}
}
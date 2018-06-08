package MMTestCase;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLEngineResult.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.mm.browsers.BrowserTypes;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyQuotePage;
import com.mm.pages.PolicySubmissionPage;
import com.mm.pages.CISPage;
import com.mm.pages.EndorsePolicyPage;
import com.mm.pages.FindPolicyPage;
import com.mm.pages.HomePage;
import com.mm.pages.LoginPage;
import com.mm.pages.PolicyIndicationPage;
import com.mm.pages.QuickAddOrganisation;
import com.mm.pages.RateApolicyPage;
import com.mm.utils.ExcelApiTest;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.CommonUtilities;
import com.relevantcodes.extentreports.LogStatus;

public class SmokeTestCase extends BrowserTypes {

	// Global Assignment/initialization of variables.
	WebDriver driver = BrowserTypes.getDriver();
	LoginPage loginpage;
	CISPage cispage;
	HomePage homepage;
	RateApolicyPage rateapolicypage;
	FindPolicyPage findpolicypage;
	PolicyBinderPage policybinderpage;
	PolicyQuotePage policyquotepage;
	PolicySubmissionPage policysubmissionpage;
	QuickAddOrganisation quick_add_orgpage;
	EndorsePolicyPage endorsepolicypage;
	PolicyIndicationPage policyindicationpage;

	// List of coverage and Phases for Coverage Update flow.
	List<String> coverages = Arrays.asList("Prof Liab-Out", "Prof Liab-Out", "UMB PL-Ins");
	List<String> phase = Arrays.asList("BINDER", "BINDER-EXCESS", "BINDER-UMB");

	// Extent report initialization before every test case.
	@BeforeMethod
	public void Setup(Method method) throws Exception {
		ExtentReporter.logger = ExtentReporter.report.startTest(method.getName());
	}

	// @Test(description="Verify Add Organization",dataProvider =
	// "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42404(String UserName, String PassWord, String LongName, String Address_Line1, String City,
			String Phone_no, String Area_code, String Class_Eff_To_Date) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToCISPage();
		cispage = new CISPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage(LongName, Address_Line1, City, Phone_no, Area_code, Class_Eff_To_Date);
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();
	}

	// @Test(description="Hospital Rate",dataProvider = "userTestData",
	// dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42239(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new RateApolicyPage(driver);
		// rateapolicypage.policySearch();
		rateapolicypage.saveRatedetails();
		rateapolicypage.startExcelExport();
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
		rateapolicypage = new RateApolicyPage(driver);
		String searchPolicyNum = "Q09101597-NB17-01";
		rateapolicypage.searchPolicy(searchPolicyNum);
		rateapolicypage.AcceptFromActionDropDown();
		rateapolicypage.isAlertPresent();
		rateapolicypage.identifyPhase();
		rateapolicypage.billingSetup();
		rateapolicypage.coverageDetailsSelect();
		String policyNumber = rateapolicypage.policyNo();
		
		// Below code will run same test steps based on number of coverage you want to update.
		//Refer List coverage and phase defined on this page for values.
		for (int i = 0; i < coverages.size(); i++) {
			rateapolicypage.coverageUpdates(coverages.get(i), phase.get(i), policyNumber);
		}

		//Need to remove below commented code once above logic is successfully run.
		/*
		 * rateapolicypage.coverageUpdates("Prof Liab-Out", "BINDER-EXCESS",
		 * policyNumber); rateapolicypage.coverageUpdates("UMB PL-Ins",
		 * "BINDER-UMB", policyNumber);
		 */

		rateapolicypage.rateFunctionality(policyNumber);
		// TODO - Add PDF verification.
		rateapolicypage.saveOption(policyNumber);
	}

	// @Test(description="Hospital Issue Policy Forms- Complete",dataProvider =
	// "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42665(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new PolicyBinderPage(driver);
		String searchPolicyNum = "09100275";
		rateapolicypage = new RateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.endorsementFromActionDropDown();
		policybinderpage.endorsPolicy(policyNumber);
		policybinderpage.identifyPhase();
		policybinderpage.rateFunctionality(policyNumber);
		// TODO - Add PDF verification.
		policybinderpage.saveOption(policyNumber);
	}
	
	@Test(description = "Hospital Create Claim", groups = { "Smoke Test" })
	public void TC43666() throws Exception
	{
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis("vthorat", "M@G580746");
		homepage = new HomePage(driver);
		Thread.sleep(5000);
		String searchPolicyNum = "Q09101675-NB16-01";
		homepage.headerPolicyTab();
		rateapolicypage = new RateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);
		policybinderpage = new PolicyBinderPage(driver);
		String clientID = policybinderpage.getClientId();
		policybinderpage.navigatetoClaimsPage();
		policybinderpage.getPaitentDetails(clientID);
	}
	

//	/@Test(description = "Hospital Quote", dataProvider = "userTestData", dataProviderClass = ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC42238(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		policyquotepage = new PolicyQuotePage(driver);
		String searchPolicyNum = "09101645";
		rateapolicypage = new RateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);
		/*
		policyquotepage.CopyOptionFromActionDropDown();
		policyquotepage.changePhaseToQuote();
		policyquotepage.coverageDetailsSelect(); String policyNumber =
		policyquotepage.policyNo();
		policyquotepage.rateFunctionality(policyNumber);
		policyquotepage.saveOption(policyNumber);*/
		
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
		rateapolicypage = new RateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.copyToQuoteFromActionDropDown(policyNumber);
		// TODO - Add PDF verification.
		policysubmissionpage = new PolicySubmissionPage(driver);
		policysubmissionpage.copyFromActionDropDown(policyNumber);
		policysubmissionpage.changePhaseToIndication();
		policysubmissionpage.saveWip();
	}

	// @Test(testName="HospitalIndication",dataProvider = "userTestData",
	// dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void TC_Indication(Method method, String UserName, String PassWord) throws Exception {

		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		homepage.create_Quote();
		String ParentWindow = homepage.create_Quote();
		homepage.search_Quote(ParentWindow);
		homepage.selectPolicyType();
		policysubmissionpage = new PolicySubmissionPage(driver);
		policysubmissionpage.updatePolicyDetails();

		policyindicationpage = new PolicyIndicationPage(driver);
		List<WebElement> FirstName = policyindicationpage.open_Underwriter();
		policyindicationpage.add_Underwriter(FirstName, "Arwood, Ruth", "Claims Rep", "Angelly, Sandy");
		policyindicationpage.add_Underwriter(FirstName, "Arwood, Ruth", "Risk Mgmt", "Civali, Karen");
		policyindicationpage.close_Underwriter();
		policyindicationpage.addAgent();
		policyindicationpage.addRiskInformation();
		policyindicationpage.addCoverage();

		policyindicationpage.selectCoverageFromPopupList("01012014", "5000", "Excess Liab-Out", "Claims Made",
				"Premium text box", "Retro Date");
		policyindicationpage.closeAddCoveragetab();

		policyindicationpage.selectCoverageFromGridList("Prof Liab-Out");
		policyindicationpage.addDataInCoverage("01012014", "5000", "Retro Date", "Premium text box");

		policyindicationpage.selectCoverageFromGridList("Gen Liab-Out");
		policyindicationpage.addDataInCoverage("01012014", "5000", "Retro Date", "Premium text box");

		policyindicationpage.selectCoverageFromGridList("UMB PL-Ins");
		policyindicationpage.addDataInCoverage("01012014", "5000", "Retro Date", "Premium text box");

		policyindicationpage.selectCoverageFromGridList("Emp Benefit-Out");
		policyindicationpage.addRetroDateInCoverage("01012014", "Retro Date");

		policyindicationpage.selectCoverageFromGridList("Adm/Reg-Ins");
		policyindicationpage.addRetroDateInCoverage("01012014", "Retro Date");

		policyindicationpage.selectCoverageFromGridList("First Aid-Ins");
		policyindicationpage.addRetroDateInCoverage("01012014", "Retro Date");

		policyindicationpage.selectCoverageFromGridList("Evacuation-Ins");
		policyindicationpage.addRetroDateInCoverage("01012014", "Retro Date");

		policyindicationpage.selectCoverageFromGridList("Loss Earn-Ins");
		policyindicationpage.addRetroDateInCoverage("01012014", "Retro Date");

		policyindicationpage.selectCoverageFromGridList("Prof Liab-Out");
		policyindicationpage.addCoverageClass();

		String PolicyNo = policyindicationpage.policyNo();
		policyindicationpage.coverageUpdates("Prof Liab-Out", "INDICATION", PolicyNo);
		policyindicationpage.coverageUpdates("Excess Liab-Out", "INDICATION-EXCESS", PolicyNo);
		policyindicationpage.coverageUpdates("UMB PL-Ins", "INDICATION-UMB", PolicyNo);

		policyindicationpage.openLimitSharingTab(PolicyNo);
		policyindicationpage.addSharedGroup("Fire Dmg-Ins", "PL Shared", PolicyNo);
		policyindicationpage.addSharedGroup("Loss Earn-Ins", "GL Shared", PolicyNo);
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
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new RateApolicyPage(driver);
		String searchPolicyNum = "09100275";
		rateapolicypage.searchPolicy(searchPolicyNum);
	}

	// This is additional test, added later in rally
	// @Test(description= "Quick_Add_Organisation",dataProvider =
	// "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke Test" })
	public void Quick_Add(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new HomePage(driver);
		homepage.navigateToCISPage();
		Thread.sleep(3000);
		quick_add_orgpage = new QuickAddOrganisation(driver);
		quick_add_orgpage.navigate_To_Add_Org_Window();
		quick_add_orgpage.add_Org_Information();
		quick_add_orgpage.add_Org_Address();
		Thread.sleep(3000);
		quick_add_orgpage.selectZipCode();
		quick_add_orgpage.add_Phone_Number();
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
		// ExtentReporter.report.close();
		// driver.quit();
	}
}

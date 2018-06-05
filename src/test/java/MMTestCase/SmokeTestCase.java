package MMTestCase;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
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
import com.mm.pages.Policy_Binder_Page;
import com.mm.pages.Policy_Quote_Page;
import com.mm.pages.Policy_Submission_Page;
import com.mm.pages.cisPage;
import com.mm.pages.endorsePolicyPage;
import com.mm.pages.findPolicyPage;
import com.mm.pages.homePage;
import com.mm.pages.indicationPage;
import com.mm.pages.loginPage;
import com.mm.pages.policyDetailsPage;
import com.mm.pages.quick_Add_Organisation;
import com.mm.pages.rateApolicyPage;
import com.mm.utils.ExcelApiTest;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.commonUtilities;
import com.relevantcodes.extentreports.LogStatus;


public class SmokeTestCase extends BrowserTypes {
	
	WebDriver driver = BrowserTypes.getDriver();
	
	loginPage loginpage;
	cisPage cispage;
	homePage homepage;
	rateApolicyPage rateapolicypage;
	findPolicyPage findpolicypage;
	Policy_Binder_Page policybinderpage;
	Policy_Quote_Page policyquotepage;
	Policy_Submission_Page policysubmissionpage;
	indicationPage indicationpage;
	quick_Add_Organisation quick_add_orgpage;
	policyDetailsPage policydetailspage;
	endorsePolicyPage endorsepolicypage;
	
	@BeforeMethod
	public void Setup(Method method) throws Exception
	{
		ExtentReporter.logger=ExtentReporter.report.startTest(method.getName());
	}
	
	//@Test(description="Verify Add Organization",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	public void TC42404(String UserName, String PassWord,String LongName,String Address_Line1, String City, String Phone_no, String Area_code, String Class_Eff_To_Date) throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new homePage(driver);
		homepage.navigateToCISPage();
		cispage = new cisPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage(LongName,Address_Line1,City,Phone_no,Area_code, Class_Eff_To_Date);
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();
	}
	
	//@Test(description="Hospital Rate",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	public void TC42239(String UserName, String PassWord) throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new rateApolicyPage(driver);
		//rateapolicypage.policySearch();
		rateapolicypage.saveRatedetails();
		rateapolicypage.startExcelExport();
	}
	
	//@Test(description="HPL - Binder",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	public void TC42242(String UserName, String PassWord) throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		findpolicypage = new findPolicyPage(driver);
		//String policyNo = findpolicypage.findQuotewithActiveState("Policy", "Active");
		rateapolicypage = new rateApolicyPage(driver);
		String searchPolicyNum = "Q09101597-NB17-01";
		rateapolicypage.searchPolicy(searchPolicyNum);
		rateapolicypage.AcceptFromActionDropDown();
		rateapolicypage.isAlertPresent();
		rateapolicypage.identifyPhase();
		rateapolicypage.billingSetup();
		rateapolicypage.coverageDetailsSelect();
		String policyNumber = rateapolicypage.policyNo();
		rateapolicypage.coverageUpdates("Prof Liab-Out", "BINDER", policyNumber);
		//rateapolicypage.coverageUpdates("Prof Liab-Out", "BINDER-EXCESS");
		//rateapolicypage.coverageUpdates("UMB PL-Ins", "BINDER-UMB");
		rateapolicypage.rateFunctionality(policyNumber);
		//rateapolicypage.pdfVerify();
		rateapolicypage.saveOption(policyNumber);
	}
	
	//@Test(description="Hospital Issue Policy Forms- Complete",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	public void TC42665(String UserName, String PassWord) throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new Policy_Binder_Page(driver);
		String searchPolicyNum = "09100275";
		rateapolicypage = new rateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.endorsementFromActionDropDown();
		policybinderpage.endorsPolicy(policyNumber);
		policybinderpage.identifyPhase();
		policybinderpage.rateFunctionality(policyNumber);
		policybinderpage.saveOption(policyNumber);
	}
	
	@Test(description="Hospital Quote", dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	public void TC42238(String UserName, String PassWord) throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		policyquotepage = new Policy_Quote_Page(driver);
		String searchPolicyNum = "09101645";
		rateapolicypage = new rateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);
		/*policyquotepage.CopyOptionFromActionDropDown();
		policyquotepage.changePhaseToQuote();
		policyquotepage.coverageDetailsSelect();
		String policyNumber = policyquotepage.policyNo();
		policyquotepage.coverageUpdates("Prof Liab-Out", "QUOTE-EXCESS", policyNumber);
		//policyquotepage.coverageUpdates("Quote UMB-Out", "QUOTE-UMB", policyNumber);
		//policyquotepage.coverageUpdates("UMB PL-Ins", "INDICATION_UMB", policyNumber);
		policyquotepage.rateFunctionality(policyNumber);
		policyquotepage.saveOption(policyNumber);*/
		
		policyquotepage.clickPreviewTab();
		Thread.sleep(8000);
		PDFReader pdf = new PDFReader();
		pdf.savePDF();
		pdf.verifyPdfContent("Binder 03 16");
	}
	
	//@Test(description = "Hospital Copy to Quote",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	public void TC42245(String UserName, String PassWord) throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new Policy_Binder_Page(driver);
		String searchPolicyNum = "09100200";
		rateapolicypage = new rateApolicyPage(driver);
		rateapolicypage.searchPolicy(searchPolicyNum);
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.copyToQuoteFromActionDropDown(policyNumber);
		policysubmissionpage = new Policy_Submission_Page(driver);
		policysubmissionpage.copyFromActionDropDown(policyNumber);
		policysubmissionpage.changePhaseToIndication();
		policysubmissionpage.saveWip();
	}
	
	//@Test(testName="HospitalIndication",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	   public void TC_Indication(Method method,String UserName, String PassWord) throws Exception{
			
		   	loginpage = new loginPage(driver);
			loginpage.loginToeOasis(UserName, PassWord);
			homepage = new homePage(driver);
			homepage.navigateToPolicyPage();
			indicationpage=new indicationPage(driver);
			indicationpage.create_New();
			indicationpage.create_Quote();
			indicationpage.selectPolicyType();
			policydetailspage=new policyDetailsPage(driver);
			policydetailspage.updatePolicyDetails();
			
			List<WebElement> FirstName=policydetailspage.open_Underwriter();
			policydetailspage.add_Underwriter(FirstName, "Arwood, Ruth", "Claims Rep","Angelly, Sandy");
			policydetailspage.add_Underwriter(FirstName, "Arwood, Ruth", "Risk Mgmt","Civali, Karen");
			policydetailspage.close_Underwriter();
			policydetailspage.addAgent();
			policydetailspage.addRiskInformation();
			policydetailspage.addCoverage();
			
			policydetailspage.selectCoverageFromPopupList("01012014", "5000", "Excess Liab-Out", "Claims Made","Premium text box", "Retro Date");
			policydetailspage.closeAddCoveragetab();
			
			policydetailspage.selectCoverageFromGridList("Prof Liab-Out");
			policydetailspage.addDataInCoverage("01012014", "5000", "Retro Date", "Premium text box");
			
			policydetailspage.selectCoverageFromGridList("Gen Liab-Out");
			policydetailspage.addDataInCoverage("01012014", "5000", "Retro Date", "Premium text box");
			
			policydetailspage.selectCoverageFromGridList("UMB PL-Ins");
			policydetailspage.addDataInCoverage("01012014", "5000", "Retro Date", "Premium text box");
			
			policydetailspage.selectCoverageFromGridList("Emp Benefit-Out");
			policydetailspage.addRetroDateInCoverage("01012014", "Retro Date");
			
			policydetailspage.selectCoverageFromGridList("Adm/Reg-Ins");
			policydetailspage.addRetroDateInCoverage("01012014", "Retro Date");
			
			policydetailspage.selectCoverageFromGridList("First Aid-Ins");
			policydetailspage.addRetroDateInCoverage("01012014", "Retro Date");
			
			policydetailspage.selectCoverageFromGridList("Evacuation-Ins");
			policydetailspage.addRetroDateInCoverage("01012014", "Retro Date");
			
			policydetailspage.selectCoverageFromGridList("Loss Earn-Ins");
			policydetailspage.addRetroDateInCoverage("01012014", "Retro Date");
					
			policydetailspage.selectCoverageFromGridList("Prof Liab-Out");
			policydetailspage.addCoverageClass();
			
			String PolicyNo= policydetailspage.policyNo();
			policydetailspage.coverageUpdates("Prof Liab-Out", "INDICATION", PolicyNo);
			policydetailspage.coverageUpdates("Excess Liab-Out", "INDICATION-EXCESS", PolicyNo);
			policydetailspage.coverageUpdates("UMB PL-Ins", "INDICATION-UMB", PolicyNo);
					
			policydetailspage.openLimitSharingTab(PolicyNo);
			policydetailspage.addSharedGroup("Fire Dmg-Ins","PL Shared", PolicyNo);
			policydetailspage.addSharedGroup("Loss Earn-Ins","GL Shared", PolicyNo);
			policydetailspage.closeLimitSharingtab();
		}
	
	//@Test(testName="EndorsePolicy",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	public void TC42530(Method method,String UserName, String PassWord) throws Exception{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		endorsepolicypage = new endorsePolicyPage(driver);
		endorsepolicypage.findPolicy();
	}
	
	//This is additional test, removed later from rally
	//@Test(testName="VerifyExistingPolicy",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
	public void TC42536(String UserName, String PassWord) throws Exception{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord);
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new rateApolicyPage(driver);
		String searchPolicyNum = "09100275";
		rateapolicypage.searchPolicy(searchPolicyNum);
	}

	//This is additional test, added later in rally
	//@Test(description= "Quick_Add_Organisation",dataProvider = "userTestData", dataProviderClass=ExcelApiTest.class)
		public void Quick_Add(String UserName, String PassWord) throws Exception{
			loginpage = new loginPage(driver);
			loginpage.loginToeOasis(UserName, PassWord);
			homepage = new homePage(driver);
			homepage.navigateToCISPage();
			Thread.sleep(3000);
			quick_add_orgpage=new quick_Add_Organisation(driver);
			quick_add_orgpage.navigate_To_Add_Org_Window();
			quick_add_orgpage.add_Org_Information();
			quick_add_orgpage.add_Org_Address();
			Thread.sleep(3000);
			quick_add_orgpage.selectZipCode();
			quick_add_orgpage.add_Phone_Number();
		}
	
	@AfterMethod
	public void logoffFromAppclication(ITestResult result) throws IOException, InterruptedException
	{
		//homepage.logoutFromeOasis();
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
		//driver.close();
	}
	
	@AfterClass
	public void closeBrowser()
	{	
		//ExtentReporter.report.close();
		//driver.quit();
	}
}


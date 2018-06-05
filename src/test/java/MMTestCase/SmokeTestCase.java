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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mm.browsers.BrowserTypes;
import com.mm.pages.Policy_Binder_Page;
import com.mm.pages.Policy_Quote_Page;
import com.mm.pages.Policy_Submission_Page;
import com.mm.pages.cisPage;
import com.mm.pages.endorsePolicyPage;
import com.mm.pages.findPolicyPage;
import com.mm.pages.homePage;
import com.mm.pages.Policy_Indication_Page;
import com.mm.pages.loginPage;
import com.mm.pages.quick_Add_Organisation;
import com.mm.pages.rateApolicyPage;
import com.mm.utils.ExtentReporter;
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
	quick_Add_Organisation quick_add_orgpage;
	Policy_Indication_Page policyindicationpage;
	endorsePolicyPage endorsepolicypage;
	
	@BeforeMethod
	public void Setup(Method method) throws Exception
	{
		ExtentReporter.logger=ExtentReporter.report.startTest(method.getName());
	
	}
	
//	@Test(description="Verify Add Organization")
	public void TC42404() throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new homePage(driver);
		homepage.navigateToCISPage();
		cispage = new cisPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage();
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();
	}
	
//	@Test(description="RateAPolicy")
	public void RateaPolicy() throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new rateApolicyPage(driver);
		//rateapolicypage.policySearch();
		
		rateapolicypage.searchPolicy("Q09101421-NB17-01");
		rateapolicypage.saveRatedetails();
		rateapolicypage.startExcelExport();
	}
	
	//@Test(description="HPL - Binder")
	public void TC42242() throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
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
	
//	@Test(description="Hospital Issue Policy Forms- Complete")
	public void TC42665() throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new Policy_Binder_Page(driver);
		String searchPolicyNum = "09100275";
		policybinderpage.searchPolicy(searchPolicyNum);
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.endorsementFromActionDropDown();
		policybinderpage.endorsPolicy(policyNumber);
		policybinderpage.identifyPhase();
		policybinderpage.rateFunctionality(policyNumber);
		policybinderpage.saveOption(policyNumber);
		policyquotepage.exit_SaveOption();
		
		
		
	}
	
	
	
	//@Test(description="Hospital Renewal - Complete")
	public void TC_42400() throws Exception{
		
		String policy_no="09101644"; 
				
		
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage= new rateApolicyPage(driver);
		rateapolicypage.searchPolicy(policy_no);
		
		policyquotepage=new Policy_Quote_Page(driver);
		policyquotepage.select_policyAction("Renewal");
		policyquotepage.save_CaptureTransactionDetails();
		policyquotepage.saveOption("Renewal Quote");
		policyquotepage.switchToNextFrame();
		policyquotepage.save_CaptureTransactionDetails();
		policyquotepage.saveOption("Official");
		policyquotepage.product_Notify();
		policyquotepage.exit_SaveOption();
		
		
	}
	
	
	
//	@Test(description="Hospital Quote")
	public void TC42238() throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		policyquotepage = new Policy_Quote_Page(driver);
		String searchPolicyNum = "09100275";
		policyquotepage.searchPolicy(searchPolicyNum);
		policyquotepage.CopyOptionFromActionDropDown();
		policyquotepage.changePhaseToQuote();
		policyquotepage.coverageDetailsSelect();
		String policyNumber = policyquotepage.policyNo();
		policyquotepage.coverageUpdates("Prof Liab-Out", "QUOTE-EXCESS", policyNumber);
		//policyquotepage.coverageUpdates("Quote UMB-Out", "QUOTE-UMB", policyNumber);
		//policyquotepage.coverageUpdates("UMB PL-Ins", "INDICATION_UMB", policyNumber);
		policyquotepage.rateFunctionality(policyNumber);
		policyquotepage.saveOption("Official");
		policyquotepage.exit_SaveOption();
	}
	
	//@Test(description = "Hospital Copy to Quote")
	public void TC42245() throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		policybinderpage = new Policy_Binder_Page(driver);
		String searchPolicyNum = "09100200";
		policybinderpage.searchPolicy(searchPolicyNum);
		String policyNumber = policybinderpage.policyNo();
		policybinderpage.copyToQuoteFromActionDropDown(policyNumber);
		policysubmissionpage = new Policy_Submission_Page(driver);
		policysubmissionpage.copyFromActionDropDown(policyNumber);
		policysubmissionpage.changePhaseToIndication();
		policysubmissionpage.saveWip();
	}
	
	
	
	@Test(testName="HospitalIndication")
	   public void TC_Indication(Method method) throws Exception{
			
		   	loginpage = new loginPage(driver);
			loginpage.loginToeOasis("UserName", "Password");
			homepage = new homePage(driver);
			homepage.navigateToPolicyPage();
			homepage.create_New();
			String ParentWindow=homepage.create_Quote();
			homepage.search_Quote(ParentWindow);
			homepage.selectPolicyType();
			policysubmissionpage=new Policy_Submission_Page(driver);
			policysubmissionpage.updatePolicyDetails();
			
			policyindicationpage =new Policy_Indication_Page(driver);
			List<WebElement> FirstName=policyindicationpage.open_Underwriter();
			policyindicationpage.add_Underwriter(FirstName, "Arwood, Ruth", "Claims Rep","Angelly, Sandy");
			policyindicationpage.add_Underwriter(FirstName, "Arwood, Ruth", "Risk Mgmt","Civali, Karen");
			policyindicationpage.close_Underwriter();
			policyindicationpage.addAgent();
			policyindicationpage.addRiskInformation();
			policyindicationpage.addCoverage();
			
			policyindicationpage.selectCoverageFromPopupList("01012014", "5000", "Excess Liab-Out", "Claims Made","Premium text box", "Retro Date");
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
			
			String PolicyNo= policyindicationpage.policyNo();
			policyindicationpage.coverageUpdates("Prof Liab-Out", "INDICATION", PolicyNo);
			policyindicationpage.coverageUpdates("Excess Liab-Out", "INDICATION-EXCESS", PolicyNo);
			policyindicationpage.coverageUpdates("UMB PL-Ins", "INDICATION-UMB", PolicyNo);
					
			policyindicationpage.openLimitSharingTab(PolicyNo);
			policyindicationpage.addSharedGroup("Fire Dmg-Ins","PL Shared", PolicyNo);
			policyindicationpage.addSharedGroup("Loss Earn-Ins","GL Shared", PolicyNo);
			policyindicationpage.closeLimitSharingtab();
			
		}
	
	
//	@Test(testName="EndorsePolicy")
	public void TC42530(Method method) throws Exception{
		
		
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis("UserName", "Password");
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		endorsepolicypage = new endorsePolicyPage(driver);
		endorsepolicypage.findPolicy();
		
	}
	
	
	
	
	//This is additional test, removed later from rally
//	@Test(testName="VerifyExistingPolicy")
	public void TC42536() throws Exception{
		
	loginpage = new loginPage(driver);
	loginpage.loginToeOasis("UserName", "Password");
	homepage = new homePage(driver);
	homepage.navigateToPolicyPage();
	rateapolicypage = new rateApolicyPage(driver);
	String searchPolicyNum = "09100275";
	rateapolicypage.searchPolicy(searchPolicyNum);
	
	}
	
	
	//This is additional test, added later in rally
	//@Test(description= "Quick_Add_Organisation")
		public void Quick_Add() throws Exception{
			
			loginpage = new loginPage(driver);
			loginpage.loginToeOasis("UserName", "Password");
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
		ExtentReporter.report.close();
		//driver.quit();
	}
}


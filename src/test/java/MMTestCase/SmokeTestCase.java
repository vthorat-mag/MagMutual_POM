package MMTestCase;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import javax.net.ssl.SSLEngineResult.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.mm.browsers.BrowserTypes;
import com.mm.listeners.testListeners;
import com.mm.pages.cisPage;
import com.mm.pages.findPolicyPage;
import com.mm.pages.homePage;
import com.mm.pages.loginPage;
import com.mm.pages.rateApolicyPage;
import com.mm.utils.ExtentReporter;
import com.mm.utils.commonUtilities;
import com.relevantcodes.extentreports.LogStatus;


public class SmokeTestCase extends BrowserTypes {
	
	WebDriver driver = BrowserTypes.getDriver();
	loginPage loginpage;
	cisPage cispage;
	homePage homepage;
	rateApolicyPage rateapolicypage;
	findPolicyPage findpolicypage;
	
	@BeforeMethod
	public void loginToeOasis() throws Exception
	{
		testListeners testlist = new testListeners();
	}
	
	//@Test(description="Verify Add Organization")
	public void TC42404() throws Exception
	{
		ExtentReporter.logger=ExtentReporter.report.startTest("TC42404");
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis();
		ExtentReporter.logger.log(LogStatus.INFO, "User logged into application sucessfully.");
		ExtentReporter.logger.log(LogStatus.INFO, "Started Add org  test");
		homepage = new homePage(driver);
		homepage.navigateToCISPage();
		cispage = new cisPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage();
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();
	}
	
	//@Test(description="Hospital Rate")
	public void TC42239() throws Exception
	{
		ExtentReporter.logger=ExtentReporter.report.startTest("TC42239");
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis();
		ExtentReporter.logger.log(LogStatus.INFO, "User logged into application sucessfully.");
		ExtentReporter.logger.log(LogStatus.INFO, "Started Add rate  test");
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new rateApolicyPage(driver);
		//rateapolicypage.policySearch();
		rateapolicypage.saveRatedetails();
		rateapolicypage.startExcelExport();
	}
	
	@Test(description="HPL - Binder")
	public void TC42541() throws Exception
	{
		ExtentReporter.logger=ExtentReporter.report.startTest("TC42541");
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis();
		ExtentReporter.logger.log(LogStatus.INFO, "User logged into application sucessfully.");
		ExtentReporter.logger.log(LogStatus.INFO, "Started HPL - Binder  test");
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		findpolicypage = new findPolicyPage(driver);
		String policyNo = findpolicypage.findQuotewithActiveState();
		rateapolicypage = new rateApolicyPage(driver);
		rateapolicypage.policySearch(policyNo);
		rateapolicypage.AcceptFromActionDropDown();
		rateapolicypage.isAlertPresent();
		ExtentReporter.logger.log(LogStatus.INFO, "Make sure Phase is set to Binder.");
		//rateapolicypage.identifyPhase();
		rateapolicypage.billingSetup();
		
		rateapolicypage.coverageDetailsSelect();
		rateapolicypage.coverageUpdates("Prof Liab-Out", "BINDER-EXCESS");
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

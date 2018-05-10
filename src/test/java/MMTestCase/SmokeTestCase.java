package MMTestCase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mm.browsers.BrowserTypes;
import com.mm.listeners.testListeners;
import com.mm.pages.cisPage;
import com.mm.pages.homePage;
import com.mm.pages.loginPage;
import com.mm.pages.rateApolicyPage;
import com.mm.utils.ExtentReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SmokeTestCase extends BrowserTypes {
	
	WebDriver driver = BrowserTypes.getDriver();
	loginPage loginpage;
	cisPage cispage;
	homePage homepage;
	rateApolicyPage rateapolicypage;
//	ExtentReporter	extReport;
	
	@BeforeTest	
	public void loginToeOasis() throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis();
	}
	
	@Test(testName ="Verify Add Organization")
	public void TC42404(Method method) throws Exception
	{
		ExtentReporter.logger=ExtentReporter.report.startTest(method.getName());
		//extReport.logger.assignCategory("Smoke Test");
		
		homepage = new homePage(driver);
	
		homepage.navigateToCISPage();
		cispage = new cisPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage();
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();
	}
	
	@Test(testName="Hospital Rate")
	public void TC42239(Method method) throws InterruptedException, AWTException
	{
		ExtentReporter.logger=ExtentReporter.report.startTest(method.getName());
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new rateApolicyPage(driver);
		rateapolicypage.policySearch();
		rateapolicypage.saveRatedetails();
		rateapolicypage.startExcelExport();
	}
	
	@AfterTest
	public void logoffFromAppclication()
	{
		
		ExtentReporter.report.endTest(ExtentReporter.logger);
		
		homepage.logoutFromeOasis();
		driver.quit();
	}

	@AfterClass
	public void closeBrowser()
	{
		ExtentReporter.report.flush();
		ExtentReporter.report.close();
		
	}
}

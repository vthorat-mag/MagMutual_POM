package MMTestCase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mm.browsers.BrowserTypes;
import com.mm.listeners.testListeners;
import com.mm.pages.cisPage;
import com.mm.pages.homePage;
import com.mm.pages.loginPage;
import com.mm.pages.rateApolicyPage;

public class SmokeTestCase extends BrowserTypes {
	
	WebDriver driver = BrowserTypes.getDriver();
	loginPage loginpage;
	cisPage cispage;
	homePage homepage;
	rateApolicyPage rateapolicypage;
	
	@BeforeMethod
	public void loginToeOasis() throws Exception
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis();
		testListeners testlist = new testListeners();
	}
	
	@Test(description="Verify Add Organization")
	public void TC42404() throws Exception
	{
		homepage = new homePage(driver);
		homepage.navigateToCISPage();
		cispage = new cisPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage();
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();
	}
	
	@Test(description="Hospital Rate")
	public void TC42239() throws InterruptedException, AWTException
	{
		homepage = new homePage(driver);
		homepage.navigateToPolicyPage();
		rateapolicypage = new rateApolicyPage(driver);
		rateapolicypage.policySearch();
		rateapolicypage.saveRatedetails();
		rateapolicypage.startExcelExport();
	}
	
	@AfterMethod
	public void logoffFromAppclication()
	{
		homepage.logoutFromeOasis();
	}

	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}
}

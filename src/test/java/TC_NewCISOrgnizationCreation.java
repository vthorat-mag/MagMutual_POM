import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mm.browsers.BrowserTypes;
import com.mm.pages.cisPage;
import com.mm.pages.homePage;
import com.mm.pages.loginPage;

public class TC_NewCISOrgnizationCreation extends BrowserTypes {
	
	WebDriver driver = BrowserTypes.getDriver();
	loginPage loginpage;
	cisPage cispage;
	homePage homepage;
	
	@BeforeMethod
	public void loginToeOasis()
	{
		loginpage = new loginPage(driver);
		loginpage.loginToeOasis();
	}
	
	@Test
	public void createNewOrganization() throws InterruptedException
	{
		homepage = new homePage(driver);
		homepage.navigateToCISPage();
		cispage = new cisPage(driver);
		cispage.clickOnNewOrganization();
		cispage.enterDataInNewOrgPage();
		cispage.selectZipCode();
		cispage.saveNewOrgDetails();
	}
	
	@AfterMethod
	public void closeBrowser()
	{
        driver.quit();
	}

}

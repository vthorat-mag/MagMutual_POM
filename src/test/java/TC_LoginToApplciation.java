import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.mm.browsers.BrowserTypes;
import com.mm.pages.loginPage;

public class TC_LoginToApplciation extends BrowserTypes{
	
WebDriver driver = BrowserTypes.getDriver();
loginPage loginpage ;
@Test
public void verifyLoginFunctionality()
{
	loginpage = new loginPage(driver);
	/*loginpage.loginToeOasis();*/
}
}

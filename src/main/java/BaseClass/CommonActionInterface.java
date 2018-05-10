package BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mm.utils.ExtentReporter;

public interface CommonActionInterface {
	

	void navigateTo(String url);
	void enterTextIn(WebElement pageElement, String text, String field);
    void clickButton(WebElement pageElement, String buttonName);
    void waitFor(long ms);
    void close(WebDriver driver);
    void takeScreenShot(String pageTitle);
    String getPageTitle();
    String getText(WebElement pageElement);
    void clearTextBox(WebElement pageElement, String textfield);
    String getAttributeValue(WebElement pageElement, String attributeName);
    void click(WebElement pageElement, String elementName);
    void clear(WebElement pageElement);
    void switchToFrame(WebDriver driver, String uniqId);
    void switchToParentWindowfromframe(WebDriver driver);
    void visibilityOfElement(WebElement pageElement);
    String switchToWindow(WebDriver driver);
    void selectDropdown(WebElement Value,String DropDownOption, String name);
    void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow);
}

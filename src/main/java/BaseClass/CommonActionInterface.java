package BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CommonActionInterface {
	
	void navigateTo(String url);
	void enterTextIn(WebElement pageElement, String text);
    void clickButton(WebElement pageElement);
    void waitFor(long ms);
    void close(WebDriver driver);
    void takeScreenShot(String pageTitle);
    String getPageTitle();
    String getText(WebElement pageElement);
    void clearTextBox(WebElement pageElement);
    String getAttributeValue(WebElement pageElement, String attributeName);
    void click(WebElement pageElement);
    void clear(WebElement pageElement);
    void switchToFrame(WebDriver driver, String uniqId);
    void switchToParentWindowfromframe(WebDriver driver);
    void visibilityOfElement(WebElement pageElement);
    String switchToWindow(WebDriver driver);
    void selectDropdown(WebElement Value,String DropDownOption);
    void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow);
}

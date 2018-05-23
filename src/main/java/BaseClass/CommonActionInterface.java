package BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CommonActionInterface {
	
	void navigateTo(String url);
	void enterTextIn(WebElement pageElement, String text);
    void clickButton(WebDriver driver, WebElement pageElement, String buttonName);
    void waitFor(long ms);
    void close(WebDriver driver);
    void takeScreenShot(String pageTitle);
    String getPageTitle();
    String getText(WebElement pageElement);
    void clearTextBox(WebElement pageElement, String textfield);
    String getAttributeValue(WebElement pageElement, String attributeName);
    void click(WebElement pageElement, String elementName);
//  void clear(WebElement pageElement);
    void switchToFrameUsingId(WebDriver driver, String uniqId);
    void switchToFrameUsingElement(WebDriver driver, WebElement element);
    void switchToParentWindowfromframe(WebDriver driver);
    void visibilityOfElement(WebElement pageElement, String text);
    String switchToWindow(WebDriver driver);
    void selectDropdownByValue(WebElement Value,String DropDownOption, String name);
    void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow);
}

package BaseClass;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CommonActionInterface {
	
	void navigateTo(String url);
	void enterTextIn(WebDriver driver, WebElement pageElement, String text);
    void clickButton(WebDriver driver, WebElement pageElement, String buttonName);
    void waitFor(long ms);
    void close(WebDriver driver);
    void takeScreenShot(String pageTitle);
    String getPageTitle(WebDriver driver, String pageTitle);
    String getText(WebDriver driver, WebElement pageElement);
    void clearTextBox(WebDriver driver, WebElement pageElement, String textfield);
    String getAttributeValue(WebElement pageElement, String attributeName);
    void click(WebDriver driver, WebElement pageElement, String elementName);
//  void clear(WebElement pageElement);
    void switchToFrameUsingId(WebDriver driver, String uniqId);
    void switchToFrameUsingElement(WebDriver driver, WebElement element);
    void switchToParentWindowfromframe(WebDriver driver);
    void visibilityOfElement(WebDriver driver, WebElement pageElement, String text);
    String switchToWindow(WebDriver driver);
    void selectDropdownByValue(WebDriver driver, WebElement Value,String DropDownOption, String name);
    void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow);
    void policySearch(String policyNo, WebElement policySearchTxtBox, WebElement earchBtn);
}


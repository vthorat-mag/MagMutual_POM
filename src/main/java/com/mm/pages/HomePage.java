package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.utils.CommonAction;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends CommonAction {

	//Global Assignment/initialization of variables.
	WebDriver driver;
	
	//Element repository for Home page.
	@FindBy(id = "CIS")
	WebElement cisTab;

	@FindBy(xpath = "//li[@id='Policy']//a[@class='fNiv']")
	WebElement Policy_tab;

	@FindBy(name = "logoff")
	WebElement logoff;

	@FindBy(id = "headerLogoTips")
	WebElement logo;

	@FindBy(id = "topnav_Policy")
	WebElement Policy_link;

	@FindBy(xpath = "//a[@class='topNavCurrentApp']")
	WebElement headerPolicyTab;

	//Constructor to initialize elements on Home page.
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Verify logo is preent on page.
	public void verifyLogoIsAvailable() {

		visibilityOfElement(driver, logo, "DELPHI TECHNOLOGY");
	}

	// Navigate to CIS page.
	public void navigateToCISPage() {
		click(driver, cisTab, "CIS tab");
	}

	// Navigate to policy page from Policy tab.
	public void navigateToPolicyPage() {
		click(driver, Policy_tab, "Policy tab");
		ExtentReporter.logger.log(LogStatus.INFO, "Search Policy Screen is opened");
	}

	// navigate to Policy page from policy link[Header]
	public void navigateToPolicyPageThroughPolicyTab() {
		click(driver, headerPolicyTab, "Policy tab on Header");
	}

	// Logout from application.
	public void logoutFromeOasis() {
		click(driver, logoff, "Logoff button");
		ExtentReporter.logger.log(LogStatus.INFO, "User is logged out from application");
	}
}

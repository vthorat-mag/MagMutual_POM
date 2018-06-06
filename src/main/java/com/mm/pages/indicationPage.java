package com.mm.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mm.utils.ExtentReporter;
import com.mm.utils.commonAction;
import com.mm.utils.properties;
import com.relevantcodes.extentreports.LogStatus;


public class indicationPage extends commonAction {
	
	 WebDriver driver;

	 properties pro=new properties();
	 
	@FindBy(xpath="//li[@id='PM_POLICY_MENU']//a[@class='fNiv isParent']")
	WebElement Policy_tab;

	@FindBy(xpath="//li[@id='PM_NEW_POL_QUOTE_MENU']//a[@class='subMenuLinks isParent']/span")
	WebElement Create_New;

	@FindBy(xpath="//li[@id='PM_CREATE_QUOTE']//a/span")
	WebElement Create_Quote;
	
	@FindBy(name="entitySearch_lastOrOrgName")
	WebElement Last_Org_Name;

	@FindBy(id="CI_ENTITY_SELECT_SCH_SCH")
	WebElement Search_Quote;
	
	//@FindBy(xpath="//div[@id='CCLIENT_NAME' and contains(text()='Gerber, Jon,')]//parent::td//preceding-sibling::td//input[@type='checkbox']")
//	List<WebElement> Select_Entity_Checkbox;
	
	@FindBy(xpath ="//input[@name='chkCSELECTIND']")
	WebElement Select_Entity_Checkbox;
	
	
// @FindBy(xpath="//input[@class='BlueButton' and value = 'Select']")
	@FindBy(id="CI_ENT_SEL_LST_FORM_SEL")
	WebElement Select_Entity;
	
	//Select Policy Type window
	
	@FindBy(name="termEffectiveFromDate")
	WebElement Effe_Date;
	
	@FindBy(name="issueCompanyEntityId")
	WebElement Issue_Comp;

	@FindBy(name="issueStateCode")
	WebElement Issue_State_Code;
	

	@FindBy(id="PM_CREPOL_SRCH")
	WebElement Policy_Search;
	
	@FindBy(id="CPOLICYTYPEDESC")
	WebElement Policy_type;
	
	@FindBy(id="PM_CREPOL_DONE")
	WebElement CrePol_Done;

	
	
	// create policy pop up

	/*	@FindBy(xpath="//li[@id='PM_CREATE_PLC']//a/span")
		WebElement Create_Policy;
		
		@FindBy(name ="entitySearch_lastOrOrgName")
		WebElement Last_org_name;

		@FindBy(name="entity_dateOfBirth")
		WebElement DOB;

		@FindBy(name="licenseProfile_licenseNo")
		WebElement LicenseNo;*/

	
	public indicationPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void create_New() throws InterruptedException{		
		
		Actions action = new Actions(driver);
		action.moveToElement(Policy_tab).click().build().perform();
	
		
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Create_New);
	//	ExtentReporter.logger.log(LogStatus.INFO, "");
			
	}
		
	public void create_Quote() throws InterruptedException{
		
		//waitForElementToLoad(driver, 10, Create_Quote);
		Thread.sleep(2000);
	//	click(Create_Quote, "CReate Quote");
	//	clickButton(driver, Create_Quote, "CReate Quote");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Create_Quote);
		
		ExtentReporter.logger.log(LogStatus.INFO, "Entity Select Search window opens​​​​​​​");

		String parentWindow = switchToWindow(driver);
		
		
	//	waitForElementToLoad(driver, Integer.valueOf(properties.prop.getProperty("High")));
		
		waitForElementToLoad(driver, 10, Last_Org_Name);
		
		enterTextIn(driver, Last_Org_Name, "Test_Automation_V1", "Last Org Name");
		
		
		click(driver, Search_Quote,"Search button");
	//	ExtentReporter.logger.log(LogStatus.INFO, "");
		
		waitForElementToLoad(driver, 30, Select_Entity_Checkbox);
		
		ExtentReporter.logger.log(LogStatus.INFO, "List is populated of risk to select Automated Hospital with today's date");
		
		clickButton(driver, Select_Entity_Checkbox, "Select Entity Checkbox");
		
		ExtentReporter.logger.log(LogStatus.INFO, "Risk is selected");	
		click(driver, Select_Entity_Checkbox, "Check box");
		
		click(driver, Select_Entity, "Select button");
			
		ExtentReporter.logger.log(LogStatus.INFO, "Select Policy Type Window displays");	
		switchToParentWindowfromotherwindow(driver, parentWindow);
	}
		

	public void selectPolicyType() throws InterruptedException{
		
		String Eff_Date="01012017";
		Thread.sleep(1000);
		
		switchToFrameUsingId(driver, "popupframe1");
		Thread.sleep(1000);
		enterTextIn(driver, Effe_Date,Eff_Date, "Effective Date");
		
		selectDropdownByValue(driver, Issue_Comp,"363536755","Issue Company");
		selectDropdownByValue(driver, Issue_State_Code,"GA","Issue State");
		
		click(driver, Policy_Search, "Search button for policy type");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Policy Type window will display below");
		
		//Institution -highlight
		
		click(driver, CrePol_Done, "Done button");
				
		ExtentReporter.logger.log(LogStatus.INFO, "Policy Folder window is opened");
		switchToParentWindowfromframe(driver);

	}
	
	
}

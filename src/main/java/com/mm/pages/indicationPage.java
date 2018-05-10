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

import com.mm.utils.commonAction;



public class indicationPage extends commonAction {
	
	 WebDriver driver;

	@FindBy(xpath="//li[@id='PM_POLICY_MENU']//a[@class='fNiv isParent']")
	WebElement Policy_tab;

	@FindBy(xpath="//li[@id='PM_NEW_POL_QUOTE_MENU']//a[@class='subMenuLinks isParent']/span")
	WebElement Create_New;

	@FindBy(xpath="//li[@id='PM_CREATE_QUOTE']//a/span")
	WebElement Create_Quote;

	@FindBy(id="CI_ENTITY_SELECT_SCH_SCH")
	WebElement Search_Quote;
	
	//@FindBy(xpath="//div[@id='CCLIENT_NAME' and contains(text()='Gerber, Jon,')]//parent::td//preceding-sibling::td//input[@type='checkbox']")
	@FindBy(xpath ="//input[@name='chkCSELECTIND']")
//	List<WebElement> Select_Entity_Checkbox;
	WebElement Select_Entity_Checkbox;
	
	
// @FindBy(xpath="//input[@class='BlueButton' and value = 'Select']")
	@FindBy(id="CI_ENT_SEL_LST_FORM_SEL")
	WebElement Select_Entity;
	
	@FindBy(name="termEffectiveFromDate")
	WebElement Effe_Date;
	
	@FindBy(name="issueCompanyEntityId")
	WebElement Issue_Comp;

	@FindBy(name="issueStateCode")
	WebElement Issue_State_Code;
	

	@FindBy(id="PM_CREPOL_SRCH")
	WebElement Policy_Search;
	
	@FindBy(id="PM_CREPOL_DONE")
	WebElement CrePol_Done;


	
	public indicationPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void create_New(WebDriver driver) throws InterruptedException{		
		
		Actions action = new Actions(driver);
		action.moveToElement(Policy_tab).click().build().perform();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Create_New);
		
	}
		
	public void create_Quote(WebDriver driver) throws InterruptedException{
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Create_Quote);

		String parentWindow = switchToWindow(driver);
		WebDriverWait wait=new WebDriverWait(driver, 60);
		Thread.sleep(4000);
		clickButton(Search_Quote,"Search button for Quote");
		
		wait.until(ExpectedConditions.visibilityOf(Select_Entity_Checkbox));
		js.executeScript("arguments[0].click();", Select_Entity_Checkbox);
		
		clickButton(Select_Entity,"Select button");
		
		Thread.sleep(2000);
		
		switchToParentWindowfromotherwindow(driver, parentWindow);
	}
		


	public void selectPolicyType(WebDriver driver) throws InterruptedException{
		
		String Eff_Date="01012017";
		Thread.sleep(3000);
		
		switchToFrame(driver, "popupframe1");
		Thread.sleep(2000);
		enterTextIn(Effe_Date,Eff_Date, "Effective Date");
		Thread.sleep(2000);
		
		selectDropdown(Issue_Comp,"363536755","Issue Company");
		Thread.sleep(2000);
		selectDropdown(Issue_State_Code,"GA","Issue State");
		Thread.sleep(2000);
		
		clickButton(Policy_Search, "Search button for policy type");
		Thread.sleep(2000);
		clickButton(CrePol_Done, "Done button");
	
	}
	
	
	
	

	
	// create policy pop up

	@FindBy(xpath="//li[@id='PM_CREATE_PLC']//a/span")
	WebElement Create_Policy;
	
	@FindBy(name ="entitySearch_lastOrOrgName")
	WebElement Last_org_name;

	@FindBy(name="entity_dateOfBirth")
	WebElement DOB;

	@FindBy(name="licenseProfile_licenseNo")
	WebElement LicenseNo;

	
	
	
}

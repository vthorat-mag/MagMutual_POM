package com.mm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.dto.HomePageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.ExtentReporter;
import com.mm.utils.TestCaseDetails;
import com.mm.utils.CommonAction;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends CommonAction {

	WebDriver driver;
	HomePageDTO homepageDTO;
	RateApolicyPage rateapolicyPage;
	String findPolicyPageTitleActualText = "Find Policy/Quote";
	String selectPOlicyTypePageTitleActualText = "Select Policy Type";
	String selectPolicyTypePageTitle = "Select Policy Type";
	String entitySearchPageTitle = "Entity Search";
	String addOrganizationquickEntry = "Add Organization Quick Entry";
	String entitySelectSearchPageTitle = "Entity Select Search";
	String entitySelectListPageTitle = "Entity Select List";
	String financePageTitle="Account Search";

	@FindBy(id = "CIS")
	WebElement cisTab;

	@FindBy(xpath = "//li[@id='Policy']//a[@class='fNiv']")
	WebElement Policy_Tab_Home;

	@FindBy(xpath = "//li[@id='PM_POLICY_MENU']//a[@class='fNiv isParent']")
	WebElement Policy_tab;

	@FindBy(id = "headerLogoTips")
	WebElement logo;

	@FindBy(id = "topnav_Policy")
	WebElement Policy_link;

	@FindBy(xpath = "//li[@id='PM_NEW_POL_QUOTE_MENU']//a[@class='subMenuLinks isParent']/span")
	WebElement Create_New;

	@FindBy(xpath = "//li[@id='PM_CREATE_QUOTE']//a/span")
	WebElement Create_Quote;

	@FindBy(name = "logoff")
	WebElement logoff;

	@FindBy(name = "entitySearch_lastOrOrgName")
	WebElement Last_Org_Name;

	@FindBy(id = "CI_ENTITY_SELECT_SCH_SCH")
	WebElement searchEntityBtn;

	@FindBy(xpath = "//input[@name='chkCSELECTIND']")
	WebElement Select_Entity_Checkbox;

	@FindBy(id = "CI_ENT_SEL_LST_FORM_SEL")
	WebElement Select_Entity;
	
	@FindBy(id="CPOLICYNO")
	WebElement firstpolicyNumFromPolicyCount;

	// Select Policy Type window

	@FindBy(name = "termEffectiveFromDate")
	WebElement Effe_Date;

	@FindBy(name = "issueCompanyEntityId")
	WebElement Issue_Comp;

	@FindBy(name = "issueStateCode")
	WebElement Issue_State_Code;

	@FindBy(id = "PM_CREPOL_SRCH")
	WebElement Policy_Search;

	@FindBy(id = "CPOLICYTYPEDESC")
	WebElement Policy_type;

	@FindBy(id = "PM_CREPOL_DONE")
	WebElement createPolicyDoneBtn;

	@FindBy(id = "pageTitleForpageHeader")
	WebElement findPolicyPageTitle;

	@FindBy(xpath = "//a[@id='topnav_Policy']")
	WebElement headerPolicyTab;

	@FindBy(name = "policyNoCriteria")
	WebElement policyOrQuoteNum;

	@FindBy(id = "PM_SPOL_SEARCH")
	WebElement searchCriteria;
	
	@FindBy(id = "CPOLICYSTATUSLOVLABEL")
	List<WebElement> policyStatus;

	// @FindBy(xpath = "//a[@class='gridcontent']//span[@id='CPOLICYNO']") - BTS
	@FindBy(xpath = "//*[@id='findPolicyListGrid_CPOLICYNO_0_HREF'] | //a[@class='gridcontent']//span[@id='CPOLICYNO']")
	WebElement policyNumFromPolicyCount;
	
	@FindBy(id = "CPOLICYNO")
	List<WebElement> policyName;

	@FindBy(id = "pageTitleForpageHeaderForPolicyFolder") // seperate for BTS and QA
	WebElement pageHeaderForPolicyFolder; 

	@FindBy(id = "PM_COMMON_TABS_RATE")
	WebElement RateBtn;

	@FindBy(xpath = "//span[@class='txtOrange']")
	WebElement pageLoader;
	
	@FindBy(id = "topnav_FM")
	WebElement FinanceTabMenu;
	
	@FindBy(id = "topnav_Claims")
	WebElement claimsPageLink;
	
	@FindBy(id = "topnav_FM")
	WebElement financePageLink;

	@FindBy(name="entitySearch_addlField")
	WebElement vendorID;
	
	

	// Constructor to initialize driver, page elements and DTO PageObject for
	// HomePage
	public HomePage(WebDriver driver) throws IllegalArgumentException, IllegalAccessException, SecurityException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		homepageDTO = new HomePageDTO(TestCaseDetails.testDataDictionary);
	}

	// Verify logo is present on page.
	public void verifyLogoIsAvailable() {
		visibilityOfElement(driver, logo, "DELPHI TECHNOLOGY");
	}

	// Verify user Navigated to Policy page when clicked on Policy tab present
	// on Header.
	public RateApolicyPage navigateToPolicyPageUsingHeaderPolicyLink() throws Exception {
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click policy in right corner of screen. Search Policy Screen is opened");
		clickButton(driver, headerPolicyTab, "Policy (from header");
		
		getPageTitle(driver, findPolicyPageTitleActualText);
		
		return new RateApolicyPage(driver);
	}
	
	
	// Navigate to CIS page.
	public CISPage navigateToCISPage() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click CIS in right corner of screen. CIS Entity Search page opens");
		click(driver, cisTab, "CIS tab");
		getPageTitle(driver, entitySearchPageTitle);
		return new CISPage(driver);
	}

	// Navigate to policy page from Policy tab.
	public HomePage navigateToPolicyPage() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click policy in right corner of screen & verify search Policy Screen is opened");
		waitForElementToLoad(driver, 10, Policy_Tab_Home);
		click(driver, Policy_Tab_Home, "Policy tab");
		Thread.sleep(2000);
  	getPageTitle(driver, findPolicyPageTitleActualText);
		return new HomePage(driver);
	}


	// Navigate to policy page using Policy tab from EndorsePolicyPage.
	public EndorsePolicyPage navigateToPolicyPageFromEndorsePolicyPage() throws Exception {
		navigateToPolicyPage();
		return new EndorsePolicyPage(driver);
	}
	
	//THis method will navigate to policy page from header PolicyTab link
	public RateApolicyPage naviagetToPolicyFromHeaderLink() throws Exception
	{
		clickButton(driver, Policy_link, "Policy Header Link");
		return new RateApolicyPage(driver);
	}

	// Navigate to policy page using Policy tab from rateApolicyPage.
	public RateApolicyPage navigateToPolicyPageFromrateApolicyPage() throws Exception {
		navigateToPolicyPage();
		return new RateApolicyPage(driver);
	}
	
	
	public FinancePage navigateToFinanceHomePage() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Click Finance in right corner of screen.");
		clickButton(driver, FinanceTabMenu, "Finanace Tab");
		return new FinancePage(driver);
	}

	// Navigate to policy page using Policy tab from rateApolicyPage.
	public RateApolicyPage navigateToPolicyPageFromPolicyBinderPage() throws Exception {
		navigateToPolicyPage();
		return new RateApolicyPage(driver);
	}

	// Navigate to Policy page from policy link[Header]
	public HomePage navigateToPolicyPageThroughPolicyHeaderLink() throws Exception {
		Thread.sleep(2000);
		click(driver, headerPolicyTab, "Policy tab on Header");
		return new HomePage(driver);
	}

	// Navigate to Claims page from Home Page[Header]
	public ClaimsPage navigateToClaimsPageFromHomePageLink() throws Exception {
		click(driver, claimsPageLink, "Claims link on Home Page");

		return new ClaimsPage(driver);
	}
	
	// Navigate to Finance page from header link
	public FinancePage navigateToFinancePageFromHeaderLink() throws Exception{
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Finance in right corner of screen. Verify Finance page opens");
		clickButton(driver, financePageLink, "Finance Link");
		invisibilityOfLoader(driver);
		Thread.sleep(5000);
		getPageTitle(driver, financePageTitle);
		return new FinancePage(driver);
	}
	

	// Logout from application.
	public void logoutFromeOasis() {
		ExtentReporter.logger.log(LogStatus.INFO, "User is logged out from application");
		click(driver, logoff, "Logoff button");
	}

	
	// Search a policy using Search Criteria tab and select a policy from count
	// tab
	public String policySearchUsingSearchCriteria() throws Exception {
		waitForElementToLoad(driver, 20, policyOrQuoteNum);

		// Enter policy number in Policy/Quote# text field
		ExtentReporter.logger.log(LogStatus.INFO, "Enter in Hospital or Facility # from 'Issue Policy Form' test case, click Search. Verify Policy # displays correctly under Policy Count tab");
		clearTextBox(driver, policyOrQuoteNum, "Policy/Quote#");
		enterTextIn(driver, policyOrQuoteNum, homepageDTO.policyNum, "Policy/Quote#");
		clickButton(driver, searchCriteria, "Search");
		Thread.sleep(2000);
		// Select the first policy from the search results under Count tab
		ExtentReporter.logger.log(LogStatus.INFO, "Click the Policy number under Policy/Quote# column. Full Policy displays when web cycles to active policy window");
		click(driver, policyNumFromPolicyCount, homepageDTO.policyNum);
		invisibilityOfLoader(driver);
		rateapolicyPage = new RateApolicyPage(driver);

		// Verify that page title is correct
		String policyNumber = rateapolicyPage.policyNo();
	    getPageTitleWithPolicyNumber(driver, policyNumber);

		return policyNumber;

	}
	
	public String backUpPolicySearchUsingSearchCriteria() throws Exception {
		waitForElementToLoad(driver, 20, policyOrQuoteNum);

		// Enter policy number in Policy/Quote# text field
		ExtentReporter.logger.log(LogStatus.INFO, "Enter in Hospital or Facility # from 'Issue Policy Form' test case, click Search. Verify Policy # displays correctly under Policy Count tab");
		clearTextBox(driver, policyOrQuoteNum, "Policy/Quote#");
		enterTextIn(driver, policyOrQuoteNum, homepageDTO.policyNum, "Policy/Quote#");
		clickButton(driver, searchCriteria, "Search");
		Thread.sleep(2000);
		// Select the first policy from the search results under Count tab
		ExtentReporter.logger.log(LogStatus.INFO, "Click the Policy number under Policy/Quote# column. Full Policy displays when web cycles to active policy window");
		click(driver, policyNumFromPolicyCount, homepageDTO.backUpPpolicyNum);
		invisibilityOfLoader(driver);
		rateapolicyPage = new RateApolicyPage(driver);

		// Verify that page title is correct
		String policyNumber = rateapolicyPage.policyNo();
	    getPageTitleWithPolicyNumber(driver, policyNumber);

		return policyNumber;

	}
	
	
	// Move to Policy tab and select Create New option from menu
	public HomePage create_New()
			throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		waitForElementToLoad(driver, 20, Policy_tab);
		Actions action = new Actions(driver);
		action.moveToElement(Policy_tab).click().build().perform();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Create_New);
		return new HomePage(driver);
	}

	/*
	 * Entity Select Search window appears and then we enter Organization name
	 * and search Then select the Organization name from populated list.
	 */
	public HomePage searchEntity(String lastOrgNameValue,String vendorIDValue)throws Exception {

		waitForElementToLoad(driver, 20, Last_Org_Name);
		getPageTitle(driver, entitySelectSearchPageTitle);
		visibilityOfElement(driver, Last_Org_Name, "Last Org Name on Entity Select Search window");
		ExtentReporter.logger.log(LogStatus.INFO, "Enter New Organization that was just created (ex.Automated Hospital(date)).Verify list will populate of risk to select Organization with today's date");
		enterTextIn(driver, Last_Org_Name, lastOrgNameValue, "Last Org Name");
		enterDataIn(driver, vendorID, vendorIDValue, "Vendor ID");
		click(driver, searchEntityBtn, "Search button");
		return new HomePage(driver);
	}
	
	public HomePage selectEntity(String parentWindow) throws Exception{
		Thread.sleep(6000);
		invisibilityOfLoader(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Click the checkbox next to the name to select the risk. Verify Risk is selected");
		clickButton(driver, Select_Entity_Checkbox, "Select Entity Checkbox");
		ExtentReporter.logger.log(LogStatus.INFO, "Click Select. Verify Select Policy Type Window displays");
		Thread.sleep(1000);
		click(driver, Select_Entity, "Select button");
	//	Thread.sleep(2000);
	/*	if(isAlertPresent(driver)==true){
			acceptAlert(driver);
			clickButton(driver, Select_Entity_Checkbox, "Select Entity Checkbox");
			click(driver, Select_Entity, "Select button");
		}*/
		switchToParentWindowfromotherwindow(driver, parentWindow);

		return new HomePage(driver);

	}

	// Selecting Policy type by adding Effective date, Issue company,state and
	// click done.
	public PolicySubmissionPage selectPolicyTypeForBTS()
			throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		Thread.sleep(2000);
		switchToFrameUsingId(driver, "popupframe1");    //  for BTS
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Enter/Select the below information Effective Date: Enter Today's Date"+
				"Issue Company: Select 'Professional Security Insurance' " 
				+"Issue State:Select GA. Click Search. Verify Policy Type window will display below");
		//getPageTitle(driver, selectPolicyTypePageTitle);      
		//TODO- clarify, Page title is different in QA
		//Verify Select Policy Type window appeared, enter Effective date,Issue company,state and click Search
		enterTextIn(driver, Effe_Date, homepageDTO.effectiveFromDate, "Effective Date");
		selectDropdownByValue(driver, Issue_Comp, homepageDTO.issueCompany, "Issue Company");
		selectDropdownByValue(driver, Issue_State_Code, homepageDTO.issueState, "Issue State");
		click(driver, Policy_Search, "Search button for policy type");
		Thread.sleep(2000);
		click(driver, Policy_type, "Policy Type");
		ExtentReporter.logger.log(LogStatus.INFO, "Select(Highlight) Policy Type: Institution. Click Done. Verify Policy Folder window will open");
		click(driver, createPolicyDoneBtn, "Done button");
		
		switchToParentWindowfromframe(driver);                    // for BTS
		return new PolicySubmissionPage(driver);

	}

	
	public PolicySubmissionPage selectPolicyTypeForQA()
			throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		Thread.sleep(2000);
		String parentWindow = switchToWindow(driver);     
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Enter/Select the below information Effective Date: Enter Today's Date"+
				"Issue Company: Select 'Professional Security Insurance' " 
				+"Issue State:Select GA. Click Search. Verify Policy Type window will display below");
		//getPageTitle(driver, selectPolicyTypePageTitle);           //TODO- clarify, Page title is different in QA
		//Verify Select Policy Type window appeared, enter Effective date,Issue company,state and click Search
		enterTextIn(driver, Effe_Date, homepageDTO.effectiveFromDate, "Effective Date");
		selectDropdownByValue(driver, Issue_Comp, homepageDTO.issueCompany, "Issue Company");
		selectDropdownByValue(driver, Issue_State_Code, homepageDTO.issueState, "Issue State");
		click(driver, Policy_Search, "Search button for policy type");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Select(Highlight) Policy Type: Institution. Click Done. Verify Policy Folder window will open");
		click(driver, Policy_type, "Policy Type");
		click(driver, createPolicyDoneBtn, "Done button");
		
		switchToParentWindowfromotherwindow(driver, parentWindow);  //For QA
		return new PolicySubmissionPage(driver);

	}
	
	
	// Select Create Quote option from POlicy tab menu
	public String create_Quote() throws InterruptedException {
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Hover over Policy on blue menu bar Move mouse over Create New to display sub menu Select Create Quote.Verify Entity Select Search window opens");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Create_Quote);
		String parentWindow = switchToWindow(driver);
		return parentWindow;
	}
}

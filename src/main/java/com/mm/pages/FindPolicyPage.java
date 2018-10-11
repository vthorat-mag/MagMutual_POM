package com.mm.pages;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.utils.TestCaseDetails;
import com.mm.dto.FindPolicyPageDTO;
import com.mm.utils.CommonAction;
import com.relevantcodes.extentreports.LogStatus;

public class FindPolicyPage extends CommonAction {

	// Global Assignment/initialization of variables.
	WebDriver driver;
	String policyNo;
	String selectedPhaseValue = "Policy";
	String selectedStatusValue = "Active";

	// Element repository for Find a policy page.
	@FindBy(xpath = "//input[@name='policyPhaseCodeMultiSelectText']")
	WebElement policyPhaseTextBox;

	@FindBy(xpath = "//a[@class='panelDownTitle']")
	WebElement paneDown;

	@FindBy(xpath = "//a[@id='AFD_policyStatus']//img[@id='btnFind']")
	WebElement policyStatusSearch;

	//@FindBy(xpath = "//table[@class='popupcontent']//input[@name='chkMultiSelectpolicyStatus']")
	@FindBy(xpath = "//input[@name='chkMultiSelectpolicyStatus']")
	List<WebElement> policyStatusValues;

	@FindBy(xpath = "//a[@id='AFD_policyPhaseCode']")
	WebElement policyPhaseSearch;

	@FindBy(xpath = "//table[@class='popupcontent']//td[2]")
	List<WebElement> policyPhaseValues;

	@FindBy(name = "chkMultiSelectpolicyPhaseCode")
	List<WebElement> policyPhaseCheckBox;

	@FindBy(xpath = "//div[@class='horizontalButtonCollection']//input[@value='Search']")
	WebElement searchBtn;
								
	//@FindBy(xpath = "//a[@id='findPolicyListGrid_CPOLICYNO_0_HREF']| //table[@class='clsGrid']//tbody//span[@id='CPOLICYNO']")
	//List<WebElement> policyList;
	
	@FindBy(xpath = "//a[contains(@id,'findPolicyListGrid_CPOLICYNO')]")
	List<WebElement> policyList;

	@FindBy(xpath = "//table[@class='clsGrid']//tbody//input[@name='chkCSELECT_IND']")
	List<WebElement> policyListChkBox;

	@FindBy(id = "polPhaseCodeLOVLABELSPAN")
	WebElement selectedPhaseValueEle;

	@FindBy(id = "termStatusLOVLABELSPAN")
	WebElement selectedStatusValueEle;
	
	@FindBy(name="termStatusCode")
	WebElement termStatus;
	
	@FindBy(name="chkMultiSelectpolicyTypeCode")
	List <WebElement> policyTypeList;
	
	@FindBy(xpath="//a[@id='AFD_policyTypeCode']//img")
	WebElement searchPolicyType;
	
	@FindBy(xpath = "//li[@id='PM_POLICY_MENU']//a[@class='fNiv isParent']")
	WebElement policyTab;
	
	@FindBy(xpath = "//li[@id='PM_FIND_POLICY']//a/span")
	WebElement findPolicy;
	
	@FindBy(name="issueCompanyEntityId")
	WebElement issueCompany;
	
	@FindBy(xpath="//div[@title='next']")
	WebElement nextPageArrow;
	
	//@FindBy(xpath="//div[@class='jqx-listitem-element']//span")
	@FindBy(xpath="//div[@id='listBoxContentinnerListBoxgridpagerlistfindPolicyListGrid']/div/div/span")
	List <WebElement> pageSize; 
	
	@FindBy(xpath="//div[@id='dropdownlistArrowgridpagerlistfindPolicyListGrid']//div")
	WebElement pageSizeDDLArrow;
	
	@FindBy(xpath="//a[@class='pageNextLink']")
	WebElement nextPolicyArrow;
	
	
	// Constructor to initialize elements on Find a policy page.
	public FindPolicyPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		FindPolicyPageDTO findpolicypagedto = new FindPolicyPageDTO(TestCaseDetails.testDataDictionary);
	}

	// Search Policy from filter.
	public String findQuotewithActiveState(String phase, String status) throws InterruptedException {
		invisibilityOfLoader(driver);
		try {
			Assert.assertTrue(paneDown.isEnabled());
			ExtentReporter.logger.log(LogStatus.INFO, "Search Criteria Section is expanded");
			click(driver, paneDown, "Search Criteria");
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.INFO, "Search Criteria Section is already Expanded");
		}
		click(driver, policyPhaseSearch, "Policy Phase Search Icon");
		waitFor(driver, 3);
		for (int i = 0; i < policyPhaseCheckBox.size(); i++) {
			if (policyPhaseCheckBox.get(i).getAttribute("value").equals("POLICY")) {
				click(driver, policyPhaseCheckBox.get(i),
						policyPhaseCheckBox.get(i).getAttribute("value") + " Check Box");
			}
			if (policyPhaseCheckBox.get(i).getAttribute("value").equals(phase)) {
				click(driver, policyPhaseCheckBox.get(i), "Policy phase Check Box");
			}
		}
		click(driver, policyStatusSearch, "Policy Status Search");
		for (int i = 0; i < policyStatusValues.size(); i++) {
			if (policyStatusValues.get(i).getAttribute("value").equals(status)) {
				click(driver, policyStatusValues.get(i),
						policyStatusValues.get(i).getAttribute("value") + " Check Box");
			}
		}
		click(driver, searchBtn, "SearchButton");
		waitFor(driver, 5);
		try {
			if (policyList.size() > 0) {
				for (int i = 0; i < policyList.size(); i++) {
					if (policyList.get(i).getAttribute("innerHTML").contains("NB19")
							&& policyList.get(i).getAttribute("innerHTML").contains("-01")) {
						policyNo = policyList.get(i).getAttribute("innerHTML");
						break;
					}
				}
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, "No Policy available for this Criteria.");
		}
		return policyNo;
	}

	//Code will use to search Policy on Policy Page.
	
	public FindPolicyPage navigateToPolicySearchPage() throws Exception {
		
		Actions action = new Actions(driver);
		action.moveToElement(policyTab).click().build().perform();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", findPolicy);
		return new FindPolicyPage(driver);
	}
	
	
	public FindPolicyPage openSearchPolicyPane() throws Exception {
		invisibilityOfLoader(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "click Policy>Find Policy Select Status: Active Click [Search]");
		// Below code will verify if search pane is expanded or not.
		try {
			Assert.assertTrue(paneDown.isEnabled());
			click(driver, paneDown, "Search Criteria");
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.PASS, "Search Criteria Section is already expanded.");
		}
		return new FindPolicyPage(driver);
	}
	
		public RateApolicyPage searchFromFindPolicyPage() throws Exception {
		// below code will select phase from Policy Phase drop down.
		click(driver, policyPhaseSearch, "Policy Phase Search Icon");
		waitFor(driver, 3);
		for (int i = 0; i < policyPhaseCheckBox.size(); i++) {
			if (policyPhaseCheckBox.get(i).getAttribute("value").equals("POLICY")) {
				clickButton(driver, policyPhaseCheckBox.get(i), FindPolicyPageDTO.phaseValue + " Check Box");
			}
		}
		for (int i = 0; i < policyPhaseCheckBox.size(); i++) {
			if (policyPhaseCheckBox.get(i).getAttribute("value").equals(FindPolicyPageDTO.phaseValue)) {
				clickButton(driver, policyPhaseCheckBox.get(i), FindPolicyPageDTO.phaseValue  + " Check Box");
				ExtentReporter.logger.log(LogStatus.PASS, FindPolicyPageDTO.phaseValue  + " is  selected from Policy phase drop down");
				break;
			}
		}

		// below code will select Status from Status drop down.
		Thread.sleep(3000);
		clickButton(driver, policyStatusSearch, "Policy Status Search");
		for (int i = 0; i < policyStatusValues.size(); i++) {
			if (policyStatusValues.get(i).getAttribute("value").equals(FindPolicyPageDTO.status)) {
				click(driver, policyStatusValues.get(i),
						policyStatusValues.get(i).getAttribute("value") + " Check Box");
				ExtentReporter.logger.log(LogStatus.PASS, FindPolicyPageDTO.status + " is  selected from Status drop down");
				break;
			}
		}
		click(driver, searchBtn, "SearchButton");
		invisibilityOfLoader(driver);
		Thread.sleep(2000);

		
		// Below code will check if the policies displayed in search criteria result match the last Transaction.
		// and will open/select matching policy.
		boolean flag=false;
		try {
			List<String> policyList= new ArrayList<String>();
			
			for(int page=0;page<10;page++) {
			//each page shows only four rows in DOM, so we need to change page to search policy after 4 rows
				for (int row = 0; row <4; row++) {
			
				WebElement lastTranctionColumn=	driver.findElement(By.xpath("//div[@id=\"row"+row+"findPolicyListGrid\"]/div[14]/div"));
				WebElement policyNum=driver.findElement(By.xpath("//div[@id=\"row"+row+"findPolicyListGrid\"]/div[3]/div/a"));
				
				//System.out.println(policyNum.getAttribute("innerHTML"));
				String policyNumValue=policyNum.getAttribute("innerHTML").trim();
				//adding policyNUmber to array list
				
			//	policyList.add(policyNumValue);
			
				
				//compare the value of last Transaction to expected value from data sheet, so select the required policy
				if(lastTranctionColumn.getAttribute("innerHTML").trim().equalsIgnoreCase(FindPolicyPageDTO.lastTransaction)) {
					ExtentReporter.logger.log(LogStatus.INFO, "Select Policy with Policy No." +policyNum.getAttribute("innerHTML"));
					//select the policy from First column if the last transaction is as expected.
					click(driver, policyNum, "Policy Name");
					flag=true;
					break;
				}
			}	
				//if the policy is not found on first page then change page size to 5 and move to next page
				if(flag==false){
				click(driver, pageSizeDDLArrow, "Page Size Down Arrow");
				Thread.sleep(1000);
				click(driver, pageSize.get(0), "Page Size 5");
				Thread.sleep(2000);
				clickButton(driver, nextPageArrow, "Next Page");
				}else {
					break;
				}
		  }	
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, "No Policy available for given Criteria.");
			throw new Exception("No Policy available for given Criteria");
		}
			/*WebElement ele = driver.findElement(By.xpath("//div[@id=\"row6findPolicyListGrid\"]//div[@title='Active']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions = new Actions(driver);
			actions.moveToElement(ele).perform();*/
		Thread.sleep(2000);
		invisibilityOfLoader(driver);

		// Below line of code will verify Selected phase value is correct or
		// not.
		//verifyValueFromField(driver, selectedPhaseValueEle, selectedPhaseValue, "innerHTML","Phase Value");

		// Below line of code will verify Selected status value is correct or
		// not.
		verifyValueFromField(driver, selectedStatusValueEle, selectedStatusValue, "innerHTML","Status Value");
		return new RateApolicyPage(driver);
	}
	
		
	public RateApolicyPage selectNextPolicyFromListUsingForwardArrow() throws Exception {
		
		Thread.sleep(3000);
		try {
		if(nextPolicyArrow.isDisplayed()==true) {
		
			clickButton(driver, nextPolicyArrow, "Arrow for next Policy");
			Thread.sleep(3000);
		}
		}catch(Exception e){
			ExtentReporter.logger.log(LogStatus.INFO, "Next Policy Arrow is not available on policy details page, so policies from list are exhausted.");
			e.printStackTrace();
			assertTrue(false,"There is no substitte policy available in policy list to progress this test.");
		}
		return new RateApolicyPage(driver);
		
	}
	
		
	public FindPolicyPage selectTermStatusAndIssueCompany() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Select Policy Status "+FindPolicyPageDTO.termStatusCode);
		selectDropdownByValue(driver, termStatus, FindPolicyPageDTO.termStatusCode, "Term Status");
		Thread.sleep(1000);
		selectDropdownByValue(driver, issueCompany, FindPolicyPageDTO.issueCompanyValue, "Issue Company");
		return new FindPolicyPage(driver);
	}
	
	public FindPolicyPage selectPolicyType() throws Exception {
		
		clickButton(driver, searchPolicyType, "Policy Search");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Select Policy Type "+FindPolicyPageDTO.policyTypeValue);
		for(int i=0;i<policyTypeList.size();i++) {
			
			if(policyTypeList.get(i).getAttribute("value").trim().equals(FindPolicyPageDTO.policyTypeValue)) {
				selectValue(driver, policyTypeList.get(i), "Policy Type Institutai");
				break;
			}
		}
		return new FindPolicyPage(driver);
	}
}

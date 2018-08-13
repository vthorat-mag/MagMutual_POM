package com.mm.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.IOException;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.mm.dto.FinancePageDTO;
import com.mm.dto.FindPolicyPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.TestCaseDetails;
import com.relevantcodes.extentreports.LogStatus;

import bsh.StringUtil;

public class FinancePage extends CommonAction {

	WebDriver driver;
	FinancePageDTO financePageDTO;
	static String batchNumber;
	static String accountNumber;
	static String invoiceAmount;
	String cancelTypeDDLValue = "COMPANY";
	String cancelReasonDDLValue = "COMPOTHER";
	String cancelMethodDLValue = "PRORATA";
	String onDemandInvoiceExcelName = "OnDemandInvoiceCredit";
	String onDemandInvoiceInstallementExcelSheet = "OnDemandInvoiceInstallementBefore";
	String invoicesInstallmentDueDate = "invoicesInstallmentDueDate";
	String PaymentCreditExcelName = "PaymentCredit";
	String openbatchcreditExcelName = "openbatchcredit";
	String FinancePageExcelName = "FinancePage";
	String validatebatchcreditExcelName = "validatebatchcredit";
	String postedbatchcreditExcelName = "postedbatchcredit";
	String alltransactionlistafterpaymentcreditExcelName = "alltransactionlistafterpaymentcredit";
	String pollnstallmentList = "pollnstallmentList";
	String accountSearchPageTitle = "Account Search";
	String allTxnInquireyPageTitle = "All Transactions Inquiry";
	String validateBatchPageTitle = "Validate Batch";
	String cashEntryPageTitle = "Cash Entry";
	String batchFunctionPageTitle = "Batch Functions";
	String invoiceOptionDDLValue = "I";
	String createChargesDDLValue = "N";
	String specifyInvoiceDateDDLValue = "N";
	String specifyDueDateDDLValue = "N";
	String paymentTypeDDLValue = "CHECK";
	String BatchAmount = "1";
	String BatchNoPayment = "1";
	String validlateFieldExpectedValue = "VALIDATE";
	String validlateFieldAttributeValue = "innerHTML";
	String validlateFieldName = "Validate";
	String onDemandPageTitle = "On Demand Invoice";
	String checkNo = "ST12345";
	String accountNoUnExpectedValue = "";
	String accountNovalueAttributeName = "value";
	String policyNo;
	ExcelUtil exlUtil = new ExcelUtil();	

	@FindBy(name = "globalSearch")
	WebElement Policy_Search;

	@FindBy(id = "FM_ACCOUNT_LIST_SEARCH")
	WebElement searchBtn;

	@FindBy(id = "findPolicyListGrid_CPOLICYNO_0_HREF") // QA
	WebElement policyList;

	@FindBy(name = "policyNo")
	WebElement PolicyNoTxtBox;

	@FindBy(xpath = "//span[@id='CACCOUNTNO'] | //a[@id='accountListGrid_CACCOUNTNO_0_HREF']")
	List<WebElement> accountList;

	@FindBy(id = "CACCOUNTNO")
	WebElement firstAccount;

	@FindBy(id="CACCOUNTNO")
	List<WebElement> accountNumList;

	@FindBy(xpath = "//li[@id='FM_BILLING_ADMIN']//a[@class='fNiv isParent']//span")
	WebElement billingAdminMenu;

	@FindBy(xpath = "//li[@id='FM_ON_DEMAND_INVOICE']//a")
	WebElement onDemandInvoiceMenuOption;

	@FindBy(xpath = "//li[@id='FM_CASH_APPLICATION']//span")
	WebElement paymentsMenu;

	@FindBy(xpath = "//li[@id='FM_CASH_ENTRY']//span")
	WebElement cashEntryMenuOption;

	@FindBy(xpath = "//li[@id='FM_BATCH_FUNCTION']//span")
	WebElement batchFunctionMenuOption;

	@FindBy(name = "invoiceOption")
	WebElement invoiceOptionDDL;

	@FindBy(name = "createCharges")
	WebElement createChargesDDL;

	@FindBy(name = "specifyInvoiceDate")
	WebElement specifyInvoiceDateDDL;

	@FindBy(name = "specifyDueDate")
	WebElement specifyDueDateDDL;

	@FindBy(name = "paymentTypeCode")
	WebElement paymentTypeDDL;

	@FindBy(id = "FM_ON_DEMAND_INV_PROCESS")
	WebElement processButton;

	@FindBy(id = "amountROSPAN")
	WebElement invoiceAmt;

	@FindBy(id = "invoiceNoROSPAN")
	WebElement invoiceNo;

	@FindBy(id = "accountNoCriteriaROSPAN")
	WebElement accountNo;

	@FindBy(id = "FM_CONFIRM_INVOICE_JUMP")
	WebElement jumpButton;

	@FindBy(xpath = "//*[@id = 'btnSaveAsCSV'] | //input[@name='btnSaveAsCSV']")
	List<WebElement> exportExcelLink;
	
	@FindBy(id = "CPRODUCTCOVERAGEDESC")
	List<WebElement> coverageList;

	@FindBy(xpath = "//select[@id='PM_POLICY_FOLDER_AG'] | //select[@id='PM_QT_POLICY_FOLDER_AG']")
	WebElement policyActionDDL;
	
	
	/*@FindBy(xpath = "//table[@id='coverageListGrid']//tbody//td//div[@id='CPRODUCTCOVERAGEDESC']")
	List<WebElement> coverageList;*/
	
	@FindBy(name = "cancellationDate")
	WebElement cancelDateOnCancelPopUp;
	
	@FindBy(id = "accountingDate_VALUE_CONTAINER")
	WebElement accountingDateOnCancelPopUp;
	
	@FindBy(name = "cancellationType")
	WebElement cancelTypeaccountingDateOnCancelPopUp;
	
	@FindBy(name ="cancellationReason")
	WebElement cancelReasonOnCancelPopUp;
	
	@FindBy(name ="cancellationMethod")
	WebElement cancelMethodOnCancelPopUp;
	
	@FindBy(name ="cancellationComments")
	WebElement cancelCommentsCancelPopUp;
	
	@FindBy(id ="CANCEL_DONE")
	WebElement cancelBtnOnCancelPopUp;
	
	@FindBy(id = "FM_CE_BATCH_NEW")
	WebElement newButton;

	@FindBy(name = "invoiceNo")
	WebElement invoiceNoOnCashEntryPage;

	@FindBy(name = "receiptNo")
	WebElement checkNoOnCashEntryPage;

	@FindBy(name = "receiptAmount")
	WebElement amountOnCashEntryPage;

	@FindBy(id = "FM_CE_BATCH_SAVE")
	WebElement saveBtnOnCashEntryPage;

	@FindBy(id = "batchNoROSPAN")
	WebElement batchNo;

	@FindBy(id = "CBATCHNO")
	List<WebElement> batchNoOnBatchFunPage;

	@FindBy(id = "FM_CASH_BATCH_LOG_VALIDAT")
	WebElement validateBatchBtn;

	@FindBy(id = "noOfPayment")
	WebElement numberOfPaymentTxtBox;

	@FindBy(id = "batchAmount")
	WebElement batchAmount;

	@FindBy(id = "FM_VALIDATE_BATCH_DONE")
	WebElement doneBatchPopUp;

	@FindBy(id = "FM_CASH_BATCH_LOG_POST")
	WebElement postBatchBtn;

	@FindBy(id = "CBATCHSTATUSCODE")
	WebElement validateField;

	@FindBy(id = "currentAccountBalanceROSPAN")
	WebElement currBalOnAllTxnEnqPage;

	@FindBy(xpath = "//a[@class='selectedMenu fNiv isParent']//span")
	WebElement accountMenuTab;

	@FindBy(name="accountHolder")
	WebElement accountHolder;
	
	@FindBy(xpath = "//li[@id='FM_MAINTAIN_ACCT_MENU']//a//span")
	WebElement maintainAccount;

	@FindBy(xpath = "//iframe[contains(@id,'popupframe')]")
	WebElement iFrameElement;
	
	@FindBy(xpath = "//div[@id='CLONGDESCRIPTION']")
	List<WebElement> accountType;
	
	@FindBy(xpath = "//input[@name='entitySearch_lastOrOrgName']")
	WebElement lastOrOrgNameInputField;
	
	@FindBy(id = "FM_FIND_NEW_ACCOUNT")
	WebElement newAccountBtn;
	
	@FindBy(id = "CI_ENTITY_SELECT_SCH_SCH")
	WebElement searchBtnEntitySearchPage;
	
	@FindBy(xpath = "//input[@name='chkCSELECTIND']")
	WebElement searchedEntityChkBox;
	
	@FindBy(id = "CI_ENT_SEL_LST_FORM_SEL")
	WebElement selectBtnSearchedEntity;
	
	@FindBy(xpath = "//input[@name='accountNo']")
	WebElement accountNoFieldValue;
	
	@FindBy(id="FM_MAINT_ACCT_SELECT")
	WebElement selectAdressBtn;
	
	@FindBy(id="FM_SEL_ACCT_ADDR_DONE")
	WebElement DoneBtnOnAddListPopUp;
	
	@FindBy(id="FM_MAINT_ACCT_SAVE")
	WebElement SaveBtnOnMaintainActPage;
	
	@FindBy(id = "longDescriptionROSPAN")
	WebElement descriptionAfterSave;
	
	@FindBy(id = "FM_SEL_ACCT_TYPE_DONE")
	WebElement doneBtnOnSelectAccTypePopUp;
	
	@FindBy(name="billingFrequency")
	WebElement billingFrequencyDDL;

	@FindBy(name = "baseBillMmDd")
	WebElement startDate;

	@FindBy(name = "billLeadDays")
	WebElement leadDays;

	@FindBy(id = "FM_MAINT_ACCT_SAVE")
	WebElement saveMaintAction;

	@FindBy(id = "FM_FULL_INQ_RECV_TAB")
	WebElement receivableTab;
	
	@FindBy(id = "PM_COMMON_TABS_SAVE")
	WebElement saveOptionBtn;

	@FindBy(xpath = "//select[@name='saveAsCode']")
	WebElement saveAsDropDown;

	@FindBy(id = "PM_SAVE_OPTION_OK")
	WebElement saveOptionOkBtn;
	
	@FindBy(name = "workflowExit_Ok")
	WebElement Exit_Ok;

	@FindBy(xpath = "//select[contains(@name,'confirmed')]")
	WebElement productNotifyDropDown;

	@FindBy(id = "PM_NOTIFY_CLOSE")
	WebElement prodNotifyClose;
	
	@FindBy(id="FM_FULL_INQ_ACCOUNT_TAB")
	WebElement accountTab;

	@FindBy(id = "FM_FULL_INQ_ACC_INV")
	WebElement invoicesButton;

	@FindBy(name = "effectiveFromDate")
	WebElement effectiveFromDate;

	@FindBy(name = "endorsementCode")
	WebElement endorsementReason;

	@FindBy(name = "transactionComment")
	WebElement CommentsTxtBoxOnEndorsePolicyPopup;

	@FindBy(id = "PM_ENDORSE_OK")
	WebElement endorsePolicyOK;

	public FinancePage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		financePageDTO = new FinancePageDTO(TestCaseDetails.testDataDictionary);
	}

	//Search the account number using Account Holder name
	public FinancePage searchAccountUsingSearchCriteria() throws Exception{
		
		ExtentReporter.logger.log(LogStatus.INFO, "Using the account holder from TC42251"+" Enter name "+financePageDTO.accountHolderName+" in accountholder search box. Click [Search]. Verify Search Results are displayed");
		enterTextIn(driver, accountHolder,financePageDTO.accountHolderName, "Account Holder");
		clickButton(driver, searchBtn, "Search");
		invisibilityOfLoader(driver);
		return new FinancePage(driver);
	}
	
	//Select the last account from the account list shown in search results
	public FinancePage selectLastAccountFromAccountList() throws Exception{
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click on the last account number. Verify All Transaction Inquiry screen opens");
		int lastAccountFromList=(accountNumList.size()-1);
		
		selectValue(driver, accountNumList.get(lastAccountFromList),
				"Account Number " + accountNumList.get(lastAccountFromList).getAttribute("innerHTML").trim());
		invisibilityOfLoader(driver);
		return new FinancePage(driver);
	}
	
	//Select Maintain Account menu from Account menu list and edit the account information
	public FinancePage maintainAccount() throws Exception {

		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Account>Maintain Account. Verify Maintain Account page is opened");
		//Hover over Account tab and select maintainAccount menu option
		Actions act = new Actions(driver);
		act.moveToElement(accountMenuTab).build().perform();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", maintainAccount);
		ExtentReporter.logger.log(LogStatus.INFO, "Select Billing Frequency DDL and change from current monthly to new quarterly. Verify Billing Frequency is changed quarterly");
		Thread.sleep(2000);
		selectDropdownByVisibleText(driver, billingFrequencyDDL, financePageDTO.billingFrequency, "Billing Frequency");
		
		//Start date is the month and day of the current date i.e. MMdd
		ExtentReporter.logger.log(LogStatus.INFO, "Change Start date (mmdd) Example 0101 to currentdate(format has to be 0000). Verify Start date is changed to current date");
		clearTextBox(driver, startDate, "Start Date");
		CommonUtilities comUtil = new CommonUtilities();
		String sysDate=comUtil.getSystemDatemmddyyyy();
		String todaysdate = sysDate.substring(0, 4);
		enterDataIn(driver, startDate, todaysdate, "Start Date");
		ExtentReporter.logger.log(LogStatus.INFO, "Change Lead Days from current number to another number accordingly(example 1 to 10). Verify Lead days is changed to different number");
		//convert lead days field current value and random generated value from string to int for comparison
		int leadDaysValue=Integer.valueOf(leadDays.getAttribute("value"));
		int randomNewValue =Integer.valueOf(randomNumGenerator(1,"123456789"));

		//New value to be entered (randomNewValue) and current lead days value should be different and between 1 to 10.
		if(randomNewValue==leadDaysValue){
			randomNewValue++;
		if(randomNewValue >10){
			randomNewValue=1;
			}
		}
		//covert new value to string
		String leadDaysNewValue =Integer.toString(randomNewValue);
		
		clearTextBox(driver, leadDays, "Lead Days");
		enterDataIn(driver, leadDays, leadDaysNewValue, "Lead Days");
		return new FinancePage(driver);
	}
	
	//Save the changes made to Account in maintain Account method.
	public FinancePage saveAccountInformation() throws Exception{
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]. Verify Account Information is saved message appears");
		clickButton(driver, saveMaintAction, "Save");
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO,"Click [OK]. Verify Message is closed and changes are saved");
		if(isAlertPresent(driver)){
			acceptAlert(driver);
		}
		
		return new FinancePage(driver);
	}

	// Search Account from Search Account text field on Finance Home Page.
	public FinancePage searchPolicyOnFinanceHomePage() throws Exception {
		Thread.sleep(2000);
		invisibilityOfLoader(driver);
		getPageTitle(driver, accountSearchPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO,
				"Using the policy from 'Issue Policy Forms' test case enter Policy number in Policy#: search box and click Search.");
		enterTextIn(driver, PolicyNoTxtBox, financePageDTO.policyNo, "Policy Number");
		clickButton(driver, searchBtn, "Search");
		invisibilityOfLoader(driver);
		Thread.sleep(3000);
		Assert.assertTrue(accountList.get(0).isDisplayed(),
				"Account list is not displayed on " + "Account Search" + "page");
		return new FinancePage(driver);
	}

	// This method will click on first account number displayed after Policy
	// search.
	public FinancePage openFirstAccount() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO,
				"Click Account No(" + accountList.get(0).getAttribute("innerHTML") + ").");
		clickButton(driver, accountList.get(0), "Account number");
		invisibilityOfLoader(driver);
		Thread.sleep(2000);
		getPageTitle(driver, allTxnInquireyPageTitle);
		return new FinancePage(driver);
	}

	
	public FinancePage onDemandInvoice() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Billing Admin>On Demand Invoice.");
		navigatetoMenuItemPage(driver, billingAdminMenu, onDemandInvoiceMenuOption);
		Thread.sleep(3000);
		getPageTitle(driver, onDemandPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Select the below options.");
		ExtentReporter.logger.log(LogStatus.INFO, "Invoice Option: Immediately.");
		ExtentReporter.logger.log(LogStatus.INFO, "Create Charges: No.");
		ExtentReporter.logger.log(LogStatus.INFO, "Specify Invoice Date: No.");
		ExtentReporter.logger.log(LogStatus.INFO, "No Specify Due Date: No.");
		selectDropdownByValue(driver, invoiceOptionDDL, invoiceOptionDDLValue, "Invoice Option");
		selectDropdownByValue(driver, createChargesDDL, createChargesDDLValue, "Invoice Option");
		selectDropdownByValue(driver, specifyInvoiceDateDDL, specifyInvoiceDateDDLValue, "Invoice Option");
		selectDropdownByValue(driver, specifyDueDateDDL, specifyDueDateDDLValue, "Invoice Option");
		accountNumber = accountNo.getAttribute("innerHTML");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Process].");
		clickButton(driver, processButton, "Process");
		invisibilityOfLoader(driver);
		Thread.sleep(8000);
		switchToFrameUsingId(driver, "popupframe1");
		/*
		 * switchToFrameUsingElement(driver,
		 * driver.findElement(By.xpath("//iframe[contains(@src,'accountNo=" +
		 * accountNumber + "')]")));
		 */
		invoiceAmount = invoiceAmt.getAttribute("innerHTML");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Jump].");
		clickButton(driver, jumpButton, "Jump");
		invisibilityOfLoader(driver);
		switchToParentWindowfromframe(driver);
		return new FinancePage(driver);
	}

	public FinancePage exportExcelSheet(String saveExcelNameAs) throws Exception {

		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		downloadExcel(saveExcelNameAs);
		return new FinancePage(driver);
	}

	public FinancePage selectReceivableTabAndExportExcel(String saveExcelNameAs) throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Receivable page is opened");
		clickButton(driver, receivableTab, "Receivable");
		invisibilityOfLoader(driver);
		Thread.sleep(2000);
		exportExcelSheet(saveExcelNameAs);
		return new FinancePage(driver);
	}

	public HomePage selectAccountTabInvoicesButtonAndExportExcel() throws Exception {
		clickButton(driver, accountTab, "Account tab");
		invisibilityOfLoader(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Invoices transactions page is displayed");
		clickButton(driver, invoicesButton, "Invoices");
		invisibilityOfLoader(driver);
		exportExcelSheet(financePageDTO.invoicesInstallmentDueDateExcel);
		return new HomePage(driver);
	}

	public String readDataFromExcelSheet(String readerDataSheetName, String readerTestDataColumnName,
			int readerDataRowNumber, String readerExportedExcelSheetName) throws Exception {

		// String numberValue =
		// getDataFromExcel("Sheet1","Number",1,"C:\\saveExcel\\OnDemandInvoiceCredit.xlsx");
		// writeData("TC42250","Number",numberValue,1,System.getProperty("user.dir")+"\\src\\main\\resources\\Form_Data.xlsx");
		// String amountValue =
		// getDataFromExcel("Sheet1","Amount",1,"C:\\saveExcel\\OnDemandInvoiceCredit.xlsx");
		// writeData("TC42250","Amount",amountValue,1,System.getProperty("user.dir")+"\\src\\main\\resources\\Form_Data.xlsx");

		String columnCellValue = getDataFromExcel(readerDataSheetName, readerTestDataColumnName, readerDataRowNumber,
				"C:\\saveExcel\\" + readerExportedExcelSheetName + ".xlsx");
		Thread.sleep(3000);

		// getPageTitle(driver, allTxnInquireyPageTitle); TODO-Need to adjust
		// this at proper place
		return columnCellValue;
	}
	
	public FinancePage writeDataInExcelSheet(String cellValue,String writerTCSheetNumber,
			String writerTestDataColumnHeader, int writerRowNumber) throws Exception {
	
		writeData(writerTCSheetNumber, writerTestDataColumnHeader, cellValue, writerRowNumber,
				System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx");

		Thread.sleep(3000);
		return new FinancePage(driver);
		
	}

	//Get the next day of the due date column from exported excel sheet
	public String nextDayOfDueDate() throws Exception {
		 String dueDate = financePageDTO.retroDate;
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		 Date date = formatter.parse(dueDate);
		 Thread.sleep(1000);
		 Calendar c = Calendar.getInstance();
		 c.setTime(date);
		 c.add(Calendar.DATE, 1); 
		 Thread.sleep(1000);
		 Date d = c.getTime();
		 System.out.println("dueDate= "+dueDate);
         String nextDay=formatter.format(d);
         System.out.println("nextDay= "+nextDay);
         return nextDay;
	}
	
	//Endorse policy scenario with input having date
	public PolicyIndicationPage policyEndorsementWithDate(String PolicyNo, String nextDayOfDueDate) throws Exception {
		invisibilityOfLoader(driver);
		Thread.sleep(2000);
		// Select Endorsement from Policy Action
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Endorsement.");
		selectDropdownByVisibleText(driver, policyActionDDL, financePageDTO.policyAction, "Policy Action");
		Thread.sleep(2000);
		// Navigate to pop up frame using policy no.
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
		waitForElementToLoad(driver, 10, endorsementReason);
		// Enter Data in pop up like Effective Date,Endorsement Reason
		ExtentReporter.logger.log(LogStatus.INFO,
				"Enter/Select Below Information: Effective Date:Policy Effective Date Accounting Date: Fixed Date Reason: Issue Policy Forms Comment: Issue Policy Forms");
		// Effective Date is the retroDate from Sheet
		clearTextBox(driver, effectiveFromDate, "Effective Date");
		enterDataIn(driver, effectiveFromDate, nextDayOfDueDate, "Effective Date");
		// enterDataIn(driver, effectiveFromDate, dueDate, "Effective Date");
		selectDropdownByVisibleText(driver, endorsementReason, financePageDTO.endorsementReason, "Reason");
		enterTextIn(driver, CommentsTxtBoxOnEndorsePolicyPopup, financePageDTO.endorsementComment, "Comments");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK].");
		clickButton(driver, endorsePolicyOK, "OK");
		switchToParentWindowfromframe(driver);

		return new PolicyIndicationPage(driver);
	}

	
	public PolicyIndicationPage selectCoverageFromGridList() throws Exception {

		Thread.sleep(4000);
		// Get coverage count from the grid list on coverage page
		for (int i = 0; i < coverageList.size(); i++) {
			// compare if the coverage selected from excel sheet is same as
			// coverage from grid on coverage page
			if (coverageList.get(i).getAttribute("innerHTML").trim()
					.equalsIgnoreCase(financePageDTO.coverageNameFromGrid)) {
				ExtentReporter.logger.log(LogStatus.INFO,
						financePageDTO.coverageNameFromGrid + " Coverage is selected from Grid");
				// select the coverage from grid if it matches
				selectValue(driver, coverageList.get(i), financePageDTO.coverageNameFromGrid);
				// Assert.assertTrue(coverageList.get(i).isSelected(),coverageList.get(i)+"is
				// NOt selected");
				break;
			}
		}
		return new PolicyIndicationPage(driver);
	}

	
	public FinancePage cashEntry() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Payments>Cash Entry");
		navigatetoMenuItemPage(driver, paymentsMenu, cashEntryMenuOption);

		invisibilityOfLoader(driver);
		getPageTitle(driver, cashEntryPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [New]");
		clickButton(driver, newButton, "New");
		invisibilityOfLoader(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Payment Type: Check");
		selectDropdownByValue(driver, paymentTypeDDL, paymentTypeDDLValue, "Payment Type");
		ExtentReporter.logger.log(LogStatus.INFO, "Invoice No: " + financePageDTO.Number + "");
		Thread.sleep(1000);
		enterTextIn(driver, invoiceNoOnCashEntryPage, financePageDTO.Number, "Cash Entry Page's invoice Number");
		checkNoOnCashEntryPage.click();// check no element is clicked to enable
										// Alert.
		Thread.sleep(1000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Ok");
		acceptAlert(driver);
		Thread.sleep(1000);
		ExtentReporter.logger.log(LogStatus.INFO, "Check No: Enter ST12345");
		enterTextIn(driver, checkNoOnCashEntryPage, checkNo, "Cash Entry Page's Check Number");
		ExtentReporter.logger.log(LogStatus.INFO, "Amount:" + financePageDTO.Amount + "");
		enterTextIn(driver, amountOnCashEntryPage, financePageDTO.Amount, "Cash Entry Page's Amount");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]");
		clickButton(driver, saveBtnOnCashEntryPage, "Cash Entry Page's Save");
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		downloadExcel(PaymentCreditExcelName);
		return new FinancePage(driver);
	}

	public FinancePage batchFunction() throws Exception {
		// invisibilityOfLoader(driver);
		batchNumber = batchNo.getAttribute("innerHTML");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Payments>Batch Functions");
		/*
		 * js.executeScript("arguments[0].click();", paymentsMenu);
		 * js.executeScript("arguments[0].click();", batchFunctionMenuOption);
		 */
		navigatetoMenuItemPage(driver, paymentsMenu, batchFunctionMenuOption);
		invisibilityOfLoader(driver);
		getPageTitle(driver, batchFunctionPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Select Batch that was just created(usually will be the first one)");
		for (int i = 0; i < batchNoOnBatchFunPage.size(); i++) {
			if (batchNoOnBatchFunPage.get(i).getAttribute("innerHTML").equals(batchNumber)) {
				clickButton(driver, batchNoOnBatchFunPage.get(i), "Batch Number");
				break;
			}
		}
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		downloadExcel(openbatchcreditExcelName);
		return new FinancePage(driver);
	}

	public FinancePage validateBatch() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Validate Batch]");
		clickButton(driver, validateBatchBtn, "Validate Batch");
		getPageTitle(driver, validateBatchPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Enter below information");
		ExtentReporter.logger.log(LogStatus.INFO, "Amount:" + financePageDTO.Amount + "");
		enterTextIn(driver, batchAmount, financePageDTO.Amount, "batch Amount");
		ExtentReporter.logger.log(LogStatus.INFO, "No. of Payment: 1");
		enterTextIn(driver, numberOfPaymentTxtBox, BatchNoPayment, "Batch Number Of Payment");
		ExtentReporter.logger.log(LogStatus.INFO, "Click[Done]");
		clickButton(driver, doneBatchPopUp, "Done");
		switchToParentWindowfromframe(driver);
		invisibilityOfLoader(driver);
		/*
		 * verifyValueFromField(driver, validateField,
		 * validlateFieldExpectedValue, validlateFieldAttributeValue,
		 * validlateFieldName);
		 */
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		downloadExcel(validatebatchcreditExcelName);
		return new FinancePage(driver);
	}

	public FinancePage postBatchFunctionality() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Post Batch Button.");
		clickButton(driver, postBatchBtn, "Post Batch");
		/*
		 * Alert alert = driver.switchTo().alert(); alert.accept();
		 */
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Ok]");
		acceptAlert(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		downloadExcel(postedbatchcreditExcelName);
		return new FinancePage(driver);
	}
	
	
	//this method will download excel sheet
	public FinancePage downloadExcel(String fileName) throws Exception
	{
		clickButton(driver, exportExcelLink.get(0), "Export Excel");
		exlUtil.downloadExcel();
		copyFile(fileName);
		return new FinancePage(driver);
	}
	
	
	//this method will download excel sheet from Recivalbe Policy inquiry  for Poll transaction inquirey list.
		public FinancePage downloadExcelPollTxnInq(String fileName) throws Exception
		{
			Thread.sleep(3000);
			clickButton(driver, exportExcelLink.get(1), "Export Excel");
			exlUtil.downloadExcel();
			copyFile(fileName);
			return new FinancePage(driver);
		}
  
  public void donwloadFinalSheetBySearchingAccountNo() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO,
				"Enter account number in account search box in upper right corner of the page.");
		policySearch(driver, accountNumber, Policy_Search, searchBtn, policyList);
		invisibilityOfLoader(driver);
		getPageTitle(driver, allTxnInquireyPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		downloadExcel(alltransactionlistafterpaymentcreditExcelName);
	}
	
	//This method will download receivable list.
	public HomePage receivableDownload(String fileName) throws Exception
	{
		HomePage homepage = new HomePage(driver);
		searchPolicyOnFinanceHomePage();
		openFirstAccount();
		clickButton(driver, receivableTab, "Receivable");
		downloadExcelPollTxnInq(fileName);
		return new HomePage(driver);
	}
	
	
	//below method will cancel UMB_PL_Coverage.
	public FinancePage selectUMBCoverage() throws Exception
	{
		for (int i = 0; i < coverageList.size(); i++) {
			if (coverageList.get(i).getAttribute("innerHTML").equals(financePageDTO.coverage)) {
				clickButton(driver, coverageList.get(i), coverageList.get(i).getAttribute("innerHTML"));
				ExtentReporter.logger.log(LogStatus.INFO,
						"Select" + financePageDTO.coverage + " Coverage.");
				break;
			}
		}
		/*Assert.assertTrue(currBalOnAllTxnEnqPage.getAttribute("innerHTML").equals(financePageDTO.currunetBalance),
		"Current Balance is not zero on All transaction enquirey Page");*/
		return new FinancePage(driver);
	}
	
	//this method will select cancel from action drop down.
	public FinancePage selectCancelFromPolicyActionDDL() throws Exception
	{
		RateApolicyPage rateapolicypage  = new RateApolicyPage(driver);
		CommonUtilities commutil = new CommonUtilities();
		//String policyNo = rateapolicypage.policyNo();
		selectDropdownByVisibleText(driver, policyActionDDL, financePageDTO.policyAction, "Policy Action");
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + financePageDTO.policyNum + "')]")));
		Thread.sleep(3000);
		enterTextIn(driver, cancelDateOnCancelPopUp,commutil.getSystemDatemm_dd_yyyy(),"Cancel Date");
		verifyValueFromField(driver, accountingDateOnCancelPopUp, "N", "iseditable", "Accounting Date");
		selectDropdownByValue(driver, cancelTypeaccountingDateOnCancelPopUp, cancelTypeDDLValue, "Cancel Type");
		selectDropdownByValue(driver, cancelReasonOnCancelPopUp, cancelReasonDDLValue, "Cancel Type");
		selectDropdownByValue(driver, cancelMethodOnCancelPopUp, cancelMethodDLValue, "Cancel Type");
		enterTextIn(driver, cancelCommentsCancelPopUp, financePageDTO.cancelComment, "Comments");
		clickButton(driver, cancelBtnOnCancelPopUp, "Cancel");
		invisibilityOfLoader(driver);
		return new FinancePage(driver);
	}
	
	//this method will perform rate functionality.
	public FinancePage rateFunctionality() throws Exception
	{
		RateApolicyPage rateapolicypage  = new RateApolicyPage(driver);
		String policyNo = rateapolicypage.policyNo();
		rateapolicypage.rateFunctionality(policyNo);
		return new FinancePage(driver);
	}
	
	//this method will click on preview button.
	public PDFReader openPDF(String policyNo) throws Exception
	{
		PolicyQuotePage policyquotepage = new PolicyQuotePage(driver);
		policyquotepage.clickPreviewTab(policyNo);
		return new PDFReader(driver);
	}
	
	//this method will save policy.
	public HomePage savePolicyAsWIP() throws Exception
	{
		saveOption(driver, saveOptionBtn, saveAsDropDown, saveOptionOkBtn,Exit_Ok, financePageDTO.saveOption,policyNo);
		clickButton(driver, exportExcelLink.get(0), "Export Excel");
		exlUtil.downloadExcel();
		copyFile(alltransactionlistafterpaymentcreditExcelName);
		return new HomePage(driver);
	}
	
	//This method will open new account page.
	public FinancePage newAccountOpen() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Click Account>Maintain Account");
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(accountMenuTab).build().perform();
		JavascriptExecutor jse= (JavascriptExecutor)driver; 
		jse.executeScript("arguments[0].click();",maintainAccount);
		invisibilityOfLoader(driver);
		switchToFrameUsingElement(driver, iFrameElement);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [New Account]");
		clickButton(driver, newAccountBtn, "New Account");
		invisibilityOfLoader(driver);
		Thread.sleep(2000);
		switchToFrameUsingElement(driver, iFrameElement);
		for(int i = 0 ;i<accountType.size();i++)
		{
			if(accountType.get(i).getAttribute("innerText").equals(financePageDTO.AccountType))
			{
				ExtentReporter.logger.log(LogStatus.INFO, "Select Account type("+accountType.get(i).getAttribute("innerHTML")+").");
				ExtentReporter.logger.log(LogStatus.INFO, "Click[Done]");
				clickButton(driver, accountType.get(i), accountType.get(i).getAttribute("innerText"));
				break;
			}
			else
			{
				ExtentReporter.logger.log(LogStatus.INFO, "Account type("+accountType.get(i).getAttribute("innerHTML")+" is not found).");
			}
		}
		clickButton(driver, doneBtnOnSelectAccTypePopUp, "Done Button on Select Account Type Pop up");
		return new FinancePage(driver);
	}
	
	//This method will search organization and select address for organization.
	public FinancePage entitySelectSearch() throws Exception
	{
		invisibilityOfLoader(driver);
		String parentWindow = switchToWindow(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Enter: Test Agency Account Click[Search}");
		enterTextIn(driver, lastOrOrgNameInputField, financePageDTO.LastOrgName, "Last/Organization Name");
		clickButton(driver, searchBtnEntitySearchPage, "Search");
		invisibilityOfLoader(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Select entity by checking the check box then Click [Select]"); 
		clickButton(driver, searchedEntityChkBox, "Search Entity Check Box");
		Thread.sleep(1000);
		clickButton(driver, selectBtnSearchedEntity, "Select");
		invisibilityOfLoader(driver);
		Thread.sleep(3000);
		switchToParentWindowfromotherwindow(driver, parentWindow);
		//verifyValueFromField(driver, accountNoFieldValue, , accountNovalueAttributeName, "Account Number")
		if(accountNoFieldValue.getAttribute("value").trim().equals(accountNoUnExpectedValue))
		{
			Assert.assertTrue(false,"Account Information is NOT automatically populated on Maintain account page.");
		}
		else {
			ExtentReporter.logger.log(LogStatus.INFO,"Account Information is automatically populated on Maintain account page.");
		}
		return new FinancePage(driver);
	}
	
	//This method will select address for prepopulated account details.
	public FinancePage selectAddress() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Click Select Address Button"); 
		clickButton(driver, selectAdressBtn, "Select Adress");
		invisibilityOfLoader(driver);
		switchToFrameUsingElement(driver, iFrameElement);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Done]"); 
		clickButton(driver, DoneBtnOnAddListPopUp, "Done");
		switchToParentWindowfromframe(driver);
		invisibilityOfLoader(driver);
		return new FinancePage(driver);
	}
	
	//This method will save account details.
	public FinancePage saveAccountDetails() throws Exception
	{
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]");
		clickButton(driver, SaveBtnOnMaintainActPage, "save");
		invisibilityOfLoader(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Ok]");
		if(isAlertPresent(driver)){
		acceptAlert(driver);
		}else{
			ExtentReporter.logger.log(LogStatus.WARNING, "Save Confirmation Alert is NOT displayed"); 
		}
		invisibilityOfLoader(driver);
		Thread.sleep(2000);
		Assert.assertEquals(descriptionAfterSave.getAttribute("innerHTML"), financePageDTO.LastOrgName,"Account details are not save Sucessfully");
		return new FinancePage(driver);
	}
	
	public void captureSaveScreenshotofMantainAccountpage() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Take screenshot of account, save the name as "+financePageDTO.screenShotName+" in folder SmokeTestFM");
		invisibilityOfLoader(driver);
		captureScreenshot(driver, financePageDTO.screenShotName);
		ExtentReporter.logger.log(LogStatus.INFO, "Upload screenshot to Rally");
	}
	
}

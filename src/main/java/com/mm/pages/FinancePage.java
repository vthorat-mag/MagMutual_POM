package com.mm.pages;

import java.util.List;

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
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.relevantcodes.extentreports.LogStatus;

public class FinancePage extends CommonAction {

	WebDriver driver;
	FinancePageDTO financePageDTO;
	static String batchNumber;
	static String accountNumber;
	static String invoiceAmount;
	String onDemandInvoiceExcelName = "OnDemandInvoiceCredit";
	String onDemandInvoiceInstallementExcelSheet="OnDemandInvoiceInstallementBefore";
	String invoicesInstallmentDueDate="invoicesInstallmentDueDate";
	String PaymentCreditExcelName = "PaymentCredit";
	String openbatchcreditExcelName = "openbatchcredit";
	String FinancePageExcelName = "FinancePage";
	String validatebatchcreditExcelName = "validatebatchcredit";
	String postedbatchcreditExcelName = "postedbatchcredit";
	String alltransactionlistafterpaymentcreditExcelName = "alltransactionlistafterpaymentcredit";
	JavascriptExecutor js = (JavascriptExecutor) driver;
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
	ExcelUtil exlUtil = new ExcelUtil();	

	@FindBy(name = "globalSearch")
	WebElement Policy_Search;

	@FindBy(id = "FM_ACCOUNT_LIST_SEARCH")
	WebElement Search_btn;
	
	@FindBy(id="findPolicyListGrid_CPOLICYNO_0_HREF")  // QA
	WebElement policyList;

	@FindBy(name = "policyNo")
	WebElement PolicyNoTxtBox;

	@FindBy(xpath = "//a[@id='URL_CACCOUNTNO'] | //a[@id='accountListGrid_CACCOUNTNO_0_HREF']")
	List<WebElement> accountList;
	
	@FindBy(xpath = "//a[@id='URL_CACCOUNTNO']//span")
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
	WebElement exportExcelLink;
	
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
	
	@FindBy(xpath="//a[@class='selectedMenu fNiv isParent']//span")
	WebElement accountMenuTab;
	
	@FindBy(xpath="//li[@id='FM_MAINTAIN_ACCT_MENU']//a//span")
	WebElement maintainAccount;
	
	@FindBy(name="billingFrequency")
	WebElement billingFrequencyDDL;
	
	@FindBy(name="baseBillMmDd")
	WebElement startDate;
	
	@FindBy(name="billLeadDays")
	WebElement leadDays;
	
	@FindBy(id="FM_MAINT_ACCT_SAVE")
	WebElement saveMaintAction;
	
	@FindBy(id="FM_FULL_INQ_RECV_TAB")
	WebElement receivableTab;
	
	@FindBy(id="FM_FULL_INQ_ACCOUNT_TAB")
	WebElement accountTab;
	
	@FindBy(id="FM_FULL_INQ_ACC_INV")
	WebElement invoicesButton;
	
	
	
	
	
	
	
	public FinancePage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		financePageDTO = new FinancePageDTO();
	}
	
	
	//For TC 42403 its on hold
	public void maintainAccount() throws InterruptedException{
		
		clickButton(driver, Search_btn, "Search for account");
		invisibilityOfLoader(driver);
		Thread.sleep(2000);
		
		for(int i=0;i<accountNumList.size();i++){
			
			if(accountNumList.get(i).getAttribute("innerHTML").trim().equals("A0000550")){
				
				selectValue(driver, accountNumList.get(i), "Account Number"+accountNumList.get(i));
				break;
			}
		}//TODO - if the account num is not available in list - use flag
		//invisibilityOfLoader(driver);
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(accountMenuTab).build().perform();
		JavascriptExecutor jse= (JavascriptExecutor)driver; 
		jse.executeScript("arguments[0].click();",maintainAccount);
		
		Thread.sleep(2000);
		selectDropdownByVisibleText(driver, billingFrequencyDDL, "Bi-Monthly", "Billing Frequency");
		clearTextBox(driver, startDate, "Start Date");
		enterDataIn(driver, startDate, "0301", "Start Date");
		clearTextBox(driver, leadDays, "Lead Days");
		enterDataIn(driver, leadDays, "9", "Lead Days");
		clickButton(driver, saveMaintAction, "Save");
		
	}
	
	
	// Search Account from Search Account text field on Finance Home Page.
	public FinancePage searchPolicyOnFinanceHomePage() throws Exception {
		Thread.sleep(2000);
		invisibilityOfLoader(driver);
		getPageTitle(driver, accountSearchPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Using the policy from 'Issue Policy Forms' test case enter Policy number in Policy#: search box and click Search.");
		enterTextIn(driver, PolicyNoTxtBox, financePageDTO.policyNo, "Policy Number");
		clickButton(driver, Search_btn, "Search");
		invisibilityOfLoader(driver);
		Thread.sleep(3000);
		Assert.assertTrue(accountList.get(0).isDisplayed(), "Account list is not displayed on " + "Account Search" + "page");
		return new FinancePage(driver);
	}

	// This method will click on first account number displayed after Policy
	// search.
	public FinancePage openFirstAccount() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Account No("+accountList.get(0).getAttribute("innerHTML")+").");
		System.out.println("******"+accountList.get(0).getAttribute("innerHTML"));
		//clickButton(driver, accountList.get(0), "Account List");
		Actions action = new Actions(driver);
		action.click(accountList.get(0)).build().perform();
		invisibilityOfLoader(driver);
		getPageTitle(driver, allTxnInquireyPageTitle);
		return new FinancePage(driver);
	}

	public FinancePage onDemandInvoice() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Billing Admin>On Demand Invoice.");
		navigatetoMenuItemPage(driver,billingAdminMenu,onDemandInvoiceMenuOption);
		Thread.sleep(2000);
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
		Thread.sleep(15000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'accountNo=" + accountNumber + "')]")));
		invoiceAmount = invoiceAmt.getAttribute("innerHTML");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Jump].");
		clickButton(driver, jumpButton, "Jump");
		invisibilityOfLoader(driver);
		switchToParentWindowfromframe(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		clickButton(driver, exportExcelLink, "Export Excel");
		exlUtil.downloadExcel();
		copyFile(financePageDTO.onDemandInvoiceInstallmentExcel);
		
		
		return new FinancePage(driver);
	}
		
	
	public void receivableTabActions() throws Exception{
		clickButton(driver, receivableTab, "Receivable");
		invisibilityOfLoader(driver);
		clickButton(driver, exportExcelLink, "Export Excel");
		exlUtil.downloadExcel();
		copyFile(financePageDTO.onDemandInvoiceInstallementBeforeExcel);
		clickButton(driver, accountTab, "Account tab");
		invisibilityOfLoader(driver);
		clickButton(driver, invoicesButton, "Invoices");
		invisibilityOfLoader(driver);
		clickButton(driver, exportExcelLink, "Export Excel");
		exlUtil.downloadExcel();
		copyFile(financePageDTO.invoicesInstallmentDueDateExcel);
	}
	
	
	
	public FinancePage writeDataInExcelSheet() throws Exception{
		
		String numberValue = getDataFromExcel("Sheet1","Number",1,"C:\\saveExcel\\OnDemandInvoiceCredit.xlsx");
		Thread.sleep(3000);
		writeData("TC42250","Number",numberValue,1,System.getProperty("user.dir")+"\\src\\main\\resources\\Form_Data.xlsx");
		
		String amountValue = getDataFromExcel("Sheet1","Amount",1,"C:\\saveExcel\\OnDemandInvoiceCredit.xlsx");
		Thread.sleep(3000);
		writeData("TC42250","Amount",amountValue,1,System.getProperty("user.dir")+"\\src\\main\\resources\\Form_Data.xlsx");
		
		getPageTitle(driver, allTxnInquireyPageTitle);
		return new FinancePage(driver);
	}

	public FinancePage cashEntry() throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Payments>Cash Entry");
		navigatetoMenuItemPage(driver,paymentsMenu,cashEntryMenuOption);
		
		invisibilityOfLoader(driver);
		getPageTitle(driver, cashEntryPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [New]");
		clickButton(driver, newButton, "New");
		invisibilityOfLoader(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Payment Type: Check");
		selectDropdownByValue(driver, paymentTypeDDL, paymentTypeDDLValue, "Payment Type");
		ExtentReporter.logger.log(LogStatus.INFO, "Invoice No: "+financePageDTO.Number+"");
		Thread.sleep(1000);
		enterTextIn(driver, invoiceNoOnCashEntryPage, financePageDTO.Number, "Cash Entry Page's invoice Number");
		checkNoOnCashEntryPage.click();//check no element is clicked to enable Alert.
		Thread.sleep(1000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Ok");
		acceptAlert(driver);
		Thread.sleep(1000);
		ExtentReporter.logger.log(LogStatus.INFO, "Check No: Enter ST12345");
		enterTextIn(driver, checkNoOnCashEntryPage, checkNo, "Cash Entry Page's Check Number");
		ExtentReporter.logger.log(LogStatus.INFO, "Amount:"+financePageDTO.Amount+"");
		enterTextIn(driver, amountOnCashEntryPage, financePageDTO.Amount, "Cash Entry Page's Amount");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]");
		clickButton(driver, saveBtnOnCashEntryPage, "Cash Entry Page's Save");
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		clickButton(driver, exportExcelLink, "Export Excel");
		exlUtil.downloadExcel();
		copyFile(PaymentCreditExcelName);
		return new FinancePage(driver);
	}

	public FinancePage batchFunction() throws Exception {
		//invisibilityOfLoader(driver);
		batchNumber = batchNo.getAttribute("innerHTML");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Payments>Batch Functions");
		/*js.executeScript("arguments[0].click();", paymentsMenu);
		js.executeScript("arguments[0].click();", batchFunctionMenuOption);*/
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
		clickButton(driver, exportExcelLink, "Export Excel");
		exlUtil.downloadExcel();
		copyFile(openbatchcreditExcelName);
		
		return new FinancePage(driver);
	}
	
	public FinancePage validateBatch() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Validate Batch]");
		clickButton(driver, validateBatchBtn, "Validate Batch");
		getPageTitle(driver, validateBatchPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Enter below information");
		ExtentReporter.logger.log(LogStatus.INFO, "Amount:"+financePageDTO.Amount+"");
		enterTextIn(driver, batchAmount, financePageDTO.Amount, "batch Amount");
		ExtentReporter.logger.log(LogStatus.INFO, "No. of Payment: 1");
		enterTextIn(driver, numberOfPaymentTxtBox, BatchNoPayment, "Batch Number Of Payment");
		ExtentReporter.logger.log(LogStatus.INFO, "Click[Done]");
		clickButton(driver, doneBatchPopUp, "Done");
		switchToParentWindowfromframe(driver);
		invisibilityOfLoader(driver);
		/*verifyValueFromField(driver, validateField, validlateFieldExpectedValue, validlateFieldAttributeValue,
				validlateFieldName);*/
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		clickButton(driver, exportExcelLink, "Export Excel");
		exlUtil.downloadExcel();
		copyFile(validatebatchcreditExcelName);
		return new FinancePage(driver);
	}
	
	public FinancePage postBatchFunctionality() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Click Post Batch Button.");
		clickButton(driver, postBatchBtn, "Post Batch");
		/*Alert alert = driver.switchTo().alert();
		alert.accept();*/
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Ok]");
		acceptAlert(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		clickButton(driver, exportExcelLink, "Export Excel");
		exlUtil.downloadExcel();
		copyFile(postedbatchcreditExcelName);
		return new FinancePage(driver);
	}
	
	public void donwloadFinalSheetBySearchingAccountNo() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Enter account number in account search box in upper right corner of the page.");
		policySearch(driver, accountNumber, Policy_Search, Search_btn,policyList);
		invisibilityOfLoader(driver);
		getPageTitle(driver, allTxnInquireyPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO, "Export All Transactions to excel");
		clickButton(driver, exportExcelLink, "Export Excel");
		exlUtil.downloadExcel();
		copyFile(alltransactionlistafterpaymentcreditExcelName);
	}
}

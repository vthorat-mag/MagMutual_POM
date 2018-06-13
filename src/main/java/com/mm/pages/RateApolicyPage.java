package com.mm.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.dto.RateAPolicyPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mongodb.client.model.ReturnDocument;
import com.relevantcodes.extentreports.LogStatus;

public class RateApolicyPage<returnMultipleValues> extends CommonAction {
	
	WebDriver driver;
	
	RateAPolicyPageDTO rateApolicyPageDTO;
	
	String valueOfPolicyActionAccept = "javascript:acceptQuote();";
	String billingSetup = "javascript:billingSetup();";
	String paymentPlanValue = "659689385";
	String saveAsPolicyValue="OFFICIAL";
	String ProductNotifyValue="Y";
	CommonUtilities comUtil = new CommonUtilities();
	PDFReader pdfread = new PDFReader();
	
	//Object repository for all elements on rate A policy page.
	
	@FindBy(name="globalSearch")
	WebElement Policy_Search;

	@FindBy(name="search")
	WebElement Search_btn;

	@FindBy(id="PM_COMMON_TABS_RATE")
	WebElement RateBtn;

	@FindBy(name="483865737.confirmed")
	WebElement Continue_saving;

	@FindBy(id="PM_NOTIFY_CLOSE")
	WebElement Notify_Close;

//	@FindBy(id="PM_VIEW_PREM_CLOSE")
	@FindBy(id="PM_VIEW_VAL_ERR_CLOSE ")
	WebElement Prem_Close;

	@FindBy(name="btnSaveAsCSV")
	WebElement Export;

	@FindBy(name="workflowExit_Ok")
	WebElement Exit_Ok;
	
	@FindBy(xpath="//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
	WebElement policyAction;
	
	@FindBy(id="pageTitleForpageHeaderForPolicyFolder")
	WebElement pageHeaderForPolicyFolder;
	
	//@FindBy(id="polPhaseCodeROSPAN")
	@FindBy(xpath="//table[@id='formFieldsTableForHeaderFieldsSecond']//span[@id='polPhaseCodeROSPAN']")
	WebElement policyPhaseBinder;
	
	@FindBy(xpath="//select[@name='paymentPlanId']")
	WebElement paymentPlan;
	
	@FindBy(xpath="//div[@class='horizontalButtonCollection']//input[@id='PM_BILLNG_SAVE']")
	WebElement billingSetupSaveBtn;
	
	@FindBy(xpath="//a[@id='PM_PT_VIEWCVG']//span[@class='tabWithNoDropDownImage']")
	WebElement coverageTab;
	
	@FindBy(xpath = "//table[@id='coverageListGrid']//tbody//td//div[@id='CPRODUCTCOVERAGEDESC']")
	List<WebElement> coverageList;
	
	@FindBy(xpath = "//div[@class='horizontalButtonCollection']//span[@class='leftGrayButtonArea']//input[@id='PM_MANU_PUP']")
	WebElement optionalFormBtn;
	
	@FindBy(xpath = "//table[@id='maintainManuscriptListGrid']")
	WebElement 	manuscriptList;
	
	@FindBy(id="PM_MANU_DELETE")
	WebElement manuscriptPageDeleteBtn;
	
	@FindBy(id="PM_MANU_ADD")
	WebElement manuscriptPageAddBtn;
	
	@FindBy(xpath="//table[@id='selectManuscriptGrid']//tr//div[@id='CSHORTDESCRIPTION']")
	List<WebElement> manuscriptAddListformName;
	
	@FindBy(xpath="//table[@id='selectManuscriptGrid']//tr//input[@name='chkCSELECT_IND']")
	List<WebElement> manuscriptAddListformNameChkBox;
	
	@FindBy(id="PM_SEL_MANU_DONE")
	WebElement manuscriptAddListDoneBtn;
	
	@FindBy(id="PM_MANU_SAVE")
	WebElement manuscriptPageSaveBtn;
	
	@FindBy(id="PM_MANU_CLOSE")
	WebElement manuscriptPageCloseBtn;
	
	@FindBy(xpath="//iframe[contains(@id='popupframe')]")
	List<WebElement> allIframes;
	
	@FindBy(id="PM_COMMON_TABS_RATE")
	WebElement rateBtn;
	
	@FindBy(id="PM_VIEW_PREM_CLOSE")
	WebElement closeBtnOnViewPremiumPopup;
	
	@FindBy(name="workflowExit_Ok")
	WebElement okPolicySaveAsWIPPopup;
	
	@FindBy(id="PM_COMMON_TABS_PREVIEW")
	WebElement previewBtn;

	@FindBy(xpath="//span[@class='txtOrange']")
	WebElement loader;
	
	@FindBy(xpath="//button[contains(@class,'titlebar-close')]")
	WebElement closePdf;
	
	@FindBy(id="PM_COMMON_TABS_SAVE")
	WebElement saveOptionBtn;
	
	@FindBy(xpath="//select[@name='saveAsCode']")
	WebElement saveAsDropDown;
	
	@FindBy(id="PM_SAVE_OPTION_OK")
	WebElement saveOptionOkBtn;
	
	@FindBy(xpath="//select[contains(@name,'confirmed')]")
	WebElement productNotifyDropDown;
	
	@FindBy(id="PM_NOTIFY_CLOSE")
	WebElement prodNotifyClose;
	
	@FindBy(name="additionalText")
	WebElement addText;
	
	
	//Constructor to initialize driver, page elements and DTO PageObject for CISPage
	public RateApolicyPage(WebDriver driver) throws Exception
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		rateApolicyPageDTO = new RateAPolicyPageDTO();
	}
	PolicyQuotePage policyquotepage =  new PolicyQuotePage(this.driver);
	
	//Search Policy from Search Policy text field.
	public RateApolicyPage searchPolicy( String policy_no) throws Exception
	{
		Thread.sleep(3000);
		policySearch(driver, policy_no,Policy_Search, Search_btn);
		String actual=getText(driver,pageHeaderForPolicyFolder);
		Assert.assertEquals(actual, "Policy Folder "+policy_no, "The policy "+policy_no+" is Not available.");
		Thread.sleep(3000);
		return new RateApolicyPage(driver);
	}
	
	//Save Rate details code.
	public RateApolicyPage saveRatedetails() throws Exception
	{
		click(driver,RateBtn, "Rate button");
		Thread.sleep(5000);
		switchToFrameUsingId(driver,"popupframe1");
	    Thread.sleep(1000);
	    selectDropdownByValue(driver,Continue_saving, "Y", "Continue saving without Quote"); 
	    Thread.sleep(1000);
	    click(driver,Notify_Close, "Close button");
	    Thread.sleep(3000);
	    return new RateApolicyPage(driver);
	}
	
	//Download Excel report and save in defined folder
	public String startExcelExport() throws InterruptedException,AWTException
	{
	    	 clickButton(driver,Export, "Export link");
	    	 Thread.sleep(2000);
	    	 
	    	 Robot rob = new Robot();
	    	 
	    	 rob.keyPress(KeyEvent.VK_F6);
	    	 rob.keyRelease(KeyEvent.VK_F6);
	    	 
	    	 rob.setAutoDelay(1000);
	    	 rob.keyPress(KeyEvent.VK_TAB);
	    	 rob.keyRelease(KeyEvent.VK_TAB);
	    	 
	    	 rob.setAutoDelay(1000);
	    	 rob.keyPress(KeyEvent.VK_DOWN);
	    	 
	    	 rob.setAutoDelay(1000);
	    	 rob.keyPress(KeyEvent.VK_DOWN);
	    	 rob.keyRelease(KeyEvent.VK_DOWN);
	    	 
	    	 rob.setAutoDelay(1000);
	    	 rob.keyPress(KeyEvent.VK_ENTER);
	    	 rob.keyRelease(KeyEvent.VK_ENTER);
	    	    	 
	    	 String fileDate = comUtil.getSystemDateMMddyy_hhmmss();
	    	 
	    	 String fileNamePath = "C:\\MM_Testcase_Output\\"+fileDate+".xlsx";
	    	 StringSelection fileName = new StringSelection(fileNamePath);
	 		
	 	 	 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileName, null);

	 	 	 rob.keyPress(KeyEvent.VK_CONTROL); 									//press 'cntrl' key
			 rob.keyPress(KeyEvent.VK_V);											//press 'V' key
			
			 rob.keyRelease(KeyEvent.VK_CONTROL);
			 rob.keyRelease(KeyEvent.VK_V);
	 		 rob.setAutoDelay(2000);
	    	 rob.keyPress(KeyEvent.VK_ENTER);
	    	 rob.keyRelease(KeyEvent.VK_ENTER);
	    	 
	    	 click(driver,Prem_Close,"Premium close");
	    	 Thread.sleep(1000);
	    	 
	    	 click(driver,Exit_Ok, "OK button");
	    	 switchToParentWindowfromframe(driver);
	    	
	    	 rob.keyPress(KeyEvent.VK_F6);
	    	 rob.keyRelease(KeyEvent.VK_F6);
	    	 rob.setAutoDelay(1000);
	    	 rob.keyPress(KeyEvent.VK_TAB);
	    	 rob.keyPress(KeyEvent.VK_TAB);
	    	 rob.keyPress(KeyEvent.VK_TAB);
	    	 rob.keyRelease(KeyEvent.VK_TAB);
	    	 rob.setAutoDelay(1000);
	    	 rob.keyPress(KeyEvent.VK_ENTER);
	    	 rob.keyRelease(KeyEvent.VK_ENTER);
	    	/*try
		 	{
	    	 File file = new File("C:\\MM_Testcase_Output\\"+fileDate+".xlsx");
	    	 
	    	if(file.exists())
	    	{
	    	System.out.println("File is available at location");
	    	ExtentReporter.logger.log(LogStatus.PASS, "Excel file is available at download location.");
	    	}
	    	 
	    	}catch(Exception e)
	    	{
	    	System.out.println("File is Not available at location");
	    	ExtentReporter.logger.log(LogStatus.FAIL, "Excel file is Not available at download location.");
	    	 e.printStackTrace();	
	    	}*/
	    	 return fileNamePath;
	    }	
	//Verify file exist or not.
	public void verifyFileExists(String fileNamePath){
		
		comUtil.downloadedFileExists(fileNamePath);
	}

	//Select Accept option from "Action Drop Down".
	public RateApolicyPage AcceptFromActionDropDown() throws Exception
	{
		ExtentReporter.logger.log(LogStatus.INFO, "Select Accept from the dropdown screen.");
		selectDropdownByValue(driver,policyAction, valueOfPolicyActionAccept, "Policy Action");
		return new RateApolicyPage(driver);
	}
	
	//Verify Alert is present or not.
	public RateApolicyPage isAlertPresent() throws Exception 
	{ 
	    try 
	    { 
	    	Thread.sleep(5000);
	    	Alert alert = driver.switchTo().alert();
	        alert.accept();
	        ExtentReporter.logger.log(LogStatus.INFO, alert.getText());
	        Thread.sleep(5000);
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	    	ExtentReporter.logger.log(LogStatus.INFO, "Alert is not displayed for Same policy exist.");
	    }   // catch 
	    return new RateApolicyPage(driver);
	}   
  
	//Identify Phase displayed on Page.	
	public RateApolicyPage identifyPhase() throws Exception
	{
		waitFor(driver, 2);
		Thread.sleep(3000);
		String getTextPolicyPhase = policyPhaseBinder.getAttribute("innerText");
		//String getTextPolicyPhase = getText(driver, policyPhaseBinder);
		verifyTextPresent(getTextPolicyPhase,"Binder","Policy Phase");
		ExtentReporter.logger.log(LogStatus.PASS, "Verify Phase is changed to Binder.");
		return new RateApolicyPage(driver);
	}
	
	//Identify Policy number from Page.
	public String policyNo() throws InterruptedException
	{
		Thread.sleep(3000);
		//String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
		String profileNoLable = getText(driver, pageHeaderForPolicyFolder);
		String[] portfolioNo = profileNoLable.split(" ",3);
		return portfolioNo[2];
	}
	
	//Billing setup flow code.
	public RateApolicyPage billingSetup() throws Exception
	{
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions-->Select Billing Setup");
		selectDropdownByValue(driver,policyAction, billingSetup, "Policy Action");
		waitFor(driver, 5000);
		switchToFrameUsingId(driver, "popupframe1");
		ExtentReporter.logger.log(LogStatus.INFO, "Payment plan dropdown: Select A-Monthly");
		selectDropdownByValue(driver,paymentPlan, paymentPlanValue, "Payment Plan");
		Thread.sleep(5000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]");
		clickButton(driver, billingSetupSaveBtn, "Save Button");
		Thread.sleep(35000);
		switchToParentWindowfromframe(driver);
		return new RateApolicyPage(driver);
	}
	
	//Coverage Details flow.
	public RateApolicyPage coverageDetailsSelect() throws Exception
	{
		try{
			clickButton(driver,coverageTab, "Coverage");
			ExtentReporter.logger.log(LogStatus.PASS, "Click Coverage tab.");
		}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Can not click on Coverage tab.");
		}
		return new RateApolicyPage(driver);
	}
	
	//Coverage updates flow.
	public void coverageUpdates(String CoverageName, String binderForm, String PolicyNo) throws Exception
	{
		for (int i = 0; i<coverageList.size();i++)
		{
			if(coverageList.get(i).getAttribute("innerHTML").equals(CoverageName))
			{
				clickButton(driver, coverageList.get(i),coverageList.get(i).getAttribute("innerHTML"));
				ExtentReporter.logger.log(LogStatus.INFO, "Select"+ CoverageName +" Coverage.");
				Thread.sleep(4000);
				break;
			}
		}
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Optional Forms]");
		clickButton(driver, optionalFormBtn, "Optional Form");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		if (manuscriptList.isDisplayed())
		{
			ExtentReporter.logger.log(LogStatus.INFO, "Delete current Indication form, Are you sure you want to delete this? Click Ok");
			clickButton(driver, manuscriptPageDeleteBtn, "Manu script Delete");
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
			
		}else{
			ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
		}
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		Thread.sleep(3000);
		for(int i=0;i<manuscriptAddListformName.size();i++)
		{
			if(manuscriptAddListformName.get(i).getAttribute("innerHTML").equals(binderForm))
			{
				clickButton(driver, manuscriptAddListformNameChkBox.get(i), "check Box for "+binderForm);
				ExtentReporter.logger.log(LogStatus.INFO, "Select "+ binderForm +", Click done.");
				break;
			}
		}
		clickButton(driver, manuscriptAddListDoneBtn, "Done");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		enterTextIn(driver,addText, binderForm+" form added.", "Aditional Text");
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Enter additional text: "+ binderForm+" form added  Click [Save] and Click [Close].");
		clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
		switchToParentWindowfromframe(driver);
	}
	
	
	//Rate a functionality flow.
	public void rateFunctionality(String policyNo) throws Exception
	{
		Thread.sleep(3000);
		clickButton(driver, rateBtn, "Rate Tab");
		ExtentReporter.logger.log(LogStatus.PASS, "Click [Rate].");
		Thread.sleep(4000);
		
		/*try{
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
			selectDropdownByValue(productNotifyDropDown, ProductNotifyValue, "product notify");
			Thread.sleep(1000);
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selected from Product Notify dorp down.");
		}catch (Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Product Notify Window is NOT dispalyed to user.");
		}*/
		Thread.sleep(3000);
		//switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		Thread.sleep(2000);
		clickButton(driver, closeBtnOnViewPremiumPopup, "Close");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
		switchToParentWindowfromframe(driver);
	}
	
	//PDF verification flow.
	public void pdfVerify() throws Exception
	{
		clickButton(driver, previewBtn, "Preview");
		Thread.sleep(4000);
		//switchToFrameUsingId(driver,"popupframe1");
		//WebDriverWait wait=new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.invisibilityOf(loader));
		Thread.sleep(4000);
		//pdfread.readValue();
		clickButton(driver, closePdf, "Close PDF");
		//switchToParentWindowfromframe(driver);
		
	}
	
	//Save Option flow.
	public void saveOption(String policyNo) throws Exception
	{
		Thread.sleep(2000);
		clickButton(driver, saveOptionBtn, "Save Option");
		ExtentReporter.logger.log(LogStatus.PASS, "Click Save Options.");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		selectDropdownByValue(driver,saveAsDropDown, saveAsPolicyValue, "Save Option");
		ExtentReporter.logger.log(LogStatus.PASS, "Select Official.");
		clickButton(driver, saveOptionOkBtn, "Save");
		ExtentReporter.logger.log(LogStatus.PASS, "Click [OK]");
		Thread.sleep(6000);
		try{
			switchToParentWindowfromframe(driver);
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
			selectDropdownByValue(driver,productNotifyDropDown, ProductNotifyValue, "product notify");
			Thread.sleep(1000);
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selection from Product Notify dorp down.");
		}catch (Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Product Notify Window is NOT dispalyed to user.");
		}
		Thread.sleep(5000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		clickButton(driver, Exit_Ok, "Exit Ok");
		ExtentReporter.logger.log(LogStatus.PASS, "Click [OK]");
	}

}







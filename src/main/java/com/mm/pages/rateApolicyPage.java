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

import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.utils.commonAction;
import com.mm.utils.commonUtilities;
import com.relevantcodes.extentreports.LogStatus;

public class rateApolicyPage extends commonAction {
	
	WebDriver driver;
	String valueOfPolicyActionAccept = "javascript:acceptQuote();";
	String billingSetup = "javascript:billingSetup();";
	String paymentPlanValue = "659689385";
	String saveAsPolicyValue="OFFICIAL";
	String ProductNotifyValue="Y";
	commonUtilities comUtil = new commonUtilities();
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

	@FindBy(id="PM_VIEW_PREM_CLOSE")
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
	
	public rateApolicyPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
	String[] portfolioNo = profileNoLable.split(" ",3);*/
	/*
	public void policySearch() throws InterruptedException, AWTException{
		
		String policy_no="Q09101503-NB17-01";
		Thread.sleep(2000);
		clearTextBox(Policy_Search, "Enter Policy text field");
		enterTextIn(Policy_Search, policy_no, "Enter Policy text field");
		click(Search_btn, "Search button");
		
		String actual=getText(pageHeaderForPolicyFolder);
				
		Assert.assertEquals(actual, "Policy Folder "+policy_no, "The policy "+policy_no+" is Not available.");
		Thread.sleep(4000);
	}*/
	
	public void searchPolicy( String policy_no) throws InterruptedException
	{
		Thread.sleep(3000);
		policySearch(policy_no,Policy_Search, Search_btn);
		String actual=getText(pageHeaderForPolicyFolder);
		Assert.assertEquals(actual, "Policy Folder "+policy_no, "The policy "+policy_no+" is Not available.");
		Thread.sleep(3000);
	}
	
	
/*	public void policySearch(String policyNo, WebElement policySearchTxtBox, WebElement searchBtn) 
	
	{
		clearTextBox(policySearchTxtBox, "Enter Policy text field");
		enterTextIn(policySearchTxtBox, policyNo, "Enter Policy text field");
		click(searchBtn, "Search button");
	}*/

	
	//Save Rate details code.
	public void saveRatedetails() throws InterruptedException
	{
		click(RateBtn, "Rate button");
		Thread.sleep(5000);
		switchToFrameUsingId(driver,"popupframe1");
	    Thread.sleep(1000);
	    selectDropdownByValue(Continue_saving, "Y", "Continue saving without Quote"); 
	    Thread.sleep(1000);
	    click(Notify_Close, "Close button");
	    Thread.sleep(3000);
	}
	
	//Donwload Excel report and save in defined folder
	public String startExcelExport() throws InterruptedException,AWTException
	{
	    	 click(Export, "Export link");
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
	    	/* 
	    	 DateFormat dateFormatter = new SimpleDateFormat("MMddyy_hhmmss");
	 		 Date today = Calendar.getInstance().getTime();        
	 		 String date= dateFormatter.format(today);
	 		*/
	    	 
	    	 String fileDate = comUtil.getSystemDate();
	    	 
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
	    	 
	    	 click(Prem_Close,"Premium close");
	    	 Thread.sleep(1000);
	    	 
	    	 click(Exit_Ok, "OK button");
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
	public void verifyFileExists(String fileNamePath){
		
		comUtil.downloadedFileExists(fileNamePath);
	}

	public void AcceptFromActionDropDown()
	{
		selectDropdownByValue(policyAction, valueOfPolicyActionAccept, "Policy Action");
		ExtentReporter.logger.log(LogStatus.PASS, "Select Accept from the dropdown screen.");
		
	}
	public void isAlertPresent() throws InterruptedException 
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
	}   
	
	public void identifyPhase() throws InterruptedException
	{
		waitFor(driver, 2);
		String getTextPolicyPhase = policyPhaseBinder.getAttribute("innerText");
		verifyTextPresent(getTextPolicyPhase,"Binder","Policy Phase");
		ExtentReporter.logger.log(LogStatus.PASS, "Verify Phase is changed to Binder.");
	}
	public String policyNo()
	{
		String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
		String[] portfolioNo = profileNoLable.split(" ",3);
		return portfolioNo[2];
	}
	
	public void billingSetup() throws InterruptedException
	{
		Thread.sleep(3000);
		selectDropdownByValue(policyAction, billingSetup, "Policy Action");
		waitFor(driver, 5000);
		switchToFrameUsingId(driver, "popupframe1");
		selectDropdownByValue(paymentPlan, paymentPlanValue, "Payment Plan");
		Thread.sleep(5000);
		clickButton(driver, billingSetupSaveBtn, "Save Button");
		Thread.sleep(35000);
		switchToParentWindowfromframe(driver);
	}
	public void coverageDetailsSelect()
	{
		try{
			clickButton(driver,coverageTab, "Coverage");
			ExtentReporter.logger.log(LogStatus.PASS, "Click Coverage tab.");
		}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Can not click on Coverage tab.");
		}
	}
	
	public void coverageUpdates(String CoverageName, String binderForm, String PolicyNo) throws InterruptedException
	{
		for (int i = 0; i<coverageList.size();i++)
		{
			if(coverageList.get(i).getAttribute("innerHTML").equals(CoverageName))
			{
				try{
					clickButton(driver, coverageList.get(i),coverageList.get(i).getAttribute("innerHTML"));
					ExtentReporter.logger.log(LogStatus.PASS, "Select"+ CoverageName +" Coverage.");
					Thread.sleep(4000);
					break;
				}catch(Exception e){
					ExtentReporter.logger.log(LogStatus.FAIL, "Can not select"+ CoverageName + "Coverage.");
				}
			}
		}
		clickButton(driver, optionalFormBtn, "Optional Form");
		ExtentReporter.logger.log(LogStatus.PASS, "Click [Optional Forms]");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		if (manuscriptList.isDisplayed())
		{
			try {
				clickButton(driver, manuscriptPageDeleteBtn, "Manu script Delete");
				driver.switchTo().alert().accept();
				ExtentReporter.logger.log(LogStatus.PASS, "Delete the Quote form.");
				Thread.sleep(2000);
				clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
				ExtentReporter.logger.log(LogStatus.PASS, "Click [Add].");
			}catch(Exception e)
			{
				ExtentReporter.logger.log(LogStatus.FAIL, "Can not delete the Quote form.");
			}
		}else{
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
			ExtentReporter.logger.log(LogStatus.PASS, "Click [Add].");
		}
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		Thread.sleep(3000);
		for(int i=0;i<manuscriptAddListformName.size();i++)
		{
			if(manuscriptAddListformName.get(i).getAttribute("innerHTML").equals(binderForm))
			{
				try{
					clickButton(driver, manuscriptAddListformNameChkBox.get(i), "check Box for "+binderForm);
					ExtentReporter.logger.log(LogStatus.PASS, "Select "+ binderForm +" and Click done.");
					break;
				}
				catch(Exception e){
					ExtentReporter.logger.log(LogStatus.FAIL, "Can not select "+ binderForm +".");
				}
			}
		}
		clickButton(driver, manuscriptAddListDoneBtn, "Done");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		try{
			enterTextIn(addText, binderForm+" form added.", "Aditional Text");
			
			/*JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].value='admin'",addText);*/
			
			ExtentReporter.logger.log(LogStatus.PASS, "Enter additional text: "+ binderForm+" form added.");
		}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Can not enter additional text");
		}
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		
		try{
			clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
			Thread.sleep(2000);
			clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
			ExtentReporter.logger.log(LogStatus.PASS, "Click [Save] and Click [Close]");
		}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Can not click [Save] and Click [Close]");
		}
		switchToParentWindowfromframe(driver);
	}
	
	public void rateFunctionality(String policyNo) throws InterruptedException
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
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		Thread.sleep(2000);
		clickButton(driver, closeBtnOnViewPremiumPopup, "Close");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
		switchToParentWindowfromframe(driver);
	}
	
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
	
	public void saveOption(String policyNo) throws InterruptedException
	{
		Thread.sleep(2000);
		clickButton(driver, saveOptionBtn, "Save Option");
		ExtentReporter.logger.log(LogStatus.PASS, "Click Save Options.");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		selectDropdownByValue(saveAsDropDown, saveAsPolicyValue, "Save Option");
		ExtentReporter.logger.log(LogStatus.PASS, "Select Official.");
		clickButton(driver, saveOptionOkBtn, "Save");
		ExtentReporter.logger.log(LogStatus.PASS, "Click [OK]");
		Thread.sleep(6000);
		try{
			switchToParentWindowfromframe(driver);
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
			selectDropdownByValue(productNotifyDropDown, ProductNotifyValue, "product notify");
			Thread.sleep(1000);
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selection from Product Notify dorp down.");
		}catch (Exception e)
		{
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window is NOT dispalyed to user.");
		}
		Thread.sleep(5000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		clickButton(driver, Exit_Ok, "Exit Ok");
		ExtentReporter.logger.log(LogStatus.PASS, "Click [OK]");
	}
}

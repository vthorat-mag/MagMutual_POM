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
	String paymentPlanValue = "660262192";
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
	
	@FindBy(id="PM_COMMON_TABS_RATE")
	WebElement closeBtnOnViewPremiumPopup;
	
	@FindBy(name="workflowExit_Ok")
	WebElement okPolicySaveAsWIPPopup;
	
	@FindBy(id="PM_COMMON_TABS_PREVIEW")
	WebElement previewBtn;

	@FindBy(xpath="//span[@class='txtOrange']")
	WebElement loader;
	
	
	public rateApolicyPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
	String[] portfolioNo = profileNoLable.split(" ",3);
	
	public void policySearch() throws InterruptedException, AWTException{
		
		String policy_no="Q09100439-NB17-01";
		Thread.sleep(2000);
		clearTextBox(Policy_Search, "Enter Policy text field");
		enterTextIn(Policy_Search, policy_no, "Enter Policy text field");
		click(Search_btn, "Search button");
		
		String actual=getText(pageHeaderForPolicyFolder);
				
		Assert.assertEquals(actual, "Policy Folder "+policy_no, "The policy "+policy_no+" is Not available.");
		Thread.sleep(4000);
	}

	
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
		
	}
	public void isAlertPresent() throws InterruptedException 
	{ 
	    try 
	    { 
	    	Thread.sleep(5000);
	        driver.switchTo().alert().accept();
	        //ExtentReporter.logger.log(LogStatus.INFO, alert.getText());
	        /*driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        alert.dismiss();*/
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
	}
	public void billingSetup() throws InterruptedException
	{
		selectDropdownByValue(policyAction, billingSetup, "Policy Action");
		waitFor(driver, 5000);
		switchToFrameUsingId(driver, "popupframe1");
		selectDropdownByValue(paymentPlan, paymentPlanValue, "Payment Plan");
		Thread.sleep(5000);
		//clickButton(billingSetupSaveBtn, "Save Button");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", billingSetupSaveBtn);
		Thread.sleep(35000);
		switchToParentWindowfromframe(driver);
	}
	public void coverageDetailsSelect()
	{
		//click(coverageTab, "Coverage");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", coverageTab);
	}
	
	public void coverageUpdates(String CoverageName, String binderForm) throws InterruptedException
	{
		for (int i = 0; i<coverageList.size();i++)
		{
			if(coverageList.get(i).getAttribute("innerHTML").equals(CoverageName))
			{
				//click(coverageList.get(i),coverageList.get(i).getAttribute("innerHTML"));
				Thread.sleep(4000);
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", coverageList.get(i));
				
				break;
			}
		}
		clickButton(driver, optionalFormBtn, "Optional Form");
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+portfolioNo[2]+"')]")));
		if (manuscriptList.isDisplayed())
		{
			clickButton(driver, manuscriptPageDeleteBtn, "Manu script Delete");
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
		}else{
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
		}

		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+portfolioNo[2]+"')]")));
		
		for(int i=0;i<manuscriptAddListformName.size();i++)
		{
			if(manuscriptAddListformName.get(i).getAttribute("innerHTML").equals(binderForm))
			{
				//click(manuscriptAddListformNameChkBox.get(i), "check Box for "+binderForm);
				//js.executeScript("arguments[0].click();", manuscriptAddListformNameChkBox.get(i));
				clickButton(driver, manuscriptAddListformNameChkBox.get(i), "check Box for "+binderForm);
				break;
			}
		}
		clickButton(driver, manuscriptAddListDoneBtn, "Done");
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+portfolioNo[2]+"')]")));
		clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
		clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
	}
	
	public void rateFunctionality() throws InterruptedException
	{
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+portfolioNo[2]+"')]")));
		clickButton(driver, rateBtn, "Rate Tab");
		Thread.sleep(3000);
		clickButton(driver, closeBtnOnViewPremiumPopup, "Close");
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+portfolioNo[2]+"')]")));
		clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
		
	}
	
	public void pdfVerify() throws Exception
	{
		clickButton(driver, previewBtn, "Preview");
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOf(loader));
		pdfread.readValue();
	}
}

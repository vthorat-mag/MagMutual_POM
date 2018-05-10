package com.mm.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.commonAction;
import com.mm.utils.commonUtilities;

public class rateApolicyPage extends commonAction {
	
	WebDriver driver;
	
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
	
	public rateApolicyPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void policySearch() throws InterruptedException, AWTException{
		
		Thread.sleep(3000);
		clearTextBox(Policy_Search, "Exter Policy text field");
		enterTextIn(Policy_Search, "Q09101299-NB18-01", "Exter Policy text field");
		click(Search_btn, "Search button");
		click(RateBtn, "Rate button");
		Thread.sleep(5000);
	}
	public void saveRatedetails() throws InterruptedException
	{
		switchToFrame(driver,"popupframe1");
	    Thread.sleep(1000);
	    selectDropdown(Continue_saving, "Y", "Continue saving without Quote"); 
	    Thread.sleep(1000);
	    click(Notify_Close, "Close button");
	    Thread.sleep(3000);
	}
	
				
	public void startExcelExport() throws InterruptedException,AWTException
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
	    	 
	    	 DateFormat dateFormatter = new SimpleDateFormat("MMddyy_hhmmss");
	 		 Date today = Calendar.getInstance().getTime();        
	 		 String date= dateFormatter.format(today);
	 		
	 		 StringSelection sysDate = new StringSelection("C:\\MM_Testcase_Output\\"+date+".xlsx");
	 		
	 	 	 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sysDate, null);

	 	 	 rob.keyPress(KeyEvent.VK_CONTROL); 									    //press 'cntrl' key
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
	}


}

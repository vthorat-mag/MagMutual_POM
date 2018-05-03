package com.mm.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.commonUtilities;

public class cisPage {
	
	WebDriver driver;
	commonUtilities util = new commonUtilities();
	
	@FindBy(id="CI_NEW_ORG")
	WebElement New_Org;

	@FindBy(name="entity_veryLongName")
	WebElement Long_Name;

	@FindBy(name="entity_organizationName")
	WebElement CIS_OrgName;

	@FindBy(name="address_addressTypeCode")
	WebElement Addr_Type;

	@FindBy(name="address_addressLine1")
	WebElement Addr_Line1;

	@FindBy(name="address_addressLine2")
	WebElement Addr_Line2;

	@FindBy(name="address_addressLine3")
	WebElement Addr_Line3;

	@FindBy(name="address_city")
	WebElement Addr_City;

	@FindBy(name="address_stateCode")
	WebElement State;


	//We will value of this zip code from config.properties, so no need of xpath for all zip codes.

	/*@FindBy(xpath="//input[@value='30004']")
	WebElement Zip_Code1;*/

	@FindBy(xpath="//input[@type='button' and @value='OK']")
	WebElement OK;

	@FindBy(name="phoneNumber_phoneNumber_DISP_ONLY")
	WebElement Ph_no;

	@FindBy(name="phoneNumber_areaCode")
	WebElement AreaCode;

	@FindBy(name="entityClass_entityClassCode")
	WebElement Classification;

	@FindBy(name="entityClass_effectiveToDate")
	WebElement Eff_To_Date;

	@FindBy(id="CI_ENTADDOU_SAV")
	WebElement Save_btn;

		
	public cisPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	public void clickOnNewOrganization()
	{
		New_Org.click();
	}
	
	
			
	/*Excel_File_Reader reader= new Excel_File_Reader("C:\\Vivek_MM_docs\\Form_Data.xlsx");
	int rowCount = reader.getRowCount("New_Org_data");
	
	for(int rowNum=2;rowNum<=rowCount;rowNum++){
		
		Thread.sleep(2000);
		String longName1 =reader.getCellData("New_Org_data", "LongName", rowNum);
		
		String Addr_Line_1 =reader.getCellData("New_Org_data", "Address_Line1", rowNum);
		
		String City1 = reader.getCellData("New_Org_data", "City", rowNum);
	
		String Phon_num = reader.getCellData("New_Org_data", "Phone_no", rowNum);
	
	    String ToDate = reader.getCellData("New_Org_data", "Class_Eff_To_Date", rowNum);
	    
	    String Area_code1 = reader.getCellData("New_Org_data", "Area_code", rowNum);*/
	
	public void enterDataInNewOrgPage() throws InterruptedException
	{
	   
   	    Long_Name.sendKeys("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	    
   	    CIS_OrgName.click();
	    
	    Addr_Line1.sendKeys("Add line 1");
	    
	    Addr_City.sendKeys("Alpharetta");
	    
	    Ph_no.sendKeys("1234567890");
	    
	    AreaCode.sendKeys("12345");
	    
	    Thread.sleep(2000);
	    
	    Eff_To_Date.sendKeys("05102018");
	    
	    util.selectDropdown(Classification, "CARRIER");
		
	    util.selectDropdown(Addr_Type, "POLICY");
	    
		util.selectDropdown(State,"GA");
	    Thread.sleep(3000);
	}
	   
	    public void selectZipCode() throws InterruptedException
	    {
	    	   /*  for(String currentWindow : handles){
	    	   if(!currentWindow.equals(parentWindow))
	    	  {
	    	   driver.switchTo().window(currentWindow);*/
	    	   String parentwindow = util.switchToWindow(driver);
	    	   Thread.sleep(4000);
	    	  	    	   
	    	   WebElement zipCode = driver.findElement(By.xpath("//input[@value='30004']")); 
	    	   
	    	   JavascriptExecutor executor = (JavascriptExecutor) driver;
	           executor.executeScript("arguments[0].click();", zipCode);

	           Thread.sleep(2000);
	    	   OK.click();

	    	   Thread.sleep(2000);
      	       util.switchToParentWindow(driver, parentwindow); 
      	      
		}
		
		public void saveNewOrgDetails() throws InterruptedException
		{
			Thread.sleep(3000);
			Save_btn.click();
		}
	}
	


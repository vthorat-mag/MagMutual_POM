package com.mm.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.ExcelUtil;
import com.mm.utils.commonAction;
import com.mm.utils.commonUtilities;

import BaseClass.CommonActionInterface;

public class cisPage  extends commonAction{
	
	WebDriver driver;
	//commonUtilities util = new commonUtilities();
	
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
		click(New_Org);
	}
			
	public void enterDataInNewOrgPage() throws Exception
	{
		ExcelUtil exlutil = new ExcelUtil();
   	    enterTextIn(Long_Name,exlutil.getCellData("TC42404", "LongName", 2));
	    
   	    click(CIS_OrgName);
	    
	    enterTextIn(Addr_Line1,exlutil.getCellData("TC42404", "Address_Line1", 2));
	    
	    enterTextIn(Addr_City,exlutil.getCellData("TC42404", "City", 2));
	    
	    enterTextIn(Ph_no,exlutil.getCellData("TC42404", "Phone_no", 2));
	    
	    enterTextIn(AreaCode,exlutil.getCellData("TC42404", "Area_code", 2));
	    Thread.sleep(2000);
	    
	    enterTextIn(Eff_To_Date,exlutil.getCellData("TC42404", "Class_Eff_To_Date", 2));
	    
	    selectDropdown(Classification, "CARRIER");
		
	    selectDropdown(Addr_Type, "POLICY");
	    
		selectDropdown(State,"GA");
	    Thread.sleep(3000);
	}
	   
	    public void selectZipCode() throws InterruptedException
	    {
	    	   String parentwindow = switchToWindow(driver);
	    	   Thread.sleep(2000);
	    	  	    	   
	    	   WebElement zipCode = driver.findElement(By.xpath("//input[@value='30004']")); 
	    	   click(zipCode);

	           Thread.sleep(1000);
	    	   click(OK);

	    	   Thread.sleep(2000);
      	       switchToParentWindowfromotherwindow(driver, parentwindow); 
      	      
		}
		
		public void saveNewOrgDetails() throws InterruptedException
		{
			Thread.sleep(3000);
			click(Save_btn);
		}
	}
	


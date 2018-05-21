package com.mm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.utils.commonAction;
import com.relevantcodes.extentreports.LogStatus;

public class findPolicyPage extends commonAction{
	WebDriver driver;
	String policyNo;
	 
	@FindBy(xpath="//input[@name='policyPhaseCodeMultiSelectText']")
	WebElement policyPhaseTextBox;
	
	@FindBy(xpath="//a[@class='panelDownTitle']")
	WebElement paneDown;
	
	@FindBy(xpath="//a[@id='AFD_policyStatus']")
	WebElement policyStatusSearch;
	
	@FindBy(xpath="//table[@class='popupcontent']//input[@name='chkMultiSelectpolicyStatus']")
	List<WebElement> policyStatusValues;
	
	@FindBy(xpath="//a[@id='AFD_policyPhaseCode']")
	WebElement policyPhaseSearch;
	
	//@FindBy(xpath="//table[@class='popupcontent']//tr//input[@name='chkMultiSelectpolicyPhaseCode']")
	@FindBy(xpath="//table[@class='popupcontent']//td[2]")
	List<WebElement> policyPhaseValues;
	
	@FindBy(xpath="//table[@class='popupcontent']//td//input[@name='chkMultiSelectpolicyPhaseCode']")
	List<WebElement> policyPhaseCheckBox;
	
	@FindBy(xpath="//div[@class='horizontalButtonCollection']//input[@value='Search']")
	WebElement searchBtn;
	
	@FindBy(xpath="//table[@class='clsGrid']//tbody//span[@id='CPOLICYNO']")
	List<WebElement> policyList;
	
	@FindBy(xpath="//table[@class='clsGrid']//tbody//input[@name='chkCSELECT_IND']")
	List<WebElement> policyListChkBox;
	
	public findPolicyPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String findQuotewithActiveState() throws InterruptedException
	{
		
		Thread.sleep(5000);//Need to replace with explicit wait
		try{
			Assert.assertTrue(paneDown.isEnabled());
			click(paneDown, "Search Criteria");
			ExtentReporter.logger.log(LogStatus.INFO, "Search Criteria Section is expanded");
		}catch (Exception e)
		{
			ExtentReporter.logger.log(LogStatus.INFO, "Search Criteria Section is already Expanded");
		}
			click(policyPhaseSearch, "Policy Phase Search Icon");
			waitFor(driver, 3);
			for(int i=0;i<policyPhaseCheckBox.size();i++)
			{
				if(policyPhaseCheckBox.get(i).getAttribute("value").equals("POLICY"))
				{
					click(policyPhaseCheckBox.get(i), policyPhaseCheckBox.get(i).getAttribute("value")+" Check Box");
				}
				if(policyPhaseCheckBox.get(i).getAttribute("value").equals("QUOTE"))
				{
					click(policyPhaseCheckBox.get(i), "Policy phase Check Box");
				}
			}
			click(policyStatusSearch, "Policy Status Search");
			for(int i=0;i<policyStatusValues.size();i++)
			{
				if(policyStatusValues.get(i).getAttribute("value").equals("ACTIVE"))
				{
					click(policyStatusValues.get(i), policyStatusValues.get(i).getAttribute("value")+" Check Box");
				}
			}
			
			click(searchBtn, "SearchButton");
			
		waitFor(driver, 5);
		try{
			if(policyList.size()>0)
			{
				for(int i=0; i<policyList.size();i++)
				{
					if(policyList.get(i).getAttribute("innerHTML").contains("NB18") && policyList.get(i).getAttribute("innerHTML").contains("-01"))
					{
						policyNo= policyList.get(i).getAttribute("innerHTML");
						break;
					}
				}
			}
		}catch (Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "No Policy available for this Criteria.");
		}
		return policyNo;
	}
	
	

}

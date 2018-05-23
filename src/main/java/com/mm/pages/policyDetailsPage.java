package com.mm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.commonAction;

public class policyDetailsPage extends commonAction {

	WebDriver driver;
	
	//policy details page
	
		@FindBy(name="policyPhaseCode")
		WebElement Phase;
		
		@FindBy(name="organizationTypeCode")
		WebElement Org_Type;
		
		@FindBy(name="discoveryPeriodRating")
		WebElement Hosp_Disc_Period_Rating;
		
		@FindBy(name="termDesc")
		WebElement Quote_Description;
		
		@FindBy(id="PM_COMMON_TABS_SAVEWIP")
		WebElement Save_WIP;
		
		@FindBy(id="PM_QT_UNDW_PUP")
		WebElement Underwriter;
				
		@FindBy(id="PM_UNDWRI_ADD")
		WebElement Add_Underwriter;
		
		@FindBy(name="entityId")
		WebElement Name;
		
		@FindBy(id="PM_ADD_UNDWRTR_OK")
		WebElement Add_Underwriter_Ok;
		
		@FindBy(name="uwTypeCode")
		WebElement UnderwriterType;
		
		@FindBy(name="entityId")
		WebElement Underwriter_name;
		
		@FindBy(id="PM_UNDWRI_SAVE")
		WebElement Save_Underwritter;
		
		@FindBy(id="PM_UNDWRI_BACK")
		WebElement Close_Underwritter;
		
		@FindBy(id="PM_QT_POLICY_FOLDER_AG")
		WebElement Policy_Action;
		
		@FindBy(id="PM_AGNT_ADD")
		WebElement Add_Agent;
		
		@FindBy(name="producerAgentLicId")
		WebElement Producer;
		
		@FindBy(id="PM_AGNT_SAVE")
		WebElement Save_Agent;
		
		@FindBy(id="PM_AGNT_CLOSE")
		WebElement Close_Agent;
		
		@FindBy(xpath="//a[@id='PM_PT_VIEWRISK']//span")
		WebElement Risk_tab;
		
	//	@FindBy(xpath="//td//div[@id='CRISKTYPECODELOVLABEL' and contains(text(),'Hospital')]")
		@FindBy(xpath="//td//div[@id='CRISKTYPECODELOVLABEL']")
		WebElement Risk_Type;
		
		@FindBy(name="riskCounty")
		WebElement Risk_Country;
		
		@FindBy(name="riskClass")
		WebElement Risk_Speciality;
		
		@FindBy(xpath="//a[@id='PM_PT_VIEWCVG']//span")
		WebElement Coverage_tab;
		
		@FindBy(id="PM_QT_COVG_ADD")
		WebElement Add_Coverage;
		
		
		
		//@FindBy(xpath="//div[@id='CPOLICYFORMCODEDESCRIPTION' and contains(text()='Claims Made')]//parent::td"
				//+ "//parent-sibling:://td//div[contains(text()='Excess Liab-Out']//parent::td//parent-sibling:://td[@type='checkbox']")
		
		/*@FindBy(xpath="//table[@id='selectCoverageGrid']//div[text()='Claims Made']//preceding-sibling::div[contains(text()='Excess Liab-Out']//preceding-sibling:://td[@type='checkbox']")
		List<WebElement> ASa;*/
		
		/*@FindBy(xpath="//div[text()='Claims Made']//parent::td"
				+ "//preceding-sibling:://td//div[text()='Excess Liab-Out']//parent::td//preceding-sibling:://td[@type='checkbox']")
		*/
		
		@FindBy(xpath="//div[text()='Excess Liab-Out']//parent::td//preceding-sibling:://td[@type='checkbox']")
		
		WebElement Checkbox;
		
		
		@FindBy(name="annualBaseRate_DISP_ONLY")
		WebElement Premium;
		
		
		@FindBy(name="retroDate")		
		WebElement Retro_Date;
		
		@FindBy(id="PM_SEL_COVG_DONE")
		WebElement Select_coverage;
		
		@FindBy(xpath="//a[@id='PM_PT_VIEWCLASS']//span")
		WebElement Coverage_Class_tab;
		
		@FindBy(id="PM_QT_COVGCLAZ_ADD")
		WebElement Add_CoverageClass;
		
		@FindBy(name="exposureUnit")
		WebElement ExposureUnit;
		
		@FindBy(id="PM_SEL_CLASS_DONE")
		WebElement Select_CoverageClass;
		
		@FindBy(id="PM_QT_MANU_PUP")
		WebElement Optional_Forms;
		
		@FindBy(id="PM_MANU_ADD")
		WebElement Add_Manuscript;
		
		@FindBy(id="PM_SEL_MANU_DONE")
		WebElement Done_Manuscript;
		
		@FindBy(id="PM_MANU_SAVE")
		WebElement Save_Manuscript;
		
		@FindBy(id="PM_MANU_CLOSE")
		WebElement Close_Manuscript;
		
		
		
		
		
		public policyDetailsPage(WebDriver driver){
			
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		}
		
		//Quote#  Q09101408-NB18-01		
		
		//	Policy Folder Q09101421-NB17-01   - new
		
		public void updatePolicyDetails(){
		
		selectDropdownByValue(Phase, "INDICATION", "Phase");
		
		selectDropdownByValue(Org_Type, "HOSPITAL", "Organisation Type");
		enterTextIn(Hosp_Disc_Period_Rating, "2");
		enterTextIn(Quote_Description, "Automated Test");
		
		click(Save_WIP, "Save WIP button");
	
		}
	
		
		public void add_Underwriter() throws InterruptedException{
			
			//popupframe1
					
			click(Underwriter, "Underwriter button");
			Thread.sleep(4000);
			
	     	List<WebElement> firstName = driver.findElements(By.id("popupframe1"));
	     	driver.switchTo().frame(firstName.get(0));
	     	
	     	
	     	
			//driver.switchTo().
			//switchToFrame(driver, "popupframe1");
			
			Thread.sleep(3000);
			//below steps are given twice with different data in TC
			//Type = Risk Mgmt
			//Name = Civali, Karen
			
			
			click(Add_Underwriter, "Add Underwriter");
			Thread.sleep(4000);
			
		//	WebElement frame_src=//iframe[contains(src,'addUnderwriter']
			//driver.switchTo().defaultContent();
			
			
			List<WebElement> secondName = driver.findElements(By.id("popupframe1"));
	     	driver.switchTo().frame(secondName.get(0));
	     	
	     	
	     	
	//		WebElement frame_src = driver.findElement(By.xpath("//iframe[contains(@src,'addUnderwriter']"));
					
			//driver.switchTo().frame(frame_src);
			
			Thread.sleep(4000);
			
			selectDropdownByVisibleText(Name, "Arwood, Ruth", "Name");  //element changed - Name
			
			Thread.sleep(2000);
			click(Add_Underwriter_Ok, "OK button");
			
			driver.switchTo().defaultContent();
			
			Thread.sleep(2000);
			
			driver.switchTo().frame(firstName.get(0));
			
		//	switchToFrame(driver, "popupframe1");  //may be we need to switch back to parent and then to second frame
			
			Thread.sleep(3000);
			
			selectDropdownByVisibleText(UnderwriterType, "Claims Rep", "Type");
			
			selectDropdownByVisibleText(Underwriter_name, "Angelly, Sandy", "Name");
			
		}
		/*	public void underwriterTeamMemberInformation(WebDriver driver){
		
		selectDropdownByVisibleText(UnderwriterType, "Claims Rep", "Type");
		
		selectDropdownByVisibleText(Underwriter_name, "Angelly, Sandy", "Name");
		
	}*/
		
		public void saveUnderwriter() throws InterruptedException{
			
			Thread.sleep(3000);
			click(Save_Underwritter, "Save button");
			click(Close_Underwritter, "Close button");
			
			switchToParentWindowfromframe(driver);
			Thread.sleep(3000);
			
			click(Save_WIP, "Save WIP button");
			
		}
		
		
		public void addAgent() throws InterruptedException{
			
			Thread.sleep(3000);
			selectDropdownByVisibleText(Policy_Action,"Agent","Policy Action");
			
			Thread.sleep(3000);
			
			switchToFrameUsingId(driver, "popupframe1");
			Thread.sleep(2000);
			click(Add_Agent, "Add button");
			
			Thread.sleep(3000);
			selectDropdownByVisibleText(Producer, "AB Risk Specialist, Inc. (AG00045, Med. Mal. PL, 02/17/2015 - 01/01/3000)", "Producer");
			
			click(Save_Agent, "Save button");
			click(Close_Agent, "Close button");
			
			switchToParentWindowfromframe(driver);
			
	//		click(Save_WIP, "Save WIP button");   not in test, might be needed
			
		}
		
		public void addRisk() throws InterruptedException{
			
			Thread.sleep(3000);
			click(Risk_tab, "Risk tab");
			Thread.sleep(3000);
			click(Risk_Type, "Risk Type");
			Thread.sleep(3000);
			
			selectDropdownByVisibleText(Risk_Country, "Appling", "Risk Country");
			
			selectDropdownByVisibleText(Risk_Speciality, "Acute Care - 900010", "Risk speciality");
			
			
			click(Save_WIP, "Save WIP"); // not in test, might be needed
			
		}
		
		public void addCoverage() throws InterruptedException{
			
			click(Coverage_tab, "Coverage tab");
		
			Thread.sleep(3000);
// ****** below step will be called 7 times using different data, use text as parameter for value in xpath
			
			click(Add_Coverage, "Add button");
			
			Thread.sleep(3000);
			switchToFrameUsingId(driver, "popupframe1");
			
			Thread.sleep(2000);
			click(Checkbox, "checkbox from coverage list");			//change comment label
			
			Thread.sleep(2000);
			enterTextIn(Premium, "12 Month Premium");
			enterTextIn(Retro_Date, "Retroactive Date");
			
			click(Select_coverage, "Select button for coverage");
			
			switchToParentWindowfromframe(driver);
			
			Thread.sleep(3000);
			List<WebElement> Coverage_List=driver.findElements(By.xpath("tr[@class='hiliteSelectRow']//td//div[@id='CPRODUCTCOVERAGEDESC']"));
			if(Coverage_List.get(1).getAttribute("innerHTML").equals("Prof Liab-Out"))
			{
				Coverage_List.get(1).click();
			}
			
			click(Coverage_Class_tab, "Coverage class tab");
			Thread.sleep(2000);
			click(Add_CoverageClass, "Add button for coverage class");
			Thread.sleep(2000);
			switchToFrameUsingId(driver, "popupframe1");
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//div[@id='CCOVERAGECLASSLONGDESCRIPTION'and contains(text(),'Acute Care')]"
					+ "//parent::td//preceding-sibling::td//preceding-sibling::td//input[@type='checkbox']")).click();;
		
		
			enterTextIn(ExposureUnit, "50");

			click(Select_CoverageClass, "Select button for Coverage class");
			
			Thread.sleep(2000);
			switchToParentWindowfromframe(driver);
			
			
			click(Coverage_tab, "Coverage tab");
			Thread.sleep(2000);
			click(Optional_Forms, "Optional Forms button");
			
			Thread.sleep(3000);
			switchToFrameUsingId(driver, "popupframe1");
			Thread.sleep(2000);
			click(Add_Manuscript, "Add button for Manuscript");
			Thread.sleep(3000);
			switchToFrameUsingId(driver, "popupframe4");
			
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@id='CSHORTDESCRIPTION'and contains(text(),'INDICATION')]//parent::td//preceding-sibling::"
					+ "td//preceding-sibling::td//input[@type='checkbox']")).click();
		
			switchToFrameUsingId(driver, "popupframe1");
			
			Thread.sleep(3000);
			click(Save_Manuscript, "Save button for Manuscript");
			
			click(Close_Manuscript, "Close button for Manuscript");
			
			switchToParentWindowfromframe(driver);
		}

}

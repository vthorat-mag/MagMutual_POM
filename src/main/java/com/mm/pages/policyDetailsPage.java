package com.mm.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.ExtentReporter;
import com.mm.utils.commonAction;
import com.relevantcodes.extentreports.LogStatus;

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
		
		@FindBy(id="PM_QT_LIMIT_SHARING")
		WebElement Limit_Sharing;
		
		@FindBy(id="PM_SHARED_GROUP_ADD")
		WebElement Add_Shared_Group;
		
		@FindBy(name="shareGroupDesc")
		WebElement Desc_Shared_Group;		
		
		@FindBy(id="PM_SHARED_DETAIL_ADD")
		WebElement Add_Shared_Group_Details;
					
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
		
		@FindBy(xpath="//a[@id='PM_PT_VIEWPOL']//span")
		WebElement Policy_tab;
		
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
		
		@FindBy(xpath="//table[@id='selectCoverageGrid']//tr//input[@name='chkCSELECT_IND']")
		List<WebElement> selectCoverageChkBox;
		
		@FindBy(xpath="//table[@id='selectCoverageGrid']//tr//div[@id='CPRODUCTCOVERAGEDESC']")
		List<WebElement> selectCoveragevalues;
		
		@FindBy(xpath="//table[@id='selectCoverageGrid']//tr//div[@id='CPOLICYFORMCODEDESCRIPTION']")
		List<WebElement> selectPolicyForm;
		
		@FindBy(xpath="//table[@id='selectCoverageClassGrid']//tr//input[@name='chkCSELECT_IND']")
		List<WebElement> selectCoverageClassChkBox;
		
		@FindBy(xpath="//table[@id='selectCoverageClassGrid']//tr//div[@id='CCOVERAGECLASSLONGDESCRIPTION']")
		List<WebElement> selectCoverageClass;
				
		@FindBy(xpath="//table[@id='selectSharedDetailGrid']//tr//input[@name='chkCSELECT_IND']")
		List<WebElement> selectSharedGroupDetailChkBox;
		
		@FindBy(xpath="//table[@id='selectSharedDetailGrid']//tr//input[@name='chkCSHAREDTLOWNERB']")
		List<WebElement> selectSharedGroupOwnerChkBox;
		
		@FindBy(xpath="//table[@id='selectSharedDetailGrid']//tr//div[@id='CSHAREDTLCOVERAGESHORTDESC']")
		List<WebElement> selectCoverage;
				
		@FindBy(id="PM_SEL_SHARED_DTL_DONE")
		WebElement Done_Shared_Details;
		
			
		//VT locators
		
		@FindBy(xpath="//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
		WebElement policyAction;
		
		@FindBy(id="pageTitleForpageHeaderForPolicyFolder")
		WebElement pageHeaderForPolicyFolder;
				
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
		
		@FindBy(xpath = "//input[@id='PM_QT_MANU_PUP']")
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
		
		@FindBy(id="PM_LIMIT_SHARING_SAVE")
		WebElement Save_Limit_Sharing;
				
		@FindBy(id="PM_LIMIT_SHARING_CLOSE")
		WebElement Close_Limit_Sharing;
				
	
	//Initialize driver and page elements in constructor
		
	public policyDetailsPage(WebDriver driver){
			
			this.driver=driver;
			PageFactory.initElements(driver, this);
		
		}
				
		  
	// Update policy details for a policy and change policy phase from Submission to Indication.
	
	public void updatePolicyDetails() throws InterruptedException{
		
		//	Thread.sleep(2000);
		
		
		
			waitForElementToLoad(driver, 10, Phase);
		
			selectDropdownByValue(Phase, "INDICATION", "Phase");
		
			selectDropdownByValue(Org_Type, "HOSPITAL", "Organisation Type");
			enterTextIn(Hosp_Disc_Period_Rating, "2");
			enterTextIn(Quote_Description, "Automated Test");
		
			click(Save_WIP, "Save WIP button");
	
			ExtentReporter.logger.log(LogStatus.INFO, "Indication saved as WIP");
	}
	
	//Select Underwriter button from Policy tab, a pop up appears
		
	public List<WebElement> open_Underwriter() throws InterruptedException{
			
		//	Thread.sleep(2000);
			waitForElementToLoad(driver, 10, Underwriter);
		
		
			click(Underwriter, "Underwriter button");
			Thread.sleep(4000);
			ExtentReporter.logger.log(LogStatus.INFO, "Underwriter window is displayed");
			
	     	List<WebElement> firstName = driver.findElements(By.id("popupframe1"));
	     	driver.switchTo().frame(firstName.get(0));
	     	
	     	return firstName;
	     		     				
		}
		
	
	//Add Underwriters info, create underwriters and Save
	
	public void add_Underwriter(List<WebElement> firstName, String name, String type, String underWriter_Name) throws InterruptedException{
				
		
		
			waitForElementToLoad(driver, 10, Add_Underwriter);
			
			click(Add_Underwriter, "Add Underwriter");
			Thread.sleep(4000);
			ExtentReporter.logger.log(LogStatus.INFO, "Add Underwriter window displayed");
			
			List<WebElement> secondName = driver.findElements(By.id("popupframe1"));
	     	driver.switchTo().frame(secondName.get(0));
	     			
			Thread.sleep(4000);
			
			selectDropdownByVisibleText(Name, name, "Name");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Underwriter is added to list");
			
			Thread.sleep(2000);
			click(Add_Underwriter_Ok, "OK button");
			
			driver.switchTo().defaultContent();
			
			Thread.sleep(2000);
			
			driver.switchTo().frame(firstName.get(0));
			
			Thread.sleep(3000);
			
			selectDropdownByVisibleText(UnderwriterType,type, "Type");
			ExtentReporter.logger.log(LogStatus.INFO, "Underwriting Team Member List displays the updated type for Entity");
			
			selectDropdownByVisibleText(Underwriter_name,underWriter_Name, "Underwriter Name");
			ExtentReporter.logger.log(LogStatus.INFO, "Name is displayed");
			
			
			Thread.sleep(2000);
			click(Save_Underwritter, "Save button");
		}
	
	
	//Close 'Maintain Underwriting team' pop up
	
	public void close_Underwriter() throws InterruptedException{
			
			Thread.sleep(3000);
			click(Close_Underwritter, "Close button");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Underwriter Window is closed");
			
			switchToParentWindowfromframe(driver);
			Thread.sleep(3000);
			
			click(Save_WIP, "Save WIP button");
			ExtentReporter.logger.log(LogStatus.INFO, "WIP is saved");
		}
		
	//Select Agent from Policy Action drop down and Add Agent information in pop up and save Agent
	
	public void addAgent() throws InterruptedException{
			
			Thread.sleep(3000);
			selectDropdownByVisibleText(Policy_Action,"Agent","Policy Action");
			ExtentReporter.logger.log(LogStatus.INFO, "	Agent window is open");
			
			Thread.sleep(3000);
			
			switchToFrameUsingId(driver, "popupframe1");
			Thread.sleep(2000);
			click(Add_Agent, "Add button");
			ExtentReporter.logger.log(LogStatus.INFO, "Producer Agent Entry window opens");
			Thread.sleep(3000);
			selectDropdownByVisibleText(Producer, "AB Risk Specialist, Inc. (AG00045, Med. Mal. PL, 02/17/2015 - 01/01/3000)", "Producer");
			ExtentReporter.logger.log(LogStatus.INFO, "Agent is selected");
			click(Save_Agent, "Save button");
			Thread.sleep(2000);
			click(Close_Agent, "Close button");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Agent is saved to policy and window is closed");
			
			switchToParentWindowfromframe(driver);
			
	//		click(Save_WIP, "Save WIP button");   not in test, might be needed
			
		}
		
	//Select Risk tab and fill the Risk Information
	
	public void addRiskInformation() throws InterruptedException{
			
			Thread.sleep(3000);
			
			click(Risk_tab, "Risk tab");
			ExtentReporter.logger.log(LogStatus.INFO, "Risk tab is displayed");
			
			Thread.sleep(3000);
			
			click(Risk_Type, "Risk Type");
			Thread.sleep(3000);
			ExtentReporter.logger.log(LogStatus.INFO, "Hospital Risk is highlighted");
			
			selectDropdownByVisibleText(Risk_Country, "Appling", "Risk Country");
			
			selectDropdownByVisibleText(Risk_Speciality, "Acute Care - 900010", "Risk speciality");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Risk information is displayed and selected");
			
			click(Save_WIP, "Save WIP"); // not in test, might be needed
			
		}
	
	//Select Coverage tab and click on Add button, the pop up appears
		
	public void addCoverage() throws InterruptedException{
			Thread.sleep(2000);
			
			click(Coverage_tab, "Coverage tab");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Coverage tab displays with the primary defaulting in the dropdown");
		
			Thread.sleep(3000);
			
			click(Add_Coverage, "Add button");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Select Coverage window displays");
			
			Thread.sleep(3000);
			switchToFrameUsingId(driver, "popupframe1");
		
		}
		
		
	//Select Coverage from the pop up List appearing after 'Add' button on coverage tab
		
	public void selectCoverageFromPopupList(String Date, String Amount,String Coverage, String PolicyForm,String elementName1, String elementName2) throws InterruptedException{
			
			Thread.sleep(2000);
			
			for(int i=0;i<selectCoverageChkBox.size();i++)
				
			{
				if( selectCoveragevalues.get(i).getAttribute("innerHTML").equals(Coverage)&& selectPolicyForm.get(i).getAttribute("innerHTML").equals(PolicyForm))
				{
					clickButton(driver, selectCoverageChkBox.get(i),"Coverage check box");
					ExtentReporter.logger.log(LogStatus.INFO, "Coverage is selected from list");
					break;
					
				}else if(selectCoveragevalues.get(i).getAttribute("innerHTML").equals(Coverage))
				{
					clickButton(driver, selectCoverageChkBox.get(i),"Coverage check box");
					ExtentReporter.logger.log(LogStatus.INFO, "Coverage is selected from Grid");
					break;
				}
			}
			
			Thread.sleep(2000);
			try{
				
				if(Retro_Date.isDisplayed())
				{
					enterTextIn(Premium, Amount, elementName1);
					enterTextIn(Retro_Date, Date, elementName2);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
		
	
	//Close Select Coverage pop up
	
	public void closeAddCoveragetab(){
					
			click(Select_coverage, "Select button for coverage");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Information has been entered and coverage has been added to primary risk");	
			switchToParentWindowfromframe(driver);
			
		}
		
		
	 
	//Select and return the policy# from source of a frame to be utilized for switching to frame
	
	public String policyNo() throws InterruptedException
		{
			Thread.sleep(2000);
			String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
			String[] portfolioNo = profileNoLable.split(" ",3);
			return portfolioNo[2];
		}
		
		
	
	//Select Coverage from the List given in Grid on Coverage tab
		
	public void selectCoverageFromGridList(String Coverage) throws InterruptedException	{
			
			Thread.sleep(3000);
			for (int i = 0; i<coverageList.size();i++)
			{
				if(coverageList.get(i).getAttribute("innerHTML").equals(Coverage))
				{
					Thread.sleep(3000);
					clickButton(driver, coverageList.get(i), Coverage);
					ExtentReporter.logger.log(LogStatus.INFO, "Coverage is selected from Grid");
					break;
				}
			
			}
			
		}
		
	//Add Retro Date and Premium Amount for selected coverage
	
	public void addDataInCoverage(String Date, String Amount, String elementName1, String elementName2) throws InterruptedException{
		
			Thread.sleep(2000);
		
			if(Retro_Date.isDisplayed()){
			
			enterTextIn(Premium, Amount, elementName1);
			enterTextIn(Retro_Date, Date, elementName2);
			
			}else if(Premium.isDisplayed()){
			
			enterTextIn(Premium, Amount, elementName1);
			}
		}
	
	
	//Add Retro Date for selected coverage
	
	public void addRetroDateInCoverage(String Date, String elementName2){
		
	try{
			
		if(Retro_Date.isDisplayed()){
			
			enterTextIn(Retro_Date, Date, elementName2);
		}
	}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	
	//Select Coverage Class tab, Add Coverage Class and save
	
	public void addCoverageClass() throws InterruptedException{
			
			Thread.sleep(5000);
			clickButton(driver, Coverage_Class_tab, "Coverage class tab");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Coverage Class Tab Displays");
			Thread.sleep(3000);
			clickButton(driver, Add_CoverageClass,  "Add button for coverage class");
			ExtentReporter.logger.log(LogStatus.INFO, "Select Coverage class window displays");
			Thread.sleep(2000);
			switchToFrameUsingId(driver, "popupframe1");
			Thread.sleep(4000);
			
									
			for(int i=0;i<selectCoverageClassChkBox.size();i++)
			{
				if(selectCoverageClass.get(i).getAttribute("innerHTML").equals("Acute Care"))
				{
							
					clickButton(driver, selectCoverageClassChkBox.get(i), "Coverage Class selection");
					
					break;
				}
			}
		
			Thread.sleep(2000);			
		
			enterTextIn(ExposureUnit, "50");

			click(Select_CoverageClass, "Select button for Coverage class");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Coverage class is saved.");
		
			switchToParentWindowfromframe(driver);
			
			Thread.sleep(2000);
			click(Coverage_tab, "Coverage tab");
			
		}
		
		//Select Coverage tab and add Manuscript from optional forms and Save
	
		public void coverageUpdates(String CoverageName, String binderForm, String PolicyNo) throws InterruptedException{
			Thread.sleep(2000);
			for (int i = 0; i<coverageList.size();i++)
			{
				if(coverageList.get(i).getAttribute("innerHTML").equals(CoverageName))
				{
					Thread.sleep(2000);
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("arguments[0].click();", coverageList.get(i));
					
					ExtentReporter.logger.log(LogStatus.INFO, "Coverage is highlighted");
					break;
				}
			}
			
			
			
			Thread.sleep(2000);
			clickButton(driver, optionalFormBtn, "Optional Form");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Manuscript Information Window displays");
			Thread.sleep(2000);
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
						
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
			
			ExtentReporter.logger.log(LogStatus.INFO, "Add Manuscript window displays");
			
			Thread.sleep(1000);
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
			
			Thread.sleep(3000);
			for(int i=0;i<manuscriptAddListformName.size();i++)
			{
				if(manuscriptAddListformName.get(i).getAttribute("innerHTML").equals(binderForm))
				{
					clickButton(driver, manuscriptAddListformNameChkBox.get(i), "check Box for "+binderForm);
					break;
				}
			}
			
			
			clickButton(driver, manuscriptAddListDoneBtn, "Done");
			ExtentReporter.logger.log(LogStatus.INFO, "Window closes and forms are attached to Policy");
			
			switchToParentWindowfromframe(driver);
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
			Thread.sleep(2000);
			clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
			Thread.sleep(2000);
			clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
			ExtentReporter.logger.log(LogStatus.INFO, "Form is saved to coverage and window closes");
			
			switchToParentWindowfromframe(driver);
			Thread.sleep(2000);
			click(Save_WIP, "Save WIP");
		}

		
		//Open Limit Sharing pop up and switch to pop up
		
		public void openLimitSharingTab(String PolicyNo) throws InterruptedException{
			
						
			Thread.sleep(2000);
			
			clickButton(driver, Policy_tab, "Policy tab");
			
			Thread.sleep(1000);
			
			click(Limit_Sharing, "Limit Sharing button");
			Thread.sleep(2000);
			
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
			
		}
			
		
		//Add Shared group from the limit sharing tab
		
		public void addSharedGroup(String CoverageName, String Description,String PolicyNo) throws InterruptedException{
				
			Thread.sleep(2000);
			click(Add_Shared_Group, "Add button for Shared group");
			
			Thread.sleep(1000);
			
			selectDropdownByVisibleText(Desc_Shared_Group, Description, "Shared group description");
			
			click(Add_Shared_Group_Details, "Add button for Shared Group details");
			
			Thread.sleep(3000);
			
			List<WebElement> firstName = driver.findElements(By.id("popupframe1"));
	     	driver.switchTo().frame(firstName.get(0));
			Thread.sleep(2000);
			
					
			for(int i=0;i<selectSharedGroupDetailChkBox.size();i++)
				{
					if(selectCoverage.get(i).getAttribute("innerHTML").equals(CoverageName))
					{
						clickButton(driver, selectSharedGroupDetailChkBox.get(i), "Coverage Class selection");
						clickButton(driver, selectSharedGroupOwnerChkBox.get(i), "Owner checkbox");
						break;
					}
			}
			
			Thread.sleep(2000);
			
			click(Done_Shared_Details, "Done button for Select Shared Group details");
			
			switchToParentWindowfromframe(driver);
			
			Thread.sleep(1000);
			
			switchToFrameUsingId(driver, "popupframe1");
		}
			
		//Close Limit Sharing pop up
		
		public void closeLimitSharingtab() throws InterruptedException{
		
			Thread.sleep(2000);
			
			click(Save_Limit_Sharing, "Save button");
					
			click(Close_Limit_Sharing, "Close button");
			switchToParentWindowfromframe(driver);
			
		}	
}

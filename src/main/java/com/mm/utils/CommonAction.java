package com.mm.utils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.pages.PolicyQuotePage;
import com.relevantcodes.extentreports.LogStatus;

import BaseClass.CommonActionInterface;

public class CommonAction implements CommonActionInterface {

	Properties pro = new Properties();

	// Integer.valueOf(properties.prop.getProperty("Medium"));

	int Low = 10;
	int Medium = 30;
	int High = 50;
	String findPolicyQuotePage = "Find Policy/Quote";

	public void selectValue(WebDriver driver, WebElement pageElement, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), pageElement + " is Not displayed on screen.");
			ExtentReporter.logger.log(LogStatus.PASS, "Selected value " + value);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", pageElement);
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, value + " element is not found.");
		}
	}

	public void switchToSecondFramefromFirst(WebDriver driver, String frameID) {

		List<WebElement> secondFrame = driver.findElements(By.id(frameID));
		driver.switchTo().frame(secondFrame.get(0));

	}

	public void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow) {

		try {
			driver.switchTo().window(parentwindow);
			ExtentReporter.logger.log(LogStatus.INFO, "Control switched back to parent window.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.WARNING, "Error while switching to parent window.");
		}
	}

	public void switchToFrameUsingId(WebDriver driver, String frameName) throws InterruptedException {
		Thread.sleep(3000);
		try {
			driver.switchTo().frame(frameName);
			ExtentReporter.logger.log(LogStatus.INFO, "Control switched to frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.WARNING, "Error while switching to frame.");
		}

	}

	public void switchToFrameUsingElement(WebDriver driver, WebElement element) throws Exception {
		Thread.sleep(4000);
		try {
			driver.switchTo().frame(element);
			ExtentReporter.logger.log(LogStatus.INFO, "Control switched to frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.WARNING, "Error while switching to frame.");
		}
	}

	public void switchToParentWindowfromframe(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			ExtentReporter.logger.log(LogStatus.PASS, "User Switched to default frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to default frame.");
		}
	}

	public String getSelectedTextFromDropDown(WebDriver driver, WebElement dropDownElement) {

		Select dropDownList = new Select(dropDownElement);
		String selectedDDLValue = dropDownList.getFirstSelectedOption().getText();

		return selectedDDLValue;
	}

	public String randomNumGenerator() {
		return RandomStringUtils.random(3, "1234567890");
	}

	// Enter text values in the text field
	public void enterTextIn(WebDriver driver, WebElement pageElement, String text, String textField) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
			pageElement.sendKeys(text);
			ExtentReporter.logger.log(LogStatus.PASS, "Value " + text + " is entered in text field " + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

	// Enter the data values which are not text, like date and amount.
	public void enterDataIn(WebDriver driver, WebElement pageElement, String text, String textField) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value=text;", pageElement);
			ExtentReporter.logger.log(LogStatus.PASS, "Value " + text + " entered in text field " + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

	public void clickButton(WebDriver driver, WebElement pageElement, String buttonName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.elementToBeClickable(pageElement));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Assert.assertTrue(pageElement.isDisplayed(), buttonName + " button is Not displayed on screen.");
			js.executeScript("arguments[0].click();", pageElement);
			ExtentReporter.logger.log(LogStatus.PASS, "clicked on button / Link- " + buttonName);
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, buttonName + " element is not found.");
		}
	}

	public void waitFor(WebDriver driver, long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void close(WebDriver driver) {
		driver.quit();
		ExtentReporter.logger.log(LogStatus.PASS, "Browser is closed.");
	}

	public void takeScreenShot(String pageTitle) {
		// TODO Auto-generated method stub
	}

	public void navigatetoMenuItemPage(WebDriver driver, WebElement mainMenu, WebElement menuItem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", mainMenu);
		Actions act = new Actions(driver);
		act.moveToElement(mainMenu).build().perform();
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", menuItem);
		invisibilityOfLoader(driver);
	}

	public String getPageTitle(WebDriver driver, String expectedPageTitle) throws InterruptedException {
		invisibilityOfLoader(driver);
		Thread.sleep(3000);
		List<WebElement> getPageTitleFromPage = driver.findElements(By.xpath("//div[@class='pageTitle']"));
		WebDriverWait wait = new WebDriverWait(driver, High);
		try {
			int i=0;
			for (i = 0; i < getPageTitleFromPage.size(); i++) {
				if ((getPageTitleFromPage.get(i).getAttribute("innerHTML").trim().equals(expectedPageTitle))) {
					ExtentReporter.logger.log(LogStatus.PASS,
							getPageTitleFromPage.get(i).getAttribute("innerHTML").trim()
									+ " is sucessfully displayed.");
					break;
				}
			}
			// if Expected page title is not found then below code will stop the
			// test case and throw the error.
			if (i == getPageTitleFromPage.size()) {
				ExtentReporter.logger.log(LogStatus.FAIL, expectedPageTitle + " Page is NOT displayed");
				assertTrue(false, expectedPageTitle + " Page is NOT displayed");
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, " Expected page title is NOT displayed.");
			return "false";
		}
		return "true";
	}

	public String getPageTitleWithPolicyNumber(WebDriver driver, String policyNum) throws InterruptedException {
		Thread.sleep(5000);

		List<WebElement> pageheaders = driver.findElements(By.xpath("//div[@class='pageTitle']"));
		WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOf(pageLoader));
		Assert.assertEquals(pageheaders.get(1).getAttribute("innerHTML").trim(), "Policy Folder " + policyNum,
				"Page title is not matching.");

		/*
		 * String getPageTitleFromPage =
		 * driver.findElement(By.xpath("//div[@class='pageTitle']"))
		 * .getAttribute("innerHTML").trim(); WebDriverWait wait = new
		 * WebDriverWait(driver, Medium); // WebDriverWait wait=new
		 * WebDriverWait(driver, 40); WebElement pageLoader =
		 * driver.findElement(By.xpath("//span[@class='txtOrange']"));
		 * wait.until(ExpectedConditions.invisibilityOf(pageLoader)); //
		 * wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.
		 * xpath("//div[@class='pageTitle']"))));
		 * Assert.assertEquals(getPageTitleFromPage, expectedPageTitle,
		 * "Page title is not matching.");
		 */
		return null;
	}

	public String getText(WebDriver driver, WebElement pageElement) {
		WebDriverWait wait = new WebDriverWait(driver, Medium);
		wait.until(ExpectedConditions.visibilityOf(pageElement));
		return pageElement.getAttribute("innerHTML");
	}

	public void clearTextBox(WebDriver driver, WebElement pageElement, String textField) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is displayed");
			pageElement.clear();
			ExtentReporter.logger.log(LogStatus.PASS, "Cleared the initial contents from " + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

	public String getAttributeValue(WebElement pageElement, String attributeName) {

		return null;
	}

	public void click(WebDriver driver, WebElement pageElement, String ElementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), ElementName + " is not displayed on screen.");
			pageElement.click();
			ExtentReporter.logger.log(LogStatus.PASS, "clicked on Element - " + ElementName);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, ElementName + " element is not found on page.");
		}
	}

	public void visibilityOfElement(WebDriver driver, WebElement pageElement, String text) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), "Logo / text " + text + " is not displayed on the page.");
			ExtentReporter.logger.log(LogStatus.PASS, "Logo / text " + text + " is displayed on page");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, text + " is not displayed on page");
		}
	}

	public String switchToWindow(WebDriver driver) throws InterruptedException {

		ExtentReporter.logger.log(LogStatus.INFO, "Switching to the pop up window");

		String parentWindow = driver.getWindowHandle();
		
		Set<String> handles = driver.getWindowHandles(); // Return a set of
															// window handle
		Thread.sleep(3000);
		for (String currentWindow : handles) {
			if (!currentWindow.equals(parentWindow)) {
				driver.switchTo().window(currentWindow);
				driver.manage().window().maximize();
				ExtentReporter.logger.log(LogStatus.INFO, "Control is switched to pop up window");
			}
		}
		Thread.sleep(2000);
		return parentWindow;
	}

	// Select drop down value based on passed parameter driver, element locator
	// for drop down, DropDown Option and lable of drop down.
	public void selectDropdownByValue(WebDriver driver, WebElement element, String DropDownOption, String label) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(element));
			Select Sel = new Select(element);
			Sel.selectByValue(DropDownOption);
			ExtentReporter.logger.log(LogStatus.PASS, DropDownOption+" value is selected from " + label + " drop down list");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "No value is selected from " + label + " drop down list");
		}
	}

	public void selectDropdownByVisibleText(WebDriver driver, WebElement element, String DropDownOption, String label) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(element));
			Select Sel = new Select(element);
			Sel.selectByVisibleText(DropDownOption);
			ExtentReporter.logger.log(LogStatus.PASS, DropDownOption + "  is selected from " + label + " drop down list");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "No value is selected from " + label + " drop down list");
		}

	}

	public void excelRead() throws Exception {
		ExcelUtil exlutil = new ExcelUtil();
	}

	public Boolean verifyValueFromField(WebDriver driver, WebElement pageElement, String expectedValue,
			String attributeName, String fieldName) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertEquals(pageElement.getAttribute(attributeName).trim(), expectedValue,
					"Value entered/ selected in " + fieldName + " is NOT as expected. Expected value is "
							+ expectedValue + ". And actual value is  " + pageElement.getAttribute(attributeName).trim()
							+ ".");
			ExtentReporter.logger.log(LogStatus.PASS, expectedValue,
					"Value entered/ selected in " + fieldName + " is as expected. Expected value is " + expectedValue
							+ ". And actual value is  " + pageElement.getAttribute(attributeName).trim() + ".");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, expectedValue,
					"Value entered/ selected in " + fieldName + " is NOT as expected. Expected value is "
							+ expectedValue + ". And actual value is  " + pageElement.getAttribute(attributeName).trim()
							+ ".");
			return false;
		}

	}

	// This method is called to load a page during navigation through pages
	public void waitForPageLoad(WebDriver driver, int Timeout) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(pageLoadCondition);

	}

	public void waitForElementToLoad(WebDriver driver, int time, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void acceptAlert(WebDriver driver) {

		Alert saveAlert = driver.switchTo().alert();
		saveAlert.accept();
	}

	public String getAlertText(WebDriver driver) {
		String saveAlertText = driver.switchTo().alert().getText();
	
	public static boolean isAlertPresent(WebDriver driver) throws InterruptedException{
         try{
        	 Thread.sleep(2000);
             driver.switchTo().alert();
             return true;
             }catch(NoAlertPresentException ex){
                   return false;
             }
         }
	
	
	public void acceptAlert(WebDriver driver){
		
		Alert saveAlert= driver.switchTo().alert();
			  saveAlert.accept();
			  
	}
	
	
	public String getAlertText(WebDriver driver){
		String saveAlertText= driver.switchTo().alert().getText();
		return saveAlertText;
	}

	public void policySearch(WebDriver driver, String policyNo, WebElement policySearchTxtBox, WebElement searchBtn,WebElement policyList) {
		clearTextBox(driver, policySearchTxtBox, "Enter Policy text field");
		ExtentReporter.logger.log(LogStatus.INFO, "Click policy in right corner of screen");
		enterTextIn(driver, policySearchTxtBox, policyNo, "Enter Policy text field");
		click(driver, searchBtn, "Search button");
		invisibilityOfLoader(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(verifyPolicyListDispOnQAEnv(driver,policyList)==true){
			//clickButton(driver, policyList, "First policy from Searched Policies");
			Actions action = new Actions(driver);
			action.click(policyList).build().perform();
		}
		ExtentReporter.logger.log(LogStatus.INFO, "Enter Policy # into Policy entry box, Click Search.");
	}

	public boolean verifyPolicyListDispOnQAEnv(WebDriver driver, WebElement policyList) {
		try{
		if (policyList.isDisplayed()) {
			return true;
		}
		else {return false;}
		}catch(Exception e){
			return false;
		}
	}

	public void invisibilityOfLoader(WebDriver driver) {

		try {
			if (verifypageloaderdisplayedornot(driver) == true) {
				WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
				WebDriverWait wait = new WebDriverWait(driver, Medium);
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(pageLoader));
				ExtentReporter.logger.log(LogStatus.PASS, "Page Loader disappeared sucessfully.");
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.WARNING, "Page is taking longer time than usual for loading.");
		}
	}

	public void copyFile(String saveFilName) {
		File source = new File("C:\\TempsaveExcel\\OnDemandInvoiceCredit.xlsx");
		File dest = new File("C:\\saveExcel\\" + saveFilName + ".xlsx");
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDataFromExcel(String sheetName, String columnName, int rowNum, String filePath)
			throws IOException {

		String excelFilePath = filePath;
		FileInputStream inputStream;
		String returnCellValue = null;
		inputStream = new FileInputStream(new File(excelFilePath));

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet dataSheet = (XSSFSheet) workbook.getSheet(sheetName);

			Row headerRow = dataSheet.getRow(0);

			for (int cellNumber = headerRow.getFirstCellNum(); cellNumber <= headerRow.getLastCellNum()
					- 1; cellNumber++) {
				Cell headerCell = headerRow.getCell(cellNumber);

				System.out.println(headerCell.getStringCellValue().toLowerCase());
				if (headerCell.getStringCellValue().toLowerCase().trim().equals(columnName.toLowerCase())) {
					// System.out.println("cell found");
					Row dataRow = dataSheet.getRow(rowNum);
					Cell dataCell = dataRow.getCell(cellNumber);
					returnCellValue = dataCell.getStringCellValue();
					break;
				}

			} // for loop - cellNumber
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.flush();
			outputStream.close();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return returnCellValue;
	}

	public static void writeData(String testCaseId, String columnName, String cellValue, int rowNum,
			String saveDataFilePath) throws Exception {
		String excelFilePath = saveDataFilePath;
		FileInputStream inputStream;

		inputStream = new FileInputStream(new File(excelFilePath));
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet dataSheet = (XSSFSheet) workbook.getSheet(testCaseId);

			Row headerRow = dataSheet.getRow(0);

			for (int cellNumber = headerRow.getFirstCellNum(); cellNumber <= headerRow.getLastCellNum()
					- 1; cellNumber++) {
				Cell headerCell = headerRow.getCell(cellNumber);
				System.out.println(headerCell.getStringCellValue().toLowerCase());
				if (headerCell.getStringCellValue().toLowerCase().trim().equals(columnName.toLowerCase())) {
					Row dataRow = dataSheet.getRow(rowNum);
					Cell dataCell = dataRow.getCell(cellNumber);
					dataCell.setCellValue(cellValue);
					break;
				}

			} // for loop - cellNumber
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.flush();
			outputStream.close();

		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}

	}

	public boolean verifypageloaderdisplayedornot(WebDriver driver) {
		try {
			if (driver.findElement(By.xpath("//span[@class='txtOrange']")).isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, "Page Loader is not displayed.");
		}
		return false;
	}

	
	public void refreshAPage(WebDriver driver){
		driver.navigate().refresh();
	}
	
	
	public void saveOption(WebDriver driver, WebElement saveOptionBtn, WebElement saveAsDropDown,
			WebElement saveOKBtn, WebElement exitOK, String saveAsValue)
			throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options");
		waitForElementToLoad(driver, 15, saveOptionBtn);
		clickButton(driver, saveOptionBtn, "Save Option");
		Thread.sleep(2000);
		switchToFrameUsingId(driver, "popupframe1");
		getPageTitle(driver, "Save As");
		selectDropdownByVisibleText(driver, saveAsDropDown, saveAsValue, "Selected " + saveAsValue);
		ExtentReporter.logger.log(LogStatus.INFO, "Select " + saveAsValue + " Click [OK]");
		clickButton(driver, saveOKBtn, "Save");
		invisibilityOfLoader(driver);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingId(driver, "popupframe1");
		Thread.sleep(5000);
		ExtentReporter.logger.log(LogStatus.INFO, "Save as Official window displays");
		clickButton(driver, exitOK, "Workflow exit OK");
		switchToParentWindowfromframe(driver);
		Thread.sleep(2000);
	}

}

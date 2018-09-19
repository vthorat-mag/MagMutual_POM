package com.mm.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.management.relation.InvalidRelationServiceException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.dto.pdfReaderDTO;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.RateApolicyPage;
import com.relevantcodes.extentreports.LogStatus;

import bsh.Parser;
import okio.Timeout;

public class PDFReader extends CommonAction {
	CommonUtilities comUtil = new CommonUtilities();
	WebDriver driver;
	pdfReaderDTO pdfreaderdto;
	int i =0;
	String ExcelPath = System.getProperty("user.dir")+"\\src\\main\\resources\\Form_Data.xlsx";
	
	//AUTOIT script execution to save PDF.
	public PDFReader savePDF(String reportFolderPath) throws IOException, InterruptedException {
		//invisibilityOfLoader(driver);
		Thread.sleep(6000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click the Save button on the PDF to save the results & verify PDF is saved");
		String[] savePDFPath = 
				{System.getProperty("user.dir") + "\\src\\main\\resources\\StoredPDF\\pdfDocument.pdf"};
		String[] executionPath = {System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\savePdf.exe"};
		Runtime.getRuntime().exec(executionPath).waitFor(15, TimeUnit.SECONDS);
		/*String fileSavePath = reportFolderPath.concat("\\").concat(TestCaseDetails.testcaseId).concat(".pdf");
		ProcessBuilder pb =new ProcessBuilder(System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\savePdf.exe",fileSavePath);*/
		 
		if(isAlertPresent(driver)==false)
		{
			ExtentReporter.logger.log(LogStatus.INFO,
					"Alert[Optional] is NOT present.");
		}
		Thread.sleep(6000);
		//ProcessBuilder pb = new ProcessBuilder(executionPath);
		switchToParentWindowfromframe(driver);
		return new PDFReader(driver);
	}
	
	public PDFReader(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pdfreaderdto = new pdfReaderDTO(TestCaseDetails.testDataDictionary);
	}

	//Logic to verify PDF content.
	public PolicyBinderPage verifyPdfContent() throws Exception {
		Thread.sleep(15000);
		//getPageTitle(driver, "Policy Folder "+PolicyNo);
		boolean flag = false;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;
		int noOfPDFPages = 0;

		try {
			File file = new File("C:\\savePDF\\verifyPDF.pdf");
			pdDoc = PDDocument.load(file);
			noOfPDFPages = pdDoc.getNumberOfPages();
			pdfStripper = new PDFTextStripper();
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed " + e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		try {
			  for (i =0;i<pdfreaderdto.verifyPDFcontent.size();i++)
				{
				//Assert.assertTrue(parsedText.contains(pdfreaderdto.verifyPDFcontent.get(i)), "PDF dose not content '" + pdfreaderdto.verifyPDFcontent.get(i) + "'.");
				  ExtentReporter.logger.log(LogStatus.INFO, "Verify PDF display '" + pdfreaderdto.verifyPDFcontent.get(i) + "'.");
				if(parsedText.toLowerCase().contains(pdfreaderdto.verifyPDFcontent.get(i).toLowerCase()))
				{
					ExtentReporter.logger.log(LogStatus.INFO, "PDF contains '" + pdfreaderdto.verifyPDFcontent.get(i) + "'.");
				}else {
					ExtentReporter.logger.log(LogStatus.FAIL, pdfreaderdto.verifyPDFcontent.get(i)+" value  is not present in PDF.");
				}
			}
		}catch (Exception e){
				ExtentReporter.logger.log(LogStatus.FAIL, pdfreaderdto.verifyPDFcontent.get(i)+" value  is not present in PDF.");
				//Assert.assertTrue(false);
		}
		return new PolicyBinderPage(driver);
	}

}

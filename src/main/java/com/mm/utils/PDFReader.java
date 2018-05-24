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

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import bsh.Parser;

public class PDFReader {
	commonUtilities comUtil = new commonUtilities();
	WebDriver driver;
	
	public void readValue () throws IOException, AWTException, InterruptedException
	{
		
		Robot robot = new Robot();
	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_SHIFT);
	    robot.keyPress(KeyEvent.VK_S);
	    robot.keyRelease(KeyEvent.VK_S);
	    robot.keyRelease(KeyEvent.VK_SHIFT);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    Thread.sleep(2000);
	    
	    String fileDate = comUtil.getSystemDate();
   	 
   	 	String fileNamePath = "C:\\MM_Testcase_Output\\"+fileDate+".xlsx";
   	 	StringSelection fileName = new StringSelection(fileNamePath);
		
	 	 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileName, null);

	 	 robot.keyPress(KeyEvent.VK_CONTROL); 									//press 'cntrl' key
		 robot.keyPress(KeyEvent.VK_V);											//press 'V' key
		
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyRelease(KeyEvent.VK_V);
		 robot.setAutoDelay(2000);
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 
		driver.get("www.google.com");
		URL url = new URL(driver.getCurrentUrl());
		
		BufferedInputStream fileToParse = new BufferedInputStream(url.openStream());
		PDFParser parser = new PDFParser((RandomAccessRead) fileToParse);
		//parser.parse();
		String output = new PDFTextStripper().getText(parser.getPDDocument());
		if(output.contains("Lieferantenkürzelabcd")){
			ExtentReporter.logger.log(LogStatus.PASS, "clicked on button");
		}else{
		    System.out.println("False");
		}
	}
}

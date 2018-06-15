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

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;

import bsh.Parser;

public class PDFReader {
	CommonUtilities comUtil = new CommonUtilities();

	//AUTOIT script execution to save PDF.
	public void savePDF() throws IOException {
		String[] savePDFPath = {
				System.getProperty("user.dir") + "\\src\\main\\resources\\StoredPDF\\pdfDocument.pdf" };
		String[] executionPath = { System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\savePdf.exe" };
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\savePdf.exe",
				savePDFPath);
		// ProcessBuilder pb = new ProcessBuilder(executionPath);
	}

	//Logic to verify PDF content.
	public boolean verifyPdfContent(String content) throws IOException, AWTException, InterruptedException {
		boolean flag = false;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;
		int noOfPDFPages = 0;

		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\StoredPDF\\pdfDocument.pdf");
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
		if (parsedText.contains(content)) {
			flag = true;
			ExtentReporter.logger.log(LogStatus.INFO, "Verify footer display '" + content + "'.");
		}
		ExtentReporter.logger.log(LogStatus.INFO, "Footer dose not content '" + content + "'.");
		return flag;
	}
}

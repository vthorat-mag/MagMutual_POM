package com.mm.utils;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.LogStatus;

public class CommonUtilities {

	// Select option from drop down.
	public void selectDropdown(WebElement Element, String value) {

		Select Sel = new Select(Element);

		Sel.selectByValue(value);

	}

	// Logic to get system date.
	public String getSystemDate() {

		DateFormat dateFormatter = new SimpleDateFormat("MMddyy_hhmmss");
		Date today = Calendar.getInstance().getTime();
		String date = dateFormatter.format(today);

		return date;
	}

	// Logic to verify if downloaded file exist at the location.
	public void downloadedFileExists(String fileNamePath) {

		try {

			File file = new File(fileNamePath);

			if (file.exists()) {
				System.out.println("File is available at location");
				ExtentReporter.logger.log(LogStatus.PASS, "Excel file is available at download location.");
			}

		} catch (Exception e) {
			System.out.println("File is Not available at location");
			ExtentReporter.logger.log(LogStatus.FAIL, "Excel file is Not available at download location.");
			e.printStackTrace();
		}

	}

}

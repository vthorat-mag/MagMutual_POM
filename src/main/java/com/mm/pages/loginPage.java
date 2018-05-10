package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.ExcelUtil;
import com.mm.utils.commonAction;

public class loginPage extends commonAction{
	
	WebDriver driver;
	
	@FindBy(name="j_username")
	WebElement userName;
		
	@FindBy(name="j_password")
	WebElement password;
		
	@FindBy(name="btnSearch")
	WebElement loginBtn;
	
	public loginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void loginToeOasis() throws Exception
	{
		ExcelUtil exlutil = new ExcelUtil();
		driver.get("http://oasiscloud2017t:8081/oas17bts/CS/login.jsp");
		driver.manage().window().maximize();
		enterTextIn(userName,exlutil.getCellData("eOasis_Credentials", "UserName", 2));
		enterTextIn(password,exlutil.getCellData("eOasis_Credentials", "Password", 2));
		loginBtn.click();
	}
	
}

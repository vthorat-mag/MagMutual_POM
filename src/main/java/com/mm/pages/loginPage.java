package com.mm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
	
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

	public void loginToeOasis()
	{
		driver.get("http://oasiscloud2017t:8081/oas17bts/CS/login.jsp");
		driver.manage().window().maximize();
		userName.sendKeys("vthorat");
		password.sendKeys("M@G580746");
		loginBtn.click();
	}
	
}

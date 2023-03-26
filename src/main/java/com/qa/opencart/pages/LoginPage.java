package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1.private By locators
	
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	
	//2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.Page Actions :
	
	public String  getLoginPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTIONURL, TimeUtil.DEFAULT_TIME_OUT);
	}
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	public AccountPage doLogin(String un,String pwd) {
		
		System.out.println("credentials are :username :"+un+" password :"+pwd);
		
	    eleUtil.waitForElementVisible(emailID, TimeUtil.DEFAULT_TIME_OUT).sendKeys(un);
	    eleUtil.doSendKeys(password, pwd);
	    eleUtil.doClick(loginBtn);
	    
		return new AccountPage(driver);
		
	}
	public RegPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegPage(driver);
	}
	
	
	
}

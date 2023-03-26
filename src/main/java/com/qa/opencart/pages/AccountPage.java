package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logout = By.linkText("Logout");
	private By searchIcon = By.cssSelector("button.btn.btn-default.btn-lg");
	private By search = By.name("search");
	private By accHeaders = By.cssSelector("div#content h2");

	
	
	

	public AccountPage(WebDriver driver) {
		this.driver= driver;
		eleUtil=new ElementUtil(driver);
	}
	public String  getAccPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}
	public String  getAccPageUrl() {
		return eleUtil.waitForUrlContains(AppConstants.ACCOUNT_PAGE_FRACTIONURL, TimeUtil.DEFAULT_TIME_OUT);
	}
	public boolean isSearchIconExist() {
		return eleUtil.doIsDisplayed(searchIcon);
	}
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(search);
	}
	public boolean isLogOutExist() {
		return eleUtil.doIsDisplayed(logout);
	}
	public List<String> getaccHeaders() {
		
		List<WebElement> accHeaderList = eleUtil.waitForAllElementsVisible(accHeaders, TimeUtil.DEFAULT_TIME_OUT);
		List<String> HeaderList = new ArrayList<String>();
		
		for(WebElement e : accHeaderList) {
			String text = e.getText();
			HeaderList.add(text);
		
		}
		return HeaderList;
		
	}
	public ResultsPage performSearch(String productName) {
		
		System.out.println("Entering product name is .....  :"+productName);
	    if( isSearchExist()) {
		
	    	eleUtil.doSendKeys(search, productName);
	        eleUtil.doClick(searchIcon);
	        
	          return new ResultsPage(driver);
	    }
	    return null;
	
	}


}

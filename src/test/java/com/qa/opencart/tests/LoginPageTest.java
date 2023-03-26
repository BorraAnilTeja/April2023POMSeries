package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("act title is .....:"+actTitle);
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE,AppErrors.NO_TITLE_MATCHED);
		
	}	
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		System.out.println("actual url is .....:"+actUrl);
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTIONURL),AppErrors.NO_FRACTIONURL_MATCHED);
	}
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	@Test
	public void loginTest() {
		accPage= loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogOutExist(),AppErrors.LOGIN_UNSUCCESSFUL);
		
	}

}

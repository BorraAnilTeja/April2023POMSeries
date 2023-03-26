package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}	
      @Test
      public void accPageTitleTest() {
    	  String actTitle = accPage.getAccPageTitle();
    	  System.out.println("act title is .....:"+actTitle);
    	  Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE,AppErrors.NO_TITLE_MATCHED);
      }
      @Test
      public void accPageUrlTest() {
    	  String actUrl = accPage.getAccPageUrl();
    	  System.out.println("act url is .....:"+actUrl);
    	  Assert.assertTrue(actUrl.contains(AppConstants.ACCOUNT_PAGE_FRACTIONURL),AppErrors.NO_FRACTIONURL_MATCHED);
      }
      @Test
      public void accPageSearchIconDisplayedTest() {
    	 Assert.assertTrue( accPage.isSearchIconExist());
      }
      @Test
      public void accPageSearchBarDisplayedTest() {
     	 Assert.assertTrue( accPage.isSearchExist());
       }
      @Test
      public void accPageLogOutExistTest() {
      	 Assert.assertTrue( accPage.isLogOutExist());
        }
      @Test
      public void accPageHeadersTest() {
    	 List<String> actHeaders=accPage.getaccHeaders();
    	 Assert.assertEquals(actHeaders,AppConstants.EXPECTED_ACC_HEADERS_LIST);
      }
      @DataProvider
      public Object[][] getProductName() {
    	  return new Object[][] {
    		  
    		  {"macbook"},
    		  {"samsung"},
    		  {"iMac"}
    		  
    	  };
      }
      @Test(dataProvider="getProductName")
      public void productSearchTest(String productName) {
    	  
    	 resultsPage = accPage.performSearch(productName);
    	 String actTitle =resultsPage.getResultsPageTitle(productName);
    	 System.out.println("searchPage title is ..:"+actTitle);
    	 
    	 softAssert.assertEquals(actTitle, AppConstants.SEARCH_PAGE_TITLE+" "+productName);
    	 Assert.assertTrue(resultsPage.getSearchProductCount()>0);
    	  
    	  
    	  
      }
	}



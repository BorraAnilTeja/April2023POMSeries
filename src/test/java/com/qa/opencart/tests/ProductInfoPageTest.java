package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage =loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}	
	@DataProvider
	public Object[][] getProductHeaderTestData() {
		return new  Object[][] {
			
			{"MacBook","MacBook Pro"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Apple","Apple Cinema 30\""},
		};
		
	}
		@Test(dataProvider="getProductHeaderTestData")
		 public void productHeaderTest(String searchKey,String mainProductName) {
			resultsPage =accPage.performSearch(searchKey);
			productInfoPage =resultsPage.selectProduct(mainProductName);
			String actHeader =productInfoPage.getProductHeader();
			Assert.assertEquals(actHeader, mainProductName);
			
	}@DataProvider
		 public Object[][] getProductImgCountTestData() {
		return new  Object[][] {
			
			{"MacBook","MacBook Pro",4},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Apple","Apple Cinema 30\"",6},
		};
		
	}
		 
		@Test(dataProvider="getProductImgCountTestData")
		public void productImgCountTest(String searchKey,String mainProductName,int expImageCount ) {
			resultsPage =accPage.performSearch(searchKey);
			productInfoPage =resultsPage.selectProduct(mainProductName);
			int actimgCount = productInfoPage.getProductImgCount();
			Assert.assertEquals(actimgCount, expImageCount);
			
		}
		@Test
		public void productMetaDataTest() {
			resultsPage =accPage.performSearch("Macbook");
			productInfoPage =resultsPage.selectProduct("MacBook Pro");
			Map<String,String> actProdInfo =productInfoPage.getProductInformation();
			
			softAssert.assertEquals(actProdInfo.get("Brand"),"Apple");
			softAssert.assertEquals(actProdInfo.get("Product Code"),"Product 18");
			softAssert.assertEquals(actProdInfo.get("Reward Points"),"800");
			softAssert.assertEquals(actProdInfo.get("Availability"),"In Stock");
            softAssert.assertAll();
		}

}

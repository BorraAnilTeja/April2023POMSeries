package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImg = By.cssSelector("a.thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]//li");
	
	private Map<String,String> productMap ;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
		
	}
	public String getProductHeader() {
		return eleUtil.dogetElementText(productHeader);
	}
	public int getProductImgCount() {
	int imgCount =  eleUtil.waitForAllElementsVisible(productImg,TimeUtil.DEFAULT_TIME_OUT).size();
	System.out.println("images count of product ......: "+imgCount);
	return imgCount;
	}
	public Map<String,String> getProductInformation() {
		productMap = new HashMap<String,String>();
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
		
		
	}
	
	
	
	
	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: In Stock
	
	private void getProductMetaData() {
		
		List<WebElement> prodMetaDataList =eleUtil.getElements(productMetaData);
		System.out.println("meta data list size .....:"+prodMetaDataList.size());
	
		for(WebElement e : prodMetaDataList) {
			String text = e.getText();
			String meta[]=text.split(":");
			String metaKey =meta[0].trim();
			String metaVal = meta[1].trim();
			productMap.put(metaKey, metaVal);
		}
		
		
	}
	//$2,000.00
	//Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> prodpriceList =eleUtil.getElements(productPriceData);
		String price =prodpriceList.get(0).getText().trim();
		String exTaxPrice = prodpriceList.get(1).getText().trim();
		productMap.put("price",price);
		productMap.put("actTaxprice",exTaxPrice);

	}
	
	

}

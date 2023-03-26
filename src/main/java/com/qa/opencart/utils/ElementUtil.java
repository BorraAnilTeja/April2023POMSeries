package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil=new JavaScriptUtil(driver);
	}
	
	
	public WebElement getElement(By locator) {
		
		 WebElement ele =driver.findElement(locator);
		 if(Boolean.parseBoolean(DriverFactory.highlight)) {
			 jsUtil.flash(ele);
		 }
		 return ele;
	}
	public void doSendKeys(By locator,String value) {
		
		WebElement ele =getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	public  void doClick(By locator) {
		getElement(locator).click();
	}
	public String dogetElementText(By locator) {
		return getElement(locator).getText();
	}
	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	public String doGetAttributeValue(By locator,String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	public  void doClickOnElement(By locator,String linkText ) {
	   
		List<WebElement>linkList =driver.findElements(locator);
			
				System.out.println("total links :"+linkList.size());
				
				for(WebElement e : linkList) {
					String text = e.getText();
					System.out.println(text);
					if(text.contains(linkText)) {
						e.click();
						break;
					}
				}
	}
     	public  List<WebElement> getElements(By locator) {
	    	return driver.findElements(locator);
	}
    	public  int  totalElementsCount(By locator) {
	     	return getElements(locator).size();
	}
	    public  List<String> getElementsTextList(By locator) {
	    	List<WebElement>footerList =getElements(locator);
		    List<String> eleTextList = new ArrayList<String>();
		
		          for(WebElement e :footerList) {
			           String text = e.getText();
			           eleTextList.add(text);
		}
		           return eleTextList;
	}

				
				public  boolean doIsDisplayed(By locator) {
					return getElement(locator).isDisplayed();
				}
				public  boolean  isSingleElementExist(By locator) {
					
					int actCount = getElements(locator).size();
					System.out.println("actual count of element :"+actCount);
					if(actCount==1) {
					return true;
					}
					return false;
					}
			    public  boolean  isTwoElementExist(By locator) {
					
					int actCount = getElements(locator).size();
					System.out.println("actual count of element :"+actCount);
					if(actCount==2) {
					return true;
					}
					return false;
					}
			public  boolean  isMultipleElementExist(By locator) {
					
					int actCount = getElements(locator).size();
					System.out.println("actual count of element :"+actCount);
					if(actCount>1) {
					return true;
					}
					return false;
					}
					
					
				public  boolean  isMultipleElementExist(By locator,int expectedCount) {
					
					int actCount = getElements(locator).size();
					System.out.println("actual count of element :"+actCount);
					if(actCount==expectedCount) {
					return true;
					}
					return false;
					}
					
				
				
	
	//******************************Drop down Utils ---Select class **************************************************//
	
	public void doSelectDropDownByIndex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	public void doSelectDropDownByValue(By locator,String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	public void doSelectDropDownByVisibleText(By locator,String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	public void doSelectDropDownValue(By locator,String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList =select.getOptions();
		
		for(WebElement e : optionsList) {
			String text =e.getText();
			if(text.contains(value)) {
				e.click();
				break;
				
			}
		}
	}
	//**********************************************Actions Class-Utils*********************************************************//

	
	public  void selectSubMenu(String htmlTag,String parentMenu,String childMenu) throws InterruptedException {
		
		By parentMenuLocator = By.xpath("//"+htmlTag+"[text()='"+parentMenu+"']");
		By childMenuLocator = By.xpath("//"+htmlTag+"[text()='"+childMenu+"']");
		
        WebElement parentMenuElement =getElement(parentMenuLocator);
        
		
		Actions act = new Actions(driver);
		act.moveToElement(parentMenuElement).build().perform();
		
		Thread.sleep(2000);
		
		doClick(childMenuLocator);
		
		
		
	}
	public  void selectLevel4MenuItems(By parentMenuLocator,String htmlTag,String level1Menu,String level2Menu,String level3Menu) throws InterruptedException {

		Actions act = new Actions(driver);
		
		WebElement parentMenu =driver.findElement(parentMenuLocator);
		act.moveToElement(parentMenu).build().perform();
		Thread.sleep(2000);
		

		WebElement level1 =driver.findElement(By.xpath("(//"+htmlTag+"[text()='"+level1Menu+"'])[2]"));
		act.moveToElement(level1 ).build().perform();
		Thread.sleep(2000);
		

		WebElement level2 =driver.findElement(By.xpath("(//"+htmlTag+"[text()='"+level2Menu+"'])[2]"));
		act.moveToElement(level2).build().perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//"+htmlTag+"[text()='"+level3Menu+"'])[2]")).click();
		

	}
public  void doActionsDragAndDrop(By sourceLocator,By targetLocator) {
		
		WebElement sourceEle =getElement(sourceLocator);
		WebElement targetEle =getElement(targetLocator);
		
		Actions act = new Actions(driver);
		//1
	//	act.clickAndHold(sourceEle).moveToElement(targetEle).release().build().perform();
		//2
		act.dragAndDrop(sourceEle, targetEle).build().perform();
	



}
//***************************************************Wait Utils****************************************************************//

public  Alert waitForAlertPresentAndSwitch(int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.alertIsPresent());
	
}
public  String getAlertText(int timeout) {
	return waitForAlertPresentAndSwitch(timeout).getText();
}
public  void acceptAlert(int timeout) {
	waitForAlertPresentAndSwitch(timeout).accept();
}
public  void dismissAlert(int timeout) {
	waitForAlertPresentAndSwitch(timeout).dismiss();
}
public  void alertSendKeys(int timeout,String value) {
	waitForAlertPresentAndSwitch(timeout).sendKeys(value);

}
/**
 * An expectation for checking whether the given frame is available to switch to. 

*If the frame is available it switches the given driver to the specified frame.

 * @param locator
 * @param timeout
 */
public  void waitForFramePresentAndSwitch(By locator,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
}
/**
 * An expectation for checking whether the given frame is available to switch to. 

*If the frame is available it switches the given driver to the specified frameIndex.

 * @param frameIndex
 * @param timeout
 */
public  void waitForFramePresentAndSwitch(int frameIndex,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
}
/**
 * An expectation for checking whether the given frame is available to switch to. 

*If the frame is available it switches the given driver to the specified web element.

 * @param frameElement
 * @param timeout
 */
public  void waitForFramePresentAndSwitch(WebElement frameElement,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
}
/**
 * An expectation for checking whether the given frame is available to switch to. 

*If the frame is available it switches the given driver to the specified frame.

 * @param nameorID
 * @param timeout
 */
public  void waitForFramePresentAndSwitch(String  nameorID,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameorID));
}


/**
 * An expectation for checking that an element is present on the DOM of a page. 
 * This does not necessarily mean that the element is visible.
 * @param locator
 * @param timeout
 * @return
 */
public  WebElement waitForElementPresence(By locator,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}
/**
 * An expectation for checking that an element is present on the DOM of a page and visible.
 * Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
 * @param locator
 * @param timeout
 * @return
 */
public   WebElement   waitForElementVisible(By locator,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}
/**
 * An expectation for checking that all elements present on the web page that match the locatorare visible. 
 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
 * @param locator
 * @param timeout
 * @return
 */
public  List<WebElement> waitForAllElementsVisible(By locator ,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
}
/**
 * An expectation for checking that there is at least one element present on a web page.
 * @param locator
 * @param timeout
 * @return
 */
public  List<WebElement> waitForAllElementsPresence(By locator ,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
}



/**
 * An expectation for checking that the title contains a case-sensitive substring
 * @param titleFractionValue
 * @param timeout
 * @return
 */

public  String waitForTitleContains(String titleFractionValue,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	if(wait.until(ExpectedConditions.titleContains(titleFractionValue))) {
	return driver.getTitle();
}
	else {
		System.out.println("expected title is not visible");
		return null;
	}
}
/**
 * An expectation for checking the title of a page
 * @param titleVal
 * @param timeout
 * @return
 */
public  String waitForTitleIs(String titleVal,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	if(wait.until(ExpectedConditions.titleIs(titleVal))) {
	return driver.getTitle();
}
	else {
		System.out.println("expected title is not visible");
		return null;
	}
}
/**
 * An expectation for checking that the title contains a case-sensitive substring
 * @param urlFractionValue
 * @param timeout
 * @return
 */

public  String waitForUrlContains(String urlFractionValue,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	if(wait.until(ExpectedConditions.urlContains(urlFractionValue))) {
	return driver.getCurrentUrl();
}
	else {
		System.out.println("expected url  is not visible");
		return null;
	}
}
/**
 * An expectation for checking the title of a page
 * @param urlVal
 * @param timeout
 * @return
 */
public  String waitForUrlToBe(String urlVal,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	if(wait.until(ExpectedConditions.urlToBe(urlVal))) {
		return driver.getCurrentUrl();
}
	else {
		System.out.println("expected url  is not visible");
		return null;
	}
}
public  void waitForElementToBeClickable(By locator,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
}
public  void waitForElementToBeClickable(By locator,int timeout,long timeInterval) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout),Duration.ofSeconds(timeInterval));
	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
}
public  void waitForElementToBeClickable(WebElement ele,int timeout) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	 wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
}

}


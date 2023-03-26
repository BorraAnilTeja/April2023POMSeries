package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {
	
	//public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight ;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName =prop.getProperty("browser").trim();
		optionsManager = new OptionsManager(prop);
	
		
		System.out.println("Browser name is :"+browserName);
		
		 highlight=prop.getProperty("highlight");
		
		
		switch (browserName.toLowerCase()){
		case "chrome":
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			
			//driver = new ChromeDriver(co);
			tlDriver.set(new ChromeDriver(co));
			
			break;
        case "firefox":
			
		//	driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
        	tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
         case "edge":
			
			//driver = new EdgeDriver();
        	 tlDriver.set(new EdgeDriver());
			break;

		default:
			System.out.println("please pass the right browser name :"+prop);
			break;
			
			
			
		}
		 getDriver().manage().deleteAllCookies();
		 getDriver().manage().window().maximize();
		 getDriver().get(prop.getProperty("url"));
		return  getDriver();
		
	}
	
	//get the local thread copy of the driver
		public synchronized static WebDriver getDriver() {
			return tlDriver.get();
		}
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
		
	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

	
	

}
package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	public ChromeOptions  getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("......Running the test in Headless Mode.......");
			//addArguments("--headless=new")
			co.addArguments("--headless=new");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("......Running the test in Incognito Mode.......");

			co.addArguments("--incognito");
		}
		return co;
		
	}
	public FirefoxOptions  getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("......Running the test in Headless Mode.......");

			//addArguments("-headless")`.
			fo.addArguments("-headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("......Running the test in Incognito Mode.......");

			fo.addArguments("--incognito");
		}
		return fo;
		
	}

}

package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {
	
	By cart = By.id("macbook");
	
	public CartPage() {
		
	}
	public void addtoCart() {
		System.out.println("macbook added to cart");
	}

}

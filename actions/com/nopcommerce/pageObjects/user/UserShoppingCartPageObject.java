package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserShoppingCartPageObject extends BasePage {
	WebDriver driver;

	public UserShoppingCartPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

}

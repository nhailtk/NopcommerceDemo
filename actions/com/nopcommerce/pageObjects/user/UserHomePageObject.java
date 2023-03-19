package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserHomePageUI;
import com.nopcommerce.pageUIs.user.UserRegisterPageUI;

import commons.BasePage;

public class UserHomePageObject extends BasePage {
	WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean verifyDisplayMyAccountMenu() {
		waitForElementVisible(driver, UserHomePageUI.HEADER_DYNAMIC_LINK,"ico-account");
		return elementIsDisplayed(driver, UserHomePageUI.HEADER_DYNAMIC_LINK,"ico-account");
	}





}

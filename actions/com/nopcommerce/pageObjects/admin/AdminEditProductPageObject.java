package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.admin.AdminEditProductPageUI;
import com.nopcommerce.pageUIs.user.BasePageUI;

import commons.BasePage;

public class AdminEditProductPageObject extends BasePage {
	WebDriver driver;

	public AdminEditProductPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public String getValueOfProductName() {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, "Name");
		return getElementAttribute(driver, BasePageUI.DYNAMIC_TEXTBOX, "value", "Name");
	}

	public boolean isDisplayedTitle() {
		waitForElementVisible(driver, AdminEditProductPageUI.TITLE_PAGE);
		return elementIsDisplayed(driver, AdminEditProductPageUI.TITLE_PAGE);
	}

}

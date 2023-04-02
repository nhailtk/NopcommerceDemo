package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.BasePageUI;
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
		waitForElementVisible(driver, BasePageUI.HEADER_DYNAMIC_LINK,"ico-account");
		return elementIsDisplayed(driver, BasePageUI.HEADER_DYNAMIC_LINK,"ico-account");
	}

	public UserProductListPageObject openProductListPage() {
		waitForElementVisible(driver, BasePageUI.HEADER_DYNAMIC_MENU, "Computers");
		hoverMouseToElement(driver, BasePageUI.HEADER_DYNAMIC_MENU, "Computers");
		
		return (UserProductListPageObject)openDynamicHeaderMenu(driver,"Desktops");
	}





}

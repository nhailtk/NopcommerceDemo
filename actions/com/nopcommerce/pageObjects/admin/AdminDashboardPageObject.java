package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.admin.AdminDashboardPageUI;

import commons.BasePage;

public class AdminDashboardPageObject extends BasePage{
	WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean verifyDisplayedTitle() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_TITLE);
		return elementIsDisplayed(driver, AdminDashboardPageUI.DASHBOARD_TITLE);
		
	}
	

}

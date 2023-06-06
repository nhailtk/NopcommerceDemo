package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.admin.AdminLoginPageUI;

import commons.BasePage;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputToUsernameTextbox(String username) {
		// TODO Auto-generated method stub
		
	}

	public AdminDashboardPageObject clickToLoginButton() {
		waitForElementVisible(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return AdminPageGeneratorManager.getAdminDashboardPage(driver);
	}
	

}

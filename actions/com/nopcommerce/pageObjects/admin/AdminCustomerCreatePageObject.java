package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.admin.AdminCustomerCreatePageUI;
import com.nopcommerce.pageUIs.admin.AdminCustomerListPageUI;

import commons.BasePage;

public class AdminCustomerCreatePageObject extends BasePage {
	WebDriver driver;

	public AdminCustomerCreatePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean verifyDisplayedTitle() {
		waitForElementVisible(driver, AdminCustomerCreatePageUI.CUSTOMER_CREATE_TITLE);
		return elementIsDisplayed(driver, AdminCustomerCreatePageUI.CUSTOMER_CREATE_TITLE);
	}

	public AdminCustomerEditPageObject clickToSaveContinueButton() {
		waitForElementClickable(driver, AdminCustomerCreatePageUI.SAVE_CONTINUE_BUTTON);
		clickToElement(driver, AdminCustomerCreatePageUI.SAVE_CONTINUE_BUTTON);
		return AdminPageGeneratorManager.getAdminCustomerEditPage(driver);

	}

}

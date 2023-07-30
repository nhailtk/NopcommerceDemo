package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.admin.AdminAddressCreatePageUI;
import com.nopcommerce.pageUIs.admin.AdminCustomerCreatePageUI;
import com.nopcommerce.pageUIs.admin.AdminCustomerListPageUI;

import commons.BasePage;

public class AdminAddressCreatePageObject extends BasePage {
	WebDriver driver;

	public AdminAddressCreatePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToButtonSave() {
		waitForElementClickable(driver, AdminAddressCreatePageUI.SAVE_BUTTON);
		clickToElement(driver, AdminAddressCreatePageUI.SAVE_BUTTON);

	}

	public AdminCustomerEditPageObject clickToBackToCustomerDetailsLink() {
		waitForElementClickable(driver, AdminAddressCreatePageUI.BACK_TO_CUSTOMER_DETAILS_LINK);
		clickToElement(driver, AdminAddressCreatePageUI.BACK_TO_CUSTOMER_DETAILS_LINK);
		return AdminPageGeneratorManager.getAdminCustomerEditPage(driver);
	}

}

package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.admin.AdminCustomerCreatePageUI;
import com.nopcommerce.pageUIs.admin.AdminCustomerListPageUI;
import com.nopcommerce.pageUIs.admin.AdminProductListPageUI;

import commons.BasePage;

public class AdminCustomerListPageObject extends BasePage {
	WebDriver driver;

	public AdminCustomerListPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean verifyDisplayedTitle() {
		waitForElementVisible(driver, AdminCustomerListPageUI.CUSTOMER_LIST_TITLE);
		return elementIsDisplayed(driver, AdminCustomerListPageUI.CUSTOMER_LIST_TITLE);
	}

	public AdminCustomerCreatePageObject clickToAddNewButton() {
		waitForElementClickable(driver, AdminCustomerListPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminCustomerListPageUI.ADD_NEW_BUTTON);
		return AdminPageGeneratorManager.getAdminCustomerCreatePage(driver);

	}

	public void clickToDeleteIconInRoleField() {
		waitForElementClickable(driver, AdminCustomerListPageUI.DELETE_ICON_IN_ROLE);
		clickToElementByJS(driver, AdminCustomerListPageUI.DELETE_ICON_IN_ROLE);

	}

	public void selectCustomerRoleDropdown(String value) {
		selectItemInCustomerDropdown(driver, AdminCustomerListPageUI.ROLE_DROPDOWN_PARENT, AdminCustomerListPageUI.ROLE_DROPDOWN_CHILD, value);

	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminCustomerListPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminCustomerListPageUI.SEARCH_BUTTON);
	}

	public boolean isDisplayedTrueIcon() {
		waitForElementVisible(driver, AdminCustomerListPageUI.TRUE_ICON);
		return elementIsDisplayed(driver, AdminCustomerListPageUI.TRUE_ICON);
	}

	public AdminCustomerEditPageObject clickToEditButton() {
		waitForElementClickable(driver, AdminCustomerListPageUI.EDIT_BUTTON);
		scrollToElement(driver, AdminCustomerListPageUI.EDIT_BUTTON);
		clickToElement(driver, AdminCustomerListPageUI.EDIT_BUTTON);
		return AdminPageGeneratorManager.getAdminCustomerEditPage(driver);
	}

}

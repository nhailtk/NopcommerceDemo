package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.admin.AdminProductListPageUI;
import com.nopcommerce.pageUIs.user.BasePageUI;

import commons.BasePage;

public class AdminProductListPageObject extends BasePage {
	WebDriver driver;

	public AdminProductListPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean verifyDisplayedTitle() {
		waitForElementVisible(driver, AdminProductListPageUI.PRODUCTS_LIST_TITLE);
		return elementIsDisplayed(driver, AdminProductListPageUI.PRODUCTS_LIST_TITLE);

	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminProductListPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminProductListPageUI.SEARCH_BUTTON);

	}

	public boolean isDisplayedTrueIcon() {
		waitForElementVisible(driver, AdminProductListPageUI.TRUE_ICON);
		return elementIsDisplayed(driver, AdminProductListPageUI.TRUE_ICON);
	}

	public void uncheckedSearchSubCategory() {
		waitForElementClickable(driver, AdminProductListPageUI.SEARCH_CHECKBOX);
		unchecktoDefaultCheckboxRadio(driver, AdminProductListPageUI.SEARCH_CHECKBOX);
	}

	public void checkedSearchSubCategory() {
		waitForElementClickable(driver, AdminProductListPageUI.SEARCH_CHECKBOX);
		checktoDefaultCheckboxRadio(driver, AdminProductListPageUI.SEARCH_CHECKBOX);
	}

	public boolean isDisplayedNoData() {
		waitForElementVisible(driver, AdminProductListPageUI.NO_DATA_IN_TABLE);
		return elementIsDisplayed(driver, AdminProductListPageUI.NO_DATA_IN_TABLE);
	}

	public AdminEditProductPageObject clickToGoButton() {
		waitForElementClickable(driver, AdminProductListPageUI.GO_BUTTON);
		clickToElement(driver, AdminProductListPageUI.GO_BUTTON);
		return AdminPageGeneratorManager.getAdminEditProductPage(driver);
	}

}

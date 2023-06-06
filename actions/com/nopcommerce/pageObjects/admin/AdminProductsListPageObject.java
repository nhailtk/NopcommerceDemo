package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.admin.AdminProductsListPageUI;
import com.nopcommerce.pageUIs.user.BasePageUI;

import commons.BasePage;

public class AdminProductsListPageObject extends BasePage{
	WebDriver driver;

	public AdminProductsListPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean verifyDisplayedTitle() {
		waitForElementVisible(driver, AdminProductsListPageUI.PRODUCTS_LIST_TITLE);
		return elementIsDisplayed(driver, AdminProductsListPageUI.PRODUCTS_LIST_TITLE);
		
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminProductsListPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminProductsListPageUI.SEARCH_BUTTON);
		
	}

	public String getValueInCellByHeaderName(String headerName) {
		int index = getElementSize(driver, AdminProductsListPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerName) + 1;
		waitForElementVisible(driver, AdminProductsListPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(index));
		return getElementText(driver, AdminProductsListPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(index));
	}

	public boolean isDisplayedTrueIcon() {
		waitForElementVisible(driver, AdminProductsListPageUI.TRUE_ICON);
		return elementIsDisplayed(driver, AdminProductsListPageUI.TRUE_ICON);
	}

	public void uncheckedSearchSubCategory() {
		waitForElementClickable(driver, AdminProductsListPageUI.SEARCH_CHECKBOX);
		unchecktoDefaultCheckboxRadio(driver, AdminProductsListPageUI.SEARCH_CHECKBOX);
	}
	
	public void checkedSearchSubCategory() {
		waitForElementClickable(driver, AdminProductsListPageUI.SEARCH_CHECKBOX);
		checktoDefaultCheckboxRadio(driver, AdminProductsListPageUI.SEARCH_CHECKBOX);
	}

	public boolean isDisplayedNoData() {
		waitForElementVisible(driver, AdminProductsListPageUI.NO_DATA_IN_TABLE);
		return elementIsDisplayed(driver, AdminProductsListPageUI.NO_DATA_IN_TABLE);
	}

	public AdminEditProductPageObject clickToGoButton() {
		waitForElementClickable(driver, AdminProductsListPageUI.GO_BUTTON);
		clickToElement(driver, AdminProductsListPageUI.GO_BUTTON);
		return AdminPageGeneratorManager.getAdminEditProductPage(driver);
	}

	public void doubleClickToCatalogMenu(String rightIcon, String text) {
		waitForElementClickable(driver, BasePageUI.ADMIN_RIGHT_MENU, rightIcon, text);
		doubleClickToElement(driver, BasePageUI.ADMIN_RIGHT_MENU, rightIcon, text);
		
	}
	

}

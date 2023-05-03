package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserCompareProductsListPageUI;

import commons.BasePage;

public class UserCompareProuductsListPageObject extends BasePage {
	WebDriver driver;
	String nameProduct1 = "Asus N551JK-XO076H Laptop", nameProduct2 = "Apple MacBook Pro 13-inch";
	String priceProduct1 = "$1,500.00", priceProduct2 = "$1,800.00";

	public UserCompareProuductsListPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean verifyInformationDisplayed() {
		if (verifyDisplayDynamicTitlePage(driver, "Compare products") == true && verifyDisplayButtonRemove() == 2 && verifyDisplayedNameProduct() == true && verifyDisplayedPriceProduct() == true) {
			return true;
		} else {
			return false;
		}
	}

	private int verifyDisplayButtonRemove() {
		waitForElementVisible(driver, UserCompareProductsListPageUI.REMOVE_BUTTON);
		return getElementSize(driver, UserCompareProductsListPageUI.REMOVE_BUTTON);

	}

	private boolean verifyDisplayedNameProduct() {
		waitForAllElementVisible(driver, UserCompareProductsListPageUI.NAME_PRODUCTS_LIST);
		if (getListWebElement(driver, UserCompareProductsListPageUI.NAME_PRODUCTS_LIST).get(0).getText().equals(nameProduct1) == true
				&& getListWebElement(driver, UserCompareProductsListPageUI.NAME_PRODUCTS_LIST).get(1).getText().equals(nameProduct2) == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean verifyDisplayedPriceProduct() {
		waitForAllElementVisible(driver, UserCompareProductsListPageUI.PRICE_PRODUCTS_LIST);
		if (getListWebElement(driver, UserCompareProductsListPageUI.PRICE_PRODUCTS_LIST).get(1).getText().equals(priceProduct1) == true
				&& getListWebElement(driver, UserCompareProductsListPageUI.PRICE_PRODUCTS_LIST).get(2).getText().equals(priceProduct2) == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyNoData() {
		waitForElementVisible(driver, UserCompareProductsListPageUI.NO_DATA);
		if (elementIsDisplayed(driver, UserCompareProductsListPageUI.NO_DATA) && elementIsUndisplayed(driver, UserCompareProductsListPageUI.NAME_PRODUCTS_LIST) == true) {
			return true;
		} else {
			return false;
		}

	}

	public void clickToClearListButton() {
		waitForElementClickable(driver, UserCompareProductsListPageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, UserCompareProductsListPageUI.CLEAR_LIST_BUTTON);

	}

}

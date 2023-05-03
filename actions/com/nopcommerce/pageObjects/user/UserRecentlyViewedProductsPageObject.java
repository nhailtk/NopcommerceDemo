package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserCompareProductsListPageUI;
import com.nopcommerce.pageUIs.user.UserRecentlyViewedProductsPageUI;

import commons.BasePage;

public class UserRecentlyViewedProductsPageObject extends BasePage {
	WebDriver driver;
	String nameProduct1 = "Lenovo Thinkpad X1 Carbon Laptop", nameProduct2 = "HP Spectre XT Pro UltraBook", nameProduct3 = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
	public UserRecentlyViewedProductsPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	

	public boolean verifyInformationDisplayed() {
		waitForAllElementVisible(driver, UserRecentlyViewedProductsPageUI.PRODUCT_TITLE);
		if (getListWebElement(driver, UserRecentlyViewedProductsPageUI.PRODUCT_TITLE).get(0).getText().equals(nameProduct1) == true
				&& getListWebElement(driver, UserRecentlyViewedProductsPageUI.PRODUCT_TITLE).get(1).getText().equals(nameProduct2) == true
				&& getListWebElement(driver, UserRecentlyViewedProductsPageUI.PRODUCT_TITLE).get(2).getText().equals(nameProduct3) == true) {
			return true;
		} else {
			return false;
		}
	}

}

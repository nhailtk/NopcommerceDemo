package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserProductListPageUI;

import commons.BasePage;

public class UserProductListPageObject extends BasePage{
	WebDriver driver;

	public UserProductListPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public UserProductDetailPageObject clickToProductTitle() {
		clickToElement(driver, UserProductListPageUI.PRODUCT_TITLE);
		return PageGeneratorManager.getUserProductDetailPage(driver);
	}

	public String getProductTitle() {
		waitForElementVisible(driver, UserProductListPageUI.PRODUCT_TITLE);
		return getElementText(driver, UserProductListPageUI.PRODUCT_TITLE);
	}
}

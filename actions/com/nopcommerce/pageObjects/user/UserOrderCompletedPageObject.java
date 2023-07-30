package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserOrderCompletedPageUI;

import commons.BasePage;

public class UserOrderCompletedPageObject extends BasePage {
	WebDriver driver;

	public UserOrderCompletedPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean isDisplayedOrderNumber() {
		waitForElementVisible(driver, UserOrderCompletedPageUI.ORDER_NUMBER_OF_ORDER_COMPLETED);
		return elementIsDisplayed(driver, UserOrderCompletedPageUI.ORDER_NUMBER_OF_ORDER_COMPLETED);
	}

	public String getPageTitleOfOrderCompleted() {
		waitForElementVisible(driver, UserOrderCompletedPageUI.PAGE_TILE_OF_ORDER_COMPLETED);
		return getElementText(driver, UserOrderCompletedPageUI.PAGE_TILE_OF_ORDER_COMPLETED);
	}

	public String getTitleOfOrderCompleted() {
		waitForElementVisible(driver, UserOrderCompletedPageUI.TILE_OF_ORDER_COMPLETED);
		return getElementText(driver, UserOrderCompletedPageUI.TILE_OF_ORDER_COMPLETED);
	}

	public String getOrderNumber() {
		waitForElementVisible(driver, UserOrderCompletedPageUI.ORDER_NUMBER_OF_ORDER_COMPLETED);
		return getElementText(driver, UserOrderCompletedPageUI.ORDER_NUMBER_OF_ORDER_COMPLETED);
	}

}

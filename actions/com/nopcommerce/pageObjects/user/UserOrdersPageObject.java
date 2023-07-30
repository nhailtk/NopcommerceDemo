package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserCheckoutPageUI;
import com.nopcommerce.pageUIs.user.UserOrdersPageUI;

import commons.BasePage;

public class UserOrdersPageObject extends BasePage {
	WebDriver driver;

	public UserOrdersPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean verifyDisplayedOrderNumber() {
		waitForElementVisible(driver, UserOrdersPageUI.ORDER_NUMBER);
		return elementIsDisplayed(driver, UserOrdersPageUI.ORDER_NUMBER);
	}

	public String getOrderNumber() {
		waitForElementVisible(driver, UserOrdersPageUI.ORDER_NUMBER);
		return getElementText(driver, UserOrdersPageUI.ORDER_NUMBER);
	}

	public void clickToDetailsButton(String orderNumber) {
		waitForElementClickable(driver, UserOrdersPageUI.DETAIL_BUTTON, orderNumber);
		clickToElement(driver, UserOrdersPageUI.DETAIL_BUTTON, orderNumber);

	}

	public String getOrderNumberDetails() {
		waitForElementVisible(driver, UserOrdersPageUI.ORDER_NUMBER_DETAIL);
		return getElementText(driver, UserOrdersPageUI.ORDER_NUMBER_DETAIL);
	}

	public String verifyDisplayedOrderInfo(String string) {
		waitForElementVisible(driver, UserOrdersPageUI.ORDER_INFO, string);
		return getElementText(driver, UserOrdersPageUI.ORDER_INFO, string);
	}

	public String getDynamicTitleOfConfirmOrder(String string) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_TITLE_OF_CONFIRM_ORDER, string);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_TITLE_OF_CONFIRM_ORDER, string);
	}

	public String getDynamicInfoOfConfirmOrder(String string, String string2) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_CONFIRM_ORDER, string, string2);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_CONFIRM_ORDER, string, string2);
	}

	public String getDynamicInfoOfProduct(String string) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_PRODUCT, string);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_PRODUCT, string);
	}

	public String getInfoGiftWrapping() {
		waitForElementVisible(driver, UserCheckoutPageUI.GIFT_WRAPPING);
		return getElementText(driver, UserCheckoutPageUI.GIFT_WRAPPING);
	}

	public String getDynamicInfoOfProductPrice(String string) {
		waitForElementVisible(driver, UserOrdersPageUI.DYNAMIC_INFO_PRODUCT_PRICE, string);
		return getElementText(driver, UserOrdersPageUI.DYNAMIC_INFO_PRODUCT_PRICE, string);
	}

	public String getProductName() {
		waitForElementVisible(driver, UserOrdersPageUI.PRODUCT_NAME);
		return getElementText(driver, UserOrdersPageUI.PRODUCT_NAME);
	}

	public UserShoppingCartPageObject clickToReOrderButton() {
		waitForElementClickable(driver, UserOrdersPageUI.REORDER_BUTTON);
		clickToElement(driver, UserOrdersPageUI.REORDER_BUTTON);
		return UserPageGeneratorManager.getUserShoppingCartPage(driver);
	}
}

package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.BasePageUI;
import com.nopcommerce.pageUIs.user.UserProductDetailPageUI;

import commons.BasePage;

public class UserProductDetailPageObject extends BasePage {
	WebDriver driver;

	public UserProductDetailPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public UserProductReviewsPageObject clickAddReviewLink() {
		waitForElementVisible(driver, UserProductDetailPageUI.ADD_REVIEW_LINK);
		clickToElement(driver, UserProductDetailPageUI.ADD_REVIEW_LINK);
		return UserPageGeneratorManager.getUserMyProductReviewsPage(driver);
	}

	public void clickToAddWishlistButton() {
		waitForElementVisible(driver, UserProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);
		clickToElement(driver, UserProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);

	}

	public void scrollToBottom(WebDriver driver2) {
		scrollToBottomPage(driver2);
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver2, String string) {
		waitForElementVisible(driver2, UserProductDetailPageUI.DYNAMIC_CHECKBOX_RADIO, string);
		checktoDefaultCheckboxRadio(driver2, UserProductDetailPageUI.DYNAMIC_CHECKBOX_RADIO, string);

	}

	public void clickToAddToCartButton() {
		waitForElementClickable(driver, UserProductDetailPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, UserProductDetailPageUI.ADD_TO_CART_BUTTON);

	}

	public void clickToUpdateButton() {
		waitForElementClickable(driver, UserProductDetailPageUI.UPDATE_TO_CART_BUTTON);
		clickToElement(driver, UserProductDetailPageUI.UPDATE_TO_CART_BUTTON);

	}

	public String getConfirmAddedProduct() {
		waitForElementVisible(driver, BasePageUI.CONFIRM_MASSAGE_OF_PRODUCTS);
		return getElementText(driver, BasePageUI.CONFIRM_MASSAGE_OF_PRODUCTS);
	}

	public String verifyProductInCart(String locator) {
		waitForElementVisible(driver, BasePageUI.HEADER_DYNAMIC_LINK, locator);
		hoverMouseToElement(driver, BasePageUI.HEADER_DYNAMIC_LINK, locator);
		return getElementText(driver, BasePageUI.HEADER_DYNAMIC_LINK, locator);
	}

	public String getInfoProduct(String locator) {
		waitForElementVisible(driver, UserProductDetailPageUI.DYNAMIC_INFO_PRODUCT, locator);
		return getElementText(driver, UserProductDetailPageUI.DYNAMIC_INFO_PRODUCT, locator);
	}

	public boolean isDisplayedNameProduct() {
		waitForElementVisible(driver, UserProductDetailPageUI.NAME_PRODUCT);
		return elementIsDisplayed(driver, UserProductDetailPageUI.NAME_PRODUCT);
	}

	public void uncheckToDefaultCheckboxRadio(WebDriver driver2, String string) {
		waitForElementVisible(driver2, UserProductDetailPageUI.DYNAMIC_CHECKBOX_RADIO, string);
		unchecktoDefaultCheckboxRadio(driver2, UserProductDetailPageUI.DYNAMIC_CHECKBOX_RADIO, string);

	}

	public String getPriceProduct() {
		waitForElementVisible(driver, UserProductDetailPageUI.PRICE_PRODUCT);
		return getElementText(driver, UserProductDetailPageUI.PRICE_PRODUCT);
	}

}

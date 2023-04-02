package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserProductReviewsPageUI;

import commons.BasePage;

public class UserProductReviewsPageObject extends BasePage {
	WebDriver driver;

	public UserProductReviewsPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickSubmitReviewButton() {
		waitForElementClickable(driver, UserProductReviewsPageUI.SUBMIT_REVIEW_BUTTON);
		clickToElement(driver, UserProductReviewsPageUI.SUBMIT_REVIEW_BUTTON);

	}

	public boolean verifyDisplayProductReviewTitle(String textAddProductReviewTitle, String productTitle) {
		waitForElementVisible(driver, UserProductReviewsPageUI.PRODUCT_REVIEW_TITLE, textAddProductReviewTitle + productTitle);
		return elementIsDisplayed(driver, UserProductReviewsPageUI.PRODUCT_REVIEW_TITLE, textAddProductReviewTitle + productTitle);
	}

	public boolean verifyDisplayProductReviewContent(String textAddProductReviewContent, String productTitle) {
		waitForElementVisible(driver, UserProductReviewsPageUI.PRODUCT_REVIEW_CONTENT, textAddProductReviewContent + productTitle);
		return elementIsDisplayed(driver, UserProductReviewsPageUI.PRODUCT_REVIEW_CONTENT, textAddProductReviewContent + productTitle);
	}

	public void inputValueToProductReviewContentTextArea(WebDriver driver,  String string) {
		waitForElementVisible(driver, UserProductReviewsPageUI.ADD_PRODUCT_REVIEW_CONTENT);
		sendkeyToElement(driver, UserProductReviewsPageUI.ADD_PRODUCT_REVIEW_CONTENT, string);
	}
}

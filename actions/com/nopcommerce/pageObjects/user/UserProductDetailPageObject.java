package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

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
		return PageGeneratorManager.getUserMyProductReviewsPage(driver);
	}

	public void clickToAddWishlistButton() {
		waitForElementVisible(driver, UserProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);
		clickToElement(driver, UserProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);
		
	}
	
	public void scrollToBottom(WebDriver driver2) {
		scrollToBottomPage(driver2);
	}

}

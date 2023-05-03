package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.BasePageUI;
import com.nopcommerce.pageUIs.user.UserWishlistPageUI;

import commons.BasePage;

public class UserWishlistPageObject extends BasePage {

	WebDriver driver;
	public UserWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSharingLink() {
		waitForElementClickable(driver, UserWishlistPageUI.SHARING_LINK);
		clickToElement(driver, UserWishlistPageUI.SHARING_LINK);
		
	}
	public void clickToCheckboxAddToCart() {
		waitForElementClickable(driver, UserWishlistPageUI.ADD_TO_CART_CHECKBOX);
		checktoDefaultCheckboxRadio(driver, UserWishlistPageUI.ADD_TO_CART_CHECKBOX);
		
	}
	public void clickToButtonAddToCart() {
		waitForElementClickable(driver, UserWishlistPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, UserWishlistPageUI.ADD_TO_CART_BUTTON);
	}
	public UserShoppingCartPageObject openShoppingCartPage() {
		return PageGeneratorManager.getUserShoppingCartPage(driver);
	}

	public void clickToRemoveProductButton() {
		waitForElementClickable(driver, UserWishlistPageUI.REMOVE_PRODUCT_BUTTON);
		clickToElement(driver, UserWishlistPageUI.REMOVE_PRODUCT_BUTTON);
	}

	public boolean isDisplayedNoData() {
		waitForAllElementVisible(driver, UserWishlistPageUI.NO_DATA);
		if(elementIsUndisplayed(driver, BasePageUI.PRODUCT_NAME)== true  && elementIsDisplayed(driver, UserWishlistPageUI.NO_DATA)) {
			return true;
		}else {
			return false;
		}
		
	}

}

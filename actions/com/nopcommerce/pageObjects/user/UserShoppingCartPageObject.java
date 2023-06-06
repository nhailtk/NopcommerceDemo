package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserShoppingCartPageUI;

import commons.BasePage;

public class UserShoppingCartPageObject extends BasePage {
	WebDriver driver;

	public UserShoppingCartPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToEditButton() {
		waitForElementClickable(driver, UserShoppingCartPageUI.EDIT_BUTTON);
		clickToElement(driver, UserShoppingCartPageUI.EDIT_BUTTON);

	}

	public void clickToRemoveButton() {
		waitForElementClickable(driver, UserShoppingCartPageUI.REMOVE_BUTTON);
		clickToElement(driver, UserShoppingCartPageUI.REMOVE_BUTTON);
		
	}

	public boolean verifyNoData() {
		waitForElementVisible(driver, UserShoppingCartPageUI.NO_DATA);
		if(elementIsDisplayed(driver, UserShoppingCartPageUI.NO_DATA) && elementIsUndisplayed(driver, UserShoppingCartPageUI.EDIT_BUTTON)) {
			return true;
		}else {
			return false;
		}
	}

	public void clickToUpdateShoppingCart() {
		waitForElementClickable(driver, UserShoppingCartPageUI.UPDATE_SHOPPING_CART);
		clickToElement(driver, UserShoppingCartPageUI.UPDATE_SHOPPING_CART);
		
	}

	public String getSubTotalOfProduct() {
		waitForElementVisible(driver, UserShoppingCartPageUI.SUB_TOTAL);
		return getElementText(driver, UserShoppingCartPageUI.SUB_TOTAL);
	}

	public void inputQuantityProduct(String string) {
		waitForElementVisible(driver, UserShoppingCartPageUI.QUANTITY_PRODUCT);
		sendkeyToElement(driver, UserShoppingCartPageUI.QUANTITY_PRODUCT, string);
		
	}
	
	public void checkToAgreeCheckbox() {
		waitForElementClickable(driver, UserShoppingCartPageUI.AGREE_CHECKBOX);
		checktoDefaultCheckboxRadio(driver,UserShoppingCartPageUI.AGREE_CHECKBOX);
	}
	
	public UserCheckoutPageObject clickToCheckoutButton() {
		waitForElementClickable(driver, UserShoppingCartPageUI.CHECKOUT_BUTTON);
		clickToElement(driver, UserShoppingCartPageUI.CHECKOUT_BUTTON);
		return UserPageGeneratorManager.getUserCheckoutPage(driver);
		
	}


}

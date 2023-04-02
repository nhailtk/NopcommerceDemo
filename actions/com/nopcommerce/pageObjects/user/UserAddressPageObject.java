package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserAddressPageUI;
import com.nopcommerce.pageUIs.user.UserCustomerInfoPageUI;

import commons.BasePage;

public class UserAddressPageObject extends BasePage {
	WebDriver driver;

	public UserAddressPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToAddNewButton() {
		waitForElementClickable(driver, UserAddressPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, UserAddressPageUI.ADD_NEW_BUTTON);
		
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, UserAddressPageUI.SAVE_BUTTON);
		clickToElement(driver, UserAddressPageUI.SAVE_BUTTON);
		
	}

	public String getValueOfDynamicLabel(String string) {
		waitForElementVisible(driver, UserAddressPageUI.DYNAMIC_LABEL, string);
		return getElementText(driver, UserAddressPageUI.DYNAMIC_LABEL, string);
	}
	

}

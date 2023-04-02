package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.BasePageUI;
import com.nopcommerce.pageUIs.user.UserCustomerInfoPageUI;

import commons.BasePage;

public class UserCustomerInfoPageObject extends BasePage {
	WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void selectValueOfGenderRadioButton() {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, "gender-female");
		clickToElement(driver, BasePageUI.DYNAMIC_TEXTBOX, "gender-female");
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.SAVE_BUTTON);
		clickToElement(driver, UserCustomerInfoPageUI.SAVE_BUTTON);

	}

	public boolean verifyValueOfGender() {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, "gender-female");
		return elementIsSelected(driver, BasePageUI.DYNAMIC_TEXTBOX, "gender-female");
	}

}

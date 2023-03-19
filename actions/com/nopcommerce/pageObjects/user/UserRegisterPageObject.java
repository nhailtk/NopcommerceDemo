package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserHomePageUI;
import com.nopcommerce.pageUIs.user.UserRegisterPageUI;

import commons.BasePage;

public class UserRegisterPageObject extends BasePage{
	WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public UserHomePageObject clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
		
	}

	public String getErrorMessageAtTextbox(String locatorError) {
		waitForElementVisible(driver, UserRegisterPageUI.ERROR_VALIDATE_FIELD, locatorError);
		return getElementText(driver, UserRegisterPageUI.ERROR_VALIDATE_FIELD, locatorError);
	}

	public void inputValueToTextbox(String locatorTextbox, String value) {
		waitForElementVisible(driver, UserRegisterPageUI.TEXTBOX_LOCATOR, locatorTextbox);
		sendkeyToElement(driver, UserRegisterPageUI.TEXTBOX_LOCATOR, value, locatorTextbox);
		
	}
	
	public String getTextConfirmRegisterSuccess() {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_REGISTER_SUCCESS);
		return getElementText(driver, UserRegisterPageUI.CONFIRM_REGISTER_SUCCESS);
	}

	public String getTextErrorExistEmail() {
		waitForElementVisible(driver, UserRegisterPageUI.ERROR_EXIST_EMAIL);
		return getElementText(driver, UserRegisterPageUI.ERROR_EXIST_EMAIL);
	}

	public String getTextErrorLengthPassword() {
		waitForElementVisible(driver, UserRegisterPageUI.ERROR_LENGTH_PASSWORD);
		return getElementText(driver, UserRegisterPageUI.ERROR_LENGTH_PASSWORD);
	}
	
	

}

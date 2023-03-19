package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserLoginPageUI;

import commons.BasePage;

public class UserLoginPageObject extends BasePage{
	WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		
	}

	public String getTextErrorWrongEmail() {
		waitForElementVisible(driver, UserLoginPageUI.ERROR_WRONG_EMAIL);
		return getElementText(driver, UserLoginPageUI.ERROR_WRONG_EMAIL);
	}

	public String getTextErrorWithInvalidValue() {
		waitForElementVisible(driver, UserLoginPageUI.ERROR_UNREGISTERED_EMAIL);
		return getElementText(driver, UserLoginPageUI.ERROR_UNREGISTERED_EMAIL);
	}
	
	public void inputTextboxAndClickButton(String Email, String password) {
		inputValueToTextbox("Email",Email);
		inputValueToTextbox("Password",password);
		clickToLoginButton();
	}
	
	public void inputValueToTextbox(String locator, String value) {
		waitForElementVisible(driver, UserLoginPageUI.TEXTBOX_LOCATOR, locator);
		sendkeyToElement(driver, UserLoginPageUI.TEXTBOX_LOCATOR, value, locator);
	}

}

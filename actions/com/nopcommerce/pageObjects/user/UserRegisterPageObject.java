package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.BasePageUI;
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
		return UserPageGeneratorManager.getUserHomePage(driver);
		
	}

	public String getErrorMessageAtTextbox(String locatorError) {
		waitForElementVisible(driver, UserRegisterPageUI.ERROR_VALIDATE_FIELD, locatorError);
		return getElementText(driver, UserRegisterPageUI.ERROR_VALIDATE_FIELD, locatorError);
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

	public boolean isDisplayedIconLogIn() {
		return elementIsDisplayed(driver, BasePageUI.HEADER_DYNAMIC_LINK, "ico-login");
	}
	
	

}

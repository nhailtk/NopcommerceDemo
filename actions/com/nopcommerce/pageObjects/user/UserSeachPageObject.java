package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.UserSearchPageUI;

import commons.BasePage;

public class UserSeachPageObject extends BasePage {
	WebDriver driver;

	public UserSeachPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, UserSearchPageUI.SEARCH_BUTTON);

	}

	public String verifyDisplayWarningText() {
		waitForElementVisible(driver, UserSearchPageUI.WARNING_TEXT);
		return getElementText(driver, UserSearchPageUI.WARNING_TEXT);
	}

	public String verifyNoResult() {
		waitForElementVisible(driver, UserSearchPageUI.NO_RESULT);
		return getElementText(driver, UserSearchPageUI.NO_RESULT);
	}

	public boolean verifyResultWithRelativeSearch() {
		waitForAllElementVisible(driver, UserSearchPageUI.RELATIVE_RESULT);
		if (getListWebElement(driver, UserSearchPageUI.RELATIVE_RESULT).get(0).getText().equals("Lenovo IdeaCentre 600 All-in-One PC")
				&& getListWebElement(driver, UserSearchPageUI.RELATIVE_RESULT).get(1).getText().equals("Lenovo Thinkpad X1 Carbon Laptop")) {
			return true;
		} else {
			return false;
		}

	}

	public String verifyResultWithSearch() {
		waitForElementVisible(driver, UserSearchPageUI.RELATIVE_RESULT);
		return getElementText(driver, UserSearchPageUI.RELATIVE_RESULT);
	}

	public void actionWithCheckBox(boolean b, String string) {

		if (b == true) {
			waitForElementClickable(driver, UserSearchPageUI.DYNAMIC_CHECKBOX, string);
			clickToElement(driver, UserSearchPageUI.DYNAMIC_CHECKBOX, string);
		}

	}

}

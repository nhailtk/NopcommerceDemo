package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nopcommerce.pageUIs.admin.AdminCustomerCreatePageUI;
import com.nopcommerce.pageUIs.admin.AdminCustomerEditPageUI;
import com.nopcommerce.pageUIs.admin.AdminProductListPageUI;
import com.nopcommerce.pageUIs.user.BasePageUI;

import commons.BasePage;

public class AdminCustomerEditPageObject extends BasePage {
	WebDriver driver;

	public AdminCustomerEditPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean isDisplayedCustomerRole() {
		waitForElementVisible(driver, AdminCustomerEditPageUI.GUEST_ROLE);
		return elementIsDisplayed(driver, AdminCustomerEditPageUI.GUEST_ROLE);
	}

	public String getTextInCommentTextArea() {
		waitForElementVisible(driver, AdminCustomerCreatePageUI.COMMENT_TEXTAREA);
		return getElementText(driver, AdminCustomerCreatePageUI.COMMENT_TEXTAREA);
	}

	public AdminCustomerListPageObject clickToBackButton() {
		waitForElementClickable(driver, AdminCustomerEditPageUI.BACK_TO_CUSTOMER_LIST_BUTTON);
		clickToElement(driver, AdminCustomerEditPageUI.BACK_TO_CUSTOMER_LIST_BUTTON);
		return AdminPageGeneratorManager.getAdminCustomerListPage(driver);
	}

	public AdminCustomerListPageObject clickToSaveButton() {
		waitForElementClickable(driver, AdminCustomerEditPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminCustomerEditPageUI.SAVE_BUTTON);
		return AdminPageGeneratorManager.getAdminCustomerListPage(driver);

	}

	public String getValueInCellByHeaderNameOfAddress(WebDriver driver, String headerName) {
		int index = getElementSize(driver, AdminCustomerEditPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerName) + 1;
		waitForElementVisible(driver, AdminCustomerEditPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(index));
		return getElementText(driver, AdminCustomerEditPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(index));
	}

	public String getValueInCellByHeaderNameAtFirstColumnOfAddress(WebDriver driver, String headerName) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, AdminCustomerEditPageUI.ADDRESSES_TAB));
		int index = getElementSize(driver, AdminCustomerEditPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME_AT_FIRST_POSITION, headerName);
		waitForElementVisible(driver, AdminCustomerEditPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(index));
		return getElementText(driver, AdminCustomerEditPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(index));
	}

	public void clickToAddressesPanel() {
		if (elementIsUndisplayed(driver, AdminCustomerEditPageUI.ADD_NEW_ADDRESS_BUTTON) == true) {
			waitForElementClickable(driver, AdminCustomerEditPageUI.ADDRESSES_TAB);
			clickToElementByJS(driver, AdminCustomerEditPageUI.ADDRESSES_TAB);
		} else {
			return;
		}
	}

	public AdminAddressCreatePageObject clickToAddNewAddress() {
		waitForElementClickable(driver, AdminCustomerEditPageUI.ADD_NEW_ADDRESS_BUTTON);
		clickToElement(driver, AdminCustomerEditPageUI.ADD_NEW_ADDRESS_BUTTON);
		return AdminPageGeneratorManager.getAdminAddressCreatePage(driver);
	}

	public AdminAddressCreatePageObject clickToEditAddressButton() {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, AdminCustomerEditPageUI.ADDRESSES_TAB));
		waitForElementClickable(driver, AdminCustomerEditPageUI.EDIT_ADDRESS_BUTTON);
		clickToElement(driver, AdminCustomerEditPageUI.EDIT_ADDRESS_BUTTON);
		return AdminPageGeneratorManager.getAdminAddressCreatePage(driver);
	}

	public void clickToDeleteAddressButton() {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, AdminCustomerEditPageUI.ADDRESSES_TAB));
		waitForElementClickable(driver, AdminCustomerEditPageUI.DELETE_ADDRESS_BUTTON);
		clickToElement(driver, AdminCustomerEditPageUI.DELETE_ADDRESS_BUTTON);
		acceptAlert(driver);
	}

	public boolean verifyDisplayedEmptyData() {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, AdminCustomerEditPageUI.ADDRESSES_TAB));
		waitForElementVisible(driver, AdminCustomerEditPageUI.EMPTY_DATA);
		return elementIsDisplayed(driver, AdminCustomerEditPageUI.EMPTY_DATA);
	}

}

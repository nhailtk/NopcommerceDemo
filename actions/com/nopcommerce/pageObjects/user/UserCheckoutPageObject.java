package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageUIs.user.BasePageUI;
import com.nopcommerce.pageUIs.user.UserCheckoutPageUI;

import commons.BasePage;

public class UserCheckoutPageObject extends BasePage {
	WebDriver driver;

	public UserCheckoutPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void uncheckToCheckboxRadioButton(String string) {
		waitForElementClickable(driver, UserCheckoutPageUI.DYNAMIC_CHECKBOX_RADIO, string);
		unchecktoDefaultCheckboxRadio(driver, UserCheckoutPageUI.DYNAMIC_CHECKBOX_RADIO, string);

	}

	public void clickToContinueButton(String string) {
		waitForElementClickable(driver, UserCheckoutPageUI.DYNAMIC_CONTINUE_BUTTON, string);
		clickToElement(driver, UserCheckoutPageUI.DYNAMIC_CONTINUE_BUTTON, string);

	}

	public void checkToCheckboxRadioButton(String string) {
		waitForElementClickable(driver, UserCheckoutPageUI.DYNAMIC_CHECKBOX_RADIO, string);
		checktoDefaultCheckboxRadio(driver, UserCheckoutPageUI.DYNAMIC_CHECKBOX_RADIO, string);

	}

	public String getPaymentInfo() {
		waitForElementVisible(driver, UserCheckoutPageUI.PAYMENT_INFO_LABEL);
		return getElementText(driver, UserCheckoutPageUI.PAYMENT_INFO_LABEL);
	}
	
	public void getPaymentInfoWithCard(String cardName, String cardNumber, String cardCode, String monthExprationCard, String yearExprationCard) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_DROPDOWN, "CreditCardType");
		selectItemByTextInDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN, "Visa", "CreditCardType");
		
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, "CardholderName");
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX, cardName, "CardholderName");
		
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, "CardNumber");
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX, cardNumber, "CardNumber");
		
		waitForElementVisible(driver, BasePageUI.DYNAMIC_DROPDOWN, "ExpireMonth");
		selectItemByTextInDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN, monthExprationCard, "ExpireMonth");
		
		waitForElementVisible(driver, BasePageUI.DYNAMIC_DROPDOWN, "ExpireYear");
		selectItemByTextInDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN, yearExprationCard, "ExpireYear");
		
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, "CardCode");
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX, cardCode, "CardCode");
	}

	public String getDynamicTitleOfConfirmOrder(String string) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_TITLE_OF_CONFIRM_ORDER, string);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_TITLE_OF_CONFIRM_ORDER, string);
	}

	public String getDynamicInfoOfConfirmOrder(String string, String string2) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_CONFIRM_ORDER, string, string2);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_CONFIRM_ORDER, string, string2);
	}

	public String getDynamicInfoOfProduct(String string) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_PRODUCT, string);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_PRODUCT, string);
	}

	public String getInfoGiftWrapping() {
		waitForElementVisible(driver, UserCheckoutPageUI.GIFT_WRAPPING);
		return getElementText(driver, UserCheckoutPageUI.GIFT_WRAPPING);
	}

	public UserOrderCompletedPageObject clickToConfirmButton() {
		waitForElementClickable(driver, UserCheckoutPageUI.CONFIRM_BUTTON);
		clickToElement(driver, UserCheckoutPageUI.CONFIRM_BUTTON);
		return UserPageGeneratorManager.getUserOrderCompletedPage(driver);
	}

	public String getDynamicInfoOfProductPrice(String string) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_PRICE, string);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_INFO_OF_PRICE, string);
	}



}

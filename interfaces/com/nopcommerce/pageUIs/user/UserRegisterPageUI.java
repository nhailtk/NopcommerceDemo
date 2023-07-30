package com.nopcommerce.pageUIs.user;

public class UserRegisterPageUI {
	public static String REGISTER_BUTTON = "css=button#register-button";
	public static String ERROR_VALIDATE_FIELD = "xpath=//span[@id='%s-error']";
	public static String TEXTBOX_LOCATOR = "xpath=//input[@id='%s']";
	public static String CONFIRM_REGISTER_SUCCESS = "xpath=//div[@class='result']";
	public static String ERROR_EXIST_EMAIL = "xpath=//div[contains(@class,'message-error')]/ul/li";
	public static String ERROR_LENGTH_PASSWORD = "xpath=//span[contains(.,'Password must meet the following rules: ')]";
}

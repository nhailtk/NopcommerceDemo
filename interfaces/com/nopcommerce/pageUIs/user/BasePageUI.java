package com.nopcommerce.pageUIs.user;

public class BasePageUI {
	public static String HEADER_DYNAMIC_LINK = "xpath=//a[@class='%s']";
	public static String HEADER_DYNAMIC_MENU = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static String FOOTER_DYNAMIC_LINK = "xpath=//div[@class='footer']//a[text()='%s']";
	public static String RIGHT_DYNAMIC_MENU_OF_MY_ACCOUNT = "xpath=//div[@class='block block-account-navigation']//a[contains(text(),'%s')]";
	public static String DYNAMIC_TEXTBOX = "XPATH=//input[@id='%s']";
	public static String DYNAMIC_CHECKBOX_RADIO_BUTTON = "XPATH=//input[@id='%s']";
	public static String DYNAMIC_DROPDOWN = "XPATH=//select[@name='%s']";
	public static String UPDATE_SUCCESS_NOTIFICATION = "XPATH=//div[@id='bar-notification']//p[contains(text(),'%s')]";
	public static String CLOSE_BUTTON_IN_NOTIFICATION = "XPATH=//div[@id='bar-notification']//span[@class='close']";
	public static String DYNAMIC_TITLE_PAGE = "XPATH=//h1[contains(text(),'%s')]";
	public static String PRODUCT_NAME = "XPATH=//td[@class='product']/a";
	public static String CONFIRM_MASSAGE_OF_PRODUCTS = "XPATH=//p[@class='content']";
	public static String ADMIN_RIGHT_MENU = "XPATH=//i[contains(@class,'%s')]/following-sibling::p[contains(text(),'%s')]";
	public static String ADMIN_RIGHT_SUB_MENU = "XPATH=//p[contains(text(),'%s')]/parent::a/following-sibling::ul//p[contains(text(),'%s')]";

}

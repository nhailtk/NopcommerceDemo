package com.nopcommerce.pageUIs.user;

public class BasePageUI {
	public static String  HEADER_DYNAMIC_LINK="xpath=//a[@class='%s']";
	public static String  HEADER_DYNAMIC_MENU="xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static String  HEADER_DYNAMIC_FOOTER="xpath=//div[@class='footer']//a[text()='%s']";
	public static String  RIGHT_DYNAMIC_MENU_OF_MY_ACCOUNT="xpath=//div[@class='block block-account-navigation']//a[contains(text(),'%s')]";
	public static String DYNAMIC_TEXTBOX ="XPATH=//input[@id='%s']";
	public static String DYNAMIC_DROPDOWN="XPATH=//select[@name='%s']";
	public static String UPDATE_SUCCESS_NOTIFICATION = "XPATH=//div[@id='bar-notification']//p[text()='%s']";
	public static String CLOSE_BUTTON_IN_NOTIFICATION = "XPATH=//div[@id='bar-notification']//span[@class='close']";
	public static String DYNAMIC_TITLE_PAGE = "XPATH=//h1[contains(text(),'%s')]";
	

}

package com.nopcommerce.pageUIs.user;

public class UserOrdersPageUI {
	public static String ORDER_NUMBER = "XPATH=//div[@class='page-body']//div[@class='title']/strong";
	public static String DETAIL_BUTTON = "XPATH=//strong[text()='Order Number: %s']/parent::div/following-sibling::div[@class='buttons']/button[text()='Details']";
	public static String ORDER_NUMBER_DETAIL = "XPATH=//div[@class='order-number']/strong";
	public static String ORDER_INFO = "XPATH=//li[@class='%s']";
	public static String PRODUCT_NAME = "XPATH=//td[@class='product']/em/a";
	public static String DYNAMIC_INFO_PRODUCT_PRICE = "XPATH=//label[text()='%s:']/parent::td/following-sibling::td/span";
	public static String REORDER_BUTTON = "XPATH=//button[text()='Re-order']";
	

}

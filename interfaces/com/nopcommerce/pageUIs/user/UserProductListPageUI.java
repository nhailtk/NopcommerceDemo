package com.nopcommerce.pageUIs.user;

public class UserProductListPageUI {
	public static String PRODUCT_TITLE = "XPATH=//h2[@class='product-title']/a[text()='%s']";
	public static String PRODUCT_LIST_TITLE = "XPATH=//h2/a";
	public static String PRODUCT_LIST_PRICE = "XPATH=//div[@class='prices']/span";
	public static String NEXT_PAGING_ICON = "XPATH=//li[@class='%s']/a";
	public static String ADD_TO_COMPARE_LIST_BUTTON = "XPATH=//a[contains(text(),'%s')]/parent::h2/following-sibling::div[@class='add-info']//button[text()='Add to compare list']";

}

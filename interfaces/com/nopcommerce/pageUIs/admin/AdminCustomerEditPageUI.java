package com.nopcommerce.pageUIs.admin;

public class AdminCustomerEditPageUI {
	public static String CONFIRM_MESSAGE = "XPATH=//div[contains(@class,'alert-success') and contains(.,'%s')]";
	public static String EDIT_CONFIRM_MESSAGE = "XPATH=//div[contains(@class,'alert-success') and contains(.,'The customer has been updated successfully.')]";
	public static String GUEST_ROLE = "xpath=//span[text()='Guests']";
	public static String BACK_TO_CUSTOMER_LIST_BUTTON = "xpath=//a[contains(text(),'back to customer list')]";
	public static String SAVE_BUTTON = "xpath=//button[contains(.,'Save') and @name ='save']";
	public static String ADDRESSES_TAB = "xpath=//div[@class='card-title' and contains(.,'Addresses')]/parent::div";
	public static String ADD_NEW_ADDRESS_BUTTON = "xpath=//button[contains(text(),'Add new address')]";
	public static String EDIT_ADDRESS_BUTTON = "xpath=//table[@id='customer-addresses-grid']/tbody/tr/*[7]";
	public static String DELETE_ADDRESS_BUTTON = "xpath=//table[@id='customer-addresses-grid']/tbody/tr/*[8]";
	public static final String TABLE_HEADER_INDEX_BY_HEADER_NAME = "Xpath=//table[contains(@class,'dataTable')]/thead//th[text()='%s']/preceding-sibling::*";
	public static final String TABLE_HEADER_INDEX_BY_HEADER_NAME_AT_FIRST_POSITION = "Xpath=//table[contains(@class,'dataTable')]/thead//th[text()='%s']";
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "Xpath=//table[@id='customer-addresses-grid']/tbody/tr/*[%s]";
	public static final String EMPTY_DATA = "Xpath=//div[@id='customer-addresses-grid_wrapper']//td[text()='No data available in table']";

}

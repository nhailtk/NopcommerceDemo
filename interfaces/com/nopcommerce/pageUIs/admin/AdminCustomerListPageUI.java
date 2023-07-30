package com.nopcommerce.pageUIs.admin;

public class AdminCustomerListPageUI {
	public static String CUSTOMER_LIST_TITLE = "XPATH=//h1[contains(text(),'Customers')]";
	public static String ADD_NEW_BUTTON = "XPATH=//a[@class='btn btn-primary']";
	public static String DELETE_ICON_IN_ROLE = "XPATH=//span[contains(@class,'k-icon k-i-close')]";
	public static String ROLE_DROPDOWN_PARENT = "XPATH=//label[text()='Customer roles']/parent::div/parent::div/following-sibling::div[@class='col-md-8']//div[contains(@class,'k-multiselect k-multiselect-clearable')]";
	public static String ROLE_DROPDOWN_CHILD = "XPATH=//li[text()='Guests']";
	public static String SEARCH_BUTTON = "XPATH=//button[@id='search-customers']";
	public static String EDIT_BUTTON = "XPATH=//td[@class='  button-column']/a[contains(.,'Edit')]";
	public static final String TRUE_ICON = "Xpath=//table[contains(@class,'dataTable')]/tbody//tr/*[6]/i[contains(@class, 'true-icon')]";

}

package com.nopcommerce.pageUIs.admin;

public class AdminCustomerCreatePageUI {
	public static String CUSTOMER_CREATE_TITLE = "XPATH=//h1[contains(text(),'Add a new customer')]";
	public static String DELETE_ICON_IN_ROLE = "XPATH=//span[contains(@class,'k-icon k-i-close')]";
	public static String ROLE_DROPDOWN_PARENT = "XPATH=//label[text()='Customer roles']/parent::div/parent::div/following-sibling::div[@class='col-md-9']//div[contains(@class,'k-multiselect k-multiselect-clearable')]";
	public static String ROLE_DROPDOWN_CHILD = "XPATH=//li[text()='Guests']";
	public static String COMMENT_TEXTAREA = "XPATH=//textarea[@id='AdminComment']";
	public static String SAVE_CONTINUE_BUTTON = "XPATH=//button[@name='save-continue']";

}

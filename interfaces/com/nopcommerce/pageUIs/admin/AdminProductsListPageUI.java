package com.nopcommerce.pageUIs.admin;

public class AdminProductsListPageUI {
	public static String PRODUCTS_LIST_TITLE = "XPATH=//h1[contains(text(),'Products')]";
	public static String SEARCH_BUTTON = "XPATH=//button[@id='search-products']";
	public static final String TABLE_HEADER_INDEX_BY_HEADER_NAME = "Xpath=//table[contains(@class,'dataTable')]/thead//th[text()='%s']/preceding-sibling::*";
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "Xpath=//table[contains(@class,'dataTable')]/tbody//tr/*[%s]";
	public static final String TRUE_ICON = "Xpath=//table[contains(@class,'dataTable')]/tbody//tr/*[7]/i[contains(@class, 'true-icon')]";
	public static final String NO_DATA_IN_TABLE = "Xpath=//table[contains(@class,'dataTable')]/tbody/tr/td[text()='No data available in table']";
	public static final String SEARCH_CHECKBOX = "Xpath=//input[@id='SearchIncludeSubCategories']";
	public static final String GO_BUTTON = "Xpath=//button[@id='go-to-product-by-sku']";
}

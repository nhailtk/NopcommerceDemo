package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;


public class AdminPageGeneratorManager {
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
	
	public static AdminProductsListPageObject getAdminProductsListPage(WebDriver driver) {
		return new AdminProductsListPageObject(driver);
	}
	
	public static AdminEditProductPageObject getAdminEditProductPage(WebDriver driver) {
		return new AdminEditProductPageObject(driver);
	}

}

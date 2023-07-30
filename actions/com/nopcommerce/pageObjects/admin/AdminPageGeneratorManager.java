package com.nopcommerce.pageObjects.admin;

import org.openqa.selenium.WebDriver;

public class AdminPageGeneratorManager {
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminProductListPageObject getAdminProductListPage(WebDriver driver) {
		return new AdminProductListPageObject(driver);
	}

	public static AdminEditProductPageObject getAdminEditProductPage(WebDriver driver) {
		return new AdminEditProductPageObject(driver);
	}

	public static AdminCustomerListPageObject getAdminCustomerListPage(WebDriver driver) {
		return new AdminCustomerListPageObject(driver);
	}

	public static AdminCustomerCreatePageObject getAdminCustomerCreatePage(WebDriver driver) {
		return new AdminCustomerCreatePageObject(driver);
	}

	public static AdminCustomerEditPageObject getAdminCustomerEditPage(WebDriver driver) {
		return new AdminCustomerEditPageObject(driver);
	}

	public static AdminAddressCreatePageObject getAdminAddressCreatePage(WebDriver driver) {
		return new AdminAddressCreatePageObject(driver);
	}
}

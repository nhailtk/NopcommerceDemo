package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.admin.AdminDashboardPageObject;
import com.nopcommerce.pageObjects.admin.AdminEditProductPageObject;
import com.nopcommerce.pageObjects.admin.AdminLoginPageObject;
import com.nopcommerce.pageObjects.admin.AdminPageGeneratorManager;
import com.nopcommerce.pageObjects.admin.AdminProductsListPageObject;

import commons.BaseTest;

public class AdminSearch extends BaseTest {
	WebDriver driver;
	String urlAdmin = "", urlUser = "";
	String username = "admin@yourstore.com", password = "admin";
	String productName = "Lenovo IdeaCentre 600 All-in-One PC";
	AdminLoginPageObject adminLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	AdminProductsListPageObject adminProductsListPage;
	AdminEditProductPageObject adminEditProductPage;

	@Parameters({ "browser", "urlAdmin", "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String urlAdmin, String urlUser) {
		this.urlAdmin = urlAdmin;
		this.urlUser = urlUser;
		driver = getWebDriver(browserName, this.urlAdmin);


		adminLoginPage = AdminPageGeneratorManager.getAdminLoginPage(driver);

		adminLoginPage.inputValueToDynamicTextbox(driver, "Email", username);
		adminLoginPage.inputValueToDynamicTextbox(driver, "Password", password);
		adminDashboardPage = (AdminDashboardPageObject) adminLoginPage.clickToLoginButton();

		Assert.assertTrue(adminDashboardPage.verifyDisplayedTitle());
	}

	@Test
	public void TC_01_Search_With_Product_Name() {
		adminDashboardPage.clickToCatalogMenu(driver, "fa-book", "Catalog");
		adminProductsListPage = (AdminProductsListPageObject) adminDashboardPage.clickToProductsSubMenu(driver, "Catalog","Products");
		Assert.assertTrue(adminProductsListPage.verifyDisplayedTitle());

		adminProductsListPage.inputValueToDynamicTextbox(driver, "SearchProductName", productName);
		adminProductsListPage.clickToSearchButton();
		adminProductsListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Product name"), productName);
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("SKU"), "LE_IC_600");
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Price"), "500");
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Stock quantity"), "10000");
		Assert.assertTrue(adminProductsListPage.isDisplayedTrueIcon());
	}

	@Test
	public void TC_02_Search_With_Product_Name_And_Parent_Category_And_Unchecked_Search_ChildCategory() {
		adminProductsListPage.doubleClickToCatalogMenu("fa-book", "Catalog");
		adminProductsListPage.clickToProductsSubMenu(driver, "Catalog","Products");
		Assert.assertTrue(adminProductsListPage.verifyDisplayedTitle());

		adminProductsListPage.inputValueToDynamicTextbox(driver, "SearchProductName", productName);
		adminProductsListPage.selectTextOfDynamicDropdown(driver, "SearchCategoryId", "Computers");
		adminProductsListPage.uncheckedSearchSubCategory();
		adminProductsListPage.clickToSearchButton();
		adminProductsListPage.isPageLoadedSuccess(driver);

		Assert.assertTrue(adminProductsListPage.isDisplayedNoData());
	}

	@Test
	public void TC_03_Search_With_Product_Name_And_Parent_Category_And_Checked_Search_ChildCategory() {
		adminProductsListPage.doubleClickToCatalogMenu("fa-book", "Catalog");
		adminProductsListPage.clickToProductsSubMenu(driver, "Catalog","Products");
		Assert.assertTrue(adminProductsListPage.verifyDisplayedTitle());

		adminProductsListPage.inputValueToDynamicTextbox(driver, "SearchProductName", productName);
		adminProductsListPage.selectTextOfDynamicDropdown(driver, "SearchCategoryId", "Computers");
		adminProductsListPage.checkedSearchSubCategory();
		adminProductsListPage.clickToSearchButton();
		adminProductsListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Product name"), productName);
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("SKU"), "LE_IC_600");
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Price"), "500");
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Stock quantity"), "10000");
		Assert.assertTrue(adminProductsListPage.isDisplayedTrueIcon());
	}

	@Test
	public void TC_04_Search_With_Product_Name_And_Child_Category_And_Unchecked_Search_ChildCategory() {
		adminProductsListPage.doubleClickToCatalogMenu("fa-book", "Catalog");
		adminProductsListPage.clickToProductsSubMenu(driver, "Catalog","Products");
		Assert.assertTrue(adminProductsListPage.verifyDisplayedTitle());

		adminProductsListPage.inputValueToDynamicTextbox(driver, "SearchProductName", productName);
		adminProductsListPage.selectTextOfDynamicDropdown(driver, "SearchCategoryId", "Computers >> Desktops");
		adminProductsListPage.uncheckedSearchSubCategory();
		adminProductsListPage.clickToSearchButton();
		adminProductsListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Product name"), productName);
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("SKU"), "LE_IC_600");
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Price"), "500");
		Assert.assertEquals(adminProductsListPage.getValueInCellByHeaderName("Stock quantity"), "10000");
		Assert.assertTrue(adminProductsListPage.isDisplayedTrueIcon());
	}

	@Test
	public void TC_05_Search_With_Product_Name_And_Manufacturer() {
		adminProductsListPage.doubleClickToCatalogMenu("fa-book", "Catalog");
		adminProductsListPage.clickToProductsSubMenu(driver, "Catalog","Products");
		Assert.assertTrue(adminProductsListPage.verifyDisplayedTitle());

		adminProductsListPage.inputValueToDynamicTextbox(driver, "SearchProductName", productName);
		adminProductsListPage.selectTextOfDynamicDropdown(driver, "SearchCategoryId", "All");
		adminProductsListPage.uncheckedSearchSubCategory();
		adminProductsListPage.selectTextOfDynamicDropdown(driver, "SearchManufacturerId", "Apple");
		adminProductsListPage.clickToSearchButton();
		adminProductsListPage.isPageLoadedSuccess(driver);

		Assert.assertTrue(adminProductsListPage.isDisplayedNoData());
	}

	@Test
	public void TC_06_Go_Direcly_To_Product_SKU() {
		adminProductsListPage.doubleClickToCatalogMenu("fa-book", "Catalog");
		adminProductsListPage.clickToProductsSubMenu(driver, "Catalog","Products");
		Assert.assertTrue(adminProductsListPage.verifyDisplayedTitle());

		adminProductsListPage.inputValueToDynamicTextbox(driver, "GoDirectlyToSku", "LE_IC_600");
		adminEditProductPage = (AdminEditProductPageObject) adminProductsListPage.clickToGoButton();

		Assert.assertTrue(adminEditProductPage.isDisplayedTitle());
		Assert.assertEquals(adminEditProductPage.getValueOfProductName(), productName);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

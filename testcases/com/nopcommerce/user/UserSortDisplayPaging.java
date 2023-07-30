package com.nopcommerce.user;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.user.UserPageGeneratorManager;
import com.nopcommerce.pageObjects.user.UserHomePageObject;
import com.nopcommerce.pageObjects.user.UserProductListPageObject;

import commons.BaseTest;

public class UserSortDisplayPaging extends BaseTest {
	private WebDriver driver;
	UserHomePageObject userHomePage;
	UserProductListPageObject userProductListPage;
	String urlUser = "";

	@Parameters({ "browser", "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String urlUser) {
		this.urlUser = urlUser;
		driver = getWebDriver(browserName, this.urlUser);

		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		userProductListPage = (UserProductListPageObject) userHomePage.openProductListPage(driver, "Computers", "Notebooks");
	}

	@Test
	public void TC_01_Sort_With_Name_ASC() {
		userProductListPage.scrollToSortDropDown("products-orderby");
		userProductListPage.selectTextOfDynamicDropdown(driver, "products-orderby", "Name: A to Z");
		Assert.assertEquals(userProductListPage.vefifyValueOfDynamicDropdown(driver, "products-orderby"), "Name: A to Z");
		Assert.assertTrue(userProductListPage.isPageLoadedSuccess(driver));
		Assert.assertTrue(userProductListPage.isDisplayedSortWithNameASC());
	}

	@Test
	public void TC_02_Sort_With_Name_DESC() {
		userProductListPage.scrollToSortDropDown("products-orderby");
		userProductListPage.selectTextOfDynamicDropdown(driver, "products-orderby", "Name: Z to A");
		Assert.assertEquals(userProductListPage.vefifyValueOfDynamicDropdown(driver, "products-orderby"), "Name: Z to A");
		Assert.assertTrue(userProductListPage.isPageLoadedSuccess(driver));
		Assert.assertTrue(userProductListPage.isDisplayedSortWithNameDESC());
	}

	@Test
	public void TC_03_Sort_With_Price_ASC() {
		userProductListPage.scrollToSortDropDown("products-orderby");
		userProductListPage.selectTextOfDynamicDropdown(driver, "products-orderby", "Price: Low to High");
		Assert.assertEquals(userProductListPage.vefifyValueOfDynamicDropdown(driver, "products-orderby"), "Price: Low to High");
		Assert.assertTrue(userProductListPage.isPageLoadedSuccess(driver));
		Assert.assertTrue(userProductListPage.isDisplayedSortWithPriceASC());
	}

	@Test
	public void TC_04_Sort_With_Price_DESC() {
		userProductListPage.scrollToSortDropDown("products-orderby");
		userProductListPage.selectTextOfDynamicDropdown(driver, "products-orderby", "Price: High to Low");
		Assert.assertEquals(userProductListPage.vefifyValueOfDynamicDropdown(driver, "products-orderby"), "Price: High to Low");
		Assert.assertTrue(userProductListPage.isPageLoadedSuccess(driver));
		Assert.assertTrue(userProductListPage.isDisplayedSortWithPriceDESC());
	}

	@Test
	public void TC_05_Display_With_Three_Product_On_Page() {
		userProductListPage.scrollToSortDropDown("products-pagesize");
		userProductListPage.selectTextOfDynamicDropdown(driver, "products-pagesize", "3");
		Assert.assertEquals(userProductListPage.vefifyValueOfDynamicDropdown(driver, "products-pagesize"), "3");
		Assert.assertTrue(userProductListPage.isPageLoadedSuccess(driver));
		Assert.assertTrue(userProductListPage.isDisplayedProductsList(3));
		Assert.assertTrue(userProductListPage.isDisplayedPagingIcon("next-page"));

		userProductListPage.clickToNextPagingIcon("next-page");
		Assert.assertTrue(userProductListPage.isDisplayedPagingIcon("previous-page"));
	}

	@Test
	public void TC_06_Display_With_Six_Product_On_Page() {
		userProductListPage.scrollToSortDropDown("products-pagesize");
		userProductListPage.selectTextOfDynamicDropdown(driver, "products-pagesize", "6");
		Assert.assertEquals(userProductListPage.vefifyValueOfDynamicDropdown(driver, "products-pagesize"), "6");
		Assert.assertTrue(userProductListPage.isPageLoadedSuccess(driver));
		Assert.assertTrue(userProductListPage.isDisplayedProductsList(6));
		Assert.assertTrue(userProductListPage.isUndisplayedPagingIcon("next-page"));

	}

	@Test
	public void TC_07_Display_With_Nine_Product_On_Page() {
		userProductListPage.scrollToSortDropDown("products-pagesize");
		userProductListPage.selectTextOfDynamicDropdown(driver, "products-pagesize", "9");
		Assert.assertEquals(userProductListPage.vefifyValueOfDynamicDropdown(driver, "products-pagesize"), "9");
		Assert.assertTrue(userProductListPage.isPageLoadedSuccess(driver));
		Assert.assertTrue(userProductListPage.isDisplayedProductsList(9));
		Assert.assertTrue(userProductListPage.isUndisplayedPagingIcon("next-page"));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}

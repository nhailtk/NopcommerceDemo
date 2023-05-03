package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.user.PageGeneratorManager;
import com.nopcommerce.pageObjects.user.UserCompareProuductsListPageObject;
import com.nopcommerce.pageObjects.user.UserHomePageObject;
import com.nopcommerce.pageObjects.user.UserLoginPageObject;
import com.nopcommerce.pageObjects.user.UserProductDetailPageObject;
import com.nopcommerce.pageObjects.user.UserProductListPageObject;
import com.nopcommerce.pageObjects.user.UserRecentlyViewedProductsPageObject;
import com.nopcommerce.pageObjects.user.UserRegisterPageObject;
import com.nopcommerce.pageObjects.user.UserShoppingCartPageObject;
import com.nopcommerce.pageObjects.user.UserWishlistPageObject;

import commons.BaseTest;

public class UserWishlistCompareRecentView extends BaseTest {
	private WebDriver driver;
	UserHomePageObject userHomePage;
	UserRegisterPageObject userRegisterPage;
	UserLoginPageObject userLoginPage;
	UserProductListPageObject userProductListPage;
	UserProductDetailPageObject userProductDetailPage;
	UserWishlistPageObject userWishlistPage;
	UserShoppingCartPageObject userShoppingCartPage;
	UserCompareProuductsListPageObject userCompareProductsListPage;
	UserRecentlyViewedProductsPageObject userRecentlyViewedProductsPage;

	String validEmail, productTitle;
	String registerFirstName = "Anh Hoa";
	String registerLastName = "Mai Nguyen";
	String password = "123456";
	String addedProduct = "The product has been added to your";
	String titleWishlistPage = "Wishlist";

	String locatorFirstName = "FirstName";
	String locatorLastName = "LastName";
	String locatorEmail = "Email";
	String locatorPassword = "Password";
	String locatorConfirmPassword = "ConfirmPassword";

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getWebDriver(browser);

		validEmail = "anhhoa" + randomInt() + "@gmail.com";
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = (UserRegisterPageObject) userHomePage.openDynamicHeaderLinks(driver, "ico-register");

		userRegisterPage.inputValueToDynamicTextbox(driver, locatorFirstName, registerFirstName);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorLastName, registerLastName);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorEmail, validEmail);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorPassword, password);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorConfirmPassword, password);

		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getTextConfirmRegisterSuccess(), "Your registration completed");

		userLoginPage = (UserLoginPageObject) userRegisterPage.openDynamicHeaderLinks(driver, "ico-login");

		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());

		userProductListPage = (UserProductListPageObject) userHomePage.openProductListPage(driver, "Computers", "Desktops");

	}

	@Test
	public void TC01_Add_Product_To_Wishlist() {
		productTitle = userProductListPage.getProductTitle("Lenovo IdeaCentre 600 All-in-One PC");
		userProductDetailPage = userProductListPage.clickToProductTitle("Lenovo IdeaCentre 600 All-in-One PC");

		userProductDetailPage.clickToAddWishlistButton();
		Assert.assertTrue(userProductDetailPage.verfifyUpdateSuccess(driver, addedProduct));
		userProductDetailPage.clickToCloseButtonInNotification(driver);

		userWishlistPage = (UserWishlistPageObject) userProductDetailPage.openDynamicHeaderLinks(driver, "ico-wishlist");
		Assert.assertTrue(userWishlistPage.verifyDisplayDynamicTitlePage(driver, titleWishlistPage));
		Assert.assertEquals(userWishlistPage.getProductName(driver), "Lenovo IdeaCentre 600 All-in-One PC");

		userWishlistPage.clickToSharingLink();
		Assert.assertTrue(userWishlistPage.verifyDisplayDynamicTitlePage(driver, titleWishlistPage + " of " + registerFirstName + " " + registerLastName));
		Assert.assertEquals(userWishlistPage.getProductName(driver), "Lenovo IdeaCentre 600 All-in-One PC");

	}

	@Test
	public void TC02_Add_Product_To_Cart_From_Wishlist() {
		userWishlistPage.clickToCheckboxAddToCart();
		userWishlistPage.clickToButtonAddToCart();

		userShoppingCartPage = userWishlistPage.openShoppingCartPage();
		Assert.assertTrue(userShoppingCartPage.verifyDisplayDynamicTitlePage(driver, "Shopping cart"));
		Assert.assertEquals(userShoppingCartPage.getProductName(driver), "Lenovo IdeaCentre 600 All-in-One PC");
	}

	@Test
	public void TC03_Remove_Product_From_Wishlist() {
		userWishlistPage = (UserWishlistPageObject) userShoppingCartPage.openDynamicHeaderLinks(driver, "ico-wishlist");
		userWishlistPage.clickToRemoveProductButton();
		Assert.assertTrue(userWishlistPage.isDisplayedNoData());
	}

	@Test
	public void TC04_Add_Product_To_Compare() {
		userProductListPage = (UserProductListPageObject) userWishlistPage.openProductListPage(driver, "Computers", "Notebooks");

		userProductListPage.addProductToCompareList("Apple MacBook Pro 13-inch");
		userProductListPage.isPageLoadedSuccess(driver);
		Assert.assertTrue(userProductListPage.verfifyUpdateSuccess(driver, addedProduct));
		userProductListPage.clickToCloseButtonInNotification(driver);

		userProductListPage.addProductToCompareList("Asus N551JK-XO076H Laptop");
		userProductListPage.isPageLoadedSuccess(driver);
		Assert.assertTrue(userProductListPage.verfifyUpdateSuccess(driver, addedProduct));
		userProductListPage.clickToCloseButtonInNotification(driver);

		userProductListPage.scrollToBottom(driver);
		userCompareProductsListPage = (UserCompareProuductsListPageObject) userProductListPage.openDynamicFooterLinks(driver, "Compare products list");
		Assert.assertTrue(userCompareProductsListPage.verifyInformationDisplayed());

		userCompareProductsListPage.clickToClearListButton();
		Assert.assertTrue(userCompareProductsListPage.verifyNoData());
	}
	
	@Test
	public void TC05_Recently_Viewed_Products() {
		userProductListPage = (UserProductListPageObject) userCompareProductsListPage.openProductListPage(driver, "Computers", "Notebooks");
		userProductDetailPage = userProductListPage.clickToProductTitle("Apple MacBook Pro 13-inch");
		
		userProductListPage = (UserProductListPageObject) userProductDetailPage.openProductListPage(driver, "Computers", "Notebooks");
		userProductDetailPage = userProductListPage.clickToProductTitle("Asus N551JK-XO076H Laptop");
				
		userProductListPage = (UserProductListPageObject) userProductDetailPage.openProductListPage(driver, "Computers", "Notebooks");
		userProductDetailPage = userProductListPage.clickToProductTitle("HP Envy 6-1180ca 15.6-Inch Sleekbook");
		
		userProductListPage = (UserProductListPageObject) userProductDetailPage.openProductListPage(driver, "Computers", "Notebooks");
		userProductDetailPage = userProductListPage.clickToProductTitle("HP Spectre XT Pro UltraBook");
		
		userProductListPage = (UserProductListPageObject) userProductDetailPage.openProductListPage(driver, "Computers", "Notebooks");
		userProductDetailPage = userProductListPage.clickToProductTitle("Lenovo Thinkpad X1 Carbon Laptop");
				
		userProductDetailPage.scrollToBottom(driver);
		userRecentlyViewedProductsPage = (UserRecentlyViewedProductsPageObject) userProductDetailPage.openDynamicFooterLinks(driver, "Recently viewed products");
		Assert.assertTrue(userRecentlyViewedProductsPage.verifyInformationDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

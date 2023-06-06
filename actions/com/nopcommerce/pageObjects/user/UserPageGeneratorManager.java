package com.nopcommerce.pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserPageGeneratorManager {
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}

	public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}

	public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}

	public static UserProductReviewsPageObject getUserMyProductReviewsPage(WebDriver driver) {
		return new UserProductReviewsPageObject(driver);
	}

	public static UserProductDetailPageObject getUserProductDetailPage(WebDriver driver) {
		return new UserProductDetailPageObject(driver);
	}

	public static UserProductListPageObject getUserProductListPage(WebDriver driver) {
		return new UserProductListPageObject(driver);
	}

	public static UserSeachPageObject getUserSearchPage(WebDriver driver) {
		return new UserSeachPageObject(driver);
	}

	public static UserWishlistPageObject getUserWishlistPage(WebDriver driver) {
		return new UserWishlistPageObject(driver);
	}

	public static UserShoppingCartPageObject getUserShoppingCartPage(WebDriver driver) {
		return new UserShoppingCartPageObject(driver);
	}

	public static UserCompareProuductsListPageObject getUserCompareProductsListPage(WebDriver driver) {
		return new UserCompareProuductsListPageObject(driver);
	}

	public static UserRecentlyViewedProductsPageObject getUserRecentlyViewedProductsPage(WebDriver driver) {
		return new UserRecentlyViewedProductsPageObject(driver);
	}
	
	public static UserCheckoutPageObject getUserCheckoutPage(WebDriver driver) {
		return new UserCheckoutPageObject(driver);
	}
	
	public static UserOrderCompletedPageObject getUserOrderCompletedPage(WebDriver driver) {
		return new UserOrderCompletedPageObject(driver);
	}

	public static UserOrdersPageObject getUserOrders(WebDriver driver) {
		return new UserOrdersPageObject(driver);
	}
}

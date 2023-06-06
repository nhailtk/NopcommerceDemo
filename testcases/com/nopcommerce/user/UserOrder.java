package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.user.UserPageGeneratorManager;
import com.nopcommerce.pageObjects.user.UserCheckoutPageObject;
import com.nopcommerce.pageObjects.user.UserCustomerInfoPageObject;
import com.nopcommerce.pageObjects.user.UserHomePageObject;
import com.nopcommerce.pageObjects.user.UserLoginPageObject;
import com.nopcommerce.pageObjects.user.UserOrderCompletedPageObject;
import com.nopcommerce.pageObjects.user.UserOrdersPageObject;
import com.nopcommerce.pageObjects.user.UserProductDetailPageObject;
import com.nopcommerce.pageObjects.user.UserProductListPageObject;
import com.nopcommerce.pageObjects.user.UserRegisterPageObject;
import com.nopcommerce.pageObjects.user.UserShoppingCartPageObject;

import commons.BaseTest;

public class UserOrder extends BaseTest {
	private WebDriver driver;
	UserHomePageObject userHomePage;
	UserRegisterPageObject userRegisterPage;
	UserLoginPageObject userLoginPage;
	UserProductListPageObject userProductListPage;
	UserProductDetailPageObject userProductDetailPage;
	UserShoppingCartPageObject userShoppingCartPage;
	UserCheckoutPageObject userCheckoutPage;
	UserOrderCompletedPageObject userOrderCompletedPage;
	UserCustomerInfoPageObject userCustomerInfoPage;
	UserOrdersPageObject userOrdersPage;

	String validEmail, productTitle;
	String registerFirstName = "Anh Hoa";
	String registerLastName = "Mai Nguyen";
	String password = "123456";
	String country = "Viet Nam", city = "Ha Noi", address1 = "123 Tran Phu", address2 = "123 Hoang Dieu";
	String zipCode = "10000", phoneNumber = "0902152334";

	String locatorFirstName = "FirstName";
	String locatorLastName = "LastName";
	String locatorEmail = "Email";
	String locatorPassword = "Password";
	String locatorConfirmPassword = "ConfirmPassword";

	String processor = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]", ram = "8GB [+$60.00]";
	String confirmAddedProduct = "The product has been added to your shopping cart";
	String productInCart = "Shopping cart (1)", countProduct = "There are 1 item(s) in your cart.";
	String attributesProduct = "Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\nRAM: 8GB [+$60.00]\nHDD: 400 GB [+$100.00]\nOS: Vista Premium [+$60.00]\nSoftware: Microsoft Office [+$50.00]\nSoftware: Acrobat Reader [+$10.00]\nSoftware: Total Commander [+$5.00]";
	String priceProduct = "Unit price: $1,500.00", quantityProduct = "Quantity: 1", totalProduct = "Sub-Total: $1,500.00";

	String processorEdit = "2.2 GHz Intel Pentium Dual-Core E2200", ramEdit = "4GB [+$20.00]";
	String priceProductEdited = "Unit price: $1,320.00", quantityProductEdited = "Quantity: 2", totalProductEdited = "Sub-Total: $2,640.00";
	String productEditedInCart = "Shopping cart (2)", countProductEdited = "There are 2 item(s) in your cart.";
	String attributesProductEdited = "Processor: 2.2 GHz Intel Pentium Dual-Core E2200\nRAM: 4GB [+$20.00]\nHDD: 320 GB\nOS: Vista Home [+$50.00]\nSoftware: Microsoft Office [+$50.00]";

	String paymentInfo = "Mail Personal or Business Check, Cashier's Check or money order to:\n\nNOP SOLUTIONS\nyour address here,\nNew York, NY 10001\nUSA\nNotice that if you pay by Personal or Business Check, your order may be held for up to 10 days after we receive your check to allow enough time for the check to clear. If you want us to ship faster upon receipt of your payment, then we recommend your send a money order or Cashier's check.\nP.S. You can edit this text from admin panel.";

	String orderNumber;
	String cardName = "Tillman Leuschke", cardNumber = "4539454156792427", monthExprationCard = "07", yearExprationCard = "2028", cardCode = "787";

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getWebDriver(browser);

		validEmail = "anhhoa" + randomInt() + "@gmail.com";
		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = (UserRegisterPageObject) userHomePage.openUserDynamicHeaderLinks(driver, "ico-register");

		userRegisterPage.inputValueToDynamicTextbox(driver, locatorFirstName, registerFirstName);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorLastName, registerLastName);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorEmail, validEmail);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorPassword, password);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorConfirmPassword, password);

		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getTextConfirmRegisterSuccess(), "Your registration completed");
		if (userRegisterPage.isDisplayedIconLogIn()) {		
			userLoginPage = (UserLoginPageObject) userRegisterPage.openUserDynamicHeaderLinks(driver, "ico-login");
		} else {
			userHomePage = (UserHomePageObject) userRegisterPage.openUserDynamicHeaderLinks(driver, "ico-logout");
			userLoginPage = (UserLoginPageObject) userHomePage.openUserDynamicHeaderLinks(driver, "ico-login");
		}
		
		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());

		userHomePage.openUserDynamicHeaderLinks(driver, "ico-logout");

	}

	@Test
	public void TC01_Add_Product_To_Cart() {
		userLoginPage = (UserLoginPageObject) userHomePage.openUserDynamicHeaderLinks(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());

		userProductListPage = (UserProductListPageObject) userHomePage.openProductListPage(driver, "Computers", "Desktops");
		userProductDetailPage = userProductListPage.clickToProductTitle("Build your own computer");

		userProductDetailPage.selectTextOfDynamicDropdown(driver, "product_attribute_1", processor);
		userProductDetailPage.selectTextOfDynamicDropdown(driver, "product_attribute_2", ram);
		userProductDetailPage.checkToDefaultCheckboxRadio(driver, "product_attribute_3_7");
		userProductDetailPage.checkToDefaultCheckboxRadio(driver, "product_attribute_4_9");
		userProductDetailPage.checkToDefaultCheckboxRadio(driver, "product_attribute_5_10");
		userProductDetailPage.checkToDefaultCheckboxRadio(driver, "product_attribute_5_11");
		userProductDetailPage.checkToDefaultCheckboxRadio(driver, "product_attribute_5_12");

		userProductDetailPage.clickToAddToCartButton();
		Assert.assertEquals(userProductDetailPage.getConfirmAddedProduct(), confirmAddedProduct);
		userProductDetailPage.clickToCloseButtonInNotification(driver);

		Assert.assertEquals(userProductDetailPage.verifyProductInCart("ico-cart"), productInCart);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("count"), countProduct);
		Assert.assertTrue(userProductDetailPage.isDisplayedNameProduct());
		Assert.assertEquals(userProductDetailPage.getInfoProduct("attributes"), attributesProduct);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("price"), priceProduct);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("quantity"), quantityProduct);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("totals"), totalProduct);

		userHomePage = (UserHomePageObject) userProductDetailPage.openUserDynamicHeaderLinks(driver, "ico-logout");
	}

	@Test
	public void TC02_Edit_Product_In_Shopping_Cart() {
		userLoginPage = (UserLoginPageObject) userHomePage.openUserDynamicHeaderLinks(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());

		userShoppingCartPage = (UserShoppingCartPageObject) userProductDetailPage.openUserDynamicHeaderLinks(driver, "ico-cart");
		userShoppingCartPage.clickToEditButton();

		userProductDetailPage.selectTextOfDynamicDropdown(driver, "product_attribute_1", processorEdit);
		userProductDetailPage.selectTextOfDynamicDropdown(driver, "product_attribute_2", ramEdit);
		userProductDetailPage.checkToDefaultCheckboxRadio(driver, "product_attribute_3_6");
		userProductDetailPage.checkToDefaultCheckboxRadio(driver, "product_attribute_4_8");
		userProductDetailPage.checkToDefaultCheckboxRadio(driver, "product_attribute_5_10");
		userProductDetailPage.uncheckToDefaultCheckboxRadio(driver, "product_attribute_5_11");
		userProductDetailPage.uncheckToDefaultCheckboxRadio(driver, "product_attribute_5_12");

		userProductDetailPage.inputValueToDynamicTextbox(driver, "product_enteredQuantity_1", "2");
		userProductDetailPage.isPageLoadedSuccess(driver);
		Assert.assertEquals(userProductDetailPage.getPriceProduct(), "$1,320.00");

		userProductDetailPage.clickToUpdateButton();
		Assert.assertEquals(userProductDetailPage.getConfirmAddedProduct(), confirmAddedProduct);
		userProductDetailPage.clickToCloseButtonInNotification(driver);

		Assert.assertEquals(userProductDetailPage.verifyProductInCart("ico-cart"), productEditedInCart);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("count"), countProductEdited);
		Assert.assertTrue(userProductDetailPage.isDisplayedNameProduct());
		Assert.assertEquals(userProductDetailPage.getInfoProduct("attributes"), attributesProductEdited);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("price"), priceProductEdited);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("quantity"), quantityProductEdited);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("totals"), totalProductEdited);

	}

	@Test
	public void TC03_Remove_From_Cart() {
		userShoppingCartPage = (UserShoppingCartPageObject) userProductDetailPage.openUserDynamicHeaderLinks(driver, "ico-cart");
		userShoppingCartPage.clickToRemoveButton();
		Assert.assertTrue(userShoppingCartPage.verifyNoData());
		userHomePage = (UserHomePageObject) userShoppingCartPage.openUserDynamicHeaderLinks(driver, "ico-logout");

	}

	@Test
	public void TC04_Update_Shopping_Cart() {
		userLoginPage = (UserLoginPageObject) userHomePage.openUserDynamicHeaderLinks(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());

		userProductListPage = (UserProductListPageObject) userHomePage.openProductListPage(driver, "Computers", "Desktops");
		userProductDetailPage = userProductListPage.clickToProductTitle("Lenovo IdeaCentre 600 All-in-One PC");

		userProductDetailPage.clickToAddToCartButton();
		Assert.assertEquals(userProductDetailPage.getConfirmAddedProduct(), confirmAddedProduct);
		userProductDetailPage.clickToCloseButtonInNotification(driver);

		Assert.assertEquals(userProductDetailPage.verifyProductInCart("ico-cart"), productInCart);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("count"), countProduct);

		userShoppingCartPage = (UserShoppingCartPageObject) userProductDetailPage.openUserDynamicHeaderLinks(driver, "ico-cart");
		userShoppingCartPage.inputQuantityProduct("5");
		userShoppingCartPage.clickToUpdateShoppingCart();
		userShoppingCartPage.isPageLoadedSuccess(driver);
		Assert.assertEquals(userShoppingCartPage.getSubTotalOfProduct(), "$2,500.00");
		
		userShoppingCartPage.clickToRemoveButton();
		Assert.assertTrue(userShoppingCartPage.verifyNoData());

		userHomePage = (UserHomePageObject) userShoppingCartPage.openUserDynamicHeaderLinks(driver, "ico-logout");
	}

	@Test
	public void TC05_Checkout_Order_Payment_Method_By_Cheque() {
		userLoginPage = (UserLoginPageObject) userHomePage.openUserDynamicHeaderLinks(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());

		userProductListPage = (UserProductListPageObject) userHomePage.openProductListPage(driver, "Computers", "Notebooks");
		userProductDetailPage = userProductListPage.clickToProductTitle("Apple MacBook Pro 13-inch");

		userProductDetailPage.clickToAddToCartButton();
		Assert.assertEquals(userProductDetailPage.getConfirmAddedProduct(), confirmAddedProduct);
		userProductDetailPage.clickToCloseButtonInNotification(driver);

		Assert.assertEquals(userProductDetailPage.verifyProductInCart("ico-cart"), productEditedInCart);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("count"), countProductEdited);

		userShoppingCartPage = (UserShoppingCartPageObject) userProductDetailPage.openUserDynamicHeaderLinks(driver, "ico-cart");
		userShoppingCartPage.selectTextOfDynamicDropdown(driver, "checkout_attribute_1", "No");
		userShoppingCartPage.checkToAgreeCheckbox();
		userCheckoutPage = (UserCheckoutPageObject) userShoppingCartPage.clickToCheckoutButton();

		userCheckoutPage.uncheckToCheckboxRadioButton("ShipToSameAddress");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_FirstName", registerFirstName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_LastName", registerLastName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_Email", validEmail);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "BillingNewAddress.CountryId", country);
		userCheckoutPage.isPageLoadedSuccess(driver);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "BillingNewAddress.StateProvinceId", "Other");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_City", city);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_Address1", address1);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_ZipPostalCode", zipCode);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_PhoneNumber", phoneNumber);
		userCheckoutPage.clickToContinueButton("billing-buttons-container");

		userCheckoutPage.selectTextOfDynamicDropdown(driver, "shipping_address_id", "New Address");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_FirstName", registerFirstName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_LastName", registerLastName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_Email", validEmail);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "ShippingNewAddress.CountryId", country);
		userCheckoutPage.isPageLoadedSuccess(driver);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "ShippingNewAddress.StateProvinceId", "Other");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_City", city);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_Address1", address2);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_ZipPostalCode", zipCode);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_PhoneNumber", phoneNumber);
		userCheckoutPage.clickToContinueButton("shipping-buttons-container");

		userCheckoutPage.checkToCheckboxRadioButton("shippingoption_0");
		userCheckoutPage.clickToContinueButton("shipping-method-buttons-container");

		userCheckoutPage.checkToCheckboxRadioButton("paymentmethod_0");
		userCheckoutPage.clickToContinueButton("payment-method-buttons-container");

		Assert.assertEquals(userCheckoutPage.getPaymentInfo(), paymentInfo);
		userCheckoutPage.clickToContinueButton("payment-info-buttons-container");

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("billing-info"), "Billing Address");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "address1"), address1);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "country"), country);

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("shipping-info"), "Shipping Address");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "address1"), address2);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "country"), country);

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("payment-method-info"), "Payment");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("payment-method-info", "payment-method"), "Payment Method: Check / Money Order");

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("shipping-method-info"), "Shipping");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-method-info", "shipping-method"), "Shipping Method: Ground");

		Assert.assertEquals(userCheckoutPage.getProductName(driver), "Apple MacBook Pro 13-inch");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("sku-number"), "AP_MBP_13");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-unit-price"), "$1,800.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-quantity"), "2");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-subtotal"), "$3,600.00");
		Assert.assertEquals(userCheckoutPage.getInfoGiftWrapping(), "Gift wrapping: No");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("order-subtotal"), "$3,600.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("shipping-cost"), "$0.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("tax-value"), "$0.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("order-total"), "$3,600.00");

		userOrderCompletedPage = (UserOrderCompletedPageObject) userCheckoutPage.clickToConfirmButton();

		userOrderCompletedPage.isPageLoadedSuccess(driver);
		Assert.assertEquals(userOrderCompletedPage.getPageTitleOfOrderCompleted(), "Thank you");
		Assert.assertEquals(userOrderCompletedPage.getTitleOfOrderCompleted(), "Your order has been successfully processed!");
		orderNumber = userOrderCompletedPage.getOrderNumber().substring(14);
		Assert.assertTrue(userOrderCompletedPage.isDisplayedOrderNumber());

		userCustomerInfoPage = (UserCustomerInfoPageObject) userOrderCompletedPage.openUserDynamicFooterLinks(driver, "My account");
		Assert.assertTrue(userCustomerInfoPage.verifyDisplayDynamicTitlePage(driver, "Customer info"));

		userOrdersPage = (UserOrdersPageObject) userCustomerInfoPage.openUserDynamicPageOfMyAccount(driver, "Orders");

		Assert.assertTrue(userOrdersPage.getOrderNumber().contains(orderNumber));
		userOrdersPage.clickToDetailsButton(orderNumber);
		userOrdersPage.isPageLoadedSuccess(driver);

		Assert.assertTrue(userOrdersPage.getOrderNumberDetails().contains(orderNumber));
		//Assert.assertEquals(userOrdersPage.verifyDisplayedOrderInfo("order-date"), getToday());
		Assert.assertEquals(userOrdersPage.verifyDisplayedOrderInfo("order-status"), "Order Status: Pending");
		Assert.assertEquals(userOrdersPage.verifyDisplayedOrderInfo("order-total"), "Order Total: $3,600.00");

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("billing-info"), "Billing Address");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "address1"), address1);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "country"), country);

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("shipping-info"), "Shipping Address");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "address1"), address2);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "country"), country);

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("payment-method-info"), "Payment");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("payment-method-info", "payment-method"), "Payment Method: Check / Money Order");

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("shipping-method-info"), "Shipping");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-method-info", "shipping-method"), "Shipping Method: Ground");

		Assert.assertEquals(userOrdersPage.getProductName(), "Apple MacBook Pro 13-inch");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("sku-number"), "AP_MBP_13");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-unit-price"), "$1,800.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-quantity"), "2");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-subtotal"), "$3,600.00");
		Assert.assertEquals(userOrdersPage.getInfoGiftWrapping(), "Gift wrapping: No");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Sub-Total"), "$3,600.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Shipping"), "$0.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Tax"), "$0.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Order Total"), "$3,600.00");
		
		userHomePage = (UserHomePageObject) userOrdersPage.openUserDynamicHeaderLinks(driver, "ico-logout");

	}
	
	@Test
	public void TC06_Checkout_Order_Payment_Method_By_Card() {
		userLoginPage = (UserLoginPageObject) userHomePage.openUserDynamicHeaderLinks(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());

		userProductListPage = (UserProductListPageObject) userHomePage.openProductListPage(driver, "Computers", "Notebooks");
		userProductDetailPage = userProductListPage.clickToProductTitle("Apple MacBook Pro 13-inch");

		userProductDetailPage.clickToAddToCartButton();
		Assert.assertEquals(userProductDetailPage.getConfirmAddedProduct(), confirmAddedProduct);
		userProductDetailPage.clickToCloseButtonInNotification(driver);

		Assert.assertEquals(userProductDetailPage.verifyProductInCart("ico-cart"), productEditedInCart);
		Assert.assertEquals(userProductDetailPage.getInfoProduct("count"), countProductEdited);

		userShoppingCartPage = (UserShoppingCartPageObject) userProductDetailPage.openUserDynamicHeaderLinks(driver, "ico-cart");
		userShoppingCartPage.selectTextOfDynamicDropdown(driver, "checkout_attribute_1", "No");
		userShoppingCartPage.checkToAgreeCheckbox();
		userCheckoutPage = (UserCheckoutPageObject) userShoppingCartPage.clickToCheckoutButton();

		userCheckoutPage.uncheckToCheckboxRadioButton("ShipToSameAddress");
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "billing_address_id", "New Address");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_FirstName", registerFirstName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_LastName", registerLastName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_Email", validEmail);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "BillingNewAddress.CountryId", country);
		userCheckoutPage.isPageLoadedSuccess(driver);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "BillingNewAddress.StateProvinceId", "Other");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_City", city);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_Address1", address1);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_ZipPostalCode", zipCode);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_PhoneNumber", phoneNumber);
		userCheckoutPage.clickToContinueButton("billing-buttons-container");

		userCheckoutPage.selectTextOfDynamicDropdown(driver, "shipping_address_id", "New Address");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_FirstName", registerFirstName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_LastName", registerLastName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_Email", validEmail);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "ShippingNewAddress.CountryId", country);
		userCheckoutPage.isPageLoadedSuccess(driver);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "ShippingNewAddress.StateProvinceId", "Other");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_City", city);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_Address1", address2);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_ZipPostalCode", zipCode);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_PhoneNumber", phoneNumber);
		userCheckoutPage.clickToContinueButton("shipping-buttons-container");

		userCheckoutPage.checkToCheckboxRadioButton("shippingoption_0");
		userCheckoutPage.clickToContinueButton("shipping-method-buttons-container");

		userCheckoutPage.checkToCheckboxRadioButton("paymentmethod_1");
		userCheckoutPage.clickToContinueButton("payment-method-buttons-container");

		userCheckoutPage.getPaymentInfoWithCard(cardName, cardNumber, cardCode, monthExprationCard, yearExprationCard);
		userCheckoutPage.clickToContinueButton("payment-info-buttons-container");

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("billing-info"), "Billing Address");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "address1"), address1);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "country"), country);

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("shipping-info"), "Shipping Address");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "address1"), address2);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "country"), country);

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("payment-method-info"), "Payment");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("payment-method-info", "payment-method"), "Payment Method: Credit Card");

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("shipping-method-info"), "Shipping");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-method-info", "shipping-method"), "Shipping Method: Ground");

		Assert.assertEquals(userCheckoutPage.getProductName(driver), "Apple MacBook Pro 13-inch");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("sku-number"), "AP_MBP_13");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-unit-price"), "$1,800.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-quantity"), "2");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-subtotal"), "$3,600.00");
		Assert.assertEquals(userCheckoutPage.getInfoGiftWrapping(), "Gift wrapping: No");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("order-subtotal"), "$3,600.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("shipping-cost"), "$0.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("tax-value"), "$0.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("order-total"), "$3,600.00");

		userOrderCompletedPage = (UserOrderCompletedPageObject) userCheckoutPage.clickToConfirmButton();

		userOrderCompletedPage.isPageLoadedSuccess(driver);
		Assert.assertEquals(userOrderCompletedPage.getPageTitleOfOrderCompleted(), "Thank you");
		Assert.assertEquals(userOrderCompletedPage.getTitleOfOrderCompleted(), "Your order has been successfully processed!");
		orderNumber = userOrderCompletedPage.getOrderNumber().substring(14);
		Assert.assertTrue(userOrderCompletedPage.isDisplayedOrderNumber());

		userCustomerInfoPage = (UserCustomerInfoPageObject) userOrderCompletedPage.openUserDynamicFooterLinks(driver, "My account");
		Assert.assertTrue(userCustomerInfoPage.verifyDisplayDynamicTitlePage(driver, "Customer info"));

		userOrdersPage = (UserOrdersPageObject) userCustomerInfoPage.openUserDynamicPageOfMyAccount(driver, "Orders");

		Assert.assertTrue(userOrdersPage.getOrderNumber().contains(orderNumber));
		userOrdersPage.clickToDetailsButton(orderNumber);
		userOrdersPage.isPageLoadedSuccess(driver);

		Assert.assertTrue(userOrdersPage.getOrderNumberDetails().contains(orderNumber));
		Assert.assertEquals(userOrdersPage.verifyDisplayedOrderInfo("order-status"), "Order Status: Pending");
		Assert.assertEquals(userOrdersPage.verifyDisplayedOrderInfo("order-total"), "Order Total: $3,600.00");

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("billing-info"), "Billing Address");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "address1"), address1);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "country"), country);

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("shipping-info"), "Shipping Address");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "address1"), address2);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "country"), country);

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("payment-method-info"), "Payment");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("payment-method-info", "payment-method"), "Payment Method: Credit Card");

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("shipping-method-info"), "Shipping");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-method-info", "shipping-method"), "Shipping Method: Ground");

		Assert.assertEquals(userOrdersPage.getProductName(), "Apple MacBook Pro 13-inch");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("sku-number"), "AP_MBP_13");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-unit-price"), "$1,800.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-quantity"), "2");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-subtotal"), "$3,600.00");
		Assert.assertEquals(userOrdersPage.getInfoGiftWrapping(), "Gift wrapping: No");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Sub-Total"), "$3,600.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Shipping"), "$0.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Tax"), "$0.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Order Total"), "$3,600.00");

	}
	
	@Test
	public void TC07_Re_Order() {
		userShoppingCartPage = (UserShoppingCartPageObject) userOrdersPage.clickToReOrderButton();
		userShoppingCartPage.inputQuantityProduct("10");
		userShoppingCartPage.clickToUpdateShoppingCart();
		userShoppingCartPage.isPageLoadedSuccess(driver);
		Assert.assertEquals(userShoppingCartPage.getSubTotalOfProduct(), "$18,000.00");
		
		userShoppingCartPage.selectTextOfDynamicDropdown(driver, "checkout_attribute_1", "No");
		userShoppingCartPage.checkToAgreeCheckbox();
		userCheckoutPage = (UserCheckoutPageObject) userShoppingCartPage.clickToCheckoutButton();

		userCheckoutPage.uncheckToCheckboxRadioButton("ShipToSameAddress");
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "billing_address_id", "New Address");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_FirstName", registerFirstName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_LastName", registerLastName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_Email", validEmail);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "BillingNewAddress.CountryId", country);
		userCheckoutPage.isPageLoadedSuccess(driver);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "BillingNewAddress.StateProvinceId", "Other");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_City", city);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_Address1", "119 Tran Duy Hung");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_ZipPostalCode", zipCode);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "BillingNewAddress_PhoneNumber", phoneNumber);
		userCheckoutPage.clickToContinueButton("billing-buttons-container");

		userCheckoutPage.selectTextOfDynamicDropdown(driver, "shipping_address_id", "New Address");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_FirstName", registerFirstName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_LastName", registerLastName);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_Email", validEmail);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "ShippingNewAddress.CountryId", country);
		userCheckoutPage.isPageLoadedSuccess(driver);
		userCheckoutPage.selectTextOfDynamicDropdown(driver, "ShippingNewAddress.StateProvinceId", "Other");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_City", city);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_Address1", "6 Quang Trung");
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_ZipPostalCode", zipCode);
		userCheckoutPage.inputValueToDynamicTextbox(driver, "ShippingNewAddress_PhoneNumber", phoneNumber);
		userCheckoutPage.clickToContinueButton("shipping-buttons-container");

		userCheckoutPage.checkToCheckboxRadioButton("shippingoption_1");
		userCheckoutPage.clickToContinueButton("shipping-method-buttons-container");

		userCheckoutPage.checkToCheckboxRadioButton("paymentmethod_1");
		userCheckoutPage.clickToContinueButton("payment-method-buttons-container");

		userCheckoutPage.getPaymentInfoWithCard(cardName, cardNumber, cardCode, monthExprationCard, yearExprationCard);
		userCheckoutPage.clickToContinueButton("payment-info-buttons-container");

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("billing-info"), "Billing Address");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "address1"), "119 Tran Duy Hung");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("billing-info", "country"), country);

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("shipping-info"), "Shipping Address");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "address1"), "6 Quang Trung");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-info", "country"), country);

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("payment-method-info"), "Payment");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("payment-method-info", "payment-method"), "Payment Method: Credit Card");

		Assert.assertEquals(userCheckoutPage.getDynamicTitleOfConfirmOrder("shipping-method-info"), "Shipping");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfConfirmOrder("shipping-method-info", "shipping-method"), "Shipping Method: Next Day Air");

		Assert.assertEquals(userCheckoutPage.getProductName(driver), "Apple MacBook Pro 13-inch");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("sku-number"), "AP_MBP_13");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-unit-price"), "$1,800.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-quantity"), "10");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProduct("product-subtotal"), "$18,000.00");
		Assert.assertEquals(userCheckoutPage.getInfoGiftWrapping(), "Gift wrapping: No");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("order-subtotal"), "$18,000.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("shipping-cost"), "$0.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("tax-value"), "$0.00");
		Assert.assertEquals(userCheckoutPage.getDynamicInfoOfProductPrice("order-total"), "$18,000.00");

		userOrderCompletedPage = (UserOrderCompletedPageObject) userCheckoutPage.clickToConfirmButton();

		userOrderCompletedPage.isPageLoadedSuccess(driver);
		Assert.assertEquals(userOrderCompletedPage.getPageTitleOfOrderCompleted(), "Thank you");
		Assert.assertEquals(userOrderCompletedPage.getTitleOfOrderCompleted(), "Your order has been successfully processed!");
		orderNumber = userOrderCompletedPage.getOrderNumber().substring(14);
		Assert.assertTrue(userOrderCompletedPage.isDisplayedOrderNumber());

		userCustomerInfoPage = (UserCustomerInfoPageObject) userOrderCompletedPage.openUserDynamicFooterLinks(driver, "My account");
		Assert.assertTrue(userCustomerInfoPage.verifyDisplayDynamicTitlePage(driver, "Customer info"));

		userOrdersPage = (UserOrdersPageObject) userCustomerInfoPage.openUserDynamicPageOfMyAccount(driver, "Orders");

		Assert.assertTrue(userOrdersPage.getOrderNumber().contains(orderNumber));
		userOrdersPage.clickToDetailsButton(orderNumber);
		userOrdersPage.isPageLoadedSuccess(driver);

		Assert.assertTrue(userOrdersPage.getOrderNumberDetails().contains(orderNumber));
		Assert.assertEquals(userOrdersPage.verifyDisplayedOrderInfo("order-status"), "Order Status: Pending");
		Assert.assertEquals(userOrdersPage.verifyDisplayedOrderInfo("order-total"), "Order Total: $18,000.00");

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("billing-info"), "Billing Address");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "address1"), "119 Tran Duy Hung");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("billing-info", "country"), country);

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("shipping-info"), "Shipping Address");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "name"), registerFirstName + " " + registerLastName);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "email"), "Email: " + validEmail);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "phone"), "Phone: " + phoneNumber);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "address1"), "6 Quang Trung");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "city-state-zip"), city + "," + zipCode);
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-info", "country"), country);

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("payment-method-info"), "Payment");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("payment-method-info", "payment-method"), "Payment Method: Credit Card");

		Assert.assertEquals(userOrdersPage.getDynamicTitleOfConfirmOrder("shipping-method-info"), "Shipping");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfConfirmOrder("shipping-method-info", "shipping-method"), "Shipping Method: Next Day Air");

		Assert.assertEquals(userOrdersPage.getProductName(), "Apple MacBook Pro 13-inch");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("sku-number"), "AP_MBP_13");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-unit-price"), "$1,800.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-quantity"), "10");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProduct("product-subtotal"), "$18,000.00");
		Assert.assertEquals(userOrdersPage.getInfoGiftWrapping(), "Gift wrapping: No");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Sub-Total"), "$18,000.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Shipping"), "$0.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Tax"), "$0.00");
		Assert.assertEquals(userOrdersPage.getDynamicInfoOfProductPrice("Order Total"), "$18,000.00");

		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

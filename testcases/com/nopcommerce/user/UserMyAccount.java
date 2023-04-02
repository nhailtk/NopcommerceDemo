package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.user.PageGeneratorManager;
import com.nopcommerce.pageObjects.user.UserAddressPageObject;
import com.nopcommerce.pageObjects.user.UserChangePasswordPageObject;
import com.nopcommerce.pageObjects.user.UserCustomerInfoPageObject;
import com.nopcommerce.pageObjects.user.UserHomePageObject;
import com.nopcommerce.pageObjects.user.UserLoginPageObject;
import com.nopcommerce.pageObjects.user.UserProductDetailPageObject;
import com.nopcommerce.pageObjects.user.UserProductListPageObject;
import com.nopcommerce.pageObjects.user.UserProductReviewsPageObject;
import com.nopcommerce.pageObjects.user.UserRegisterPageObject;

import commons.BaseTest;

public class UserMyAccount extends BaseTest {
	private WebDriver driver;
	UserHomePageObject userHomePage;
	UserRegisterPageObject userRegisterPage;
	UserLoginPageObject userLoginPage;
	UserCustomerInfoPageObject userCustomerInfoPage;
	UserAddressPageObject userAddressPage;
	UserChangePasswordPageObject userChangePasswordPage;
	UserProductListPageObject userProductListPage;
	UserProductDetailPageObject userProductDetailPage;
	UserProductReviewsPageObject userProductReviewsPage;

	String locatorFirstName = "FirstName";
	String locatorLastName = "LastName";
	String locatorEmail = "Email";
	String locatorPassword = "Password";
	String locatorConfirmPassword = "ConfirmPassword";
	String locatorGender = "gender-female";
	String locatorCompany = "Company";
	String locatorDay = "DateOfBirthDay", locatorMonth = "DateOfBirthMonth", locatorYear = "DateOfBirthYear";

	String registerFirstName = "Anh Hoa";
	String registerLastName = "Mai Nguyen";
	String wrongEmail = "abc123@gmail@com";
	String unregisteredEmail = "abc135@gmail.com";
	String password = "123456";
	String validEmail, editEmail;

	String editFirstName = "Automaiton";
	String editLastName = "FC";
	String dateOfBirthDay = "1", dateOfBirthMonth = "1", dateOfBirthYear = "1999";
	String companyName = "Automation FC";
	String updateInfoSuccessful = "The customer info has been updated successfully.";
	String updateAddressSuccessful = "The new address has been added successfully.";
	String updatePasswordSuccessful = "Password was changed";

	String city = "Ha Noi", country = "Viet Nam", address1 = "30 Tran Phu", address2 = "40 Quang Trung", zipCode = "10000", phoneNumber = "09717427374", faxNumber = "0971727375";
	String locatorAddressFirstName = "Address_FirstName";
	String locatorAddressLastName = "Address_LastName";
	String locatorAddressEmail = "Address_Email";
	String locatorAddressCompany = "Address_Company";
	String locatorAddressCity = "Address_City";
	String locatorAddressAddress1 = "Address_Address1";
	String locatorAddressAddress2 = "Address_Address2";
	String locatorAddressZipCode = "Address_ZipPostalCode";
	String locatorAddressPhoneNumber = "Address_PhoneNumber";
	String locatorAddressFaxNumber = "Address_FaxNumber";
	String locatorAddressCountry = "Address.CountryId";
	String locatorAddressStateProvince = "Address.StateProvinceId";

	String newPassword = "123457";
	String locatorChangePasswordOldPassword = "OldPassword";
	String locatorChangePasswordNewPassword = "NewPassword";
	String locatorChangePasswordConfirmPassword = "ConfirmNewPassword";

	String locatorAddProductReviewTitle = "AddProductReview_Title";
	String locatorAddProductReviewContent = "AddProductReview_ReviewText";
	String textAddProductReviewTitle = "Product Review Title: ";
	String textAddProductReviewContent = "Product Review Content: ";
	String productTitle;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getWebDriver(browser);
		validEmail = "anhhoa" + randomInt() + "@gmail.com";
		editEmail = "editEmail" + randomInt() + "@gmail.com";
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = (UserRegisterPageObject) userHomePage.openDynamicHeaderLinks(driver, "ico-register");

		userRegisterPage.inputValueToDynamicTextbox(driver, locatorFirstName, registerFirstName);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorLastName, registerLastName);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorEmail, validEmail);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorPassword, password);
		userRegisterPage.inputValueToDynamicTextbox(driver, locatorConfirmPassword, password);

		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getTextConfirmRegisterSuccess(), "Your registration completed");

		// userHomePage = (UserHomePageObject) userRegisterPage.openDynamicHeaderLinks(driver, "ico-logout");

		userLoginPage = (UserLoginPageObject) userRegisterPage.openDynamicHeaderLinks(driver, "ico-login");

		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());
	}

	@Test
	public void TC_01_My_Account_Update_Customer_Info() {
		userCustomerInfoPage = (UserCustomerInfoPageObject) userHomePage.openDynamicHeaderLinks(driver, "ico-account");
		Assert.assertTrue(userCustomerInfoPage.verifyDisplayDynamicTitlePage(driver, "Customer info"));

		userCustomerInfoPage.selectValueOfGenderRadioButton();

		userCustomerInfoPage.inputValueToDynamicTextbox(driver, locatorFirstName, editFirstName);
		userCustomerInfoPage.inputValueToDynamicTextbox(driver, locatorLastName, editLastName);

		userCustomerInfoPage.selectValueOfDynamicDropdown(driver, locatorDay, dateOfBirthDay);
		userCustomerInfoPage.selectValueOfDynamicDropdown(driver, locatorMonth, dateOfBirthMonth);
		userCustomerInfoPage.selectValueOfDynamicDropdown(driver, locatorYear, dateOfBirthYear);
		userCustomerInfoPage.inputValueToDynamicTextbox(driver, locatorEmail, editEmail);
		userCustomerInfoPage.inputValueToDynamicTextbox(driver, locatorCompany, companyName);

		userCustomerInfoPage.clickToSaveButton();

		Assert.assertTrue(userCustomerInfoPage.verfifyUpdateSuccess(driver, updateInfoSuccessful));
		userCustomerInfoPage.clickToCloseButtonInNotification(driver);

		Assert.assertTrue(userCustomerInfoPage.verifyValueOfGender());
		Assert.assertEquals(userCustomerInfoPage.vefifyValueOfDynamicTextbox(driver, locatorFirstName), editFirstName);
		Assert.assertEquals(userCustomerInfoPage.vefifyValueOfDynamicTextbox(driver, locatorLastName), editLastName);
		Assert.assertEquals(userCustomerInfoPage.vefifyValueOfDynamicDropdown(driver, locatorDay), dateOfBirthDay);
		Assert.assertEquals(userCustomerInfoPage.vefifyValueOfDynamicDropdown(driver, locatorMonth), "January");
		Assert.assertEquals(userCustomerInfoPage.vefifyValueOfDynamicDropdown(driver, locatorYear), dateOfBirthYear);
		Assert.assertEquals(userCustomerInfoPage.vefifyValueOfDynamicTextbox(driver, locatorEmail), editEmail);
		Assert.assertEquals(userCustomerInfoPage.vefifyValueOfDynamicTextbox(driver, locatorCompany), companyName);

	}

	@Test
	public void TC_02_My_Account_Add_Address() {
		userCustomerInfoPage = (UserCustomerInfoPageObject) userHomePage.openDynamicHeaderLinks(driver, "ico-account");
		Assert.assertTrue(userCustomerInfoPage.verifyDisplayDynamicTitlePage(driver, "Customer info"));

		userAddressPage = (UserAddressPageObject) userCustomerInfoPage.openDynamicPageOfMyAccount(driver, "Addresses");
		Assert.assertTrue(userAddressPage.verifyDisplayDynamicTitlePage(driver, "Addresses"));

		userAddressPage.clickToAddNewButton();

		userAddressPage.inputValueToDynamicTextbox(driver, locatorAddressFirstName, editFirstName);
		userAddressPage.inputValueToDynamicTextbox(driver, locatorAddressLastName, editLastName);
		userCustomerInfoPage.inputValueToDynamicTextbox(driver, locatorAddressEmail, editEmail);
		userCustomerInfoPage.inputValueToDynamicTextbox(driver, locatorAddressCompany, companyName);
		userAddressPage.selectValueOfDynamicDropdown(driver, locatorAddressCountry, "82");
		userAddressPage.selectValueOfDynamicDropdown(driver, locatorAddressStateProvince, "0");
		userAddressPage.inputValueToDynamicTextbox(driver, locatorAddressCity, city);
		userAddressPage.inputValueToDynamicTextbox(driver, locatorAddressAddress1, address1);
		userAddressPage.inputValueToDynamicTextbox(driver, locatorAddressAddress2, address2);
		userAddressPage.inputValueToDynamicTextbox(driver, locatorAddressZipCode, zipCode);
		userAddressPage.inputValueToDynamicTextbox(driver, locatorAddressPhoneNumber, phoneNumber);
		userAddressPage.inputValueToDynamicTextbox(driver, locatorAddressFaxNumber, faxNumber);

		userAddressPage.clickToSaveButton();

		Assert.assertTrue(userAddressPage.verfifyUpdateSuccess(driver, updateAddressSuccessful));
		userAddressPage.clickToCloseButtonInNotification(driver);

		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("name"), editFirstName + " " + editLastName);
		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("email"), "Email: " + editEmail);
		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("phone"), "Phone number: " + phoneNumber);
		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("fax"), "Fax number: " + faxNumber);
		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("company"), companyName);
		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("address1"), address1);
		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("address2"), address2);
		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("city-state-zip"), city + ", " + zipCode);
		Assert.assertEquals(userAddressPage.getValueOfDynamicLabel("country"), country);

	}

	@Test
	public void TC_03_My_Account_Change_Password() {
		userCustomerInfoPage = (UserCustomerInfoPageObject) userHomePage.openDynamicHeaderLinks(driver, "ico-account");
		Assert.assertTrue(userCustomerInfoPage.verifyDisplayDynamicTitlePage(driver, "Customer info"));

		userChangePasswordPage = (UserChangePasswordPageObject) userCustomerInfoPage.openDynamicPageOfMyAccount(driver, "Change password");
		userChangePasswordPage.verifyDisplayDynamicTitlePage(driver, "Change password");

		userChangePasswordPage.inputValueToDynamicTextbox(driver, locatorChangePasswordOldPassword, password);
		userChangePasswordPage.inputValueToDynamicTextbox(driver, locatorChangePasswordNewPassword, newPassword);
		userChangePasswordPage.inputValueToDynamicTextbox(driver, locatorChangePasswordConfirmPassword, newPassword);

		userChangePasswordPage.clickToChangePasswordButton();

		Assert.assertTrue(userChangePasswordPage.verfifyUpdateSuccess(driver, updatePasswordSuccessful));
		userChangePasswordPage.clickToCloseButtonInNotification(driver);

		userHomePage = (UserHomePageObject) userChangePasswordPage.openDynamicHeaderLinks(driver, "ico-logout");

		userLoginPage = (UserLoginPageObject) userHomePage.openDynamicHeaderLinks(driver, "ico-login");

		userLoginPage.inputTextboxAndClickButton(editEmail, password);
		Assert.assertEquals(userLoginPage.getTextErrorWithInvalidValue(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		userLoginPage.inputTextboxAndClickButton(editEmail, newPassword);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());
	}

	@Test
	public void TC_04_My_Account_My_Product_Reviews() {
		userProductListPage = (UserProductListPageObject) userHomePage.openProductListPage();
		productTitle = userProductListPage.getProductTitle();
		userProductDetailPage = userProductListPage.clickToProductTitle();
		userProductReviewsPage = userProductDetailPage.clickAddReviewLink();

		userProductReviewsPage.inputValueToDynamicTextbox(driver, locatorAddProductReviewTitle, textAddProductReviewTitle + productTitle);
		userProductReviewsPage.inputValueToProductReviewContentTextArea(driver, textAddProductReviewContent + productTitle);
		userProductReviewsPage.clickSubmitReviewButton();
		
		Assert.assertTrue(userProductReviewsPage.verifyDisplayProductReviewTitle(textAddProductReviewTitle, productTitle));
		Assert.assertTrue(userProductReviewsPage.verifyDisplayProductReviewContent(textAddProductReviewContent, productTitle));
		
		userHomePage = (UserHomePageObject) userProductReviewsPage.openDynamicHeaderLinks(driver, "ico-logout");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

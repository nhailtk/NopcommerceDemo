package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.user.UserPageGeneratorManager;
import com.nopcommerce.pageObjects.user.UserHomePageObject;
import com.nopcommerce.pageObjects.user.UserLoginPageObject;
import com.nopcommerce.pageObjects.user.UserRegisterPageObject;
import com.nopcommerce.pageObjects.user.UserSeachPageObject;

import commons.BaseTest;

public class UserSearch extends BaseTest {
	private WebDriver driver;
	UserHomePageObject userHomePage;
	UserRegisterPageObject userRegisterPage;
	UserLoginPageObject userLoginPage;
	UserSeachPageObject userSearchPage;

	String urlUser = "";
	String validEmail;
	String registerFirstName = "Anh Hoa";
	String registerLastName = "Mai Nguyen";
	String password = "123456";

	String locatorFirstName = "FirstName";
	String locatorLastName = "LastName";
	String locatorEmail = "Email";
	String locatorPassword = "Password";
	String locatorConfirmPassword = "ConfirmPassword";

	String locatorSearchTextbox = "q";

	@Parameters({ "browser", "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String urlUser) {
		this.urlUser = urlUser;
		driver = getWebDriver(browserName, this.urlUser);

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

		userLoginPage = (UserLoginPageObject) userRegisterPage.openUserDynamicHeaderLinks(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = UserPageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());

	}

	@Test
	public void TC_01_Search_Empty_Data() {
		userHomePage.scrollToBottom(driver);
		userSearchPage = (UserSeachPageObject) userHomePage.openUserDynamicFooterLinks(driver, "Search");
		userSearchPage.clickToSearchButton();
		Assert.assertEquals(userSearchPage.verifyDisplayWarningText(), "Search term minimum length is 3 characters");
	}

	@Test
	public void TC_02_Search_Data_Not_Exist() {
		userHomePage.scrollToBottom(driver);
		userSearchPage = (UserSeachPageObject) userHomePage.openUserDynamicFooterLinks(driver, "Search");
		userSearchPage.inputValueToDynamicTextbox(driver, locatorSearchTextbox, "Macbook Pro 2050");
		userSearchPage.clickToSearchButton();
		Assert.assertEquals(userSearchPage.verifyNoResult(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_03_Search_Relative_Product_Name() {
		userHomePage.scrollToBottom(driver);
		userSearchPage = (UserSeachPageObject) userHomePage.openUserDynamicFooterLinks(driver, "Search");
		userSearchPage.inputValueToDynamicTextbox(driver, locatorSearchTextbox, "Lenovo");
		userSearchPage.clickToSearchButton();
		Assert.assertTrue(userSearchPage.verifyResultWithRelativeSearch());
	}

	@Test
	public void TC_04_Search_Absolute_Product_Name() {
		userHomePage.scrollToBottom(driver);
		userSearchPage = (UserSeachPageObject) userHomePage.openUserDynamicFooterLinks(driver, "Search");
		userSearchPage.inputValueToDynamicTextbox(driver, locatorSearchTextbox, "ThinkPad X1 Carbon");
		userSearchPage.clickToSearchButton();
		Assert.assertEquals(userSearchPage.verifyResultWithSearch(), "Lenovo Thinkpad X1 Carbon Laptop");
	}

	@Test
	public void TC_05_Advanced_Search_Parent_Categories() {
		userHomePage.scrollToBottom(driver);
		userSearchPage = (UserSeachPageObject) userHomePage.openUserDynamicFooterLinks(driver, "Search");
		userSearchPage.inputValueToDynamicTextbox(driver, locatorSearchTextbox, "Apple MacBook Pro");
		userSearchPage.actionWithCheckBox(true, "advs");
		userSearchPage.selectValueOfDynamicDropdown(driver, "cid", "1");
		userSearchPage.clickToSearchButton();
		Assert.assertEquals(userSearchPage.verifyNoResult(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_06_Advanced_Search_Sub_Categories() {
		userHomePage.scrollToBottom(driver);
		userSearchPage = (UserSeachPageObject) userHomePage.openUserDynamicFooterLinks(driver, "Search");
		userSearchPage.inputValueToDynamicTextbox(driver, locatorSearchTextbox, "Apple MacBook Pro");
		userSearchPage.actionWithCheckBox(true, "advs");
		userSearchPage.selectValueOfDynamicDropdown(driver, "cid", "1");
		userSearchPage.actionWithCheckBox(true, "isc");
		userSearchPage.clickToSearchButton();
		Assert.assertEquals(userSearchPage.verifyResultWithSearch(), "Apple MacBook Pro 13-inch");
	}

	@Test
	public void TC_07_Advanced_Search_Incorrect_Manufacturer() {
		userHomePage.scrollToBottom(driver);
		userSearchPage = (UserSeachPageObject) userHomePage.openUserDynamicFooterLinks(driver, "Search");
		userSearchPage.inputValueToDynamicTextbox(driver, locatorSearchTextbox, "Apple MacBook Pro");
		userSearchPage.actionWithCheckBox(true, "advs");
		userSearchPage.selectValueOfDynamicDropdown(driver, "cid", "1");
		userSearchPage.actionWithCheckBox(true, "isc");
		userSearchPage.selectValueOfDynamicDropdown(driver, "mid", "2");
		userSearchPage.clickToSearchButton();
		Assert.assertEquals(userSearchPage.verifyNoResult(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_08_Advanced_Search_Correct_Manufacturer() {
		userHomePage.scrollToBottom(driver);
		userSearchPage = (UserSeachPageObject) userHomePage.openUserDynamicFooterLinks(driver, "Search");
		userSearchPage.inputValueToDynamicTextbox(driver, locatorSearchTextbox, "Apple MacBook Pro");
		userSearchPage.actionWithCheckBox(true, "advs");
		userSearchPage.selectValueOfDynamicDropdown(driver, "cid", "1");
		userSearchPage.actionWithCheckBox(true, "isc");
		userSearchPage.selectValueOfDynamicDropdown(driver, "mid", "1");
		userSearchPage.clickToSearchButton();
		Assert.assertEquals(userSearchPage.verifyResultWithSearch(), "Apple MacBook Pro 13-inch");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}

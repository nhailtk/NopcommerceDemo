package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.user.PageGeneratorManager;
import com.nopcommerce.pageObjects.user.UserHomePageObject;
import com.nopcommerce.pageObjects.user.UserLoginPageObject;
import com.nopcommerce.pageObjects.user.UserRegisterPageObject;

import commons.BaseTest;

public class UserLogin extends BaseTest {
	private WebDriver driver;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;

	String locatorFirstName = "FirstName";
	String locatorLastName = "LastName";
	String locatorEmail = "Email";
	String locatorPassword = "Password";
	String locatorConfirmPassword = "ConfirmPassword";

	String registerFirstName = "Anh Hoa";
	String registerLastName = "Mai Nguyen";
	String wrongEmail = "abc123@gmail@com";
	String unregisteredEmail = "abc135@gmail.com";
	String password = "123456";
	String validEmail;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getWebDriver(browser);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		validEmail = "anhhoa" + randomInt() + "@gmail.com";

		userRegisterPage = (UserRegisterPageObject) userHomePage.openDynamicHeaderPage(driver, "ico-register");

		userRegisterPage.inputValueToTextbox(locatorFirstName, registerFirstName);
		userRegisterPage.inputValueToTextbox(locatorLastName, registerLastName);
		userRegisterPage.inputValueToTextbox(locatorEmail, validEmail);
		userRegisterPage.inputValueToTextbox(locatorPassword, password);
		userRegisterPage.inputValueToTextbox(locatorConfirmPassword, password);

		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getTextConfirmRegisterSuccess(), "Your registration completed");

	}

	@Test
	public void TC_01_Login_Empty_Data() {
		userLoginPage = (UserLoginPageObject) userRegisterPage.openDynamicHeaderPage(driver, "ico-login");
		userLoginPage.clickToLoginButton();
		Assert.assertEquals(userLoginPage.getTextErrorWrongEmail(), "Please enter your email");
	}

	@Test
	public void TC_02_Login_Wrong_Email() {
		userLoginPage = (UserLoginPageObject) userRegisterPage.openDynamicHeaderPage(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(wrongEmail, password);
		Assert.assertEquals(userLoginPage.getTextErrorWrongEmail(), "Wrong email");
	}

	@Test
	public void TC_03_Login_Unregistered_Email() {
		userLoginPage = (UserLoginPageObject) userRegisterPage.openDynamicHeaderPage(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(unregisteredEmail, password);
		Assert.assertEquals(userLoginPage.getTextErrorWithInvalidValue(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_04_Login_Do_Not_Input_Password() {
		userLoginPage = (UserLoginPageObject) userRegisterPage.openDynamicHeaderPage(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, "");
		Assert.assertEquals(userLoginPage.getTextErrorWithInvalidValue(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_05_Login_Invalid_Password() {
		userLoginPage = (UserLoginPageObject) userRegisterPage.openDynamicHeaderPage(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, "12345");
		Assert.assertEquals(userLoginPage.getTextErrorWithInvalidValue(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_Successful() {
		userLoginPage = (UserLoginPageObject) userRegisterPage.openDynamicHeaderPage(driver, "ico-login");
		userLoginPage.inputTextboxAndClickButton(validEmail, password);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		Assert.assertTrue(userHomePage.verifyDisplayMyAccountMenu());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

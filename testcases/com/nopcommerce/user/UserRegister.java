package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.nopcommerce.pageObjects.user.PageGeneratorManager;
import com.nopcommerce.pageObjects.user.UserHomePageObject;
import com.nopcommerce.pageObjects.user.UserRegisterPageObject;

import commons.BaseTest;

public class UserRegister extends BaseTest {
	private WebDriver driver;
	UserRegisterPageObject userRegisterPage;
	UserHomePageObject userHomePage;
	
	String locatorFirstName = "FirstName";
	String locatorLastName = "LastName";
	String locatorEmail = "Email";
	String locatorPassword = "Password";
	String locatorConfirmPassword = "ConfirmPassword";
	
	String registerFirstName = "Anh Hoa";
	String registerLastName = "Mai Nguyen";
	String wrongEmail = "abc123@gmail@com";
	String registerPassword = "123456";
	String validEmail;
	
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getWebDriver(browser);		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		validEmail = "anhhoa" + randomInt() + "@gmail.com";
		
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		userRegisterPage=(UserRegisterPageObject) userHomePage.openDynamicHeaderPage(driver,"ico-register");
		
		userRegisterPage.clickToRegisterButton();
		
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox(locatorFirstName),"First name is required.");
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox(locatorLastName),"Last name is required.");
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox(locatorEmail),"Email is required.");
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox(locatorPassword),"Password is required.");
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox(locatorConfirmPassword),"Password is required.");
		
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		userRegisterPage=(UserRegisterPageObject) userHomePage.openDynamicHeaderPage(driver,"ico-register");
		
		userRegisterPage.inputValueToTextbox(locatorFirstName,registerFirstName);
		userRegisterPage.inputValueToTextbox(locatorLastName,registerLastName);
		userRegisterPage.inputValueToTextbox(locatorEmail,wrongEmail);
		userRegisterPage.inputValueToTextbox(locatorPassword,registerPassword);
		userRegisterPage.inputValueToTextbox(locatorConfirmPassword,registerPassword);
		
		userRegisterPage.clickToRegisterButton();
		
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox(locatorEmail),"Wrong email");
		
	}
	
	@Test
	public void TC_03_Register_Valid_Email() {
		userRegisterPage=(UserRegisterPageObject) userHomePage.openDynamicHeaderPage(driver,"ico-register");
		
		userRegisterPage.inputValueToTextbox(locatorFirstName,registerFirstName);
		userRegisterPage.inputValueToTextbox(locatorLastName,registerLastName);
		userRegisterPage.inputValueToTextbox(locatorEmail,validEmail);
		userRegisterPage.inputValueToTextbox(locatorPassword,registerPassword);
		userRegisterPage.inputValueToTextbox(locatorConfirmPassword,registerPassword);
		
		userRegisterPage.clickToRegisterButton();
		
		Assert.assertEquals(userRegisterPage.getTextConfirmRegisterSuccess(),"Your registration completed");
		
		userHomePage = (UserHomePageObject)userRegisterPage.openDynamicHeaderPage(driver,"ico-lo");
	}
	
	@Test
	public void TC_04_Register_Exist_Email() {
		userRegisterPage=(UserRegisterPageObject) userHomePage.openDynamicHeaderPage(driver,"ico-register");
		
		userRegisterPage.inputValueToTextbox(locatorFirstName,registerFirstName);
		userRegisterPage.inputValueToTextbox(locatorLastName,registerLastName);
		userRegisterPage.inputValueToTextbox(locatorEmail,validEmail);
		userRegisterPage.inputValueToTextbox(locatorPassword,registerPassword);
		userRegisterPage.inputValueToTextbox(locatorConfirmPassword,registerPassword);
		
		userRegisterPage.clickToRegisterButton();
		
		Assert.assertEquals(userRegisterPage.getTextErrorExistEmail(),"The specified email already exists");
		
	}
	
	@Test
	public void TC_05_Register_Invalid_Length_Password() {
		userRegisterPage=(UserRegisterPageObject) userHomePage.openDynamicHeaderPage(driver,"ico-register");
		
		userRegisterPage.inputValueToTextbox(locatorFirstName,registerFirstName);
		userRegisterPage.inputValueToTextbox(locatorLastName,registerLastName);
		userRegisterPage.inputValueToTextbox(locatorEmail,validEmail);
		userRegisterPage.inputValueToTextbox(locatorPassword,"12345");
		userRegisterPage.inputValueToTextbox(locatorConfirmPassword,"12345");
		
		userRegisterPage.clickToRegisterButton();
		
		Assert.assertEquals(userRegisterPage.getTextErrorLengthPassword(),"Password must meet the following rules:\nmust have at least 6 characters");
		
	}
	
	
	@Test
	public void TC_06_Register_Do_Not_Match_Password() {
		userRegisterPage=(UserRegisterPageObject) userHomePage.openDynamicHeaderPage(driver,"ico-register");
		
		userRegisterPage.inputValueToTextbox(locatorFirstName,registerFirstName);
		userRegisterPage.inputValueToTextbox(locatorLastName,registerLastName);
		userRegisterPage.inputValueToTextbox(locatorEmail,validEmail);
		userRegisterPage.inputValueToTextbox(locatorPassword,registerPassword);
		userRegisterPage.inputValueToTextbox(locatorConfirmPassword,"123455");
		
		userRegisterPage.clickToRegisterButton();
		
		Assert.assertEquals(userRegisterPage.getErrorMessageAtTextbox(locatorConfirmPassword),"The password and confirmation password do not match.");
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

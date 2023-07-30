package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.admin.AdminAddressCreatePageObject;
import com.nopcommerce.pageObjects.admin.AdminCustomerCreatePageObject;
import com.nopcommerce.pageObjects.admin.AdminCustomerEditPageObject;
import com.nopcommerce.pageObjects.admin.AdminCustomerListPageObject;
import com.nopcommerce.pageObjects.admin.AdminDashboardPageObject;
import com.nopcommerce.pageObjects.admin.AdminLoginPageObject;
import com.nopcommerce.pageObjects.admin.AdminPageGeneratorManager;

import commons.BaseTest;

public class AdminCustomerFunctions extends BaseTest {
	WebDriver driver;
	String urlAdmin = "";
	String username = "admin@yourstore.com", password = "admin";
	int randomNumber = randomInt();
	String email = "thaotest" + String.valueOf(randomNumber) + "@gmail.com", firstName = "Thao" + String.valueOf(randomNumber), lastName = "Hoang";
	String company = "NHAILTKTEST" + String.valueOf(randomNumber), commentOfAdd = "Add new customer", passwordCustomer = "123456", dateOfBirth = "9/9/2002";
	String commentOfEdit = "Edit Customer (Guest)", editDate = "8/8/2002";
	String confirmCustomerAdded = "The new customer has been added successfully.", confirmCustomerEdited = "The customer has been updated successfully.";
	String confirmAddressAdded = "The new address has been added successfully.", confirmAddressEdited = "The address has been updated successfully.";
	String city = "California", address1 = "Address 1", address2 = "Address 2", postCode = "050918", phoneNumber = "7865893412", faxNumber = "7865893413";
	String editCity = "Albany", editAddress1 = "Edit Address 1", editAddress2 = "Edit Address 2", editPostCode = "050919", editPhoneNumber = "7865893414", editFaxNumber = "7865893415";
	AdminLoginPageObject adminLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	AdminCustomerListPageObject adminCustomerListPage;
	AdminCustomerCreatePageObject adminCustomerCreatePage;
	AdminCustomerEditPageObject adminCustomerEditPage;
	AdminAddressCreatePageObject adminAddressCreatePage;

	@Parameters({ "browser", "urlAdmin" })
	@BeforeClass
	public void beforeClass(String browserName, String urlAdmin) {
		this.urlAdmin = urlAdmin;
		driver = getWebDriver(browserName, this.urlAdmin);

		adminLoginPage = AdminPageGeneratorManager.getAdminLoginPage(driver);

		adminLoginPage.inputValueToDynamicTextbox(driver, "Email", username);
		adminLoginPage.inputValueToDynamicTextbox(driver, "Password", password);
		adminDashboardPage = (AdminDashboardPageObject) adminLoginPage.clickToLoginButton();

		Assert.assertTrue(adminDashboardPage.verifyDisplayedTitle());
	}

	@Test
	public void TC_01_Create_New_Customer_And_Search_With_Email() {
		adminDashboardPage.isPageLoadedSuccess(driver);
		adminDashboardPage.clickToRightMenu(driver, "fa-user", "Customers");
		adminCustomerListPage = (AdminCustomerListPageObject) adminDashboardPage.clickToRightSubMenu(driver, "Customers", "Customers");
		adminCustomerListPage.isPageLoadedSuccess(driver);
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());
		adminCustomerCreatePage = (AdminCustomerCreatePageObject) adminCustomerListPage.clickToAddNewButton();
		adminCustomerCreatePage.isPageLoadedSuccess(driver);

		Assert.assertTrue(adminCustomerCreatePage.verifyDisplayedTitle());
		adminCustomerCreatePage.inputValueToDynamicTextbox(driver, "Email", email);
		adminCustomerCreatePage.inputValueToDynamicTextbox(driver, "Password", passwordCustomer);
		adminCustomerCreatePage.inputValueToDynamicTextbox(driver, "FirstName", firstName);
		adminCustomerCreatePage.inputValueToDynamicTextbox(driver, "LastName", lastName);
		adminCustomerCreatePage.checkToDynamicCheckboxRadio(driver, "Gender_Female");
		adminCustomerCreatePage.inputValueToDynamicTextbox(driver, "DateOfBirth", dateOfBirth);
		adminCustomerCreatePage.inputValueToDynamicTextbox(driver, "Company", company);
		adminCustomerCreatePage.clickToDeleteIconInRoleField(driver);
		adminCustomerCreatePage.selectCustomerRoleDropdown(driver, "Guests");
		adminCustomerCreatePage.checkToDynamicCheckboxRadio(driver, "Active");
		adminCustomerCreatePage.inputValueToCommentTextArea(driver, commentOfAdd);
		adminCustomerEditPage = (AdminCustomerEditPageObject) adminCustomerCreatePage.clickToSaveContinueButton();

		Assert.assertTrue(adminCustomerEditPage.verifyDisplayedConfirmMessage(driver, confirmCustomerAdded));
		Assert.assertEquals(adminCustomerEditPage.getValueInDynamicTextbox(driver, "FirstName"), firstName);
		Assert.assertEquals(adminCustomerEditPage.getValueInDynamicTextbox(driver, "LastName"), lastName);
		Assert.assertTrue(adminCustomerEditPage.isSelectedDynamicCheckboxRadio(driver, "Gender_Female"));
		Assert.assertEquals(adminCustomerEditPage.getValueInDynamicTextbox(driver, "DateOfBirth"), dateOfBirth);
		Assert.assertEquals(adminCustomerEditPage.getValueInDynamicTextbox(driver, "Company"), company);
		Assert.assertTrue(adminCustomerEditPage.isDisplayedCustomerRole());
		Assert.assertTrue(adminCustomerEditPage.isSelectedDynamicCheckboxRadio(driver, "Active"));
		Assert.assertEquals(adminCustomerEditPage.getTextInCommentTextArea(), commentOfAdd);

		adminCustomerListPage = (AdminCustomerListPageObject) adminCustomerEditPage.clickToBackButton();
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchEmail", email);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), firstName + " " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Company name"), company);
		Assert.assertTrue(adminCustomerListPage.isDisplayedTrueIcon());
	}

	@Test
	public void TC_02_Search_With_First_Name_And_Last_Name() {
		adminCustomerListPage.doubleClickToRightMenu(driver, "fa-user", "Customers");
		adminCustomerListPage.clickToRightSubMenu(driver, "Customers", "Customers");
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());
		adminCustomerListPage.isPageLoadedSuccess(driver);

		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchFirstName", firstName);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchLastName", lastName);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), firstName + " " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Company name"), company);
		Assert.assertTrue(adminCustomerListPage.isDisplayedTrueIcon());
	}

	@Test
	public void TC_03_Search_With_Company_Name() {
		adminCustomerListPage.doubleClickToRightMenu(driver, "fa-user", "Customers");
		adminCustomerListPage.clickToRightSubMenu(driver, "Customers", "Customers");
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());
		adminCustomerListPage.isPageLoadedSuccess(driver);

		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchCompany", company);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), firstName + " " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Company name"), company);
		Assert.assertTrue(adminCustomerListPage.isDisplayedTrueIcon());
	}

	@Test
	public void TC_04_Search_With_Full_Data() {
		adminCustomerListPage.doubleClickToRightMenu(driver, "fa-user", "Customers");
		adminCustomerListPage.clickToRightSubMenu(driver, "Customers", "Customers");
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());

		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchEmail", email);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchFirstName", firstName);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchLastName", lastName);
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchMonthOfBirth", "9");
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchDayOfBirth", "9");
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchCompany", company);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), firstName + " " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Company name"), company);
		Assert.assertTrue(adminCustomerListPage.isDisplayedTrueIcon());
	}

	@Test
	public void TC_05_Edit_Customer() {
		adminCustomerListPage.doubleClickToRightMenu(driver, "fa-user", "Customers");
		adminCustomerListPage.clickToRightSubMenu(driver, "Customers", "Customers");
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());
		adminCustomerListPage.isPageLoadedSuccess(driver);

		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchEmail", email);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchFirstName", firstName);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchLastName", lastName);
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchMonthOfBirth", "9");
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchDayOfBirth", "9");
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchCompany", company);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), firstName + " " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");

		adminCustomerEditPage = (AdminCustomerEditPageObject) adminCustomerListPage.clickToEditButton();
		adminCustomerEditPage.isPageLoadedSuccess(driver);

		adminCustomerEditPage.inputValueToDynamicTextbox(driver, "Email", "edit_" + email);
		adminCustomerEditPage.inputValueToDynamicTextbox(driver, "FirstName", "Edit " + firstName);
		adminCustomerEditPage.inputValueToDynamicTextbox(driver, "LastName", "Edit " + lastName);
		adminCustomerEditPage.inputValueToDynamicTextbox(driver, "DateOfBirth", editDate);
		adminCustomerEditPage.inputValueToDynamicTextbox(driver, "Company", "Edit " + company);
		adminCustomerEditPage.clickToDeleteIconInRoleField(driver);
		adminCustomerEditPage.selectCustomerRoleDropdown(driver, "Guests");
		adminCustomerEditPage.checkToDynamicCheckboxRadio(driver, "Active");
		adminCustomerEditPage.inputValueToCommentTextArea(driver, commentOfEdit);

		adminCustomerListPage = (AdminCustomerListPageObject) adminCustomerEditPage.clickToSaveButton();
		adminCustomerListPage.verifyDisplayedConfirmMessage(driver, confirmCustomerEdited);
		adminCustomerListPage.isPageLoadedSuccess(driver);

		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchEmail", "edit_" + email);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchFirstName", "Edit " + firstName);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchLastName", "Edit " + lastName);
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchMonthOfBirth", "8");
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchDayOfBirth", "8");
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchCompany", "Edit " + company);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), "Edit " + firstName + " " + "Edit " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Company name"), "Edit " + company);
		Assert.assertTrue(adminCustomerListPage.isDisplayedTrueIcon());

	}

	@Test
	public void TC_06_Add_New_Address_In_Customer_Detail() {
		adminCustomerListPage.doubleClickToRightMenu(driver, "fa-user", "Customers");
		adminCustomerListPage.clickToRightSubMenu(driver, "Customers", "Customers");
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());
		adminCustomerListPage.isPageLoadedSuccess(driver);

		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchEmail", "edit_" + email);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchFirstName", "Edit " + firstName);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchLastName", "Edit " + lastName);
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchMonthOfBirth", "8");
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchDayOfBirth", "8");
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchCompany", "Edit " + company);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), "Edit " + firstName + " " + "Edit " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Company name"), "Edit " + company);
		Assert.assertTrue(adminCustomerListPage.isDisplayedTrueIcon());

		adminCustomerEditPage = (AdminCustomerEditPageObject) adminCustomerListPage.clickToEditButton();
		adminCustomerEditPage.isPageLoadedSuccess(driver);
		adminCustomerEditPage.clickToAddressesPanel();
		adminAddressCreatePage = (AdminAddressCreatePageObject) adminCustomerEditPage.clickToAddNewAddress();
		adminAddressCreatePage.isPageLoadedSuccess(driver);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_FirstName", "Edit " + firstName);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_LastName", "Edit " + lastName);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_Email", "edit_" + email);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_Company", "Edit " + company);
		adminAddressCreatePage.selectTextOfDynamicDropdown(driver, "Address.CountryId", "United States");
		adminAddressCreatePage.selectTextOfDynamicDropdown(driver, "Address.StateProvinceId", "Alabama");
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_County", city);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_City", city);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_Address1", address1);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_Address2", address2);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_ZipPostalCode", postCode);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_PhoneNumber", phoneNumber);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_FaxNumber", faxNumber);
		adminAddressCreatePage.clickToButtonSave();

		adminAddressCreatePage.verifyDisplayedConfirmMessage(driver, confirmAddressAdded);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_FirstName"), "Edit " + firstName);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_LastName"), "Edit " + lastName);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_Company"), "Edit " + company);
		Assert.assertEquals(adminAddressCreatePage.vefifyValueOfDynamicDropdown(driver, "Address.CountryId"), "United States");
		Assert.assertEquals(adminAddressCreatePage.vefifyValueOfDynamicDropdown(driver, "Address.StateProvinceId"), "Alabama");
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_County"), city);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_City"), city);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_Address1"), address1);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_Address2"), address2);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_ZipPostalCode"), postCode);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_PhoneNumber"), phoneNumber);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_FaxNumber"), faxNumber);

		adminCustomerEditPage = (AdminCustomerEditPageObject) adminAddressCreatePage.clickToBackToCustomerDetailsLink();
		adminCustomerEditPage.isPageLoadedSuccess(driver);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameAtFirstColumnOfAddress(driver, "First name"), "Edit " + firstName);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Last name"), "Edit " + lastName);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Email"), "edit_" + email);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Phone number"), phoneNumber);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Fax number"), faxNumber);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Address"), "Edit " + company + "\n" + address1 + "\n" + address2 + "\n" + city + "," + "Alabama" + "," + postCode + "\n" + "United States");
	}

	@Test
	public void TC_07_Edit_Address_In_Customer_Detail() {
		adminCustomerListPage.doubleClickToRightMenu(driver, "fa-user", "Customers");
		adminCustomerListPage.clickToRightSubMenu(driver, "Customers", "Customers");
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());
		adminCustomerListPage.isPageLoadedSuccess(driver);

		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchEmail", "edit_" + email);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchFirstName", "Edit " + firstName);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchLastName", "Edit " + lastName);
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchMonthOfBirth", "8");
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchDayOfBirth", "8");
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchCompany", "Edit " + company);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), "Edit " + firstName + " " + "Edit " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Company name"), "Edit " + company);
		Assert.assertTrue(adminCustomerListPage.isDisplayedTrueIcon());

		adminCustomerEditPage = (AdminCustomerEditPageObject) adminCustomerListPage.clickToEditButton();
		adminCustomerEditPage.isPageLoadedSuccess(driver);
		adminCustomerEditPage.clickToAddressesPanel();
		adminAddressCreatePage = (AdminAddressCreatePageObject) adminCustomerEditPage.clickToEditAddressButton();
		adminAddressCreatePage.isPageLoadedSuccess(driver);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_FirstName", "Edit " + firstName);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_LastName", "Edit " + lastName);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_Email", "edit_" + email);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_Company", "Edit " + company);
		adminAddressCreatePage.selectTextOfDynamicDropdown(driver, "Address.CountryId", "United States");
		adminAddressCreatePage.selectTextOfDynamicDropdown(driver, "Address.StateProvinceId", "Alabama");
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_County", editCity);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_City", editCity);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_Address1", editAddress1);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_Address2", editAddress2);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_ZipPostalCode", editPostCode);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_PhoneNumber", editPhoneNumber);
		adminAddressCreatePage.inputValueToDynamicTextbox(driver, "Address_FaxNumber", editFaxNumber);
		adminAddressCreatePage.clickToButtonSave();

		adminAddressCreatePage.verifyDisplayedConfirmMessage(driver, confirmAddressEdited);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_FirstName"), "Edit " + firstName);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_LastName"), "Edit " + lastName);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_Company"), "Edit " + company);
		Assert.assertEquals(adminAddressCreatePage.vefifyValueOfDynamicDropdown(driver, "Address.CountryId"), "United States");
		Assert.assertEquals(adminAddressCreatePage.vefifyValueOfDynamicDropdown(driver, "Address.StateProvinceId"), "Alabama");
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_County"), editCity);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_City"), editCity);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_Address1"), editAddress1);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_Address2"), editAddress2);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_ZipPostalCode"), editPostCode);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_PhoneNumber"), editPhoneNumber);
		Assert.assertEquals(adminAddressCreatePage.getValueInDynamicTextbox(driver, "Address_FaxNumber"), editFaxNumber);

		adminCustomerEditPage = (AdminCustomerEditPageObject) adminAddressCreatePage.clickToBackToCustomerDetailsLink();
		adminCustomerEditPage.isPageLoadedSuccess(driver);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameAtFirstColumnOfAddress(driver, "First name"), "Edit " + firstName);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Last name"), "Edit " + lastName);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Email"), "edit_" + email);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Phone number"), editPhoneNumber);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Fax number"), editFaxNumber);
		Assert.assertEquals(adminCustomerEditPage.getValueInCellByHeaderNameOfAddress(driver, "Address"),
				"Edit " + company + "\n" + editAddress1 + "\n" + editAddress2 + "\n" + editCity + "," + "Alabama" + "," + editPostCode + "\n" + "United States");
	}

	@Test
	public void TC_08_Deleted_Address_In_Customer_Detail() {
		adminCustomerListPage.doubleClickToRightMenu(driver, "fa-user", "Customers");
		adminCustomerListPage.clickToRightSubMenu(driver, "Customers", "Customers");
		Assert.assertTrue(adminCustomerListPage.verifyDisplayedTitle());
		adminCustomerListPage.isPageLoadedSuccess(driver);

		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchEmail", "edit_" + email);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchFirstName", "Edit " + firstName);
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchLastName", "Edit " + lastName);
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchMonthOfBirth", "8");
		adminCustomerListPage.selectTextOfDynamicDropdown(driver, "SearchDayOfBirth", "8");
		adminCustomerListPage.inputValueToDynamicTextbox(driver, "SearchCompany", "Edit " + company);
		adminCustomerListPage.clickToDeleteIconInRoleField();
		adminCustomerListPage.selectCustomerRoleDropdown("Guests");
		adminCustomerListPage.clickToSearchButton();
		adminCustomerListPage.isPageLoadedSuccess(driver);

		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Name"), "Edit " + firstName + " " + "Edit " + lastName);
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Customer roles"), "Guests");
		Assert.assertEquals(adminCustomerListPage.getValueInCellByHeaderName(driver, "Company name"), "Edit " + company);
		Assert.assertTrue(adminCustomerListPage.isDisplayedTrueIcon());

		adminCustomerEditPage = (AdminCustomerEditPageObject) adminCustomerListPage.clickToEditButton();
		adminCustomerEditPage.isPageLoadedSuccess(driver);
		adminCustomerEditPage.clickToAddressesPanel();
		adminCustomerEditPage.clickToDeleteAddressButton();
		adminCustomerEditPage.isPageLoadedSuccess(driver);
		Assert.assertTrue(adminCustomerEditPage.verifyDisplayedEmptyData());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}

package com.nopcommerce.pageObjects.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nopcommerce.pageUIs.user.BasePageUI;
import com.nopcommerce.pageUIs.user.UserProductListPageUI;

import commons.BasePage;

public class UserProductListPageObject extends BasePage {
	WebDriver driver;

	public UserProductListPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public UserProductDetailPageObject clickToProductTitle(String productTitle) {
		clickToElement(driver, UserProductListPageUI.PRODUCT_TITLE, productTitle);
		return UserPageGeneratorManager.getUserProductDetailPage(driver);
	}

	public String getProductTitle(String productTitle) {
		waitForElementVisible(driver, UserProductListPageUI.PRODUCT_TITLE, productTitle);
		return getElementText(driver, UserProductListPageUI.PRODUCT_TITLE, productTitle);
	}

	public boolean isDisplayedSortWithNameASC() {
		ArrayList<String> arrayName = new ArrayList<>();

		List<WebElement> elementList = getListWebElement(driver, UserProductListPageUI.PRODUCT_LIST_TITLE);

		for (WebElement element : elementList) {
			arrayName.add(element.getText());
		}

		ArrayList<String> sortArrayName = new ArrayList<>();
		for (String name : arrayName) {
			sortArrayName.add(name);
		}

		Collections.sort(sortArrayName);

		return sortArrayName.equals(arrayName);
	}

	public boolean isDisplayedSortWithNameDESC() {
		ArrayList<String> arrayName = new ArrayList<>();

		List<WebElement> elementList = getListWebElement(driver, UserProductListPageUI.PRODUCT_LIST_TITLE);

		for (WebElement element : elementList) {
			arrayName.add(element.getText());
		}

		ArrayList<String> sortArrayName = new ArrayList<>();
		for (String name : arrayName) {
			sortArrayName.add(name);
		}

		Collections.sort(sortArrayName);
		Collections.reverse(sortArrayName);

		return sortArrayName.equals(arrayName);
	}

	public void scrollToSortDropDown(String string) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_DROPDOWN, string);
		scrollToElement(driver, BasePageUI.DYNAMIC_DROPDOWN, string);
	}

	public boolean isDisplayedSortWithPriceASC() {
		ArrayList<String> arrayPrice = new ArrayList<>();

		List<WebElement> elementList = getListWebElement(driver, UserProductListPageUI.PRODUCT_LIST_PRICE);

		for (WebElement element : elementList) {
			arrayPrice.add(element.getText().replace("$", ""));
		}

		ArrayList<String> sortArrayPrice = new ArrayList<>();
		for (String price : arrayPrice) {
			sortArrayPrice.add(price);
		}

		Collections.sort(sortArrayPrice);

		return sortArrayPrice.equals(arrayPrice);
	}

	public boolean isDisplayedSortWithPriceDESC() {
		ArrayList<String> arrayPrice = new ArrayList<>();

		List<WebElement> elementList = getListWebElement(driver, UserProductListPageUI.PRODUCT_LIST_PRICE);

		for (WebElement element : elementList) {
			arrayPrice.add(element.getText().replace("$", ""));
		}

		ArrayList<String> sortArrayPrice = new ArrayList<>();
		for (String price : arrayPrice) {
			sortArrayPrice.add(price);
		}

		Collections.sort(sortArrayPrice);

		Collections.reverse(sortArrayPrice);

		return sortArrayPrice.equals(arrayPrice);
	}

	public boolean isDisplayedProductsList(int i) {
		List<WebElement> elementList = getListWebElement(driver, UserProductListPageUI.PRODUCT_LIST_TITLE);
		if (elementList.size() <= i) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isDisplayedPagingIcon(String dynamicLocator) {
		waitForElementVisible(driver, UserProductListPageUI.NEXT_PAGING_ICON, dynamicLocator);
		return elementIsDisplayed(driver, UserProductListPageUI.NEXT_PAGING_ICON, dynamicLocator);
	}

	public void clickToNextPagingIcon(String dynamicLocator) {
		waitForElementClickable(driver, UserProductListPageUI.NEXT_PAGING_ICON, dynamicLocator);
		clickToElement(driver, UserProductListPageUI.NEXT_PAGING_ICON, dynamicLocator);

	}

	public boolean isUndisplayedPagingIcon(String dynamicLocator) {
		return elementIsUndisplayed(driver, UserProductListPageUI.NEXT_PAGING_ICON, dynamicLocator);
	}

	public void addProductToCompareList(String productName) {
		waitForElementClickable(driver, UserProductListPageUI.ADD_TO_COMPARE_LIST_BUTTON, productName);
		clickToElement(driver, UserProductListPageUI.ADD_TO_COMPARE_LIST_BUTTON, productName);
		
	}

	public void scrollToBottom(WebDriver driver2) {
		scrollToBottomPage(driver2);
	}
}

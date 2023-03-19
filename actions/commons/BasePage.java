package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nopcommerce.pageObjects.user.PageGeneratorManager;
import com.nopcommerce.pageObjects.user.UserHomePageObject;
import com.nopcommerce.pageObjects.user.UserLoginPageObject;
import com.nopcommerce.pageObjects.user.UserRegisterPageObject;
import com.nopcommerce.pageUIs.user.UserHomePageUI;

public class BasePage {
	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void setCookie(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(2);
	}

	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	protected void switchToWindowById(WebDriver driver, String pageId) {

		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(pageId)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	protected void switchToWindowByTitlePage(WebDriver driver, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			String actualTitle = driver.getTitle().trim();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	protected void closeTabWithoutParentPage(WebDriver driver, String parentPageId) {

		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentPageId)) {
				driver.switchTo().window(window);
				driver.close();
			}
			driver.switchTo().window(parentPageId);
		}

	}

	protected By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}
		return by;
	}

	public String getDynamicXpath(String locatorType, String... dynamicValue) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValue);
		}
		return locatorType;
	}

	protected WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByLocator(xpathLocator));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByLocator(xpathLocator));
	}

	protected void clickToElement(WebDriver driver, String xpathLocator) {
		getElement(driver, xpathLocator).click();
	}

	protected void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).click();
	}

	protected void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	protected void clearValueInElementByPressKey(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	protected void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
		WebElement element = getElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected void selectItemByValueInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getElement(driver, xpathLocator));
		select.selectByValue(textItem);
	}

	protected void selectItemByValueInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String... dynamicValues) {
		Select select = new Select(getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		select.selectByValue(textItem);
	}

	protected void selectItemByTextInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getElement(driver, xpathLocator));
		select.selectByVisibleText(textItem);
	}

	protected void selectItemByTextInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String... dynamicValues) {
		Select select = new Select(getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	protected String getFirstItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	protected String getFirstItemInDefaultDropdown(WebDriver driver, String xpathLocator, String... dynamicValues) {
		Select select = new Select(getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getElement(driver, xpathLocator));
		return select.isMultiple();
	}

	protected void selectItemInCustomerDropdown(WebDriver driver, String parentXpath, String childXpath, String itemSelect) {
		getElement(driver, parentXpath).click();
		WebDriverWait explicitlyWait = new WebDriverWait(driver, longTimeOut);

		List<WebElement> listItem = explicitlyWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : listItem) {
			if (item.getText().trim().equals(itemSelect)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
					item.click();
				}
				break;
			}
		}
	}

	public void sleepInSecond(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getElement(driver, xpathLocator).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String... dynamicValues) {
		return getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getAttribute(attributeName);
	}

	protected String getElementText(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).getText();
	}

	protected String getElementText(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getText();
	}

	protected String getElemenCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getElement(driver, xpathLocator).getCssValue(propertyName);
	}

	protected String convertRgbaToHex(String rgvaValue) {
		return Color.fromString(rgvaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	protected int getElementSize(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).size();
	}

	protected void checktoDefaultCheckboxRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checktoDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void unchecktoDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void unchecktoDefaultCheckboxRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean elementIsDisplayed(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isDisplayed();
	}

	protected boolean elementIsDisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isDisplayed();
	}

	protected boolean elementIsUndisplayed(WebDriver driver, String xpathLocator) {
		overrideGlobalTimeOut(driver, shortTimeOut);
		List<WebElement> elements = getListWebElement(driver, xpathLocator);
		overrideGlobalTimeOut(driver, longTimeOut);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean elementIsUndisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		overrideGlobalTimeOut(driver, shortTimeOut);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		overrideGlobalTimeOut(driver, longTimeOut);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected void overrideGlobalTimeOut(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	protected boolean elementIsEnabled(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isEnabled();
	}

	protected boolean elementIsEnabled(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isEnabled();
	}

	protected boolean elementIsSelected(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isSelected();
	}

	protected boolean elementIsSelected(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getElement(driver, xpathLocator));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, xpathLocator)).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(driver, xpathLocator), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)), key).perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void highlightElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, xpathLocator));
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
	}

	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xpathLocator));
	}

	protected void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, xpathLocator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, xpathLocator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, xpathLocator));
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(xpathLocator)));
	}

	protected void waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	protected void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(xpathLocator)));
	}

	protected void waitForElementInisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(xpathLocator)));
	}

	protected void waitForElementInisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	protected void waitForElementUndisplayed(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideGlobalTimeOut(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(xpathLocator)));
		overrideGlobalTimeOut(driver, longTimeOut);
	}

	protected void waitForElementUndisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideGlobalTimeOut(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(xpathLocator, dynamicValues))));
		overrideGlobalTimeOut(driver, longTimeOut);
	}

	protected void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}

	protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(xpathLocator)));
	}

	protected void waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public BasePage openDynamicHeaderPage(WebDriver driver, String locator) {
		waitForElementClickable(driver, UserHomePageUI.HEADER_DYNAMIC_LINK, locator);
		clickToElement(driver, UserHomePageUI.HEADER_DYNAMIC_LINK, locator);
		switch (locator) {
		case "ico-register":
			return PageGeneratorManager.getUserRegisterPage(driver);
		case "ico-login":
			return PageGeneratorManager.getUserLoginPage(driver);
		case "ico-logout":
			return PageGeneratorManager.getUserHomePage(driver);

		default:
			throw new RuntimeException("Invalid Page");
		}
	}

	protected long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;
	protected long longTimeOut = GlobalConstants.LONG_TIMEOUT;

}

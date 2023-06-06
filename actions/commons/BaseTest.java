package commons;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	// String projectPath = System.getProperty("user.dir");

	public WebDriver getWebDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("coccoc")) {
			WebDriverManager.chromedriver().driverVersion("96.0.4664.45").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\Admin\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		} else {
			throw new RuntimeException("browser is invalid");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.USER_URL_LINK);
		return driver;
	}
	
	public WebDriver getWebDriver(String browser, String appUrl) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(false);
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("coccoc")) {
			WebDriverManager.chromedriver().driverVersion("96.0.4664.45").setup();
			ChromeOptions options = new ChromeOptions();
			if (GlobalConstants.OS_NAME.startsWith("window")) {
				options.setBinary("C:\\Users\\Admin\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				options.setBinary("...");
			}
			driver = new ChromeDriver(options);
		} else {
			throw new RuntimeException("browser is invalid");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		return driver;
	}

	public int randomInt() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	public void waitInSecond(long i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + String.valueOf(day);
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		GregorianCalendar date = new GregorianCalendar();
		int month = date.get(Calendar.MONTH);
		 return String.valueOf(date.MONTH);
		//return String.valueOf(month);
	}
	
	protected String getMonth() {
		int month = 1;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);  
		return c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return now.getYear() + "";
	}

	protected String getDayOfWeek() {
		LocalDate localDate = LocalDate.of(Integer.parseInt(getCurrentYear()), Integer.parseInt(getMonth()), Integer.parseInt(getCurrentDay()) - 1); // custom
		DayOfWeek day = DayOfWeek.from(localDate);

		System.out.println("Day of Week: "+ day.name());
		return day.name();
	}

	protected String getToday() {
		return getDayOfWeek() + ", " + getMonth() + " " + getCurrentDay() + ", " + getCurrentYear();
	}

}

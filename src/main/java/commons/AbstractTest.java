package commons;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest {
	private WebDriver driver;
	protected final Log log;

	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		if (browser==Browser.FIREFOX_UI) {
			WebDriverManager.firefoxdriver().setup();
//			FirefoxProfile profile = new FirefoxProfile();
//			File file = new File(GlobalConstants.BROWSER_EXTENSION_FOLDER + "adblocker_ultimate-3.7.10-an+fx.xpi");
//			profile.addExtension(file);
//			FirefoxOptions options = new FirefoxOptions();
//			options.addPreference("intl.accept_languages", "vi-VN");
//			options.addPreference("browser.download.folderList", "2");
//			options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FOLDER);
//			options.addPreference("browser.download.useDownloadDir", true);
//			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
//			options.addPreference("pdfjs.disabled", true);
//			options.addArguments("--private");
//			options.setProfile(profile);
			driver = new FirefoxDriver();
		} else if (browser==Browser.CHROME_UI) {
			WebDriverManager.chromedriver().setup();
			File file = new File(GlobalConstants.BROWSER_EXTENSION_FOLDER + "extension_2_0_9_0.crx");
			ChromeOptions options = new ChromeOptions();
//			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//			options.addArguments("--lang=vi");
//			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			options.addArguments("--start-maximized");
//			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-geolocation");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.addExtensions(file);
//			chromePrefs.put("profile.default_content_settings.popups", 0);
//			chromePrefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FOLDER);
//			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options);
		} else if (browser==Browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);
		} else if (browser==Browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browser==Browser.EDGE_CHROMIUM) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser==Browser.EDGE_LEGACY) {
			driver = new EdgeDriver();
		}else if (browser==Browser.COCCOC) {
			WebDriverManager.chromedriver().driverVersion("84.0.4147.30").setup();
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setBinary("C:\\Users\\Khang KMA\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		}  else if (browser==Browser.SAFARI) {
			driver = new SafariDriver();
		}  else if (browser==Browser.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		} else {
			throw new RuntimeException("Please input valid browser name value!");
		}
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String url) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		if (browser==Browser.FIREFOX_UI) {
			WebDriverManager.firefoxdriver().setup();
//			FirefoxProfile profile = new FirefoxProfile();
//			File file = new File(GlobalConstants.BROWSER_EXTENSION_FOLDER + "adblocker_ultimate-3.7.10-an+fx.xpi");
//			profile.addExtension(file);
//			FirefoxOptions options = new FirefoxOptions();
////			options.addPreference("intl.accept_languages", "vi-VN");
//			options.addPreference("browser.download.folderList", "2");
//			options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FOLDER);
//			options.addPreference("browser.download.useDownloadDir", true);
//			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
//			options.addPreference("pdfjs.disabled", true);
//			options.addArguments("--private");
//			options.setProfile(profile);
			driver = new FirefoxDriver();
		} else if (browser==Browser.CHROME_UI) {
			WebDriverManager.chromedriver().setup();
			File file = new File(GlobalConstants.BROWSER_EXTENSION_FOLDER + "extension_4_27_0_0.crx");
			ChromeOptions options = new ChromeOptions();
//			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//			options.addArguments("--lang=vi");
//			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			options.addArguments("--start-maximized");
//			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-geolocation");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.addExtensions(file);
//			chromePrefs.put("profile.default_content_settings.popups", 0);
//			chromePrefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FOLDER);
//			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options);
		} else if (browser==Browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);
		} else if (browser==Browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browser==Browser.EDGE_CHROMIUM) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser==Browser.COCCOC) {
			WebDriverManager.chromedriver().driverVersion("86.0.4240.22").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\Khang KMA\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		}else if (browser==Browser.SAFARI) {
			driver = new SafariDriver();
		}  else if (browser==Browser.IE) {
			WebDriverManager.edgedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		} else {
			throw new RuntimeException("Please input valid browser name value!");
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	
	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		return day + "";
	}
	
	protected String getCurrentMonth() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int month = nowUTC.getMonthOfYear();
		if(month < 10) {
			return "0" + month;
		}
		return month + "";
	}
	
	protected String getCurrentYear() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int year = nowUTC.getYear();
		return year + "";
	}
	
	protected String getToday() {
		return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
	}
	
	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			// Get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = "";
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
			
			// Quit driver executable file in Task Manager
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("edge")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill msedgedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}

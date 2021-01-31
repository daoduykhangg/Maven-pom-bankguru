package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.ManageHomePagePO;
import pageObjects.MiniStatementPO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;
import pageUIs.AbstractPageUI;
import utilities.FakerConfig;

public class Customer_07_Mini_Statement extends AbstractTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);

		fakeData = FakerConfig.getFakeData();

		emailID = fakeData.getEmailAddress();

		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("Pre-condition - Step 01: Click to 'Here' Link");
		registerPage.clickToHereLink();

		log.info("Pre-condition - Step 02: Enter to 'Email ID' Textbox");
		registerPage.enterToTextboxByName(driver, emailID, "emailid");

		log.info("Pre-condition - Step 03: Click to 'Submit' Button");
		registerPage.clickToButtonByValue(driver, "Submit");

		log.info("Pre-condition - Step 04: Get Text 'User ID' in table");
		userID = registerPage.getUserIDValue();

		log.info("Pre-condition - Step 05: Get Text 'Password' in table");
		passwordGenerate = registerPage.getPasswordValue();

		log.info("Pre-condition - Step 06: Back to the first page");
		registerPage.backToPage(driver);
		registerPage.backToPage(driver);

		log.info("Pre-condition - Step 07: Enter to 'Email ID' Textbox");
		registerPage.enterToTextboxByName(driver, userID, "uid");

		log.info("Pre-condition - Step 08: Enter to 'Password' Textbox");
		registerPage.enterToTextboxByName(driver, passwordGenerate, "password");

		log.info("Pre-condition - Step 09: Click to 'LOGIN' Button");
		registerPage.clickToButtonByValue(driver, "LOGIN");
		manageHomePage = PageGeneratorManager.getManageHomePage(driver);
		manageHomePage.sleepInSecond(15);
		log.info("Pre-condition - Step 010: Click to 'Mini Statement' Link");
		manageHomePage.openToPageInListboxByName(driver, "Mini Statement");
		miniStatementPage = PageGeneratorManager.getMiniStatementPage(driver);
	}

	@Test
	public void TC_01_MiniStatement_With_Account_Number_Can_Not_Be_Empty() {
		log.info("MiniStatement [Account Number can not be empty] - Step 01: Enter to 'Account No' with empty value");
		miniStatementPage.enterToTextboxByName(driver, "", "accountno");

		log.info("MiniStatement [Account Number can not be empty] - Step 02: Enter to 'Account No' with 'TAB' key");
		miniStatementPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "accountno");

		log.info("MiniStatement [Account Number can not be empty] - Step 03: Verify Error Message is displayed with value 'Account Number must not be blank'");
		verifyEquals(miniStatementPage.getErrorMessageValueByID(driver, "2"), "Account Number must not be blank");
	}

	@Test
	public void TC_02_MiniStatement_With_Account_Number_Must_Be_Numberic() {
		log.info("MiniStatement [Account Number must be numberic] - Step 01: Refresh current Page");
		miniStatementPage.refreshCurrentPage(driver);

		log.info("MiniStatement [Account Number must be numberic] - Step 02: Enter to 'Account No' with value 'abc123'");
		miniStatementPage.enterToTextboxByName(driver, "abc123", "accountno");

		log.info("MiniStatement [Account Number must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(miniStatementPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}

	@Test
	public void TC_03_MiniStatement_With_Account_Number_Can_Not_Have_Special_Character() {
		log.info("MiniStatement [Account Number can not have special character] - Step 01: Refresh current Page");
		miniStatementPage.refreshCurrentPage(driver);

		log.info("MiniStatement [Account Number can not have special character] - Step 02: Enter to 'Account No' with value '!@#123'");
		miniStatementPage.enterToTextboxByName(driver, "@#123", "accountno");

		log.info("MiniStatement [Account Number can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(miniStatementPage.getErrorMessageValueByID(driver, "2"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_MiniStatement_With_Account_Number_Can_Not_Have_Blank_Space() {
		log.info("MiniStatement [Account Number can not have blank space] - Step 01: Refresh current Page");
		miniStatementPage.refreshCurrentPage(driver);

		log.info("MiniStatement [Account Number can not have blank space] - Step 02: Enter to 'Account No' with value '12 123'");
		miniStatementPage.enterToTextboxByName(driver, "12 123", "accountno");

		log.info("MiniStatement [Account Number can not have blank space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(miniStatementPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}

	@Test
	public void TC_05_MiniStatement_With_Account_Number_First_Character_Can_Not_Be_Space() {
		log.info("MiniStatement [Account Number first character can not be space] - Step 01: Refresh current Page");
		miniStatementPage.refreshCurrentPage(driver);

		log.info("MiniStatement [Account Number first character can not be space] - Step 02: Enter to 'Account No' with value ' '");
		miniStatementPage.enterToTextboxByName(driver, " ", "accountno");

		log.info("MiniStatement [Account Number first character can not be space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(miniStatementPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	FakerConfig fakeData;
	RegisterPO registerPage;
	ManageHomePagePO manageHomePage;
	MiniStatementPO miniStatementPage;

	String emailID, userID, passwordGenerate;
}

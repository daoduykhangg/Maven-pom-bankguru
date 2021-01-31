package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.AccountPO;
import pageObjects.ManageHomePagePO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;
import pageUIs.AbstractPageUI;
import pageUIs.CustomerPageUI;
import utilities.FakerConfig;

public class Customer_04_New_Account extends AbstractTest {
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

		log.info("Pre-condition - Step 10: Click to 'New Account' Link");
		manageHomePage.openToPageInListboxByName(driver, "New Account");
		accountPage = PageGeneratorManager.getAccountPage(driver);
	}

	@Test
	public void TC_01_New_Account_With_CustomerID_Can_Not_Be_Empty() {
		log.info("New Account [CustomerID can not be empty] - Step 01: Enter to 'Customer id' Textbox with empty value");
		accountPage.enterToTextboxByName(driver, "", "cusid");

		log.info("New Account [CustomerID can not be empty] - Step 02: Enter to 'Customer id' Textbox with 'TAB' key");
		accountPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "cusid");

		log.info("New Account [CustomerID can not be empty] - Step 03: Verify Error Message is displayed with value 'Customer ID is required'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "14"), "Customer ID is required");
	}

	@Test
	public void TC_02_New_Account_With_CustomerID_Must_Be_Numberic() {
		log.info("New Account [CustomerID must be numberic] - Step 01: Refresh current page");
		accountPage.refreshCurrentPage(driver);

		log.info("New Account [CustomerID must be numberic] - Step 02: Enter to 'Customer id' Textbox with value 'abc123'");
		accountPage.enterToTextboxByName(driver, "abc123", "cusid");

		log.info("New Account [CustomerID must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "14"), "Characters are not allowed");
	}

	@Test
	public void TC_03_New_Account_With_CustomerID_Can_Not_Have_Special_Character() {
		log.info("New Account [CustomerID can not have special character] - Step 01: Refresh current page");
		accountPage.refreshCurrentPage(driver);

		log.info("New Account [CustomerID can not have special character] - Step 02: Enter to 'Customer id' Textbox with value '!@#123'");
		accountPage.enterToTextboxByName(driver, "!@#123", "cusid");

		log.info("New Account [CustomerID can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "14"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_New_Account_With_CustomerID_Can_Not_Have_Blank_Space() {
		log.info("New Account [CustomerID can not have blank space] - Step 01: Refresh current page");
		accountPage.refreshCurrentPage(driver);

		log.info("New Account [CustomerID can not have blank space] - Step 02: Enter to 'Customer id' Textbox with value '12 123'");
		accountPage.enterToTextboxByName(driver, "12 123", "cusid");

		log.info("New Account [CustomerID can not have blank space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "14"), "Characters are not allowed");
	}

	@Test
	public void TC_05_New_Account_With_CustomerID_First_Character_Can_Not_Be_Space() {
		log.info("New Account [CustomerID First character can not be space] - Step 01: Refresh current page");
		accountPage.refreshCurrentPage(driver);

		log.info("New Account [CustomerID First character can not be space] - Step 02: Enter to 'Customer id' Textbox with value ' '");
		accountPage.enterToTextboxByName(driver, " ", "cusid");
		log.info("New Account [CustomerID First character can not be space] - Step 03: Verify Error Message is displayed with value 'First character can not have space'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "14"), "First character can not have space");
	}

	@Test
	public void TC_06_New_Account_With_Initial_Deposit_Can_Not_Be_Empty() {
		log.info("New Account [Initial Deposit can not be empty] - Step 01: Enter to 'Initial Deposit' Textbox with empty value");
		accountPage.enterToTextboxByName(driver, "", "inideposit");

		log.info("New Account [Initial Deposit can not be empty] - Step 02: Enter to 'Initial Deposit' Textbox with 'TAB' key");
		accountPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "inideposit");

		log.info("New Account [Initial Deposit can not be empty] - Step 03: Verify Error Message is displayed with value 'Initial Deposit must not be blank'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "19"), "Initial Deposit must not be blank");
	}

	@Test
	public void TC_07_New_Account_With_Initial_Deposit_Must_Be_Numberic() {
		log.info("New Account [Initial Deposit must be numberic] - Step 01: Refresh current page");
		accountPage.refreshCurrentPage(driver);

		log.info("New Account [Initial Deposit must be numberic] - Step 02: Enter to 'Initial Deposit' Textbox with value 'abc123'");
		accountPage.enterToTextboxByName(driver, "abc123", "inideposit");

		log.info("New Account [Initial Deposit must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "19"), "Characters are not allowed");
	}

	@Test
	public void TC_08_New_Account_With_Initial_Deposit_Can_Not_Have_Special_Character() {
		log.info("New Account [Initial Deposit can not have special character] - Step 01: Refresh current page");
		accountPage.refreshCurrentPage(driver);

		log.info("New Account [Initial Deposit can not have special character] - Step 02: Enter to 'Initial Deposit' Textbox with value '!@#123'");
		accountPage.enterToTextboxByName(driver, "!@#123", "inideposit");

		log.info("New Account [Initial Deposit can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "19"), "Special characters are not allowed");
	}

	@Test
	public void TC_09_New_Account_With_Initial_Deposit_Can_Not_Have_Blank_Space() {
		log.info("New Account [Initial Deposit can not have blank space] - Step 01: Refresh current page");
		accountPage.refreshCurrentPage(driver);

		log.info("New Account [Initial Deposit can not have blank space] - Step 02: Enter to 'Initial Deposit' Textbox with value '12 123'");
		accountPage.enterToTextboxByName(driver, "12 123", "inideposit");

		log.info("New Account [Initial Deposit can not have blank space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "19"), "Characters are not allowed");
	}

	@Test
	public void TC_10_New_Account_With_Initial_Deposit_First_Character_Can_Not_Be_Space() {
		log.info("New Account [Initial Deposit First character can not be space] - Step 01: Refresh current page");
		accountPage.refreshCurrentPage(driver);

		log.info("New Account [Initial Deposit First character can not be space] - Step 02: Enter to 'Initial Deposit' Textbox with value ' '");
		accountPage.enterToTextboxByName(driver, " ", "inideposit");
		log.info("New Account [Initial Deposit First character can not be space] - Step 03: Verify Error Message is displayed with value 'First character can not have space'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "19"), "First character can not have space");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	FakerConfig fakeData;
	RegisterPO registerPage;
	ManageHomePagePO manageHomePage;
	AccountPO accountPage;

	String emailID, userID, passwordGenerate;
}

package com.bankguru.customer;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.CustomerPO;
import pageObjects.ManageHomePagePO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;
import pageUIs.AbstractPageUI;
import utilities.FakerConfig;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Customer_03_Delete_Customer extends AbstractTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = getBrowserDriver(browserName, urlValue);
		fakeData = FakerConfig.getFakeData();

		emailID = fakeData.getEmailAddress();
		customerName = fakeData.getFirstname() + " " + fakeData.getLastname();
		dateOfBirth = "08/21/1996";
		Address = "897 O Connell Row";
		city = "Bronx";
		state = "New York";
		PIN = "210896";
		mobileNumber = "0938666999";
		email = fakeData.getEmailAddress();
		password = fakeData.getPassword();

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

		log.info("Pre-condition - Step 10: Click to 'New Customer' Link");
		manageHomePage.openToPageInListboxByName(driver, "New Customer");
		customerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Pre-condition - Step 11: Enter to 'Customer Name' Textbox");
		customerPage.enterToTextboxByName(driver, customerName, "name");

		log.info("Pre-condition - Step 12: Click to 'Gender Male' Radio Button");
		customerPage.clickToRadioButtonByValue("m");

		log.info("Pre-condition - Step 13: Enter to 'Date of Birth' Textbox");
		customerPage.enterToTextboxByName(driver, dateOfBirth, "dob");

		log.info("Pre-condition - Step 14: Enter to 'Address' Textarea");
		customerPage.enterToTextareaByName(driver, Address, "addr");

		log.info("Pre-condition - Step 15: Enter to 'City' Textbox");
		customerPage.enterToTextboxByName(driver, city, "city");

		log.info("Pre-condition - Step 16: Enter to 'State' Textbox");
		customerPage.enterToTextboxByName(driver, state, "state");

		log.info("Pre-condition - Step 17: Enter to 'PIN' Textbox");
		customerPage.enterToTextboxByName(driver, PIN, "pinno");

		log.info("Pre-condition - Step 18: Enter to 'Mobile Number' Textbox");
		customerPage.enterToTextboxByName(driver, mobileNumber, "telephoneno");

		log.info("Pre-condition - Step 19: Enter to 'E-mail' Textbox");
		customerPage.enterToTextboxByName(driver, email, "emailid");

		log.info("Pre-condition - Step 20: Enter to 'Password' Textbox");
		customerPage.enterToTextboxByName(driver, password, "password");

		log.info("Pre-condition - Step 21: Click to 'Submit' Button");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("Pre-condition - Step 22: Verify Success Message is displayed with value 'Customer Registered Successfully!!!'");
		verifyEquals(customerPage.getHeadingTextInTable(driver), "Customer Registered Successfully!!!");

		log.info("Pre-condition - Step 23: Get Text 'Customer ID' in table");
		customerID = customerPage.getCustomerIDValue();

		log.info("Pre-condition - Step 24: Click to 'Delete Customer' Link");
		customerPage.openToPageInListboxByName(driver, "Delete Customer");
	}

	@Test
	public void TC_01_Customer_ID_Can_Not_Be_Empty() {
		log.info("Delete Customer [CustomerID can not be empty] - Step 01: Click to 'Customer ID' Textbox");
		customerPage.enterToTextboxByName(driver, "", "cusid");
		
		log.info("Delete Customer [CustomerID can not be empty] - Step 02: Enter to 'Customer ID' Textbox with 'TAB' key");
		customerPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "cusid");
		
		log.info("Delete Customer [CustomerID can not be empty] - Step 03: Verify Error Message is displayed with value 'Customer ID is required'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "14"), "Customer ID is required");
	}

	@Test
	public void TC_02_Customer_ID_Must_Be_Numberic() {
		log.info("Delete Customer [CustomerID must be numberic] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("Delete Customer [CustomerID must be numberic] - Step 02: Click to 'Customer ID' Textbox with value 'abc123'");
		customerPage.enterToTextboxByName(driver, "abc123", "cusid");

		log.info("Delete Customer [CustomerID must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "14"), "Characters are not allowed");
	}

	@Test
	public void TC_03_Customer_ID_Can_Not_Have_Special_Character() {
		log.info("Delete Customer [CustomerID can not have special character] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("Delete Customer [CustomerID can not have special character] - Step 02: Click to 'Customer ID' Textbox with value '!@#123'");
		customerPage.enterToTextboxByName(driver, "!@#123", "cusid");

		log.info("Delete Customer [CustomerID can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "14"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_Customer_ID_Can_Not_Have_Blank_Space() {
		log.info("Delete Customer [CustomerID can not have blank space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("Delete Customer [CustomerID can not have blank space] - Step 02: Click to 'Customer ID' Textbox with value '123 123'");
		customerPage.enterToTextboxByName(driver, "123 123", "cusid");

		log.info("Delete Customer [CustomerID can not have blank space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "14"), "Characters are not allowed");
	}

	@Test
	public void TC_05_Customer_ID_First_Character_Can_Not_Be_Space() {
		log.info("Delete Customer [CustomerID First character can not be space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("Delete Customer [CustomerID First character can not be space] - Step 02: Click to 'Customer ID' Textbox with value ' '");
		customerPage.enterToTextboxByName(driver, " ", "cusid");

		log.info("Delete Customer [CustomerID First character can not be space] - Step 03: Verify Error Message is displayed with value 'First character can not have space'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "14"), "First character can not have space");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	FakerConfig fakeData;
	ManageHomePagePO manageHomePage;
	RegisterPO registerPage;
	CustomerPO customerPage;

	String emailID, userID, passwordGenerate, customerID;
	String customerName, dateOfBirth, Address, city, state, PIN, mobileNumber, email, password;
}

package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.AccountPO;
import pageObjects.CustomerPO;
import pageObjects.ManageHomePagePO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;
import pageUIs.AbstractPageUI;
import utilities.FakerConfig;

public class Customer_06_Delete_Account extends AbstractTest{

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
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
		initialDeposit = "500000";
		
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
		customerPage = PageGeneratorManager.getCustomerPage(driver);

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

		log.info("Pre-condition - Step 24: Click to 'New Account' Link");
		customerPage.openToPageInListboxByName(driver, "New Account");
		accountPage = PageGeneratorManager.getAccountPage(driver);
		
		log.info("Pre-condition - Step 25: Enter to 'Customer id' Textbox");
		accountPage.enterToTextboxByName(driver, customerID, "cusid");
		
		log.info("Pre-condition - Step 26: Enter to 'Initial deposit' Textbox");
		accountPage.enterToTextboxByName(driver, initialDeposit, "inideposit");
		
		log.info("Pre-condition - Step 27: Click to 'Submit' button");
		accountPage.clickToButtonByValue(driver, "submit");
		
		log.info("Pre-condition - Step 28: Verify Success Message is displayed with value 'Account Generated Successfully!!!'");
		verifyEquals(accountPage.getHeadingTextInTable(driver), "Account Generated Successfully!!!");
		
		log.info("Pre-condition - Step 29: Get Text 'Account ID' in table");
		accountID = accountPage.getAccountIDValueInTable();
		
		log.info("Pre-condition - Step 30: Click to 'Delete Account' Link");
		accountPage.openToPageInListboxByName(driver, "Delete Account");
		
	}
	
	@Test
	public void TC_01_Delete_Account_With_Account_Number_Can_Not_Be_Empty() {
		log.info("Edit Account [Account Number can not be empty] - Step 01: Enter to 'Account No' with empty value");
		accountPage.enterToTextboxByName(driver, "", "accountno");
		
		log.info("Edit Account [Account Number can not be empty] - Step 02: Enter to 'Account No' with 'TAB' key");
		accountPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "accountno");
		
		log.info("Edit Account [Account Number can not be empty] - Step 03: Verify Error Message is displayed with value 'Account Number must not be blank'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "2"), "Account Number must not be blank");
	}
	
	@Test
	public void TC_02_Delete_Account_With_Account_Number_Must_Be_Numberic() {		
		log.info("Delete Account [Account Number must be numberic] - Step 01: Refresh current Page");
		accountPage.refreshCurrentPage(driver);
		
		log.info("Delete Account [Account Number must be numberic] - Step 02: Enter to 'Account No' with value 'abc123'");
		accountPage.enterToTextboxByName(driver, "abc123", "accountno");
		
		log.info("Delete Account [Account Number must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}
	
	@Test
	public void TC_03_Delete_Account_With_Account_Number_Can_Not_Have_Special_Character() {
		log.info("Delete Account [Account Number can not have special character] - Step 01: Refresh current Page");
		accountPage.refreshCurrentPage(driver);
		
		log.info("Delete Account [Account Number can not have special character] - Step 02: Enter to 'Account No' with value '!@#123'");
		accountPage.enterToTextboxByName(driver, "@#123", "accountno");
		
		log.info("Delete Account [Account Number can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "2"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_04_Delete_Account_With_Account_Number_Can_Not_Have_Blank_Space() {
		log.info("Delete Account [Account Number can not have blank space] - Step 01: Refresh current Page");
		accountPage.refreshCurrentPage(driver);
		
		log.info("Delete Account [Account Number can not have blank space] - Step 02: Enter to 'Account No' with value '12 123'");
		accountPage.enterToTextboxByName(driver, "12 123", "accountno");
		
		log.info("Delete Account [Account Number can not have blank space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}
	
	@Test
	public void TC_05_Delete_Account_With_Account_Number_First_Character_Can_Not_Be_Space() {
		log.info("Delete Account [Account Number first character can not be space] - Step 01: Refresh current Page");
		accountPage.refreshCurrentPage(driver);
		
		log.info("Delete Account [Account Number first character can not be space] - Step 02: Enter to 'Account No' with value ' '");
		accountPage.enterToTextboxByName(driver, " ", "accountno");
		
		log.info("Delete Account [Account Number first character can not be space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(accountPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}

	@Test
	public void TC_06_Delete_Account_With_Valid_Account_Number() {
		log.info("Delete Account [Valid Account Number] - Step 01: Refresh current Page");
		accountPage.refreshCurrentPage(driver);
		
		log.info("Delete Account [Valid Account Number] - Step 02: Enter to 'Account No' with value '" + accountID + "'");
		accountPage.enterToTextboxByName(driver, accountID, "accountno");
		
		log.info("Delete Account [Valid Account Number] - Step 03: Click to 'Submit' Button");
		accountPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Delete Account [Valid Account Number] - Step 04: Accept alert 'Do you really want to delete this Account?'");
		accountPage.acceptAlert(driver);
		
		log.info("Delete Account [Valid Account Number] - Step 05: Accept alert 'Account Deleted Sucessfully'");
		accountPage.acceptAlert(driver);
		manageHomePage = PageGeneratorManager.getManageHomePage(driver);
		
		log.info("Delete Account [Valid Account Number] - Step 06: Click to 'Delete Account'");
		accountPage.openToPageInListboxByName(driver, "Delete Account");
		
		log.info("Delete Account [Valid Account Number] - Step 07: Enter to 'Account No' with value '" + accountID + "'");
		accountPage.enterToTextboxByName(driver, accountID, "accountno");
		
		log.info("Delete Account [Valid Account Number] - Step 08: Click to 'Submit' Button");
		accountPage.clickToButtonByValue(driver, "Submit");
		
		log.info("Delete Account [Valid Account Number] - Step 09: Accept alert 'Do you really want to delete this Account?'");
		accountPage.acceptAlert(driver);
		
		log.info("Delete Account [Valid Account Number] - Step 10: Verify Accept alert 'Account does not exist' is displayed");
		verifyEquals(accountPage.getTextAlert(driver), "Account does not exist");
	
		log.info("Delete Account [Valid Account Number] - Step 11: Accept alert 'Account does not exist'");
		accountPage.acceptAlert(driver);
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	FakerConfig fakeData;
	RegisterPO registerPage;
	ManageHomePagePO manageHomePage;
	CustomerPO customerPage;
	AccountPO accountPage;
	
	String emailID, userID, passwordGenerate, customerID;
	String customerName, dateOfBirth, Address, city, state, PIN, mobileNumber, email, password, initialDeposit, accountID;
}

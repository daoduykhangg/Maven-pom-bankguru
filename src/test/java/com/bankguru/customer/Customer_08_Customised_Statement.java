package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.CustomisedStatementPO;
import pageObjects.ManageHomePagePO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;
import pageUIs.AbstractPageUI;
import utilities.FakerConfig;

public class Customer_08_Customised_Statement extends AbstractTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = getBrowserDriver(browserName, urlValue);

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

		log.info("Pre-condition - Step 010: Click to 'Customised Statement' Link");
		manageHomePage.openToPageInListboxByName(driver, "Customised Statement");
		customisedStatementPage = PageGeneratorManager.getCustomisedStatementPage(driver);
	}

	@Test
	public void TC_01_Customised_Statement_With_Account_Number_Can_Not_Be_Empty() {
		log.info("Customised Statement [Account Number can not be empty] - Step 01: Enter to 'Account No' Textbox with empty value");
		customisedStatementPage.enterToTextboxByName(driver, "", "accountno");
		
		log.info("Customised Statement [Account Number can not be empty] - Step 02: Enter to 'Account No' Textbox with 'TAB' key");
		customisedStatementPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "accountno");
		
		log.info("Customised Statement [Account Number can not be empty] - Step 02: Verify Error Message is displayed with value 'Account Number must not be blank'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "2"), "Account Number must not be blank");
	}

	@Test
	public void TC_02_Customised_Statement_With_Account_Number_Must_Be_Numberic() {
		log.info("Customised Statement [Account Number must be numberic] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Account Number must be numberic] - Step 02: Enter to 'Account No' Textbox with value 'Abc123'");
		customisedStatementPage.enterToTextboxByName(driver, "Abc123", "accountno");
		
		log.info("Customised Statement [Account Number must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}

	@Test
	public void TC_03_Customised_Statement_With_Account_Number_Can_Not_Have_Special_Character() {
		log.info("Customised Statement [Account Number can not have special character] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Account Number can not have special character] - Step 02: Enter to 'Account No' Textbox with value '!@#123'");
		customisedStatementPage.enterToTextboxByName(driver, "!@#123", "accountno");
		
		log.info("Customised Statement [Account Number can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "2"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_Customised_Statement_With_Account_Number_Can_Not_Have_Blank_Space() {
		log.info("Customised Statement [Account Number can not have blank space] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Account Number can not have blank space] - Step 02: Enter to 'Account No' Textbox with value '123 12'");
		customisedStatementPage.enterToTextboxByName(driver, "123 12", "accountno");
		
		log.info("Customised Statement [Account Number can not have blank space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}

	@Test
	public void TC_05_Customised_Statement_With_Account_Number_First_Character_Can_Not_Be_Space() {
		log.info("Customised Statement [Account Number first character can not be space] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Account Number first character can not be space] - Step 02: Enter to 'Account No' Textbox with value ' '");
		customisedStatementPage.enterToTextboxByName(driver, " ", "accountno");
		
		log.info("Customised Statement [Account Number first character can not be space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "2"), "Characters are not allowed");
	}
	
	@Test
	public void TC_06_Customised_Statement_With_Minimum_Transaction_Value_Must_Be_Numberic() {
		log.info("Customised Statement [Minimum transaction value must be numberic] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Minimum transaction value must be numberic] - Step 02: Enter to 'Minimum Transaction Value' Text box with value 'Abc123'");
		customisedStatementPage.enterToTextboxByName(driver, "Abc123", "amountlowerlimit");
		
		log.info("Customised Statement [Minimum transaction value must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "12"), "Characters are not allowed");
	}
	
	@Test
	public void TC_07_Customised_Statement_With_Minimum_Transaction_Value_Can_Not_Have_Special_Character() {
		log.info("Customised Statement [Minimum transaction value can not have special character] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Minimum transaction value can not have special character] - Step 02: Enter to 'Minimum Transaction Value' Text box with value '!@#123'");
		customisedStatementPage.enterToTextboxByName(driver, "!@#123", "amountlowerlimit");
		
		log.info("Customised Statement [Minimum transaction value can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "12"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_08_Customised_Statement_With_Minimum_Transaction_Value_Can_Not_Have_Blank_Space() {
		log.info("Customised Statement [Minimum transaction value can not have blank space] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Minimum transaction value can not have blank space] - Step 02: Enter to 'Minimum Transaction Value' Text box with value '123 12'");
		customisedStatementPage.enterToTextboxByName(driver, "123 12", "amountlowerlimit");
		
		log.info("Customised Statement [Minimum transaction value can not have blank space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "12"), "Characters are not allowed");
	}
	
	@Test
	public void TC_09_Customised_Statement_With_Minimum_Transaction_Value_First_Character_Can_Not_Be_Space() {
		log.info("Customised Statement [Minimum transaction value first character can not be space] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Minimum transaction value first character can not be space] - Step 02: Enter to 'Minimum Transaction Value' Text box with value ' '");
		customisedStatementPage.enterToTextboxByName(driver, " ", "amountlowerlimit");
		
		log.info("Customised Statement [Minimum transaction value first character can not be space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "12"), "Characters are not allowed");
	}
	
	@Test
	public void TC_10_Customised_Statement_With_Number_of_Transaction_Must_Be_Numberic() {
		log.info("Customised Statement [Number of Transaction value must be numberic] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Number of Transaction value must be numberic] - Step 02: Enter to 'Number of Transaction' Text box with value 'Abc123'");
		customisedStatementPage.enterToTextboxByName(driver, "Abc123", "numtransaction");
		
		log.info("Customised Statement [Number of Transaction value must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "13"), "Characters are not allowed");
	}
	
	@Test
	public void TC_11_Customised_Statement_With_Number_of_Transaction_Can_Not_Have_Special_Character() {
		log.info("Customised Statement [Number of Transaction value can not have special character] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Number of Transaction value can not have special character] - Step 02: Enter to 'Number of Transaction' Text box with value '!@#123'");
		customisedStatementPage.enterToTextboxByName(driver, "!@#123", "numtransaction");
		
		log.info("Customised Statement [Number of Transaction value can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "13"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_12_Customised_Statement_With_Number_of_Transaction_Can_Not_Have_Blank_Space() {
		log.info("Customised Statement [Number of Transaction value can not have blank space] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Number of Transaction value can not have blank space] - Step 02: Enter to 'Number of Transaction' Text box with value '123 12'");
		customisedStatementPage.enterToTextboxByName(driver, "123 12", "numtransaction");
		
		log.info("Customised Statement [Number of Transaction value can not have blank space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "13"), "Characters are not allowed");
	}

	@Test
	public void TC_13_Customised_Statement_With_Number_of_Transaction_First_Character_Can_Not_Be_Space() {
		log.info("Customised Statement [Number of Transaction value first character can not be space] - Step 01: Refresh current page");
		customisedStatementPage.refreshCurrentPage(driver);
		
		log.info("Customised Statement [Number of Transaction value first character can not be space] - Step 02: Enter to 'Number of Transaction' Text box with value ' '");
		customisedStatementPage.enterToTextboxByName(driver, " ", "numtransaction");
		
		log.info("Customised Statement [Number of Transaction value first character can not be space] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(customisedStatementPage.getErrorMessageValueByID(driver, "13"), "Characters are not allowed");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	FakerConfig fakeData;
	String emailID, userID, passwordGenerate;
	RegisterPO registerPage;
	ManageHomePagePO manageHomePage;
	CustomisedStatementPO customisedStatementPage;
}

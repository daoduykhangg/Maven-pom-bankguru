package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.ManageHomePagePO;
import pageObjects.CustomerPO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;
import pageUIs.AbstractPageUI;
import utilities.FakerConfig;

public class Customer_01_New_Customer extends AbstractTest{
	
	@Parameters({"browser", "url"})
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
		password = registerPage.getPasswordValue();
		
		log.info("Pre-condition - Step 06: Back to the first page");
		registerPage.backToPage(driver);
		registerPage.backToPage(driver);
		
		log.info("Pre-condition - Step 07: Enter to 'Email ID' Textbox");
		registerPage.enterToTextboxByName(driver, userID, "uid");
		
		log.info("Pre-condition - Step 08: Enter to 'Password' Textbox");
		registerPage.enterToTextboxByName(driver, password, "password");
		
		log.info("Pre-condition - Step 09: Click to 'LOGIN' Button");
		registerPage.clickToButtonByValue(driver, "LOGIN");
		manageHomePage = PageGeneratorManager.getManageHomePage(driver);
	}

	@Test
	public void TC_01_New_Customer_With_Name_Can_Not_Be_Empty() {
		log.info("New customer [Name cannot be empty] - Step 01: Click to 'New Customer' Link");
		manageHomePage.openToPageInListboxByName(driver, "New Customer");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
		log.info("New customer [Name cannot be empty] - Step 02: Click to 'Customer Name' Textbox");
		customerPage.enterToTextboxByName(driver, "", "name");
		
		log.info("New customer [Name cannot be empty] - Step 03: Enter to 'Customer Name' with value 'TAB' key");
		customerPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "name");
		
		log.info("New customer [Name cannot be empty] - Step 04: Verify Error message is displayed with value 'Customer name must not be blank'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, ""), "Customer name must not be blank");
	}

	@Test
	public void TC_02_New_Customer_With_Name_Can_Not_Be_Numberic() {
		log.info("New customer [Name cannot be numberic] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Name cannot be numberic] - Step 02: Enter to 'Customer Name' Textbox with value '123'");
		customerPage.enterToTextboxByName(driver, "123", "name");
		
		log.info("New customer [Name cannot be numberic] - Step 03: Verify Error message is displayed with value 'Numbers are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, ""), "Numbers are not allowed");
	}
	
	@Test
	public void TC_03_New_Customer_With_Name_Can_Not_Have_Special_Characters() {
		log.info("New customer [Name cannot have special characters] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Name cannot have special characters] - Step 02: Enter to 'Customer Name' Textbox with value '!@#'");
		customerPage.enterToTextboxByName(driver, "!@#", "name");
		
		log.info("New customer [Name cannot have special characters] - Step 03: Verify Error message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, ""), "Special characters are not allowed");
	}
	
	@Test
	public void TC_04_New_Customer_With_Name_Can_Not_Have_First_Characters_As_Blank_Space() {
		log.info("New customer [Name cannot have first characters as blank space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Name cannot have first characters as blank space] - Step 02: Enter to 'Customer Name' Textbox with value ' '");
		customerPage.enterToTextboxByName(driver, " ", "name");
		
		log.info("New customer [Name cannot have first characters as blank space] - Step 03: Verify Error message is displayed with value 'First character can not have space'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, ""), "First character can not have space");
	}

	@Test
	public void TC_05_New_Customer_With_Address_Can_Not_Be_Empty() {
		log.info("New customer [Address cannot be empty] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Address cannot be empty] - Step 02: Click to 'Address' Textbox");
		customerPage.enterToTextareaByName(driver, "", "addr");
		
		log.info("New customer [Address cannot be empty] - Step 03: Enter to 'Address' with value 'TAB' key");
		customerPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BY_NAME, Keys.TAB, "addr");
		
		log.info("New customer [Address cannot be empty] - Step 04: Verify Error message is displayed with value 'Address Field must not be blank'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "3"), "Address Field must not be blank");
	}
	
	@Test
	public void TC_06_New_Customer_With_Address_Can_Not_Have_First_Characters_As_Blank_Space() {
		log.info("New customer [Address cannot have first characters as blank space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Address cannot have first characters as blank space] - Step 02: Enter to 'Address' Textbox with value ' '");
		customerPage.enterToTextareaByName(driver, " ", "addr");
		
		log.info("New customer [Address cannot have first characters as blank space] - Step 03: Verify Error message is displayed with value 'First character can not have space'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "3"), "First character can not have space");
	}

	@Test
	public void TC_07_New_Customer_With_City_Can_Not_Be_Empty() {
		log.info("New customer [City cannot be empty] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [City cannot be empty] - Step 02: Click to 'City' Textbox");
		customerPage.enterToTextboxByName(driver, "", "city");
		
		log.info("New customer [City cannot be empty] - Step 03: Enter to 'City' with value 'TAB' key");
		customerPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "city");
		
		log.info("New customer [City cannot be empty] - Step 04: Verify Error message is displayed with value 'City Field must not be blank'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "4"), "City Field must not be blank");
	}
	
	@Test
	public void TC_08_New_Customer_With_City_Can_Not_Be_Numberic() {
		log.info("New customer [City cannot be numberic] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [City cannot be numberic] - Step 02: Enter to 'City' Textbox with value '123'");
		customerPage.enterToTextboxByName(driver, "123", "city");
		
		log.info("New customer [City cannot be numberic] - Step 03: Verify Error message is displayed with value 'Numbers are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "4"), "Numbers are not allowed");
	}
	
	@Test
	public void TC_09_New_Customer_With_City_Can_Not_Have_Special_Characters() {
		log.info("New customer [City cannot have special characters] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [City cannot have special characters] - Step 02: Enter to 'City' Textbox with value '!@#'");
		customerPage.enterToTextboxByName(driver, "!@#", "city");
		
		log.info("New customer [City cannot have special characters] - Step 03: Verify Error message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "4"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_10_New_Customer_With_City_Can_Not_Have_First_Characters_As_Blank_Space() {
		log.info("New customer [City cannot have first characters as blank space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [City cannot have first characters as blank space] - Step 02: Enter to 'City' Textbox with value ' '");
		customerPage.enterToTextboxByName(driver, " ", "city");
		
		log.info("New customer [City cannot have first characters as blank space] - Step 03: Verify Error message is displayed with value 'First character can not have space'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "4"), "First character can not have space");
	}
	
	@Test
	public void TC_11_New_Customer_With_State_Can_Not_Be_Empty() {
		log.info("New customer [State cannot be empty] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [State cannot be empty] - Step 02: Click to 'State' Textbox");
		customerPage.enterToTextboxByName(driver, "", "state");
		
		log.info("New customer [State cannot be empty] - Step 03: Enter to 'State' with value 'TAB' key");
		customerPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "state");
		
		log.info("New customer [State cannot be empty] - Step 04: Verify Error message is displayed with value 'State must not be blank'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "5"), "State must not be blank");
	}
	
	@Test
	public void TC_12_New_Customer_With_State_Can_Not_Be_Numberic() {
		log.info("New customer [State cannot be numberic] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [State cannot be numberic] - Step 02: Enter to 'State' Textbox with value '123'");
		customerPage.enterToTextboxByName(driver, "123", "state");
		
		log.info("New customer [State cannot be numberic] - Step 03: Verify Error message is displayed with value 'Numbers are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "5"), "Numbers are not allowed");
	}

	@Test
	public void TC_13_New_Customer_With_State_Can_Not_Have_Special_Characters() {
		log.info("New customer [State cannot have special characters] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [State cannot have special characters] - Step 02: Enter to 'State' Textbox with value '!@#'");
		customerPage.enterToTextboxByName(driver, "!@#", "state");
		
		log.info("New customer [State cannot have special characters] - Step 03: Verify Error message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "5"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_14_New_Customer_With_State_Can_Not_Have_First_Characters_As_Blank_Space() {
		log.info("New customer [State cannot have first characters as blank space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [State cannot have first characters as blank space] - Step 02: Enter to 'State' Textbox with value ' '");
		customerPage.enterToTextboxByName(driver, " ", "state");
		
		log.info("New customer [State cannot have first characters as blank space] - Step 03: Verify Error message is displayed with value 'First character can not have space'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "5"), "First character can not have space");
	}

	@Test
	public void TC_15_New_Customer_With_PIN_must_be_numberic() {
		log.info("New customer [PIN must be numberic] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [PIN must be numberic] - Step 02: Enter to 'PIN' Textbox with value '123PIN'");
		customerPage.enterToTextboxByName(driver, "123PIN", "pinno");
		
		log.info("New customer [PIN must be numberic] - Step 03: Verify Error message is displayed with value 'Characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "6"), "Characters are not allowed");
	}
	
	@Test
	public void TC_16_New_Customer_With_PIN_Can_Not_Be_Empty() {
		log.info("New customer [PIN cannot be empty] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [PIN cannot be empty] - Step 02: Click to 'PIN' Textbox");
		customerPage.enterToTextboxByName(driver, "", "pinno");
		
		log.info("New customer [PIN cannot be empty] - Step 03: Enter to 'PIN' with value 'TAB' key");
		customerPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "pinno");
		
		log.info("New customer [PIN cannot be empty] - Step 04: Verify Error message is displayed with value 'PIN Code must not be blank'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "6"), "PIN Code must not be blank");
	}
	
	@Test
	public void TC_17_New_Customer_With_PIN_Must_Have_6_Digits() {
		log.info("New customer [PIN must have 6 digits] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [PIN must have 6 digits] - Step 02: Enter to 'PIN' Textbox with value '123'");
		customerPage.enterToTextboxByName(driver, "123", "pinno");
		
		log.info("New customer [PIN must have 6 digits] - Step 03: Verify Error message is displayed with value 'PIN Code must have 6 Digits'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "6"), "PIN Code must have 6 Digits");
	}
	
	@Test
	public void TC_18_New_Customer_With_PIN_Can_Not_Have_Special_Characters() {
		log.info("New customer [PIN cannot have special characters] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [PIN cannot have special characters] - Step 02: Enter to 'PIN' Textbox with value '!@#'");
		customerPage.enterToTextboxByName(driver, "!@#", "pinno");
		
		log.info("New customer [PIN cannot have special characters] - Step 03: Verify Error message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "6"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_19_New_Customer_With_PIN_Can_Not_Have_First_Characters_As_Blank_Space() {
		log.info("New customer [PIN cannot have first characters as blank space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [PIN cannot have first characters as blank space] - Step 02: Enter to 'PIN' Textbox with value ' 123'");
		customerPage.enterToTextboxByName(driver, " 123", "pinno");
		
		log.info("New customer [PIN cannot have first characters as blank space] - Step 03: Verify Error message is displayed with value 'First character can not have space'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "6"), "First character can not have space");
	}

	@Test
	public void TC_20_New_Customer_With_PIN_Can_Not_Have_Space() {
		log.info("New customer [PIN cannot have space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [PIN cannot have space] - Step 02: Enter to 'PIN' Textbox with value '123 123'");
		customerPage.enterToTextboxByName(driver, "123 123", "pinno");
		
		log.info("New customer [PIN cannot have space] - Step 03: Verify Error message is displayed with value 'Characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "6"), "Characters are not allowed");
	}

	@Test
	public void TC_21_New_Customer_With_Mobile_Number_Can_Not_Be_Empty() {
		log.info("New customer [Mobile Number cannot be empty] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Mobile Number cannot be empty] - Step 02: Click to 'Mobile Number' Textbox");
		customerPage.enterToTextboxByName(driver, "", "telephoneno");
		
		log.info("New customer [Mobile Number cannot be empty] - Step 03: Enter to 'Mobile Number' with value 'TAB' key");
		customerPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "telephoneno");
		
		log.info("New customer [Mobile Number cannot be empty] - Step 04: Verify Error message is displayed with value 'Mobile no must not be blank'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "7"), "Mobile no must not be blank");
	}
	
	@Test
	public void TC_22_New_Customer_With_Mobile_Number_Can_Not_Have_First_Characters_As_Blank_Space() {
		log.info("New customer [Mobile Number cannot have first characters as blank space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Mobile Number cannot have first characters as blank space] - Step 02: Enter to 'Mobile Number' Textbox with value ' 123'");
		customerPage.enterToTextboxByName(driver, " 123", "telephoneno");
		
		log.info("New customer [Mobile Number cannot have first characters as blank space] - Step 03: Verify Error message is displayed with value 'First character can not have space'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "7"), "First character can not have space");
	}
	
	@Test
	public void TC_23_New_Customer_With_Mobile_Number_Can_Not_Have_Space() {
		log.info("New customer [Mobile Number cannot have space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Mobile Number cannot have space] - Step 02: Enter to 'Mobile Number' Textbox with value '123 123'");
		customerPage.enterToTextboxByName(driver, "123 123", "telephoneno");
		
		log.info("New customer [Mobile Number cannot have space] - Step 03: Verify Error message is displayed with value 'Characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "7"), "Characters are not allowed");
	}
	
	@Test
	public void TC_24_New_Customer_With_Mobile_Number_Can_Not_Special_Characters() {
		log.info("New customer [Mobile Number cannot have special characters] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Mobile Number cannot have special characters] - Step 02: Enter to 'PIN' Textbox with value '093*66!$5!'");
		customerPage.enterToTextboxByName(driver, "093*66!$5!", "telephoneno");
		
		log.info("New customer [Mobile Number cannot have special characters] - Step 03: Verify Error message is displayed with value 'Special characters are not allowed'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "7"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_25_New_Customer_With_Email_Can_Not_Be_Empty() {
		log.info("New customer [Email cannot be empty] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Email cannot be empty] - Step 02: Click to 'Email' Textbox");
		customerPage.enterToTextboxByName(driver, "", "emailid");
		
		log.info("New customer [Email cannot be empty] - Step 03: Enter to 'Email' with value 'TAB' key");
		customerPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "emailid");
		
		log.info("New customer [Email cannot be empty] - Step 04: Verify Error message is displayed with value 'Email-ID must not be blank'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "9"), "Email-ID must not be blank");
	}
	
	@Test
	public void TC_26_New_Customer_With_Email_Must_Be_Correct_format() {
		log.info("New customer [Email must be Correct format] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Email must be Correct format] - Step 02: Enter to 'Email' Textbox with value 'guru99@gmail'");
		customerPage.enterToTextboxByName(driver, "guru99@gmail", "emailid");
		
		log.info("New customer [Email must be Correct format] - Step 03: Verify Error message is displayed with value 'Email-ID is not valid'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "9"), "Email-ID is not valid");
	}
	@Test
	public void TC_27_New_Customer_With_Email_Can_Not_Have_Space() {
		log.info("New customer [Email cannot have space] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Email cannot have space] - Step 02: Enter to 'Email' Textbox with value 'guru99 @gmail'");
		customerPage.enterToTextboxByName(driver, "guru99 @gmail", "emailid");
		
		log.info("New customer [Email cannot have space] - Step 03: Verify Error message is displayed with value 'Email-ID is not valid'");
		verifyEquals(customerPage.getErrorMessageValueByID(driver, "9"), "Email-ID is not valid");
	}
	
	@Test
	public void TC_28_New_Customer_With_Check_All_Fields_Name_Are_As_Requirement() {
		log.info("New customer [Check all fields name] - Step 01: Refresh current page");
		customerPage.refreshCurrentPage(driver);
		
		log.info("New customer [Check all fields name] - Step 02: Verify fields name are displayed as requirement");
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "Customer Name"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "Gender"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "Date of Birth"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "Address"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "City"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "State"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "PIN"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "Mobile Number"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "E-mail"));
		verifyTrue(customerPage.isFieldNameDisplayed(driver, "Password"));
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
	
	String emailID, userID, password;
}

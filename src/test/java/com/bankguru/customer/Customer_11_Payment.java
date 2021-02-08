package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.CustomerPO;
import pageObjects.ManageHomePagePO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;
import utilities.FakerConfig;

public class Customer_11_Payment extends AbstractTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);

		fakeData = FakerConfig.getFakeData();

		emailID = fakeData.getEmailAddress();
		customerName = fakeData.getFirstname() + " " + fakeData.getLastname();
		dob = "03/01/2021";
		birthdate = "2021-03-01";
		address = "897 O Connell Row";
		city = "Bronx";
		state = "New York";
		pin = "210896";
		mobileNumber = "0938666999";
		email = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		gender = "male";

		editAddress = "1883 Cursus Avenue";
		editCity = "Houston";
		editState = "Texas";
		editPin = "166455";
		editMobileNumber = "0938123456";
		editEamil = fakeData.getEmailAddress();

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

		log.info("New customer [Name cannot be empty] - Step 01: Click to 'New Customer' Link");
		manageHomePage.openToPageInListboxByName(driver, "New Customer");
		customerPage = PageGeneratorManager.getNewCustomerPage(driver);
	}

	@Test
	public void TC_01_Create_New_Customer_Account_And_Check_Created_Successfully() {
		log.info("Create new customer - Step 01: Click to 'New Customer' Link");
		manageHomePage.openToPageInListboxByName(driver, "New Customer");
		customerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Create new customer - Step 02: Enter to 'Customer Name' Textbox with value'" + customerName + "'");
		customerPage.enterToTextboxByName(driver, customerName, "name");

		log.info("Create new customer - Step 03: Click to 'GenderMale' Radio Button");
		customerPage.clickToRadioButtonByValue("m");

		log.info("Create new customer - Step 04: Enter to 'Date of Birth' Textbox with value'" + dob + "'");
		customerPage.enterToTextboxByName(driver, dob, "dob");

		log.info("Create new customer - Step 05: Enter to 'Address' Textarea with value'" + address + "'");
		customerPage.enterToTextareaByName(driver, address, "addr");

		log.info("Create new customer - Step 06: Enter to 'City' Textbox with value'" + city + "'");
		customerPage.enterToTextboxByName(driver, city, "city");

		log.info("Create new customer - Step 07: Enter to 'State' Textbox with value'" + state + "'");
		customerPage.enterToTextboxByName(driver, state, "state");

		log.info("Create new customer - Step 08: Enter to 'PIN' Textbox with value'" + pin + "'");
		customerPage.enterToTextboxByName(driver, pin, "pinno");

		log.info("Create new customer - Step 09: Enter to 'Mobile Number' Textbox with value'" + mobileNumber + "'");
		customerPage.enterToTextboxByName(driver, mobileNumber, "telephoneno");

		log.info("Create new customer - Step 10: Enter to 'E-mail' Textbox with value'" + email + "'");
		customerPage.enterToTextboxByName(driver, email, "emailid");

		log.info("Create new customer - Step 11: Enter to 'Password' Textbox with value'" + password + "'");
		customerPage.enterToTextboxByName(driver, password, "password");

		log.info("Create new customer - Step 12: Click to 'Submit' Button");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("Create new customer - Step 13: Verify Success Message in table is displayed with value 'Customer Registered Successfully!!!'");
		customerPage.getHeadingTextInTable(driver);

		log.info("Create new customer - Step 14: Verify Customer info is displayed correctly");
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Customer Name"), customerName);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Gender"), gender);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Birthdate"), birthdate);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Address"), address);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "City"), city);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "State"), state);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Pin"), pin);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Mobile No."), mobileNumber);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Email"), email);

		log.info("Create new customer - Step 15: Get CustomerID in table");
		customerID = customerPage.getCustomerIDValue();
	}

	@Test
	public void TC_02_Edit_Customer_Account() {
		log.info("Edit customer account - Step 01: Click to 'Edit Customer' Link");
		customerPage.openToPageInListboxByName(driver, "Edit Customer");

		log.info("Edit customer account - Step 02: Enter to 'Customer ID' Textbox with value '" + customerID + "'");
		customerPage.enterToTextboxByName(driver, customerID, "cusid");

		log.info("Edit customer account - Step 03: Click to 'Submit' Button");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("Edit customer account - Step 04: Verify Heading 'Edit Customer' is displayed");
		verifyEquals(customerPage.getHeadingTextInTable(driver), "Edit Customer");

		log.info("Edit customer account - Step 05: Enter to 'Address' Textarea with value'" + editAddress + "'");
		customerPage.enterToTextareaByName(driver, editAddress, "addr");

		log.info("Edit customer account - Step 06: Enter to 'City' Textbox with value '" + editCity + "'");
		customerPage.enterToTextboxByName(driver, editCity, "city");

		log.info("Edit customer account - Step 07: Enter to 'State' Textbox with value '" + editState + "'");
		customerPage.enterToTextboxByName(driver, editState, "state");

		log.info("Edit customer account - Step 08: Enter to 'PIN' Textbox with value '" + editPin + "'");
		customerPage.enterToTextboxByName(driver, editPin, "pinno");

		log.info("Edit customer account - Step 09: Enter to 'Mobile Number' Textbox with value '" + editMobileNumber + "'");
		customerPage.enterToTextboxByName(driver, editMobileNumber, "telephoneno");

		log.info("Edit customer account - Step 01: Enter to 'E-mail' Textbox with value '" + editEamil + "'");
		customerPage.enterToTextboxByName(driver, editEamil, "emailid");

		log.info("Edit customer account - Step 11: Click to 'Submit' Button");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("Edit customer account - Step 12: Verify Success Message in table is displayed with value 'Customer details updated Successfully!!!'");
		verifyEquals(customerPage.getHeadingTextInTable(driver), "Customer details updated Successfully!!!");

		log.info("Edit customer account - Step 13: Verify Customer info is displayed correctly");
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Customer ID"), customerID);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Customer Name"), customerName);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Gender"), gender);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Birthdate"), birthdate);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Address"), editAddress);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "City"), editCity);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "State"), editState);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Pin"), editPin);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Mobile No."), editMobileNumber);
		verifyEquals(customerPage.getCustomerInfoValueByFieldname(driver, "Email"), editEamil);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	WebDriver driver;
	FakerConfig fakeData;
	String emailID, userID, passwordGenerate;
	String customerName, dob, address, city, state, pin, mobileNumber, email, password, gender, birthdate, customerID;
	String editAddress, editCity, editState, editPin, editMobileNumber, editEamil;
	RegisterPO registerPage;
	ManageHomePagePO manageHomePage;
	CustomerPO customerPage;
}

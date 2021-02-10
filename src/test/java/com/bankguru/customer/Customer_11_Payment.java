package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.AccountPO;
import pageObjects.CustomerPO;
import pageObjects.DepositPO;
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
		editEmail = fakeData.getEmailAddress();

		item = "Savings";
		initialDeposit = "50000";
		editItem = "Current";
		dateOfOpening = getToday();

		depositAmount = "5000";
		depositDescription = "Deposit";
		withdrawAmount = "15000";
		withdrawDescription = "Withdraw";
		currentDepositAmount = "55000";
		currentWithdrawAmount = "40000";
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
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Customer Name"), customerName);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Gender"), gender);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Birthdate"), birthdate);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Address"), address);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "City"), city);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "State"), state);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Pin"), pin);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Mobile No."), mobileNumber);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Email"), email);

		log.info("Create new customer - Step 15: Get 'CustomerID' in table");
		customerID = customerPage.getCustomerIDValue();
	}

	@Test(dependsOnMethods = "TC_01_Create_New_Customer_Account_And_Check_Created_Successfully")
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

		log.info("Edit customer account - Step 01: Enter to 'E-mail' Textbox with value '" + editEmail + "'");
		customerPage.enterToTextboxByName(driver, editEmail, "emailid");

		log.info("Edit customer account - Step 11: Click to 'Submit' Button");
		customerPage.clickToButtonByValue(driver, "Submit");

		log.info("Edit customer account - Step 12: Verify Success Message in table is displayed with value 'Customer details updated Successfully!!!'");
		verifyEquals(customerPage.getHeadingTextInTable(driver), "Customer details updated Successfully!!!");

		log.info("Edit customer account - Step 13: Verify Customer info is displayed correctly");
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Customer ID"), customerID);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Customer Name"), customerName);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Gender"), gender);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Birthdate"), birthdate);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Address"), editAddress);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "City"), editCity);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "State"), editState);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Pin"), editPin);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Mobile No."), editMobileNumber);
		verifyEquals(customerPage.getCustomerInfoValueByRowname(driver, "Email"), editEmail);
	}

	@Test(dependsOnMethods = { "TC_01_Create_New_Customer_Account_And_Check_Created_Successfully", "TC_02_Edit_Customer_Account" })
	public void TC_03_Add_New_Account() {
		log.info("Add new account - Step 01: Click to 'New Account' Link");
		customerPage.openToPageInListboxByName(driver, "New Account");
		accountPage = PageGeneratorManager.getAccountPage(driver);

		log.info("Add new account - Step 02: Enter to 'Customer id' Textbox with value '" + customerID + "'");
		accountPage.enterToTextboxByName(driver, customerID, "cusid");

		log.info("Add new account - Step 03: Select ' Account type' Dropdown with value '" + item + "'");
		accountPage.SelectDropdownListByName(driver, item, "selaccount");

		log.info("Add new account - Step 04: Enter to 'Initial deposit' Textbox with value '" + initialDeposit + "'");
		accountPage.enterToTextboxByName(driver, initialDeposit + "", "inideposit");

		log.info("Add new account - Step 05: Click to 'submit' Button");
		accountPage.clickToButtonByValue(driver, "submit");

		log.info("Add new account - Step 06: Verify Success Message is displayed with value 'Account Generated Successfully!!!'");
		verifyEquals(accountPage.getHeadingTextInTable(driver), "Account Generated Successfully!!!");

		log.info("Add new account - Step 07: Verify Account details are displayed correctly");
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Customer ID"), customerID);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Customer Name"), customerName);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Email"), editEmail);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Account Type"), item);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Date of Opening"), dateOfOpening);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Current Amount"), initialDeposit);

		log.info("Add new account - Step 08: get 'AccountID' in table");
		accountID = accountPage.getCustomerInfoValueByRowname(driver, "Account ID");
	}

	@Test(dependsOnMethods = { "TC_01_Create_New_Customer_Account_And_Check_Created_Successfully", "TC_02_Edit_Customer_Account", "TC_03_Add_New_Account" })
	public void TC_04_Edit_Account() {
		log.info("Edit account - Step 01: Click to 'Edit Account' Link");
		accountPage.openToPageInListboxByName(driver, "Edit Account");

		log.info("Edit account - Step 02: Enter to 'Account No' Textbox with value '" + accountID + "'");
		accountPage.enterToTextboxByName(driver, accountID, "accountno");

		log.info("Edit account - Step 03: Click to 'Submit' Button");
		accountPage.clickToButtonByValue(driver, "Submit");

		log.info("Edit account - Step 04: Select 'Type of Account' Dropdown with value '" + editItem + "'");
		accountPage.SelectDropdownListByName(driver, editItem, "a_type");

		log.info("Edit account - Step 05: Click to 'Submit' Button");
		accountPage.clickToButtonByValue(driver, "Submit");

		log.info("Edit account - Step 06: Verify Success Message is displayed with value 'Account details updated Successfully!!!'");
		verifyEquals(accountPage.getHeadingTextInTable(driver), "Account details updated Successfully!!!");

		log.info("Edit account - Step 07: Verify Account details are displayed correctly");
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Customer ID"), customerID);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Customer Name"), customerName);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Email"), editEmail);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Account Type"), editItem);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Date of Opening"), dateOfOpening);
		verifyEquals(accountPage.getCustomerInfoValueByRowname(driver, "Current Amount"), initialDeposit);
	}

	@Test(dependsOnMethods = { "TC_01_Create_New_Customer_Account_And_Check_Created_Successfully", "TC_02_Edit_Customer_Account", "TC_03_Add_New_Account", "TC_04_Edit_Account" })
	public void TC_05_Transfer_Money_Into_Current_Account() {
		log.info("Transfer money into current account - Step 01: Click to 'Deposit' Link");
		accountPage.openToPageInListboxByName(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);

		log.info("Transfer money into current account - Step 02: enter to 'Account No' Textbox with value '" + accountID + "'");
		depositPage.enterToTextboxByName(driver, accountID, "accountno");

		log.info("Transfer money into current account - Step 03: enter to 'Amount' Textbox with value '" + depositAmount + "'");
		depositPage.enterToTextboxByName(driver, depositAmount + "", "ammount");

		log.info("Transfer money into current account - Step 04: enter to 'Description' Textbox with value '" + depositDescription + "'");
		depositPage.enterToTextboxByName(driver, depositDescription, "desc");

		log.info("Transfer money into current account - Step 05: Click to 'Submit' Button");
		depositPage.clickToButtonByValue(driver, "Submit");

		log.info("Transfer money into current account - Step 06: Verify Success Message is displayed with value 'Transaction details of Deposit for Account " + accountID + "'");
		verifyEquals(depositPage.getHeadingTextInTable(driver), "Transaction details of Deposit for Account " + accountID + "");

		log.info("Transfer money into current account - Step 07: Verify Transaction details of Deposit are displayed correctly");
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Account No"), accountID);
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Amount Credited"), depositAmount);
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Type of Transaction"), depositDescription);
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Description"), depositDescription);
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Current Balance"), currentDepositAmount);
	}

	@Test(dependsOnMethods = { "TC_01_Create_New_Customer_Account_And_Check_Created_Successfully", "TC_02_Edit_Customer_Account", "TC_03_Add_New_Account", "TC_04_Edit_Account", "TC_05_Transfer_Money_Into_Current_Account" })
	public void TC_06_Withdraw_Money_From_Current_Account() {
		log.info("Withdraw money from current account - Step 01: Click to 'Withdrawal' Link");
		accountPage.openToPageInListboxByName(driver, "Withdrawal");
		depositPage = PageGeneratorManager.getDepositPage(driver);

		log.info("Withdraw money from current account - Step 02: enter to 'Account No' Textbox with value '" + accountID + "'");
		depositPage.enterToTextboxByName(driver, accountID, "accountno");

		log.info("Withdraw money from current account - Step 03: enter to 'Amount' Textbox with value '" + withdrawAmount + "'");
		depositPage.enterToTextboxByName(driver, withdrawAmount + "", "ammount");

		log.info("Withdraw money from current account - Step 04: enter to 'Description' Textbox with value '" + withdrawDescription + "'");
		depositPage.enterToTextboxByName(driver, withdrawDescription, "desc");

		log.info("Withdraw money from current account - Step 05: Click to 'Submit' Button");
		depositPage.clickToButtonByValue(driver, "Submit");

		log.info("Withdraw money from current account - Step 06: Verify Success Message is displayed with value 'Transaction details of Withdrawal for Account " + accountID + "'");
		verifyEquals(depositPage.getHeadingTextInTable(driver), "Transaction details of Withdrawal for Account " + accountID + "");

		log.info("Withdraw money from current account - Step 07: Verify Transaction details of Withdrawal are displayed correctly");
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Account No"), accountID);
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Amount Debited"), withdrawAmount);
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Type of Transaction"), "Withdrawal");
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Description"), withdrawDescription);
		verifyEquals(depositPage.getCustomerInfoValueByRowname(driver, "Current Balance"), currentWithdrawAmount);
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
	DepositPO depositPage;
	String emailID, userID, passwordGenerate;
	String customerName, dob, address, city, state, pin, mobileNumber, email, password, gender, birthdate, customerID;
	String editAddress, editCity, editState, editPin, editMobileNumber, editEmail;
	String item, editItem, dateOfOpening, accountID, depositDescription, withdrawDescription, transactionID;
	String initialDeposit, depositAmount, withdrawAmount, currentDepositAmount, currentWithdrawAmount;
}

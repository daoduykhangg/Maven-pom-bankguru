package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.FundTransferPO;
import pageObjects.ManageHomePagePO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;
import pageUIs.AbstractPageUI;
import utilities.FakerConfig;

public class Customer_09_Fund_Transfer extends AbstractTest {
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

		log.info("Pre-condition - Step 010: Click to 'Fund Transfer' Link");
		manageHomePage.openToPageInListboxByName(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
	}
	
	@Test
	public void TC_01_Fund_Transfer_With_Payers_Account_No_Can_Not_Be_Empty() {
		log.info("Fund Transfer [Payers Account No can not be empty] - Step 01: Enter to 'Payers Account' Textbox with empty value");
		fundTransferPage.enterToTextboxByName(driver, "", "payersaccount");
		
		log.info("Fund Transfer [Payers Account No can not be empty] - Step 02: Enter to 'Payers Account' Textbox with 'TAB' key");
		fundTransferPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "payersaccount");
		
		log.info("Fund Transfer [Payers Account No can not be empty] - Step 03: Verify Error Message is displayed with value 'Payers Account Number must not be blank'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "10"), "Payers Account Number must not be blank");
	}
	
	@Test
	public void TC_02_Fund_Transfer_With_Payers_Account_No_Must_Be_Numberic() {
		log.info("Fund Transfer [Payers Account No must be numberic] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Payers Account No must be numberic] - Step 02: Enter to 'Payers Account' Textbox with value 'Abc123'");
		fundTransferPage.enterToTextboxByName(driver, "Abc123", "payersaccount");
		
		log.info("Fund Transfer [Payers Account No must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "10"), "Characters are not allowed");
	}
	
	@Test
	public void TC_03_Fund_Transfer_With_Payers_Account_No_Can_Not_Have_Special_Character() {
		log.info("Fund Transfer [Payers Account No can not have special character] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Payers Account No can not have special character] - Step 02: Enter to 'Payers Account' Textbox with value '!@#123'");
		fundTransferPage.enterToTextboxByName(driver, "!@#123", "payersaccount");
		
		log.info("Fund Transfer [Payers Account No can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "10"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_04_Fund_Transfer_With_Payees_Account_No_Can_Not_Be_Empty() {
		log.info("Fund Transfer [Payees Account No can not be empty] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Payees Account No can not be empty] - Step 02: Enter to 'Payees account no' Textbox with empty value");
		fundTransferPage.enterToTextboxByName(driver, "", "payeeaccount");
		
		log.info("Fund Transfer [Payees Account No can not be empty] - Step 03: Enter to 'Payees account no' Textbox with 'TAB' key");
		fundTransferPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "payeeaccount");
		
		log.info("Fund Transfer [Payees Account No can not be empty] - Step 04: Verify Error Message is displayed with value 'Payees Account Number must not be blank'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "11"), "Payees Account Number must not be blank");
	}
	
	@Test
	public void TC_05_Fund_Transfer_With_Payees_Account_No_Must_Be_Numberic() {
		log.info("Fund Transfer [Payees Account No must be numberic] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Payees Account No must be numberic] - Step 02: Enter to 'Payees account no' Textbox with value 'Abc123'");
		fundTransferPage.enterToTextboxByName(driver, "Abc123", "payeeaccount");
		
		log.info("Fund Transfer [Payees Account No must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "11"), "Characters are not allowed");
	}
	
	@Test
	public void TC_06_Fund_Transfer_With_Payees_Account_No_Not_Have_Special_Character() {
		log.info("Fund Transfer [Payees Account No can not have special character] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Payees Account No can not have special character] - Step 02: Enter to 'Payees account no' Textbox with value '!@#123'");
		fundTransferPage.enterToTextboxByName(driver, "!@#123", "payeeaccount");
		
		log.info("Fund Transfer [Payees Account No can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "11"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_07_Fund_Transfer_With_Amount_Can_Not_Be_Empty() {
		log.info("Fund Transfer [Amount can not be empty] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Amount can not be empty] - Step 02: Enter to 'Amount' Textbox with empty value");
		fundTransferPage.enterToTextboxByName(driver, "", "ammount");
		
		log.info("Fund Transfer [Amount can not be empty] - Step 03: Enter to 'Amount' Textbox with 'TAB' key");
		fundTransferPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "ammount");
		
		log.info("Fund Transfer [Amount can not be empty] - Step 04: Verify Error Message is displayed with value 'Amount field must not be blank'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "1"), "Amount field must not be blank");
	}
	
	@Test
	public void TC_08_Fund_Transfer_With_Amount_Must_Be_Numberic() {
		log.info("Fund Transfer [Amount must be numberic] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Amount must be numberic] - Step 02: Enter to 'Amount' Textbox with value 'Abc123'");
		fundTransferPage.enterToTextboxByName(driver, "Abc123", "ammount");
		
		log.info("Fund Transfer [Amount must be numberic] - Step 03: Verify Error Message is displayed with value 'Characters are not allowed'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "1"), "Characters are not allowed");
	}
	
	@Test
	public void TC_09_Fund_Transfer_With_Amount_Not_Have_Special_Character() {
		log.info("Fund Transfer [Amount can not have special character] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Amount can not have special character] - Step 02: Enter to 'Amount' Textbox with value '!@#123'");
		fundTransferPage.enterToTextboxByName(driver, "!@#123", "ammount");
		
		log.info("Fund Transfer [Amount can not have special character] - Step 03: Verify Error Message is displayed with value 'Special characters are not allowed'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "1"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_10_Fund_Transfer_With_Description_Can_Not_Be_Empty() {
		log.info("Fund Transfer [Description can not be empty] - Step 01: Refresh current page");
		fundTransferPage.refreshCurrentPage(driver);
		
		log.info("Fund Transfer [Description can not be empty] - Step 02: Enter to 'Description' Textbox with empty value");
		fundTransferPage.enterToTextboxByName(driver, "", "desc");
		
		log.info("Fund Transfer [Description can not be empty] - Step 03: Enter to 'Description' Textbox with 'TAB' key");
		fundTransferPage.sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, "desc");
		
		log.info("Fund Transfer [Description can not be empty] - Step 04: Verify Error Message is displayed with value 'Description can not be blank'");
		verifyEquals(fundTransferPage.getErrorMessageValueByID(driver, "17"), "Description can not be blank");
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
	FundTransferPO fundTransferPage;
}

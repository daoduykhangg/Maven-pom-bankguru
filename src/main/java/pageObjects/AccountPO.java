package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.AccountPageUI;

public class AccountPO extends AbstractPage{
	WebDriver driver;

	public AccountPO(WebDriver driver) {
		this.driver = driver;
	}

	public String getAccountIDValueInTable() {
		waitForElementVisible(driver, AccountPageUI.ACCOUNT_ID_VALUE);
		return getTextElement(driver, AccountPageUI.ACCOUNT_ID_VALUE);
	}

	public Object getCustomerIDValue() {
		waitForElementVisible(driver, AccountPageUI.CUSTOMER_ID_VALUE);
		return getElementAttribute(driver, AccountPageUI.CUSTOMER_ID_VALUE, "value");
	}

	public Object getBalanceValue() {
		waitForElementVisible(driver, AccountPageUI.BALANCE_VALUE);
		return getElementAttribute(driver, AccountPageUI.BALANCE_VALUE, "value");
	}
}

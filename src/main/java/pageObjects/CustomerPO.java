package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.CustomerPageUI;

public class CustomerPO extends AbstractPage{
	WebDriver driver;

	public CustomerPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRadioButtonByValue(String value) {
		waitForElementClickable(driver, CustomerPageUI.DYNAMIC_GENDER_RADIO_BUTTON, value);
		clickToElement(driver, CustomerPageUI.DYNAMIC_GENDER_RADIO_BUTTON, value);
	}

	public String getCustomerIDValue() {
		waitForElementVisible(driver, CustomerPageUI.CUSTOMER_ID_VALUE);
		return getTextElement(driver, CustomerPageUI.CUSTOMER_ID_VALUE);
	}
}

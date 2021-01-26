package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.RegisterPageUI;

public class RegisterPO extends AbstractPage{
	WebDriver driver;

	public RegisterPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToHereLink() {
		waitForElementClickable(driver, RegisterPageUI.HERE_LINK);
		clickToElement(driver, RegisterPageUI.HERE_LINK);
	}

	public String getUserIDValue() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_TEXT_VALUE);
		return getTextElement(driver, RegisterPageUI.USER_ID_TEXT_VALUE);
	}

	public String getPasswordValue() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT_VALUE);
		return getTextElement(driver, RegisterPageUI.PASSWORD_TEXT_VALUE);
	}
}

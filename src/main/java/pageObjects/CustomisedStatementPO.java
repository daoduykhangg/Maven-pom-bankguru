package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class CustomisedStatementPO extends AbstractPage {
	WebDriver driver;

	public CustomisedStatementPO(WebDriver driver) {
		this.driver = driver;
	}
}

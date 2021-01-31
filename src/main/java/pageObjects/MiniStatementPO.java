package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class MiniStatementPO extends AbstractPage{
	WebDriver driver;

	public MiniStatementPO(WebDriver driver) {
		this.driver = driver;
	}
}

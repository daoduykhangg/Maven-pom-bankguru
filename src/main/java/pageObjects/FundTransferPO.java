package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class FundTransferPO extends AbstractPage {
	WebDriver driver;

	public FundTransferPO(WebDriver driver) {
		this.driver = driver;
	}
}

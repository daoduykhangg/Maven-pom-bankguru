package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class NewCustomerPO extends AbstractPage{
	WebDriver driver;

	public NewCustomerPO(WebDriver driver) {
		this.driver = driver;
	}
}

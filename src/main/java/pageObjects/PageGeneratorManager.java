package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static RegisterPO getRegisterPage(WebDriver driver) {
		return new RegisterPO(driver);
	}
	
	public static ManageHomePagePO getManageHomePage(WebDriver driver) {
		return new ManageHomePagePO(driver);
	}
	
	public static NewCustomerPO getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPO(driver);
	}
}

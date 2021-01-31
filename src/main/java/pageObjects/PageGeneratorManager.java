package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static RegisterPO getRegisterPage(WebDriver driver) {
		return new RegisterPO(driver);
	}
	
	public static ManageHomePagePO getManageHomePage(WebDriver driver) {
		return new ManageHomePagePO(driver);
	}
	
	public static CustomerPO getNewCustomerPage(WebDriver driver) {
		return new CustomerPO(driver);
	}
	
	public static AccountPO getAccountPage(WebDriver driver) {
		return new AccountPO(driver);
	}
	
	public static MiniStatementPO getMiniStatementPage(WebDriver driver) {
		return new MiniStatementPO(driver);
	}
}

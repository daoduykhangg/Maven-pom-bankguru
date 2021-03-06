package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static RegisterPO getRegisterPage(WebDriver driver) {
		return new RegisterPO(driver);
	}

	public static ManageHomePagePO getManageHomePage(WebDriver driver) {
		return new ManageHomePagePO(driver);
	}

	public static CustomerPO getCustomerPage(WebDriver driver) {
		return new CustomerPO(driver);
	}

	public static AccountPO getAccountPage(WebDriver driver) {
		return new AccountPO(driver);
	}

	public static MiniStatementPO getMiniStatementPage(WebDriver driver) {
		return new MiniStatementPO(driver);
	}

	public static CustomisedStatementPO getCustomisedStatementPage(WebDriver driver) {
		return new CustomisedStatementPO(driver);
	}
	
	public static FundTransferPO getFundTransferPage(WebDriver driver) {
		return new FundTransferPO(driver);
	}
	
	public static ChangePasswordPO getChangePasswordPage(WebDriver driver) {
		return new ChangePasswordPO(driver);
	}
	
	public static DepositPO getDepositPage(WebDriver driver) {
		return new DepositPO(driver);
	}
	
	public static BalanceEnquiryPO getBalanceEnquiryPage(WebDriver driver) {
		return new BalanceEnquiryPO(driver);
	}
}

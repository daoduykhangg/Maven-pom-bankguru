package pageUIs;

public class AbstractPageUI {
	public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
	public static final String DYNAMIC_TEXTAREA_BY_NAME = "//textarea[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_VALUE = "//input[@value='%s']";
	public static final String DYNAMIC_PAGE_IN_LIST_BOX_BY_TEXT = "//ul[@class='menusubnav']//a[text()='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE_BY_ID = "//label[@id='message%s']";
	public static final String DYNAMIC_FIELS_NAME_BY_TEXT = "//td[text()='%s']";
	public static final String HEADING_TEXT = "//p[@class='heading3']";
	public static final String DYNAMIC_CUSTOMER_INFO_BY_FIELDNAME = "//td[text()='%s']/following-sibling::td";
}

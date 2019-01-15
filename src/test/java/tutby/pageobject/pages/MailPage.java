package tutby.pageobject.pages;

import framework.BasePage;
import framework.Log;
import framework.elements.Label;
import org.openqa.selenium.By;
import tutby.pageobject.menu.HeaderRightMenu;
import tutby.pageobject.menu.LeftBoxMenu;

public class MailPage extends BasePage {
    private final String LOC_EMAIL_MESSAGE = "//div[contains(@class,'mail-MessageSnippet-Content')]";
    private final String LOC_EMAIL_SENDER = "//span[contains(@class,'mail-MessageSnippet-FromText')]";
    private final String ATTR_TITLE = "title";
    private Label lblEmailText = new Label(By.xpath("//span[contains(@class,'MessageSnippet-Item_firstline')]//span"));
    private Log log = Log.getInstance();

    public MailPage() {
        super(By.xpath("//div[contains(@class,'mail-User-Name')]"), "Mail page");
    }

    public HeaderRightMenu getHeaderMenu() {
        return new HeaderRightMenu();
    }

    public LeftBoxMenu getLeftBoxMenu() {
        return new LeftBoxMenu();
    }

    public String getEmailMessageText() {
        return lblEmailText.getText();
    }

    public boolean isEmailPresent() {
        if (new Label(By.xpath(LOC_EMAIL_MESSAGE)).isElementPresent()) {
            log.info("Сообшение присутствует в папке");
            return true;
        } else {
            return false;
        }
    }

    public String getEmailRecipient() {
        return new Label(By.xpath(String.format("%s%s", LOC_EMAIL_MESSAGE, LOC_EMAIL_SENDER))).getAttribute(ATTR_TITLE);
    }

}

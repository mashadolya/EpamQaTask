package tutby.pageobject.menu;

import framework.BasePage;
import framework.Log;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MailUserMenu extends BasePage {
    private Log log = Log.getInstance();
    private final String LOC_NAVIGATE_FORMAT = "//div[contains(@class,'b-mail-dropdown__item')]//a[contains(text(),'%s')]";

    private Label getLblNavigate(MailUserMenuItem mailUserMenuItem) {
        return new Label(By.xpath(String.format(LOC_NAVIGATE_FORMAT, mailUserMenuItem.getMailUserMenuItem())), mailUserMenuItem.getMailUserMenuItem());
    }

    public void navigateToMenuBar(MailUserMenuItem mailUserMenuItem) {
        log.info(String.format("%1$s '%2$s' %3$s", log.getLoc("loc.navigate"), mailUserMenuItem.getMailUserMenuItem(), "Mail User Menu"));
        getLblNavigate(mailUserMenuItem).click();
    }
}

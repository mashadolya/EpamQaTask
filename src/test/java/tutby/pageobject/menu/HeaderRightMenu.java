package tutby.pageobject.menu;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;

public class HeaderRightMenu extends BasePage {
    private final String LOC_NAVIGATE_FORMAT = "//div[contains(@class,'mail-App-Header')]//div[contains(@class,'ns-view-%s')]";

    private Label getLblNavigate(HeaderRightMenuItem headerRightMenuItem) {
        return new Label(By.xpath(String.format(LOC_NAVIGATE_FORMAT, headerRightMenuItem.getMenuItem())), headerRightMenuItem.name());
    }

    private void navigateHeaderMenu(HeaderRightMenuItem headerRightMenuItem) {
        getLblNavigate(headerRightMenuItem).clickAndWait();
    }

    private MailUserMenu getMailUserMenu() {
        return new MailUserMenu();
    }

    public void navigateMailUserMenu(HeaderRightMenuItem headerRightMenuItem, MailUserMenuItem mailUserMenuItem) {
        navigateHeaderMenu(headerRightMenuItem);
        getMailUserMenu().navigateToMenuBar(mailUserMenuItem);
    }
}

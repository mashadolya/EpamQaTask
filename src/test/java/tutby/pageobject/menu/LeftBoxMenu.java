package tutby.pageobject.menu;

import framework.BasePage;
import framework.Log;
import framework.elements.Label;
import org.openqa.selenium.By;

public class LeftBoxMenu extends BasePage {
    private Log log = Log.getInstance();
    private final String LOC_NAVIGATE_FORMAT = "//span[contains(@class,'mail-NestedList-Item-Name') and contains(text(),'%s')]";

    private Label getLblNavigate(LeftBoxMenuItem leftBoxMenuItem) {
        return new Label(By.xpath(String.format(LOC_NAVIGATE_FORMAT, leftBoxMenuItem.getMenuItem())), leftBoxMenuItem.getMenuItem());
    }

    public void navigateToMenuBar(LeftBoxMenuItem leftBoxMenuItem) {
        log.info(String.format("%1$s '%2$s' %3$s", log.getLoc("loc.navigate"), leftBoxMenuItem.getMenuItem(), "Left Box Menu"));
        getLblNavigate(leftBoxMenuItem).click();
    }
}

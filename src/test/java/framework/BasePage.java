package framework;


import framework.elements.Button;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BasePage {
    private Log log = Log.getInstance();
    private By pageLocator;
    private String title;

    public BasePage(By locator, String title) {
        init(locator, title);
        Assert.assertTrue(isOpen());
        log.info(title + " " + log.getLoc("loc.open.page"));
    }

    public BasePage() {
    }

    private boolean isOpen() {
        Button btn = new Button(pageLocator);
        return btn.isDisplayed();
    }

    private void init(By locator, String title) {
        this.pageLocator = locator;
        this.title = title;
    }
}

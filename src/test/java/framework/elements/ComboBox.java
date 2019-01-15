package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ComboBox extends BaseElement {
    public ComboBox(By by, String name) {
        super(by, name);
    }

    private Select select() {
        return new Select(getElement());
    }

    public void selectByText(String text) {

        select().selectByVisibleText(text);
    }

    public String getSelectItemText() {
        return select().getFirstSelectedOption().getText();
    }

    public void desselectByIndex(int index) {
        select().deselectByIndex(index);
    }

    public void selectByIndex(int index) {
        select().selectByIndex(index);
    }

    public List<WebElement> getElementList() {
        return select().getOptions();
    }

    protected String getElementType() {
        return getLoc("loc.cb");
    }
}
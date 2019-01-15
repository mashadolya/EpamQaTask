package framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement {
    public Button(By by, String name) {
        super(by, name);
    }

    public Button(By by) {
        super(by);
    }

    protected String getElementType() {
        return getLoc("loc.button");
    }
}

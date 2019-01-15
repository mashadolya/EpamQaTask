package tutby.pageobject.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import tutby.model.User;

public class LoginPage extends BasePage {
    private final String LOC_LOGIN_TB_FORMAT = "//label[contains(@for,'%s')]//input[contains(@id,'%s')]";
    private final String PARAM_USERNAME = "Username";
    private final String PARAM_PASSWORD = "Password";
    private Button btnLogIn = new Button(By.xpath("//input[contains(@class,'button loginButton')]"), "Log in");

    public LoginPage() {
        super(By.xpath("//div[contains(@class,'logo')]//img[contains(@alt,'TUT.BY - почтовая служба')]"), "TUT.BY Login Page");
    }

    private TextBox getTbLogIn(String parameter) {
        return new TextBox(By.xpath(String.format(LOC_LOGIN_TB_FORMAT, parameter, parameter)), parameter);
    }

    private void enterLogInData(TextBox textBox, String data) {
        textBox.sendKeys(data);
    }

    public void logIn(User user) {
        enterLogInData(getTbLogIn(PARAM_USERNAME), user.getEmail());
        enterLogInData(getTbLogIn(PARAM_PASSWORD), user.getPassword());
        btnLogIn.click();
    }
}

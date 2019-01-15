package tutby.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.Log;
import tutby.model.Message;
import tutby.model.User;
import tutby.pageobject.menu.HeaderRightMenuItem;
import tutby.pageobject.menu.LeftBoxMenuItem;
import tutby.pageobject.menu.MailUserMenuItem;
import tutby.pageobject.pages.LoginPage;
import tutby.pageobject.pages.MailPage;
import tutby.pageobject.pages.YandexHomePage;
import framework.utils.EmailSender;
import tutby.util.TestDataProvider;

public class TutBySteps {
    private Log log = Log.getInstance();
    private User firstUser;
    private User secondUser;
    private Message messageFromFirstUser;
    private MailPage mailPage;
    private YandexHomePage yandexHomePage;
    private String password1 = TestDataProvider.getProperty("password1");
    private String password2 = TestDataProvider.getProperty("password2");
    private static final String FOLDER_TO_SAVE_EMAIL = "Sent";

    @Given("^Send e-mail from \"([^\"]*)\" to \"([^\"]*)\" with text \"([^\"]*)\", send via to/cc/bc$")
    public void sendEmail(String email1, String email2, String text) {
        firstUser = new User(TestDataProvider.getProperty(email1), password1);
        secondUser = new User(TestDataProvider.getProperty(email2), password2);
        messageFromFirstUser = new Message(firstUser.getEmail(), secondUser.getEmail(), TestDataProvider.getProperty(text));
        EmailSender.sendAsHtml(firstUser, secondUser.getEmail(), secondUser.getEmail(),
                secondUser.getEmail(), messageFromFirstUser.getText(), FOLDER_TO_SAVE_EMAIL);
    }

    @When("^Log in to first account$")
    public void logInToFirstUserAccount() {
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(firstUser);
        log.info(String.format("User '%s' has logged in...", firstUser.getEmail()));
        mailPage = new MailPage();
    }

    @And("^Navigate to \"([^\"]*)\" emails")
    public void navigateToEmailFolder(String folderName) {
        mailPage.getLeftBoxMenu().navigateToMenuBar(LeftBoxMenuItem.valueOf(folderName.toUpperCase()));
    }

    @Then("^The sent email must be present in the sent folder$")
    public void isPresentEMailInFolder() {
        CustomerAsserts.assertSentEmail(mailPage, messageFromFirstUser);
    }

    @When("^Log in to second account$")
    public void logInToSecondUserAccount() {
        yandexHomePage.navigateToTutByLoginPage();
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(secondUser);
        mailPage = new MailPage();
    }

    @Then("^The email must be present in the inbox folder and text has valid message$")
    public void verifyEmail() {
        CustomerAsserts.assertInboxEmail(mailPage, messageFromFirstUser);
    }

    @And("^User navigate to \"([^\"]*)\" menu in header and \"([^\"]*)\"$")
    public void logOut(String headerMenuItem, String mailUserMenuItem) {
        mailPage.getHeaderMenu().navigateMailUserMenu(HeaderRightMenuItem.valueOf(headerMenuItem.toUpperCase()),
                MailUserMenuItem.valueOf(mailUserMenuItem.toUpperCase()));
        yandexHomePage = new YandexHomePage();
    }
}

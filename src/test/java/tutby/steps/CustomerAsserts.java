package tutby.steps;

import framework.Log;
import org.testng.Assert;
import tutby.model.Message;
import tutby.pageobject.pages.MailPage;

public class CustomerAsserts {
    private static Log log = Log.getInstance();

    public static void assertSentEmail(MailPage mailPage, Message message) {
        assertEmail(mailPage, message);
        Assert.assertEquals(mailPage.getEmailRecipient(), message.getRecipientEmail(), String.format("%s%s", "Сообщение должно быть отправлено для %s", message.getRecipientEmail()));
    }

    public static void assertInboxEmail(MailPage mailPage, Message message) {
        assertEmail(mailPage, message);
        Assert.assertEquals(mailPage.getEmailRecipient(), message.getSenderEmail(), String.format("%s%s", "Сообщение должно быть отправлено от %s", message.getSenderEmail()));
    }

    private static void assertEmail(MailPage mailPage, Message message) {
        Assert.assertTrue(mailPage.isEmailPresent(), "Сообщение должно присутствовать в папке");
        Assert.assertEquals(mailPage.getEmailMessageText(), message.getText(), String.format("%s%s", "Сообщение должно содержать текст", message.getText()));
        log.info("Текст сообщения соответствует ожидаемому");
    }
}

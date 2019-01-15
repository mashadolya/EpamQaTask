package framework.utils;

import framework.Log;
import tutby.model.User;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private static Log log = Log.getInstance();
    private static final String SMTP_HOST_NAME = "smtp.yandex.ru";
    private static final int SMTP_HOST_PORT = 587;
    private static final String PROTOCOL_IMAP = "imap";
    private static final String IMAP_HOST_NAME = "imap.yandex.ru";

    public static void sendAsHtml(User fromUser, String to, String bcc, String cc, String html, String saveTo) {
        log.info(String.format("%s %s", log.getLoc("loc.msg.snd"), to));
        Session session = createSession(fromUser.getEmail(), fromUser.getPassword());
        MimeMessage message = new MimeMessage(session);
        try {
            prepareEmailMessage(fromUser.getEmail(), message, to, bcc, cc, html);
            Transport.send(message);
            saveEmailTo(session, fromUser.getEmail(), fromUser.getPassword(), message, saveTo);
            log.info(log.getLoc("loc.ok"));
        } catch (MessagingException e) {
            log.error("loc.err.msg");
        }
    }

    private static void saveEmailTo(Session session, String fromEmail, String fromPassword, Message message, String folderName)
            throws MessagingException {
        Store store = session.getStore(PROTOCOL_IMAP);
        store.connect(IMAP_HOST_NAME, fromEmail, fromPassword);
        Folder folder = store.getFolder(folderName);
        folder.open(Folder.READ_WRITE);
        folder.appendMessages(new Message[]{message});
        store.close();
    }

    private static void prepareEmailMessage(String senderEmail, MimeMessage message, String to, String bcc, String cc, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        addRecipients(Message.RecipientType.TO, InternetAddress.parse(to), message);
        addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc), message);
        addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc), message);
    }

    private static void addRecipients(Message.RecipientType recipientType, InternetAddress[] internetAddresses, MimeMessage message) throws MessagingException {
        for (InternetAddress address : internetAddresses) {
            message.addRecipient(recipientType, address);
        }
    }

    private static Session createSession(String senderEmail, String senderPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", SMTP_HOST_PORT);
        props.put("mail.imap.starttls.enable", "true");
        props.put("mail.imap.ssl.enable", "true");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

}

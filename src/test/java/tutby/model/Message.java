package tutby.model;

public class Message {
    private String senderEmail;
    private String text;
    private String recipientEmail;

    public Message(String senderEmail, String recipientEmail, String text) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.text = text;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

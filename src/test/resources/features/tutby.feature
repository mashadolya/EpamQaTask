Feature: Testing some functionality https://mail.tut.by/

  Scenario: Manually create two e-mail boxes at tut.by
  Send e-mail and make sure that e-mail present in Sent and in Inbox with valid text message.
    Given Send e-mail from "email1" to "email2" with text "text", send via to/cc/bc
    When Log in to first account
    And Navigate to "SENT" emails
    Then The sent email must be present in the sent folder
    And User navigate to "USER" menu in header and "LOG_OUT"
    When Log in to second account
    And Navigate to "INBOX" emails
    Then The email must be present in the inbox folder and text has valid message
    And User navigate to "USER" menu in header and "LOG_OUT"
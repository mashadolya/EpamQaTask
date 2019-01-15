# EpamQaTask
Scenario: 
  Manually create two e-mail boxes at tut.by 
Test steps: 
  1. Send e-mail from account 1 to account 2 (use Java Mail API) with text -, send it to acc2 via to/cc/bcc (all 3 options). 
  2. Log in to acc1 from UI. Check e-mail in Sent present 
  3. Log in to acc2 from UI. Check e-mail in Inbox and check text has valid message 
Use TestNG, Maven
Use page object pattern Store account data in xml, csv and any SQL DB (in all 3 data storages), switch between data storages 
Implement data driven approach Tests should provide detailed log, make screenshots on failure and provide results in this format

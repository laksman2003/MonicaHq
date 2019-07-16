This Repository has the Automation Script for REST API Tasks of Monica CRM and UI Automation of Programmable Website.

API Link: https://app.monicahq.com/

UI Link: https://www.programmableweb.com/category/open-data/api

This is Maven Project hence you can clone it or pull it from the git. It compile automtically when you import it from the Eclipse.
Below are different Packages:

com.ittrident.config -- Has a property file and chrome driver exe
com.ittrident.pages -- Has the page class for programmable website
com.ittrident.test -- Has 2 Test Classes one for Validating Monica APi and otherone is for Programmable web UI

Steps To Execute
====
Once the project is imported and compiled. You can run the testng xml.

Test Results
===
Class - MonicaTest.java has test method taskTest() where we test the tasks resource. This fails anyway since authentication is not working. But
Test is completed by directly creating the JsonPath object and unit tested the code.

Class - TestProWeb has method testPrintAttributes() which does the items mentioned the code test word document. This has been Tested in chrome-75 Browser and it working fine.

Leadership Questions:
====
Updated word document is also attached in this repository in below link.
https://github.com/laksman2003/MonicaHq/blob/master/src/Leadership%20Questionire.docx

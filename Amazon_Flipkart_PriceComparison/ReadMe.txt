Created by Ronald Lopis
Date - 28/Oct/2021
===========================================================================================================================================================
Pre-Requisite:-
---------------
1. Java JDK 1.8 or higher & JRE should be available in the targeted system.
2. Browser version should be compatible with driver.
   The selected driver files are latest downloaded from respective support sites.
   These should be compatible with latest browsers as of 25/Oct/2021:-
	Chrome Browser Version - 94.0.4606.81
	Firefox Browser Version - 93.0
	Internet Explorer Version - 11
	Edge Browser Version - 95.0.1020.30

Note1:- IE browser tend to show intermittent compatibility issues.
Note2:- If the browsers are not compatible with drivers, execution issues might come. Please make sure, to have the compatible browsers preferably latest.
Note3:- The testcase is executed in chrome and firefox browser and will pass provided the product is available in both Amazon and Flipkart. Product is available in both websites as of 28th October 2021.


Framework:-
-------------
1. TestNG with POM & PageFactory using Java as the scripting language
2. Log4J for logging information
3. Allure Plugin & configuration for Test Execution report generation
4. Maven build tool to drive the test execution.



Things comes with the framework:-
--------------------------------
1. Allure package with necessary configuration for reporting.
2. Maven for test suite execution.
3. TestRunner & Report Generation batch files to trigger test suite & generate report
4. Drivers for Chrome, Firefox, IE & Edge browsers.
5. The whole package takes about 70MB of disk space.



Steps to start the execution:-
-------------------------------

1. Double click on TestRunner.bat file inside the cloned folder.
2. After test suite execution is completed & report will be automatically generated & shown in the default browser.
3. Allure report has the granular information to view the individual steps results along with it's status.


Note1:- For better experience in viewing the test report, please make Chrome as your default browser.
Note2:- As Allure report opens it's own jetty server. The report is locally generated, which can't be viewed from any other machine. 
        Once you stop the server running on command prompt, the report can't be viewed further. That's the property of Allure report.
        If this is integrated with CI/CD, the generated report can be viewed from any machine.
Note3:- After report verification is completed, stop the jetty server from the existing command prompt by pressing Ctrl+C or close command prompt forcefully.
        This is important, or else there will be many jetty servers opened in command prompt hanging around.


Cross Browser Execution:-
--------------------------
For now, the default setting is made for Chrome browser. If the execution needs to be observed on other browsers, please follow the below steps:-
1. In the cloned folder, Goto <cloned folder>\FlipkartAmazonPriceComparison\TestData\datafile.properties
2. Open the datafile.properties & change the value for browser key field. the beow values are allowed:-
	chrome - For Google Chrome
	firefox - For Firefox
	edge -  MS Edge
	IE - Internet Explorer


Assumptions while scripting:-
-------------------------------
1. If the product is not available then the testcase will stop the execution with assertion.
2. The product being searched is "REDMI Note 10 (Frost White, 64 GB)" It will click on the product if REDMI Note 10 (Frost White, 64 GB) or 
   REDMI Note 10S (Frost White, 64 GB) is returned in the search results with 4GB or 6GB RAM.
3. We assume if the exact searched product is available in Flipkart or Amazon then it will be displayed as first item in searched home page.
4. To change the product follow below steps:-
	1. In the cloned folder, Goto <cloned folder>\FlipkartAmazonPriceComparison\TestData\datafile.properties
	2. Open the datafile.properties & change the value for productName key field.
	3. Change the xpath for productTitle webelement in AmazonHomePage.java and FlipkartHomePage.java file. Here we are changing the xpath because
	   we have to search for the exact product. Xpath is written in such a way that it will search for the exact product.


========================================================================================================================================================
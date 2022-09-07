# Test automation framework for cloud.google.com 

Test checks if the calculated cost for the given model of cloud instance matches the cost, sent by email.

## Stack

- Java 17.  
- Selenium WebDriver
- TestNG
- Gradle.

## Features

- [CalculatingCostTest](src/test/java/CalculatingCostTest.java) - test itself.
- [Page Objects](src/test/java/pom) - supplying UI elements locators and page-related methods.   
- [WebDriver class](src/test/java/driver/DriverSingleton.java) driver manager choose the driver depending on given system property.  
- [Model classes](src/test/java/model) data suppliers for the test.

package util;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {

    public static WebElement waitVisibility(WebElement element, long timeToWait) {
        WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(timeToWait));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static WebElement waitToBeClickable(WebElement element, long timeToWait) {
        WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(timeToWait));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public static WebDriverWait getWait(int timeToWait) {
        return new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(timeToWait));
    }
}

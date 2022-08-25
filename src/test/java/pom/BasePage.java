package pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected WebDriver driver;
    protected static final Logger LOGGER = LogManager.getLogger();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    void switchWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String wh : driver.getWindowHandles()) {
            if (!currentWindow.contentEquals(wh)) {
                driver.switchTo().window(wh);
                break;
            }
        }
    }
}

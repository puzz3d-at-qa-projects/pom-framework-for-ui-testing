package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import util.SharedContext;

import static util.Waiters.waitVisibility;

public class YOPmailGeneratorPage extends BasePage {
    private final static String PAGE_URL = "https://yopmail.com/en/email-generator";

    @FindBy(css = "div#egen")
    private WebElement emailDiv;

    public YOPmailGeneratorPage(WebDriver driver) {

        super(driver);
    }

    public YOPmailGeneratorPage openPage() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(PAGE_URL);
        return this;
    }

    public GCloudPricingCalculatorPage getGeneratedEmail() {
        SharedContext.tempEmail = waitVisibility(emailDiv, 10).getText();
        return new GCloudPricingCalculatorPage(driver);
    }
}

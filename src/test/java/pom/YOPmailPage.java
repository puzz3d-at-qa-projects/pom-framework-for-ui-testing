package pom;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.StringUtils;

import static util.Waiters.waitToBeClickable;

public class YOPmailPage extends BasePage {

    @FindBy(css = "button#refresh")
    private WebElement refreshButton;

    @FindBy(css = "iframe#ifmail")
    private WebElement mailIframe;

    @FindBy(css = "h1 ~ h2")
    private WebElement estimatedH2;

    public YOPmailPage(WebDriver driver) {

        super(driver);
    }


    public String getEmailedEstimatedCost() {
        switchWindow();
        driver.get(StringUtils.getTempEmailWebInterfaceURL());
        waitToBeClickable(refreshButton, 10);
        for (int i = 0; i < 20; i++) {
            if (safeIsDisplayed(estimatedH2)) {
                break;
            } else {
                driver.switchTo().defaultContent();
                refreshButton.click();
                doNothing(3000);
            }
        }
        System.out.println("h2: " + estimatedH2);
        System.out.println("h2 text: " + estimatedH2.getText());
        return StringUtils.stripPrice(estimatedH2.getText());
    }

    private static void doNothing(int sleepInMillis) {
        try {
            Thread.sleep(sleepInMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean safeIsDisplayed(WebElement element) {
        boolean result = false;
        try {
            driver.switchTo().frame(mailIframe);
            System.out.println("estimatedH2.isDisplayed(): " + estimatedH2.isDisplayed());
            if (estimatedH2.isDisplayed()) result = true;
        } catch (NoSuchElementException e) {
            System.out.println("H2 not found"); // change it!
        }
        System.out.println("safeIsDisplayed return: " + result);
        return result;
    }
}

package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.SharedContext;
import util.StringUtils;

import static util.Waiters.waitToBeClickable;
import static util.Waiters.waitVisibility;

public class GCloudPricingCalculatorPage extends BasePage{

    public static final String NUMBER_OF_INSTANCES = "4";

    @FindBy(xpath = "//iframe[contains(@src, '/calculator')]")
    private WebElement firstIframe;

    @FindBy(css = "iframe#myFrame")
    private WebElement secondIframe;

    @FindBy(css = "input[ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.instance']")
    private WebElement instanceTypeMenu;

    @FindBy(xpath = "//div[contains(text(), 'e2-standard-8')]")
    private WebElement instanceType;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.location']")
    private WebElement locationMenu;


    @FindBy(xpath = "//div[contains(@class,'md-clickable')]//div[contains(text(), 'Frankfurt')]")
    private WebElement location;

    @FindBy(xpath = "//button[contains(@ng-disabled, 'ComputeEngineForm')]")
    private WebElement addToEstimateButton;

    @FindBy(css = "h2 b.ng-binding")
    private WebElement totalCost;

    @FindBy(css = "button#email_quote")
    private WebElement emailButton;

    @FindBy(css = "input[ng-model='emailQuote.user.email']")
    private WebElement emailInput;

    @FindBy(css = "button[aria-label='Send Email']")
    private WebElement sendEmailButton;

    public GCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GCloudPricingCalculatorPage fillInConditions() {
        switchToCalcIframe();
        waitToBeClickable(numberOfInstancesInput, 10).click();
        numberOfInstancesInput.sendKeys(NUMBER_OF_INSTANCES);
        instanceTypeMenu.click();
        waitToBeClickable(instanceType,5).click();
        locationMenu.click();
        waitToBeClickable(location, 5).click();
        addToEstimateButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private void switchToCalcIframe() {
        waitVisibility(firstIframe, 5);
        driver.switchTo().frame(firstIframe);
        waitVisibility(secondIframe, 5);
        driver.switchTo().frame(secondIframe);
    }

    public String getEstimatedCost() {
        return StringUtils.stripPrice(totalCost.getText());
    }

    public YOPmailPage emailEstimatedCost() {
        switchWindow();
        switchToCalcIframe();
        waitToBeClickable(emailButton, 5).click();
        waitToBeClickable(emailInput, 10).sendKeys(SharedContext.tempEmail);
        sendEmailButton.click();
        return new YOPmailPage(driver);
    }


    //    https://yopmail.com/?frouqueuppubrafro-9926@yopmail.com
}

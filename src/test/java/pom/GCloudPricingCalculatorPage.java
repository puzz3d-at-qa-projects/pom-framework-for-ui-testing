package pom;

import model.CloudInstance;
import model.TemporaryEmail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.StringUtils;

import java.util.List;

import static util.Waiters.waitToBeClickable;
import static util.Waiters.waitVisibility;

public class GCloudPricingCalculatorPage extends BasePage {

    @FindBy(xpath = "//iframe[contains(@src, '/calculator')]")
    private WebElement firstIframe;
    @FindBy(css = "iframe#myFrame")
    private WebElement secondIframe;
    @FindBy(css = "input[ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;
    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.instance']")
    private WebElement instanceTypeMenu;
    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.location']")
    private WebElement locationMenu;
    @FindBy(css = "div.md-clickable div.md-text")
    private List<WebElement> dropDownOptions;
    @FindBy(css = "div.md-clickable div.md-text")
    private WebElement justForWait;
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

    public GCloudPricingCalculatorPage fillInConditions(CloudInstance testCloudInstance) {
        String numberOfInstances = testCloudInstance.numberOfInstances();
        String instanceType = testCloudInstance.instanceType();
        String instanceLocation = testCloudInstance.instanceLocation();

        switchToCalcIframe();
        waitToBeClickable(numberOfInstancesInput, 10).click();
        numberOfInstancesInput.sendKeys(numberOfInstances);
        LOGGER.debug("Number of instances set: " + numberOfInstances);
        waitToBeClickable(instanceTypeMenu, 5).click();
        getMatchingWebElement(instanceType).click();
        LOGGER.debug("Machine type set: " + instanceType);
        waitToBeClickable(locationMenu, 5).click();
        getMatchingWebElement(instanceLocation).click();
        LOGGER.debug("Datacenter location set: " + instanceLocation);
        addToEstimateButton.click();
        LOGGER.debug("Instance model added to estimation.");
        return this;
    }

    public WebElement getMatchingWebElement(String textToSearch) {
        waitToBeClickable(justForWait, 10);
        return dropDownOptions.stream()
                .filter(w -> w.getText().contains(textToSearch))
                .findFirst()
                .orElse(null);
    }

    private void switchToCalcIframe() {
        waitVisibility(firstIframe, 5);
        driver.switchTo().frame(firstIframe);
        waitVisibility(secondIframe, 5);
        driver.switchTo().frame(secondIframe);
    }

    public String getEstimatedCost() {
        String cost = StringUtils.stripPrice(totalCost.getText());
        LOGGER.debug("Estimated cost in USD: " + cost);
        return cost;
    }

    public YOPmailPage emailEstimatedCost() {
        switchWindow();
        switchToCalcIframe();
        waitToBeClickable(emailButton, 5).click();
        waitToBeClickable(emailInput, 10).sendKeys(TemporaryEmail.getTempEmailAddress());
        sendEmailButton.click();
        return new YOPmailPage(driver);
    }
}

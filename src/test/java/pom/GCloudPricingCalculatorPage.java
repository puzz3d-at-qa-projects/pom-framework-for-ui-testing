package pom;

import model.CloudInstance;
import model.TemporaryEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.StringUtils;

import static util.Waiters.waitToBeClickable;
import static util.Waiters.waitVisibility;

public class GCloudPricingCalculatorPage extends BasePage{

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

        String numberOfInstances = testCloudInstance.getNumberOfInstances();
        String typeLocator = testCloudInstance.getInstanceTypeLocator();
        String locationLocator = testCloudInstance.getInstanceLocationLocator();
        LOGGER.debug("Model type locator: " + typeLocator);
        LOGGER.debug("Model location locator: " + locationLocator);

        switchToCalcIframe();
        waitToBeClickable(numberOfInstancesInput, 10).click();
        numberOfInstancesInput.sendKeys(numberOfInstances);
        waitToBeClickable(instanceTypeMenu, 5).click();
        waitVisibility(driver.findElement(By.xpath(typeLocator)),5).click();
        waitToBeClickable(locationMenu,5).click();
        waitVisibility(driver.findElement(By.xpath(locationLocator)),5).click();
        addToEstimateButton.click();
        LOGGER.debug("Instance model added to estimation.");
        return this;
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

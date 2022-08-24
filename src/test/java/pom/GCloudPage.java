package pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static util.Waiters.waitToBeClickable;

public class GCloudPage extends BasePage {

    private final static String PAGE_URL = "https://cloud.google.com/";
    private final static String SEARCH_STRING = "Google Cloud Platform Pricing Calculator";

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(xpath = "//a//*[text()='Google Cloud Platform Pricing Calculator']")
    private WebElement calculatorLink;


    public GCloudPage(WebDriver driver) {
        super(driver);
    }

    public GCloudPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public GCloudPricingCalculatorPage goToCalculatorCE() {
        waitToBeClickable(searchInput, 5).click();
        searchInput.sendKeys(SEARCH_STRING + Keys.ENTER);
        waitToBeClickable(calculatorLink, 10).click();
        return new GCloudPricingCalculatorPage(driver);
    }
}

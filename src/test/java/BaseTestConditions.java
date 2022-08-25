import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.TestListener;

@Listeners({TestListener.class})
public class BaseTestConditions {

    protected WebDriver driver;

    @BeforeMethod()
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.finishHim();
    }

    private void switchWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String wh : driver.getWindowHandles()) {
            if (!currentWindow.equals(driver.getWindowHandle())) {
                driver.switchTo().window(wh);
                break;
            }
        }
    }

}

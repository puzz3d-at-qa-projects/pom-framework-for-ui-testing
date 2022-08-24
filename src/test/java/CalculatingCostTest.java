import org.testng.annotations.Test;
import pom.GCloudPage;
import pom.YOPmailGeneratorPage;
import util.StringUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CalculatingCostTest extends BaseTestConditions {

    @Test
    public void estimatedCostEmailed() {

        String estimatedCost = new GCloudPage(driver)
                .openPage()
                .goToCalculatorCE()
                .fillInConditions()
                .getEstimatedCost();


        String emailedEstimatedCost = new YOPmailGeneratorPage(driver)
                .openPage()
                .getGeneratedEmail()
                .emailEstimatedCost()
                .getEmailedEstimatedCost();

        assertNotEquals(emailedEstimatedCost, StringUtils.PATTERN_ERROR, "Error while extracting cost from string.");
        assertEquals(emailedEstimatedCost, estimatedCost);
    }
}

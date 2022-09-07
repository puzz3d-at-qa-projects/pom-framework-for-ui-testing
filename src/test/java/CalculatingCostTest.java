import model.CloudInstance;
import org.testng.annotations.Test;
import pom.GCloudPage;
import pom.YOPmailGeneratorPage;
import service.CloudInstanceCreator;
import util.StringUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CalculatingCostTest extends BaseTestConditions {

    @Test
    public void estimatedCostEmailed() {

        CloudInstance testCloudInstance = new CloudInstanceCreator().fourE2Standard8Frankfurt();

        String estimatedCost = new GCloudPage(driver)
                .openPage()
                .goToCalculatorCE()
                .fillInConditions(testCloudInstance)
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

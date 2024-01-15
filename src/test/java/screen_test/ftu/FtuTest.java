package screen_test.ftu;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen_finder.ftu.FtuAllScreens;
import utils.AppiumUtils;

public class FtuTest extends AppiumUtils {
    FtuAllScreens ftu;

    @BeforeMethod
    public void beforeMethod() {
        init_TestData("ftuTestData.json");
        ftu = new FtuAllScreens();
    }

    @Test(priority='1')
    public void testFtuScreens(){
        ExtentTest testFtu = extent.createTest("FTU", "Test cases for FTU screens");
        try {
            testFtu.log(Status.INFO, "TCs started for FTU");

            ftu.clickContinueButton();
            testFtu.log(Status.PASS, "Click to continue button: Makeover Page");

            ftu.clickLetsGoButton();
            testFtu.log(Status.PASS,"Click to Let's go button: Location Page");

            ftu.clickTrafficOkSureButton();
            testFtu.log(Status.PASS, "Click to Ok Sure button: Traffic Page");

            Assert.assertEquals(ftu.getOkSureText(), "OK, sure", "Assertion for OK, sure text");

            ftu.clickImprovementOkSureButton();
            testFtu.log(Status.PASS, "Click to Ok Sure button: Improvement Page");

            ftu.stopDragScreenUp();
            testFtu.log(Status.INFO, "TCs completed for FTU");
        } catch (Exception e) {
            AppiumUtils.cleanupScreenshots(0);
            testFtu.log(Status.FAIL, e.getMessage());
            String screenshotPath = captureScreenshot();
            testFtu.log(Status.FAIL, "Screen capture for failure:",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

            // Mark the TestNG test method as failed
            Assert.fail("Test case failed: " + e.getMessage());
        }
    }

}

package screen_test.guidance;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import factory.Pillar;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen_finder.guidance.DriveGuidance;
import utils.AppiumUtils;

public class DriveGuidanceTest extends AppiumUtils {

    DriveGuidance driveGuidance;
    AppiumUtils appiumUtils;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        appiumUtils = new AppiumUtils();
        appiumUtils.init_TestData("guidanceTestData.json");
        appiumUtils.init_Strings("sendKeys.xml");
        driveGuidance = new DriveGuidance();


    }

    @Test(priority='1')
    public void getDirection() throws InterruptedException {
        driveGuidance.clickDirectionButton();
        driveGuidance.startCarGuidance();
        driveGuidance.cancelGuidance();
        driveGuidance.verifyLandingPage();
        ExtentTest test = Pillar.extent.createTest("Guidance", "Start car guidance, cancel it and back to landing page");
        try {
            test.log(Status.INFO, "TCs started for Guidance");
            driveGuidance.clickDirectionButton();
            test.log(Status.PASS, "Search place and click to direction button");

            driveGuidance.startCarGuidance();
            test.log(Status.PASS, "Start simulation");
            driveGuidance.cancelGuidance();
            test.log(Status.PASS, "Cancel ongoing guidance");
            driveGuidance.verifyLandingPage();
            test.log(Status.PASS, "Cancel from statistics screen and back to landing page");

            test.log(Status.INFO, "TCs completed for guidance");
        } catch (Exception e) {
            AppiumUtils.cleanupScreenshots(0);
            test.log(Status.FAIL, e.getMessage());
            String screenshotPath = captureScreenshot();
            test.log(Status.FAIL, "Screen capture for failure:",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

            // Mark the TestNG test method as failed
            Assert.fail("Test case failed: " + e.getMessage());
        }
    }

}

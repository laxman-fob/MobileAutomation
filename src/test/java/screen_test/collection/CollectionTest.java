package screen_test.collection;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import factory.Pillar;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen_finder.collection.Collection;
import utils.AppiumUtils;

public class CollectionTest extends AppiumUtils {

    Collection collection;
    AppiumUtils appUtils;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        appUtils = new AppiumUtils();
        appUtils.init_TestData("collectionTestData.json");
        appUtils.init_Strings("sendKeys.xml");
        collection = new Collection();

    }

    @Test(priority='1')
    public void openCollectionPage() throws InterruptedException {
        ExtentTest testColl = Pillar.extent.createTest("Collection", "Create collection and add place");
        try {
            testColl.log(Status.INFO, "TCs started for collection");

            collection.openCollectionPageAsFtu();
            testColl.log(Status.PASS, "Open collection page as first time user");

            collection.createCollectionNameAsFtu();
            testColl.log(Status.PASS, "create collection");

            collection.addPlaceToCreatedCollection();
            testColl.log(Status.PASS, "add place to collection");

            collection.closeCollectionCancelButtonLastScreen();
            testColl.log(Status.PASS, "close first collection page once place added");

            Assert.assertEquals(collection.collectionNameAndPlace(),
                    appUtils.getTestData().getJSONObject("expectResult").
                            getString("placeAndCollection"));
            collection.closeCollectionCancelButtonSecondLastScreen();
            testColl.log(Status.PASS, "close collection page once and back to landing page after assert");
            testColl.log(Status.INFO, "TCs completed for collection");
        } catch (Exception e) {
            AppiumUtils.cleanupScreenshots(0);
            testColl.log(Status.FAIL, e.getMessage());
            String screenshotPath = captureScreenshot();
            testColl.log(Status.FAIL, "Screen capture for failure:",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

            // Mark the TestNG test method as failed
            Assert.fail("Test case failed: " + e.getMessage());
        }

    }

}

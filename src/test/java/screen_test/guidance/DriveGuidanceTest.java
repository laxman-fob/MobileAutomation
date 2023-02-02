package screen_test.guidance;

import factory.Pillar;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import screen_finder.guidance.DriveGuidance;

public class DriveGuidanceTest {

    DriveGuidance driveGuidance;
    Pillar pillar;

    @BeforeMethod
    public void beforeMethod(){
        pillar = new Pillar();
        pillar.init_TestData("guidanceTestData.json");
        driveGuidance = new DriveGuidance();

    }

    @Test(priority='1')
    public void getDirection() throws InterruptedException {
        driveGuidance.clickDirectionButton();
    }

    @Test(priority='2')
    public void startCarGuidance() throws InterruptedException {
        driveGuidance.startCarGuidance();
    }
    @Test(priority='3')
    public void cancelGuidance() throws InterruptedException {
        driveGuidance.cancelGuidance();
        driveGuidance.verifyLandingPage();


    }




}

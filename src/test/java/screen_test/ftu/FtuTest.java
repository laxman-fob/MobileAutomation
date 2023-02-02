package screen_test.ftu;

import factory.Pillar;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen_finder.ftu.FtuAllScreens;

public class FtuTest extends Pillar {
    FtuAllScreens ftu;

    @BeforeMethod
    public void beforeMethod() {
        init_TestData("ftuTestData.json");
        ftu = new FtuAllScreens();

    }

    @Test(priority='1')
    public void test_makeover_page() throws InterruptedException {
        ftu.clickContinueButton();
    }

    @Test(priority='2')
    public void test_Location_page() throws InterruptedException {
        ftu.clickLetsGoButton();

    }

    @Test(priority='3')
    public void test_Traffic_page() throws InterruptedException {
        ftu.clickTrafficOkSureButton();

    }

    @Test(priority='4')
    public void test_improvement_page() throws InterruptedException {
        Assert.assertEquals(ftu.getOkSureText(),"OK, sure");
        ftu.clickImprovementOkSureButton();

    }


}

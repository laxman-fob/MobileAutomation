package tests;

import factory.Pillar;
import finder.Inspector;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestCases extends Pillar {
    Inspector ip;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        ip = new Inspector();
        Thread.sleep(3000);
    }

    @Test(priority='1')
    public void test_makeover_page() throws InterruptedException {
        ip.clickContinueButton();
    }

    @Test(priority='2')
    public void test_Location_page() throws InterruptedException {
        ip.clickLetsGoButton();

    }

    @Test(priority='3')
    public void test_Traffic_page() throws InterruptedException {
        ip.clickTrafficOkSureButton();

    }

    @Test(priority='4')
    public void test_improvement_page() throws InterruptedException {
        Assert.assertEquals(ip.getOkSureText(),"OK, sure");
        ip.clickImprovementOkSureButton();

    }


    @Test(priority='5')
    public void openCollectionPage() throws InterruptedException {
        ip.openCollectionPageAsFtu();

    }

    @Test(priority='6')
    public void addPlaceToCollection() throws InterruptedException {
        ip.createCollectionNameAsFtu();
        ip.addPlaceToCreatedCollection();
        Assert.assertEquals(ip.collectionNameAndPlace(),"VisitEstonia2023\nPlaces: 2");
        ip.closeCreatedCollection();

        int expectedNumberOfPlaces = 1;
        String expectedNameOfCollection = "VisitEstonia2023";

        /*
        String actualNumberOfPlaces = collection.getCollectionPlaceNumber();
        String actualNameOfCollection = collection.getCollectionName();

        Assert.assertEquals(actualNumberOfPlaces,expectedNumberOfPlaces);
        Assert.assertEquals(actualNameOfCollection,expectedNameOfCollection);
*/
    }

    @Test(priority='7')
    public void getDirection() throws InterruptedException {
        ip.clickDirectionButton();
    }

    @Test(priority='8')
    public void startCarGuidance() throws InterruptedException {
        ip.startCarGuidance();
    }
    @Test(priority='9')
    public void cancelGuidance() throws InterruptedException {
        ip.cancelGuidance();
        ip.verifyLandingPage();


    }




}

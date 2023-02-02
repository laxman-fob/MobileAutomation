package screen_finder.collection;

import factory.Pillar;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Collection extends Pillar {

    @AndroidFindBy(accessibility = "Get started")
    @iOSXCUITFindBy(accessibility = "Get started")
    private WebElement collectionGetStartedButton;

    @AndroidFindBy (accessibility = "New collection")
    @iOSXCUITFindBy (accessibility = "New collection")
    private WebElement createNewCollectionButton;

    @AndroidFindBy (xpath = "//*[@text='Collection name']")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Collection name\"]")
    private WebElement editableCollectionTitle;

    @AndroidFindBy (accessibility = "Create collection")
    @iOSXCUITFindBy (accessibility = "Create collection")
    private WebElement createCollectionButton;

    @AndroidFindBy (xpath = "//*[@text='Where to?']")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement searchBox;

    @AndroidBy(xpath = "VisitEstonia2023\nPlaces: 1")
    @iOSXCUITFindBy (accessibility = "VisitEstonia2023\nPlaces: 1")
    private List<WebElement> createdCollectionNameAndPlaceCount;


    public void openCollectionPageAsFtu() throws InterruptedException {
        verticalSwipeByPercentages(0.9,0.8,0.5);
        Thread.sleep(1000);
        click(collectionGetStartedButton);
    }

    public void createCollectionNameAsFtu() throws InterruptedException {
        Thread.sleep(1000);
        click(createNewCollectionButton);
        Thread.sleep(1000);
        sendKeys(editableCollectionTitle,"VisitEstonia2023");
        Thread.sleep(1000);
        click(createCollectionButton);
    }

    public void addPlaceToCreatedCollection() throws InterruptedException {
        tapByPercentage(0.5,0.27);
        sendKeys(searchBox, "Viru Keskus");
        Thread.sleep(1000);
        tapByPercentage(0.4,0.3);
        Thread.sleep(1000);
        tapByPercentage(0.92,0.17);
        Thread.sleep(700);

    }

    public void closeCreatedCollection() throws InterruptedException {
        tapByPercentage(0.92,0.17);
        Thread.sleep(700);
    }


    public String collectionNameAndPlace(){
        List<WebElement> createdCollectionNameAndPlaceCount1 = (List<WebElement>) getDriver().findElementsByAccessibilityId("VisitEstonia2023\nPlaces: 1");
        return ((createdCollectionNameAndPlaceCount1.get(0)).getAttribute("content-desc"));
    }

}

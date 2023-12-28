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

  //  @AndroidFindBy (xpath = "//*[@text='Collection name']")
   // @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Collection name\"]")
  @AndroidFindBy (xpath = "//android.widget.EditText")
  @iOSXCUITFindBy (xpath = "//android.widget.EditText")
    private WebElement editableCollectionTitle;

    @AndroidFindBy (accessibility = "Create collection")
    @iOSXCUITFindBy (accessibility = "Create collection")
    private WebElement createCollectionButton;

    //@AndroidFindBy (xpath = "//*[@text='Where to?']")
    //@iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")

    @AndroidFindBy (xpath = "//android.widget.EditText")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement searchBox;

    @AndroidFindBy (xpath = "//android.widget.ImageView[@index='1' and @scrollable='false']")
   // @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement collectionCancelButton;

    @AndroidFindBy (xpath = "//android.widget.ImageView[@index='0' and @scrollable='true']")
    // @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement collectionCancelButton2;

    @AndroidFindBy (xpath = "(//android.view.View[@index = '0'])[position() = last()]")
    // @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement recommendedFirstSearch;

    @AndroidBy( xpath = "(//android.widget.ImageView[@index=\"1\"]) [position()=2]")
    @iOSXCUITFindBy (accessibility = "Add a place")
    private WebElement addAPlaceFrame;


    public void openCollectionPageAsFtu() throws InterruptedException {
        verticalSwipeByPercentages(0.9,0.8,0.5);
        waitForDurationInSeconds(1);
        click(collectionGetStartedButton);
    }

    public void createCollectionNameAsFtu() throws InterruptedException {
        waitForDurationInSeconds(1);
        click(createNewCollectionButton);
        waitForDurationInSeconds(1);
        sendKeys(editableCollectionTitle,"VisitEstonia2024");
        waitForDurationInSeconds(1);
        click(createCollectionButton);
    }

    public void addPlaceToCreatedCollection() throws InterruptedException {
        tapByPercentage(0.5,0.27);
        sendKeys(searchBox, "Viru Keskus");
        waitForDurationInSeconds(1);
        click(recommendedFirstSearch);
    }

    public void close1stCreatedCollection() throws InterruptedException {
        tapOnSide(collectionCancelButton2,Side.RIGHT,0.1);
    }
    public void close2ndCreatedCollection() throws InterruptedException {
        click(collectionCancelButton);
    }


    public String collectionNameAndPlace(){
        WebElement createdCollectionNameAndPlaceCount = getDriver().findElementByAccessibilityId("VisitEstonia2024\nPlaces: 1");
        return createdCollectionNameAndPlaceCount.getAttribute("content-desc");

    }

}

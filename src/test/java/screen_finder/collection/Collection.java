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

    @AndroidFindBy (xpath = "//android.widget.EditText")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField")
    private WebElement editableCollectionTitle;

    @AndroidFindBy (accessibility = "Create collection")
    @iOSXCUITFindBy (accessibility = "Create collection")
    private WebElement createCollectionButton;

    @AndroidFindBy (xpath = "//android.widget.EditText")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField")
    private WebElement searchBox;

    @AndroidFindBy (xpath = "//android.widget.ImageView[@index='1' and @scrollable='false']")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeImage[@index = '2']")
    private WebElement collectionCancelButtonLastScreen;

    @AndroidFindBy (xpath = "//android.widget.ImageView[@index='0' and @scrollable='true']")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeImage[@index = '1']")
    private WebElement collectionCancelButtonSecondLastScreen;

    @AndroidFindBy (xpath = "(//android.view.View[@index = '0'])[position() = last()]")
    @iOSXCUITFindBy (xpath = "(//XCUIElementTypeOther[@index = '0' and @accessible='true']) [position()=2]")
    private WebElement recommendedFirstSearch;

    @AndroidBy( xpath = "(//android.widget.ImageView[@index=\"1\"]) [position()=2]")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeImage[@name=\"Add a place\"]")
    private WebElement addAPlaceFrame;


    public void openCollectionPageAsFtu() throws InterruptedException {
        verticalSwipeByPercentages(0.9,0.7,0.5);
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
        if (isAndroid()) {
            tapByPercentage(0.5,0.27);
        } else if (isIOS()){
            tapOnSide(addAPlaceFrame, Side.TOP_MIDDLE, 0.5);
        }
        sendKeys(searchBox, "Viru Keskus");
        waitForDurationInSeconds(1);
        click(recommendedFirstSearch);
    }

    public void closeCollectionCancelButtonSecondLastScreen() throws InterruptedException {
        tapOnSide(collectionCancelButtonSecondLastScreen,Side.RIGHT,0.1);
        waitForDurationInSeconds(3);
    }
    public void closeCollectionCancelButtonLastScreen() throws InterruptedException {
        click(collectionCancelButtonLastScreen);
        waitForDurationInSeconds(3);
    }


    public String collectionNameAndPlace(){
        String text = null;
        if(isAndroid()){
            WebElement createdCollectionNameAndPlaceCount = getDriver().findElementByAccessibilityId("VisitEstonia2024\nPlaces: 1");
            text = createdCollectionNameAndPlaceCount.getAttribute("content-desc");
        }else if(isIOS()){
            WebElement createdCollectionNameAndPlaceCount = getDriver().findElementByXPath("//XCUIElementTypeImage[@index='0']");
            text = createdCollectionNameAndPlaceCount.getAttribute("label");
        }
        return text;

    }

}

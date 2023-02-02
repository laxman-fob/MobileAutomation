package finder;

import factory.Pillar;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Inspector extends Pillar {

    @AndroidFindBy (xpath = "//android.widget.Button[@content-desc=\"Continue\"]\n")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeButton[@name=\"Continue\"]")
    private WebElement makeoverButton;

    @AndroidFindBy (accessibility = "Let's go!")
    @iOSXCUITFindBy (accessibility = "Let's go!")
    private WebElement locationButton;

    @AndroidFindBy (accessibility = "OK, sure")
    @iOSXCUITFindBy (accessibility = "OK, sure")
    private WebElement improvementButton;

    @AndroidFindBy (accessibility = "OK, sure")
    @iOSXCUITFindBy (accessibility = "OK, sure")
    private WebElement trafficButton;

    @AndroidFindBy (accessibility = "Get started")
    @iOSXCUITFindBy (accessibility = "Get started")
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


    @AndroidFindBy (accessibility = "Directions")
    @iOSXCUITFindBy (accessibility = "Directions")
    private WebElement directionButton;

    @AndroidFindBy (accessibility = "Cancel guidance")
    @iOSXCUITFindBy (accessibility = "Cancel guidance")
    private WebElement cancelGuidanceButton;


    /*

    WebElement locationButton = getDriver().findElementByAccessibilityId("Let's go!");
    WebElement improvementButton = getDriver().findElementByAccessibilityId("OK, sure");
    WebElement trafficButton =  getDriver().findElementByAccessibilityId("OK, sure");
    WebElement collectionGetStartedButton =  getDriver().findElementByAccessibilityId("Get started");
    WebElement createNewCollectionButton =  getDriver().findElementByAccessibilityId("New collection");

    @AndroidFindBy (xpath = "//*[@text='Collection name']")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Collection name\"]")
    private WebElement editableCollectionTitle;
    WebElement createCollectionButton =getDriver().findElementByAccessibilityId("Create collection");

    WebElement addAPlaceButton= getDriver().findElementByAccessibilityId("Add a place");

    @AndroidFindBy (xpath = "//*[@text='Where to?']")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement searchBox;

    WebElement createdCollectionNameAndPlaceCount = getDriver().findElementByAccessibilityId("VisitEstonia2023 Places: 1");
    WebElement directionButton=   getDriver().findElementByAccessibilityId("Directions");
    WebElement cancelGuidanceButton= getDriver().findElementByAccessibilityId("Cancel guidance");

     */

    public void clickImprovementOkSureButton() throws InterruptedException {
        Thread.sleep(1000);
        click(improvementButton);
    }

    public void clickLetsGoButton() throws InterruptedException {
        Thread.sleep(1000);
        click(locationButton);
    }

    public String getOkSureText(){
        String title = improvementButton.getAttribute("content-desc");
        return title;
    }

    public void clickContinueButton() throws InterruptedException {
        Thread.sleep(1000);
        click(makeoverButton);
    }
    public void clickTrafficOkSureButton() throws InterruptedException {
        Thread.sleep(1000);
        click(trafficButton);
    }

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

        //click(createdCollectionNameAndPlaceCount.get(0));

       // printText(createdCollectionNameAndPlaceCount);
       // System.out.println(createdCollectionNameAndPlaceCount);
       // tapByPercentage(0.92,0.17);
       // Thread.sleep(700);
    }

    public void closeCreatedCollection() throws InterruptedException {
        // printText(createdCollectionNameAndPlaceCount);

        tapByPercentage(0.92,0.17);
        Thread.sleep(700);
    }

    public void printText(WebElement e){
        System.out.println(e.getText());

    }

    public String collectionNameAndPlace(){
        List<WebElement> createdCollectionNameAndPlaceCount1 = (List<WebElement>) getDriver().findElementsByAccessibilityId("VisitEstonia2023\nPlaces: 1");
        return ((createdCollectionNameAndPlaceCount1.get(0)).getAttribute("content-desc"));
    }

    public void clickNearByRandomPlace() throws InterruptedException {
        Thread.sleep(1000);
        verticalSwipeByPercentages(0.4,0.4,0.3);
        Thread.sleep(1000);
        longTapByPercentage(0.4,0.4);

    }

    public void swipeItDown() {
        verticalSwipeByPercentages(0.4,0.8,0.5);
    }

    public void clickDirectionButton() throws InterruptedException {
        click(searchBox);
        sendKeys(searchBox, "Lasnamae Centrum");
        Thread.sleep(1000);
        tapByPercentage(0.4,0.3);
        Thread.sleep(1000);
        click(directionButton);
    }

    public void startCarGuidance() throws InterruptedException {
        Thread.sleep(1000);
        longTapByPercentage(0.93,0.95);

    }
    public void cancelGuidance() throws InterruptedException {
        Thread.sleep(1000);
        longTapByPercentage(0.94,0.93);
        click(cancelGuidanceButton);
    }

    public void verifyLandingPage() throws InterruptedException {
        Thread.sleep(1000);
        verticalSwipeByPercentages(0.95,0.85,0.5);
        verticalSwipeByPercentages(0.95,0.85,0.5);


    }


}


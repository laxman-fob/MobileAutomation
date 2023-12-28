package screen_finder.guidance;


import factory.Pillar;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class DriveGuidance extends Pillar {


    @AndroidFindBy(accessibility = "Directions")
    @iOSXCUITFindBy(accessibility = "Directions")
    private WebElement directionButton;

    @AndroidFindBy (accessibility = "Cancel guidance")
    @iOSXCUITFindBy (accessibility = "Cancel guidance")
    private WebElement cancelGuidanceButton;

    @AndroidFindBy (xpath = "//android.widget.ImageView[@index='0' and @hint='Where to?']")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement searchBox;

    @AndroidFindBy (xpath = "//android.widget.EditText")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement searchBoxEdit;

    @AndroidFindBy (xpath = "//android.widget.ImageView[@index=\"0\" and @clickable='true']")
    // @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement dismissGuidance;

    @AndroidFindBy (xpath = "(//android.view.View[@index = '0'])[position() = last()]")
    // @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement firstSearch;

    @AndroidFindBy (xpath = "(//android.widget.ImageView[@index=\"0\"]) [position()=4]")
    // @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement startGuidance;

    @AndroidFindBy (xpath = "//android.widget.ImageView[@index=\"0\" and not(@content-desc='Share')]")
   // @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement dismissButton;


    public void clickDirectionButton() throws InterruptedException {
        click(searchBox);
        sendKeys(searchBoxEdit, "Lasnamae Centrum");
        waitForDurationInSeconds(1);
        click(firstSearch);
        click(directionButton);
    }

    public void startCarGuidance() throws InterruptedException {
        waitForDurationInSeconds(1);
        longTapOnSide(startGuidance,Side.MIDDLE,0);

    }
    public void cancelGuidance() throws InterruptedException {
        waitForDurationInSeconds(10);
        click(dismissGuidance);
        click(cancelGuidanceButton);
        waitForDurationInSeconds(5);
        tapOnSide(dismissButton,Side.RIGHT,0.08);
    }

    public void verifyLandingPage() throws InterruptedException {
        waitForDurationInSeconds(5);
        verticalSwipeByPercentages(0.95,0.85,0.5);
        verticalSwipeByPercentages(0.95,0.85,0.5);

    }


}

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

    @AndroidFindBy (xpath = "//*[@text='Where to?']")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeTextField[@name=\"Where to?\"]")
    private WebElement searchBox;


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

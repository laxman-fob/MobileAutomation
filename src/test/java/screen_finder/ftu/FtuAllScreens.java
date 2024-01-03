package screen_finder.ftu;

import factory.Pillar;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class FtuAllScreens extends Pillar {

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


    public void clickImprovementOkSureButton() throws InterruptedException {
        click(improvementButton);
        waitForDurationInSeconds(1);
    }

    public void clickLetsGoButton() throws InterruptedException {
        click(locationButton);
        waitForDurationInSeconds(1);
    }

    public String getOkSureText(){
        String title = null;
        if(isAndroid()){
            title = improvementButton.getAttribute("content-desc");
        } else if(isIOS()){
            title = improvementButton.getAttribute("label");

        }
        return title;
    }

    public void clickContinueButton() {
        waitForDurationInSeconds(1);
        click(makeoverButton);
    }
    public void clickTrafficOkSureButton() {
        waitForDurationInSeconds(1);
        click(trafficButton);
    }

    public void stopDragScreenUp() {
        tapByPercentage(0.5,0.2);
    }

}

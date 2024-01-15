
package utils;

import factory.Pillar;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

public class AppiumUtils extends Pillar {

    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(getDriver(),3);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }


    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }


    public String getText(WebElement e) {
        String txt = null;
        switch(getPlatform()) {
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
        }
        return txt;
    }

    public void closeApp() {
        switch(getPlatform()){
            case "Android":
                ((InteractsWithApps) getDriver()).terminateApp(getProps().getProperty("appPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) getDriver()).terminateApp(getProps().getProperty("bundleId"));
        }
    }

    public void launchApp() {
        switch(getPlatform()){
            case "Android":
                ((InteractsWithApps) getDriver()).activateApp(getProps().getProperty("appPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) getDriver()).activateApp(getProps().getProperty("bundleId"));
        }
    }

    public enum Side {
        LEFT, RIGHT, TOP, BOTTOM, MIDDLE,TOP_MIDDLE
    }

    public void tapOnSide(WebElement element, Side side, double offsetPercentage) {
        int x = 0, y = 0;

        switch (side) {
            case LEFT:
                x = (int) (element.getLocation().getX() + element.getSize().getWidth() * offsetPercentage);
                y = element.getLocation().getY() + element.getSize().getHeight() / 2;
                break;
            case RIGHT:
                x = (int) (element.getLocation().getX() + element.getSize().getWidth() * (1 - offsetPercentage));
                y = element.getLocation().getY() + element.getSize().getHeight() / 2;
                break;
            case TOP:
                x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                y = (int) (element.getLocation().getY() + element.getSize().getHeight() * offsetPercentage);
                break;
            case BOTTOM:
                x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                y = (int) (element.getLocation().getY() + element.getSize().getHeight() * (1 - offsetPercentage));
                break;
            case MIDDLE:
                x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                y = element.getLocation().getY() + element.getSize().getHeight() / 2;
                break;
            case TOP_MIDDLE:
                x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                y = element.getLocation().getY();
                break;
        }

        // Perform a tap on the specified side of the element
        new TouchAction(getDriver())
                .tap(PointOption.point(x, y))
                .perform();
    }

    public void longTapOnSide(WebElement element, Side side, double offsetPercentage) {
        int x = 0, y = 0;

        switch (side) {
            case LEFT:
                x = (int) (element.getLocation().getX() + element.getSize().getWidth() * offsetPercentage);
                y = element.getLocation().getY() + element.getSize().getHeight() / 2;
                break;
            case RIGHT:
                x = (int) (element.getLocation().getX() + element.getSize().getWidth() * (1 - offsetPercentage));
                y = element.getLocation().getY() + element.getSize().getHeight() / 2;
                break;
            case TOP:
                x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                y = (int) (element.getLocation().getY() + element.getSize().getHeight() * offsetPercentage);
                break;
            case BOTTOM:
                x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                y = (int) (element.getLocation().getY() + element.getSize().getHeight() * (1 - offsetPercentage));
                break;
            case MIDDLE:
                x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                y = element.getLocation().getY() + element.getSize().getHeight() / 2;
                break;
        }

        // Perform a tap on the specified side of the element
        // new TouchAction(getDriver())
        //   .longPress(PointOption.point(x, y))
        //   .perform();
        TouchAction touchAction = new TouchAction(getDriver());
        touchAction.longPress(LongPressOptions.longPressOptions()
                        .withPosition(PointOption.point(x, y))
                        .withDuration(Duration.ofMillis(2000))) // Adjust the duration as needed
                .release()
                .perform();
    }

    public void waitForDurationInSeconds(int seconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), seconds);

        // Use a custom condition that always returns true after the specified duration
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(org.openqa.selenium.WebDriver driver) {
                // Check if the specified duration has passed
                long startTime = System.currentTimeMillis();
                long currentTime;
                do {
                    currentTime = System.currentTimeMillis();
                } while (currentTime - startTime < seconds * 1000);

                // Always return true after the specified duration
                return true;
            }
        });
    }

    public void verticalSwipeToElement(WebElement element, double anchorPercentage) {
        Dimension size = getDriver().manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int elementY = element.getLocation().getY() + element.getSize().getHeight() / 2;
        new TouchAction(getDriver())
                .press(PointOption.point(anchor, size.height / 2))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(anchor, elementY))
                .release().perform();
    }

    public void horizontalSwipeToElement(WebElement element, double anchorPercentage) {
        Dimension size = getDriver().manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int elementX = element.getLocation().getX() + element.getSize().getWidth() / 2;
        new TouchAction(getDriver())
                .press(PointOption.point(size.width / 2, anchor))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(elementX, anchor))
                .release().perform();
    }
    public void verticalSwipeFromElementToElement(WebElement startElement, WebElement endElement) {
        Point startPoint = startElement.getLocation();
        Point endPoint = endElement.getLocation();
        Dimension size = getDriver().manage().window().getSize();

        new TouchAction(getDriver())
                .press(PointOption.point(((Point) startPoint).getX() + startElement.getSize().getWidth() / 2, startPoint.getY() + startElement.getSize().getHeight() / 2))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(endPoint.getX() + endElement.getSize().getWidth() / 2, endPoint.getY() + endElement.getSize().getHeight() / 2))
                .release().perform();
    }

    public void horizontalSwipeFromElementToElement(WebElement startElement, WebElement endElement) {
        Point startPoint = startElement.getLocation();
        Point endPoint = endElement.getLocation();
        Dimension size = getDriver().manage().window().getSize();

        new TouchAction(getDriver())
                .press(PointOption.point(startPoint.getX() + startElement.getSize().getWidth() / 2, startPoint.getY() + startElement.getSize().getHeight() / 2))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(endPoint.getX() + endElement.getSize().getWidth() / 2, endPoint.getY() + endElement.getSize().getHeight() / 2))
                .release().perform();
    }


    //Use percentage only in extreme case, usually not recommended

    public void tapByPercentage (double widthPercentage,  double heightPercentage) {
        Dimension size = getDriver().manage().window().getSize();
        int y = (int) (size.height * heightPercentage);
        int x = (int) (size.width * widthPercentage);
        new TouchAction(getDriver())
                .tap(PointOption.point(x,y))
                .waitAction(waitOptions(ofMillis(450))).perform();
    }

    public void longTapByPercentage (double widthPercentage,  double heightPercentage) {
        Dimension size = getDriver().manage().window().getSize();
        int y = (int) (size.height * heightPercentage);
        int x = (int) (size.width * widthPercentage);
        new TouchAction(getDriver())
                .longPress(PointOption.point(x,y))
                .waitAction(waitOptions(ofMillis(450))).perform();
    }
    public void horizontalSwipeByPercentage (double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = getDriver().manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * endPercentage);
        new TouchAction(getDriver())
                .press(PointOption.point(startPoint, anchor))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(endPoint, anchor))
                .release().perform();
    }

    public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = getDriver().manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);
        new TouchAction(getDriver())
                .press(PointOption.point(anchor, startPoint))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(anchor, endPoint))
                .release().perform();
    }

    public boolean isAndroid() {
        return getDriver().getPlatformName().equalsIgnoreCase("Android");
    }

    // Helper method to check if the current platform is iOS
    public boolean isIOS() {
        return getDriver().getPlatformName().equalsIgnoreCase("iOS");
    }

    public void  init_Strings(String fileName) throws Exception {
        InputStream strings_are;
        String xmlFileName = String.format("Strings/%s",fileName);
        strings_are = getClass().getClassLoader().getResourceAsStream(xmlFileName);
        TestUtils utils = new TestUtils();
        setStrings(utils.parseStringXML(strings_are));

    }

    public void init_TestData(String fileName){
        InputStream details;
        String TestDataFileName = String.format("TestData/%s",fileName);
        details = getClass().getClassLoader().getResourceAsStream(TestDataFileName);
        JSONTokener token = new JSONTokener(details);
        setTestData(new JSONObject(token));
    }

    public String captureScreenshot() {
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

        String projectDirectory = System.getProperty("user.dir");
        String screenshotsPath = "target/Screenshots/";

        // Ensure the directory exists, create it if it doesn't
        File screenshotsDir = new File(projectDirectory, screenshotsPath);
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        String screenshotPath = projectDirectory + "/" + screenshotsPath + System.currentTimeMillis() + ".png";
        try {
            Files.write(Paths.get(screenshotPath), screenshotBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

    public static void cleanupScreenshots(int keepLatest) {
        String screenshotsDirectory = System.getProperty("user.dir") + "/target/Screenshots"; // Assuming the Screenshots directory is inside the project
        File screenshotsDir = new File(screenshotsDirectory);
        File[] screenshotFiles = screenshotsDir.listFiles();

        if (screenshotFiles != null && screenshotFiles.length > keepLatest) {
            Arrays.sort(screenshotFiles, Comparator.comparingLong(File::lastModified));
            for (int i = 0; i < screenshotFiles.length - keepLatest; i++) {
                screenshotFiles[i].delete();
            }
        }
    }


}

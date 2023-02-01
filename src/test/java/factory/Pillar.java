package factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

public class Pillar {

    protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }
    protected static ThreadLocal <String> platform = new ThreadLocal<String>();
    public AppiumDriver getDriver() {
        return driver.get();
    }
    public String getPlatform() {
        return platform.get();
    }

    public void setPlatform(String platform2) {
        platform.set(platform2);
    }

    public Properties getProps() {
        return props.get();
    }

    public void setProps(Properties props2) {
        props.set(props2);
    }

    public Pillar() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Parameters({"platformName", "udid", "deviceName", "platformVersion"})
    @BeforeTest
    public void setUp( String platformName, String udid, String deviceName, String platformVersion) throws Exception {
        InputStream inputStream;
        Properties props;
        URL url;
        AppiumDriver driver;
        URL remoteUrlAndroid = new URL("http://127.0.0.1:4723/");
        URL remoteUrlIos = new URL("http://127.0.0.1:1000/");
        try {
            props = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);

            switch (platformName.toUpperCase()) {

                case "ANDROID":
                    desiredCapabilities.setCapability("app",getClass().getResource(props.getProperty("androidAppUrl")).getFile());
                    desiredCapabilities.setCapability("appium:automationName", "UIAutomator2");
                    desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
                    desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
                    desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
                    desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
                    desiredCapabilities.setCapability("autoGrantPermissions", true);
                    driver = new AndroidDriver(remoteUrlAndroid, desiredCapabilities);
                    break;

                case "IOS":
                    desiredCapabilities.setCapability("app",getClass().getResource(props.getProperty("iosAppUrl")).getFile());
                    desiredCapabilities.setCapability("appium:automationName", "XCUITest");
                    desiredCapabilities.setCapability("appium:includeSafariInWebviews", true);
                    desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
                    desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
                    desiredCapabilities.setCapability("autoDismissAlerts", true);
                    driver = new IOSDriver(remoteUrlIos, desiredCapabilities);
                    break;

                default:
                    throw new Exception("Invalid platform! - " + platformName);

            }
            setDriver(driver);


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }
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


    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if(getDriver() != null){
            getDriver().quit();
        }
    }
}

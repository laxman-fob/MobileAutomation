package factory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.*;
import java.net.URL;

import java.util.HashMap;
import java.util.Properties;


public class Pillar {
     //AppiumDriverLocalService appiumServiceAndroid;
     //AppiumDriverLocalService appiumServiceIOS;

    protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
    protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
    protected static ThreadLocal<JSONObject> testData = new ThreadLocal<>();

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }
    protected static ThreadLocal <String> platform = new ThreadLocal<String>();
    public AppiumDriver getDriver() {
        return driver.get();
    }

    public HashMap<String, String> getStrings() {
        return strings.get();
    }

    public void setStrings(HashMap<String, String> strings2) {
        strings.set(strings2);
    }


    public JSONObject getTestData() {
        return testData.get();
    }
    public void setTestData(JSONObject testData2) {
        testData.set(testData2);
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
    public static ExtentReports extent;
    ExtentSparkReporter spark;

    @Parameters({"platformName", "udid", "deviceName", "platformVersion"})
    @BeforeTest
    public void setUp( String platformName, String udid, String deviceName, String platformVersion) throws Exception {
        InputStream inputStream;
        Properties props;
        URL url;
        AppiumDriver driver;
        URL remoteUrlAndroid = new URL("http://0.0.0.0:4723");
        URL remoteUrlIos = new URL("http://127.0.0.1:4724");
        //startAppiumServer(platformName.toUpperCase());
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

                    // Exclude WebDriverAgent-specific capabilities
                    desiredCapabilities.setCapability("useNewWDA", false);
                    desiredCapabilities.setCapability("shouldUseSingletonTestManager", false);
                    desiredCapabilities.setCapability("appium:shouldUseTestManagerForVisibilityDetection", false);

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

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if(getDriver() != null){
            getDriver().quit();
        }

    }

    @BeforeSuite
    public void reportSetup() {
        if (extent == null) {
            extent = new ExtentReports();

            // Assuming you want to create the reports directory inside the target directory
            String reportsPath = System.getProperty("user.dir") + "/target/reports/";

            // Ensure the directory exists, create it if it doesn't
            File reportsDir = new File(reportsPath);
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            spark = new ExtentSparkReporter(reportsPath + "report.html");
            extent.attachReporter(spark);
        }
    }



    @AfterSuite
    public void teardownSuite() {
        // Flush Extent report
        extent.flush();
        //stopAppiumServers();
    }

/*
    private void startAppiumServer(String platformName) {
        // Use different ports for Android and iOS
        int port = (platformName.equalsIgnoreCase("ANDROID")) ? 4723 : 4724;

        // Command to start the Appium server
        //String appiumCommand = "appium --port " + port;
        String appiumCommand = "appium";

        // Create the process builder
        ProcessBuilder processBuilder = new ProcessBuilder(appiumCommand);

        // Redirect error stream to output stream
        processBuilder.redirectErrorStream(true);

        try {
            // Start the process
            Process process = processBuilder.start();

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Print the output
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stopAppiumServers() {
        if (appiumServiceAndroid != null) {
            appiumServiceAndroid.stop();
        }

        if (appiumServiceIOS != null) {
            appiumServiceIOS.stop();
        }
    }

 */

}

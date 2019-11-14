package Framework;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.util.Strings.isNullOrEmpty;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class DriveInitialization {

    public static WebDriver driver;
    public static Properties prop;
    protected static BatchInfo batch;
    protected static EyesRunner runner;
    protected static Eyes eyes;
    public static SoftAssert softAssert;

    public DriveInitialization() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(ip);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver initialization() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    public static Eyes eyesInitialisation() {
        eyes = new Eyes(runner);
        if (isNullOrEmpty(System.getenv("APPLITOOLS_API_KEY")))
            throw new RuntimeException("No API Key found; Please set environment variable 'APPLITOOLS_API_KEY'.");
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setBatch(batch);
        return eyes;
    }

    public void openEyes(String appName, String testName) {
        eyes.open(driver, appName, testName, new RectangleSize(800, 800));
    }

    public void takeApplitoolScreenshot(String tagName) {
        eyes.checkWindow(tagName);
        Reporter.log("Screenshot For Applitool Taken with Tag Name : " + tagName, true);

    }


}

package tests.ApplitoolsTests;

import Framework.DriveInitialization;
import Pages.LoginPage;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.ClassicRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

/**
 * created by yash.zanwar on 2019-11-14
 */
public class DynamicContentTests extends DriveInitialization {

    @BeforeClass
    public static void setBatch() {
        batch = new BatchInfo("Dynamic Content Batch");
    }

    @BeforeMethod
    public void createDriver(Method method) {
        runner = new ClassicRunner();
        eyes = eyesInitialisation();
        driver = initialization();
        eyes.setForceFullPageScreenshot(true);
        openEyes("Hackathon", method.getName());
        driver.get(prop.getProperty("addURLVersion2"));
        softAssert = new SoftAssert();
    }

    @Test
    public void testDynamicContentWithAppliTools() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
        takeApplitoolScreenshot("App Page with Dynamic Ad");

    }

    @AfterMethod
    public void tearDown() {
        eyes.closeAsync();
        driver.quit();
        eyes.abortIfNotClosed();
        runner.getAllTestResults();
    }
}

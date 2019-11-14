package tests.ApplitoolsTests;

import Framework.DriveInitialization;
import Pages.LoginPage;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.ClassicRunner;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class DataDrivenTests extends DriveInitialization {

    @BeforeClass
    public static void setBatch() {
        batch = new BatchInfo("Data Driven Batch");
        runner = new ClassicRunner();
    }

    @BeforeMethod
    public void createDriver(Method method) {
        runner = new ClassicRunner();
        eyes = eyesInitialisation();
        driver = initialization();
        eyes.setForceFullPageScreenshot(true);
        openEyes("Hackathon", method.getName());
        driver.get(prop.getProperty("urlVersion1"));
        softAssert = new SoftAssert();
    }

    @Test
    public void testNullUserAndPass() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("", "");
        takeApplitoolScreenshot("Login Window With UserName And Password Null");
    }

    @Test
    public void testNullPass() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(prop.getProperty("userName"), "");
        takeApplitoolScreenshot("Login Window With Password Null");
    }

    @Test
    public void testNullUserName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("", prop.getProperty("password"));
        takeApplitoolScreenshot("Login Window with UserName Null");
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
        takeApplitoolScreenshot("App Page");
    }

    @AfterMethod
    public void tearDown() {
        eyes.closeAsync();
        driver.quit();
        eyes.abortIfNotClosed();
    }

    @AfterClass
    public void getResults() {
        runner.getAllTestResults();
    }

}



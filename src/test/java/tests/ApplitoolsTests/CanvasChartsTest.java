package tests.ApplitoolsTests;

import Framework.DriveInitialization;
import Pages.AppPage;
import Pages.ExpenseChartPage;
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
public class CanvasChartsTest extends DriveInitialization {

    @BeforeClass
    public static void setBatch() {
        batch = new BatchInfo("Canvas Charts Batch");
    }

    @BeforeMethod
    public void createDriver(Method method) {
        runner = new ClassicRunner();
        eyes = eyesInitialisation();
        driver = initialization();
        eyes.setForceFullPageScreenshot(true);
        openEyes("Hackathon", method.getName());
        driver.get(prop.getProperty("urlVersion2"));
        softAssert = new SoftAssert();
    }

    @Test
    public void testCanvasChartsWithAppliTools() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
        AppPage appPage = new AppPage(driver);
        appPage.clickOnCompareExpenses();
        takeApplitoolScreenshot("App Page With Expense Chart Of Two Years");
        ExpenseChartPage expenseChartPage = new ExpenseChartPage(driver);
        expenseChartPage.clickOnShowDataNextYr();
        takeApplitoolScreenshot("App Page With Expense Chart Of Next Years");
    }

    @AfterMethod
    public void tearDown() {
        eyes.closeAsync();
        driver.quit();
        eyes.abortIfNotClosed();
        runner.getAllTestResults();
    }
}

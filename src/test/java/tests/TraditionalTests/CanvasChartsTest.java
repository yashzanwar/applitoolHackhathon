package tests.TraditionalTests;

import Framework.DriveInitialization;
import Pages.AppPage;
import Pages.ExpenseChartPage;
import Pages.LoginPage;
import com.testautomationguru.ocular.Ocular;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class CanvasChartsTest extends DriveInitialization {

    @BeforeMethod
    public void createDriver() {
        driver = initialization();
        driver.get(prop.getProperty("urlVersion2"));
        softAssert = new SoftAssert();
        Ocular.config()
                .snapshotPath(Paths.get("src/test/resources/snap"))
                .resultPath(Paths.get("src/test/resources/result"));
    }

    @Test
    public void testCanvasCharts() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
            AppPage appPage = new AppPage(driver);
            appPage.clickOnCompareExpenses();
            ExpenseChartPage expenseChartPage = new ExpenseChartPage(driver);
            softAssert.assertTrue(expenseChartPage.verifyExpenseChart());
            expenseChartPage.clickOnShowDataNextYr();
            softAssert.assertTrue(expenseChartPage.verifyNextYearExpenseChart());
        } finally {
            softAssert.assertAll();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

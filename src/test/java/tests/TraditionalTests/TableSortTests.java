package tests.TraditionalTests;

import Framework.DriveInitialization;
import Pages.AppPage;
import Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class TableSortTests extends DriveInitialization {

    @BeforeMethod
    public void createDriver() {
        driver = initialization();
        driver.get(prop.getProperty("urlVersion1"));
        softAssert = new SoftAssert();
    }

    @Test
    public void testTableSorts() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
            AppPage appPage = new AppPage(driver);
            ArrayList<String> tableContents = appPage.getAllTheRecentTransactions();
            appPage.clickOnAmountHeader();
            softAssert.assertTrue(appPage.checkAmountIsInAscendingOrder(), "The Amount is not in ascending order.");
            ArrayList<String> tableContents1 = appPage.getAllTheRecentTransactions();
            softAssert.assertTrue(appPage.checkTheTransactionsAreIntact(tableContents, tableContents1), "Transactions are not intact.");
        } finally {
            softAssert.assertAll();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

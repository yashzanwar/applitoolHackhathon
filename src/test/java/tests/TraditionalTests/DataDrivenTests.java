package tests.TraditionalTests;

import Framework.DriveInitialization;
import Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class DataDrivenTests extends DriveInitialization {

    @BeforeMethod
    public void createDriver() {
        driver = initialization();
        driver.get(prop.getProperty("urlVersion1"));
        softAssert = new SoftAssert();
    }

    @Test
    public void testDataDrivenCases() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.doLogin("", "");
            softAssert.assertEquals(loginPage.getLoginErrorText(), "Both Username and Password must be present", loginPage.loginError().getName() + " has a different error.");
            driver.navigate().refresh();
            loginPage.doLogin(prop.getProperty("userName"), "");
            softAssert.assertEquals(loginPage.getLoginErrorText(), "Password must be present", loginPage.loginError().getName() + " has a different error.");
            driver.navigate().refresh();
            loginPage.doLogin("", prop.getProperty("password"));
            softAssert.assertEquals(loginPage.getLoginErrorText(), "Username must be present", loginPage.loginError().getName() + " has a different error.");
            driver.navigate().refresh();
            loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
            softAssert.assertFalse(loginPage.checkLoginErrorIsVisible(), loginPage.loginError().getName() + "is visible.");
        } finally {
            softAssert.assertAll();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

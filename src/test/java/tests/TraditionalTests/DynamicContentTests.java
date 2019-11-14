package tests.TraditionalTests;

import Framework.DriveInitialization;
import Pages.AppPage;
import Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class DynamicContentTests extends DriveInitialization {

    @BeforeMethod
    public void createDriver() {
        driver = initialization();
        driver.get(prop.getProperty("addURLVersion1"));
        softAssert = new SoftAssert();
    }

    @Test
    public void testDynamicContents() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
            AppPage appPage = new AppPage(driver);
            softAssert.assertEquals(appPage.getNumberOfAdDisplayed(), 2, "Advertisement is not getting displayed.");
        } finally {
            softAssert.assertAll();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

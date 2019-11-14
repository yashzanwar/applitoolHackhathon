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
public class LoginPageTests extends DriveInitialization {

    @BeforeMethod
    public void createDriver() {
        driver = initialization();
        driver.get(prop.getProperty("urlVersion1"));
        softAssert = new SoftAssert();
    }

    @Test
    public void testUIElements() {
        try {
            LoginPage loginPage = new LoginPage(softAssert, driver);
            softAssert.assertTrue(loginPage.testLogoImageComing(), "Logo is not coming.");
            loginPage.checkHeaderAndItsText();
            loginPage.checkLoginFormComponents();
            loginPage.checkSocialMediaAccounts();
        } finally {
            softAssert.assertAll();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

package Pages;

import Framework.BrowserAction;
import Framework.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class LoginPage extends BrowserAction {

    SoftAssert softAssert;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage(SoftAssert softAssert, WebDriver driver) {
        this.softAssert = softAssert;
        this.driver = driver;
    }

    /**************************************** Locators ***********************************************************/

    private Locator logo() {
        return new Locator(By.xpath("//*[@src='img/logo-big.png']"), "Website Logo");
    }

    private Locator header() {
        return new Locator(By.xpath("//*[@class='auth-header']"), "Website Header");
    }

    private Locator userName() {
        return new Locator(By.xpath("//*[@class='form-group'][1]/label"), "Username Header");
    }

    private Locator userNameTextBox() {
        return new Locator(By.id("username"), "UserName text Box");
    }

    private Locator genderIcon() {
        return new Locator(By.xpath("//*[@class='pre-icon os-icon os-icon-user-male-circle']"), "Gender Icon");
    }

    private Locator password() {
        return new Locator(By.xpath("//*[@class='form-group'][2]/label"), "Password Header");
    }

    private Locator passwordTextBox() {
        return new Locator(By.id("password"), "Password Text Box");
    }

    private Locator fingerprintImage() {
        return new Locator(By.xpath("//*[@class='pre-icon os-icon os-icon-fingerprint']"), "Finger print Image");
    }

    private Locator loginButton() {
        return new Locator(By.id("log-in"), "Log In Button");
    }

    private Locator twitterImage() {
        return new Locator(By.xpath("//*[contains(@src,'twitter')]"), "Twitter Image");
    }

    private Locator facebookImage() {
        return new Locator(By.xpath("//*[contains(@src,'facebook')]"), "Facebook Image");
    }

    private Locator linkedInImage() {
        return new Locator(By.xpath("//*[contains(@src,'linkedin')]"), "LinkedIn Image");
    }

    public Locator loginError() {
        return new Locator(By.xpath("//*[@class='alert alert-warning']"), "Login Error");
    }

    /*********************************************** Methods ************************************************************/

    public boolean testLogoImageComing() {
        return isDisplayed(logo());
    }

    public void checkHeaderAndItsText() {
        softAssert.assertTrue(isDisplayed(header()), header().getName() + " Not Visible.");
        softAssert.assertEquals(getText(header()).trim(), "Login Form", header().getName() + " Has Different Name.");
    }

    public void checkLoginFormComponents() {
        softAssert.assertTrue(isDisplayed(userName()), userName().getName() + " Not Visible.");
        softAssert.assertEquals(getText(userName()), "Username", userName().getName() + " Has Different Name.");
        softAssert.assertTrue(isDisplayed(userNameTextBox()), userNameTextBox().getName() + " Not Visible.");
        softAssert.assertEquals(getAttribute(userNameTextBox(), "placeholder"), "Enter your username", userNameTextBox().getName() + " Has Different Name.");
        softAssert.assertTrue(isDisplayed(password()), password().getName() + " Not Visible.");
        softAssert.assertEquals(getText(password()), "Password", password().getName() + " Has Different Name.");
        softAssert.assertTrue(isDisplayed(passwordTextBox()), passwordTextBox().getName() + " Not Visible.");
        softAssert.assertEquals(getAttribute(passwordTextBox(), "placeholder"), "Enter your password", passwordTextBox().getName() + " Has Different Name.");
        softAssert.assertTrue(isDisplayed(loginButton()), loginButton().getName() + " Not Visible.");
        softAssert.assertEquals(getText(loginButton()), "Log In", loginButton().getName() + " Has Different Name.");
        softAssert.assertTrue(isDisplayed(genderIcon()), genderIcon().getName() + " Not Visible.");
        softAssert.assertTrue(isDisplayed(fingerprintImage()), fingerprintImage().getName() + " Not Visible.");
    }

    public void checkSocialMediaAccounts() {
        softAssert.assertTrue(isDisplayed(twitterImage()), twitterImage().getName() + " Not Visible.");
        softAssert.assertTrue(isDisplayed(facebookImage()), facebookImage().getName() + " Not Visible.");
        softAssert.assertTrue(isDisplayed(linkedInImage()), linkedInImage().getName() + " Not Visible.");
    }

    public void doLogin(String userName, String password) {
        EnterValue(userNameTextBox(), userName);
        EnterValue(passwordTextBox(), password);
        click(loginButton());
    }

    public String getLoginErrorText() {
        return getText(loginError());
    }

    public boolean checkLoginErrorIsVisible() {
        return waitUntilDisplayed(loginError(), 2);
    }


}

package Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class BrowserAction {

    protected WebDriver driver;
    static int DefaultTime = 60;

    protected void click(Locator locator) {

        WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
        if (locator.getElement() == null) {
            webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
            driver.findElement(locator.getBy()).click();
        } else {
            WebElement element = locator.getElement().findElement(locator.getBy());
            webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
            element.click();
        }
        Reporter.log("Clicked On " + locator.getName() + "", true);
    }

    protected void EnterValue(Locator locator, String value) {

        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
        webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        if (locator.getElement() == null) {
            driver.findElement(locator.getBy()).sendKeys(value);
        } else {
            locator.getElement().findElement(locator.getBy()).sendKeys(value);
        }
        Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'", true);
    }

    protected String getText(Locator locator) {
        String Text = "No data";
        try {
            if (locator.getElement() == null) {
                WebElement webElement = driver.findElement(locator.getBy());
                Text = webElement.getText();
            } else {
                WebElement webElement = locator.getElement().findElement(locator.getBy());
                Text = webElement.getText();
            }
            Reporter.log(locator.getName() + " : " + Text, true);
            return Text;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Text;
    }

    protected boolean waitUntilDisplayed(Locator locator, int Timeout) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, Timeout);
        driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
        try {
            if (locator.getElement() == null) {
                webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
            } else {
                System.out.println(locator.getBy());
                System.out.println(locator.getElement());
                WebElement element = locator.getElement().findElement(locator.getBy());
                webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
    }

    protected String getAttribute(Locator locator, String Attribute) {
        String attributevalue;
        if (locator.getElement() == null) {
            WebElement element = driver.findElement(locator.getBy());
            attributevalue = element.getAttribute(Attribute);
        } else {
            WebElement element = locator.getElement().findElement(locator.getBy());
            attributevalue = element.getAttribute(Attribute);
        }
        Reporter.log(locator.getName() + " : " + attributevalue, true);
        return attributevalue;
    }

    protected boolean isDisplayed(Locator locator) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, 1);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            if (locator.getElement() == null) {
                webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
            } else {
                WebElement element = locator.getElement().findElement(locator.getBy());
                webdriverWait.until(ExpectedConditions.visibilityOf(element));
            }
            Reporter.log(locator.getName() + " is Displayed", true);
            return true;

        } catch (Exception e) {
            Reporter.log(locator.getName() + " is not Displayed", true);
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
    }

    protected List<WebElement> getWebElements(Locator locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
        } catch (Exception e) {
            Reporter.log("List of element not displayed with locator " + locator.getName(), true);
        }
        List<WebElement> webElementList = null;
        if (locator.getElement() == null) {
            webElementList = driver.findElements(locator.getBy());
        }
        if (!webElementList.isEmpty()) {
            Reporter.log("Viewed all list of " + locator.getName() + " and its size is " + webElementList.size(), true);
        } else {
            Reporter.log("Unable to view list of " + locator.getName(), true);
        }
        driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        return webElementList;
    }

    protected void waitFor(int a) {
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

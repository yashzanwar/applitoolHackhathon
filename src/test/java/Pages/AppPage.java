package Pages;

import Framework.BrowserAction;
import Framework.Locator;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

/**
 * created by yash.zanwar on 2019-11-13
 */
public class AppPage extends BrowserAction {

    public AppPage(WebDriver driver) {
        this.driver = driver;
    }

    /*********************************************** Locators ***********************************************************/

    private Locator amountHeader() {
        return new Locator(By.id("amount"), "Amount Header");
    }

    private Locator tableRows() {
        return new Locator(By.xpath("//tbody/tr"), "Table Rows");
    }

    private Locator status(String a) {
        return new Locator(By.xpath("//tbody/tr[" + a + "]//td[1]/span[2]"), "Status");
    }

    private Locator date(String a) {
        return new Locator(By.xpath("//tbody/tr[" + a + "]//td[2]/span[1]"), "Date");
    }

    private Locator description(String a) {
        return new Locator(By.xpath("//tbody/tr[" + a + "]//td[3]/span"), "Description");
    }

    private Locator category(String a) {
        return new Locator(By.xpath("//tbody/tr[" + a + "]//td[4]/a"), "Category");
    }

    private Locator amount(String a) {
        return new Locator(By.xpath("//tbody/tr[" + a + "]//td[5]/span"), "Amount");
    }

    private Locator compareExpensesButton() {
        return new Locator(By.id("showExpensesChart"), "Compare Expenses");
    }

    private Locator adGif() {
        return new Locator(By.xpath("//*[contains(@src,'gif')]"), "Advertisement GIFs");
    }

    /*********************************************** Methods ************************************************************/

    public ArrayList<String> getAllTheRecentTransactions() {
        List<WebElement> elements = getWebElements(tableRows());
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            String j = String.valueOf(i + 1);
            String uniqueString = getText(status(j)) + " " + getText(date(j))
                    + " " + getText(description(j)) + " " + getText(category(j)) + " " + getText(amount(j));
            arrayList.add(uniqueString);
        }
        return arrayList;
    }

    public void clickOnAmountHeader() {
        click(amountHeader());
        waitFor(3000);
    }

    public boolean checkAmountIsInAscendingOrder() {
        List<WebElement> elements = getWebElements(tableRows());
        ArrayList<Double> arrayList = new ArrayList<Double>();
        for (int i = 0; i < elements.size(); i++) {
            String j = String.valueOf(i + 1);
            String amount = getText(amount(j)).replaceAll("[A-Za-z]", "").replaceAll(",", "");
            if (amount.contains("+")) {
                arrayList.add(Double.parseDouble(amount.replace("+", "").trim()));
            } else {
                arrayList.add(Double.parseDouble(amount.replace("-", "").trim()) * -1);
            }
        }
        Reporter.log("Order Of Amount From UI :- " + arrayList, true);
        return Ordering.natural().isOrdered(arrayList);
    }

    public boolean checkTheTransactionsAreIntact(ArrayList<String> original, ArrayList<String> afterSorting) {
        Reporter.log("Recent Transactions Before Sorting : " + original, true);
        Reporter.log("Recent Transactions After Sorting : " + afterSorting, true);
        boolean status = false;
        for (String a : original) {
            status = afterSorting.contains(a);
        }
        return status;
    }

    public void clickOnCompareExpenses() {
        click(compareExpensesButton());
    }

    public int getNumberOfAdDisplayed() {
        return getWebElements(adGif()).size();
    }
}

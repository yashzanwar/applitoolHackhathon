package Pages;

import Framework.BrowserAction;
import Framework.Locator;
import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * created by yash.zanwar on 2019-11-14
 */
public class ExpenseChartPage extends BrowserAction {

    private Map<String, String> map;

    public ExpenseChartPage(WebDriver driver) {
        this.driver = driver;
        map = new HashMap<>();
        map.put("canvas", "canvas.png");
        map.put("nextYrCanvas", "nextyearcanvas.png");
    }

    /**************************************************************** Locators ****************************************************************************************/

    private Locator chartImage() {
        return new Locator(By.xpath("//*[@class='chartjs-render-monitor']"), "Chart Image");
    }

    private Locator showDataNextYrButton() {
        return new Locator(By.xpath("//*[@class='btn btn-warning']"), "Show Data For Next Year");
    }

    /**************************************************************** Methods ****************************************************************************************/

    public boolean verifyExpenseChart() {
        waitFor(4000);
        return this.verifyChart(map.get("canvas"), driver.findElement(chartImage().getBy()));
    }

    public boolean verifyNextYearExpenseChart() {
        waitFor(4000);
        return this.verifyChart(map.get("nextYrCanvas"), driver.findElement(chartImage().getBy()));
    }

    private boolean verifyChart(String fileName, WebElement element) {
        Path path = Paths.get(fileName);
        OcularResult result = Ocular.snapshot()
                .from(path)
                .sample()
                .using(driver)
                .element(element)
                .compare();

        return result.isEqualsImages();
    }

    public void clickOnShowDataNextYr() {
        click(showDataNextYrButton());
    }
}

package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConstructorPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final By bunsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(.//span, 'Булки')]");
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(.//span, 'Соусы')]");
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and contains(.//span, 'Начинки')]");
    private final String currentTabClass = "tab_tab_type_current__2BEPc";

    //Нажимаем на вкладку Начинки
    public void clickBunsTab() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    //Нажимаем на вкладку Соусы
    public void clickSaucesTab() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    //Нажимаем на вкладку Начинки
    public void clickFillingsTab() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public boolean isBunsTabActive() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
            wait.until(ExpectedConditions.attributeContains(element, "class", currentTabClass));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSaucesTabActive() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab));
            wait.until(ExpectedConditions.attributeContains(element, "class", currentTabClass));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFillingsTabActive() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
            wait.until(ExpectedConditions.attributeContains(element, "class", currentTabClass));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}

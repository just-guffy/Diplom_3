package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By loginEmailInputField = By.xpath("//div[contains(@class, 'input') and not(contains(@class, 'input_status_active'))]//input[@type='text' and @name='name']");
    private final By loginPasswordInputField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Войти']");
    private final By inscriptionCollectBurger = By.xpath("//h1[text()='Соберите бургер']");
    private final By createOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By loginToAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By loginButtonOnRegistrationAndForgotPasswordForm = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and text()='Войти']");

    //Вводим емейл при логине
    public void setLoginEmail (String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailInputField));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.clear();
        element.sendKeys(email);
    }

    //Вводим пароль
    public void setPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(loginPasswordInputField));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.clear();
        element.sendKeys(password);
    }

    //Нажимаем на кнопку войти
    public void clickLoginButton() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    //Нажимаем на кнопку войти в аккаунт
    public void clickLoginToAccountButton() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginToAccountButton));
        element.click();
    }

    //Нажимаем на кнопку Войти в форме регистрации и в форме восстановления пароля
    public void clickLoginButtonOnRegistrationAndForgotPasswordForm() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonOnRegistrationAndForgotPasswordForm));
        element.click();
    }

    //Проверяем наличие надписи "Соберите бургер" и кликабельность кнопки "Оформить заказ"
    public void checkCollectBurgerAndCheckOrderButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inscriptionCollectBurger));
        wait.until(ExpectedConditions.elementToBeClickable(createOrderButton));
    }
}

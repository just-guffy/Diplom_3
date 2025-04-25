package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By nameInputField = By.xpath("//div[contains(@class, 'input_type_text')]/label[contains(@class, 'input__placeholder') and text()='Имя']/following-sibling::input[@type='text']");
    private final By registrEmailInputField = By.xpath("//input[@type='text' and preceding-sibling::label[contains(.,'Email')]]");
    private final By registrPasswordInputField = By.xpath("//input[@type='password']");
    private final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath("//button[normalize-space()='Войти']");
    private final By signInvalidPassword = By.xpath("//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']");

    //Вводим имя
    public void setName (String name) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputField));
        element.sendKeys(name);
    }

    //Вводим емейл
    public void setEmail (String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(registrEmailInputField));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.clear();
        element.sendKeys(email);
    }

    //Вводим пароль
    public void setPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(registrPasswordInputField));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.clear();
        element.sendKeys(password);
    }

    //Нажимаем на кнопку зарегистрироваться
    public void clickRegistrationButton () {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
        element.click();
    }

    //Кнопка войти кликабельна
    public void clickableLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    //Появление фразы Некорректный пароль
    public void signInvalidPassword () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInvalidPassword));
    }
}

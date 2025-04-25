package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By userAccountButton = By.xpath("//p[contains(@class, 'AppHeader_header__linkText')][normalize-space()='Личный Кабинет']");
    private final By inscriptionCollectBurger = By.xpath("//h1[text()='Соберите бургер']");
    private final By createOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By profileButton = By.xpath("//a[@href='/account/profile' and contains(@class, 'Account_link_active__2opc9') and text()='Профиль']");
    private final By historyOrdersButton = By.xpath("//a[@href='/account/order-history' and contains(@class, 'Account_link__2ETsJ') and text()='История заказов']");
    private final By exitButton = By.xpath("//button[contains(@class, 'Account_button__14Yp3') and text()='Выход']");
    private final By constructorButton = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and text()='Конструктор']");
    private final By logoButton = By.xpath("//div[contains(@class, 'AppHeader_header__logo')]/a[@href='/']");
    private final By loginEmailInputField = By.xpath("//div[contains(@class, 'input') and not(contains(@class, 'input_status_active'))]//input[@type='text' and @name='name']");
    private final By loginPasswordInputField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Войти']");
    private final By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");

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

    //Нажимаем на кнопку выйти
    public void clickExitButton() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    //Нажимаем на кнопку личный кабинет
    public void clickUserAccountButton() {
        try {
            // 1. Ожидаем исчезновения модального окна (если есть)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
        } catch (TimeoutException e) {
            System.out.println("Модальное окно не найдено или не исчезло в течение таймаута");
        }

        // 2. Ожидаем кликабельности элемента и скроллим к нему
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(userAccountButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        // 3. Пытаемся кликнуть стандартным способом
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // 4. Если не получилось - используем JS клик
            System.out.println("Обычный клик не сработал, используем JavaScript");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        }
    }

    //Нажимаем на кнопку конструктор
    public void clickConstructorButton() {
        try {
            // 1. Ожидаем исчезновения модального окна (если есть)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
        } catch (TimeoutException e) {
            System.out.println("Модальное окно не найдено или не исчезло в течение таймаута");
        }

        // 2. Ожидаем кликабельности элемента и скроллим к нему
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(constructorButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        // 3. Пытаемся кликнуть стандартным способом
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // 4. Если не получилось - используем JS клик
            System.out.println("Обычный клик не сработал, используем JavaScript");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        }
    }

    //Нажимаем на логотип Stellar Burgers
    public void clickLogoButton() {
        try {
            // 1. Ожидаем исчезновения модального окна (если есть)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
        } catch (TimeoutException e) {
            System.out.println("Модальное окно не найдено или не исчезло в течение таймаута");
        }

        // 2. Ожидаем кликабельности элемента и скроллим к нему
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(logoButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        // 3. Пытаемся кликнуть стандартным способом
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // 4. Если не получилось - используем JS клик
            System.out.println("Обычный клик не сработал, используем JavaScript");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        }
    }

    //Проверяем кликабельность кнопки Войти
    public void checkLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    //Проверяем наличие кнопок Профиль, История заказов, Выход
    public void checkProfileAndHistoryOrdersAndExitButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(historyOrdersButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }

    //Проверяем наличие надписи "Соберите бургер" и кликабельность кнопки "Оформить заказ"
    public void checkCollectBurgerAndCheckOrderButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inscriptionCollectBurger));
        wait.until(ExpectedConditions.elementToBeClickable(createOrderButton));
    }
}

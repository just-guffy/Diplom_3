import io.qameta.allure.Step;
import model.Credentials;
import model.User;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import utils.BrowserFactory;
import user.UserService;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AccountTest {
    @Parameterized.Parameter
    public String browser;

    @Parameterized.Parameters
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"firefox"}
        });
    }

    private static WebDriver driver;
    private static AccountPage accountPage;
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String LOGIN_ENDPOINT = "/login";
    private UserService userService;
    private String userAccessToken;
    private String name = "Alex";
    private String email = "user-test1209199118@yandex.ru";
    private String validPassword = "123456";

    @Before
    public void setUp() throws Exception {
        // Создаем драйвер с помощью фабрики браузеров
        driver = BrowserFactory.createDriver(browser);
        driver.manage().window().maximize();

        // Инициализация сервиса
        userService = new UserService(BASE_URI);

        // Создание пользователя через API
        User user = new User(email, validPassword, name);
        userService.createUser(user);

        // Получение токена через авторизацию
        Credentials credentials = new Credentials(email, validPassword);
        userAccessToken = userService.loginAndGetToken(credentials);

        // Инициализация страницы логина
        accountPage = new AccountPage(driver);
        driver.get(BASE_URI + LOGIN_ENDPOINT);
    }

    @Test
    @Step("Переход по клику на «Личный кабинет»")
    public void clickUserAccountButtonTest(){
        accountPage.setLoginEmail(email);
        accountPage.setPassword(validPassword);
        accountPage.clickLoginButton();
        accountPage.clickUserAccountButton();
        accountPage.checkProfileAndHistoryOrdersAndExitButton();
    }

    @Test
    @Step("Переход из личного кабинета по клику на Конструктор")
    public void clickConstructorButtonFromUserAccountTest(){
        accountPage.setLoginEmail(email);
        accountPage.setPassword(validPassword);
        accountPage.clickLoginButton();
        accountPage.clickUserAccountButton();
        accountPage.clickConstructorButton();
        accountPage.checkCollectBurgerAndCheckOrderButton();
    }

    @Test
    @Step("Переход из личного кабинета по клику на логотип Stellar Burgers")
    public void clickLogoButtonFromUserAccountTest(){
        accountPage.setLoginEmail(email);
        accountPage.setPassword(validPassword);
        accountPage.clickLoginButton();
        accountPage.clickUserAccountButton();
        accountPage.clickLogoButton();
        accountPage.checkCollectBurgerAndCheckOrderButton();
    }

    @Test
    @Step("Выход по кнопке «Выйти» в личном кабинете")
    public void clickExitButtonFromUserAccountTest(){
        accountPage.setLoginEmail(email);
        accountPage.setPassword(validPassword);
        accountPage.clickLoginButton();
        accountPage.clickUserAccountButton();
        accountPage.clickExitButton();
        accountPage.checkLoginButton();
    }

    @After
    public void tearDown() {
        if (userAccessToken != null) {
            userService.deleteUser(userAccessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

}

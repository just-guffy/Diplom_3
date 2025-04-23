import io.qameta.allure.Step;
import model.Credentials;
import model.User;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.BrowserFactory;
import user.UserService;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LoginTest {

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
    private static LoginPage loginPage;
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_ENDPOINT = "/register";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String FORGOT_PASSWORD_ENDPOINT = "/forgot-password";
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
        loginPage = new LoginPage(driver);
    }

    @Test
    @Step("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginThroughLoginButtonOnMainPage(){
        driver.get(BASE_URI);
        loginPage.clickLoginToAccountButton();
        loginPage.setLoginEmail(email);
        loginPage.setPassword(validPassword);
        loginPage.clickLoginButton();
        loginPage.checkCollectBurgerAndCheckOrderButton();
    }

    @Test
    @Step("Вход через кнопку «Личный кабинет»")
    public void loginThroughUserAccountButton(){
        driver.get(BASE_URI + LOGIN_ENDPOINT);
        loginPage.setLoginEmail(email);
        loginPage.setPassword(validPassword);
        loginPage.clickLoginButton();
        loginPage.checkCollectBurgerAndCheckOrderButton();
    }

    @Test
    @Step("Вход через кнопку в форме регистрации")
    public void loginThroughButtonOnRegistrationForm(){
        driver.get(BASE_URI + REGISTER_ENDPOINT);
        loginPage.clickLoginButtonOnRegistrationAndForgotPasswordForm();
        loginPage.setLoginEmail(email);
        loginPage.setPassword(validPassword);
        loginPage.clickLoginButton();
        loginPage.checkCollectBurgerAndCheckOrderButton();
    }

    @Test
    @Step("Вход через кнопку в форме восстановления пароля")
    public void loginThroughButtonOnForgotPasswordForm(){
        driver.get(BASE_URI + FORGOT_PASSWORD_ENDPOINT);
        loginPage.clickLoginButtonOnRegistrationAndForgotPasswordForm();
        loginPage.setLoginEmail(email);
        loginPage.setPassword(validPassword);
        loginPage.clickLoginButton();
        loginPage.checkCollectBurgerAndCheckOrderButton();
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

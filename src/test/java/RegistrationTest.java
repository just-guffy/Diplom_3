import com.github.javafaker.Faker;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import pages.RegistrationPage;
import utils.BrowserFactory;
import user.UserService;
import model.Credentials;

public class RegistrationTest {

    private static WebDriver driver;
    private static RegistrationPage registrationPage;
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_ENDPOINT = "/register";
    private UserService userService;
    private String userAccessToken;
    private Faker faker = new Faker();
    private String name = faker.name().firstName();
    private String email = faker.internet().emailAddress();
    private String validPassword = "123456";
    private String invalidPassword = "1234";

    @Before
    public void setUp() throws Exception {
        // Читаем название браузера из системного свойства или другого источника конфигурации
        String browser = System.getProperty("browser", "chrome");

        // Создаем драйвер с помощью фабрики браузеров.
        driver = BrowserFactory.createDriver(browser);

        // Максимизируем окно браузера
        driver.manage().window().maximize();

        registrationPage = new RegistrationPage(driver);
        userService = new UserService(BASE_URI);
    }

    @Test
    @Step("Проверка успешной регистрации")
    public void validRegistration (){
        driver.get(BASE_URI + REGISTER_ENDPOINT);
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(validPassword);
        registrationPage.clickRegistrationButton();
        registrationPage.clickableLoginButton();
        userAccessToken = userService.loginAndGetToken(new Credentials(email, validPassword));
    }

    @Test
    @Step("Проверка ошибки для некорректного пароля")
    public void invalidRegistration (){
        driver.get(BASE_URI + REGISTER_ENDPOINT);
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(invalidPassword);
        registrationPage.clickRegistrationButton();
        registrationPage.signInvalidPassword();
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

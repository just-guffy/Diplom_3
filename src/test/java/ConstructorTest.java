import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.ConstructorPage;
import utils.BrowserFactory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest {
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
    private static ConstructorPage constructorPage;
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    @Before
    public void setUp() throws Exception {
        // Создаем драйвер с помощью фабрики браузеров
        driver = BrowserFactory.createDriver(browser);
        driver.manage().window().maximize();

        // Инициализация страницы логина
        constructorPage = new ConstructorPage(driver);
        driver.get(BASE_URI);
    }

    @Test
    @Step("Проверка перехода к вкладке Булки c вкладки Соусы")
    public void testBunsTabClick () {
        // Кликаем на вкладку "Соусы"
        constructorPage.clickSaucesTab();

        // Кликаем на вкладку "Булки"
        constructorPage.clickBunsTab();

        // Проверяем, что теперь активна вкладка "Булки"
        assertTrue(constructorPage.isBunsTabActive());
    }

    @Test
    @Step("Проверка перехода к вкладке Соусы")
    public void testSaucesTabClick() {
        // Сначала переходим на вкладку "Соусы"
        constructorPage.clickSaucesTab();

        // Проверяем, что теперь активна вкладка "Соусы"
        assertTrue(constructorPage.isSaucesTabActive());
    }

    @Test
    @Step("Проверка перехода к вкладке Начинки")
    public void testFillingsTabClick() {
        // Сначала переходим на вкладку "Начинки"
        constructorPage.clickFillingsTab();

        // Проверяем, что теперь активна вкладка "Начинки"
        assertTrue(constructorPage.isFillingsTabActive());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

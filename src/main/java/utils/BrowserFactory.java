package utils;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    private static String getResourcePath(String resourceName) {
        URL url = ClassLoader.getSystemResource(resourceName);
        if (url == null) {
            throw new RuntimeException("Resource not found: " + resourceName);
        }
        return new File(url.getFile()).getAbsolutePath(); // Получаем абсолютный путь к файлу
    }

    public static WebDriver createDriver(String browser) throws Exception {
        if ("chrome".equals(browser.toLowerCase())) {
            System.setProperty("webdriver.chrome.driver", getResourcePath("chromedriver.exe"));
            return new ChromeDriver();
        } else if ("firefox".equals(browser.toLowerCase())) {
            System.setProperty("webdriver.gecko.driver", getResourcePath("geckodriver.exe"));
            return new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
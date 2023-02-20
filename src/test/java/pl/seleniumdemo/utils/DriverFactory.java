package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() throws IOException {
        String name = PropertiesLoader.loadProperty("browser.name");

        if (name.equals("firefox")) {

            driver = new FirefoxDriver();
            WebDriverManager.firefoxdriver().setup();
        } else {

            driver = new ChromeDriver();
            WebDriverManager.chromedriver().setup();
        }
        return driver;
    }

    public static WebDriver getDriver1() {
        if (driver == null)
            driver = new ChromeDriver();
        else
            return driver;
        return driver;
    }
}

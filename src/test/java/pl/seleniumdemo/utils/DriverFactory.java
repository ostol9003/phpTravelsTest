package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(String name) {
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

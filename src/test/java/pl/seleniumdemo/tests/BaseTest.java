package pl.seleniumdemo.tests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pl.seleniumdemo.utils.DriverFactory;

import java.io.IOException;

@Listeners(value = {TestListener.class})
public class BaseTest {
    protected WebDriver driver;

    protected static ExtentSparkReporter html;
    protected static ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuit() {
        html = new ExtentSparkReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(html);
    }

    @AfterSuite
    public void AfterSuit() {
        extentReports.flush();
        extentReports.flush();
    }

    @BeforeMethod
    public void setup() throws IOException {
        driver = DriverFactory.getDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

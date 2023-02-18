import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest {
    @Test
    public void signUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.xpath("//a[text()=' My Account ']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        // worse way
        // driver.findElements(By.xpath("//a[text()='  Sign Up']").get(1).click());

        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        // random email
        int randomNumber = (int) (Math.random() *1000);
        String email = "marcin" + randomNumber;

        String lastname = "Ostolski";
        driver.findElement(By.name("firstname")).sendKeys("Marcin");
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("phone")).sendKeys("700800900");
        driver.findElement(By.name("email")).sendKeys(email + "@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123test");
        driver.findElement(By.name("confirmpassword")).sendKeys("123test");

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getAttribute("textContent").contains(lastname));
        Assert.assertEquals( heading.getAttribute("textContent"),"Hi, Marcin Ostolski");

//        driver.quit();

    }
}

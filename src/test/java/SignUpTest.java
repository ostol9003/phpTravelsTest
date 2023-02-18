import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
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
        int randomNumber = (int) (Math.random() * 1000);
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
        Assert.assertEquals(heading.getAttribute("textContent"), "Hi, Marcin Ostolski");

    }

    @Test
    public void signUpEmptySheetTest() {
        driver.findElements(By.xpath("//a[text()=' My Account ']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);


        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);


        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> failsList = driver.findElements(By.xpath("//p[contains(text(),'required')]"))
                .stream()
                .map(WebElement::getText)
                .toList();

        SoftAssert softAsert = new SoftAssert();

        List<String> fails = new ArrayList<>();
        fails.add("The Email field is required.");
        fails.add("The Password field is required.");
        fails.add("The Password field is required.");
        fails.add("The First name field is required.");
        fails.add("The Last Name field is required.");

        failsList.forEach(e -> softAsert.assertTrue(fails.contains(e)));

        softAsert.assertAll();
    }

    @Test
    public void signUpEmailFailTest() {
        driver.findElements(By.xpath("//a[text()=' My Account ']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);


        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);


        driver.findElement(By.name("firstname")).sendKeys("Marcin");
        driver.findElement(By.name("lastname")).sendKeys("Ostolski");
        driver.findElement(By.name("phone")).sendKeys("700800900");
        driver.findElement(By.name("email")).sendKeys("email");
        driver.findElement(By.name("password")).sendKeys("123test");
        driver.findElement(By.name("confirmpassword")).sendKeys("123test");

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> failsList = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream()
                .map(WebElement::getText)
                .toList();
        Assert.assertTrue(failsList.contains("The Email field must contain a valid email address."));
    }
}
